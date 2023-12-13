package pr3;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class BehavReceptor extends Behaviour {

    private static enum Paso {DAR_CODIGO, DAR_COORD};
    private Paso step = Paso.DAR_CODIGO;
    ACLMessage mensaje;

    @Override
    public void action() {
        switch (step) {
            case DAR_CODIGO -> {
                ACLMessage msg = myAgent.blockingReceive();
                System.out.println("Mensaje del agente: " + msg.getContent());

                if (msg.getPerformative() == ACLMessage.REQUEST) {
                    ACLMessage replay = msg.createReply(ACLMessage.AGREE);
                    this.myAgent.send(replay);
                    step = Paso.DAR_COORD;
                } else {
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

    @Override
    public boolean done() {
        return true;
    }

}
