package pr3;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;

public class BehavMessage extends Behaviour {

    private int step = 0;
    Coords coordenadas = new Coords();
    boolean buscaReno = false;
    boolean buscaSanta = false;
    DataStore ds;

    @Override
    public void action() {
        ds = this.getDataStore();
        buscaReno = (boolean) ds.get("buscareno");
        
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
                if (!buscaReno) {
                    // esperamos a que rudolph nos diga algo (buscareno, buscasanta)
                    ACLMessage msg = myAgent.blockingReceive();

                    if (msg.getConversationId().equals("code") &&
                        msg.getPerformative() == ACLMessage.AGREE)
                    {
                        parseCoords(msg.getContent());
                        ds.put("coordenadas", coordenadas);

                        ACLMessage replay = msg.createReply(ACLMessage.INFORM);
                        replay.setContent("Hi");
                        this.myAgent.send(replay);

                    } else if (msg.getPerformative() == ACLMessage.INFORM) { // buscar a santa
                        step = 2;
                    } else {
                        messageError();
                    }
                }
            }
            case 2 -> {
                buscaSanta = (boolean) ds.get("buscasanta");
                if (!buscaSanta) {
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
    }

    public void messageError() {
        System.out.println("Error en la conversacion");
        myAgent.doDelete();
    }
    
    public void parseCoords(String msg) {
        coordenadas = new Coords(msg);
        
        if ( !coordenadas.validar() ) {
            System.out.println("No se han obtenido bien las coordenadas");
            messageError();
        }
        ds.put("buscareno", coordenadas.validar());
    }

    @Override
    public boolean done() {
        ds = this.getDataStore();
        return (boolean) ds.get("fin");
    }

}
