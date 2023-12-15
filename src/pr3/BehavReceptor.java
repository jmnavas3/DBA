package pr3;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BehavReceptor extends Behaviour {

    private static enum Paso {DAR_CODIGO, DAR_COORD};
    private Paso step = Paso.DAR_CODIGO;
    ACLMessage mensaje, respuesta;
    ArrayList<Coords> renos;

    @Override
    public void action() {
        DataStore ds = this.getDataStore();
        renos = (ArrayList<Coords>) ds.get("renos");
        String codigo = (String) ds.get("codigo");
        
        switch (step) {
            case DAR_CODIGO -> {
                ACLMessage msg = myAgent.blockingReceive();
                dialogo("déjame comprobar el código");

                if (msg.getPerformative() == ACLMessage.REQUEST &&
                        msg.getConversationId().equals(codigo)) {
                    dialogo("el código es correcto!");
                    responder(msg, ACLMessage.AGREE);
                    step = Paso.DAR_COORD;
                } else {
                    dialogo("CODIGO INCORRECTO!");
                    responder(msg, ACLMessage.REFUSE);
                    messageError();
                }
            }
            case DAR_COORD -> {
                ACLMessage msg = myAgent.blockingReceive();
                System.out.println("Mensaje del agente: " + msg.getContent());

                if (msg.getPerformative() == ACLMessage.INFORM) {
                    ACLMessage replay = msg.createReply(ACLMessage.INFORM);
                    replay.setContent("Hi back!");
                    this.myAgent.send(replay);
                } else {
                    messageError();
                }
            }
        }
    }
    
    public void messageError() {
        System.out.println("Error en la conversacion");
         myAgent.doDelete();
    }
    
    
    public void dialogo(String frase) {
        System.out.println(myAgent.getAID() + ": " + frase);
    }
    
    public void responder(ACLMessage mensaje, int perf) {
        respuesta = mensaje.createReply(perf);
        myAgent.send(respuesta);
    }
    
    public void dar_coord(ACLMessage mensaje, int perf) {
        respuesta = mensaje.createReply(perf);
        respuesta.setContent(renos.remove(0).toString());
        myAgent.send(respuesta);
    }

    @Override
    public boolean done() {
        return renos.isEmpty();
    }

}
