package pr3;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;
import java.util.ArrayList;

public class BehavReceptor extends Behaviour {

    DataStore ds;
    ACLMessage respuesta;
    ArrayList<Coords> renos;

    private static enum Paso {VALIDAR_CODIGO, DAR_COORD};
    private Paso step = Paso.VALIDAR_CODIGO;

    @Override
    public void action() {
        ds = this.getDataStore();
        renos = (ArrayList<Coords>) ds.get("renos");
        String codigo = (String) ds.get("codigo");

        switch (step) {
            case VALIDAR_CODIGO -> {
                ACLMessage check_code = myAgent.blockingReceive();
                dialogo("dejame comprobar el codigo");
                if (check_code.getPerformative() == ACLMessage.QUERY_IF
                        && check_code.getConversationId().equals(codigo)) {
                    dialogo("el codigo es correcto!");
                    responder(check_code, ACLMessage.CONFIRM);
                    step = Paso.DAR_COORD;
                } else {
                    responder(check_code, ACLMessage.DISCONFIRM);
                    messageError("CODIGO INCORRECTO!");
                }
            }
            
            case DAR_COORD -> {
                ACLMessage request_coords = myAgent.blockingReceive();
                dialogo("voy a ver mi radar...");
                if (request_coords.getPerformative() == ACLMessage.REQUEST
                        && request_coords.getConversationId().equals(codigo)) {
                    responder(request_coords, ACLMessage.INFORM);
                } else {
                    messageError("Error en paso " + step);
                }
            }
        }
    }

    public void dialogo(String frase) {
        System.out.println(myAgent.getAID().getLocalName() + ": " + frase);
    }

    public void responder(ACLMessage mensaje, int perf) {
        respuesta = mensaje.createReply(perf);
        if (step == Paso.DAR_COORD) dar_coord();
        myAgent.send(respuesta);
    }

    public void dar_coord() {
        if (!renos.isEmpty()) {
            dialogo("Si! quedan " + renos.size() + ". Aqui tienes su ubicacion");
            respuesta.setContent(renos.remove(0).toString());
            ds.put("renos", renos);
        } else {
            dialogo("Ya no queda ninguno, ve con Santa!");
            respuesta.setContent("buscasanta");
            ds.put("terminar", true);
        }
        this.setDataStore(ds);
    }
    
    public void messageError(String info) {
        dialogo(info);
        myAgent.doDelete();
    }

    @Override
    public boolean done() {
        return (boolean) ds.get("terminar");
    }

}
