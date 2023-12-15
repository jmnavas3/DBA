package pr3;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;

public class BehavMessage extends Behaviour {

    private int step = 0;
    Coords coordenadas = new Coords();
    AID rudolph = new AID("Rudolph", AID.ISLOCALNAME);
    private final String codigo = "code";
    DataStore ds;
    Enviroment env;

    @Override
    public void action() {
        ds = this.getDataStore();
        
        switch (step) {
            case 0 -> {
                ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                msg.addReceiver(rudolph);
                dialogo("toma el codigo");
                msg.setConversationId(codigo);
                myAgent.send(msg);
                
                // esperamos a que rudolph nos diga si el código es correcto o no
                ACLMessage response = myAgent.blockingReceive();
                if (response.getPerformative() == ACLMessage.AGREE) {
                    step = 1;
                } else if (response.getPerformative() == ACLMessage.REFUSE) {
                    messageError("A Rudolph no le gusta mi codigo");
                } else {
                    messageError("Error en paso " + step);
                }
            }
            case 1 -> {
                // si no estamos buscando renos
                if (! (boolean)ds.get("buscareno")) {
                    // pedimos las coordenadas a rudolph
                    ACLMessage coord_request = new ACLMessage(ACLMessage.REQUEST);
                    coord_request.addReceiver(rudolph);
                    coord_request.setConversationId(codigo);
                    dialogo("quedan renos que buscar?");
                    myAgent.send(coord_request);
                    // esperamos a que rudolph nos diga algo (coordenadas, buscasanta)
                    ACLMessage msg = myAgent.blockingReceive();

                    if (msg.getConversationId().equals(codigo) &&
                        msg.getPerformative() == ACLMessage.AGREE)
                    {
                        parseCoords(msg.getContent());
                        ds.put("coordenadas", coordenadas);
                    } else if (msg.getPerformative() == ACLMessage.DISCONFIRM) { // buscar a santa
                        dialogo("De acuerdo, me voy a buscarlo!");
                        step = 2;
                        // esto habría que hacerlo una vez que se comunique con santa
                         ds.put("buscasanta", true);
                    } else {
                        messageError("Error en paso " + step);
                    }
                    
                    this.setDataStore(ds);
                }
            }
            case 2 -> {
                if (! (boolean)ds.get("buscasanta")) {
                    ACLMessage msg = myAgent.blockingReceive();

                    if (msg.getConversationId().equals("apto")
                            && msg.getPerformative() == ACLMessage.INFORM) {
                        System.out.println("Mensaje de Rudolph: " + msg.getContent());
                    } else {
                        messageError("Error en paso " + step);
                    }
                } else {
                    dialogo("esperando a que santa llegue...");
                    ds.put("fin", true);
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
    
    public void parseCoords(String msg) {
        coordenadas = new Coords(msg);
        
        if ( !coordenadas.validar() ) {
            messageError("Estas coordenadas están mal");
        }
        
        dialogo("Gracias, voy a buscarlo a " + coordenadas.mostrar());
        ds.put("buscareno", coordenadas.validar());
        env = (Enviroment) ds.get("enviroment");
        env.setGoalPosition(coordenadas.x, coordenadas.y);
        ds.put("enviroment", env);
        this.setDataStore(ds);
    }

    @Override
    public boolean done() {
        ds = this.getDataStore();
        return (boolean) ds.get("fin");
    }

}
