package pr3;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;


public class BehavReceptor extends CyclicBehaviour {

    private int step = 0;

    @Override
    public void action() {

        switch (step) {

            case 0 -> {
                ACLMessage msg = myAgent.blockingReceive();
                System.out.println(msg);

                if (msg.getPerformative() == ACLMessage.REQUEST) {
                    ACLMessage replay = msg.createReply(ACLMessage.AGREE);
                    this.myAgent.send(replay);

                    step = 1;
                } else {
                    System.out.println("Error en la conversacion");
                    // myAgent.doDelete();
                }
            }

            case 1 -> {
                ACLMessage msg = myAgent.blockingReceive();
                System.out.println(msg);

                if (msg.getPerformative() == ACLMessage.INFORM) {
                    ACLMessage replay = msg.createReply(ACLMessage.INFORM);
                    replay.setContent("Hi back!");
                    this.myAgent.send(replay);
                } else {
                    System.out.println("Error en la conversacion");
                    // myAgent.doDelete();
                }
            }
        }
    }

}
