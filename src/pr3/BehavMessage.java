package pr3;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BehavMessage extends Behaviour {

    private int step = 0;
    Coords coordenadas;

    @Override
    public void action() {
        DataStore ds = this.getDataStore();
        switch (step) {
            case 0 -> {
                ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);

                msg.addReceiver(new AID("Rudolph", AID.ISLOCALNAME));
                msg.setContent("toma el codigo");
                msg.setConversationId("code");
                myAgent.send(msg);

                step = 1;
            }
            case 1 -> {
                // esperamos a que rudolph nos dé unas coordenadas
                ACLMessage msg = myAgent.blockingReceive();

                if (msg.getConversationId().equals("code") &&
                    msg.getPerformative() == ACLMessage.AGREE)
                {
                    try {
                        coordenadas = (Coords) msg.getContentObject();
                        ds.put("coordenadas", coordenadas);
                        // bloqueamos esta behaviour hasta que se reciba una respuesta
                        block();
                    } catch (UnreadableException ex) {
                        Logger.getLogger(BehavMessage.class.getName()).log(Level.SEVERE, null, ex);
                        messageError();
                    }
                    ACLMessage replay = msg.createReply(ACLMessage.INFORM);
                    replay.setContent("Hi");
                    this.myAgent.send(replay);

                    step = 2;
                } else {
                    messageError();
                }
            }
            case 2 -> {
                ACLMessage msg = myAgent.blockingReceive();

                if (msg.getConversationId().equals("apto")
                        && msg.getPerformative() == ACLMessage.INFORM) {
                    System.out.println("Mensaje de Rudolph: " + msg.getContent());
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
