package pr3;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;
import java.util.ArrayList;

public class BehavReceptor extends Behaviour {

    private static enum Paso {RECIBIR_CODIGO, DAR_COORD};
    private Paso step = Paso.RECIBIR_CODIGO;
    ACLMessage respuesta;
    ArrayList<Coords> renos;
    DataStore ds;

    @Override
    public void action() {
        ds = this.getDataStore();
        renos = (ArrayList<Coords>) ds.get("renos");
        String codigo = (String) ds.get("codigo");
        
        switch (step) {
            case RECIBIR_CODIGO -> {
                ACLMessage check_code = myAgent.blockingReceive();
                dialogo("dejame comprobar el codigo");

                if (check_code.getPerformative() == ACLMessage.REQUEST &&
                        check_code.getConversationId().equals(codigo)) {
                    dialogo("el codigo es correcto!");
                    responder(check_code, ACLMessage.AGREE);
                    step = Paso.DAR_COORD;
                } else {
                    responder(check_code, ACLMessage.REFUSE);
                    messageError("CODIGO INCORRECTO!");
                }
            }
            case DAR_COORD -> {
                // esperamos a que nos pida unas coordenadas
                ACLMessage request_coords = myAgent.blockingReceive();
                dialogo("voy a ver mi radar...");

                if (request_coords.getPerformative() == ACLMessage.REQUEST) {
                    // comprobamos si quedan renos y respondemos
                    dar_coord(request_coords);
                } else {
                    messageError("Error en paso " + step);
                }
            }
        }
    }
    
    public void messageError(String info) {
        dialogo(info);
         myAgent.doDelete();
    }
    
    
    public void dialogo(String frase) {
        System.out.println(myAgent.getAID().getLocalName() + ": " + frase);
    }
    
    public void responder(ACLMessage mensaje, int perf) {
        respuesta = mensaje.createReply(perf);
        myAgent.send(respuesta);
    }
    
    public void dar_coord(ACLMessage mensaje) {
        respuesta = mensaje.createReply(ACLMessage.AGREE);
        
        if (!renos.isEmpty()) {
            dialogo("Si! quedan " + renos.size() + ". Aqui tienes su ubicacion");
            respuesta.setContent(renos.remove(0).toString());
            ds.put("renos", renos);
        } else {
            dialogo("Ya no queda ninguno, ve con Santa!");
            respuesta = mensaje.createReply(ACLMessage.DISCONFIRM);
            respuesta.setContent("buscasanta");
            ds.put("terminar", true);
        }
        
        this.setDataStore(ds);
        myAgent.send(respuesta);
    }

    @Override
    public boolean done() {
        return (boolean) ds.get("terminar");
    }

}
