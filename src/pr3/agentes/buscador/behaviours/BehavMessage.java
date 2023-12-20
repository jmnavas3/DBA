package pr3;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;

public class BehavMessage extends Behaviour {

    DataStore ds;
    Enviroment env;
    ACLMessage preguntaSanta;
    Coords coordenadas = new Coords();
    private String codigo = "codigoFalso";
    
    AID santa = new AID("Santa", AID.ISLOCALNAME);
    AID rudolph = new AID("Rudolph", AID.ISLOCALNAME);
    
    private static enum Paso {PROPONER_SANTA, DAR_CODIGO, BUSCAR_RENO, BUSCAR_SANTA, FINAL};
    private Paso step = Paso.PROPONER_SANTA;

    @Override
    public void action() {
        ds = this.getDataStore();

        switch (step) {
            case PROPONER_SANTA -> {
                preguntaSanta = new ACLMessage(ACLMessage.PROPOSE);
                preguntaSanta.addReceiver(santa);
                dialogo("Soy digno de realizar la mision?");
                myAgent.send(preguntaSanta);
                preguntaSanta = myAgent.blockingReceive();
                if (preguntaSanta.getPerformative() == ACLMessage.ACCEPT_PROPOSAL) {
                    step = Paso.DAR_CODIGO;
                    codigo = preguntaSanta.getContent();
                    dialogo("Voy a darselo a Rudolph");
                } else {
                    dialogo("Voy a intentarlo de nuevo");
                }
            }
            
            case DAR_CODIGO -> {
                ACLMessage msg = new ACLMessage(ACLMessage.QUERY_IF);
                msg.addReceiver(rudolph);
                msg.setConversationId(codigo);
                dialogo("Hola Rudolph, es correcto este codigo?");
                myAgent.send(msg);
                ACLMessage response = myAgent.blockingReceive();
                if (response.getPerformative() == ACLMessage.CONFIRM) {
                    step = Paso.BUSCAR_RENO;
                } else if (response.getPerformative() == ACLMessage.DISCONFIRM) {
                    messageError("Oh no! Este codigo no ha servido!");
                }
            }
            
            case BUSCAR_RENO -> {
                if ( !(boolean) ds.get("buscareno") ) {
                    ACLMessage coord_request = new ACLMessage(ACLMessage.REQUEST);
                    coord_request.addReceiver(rudolph);
                    coord_request.setConversationId(codigo);
                    dialogo("quedan renos que buscar?");
                    myAgent.send(coord_request);
                    ACLMessage msg = myAgent.blockingReceive();
                    if (msg.getContent().equals("buscasanta")) {
                        step = Paso.BUSCAR_SANTA;
                        dialogo("Vale, voy a pedirle su ubicacion");
                    } else {
                        parseCoords(msg.getContent());
                        dialogo("Gracias! voy a " + coordenadas.mostrar());
                    }
                }
            }
            
            case BUSCAR_SANTA -> {
                dialogo("Santa, pasame tu ubicacion");
                myAgent.send(preguntaSanta.createReply(ACLMessage.REQUEST));
                ACLMessage msg = myAgent.blockingReceive();
                parseCoords(msg.getContent());
            }

            case FINAL -> {
                if ((boolean) ds.get("fin")) {
                    dialogo("Te he encontrado Santa!");
                    myAgent.send(preguntaSanta.createReply(ACLMessage.INFORM));
                }
            }
        }
    }

    public void dialogo(String frase) {
        System.out.println(myAgent.getAID().getLocalName() + ": " + frase);
    }

    public void parseCoords(String msg) {
        coordenadas = new Coords(msg);
        if (step == Paso.BUSCAR_RENO) {
            ds.put("buscareno", true);
        } else if (step == Paso.BUSCAR_SANTA) {
            step = Paso.FINAL;
            ds.put("buscasanta", true);
        }
        env = (Enviroment) ds.get("enviroment");
        env.setGoalPosition(coordenadas.x, coordenadas.y);
        ds.put("enviroment", env);
        this.setDataStore(ds);
    }
    
    public void messageError(String info) {
        dialogo(info);
        myAgent.doDelete();
    }

    @Override
    public boolean done() {
        ds = this.getDataStore();
        return (boolean) ds.get("fin");
    }
}
