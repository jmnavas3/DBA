package pr3;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author joy111
 */
public class BehavMessage extends CyclicBehaviour {

    private int step = 0;

    @Override
    public void action() {

        switch (step) {

            case 0 -> {
                ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);

                msg.addReceiver(new AID("Rudolph", AID.ISLOCALNAME));
                msg.setContent("Hola Rudolph");
                msg.setConversationId("apto");
                myAgent.send(msg);

                step = 1;
            }

            case 1 -> {
                ACLMessage msg = myAgent.blockingReceive();

                if (msg.getConversationId().equals("apto")
                        && msg.getPerformative() == ACLMessage.AGREE) {
                    ACLMessage replay = msg.createReply(ACLMessage.INFORM);
                    replay.setContent("Hi");
                    this.myAgent.send(replay);

                    step = 2;
                } else {
                    System.out.println("Error en la conversacion");
                    // myAgent.doDelete();
                }
            }

            case 2 -> {
                ACLMessage msg = myAgent.blockingReceive();

                if (msg.getConversationId().equals("apto")
                        && msg.getPerformative() == ACLMessage.INFORM) {
                     System.out.println("Mensaje de Rudolph: " + msg.getContent());
                } else {
                    System.out.println("Error en la conversacion");
                    // myAgent.doDelete();
                }
            }
        }
    }

}
