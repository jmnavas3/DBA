package pr3;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;

public class BehavMessage extends Behaviour {

    private int step = 0;
    Coords coordenadas = new Coords();
    AID rudolph = new AID("Rudolph", AID.ISLOCALNAME);
    AID santa = new AID("Santa", AID.ISLOCALNAME);
    private String codigo = "code";
    DataStore ds;
    Enviroment env;

    @Override
    public void action() {
        ds = this.getDataStore();
        
        switch (step) {
            
            case 0 ->{      //Ask SantaCode
                ds = this.getDataStore();
        
                ACLMessage preguntaSanta = new ACLMessage(ACLMessage.REQUEST);
                preguntaSanta.addReceiver(santa);
        
                preguntaSanta.setContent("Hola santa. ¿He sido un chico bueno?");
                myAgent.send(preguntaSanta);
        
                ACLMessage respuestaSanta = myAgent.blockingReceive();
                dialogo(respuestaSanta.getContent());
        
                if(respuestaSanta.getContent().equals("No has sido bueno")){
                    // Si no has sido bueno deja se borra el agente
                    //myAgent.doDelete();
                    dialogo("Deberia terminar aqui");
                }
                else if(respuestaSanta.getContent().equals("CodigoRudolph")){      //El codigo: CodigoRudolph debe ser igual al que necesite Rudolph para dar renos
                    dialogo("Tengo el codigo y empiezo a buscar renos");
                    codigo = respuestaSanta.getContent();
                    dialogo(codigo);
                    step = 1;
                }
                    
            }
            case 1 -> {
                ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                msg.addReceiver(rudolph);
                dialogo("toma el codigo");
                msg.setConversationId(codigo);
                myAgent.send(msg);
                
                // esperamos a que rudolph nos diga si el código es correcto o no
                ACLMessage response = myAgent.blockingReceive();
                if (response.getPerformative() == ACLMessage.AGREE) {
                    step = 2;
                } else if (response.getPerformative() == ACLMessage.REFUSE) {
                    messageError("A Rudolph no le gusta mi codigo");
                } else {
                    messageError("Error en paso " + step);
                }
            }
            case 2 -> {
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
                        step = 3;
                        // esto habría que hacerlo una vez que se comunique con santa
                         ds.put("buscasanta", true);
                    } else {
                        messageError("Error en paso " + step);
                    }
                    
                    this.setDataStore(ds);
                }
            }
            case 3 -> {
                if (! (boolean)ds.get("buscasanta")) {
                    ACLMessage msg = myAgent.blockingReceive();

                    if (msg.getConversationId().equals("apto")
                            && msg.getPerformative() == ACLMessage.INFORM) {
                        System.out.println("Mensaje de Rudolph: " + msg.getContent());
                    } else {
                        messageError("Error en paso " + step);
                    }
                } else {    //Cuando nos hemos quedado sin renos y vamos a buscar a santa
                    dialogo("Voy a ir pedir las coords a Santa...");
                    ACLMessage coord_santa = new ACLMessage(ACLMessage.REQUEST);
                    coord_santa.addReceiver(santa);
                    myAgent.send(coord_santa);
                    
                    ACLMessage msg = myAgent.blockingReceive();
                    //coord_request.setConversationId(codigo);
                    parseCoords(msg.getContent());
                    
                    //ds.put("fin", true);
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
