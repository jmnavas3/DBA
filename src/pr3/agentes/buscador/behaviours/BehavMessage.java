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
    ACLMessage preguntaSanta;
    DataStore ds;
    Enviroment env;
    

    @Override
    public void action() {
        ds = this.getDataStore();
        
        switch (step) {
            
            case 0 ->{ //Ask SantaCode
                ds = this.getDataStore();
        
                preguntaSanta = new ACLMessage(ACLMessage.REQUEST);
                preguntaSanta.addReceiver(santa);
                dialogo("Soy digno de realizar la mision?");
                myAgent.send(preguntaSanta);
                
                preguntaSanta = myAgent.blockingReceive();
        
                if(preguntaSanta.getPerformative() == ACLMessage.AGREE) {
                    step = 1;
                    codigo = preguntaSanta.getContent();
                    dialogo("Voy a darselo a Rudolph");
                } else {
                    dialogo("Voy a intentarlo de nuevo");
                }
            }
            case 1 -> {
                ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                msg.addReceiver(rudolph);
                dialogo("Hola Rudolph, es correcto este codigo?");
                msg.setConversationId(codigo);
                myAgent.send(msg);
                
                // esperamos a que rudolph nos diga si el código es correcto o no
                ACLMessage response = myAgent.blockingReceive();
                if (response.getPerformative() == ACLMessage.CONFIRM) {
                    step = 2;
                } else if (response.getPerformative() == ACLMessage.DISCONFIRM) {
                    messageError("Oh no! Este codigo no ha servido!");
                } else {
                    messageError("Error en paso " + step);
                }
            }
            case 2 -> {
                if (! (boolean)ds.get("buscareno")) {
                    // pedimos las coordenadas a rudolph
                    ACLMessage coord_request = new ACLMessage(ACLMessage.REQUEST);
                    coord_request.addReceiver(rudolph);
                    coord_request.setConversationId(codigo);
                    dialogo("quedan renos que buscar?");
                    myAgent.send(coord_request);
                    // esperamos a que rudolph nos diga algo (coordenadas, buscasanta)
                    ACLMessage msg = myAgent.blockingReceive();

                    if ( msg.getContent().equals("buscasanta") ) {
                        step = 3;
                        dialogo("Vale, voy a pedirle su ubicacion");
                    } else {
                        parseCoords(msg.getContent());
                        dialogo("Gracias! voy a " + coordenadas.mostrar());
                        ds.put("coordenadas", coordenadas);
                    }
                    
                    this.setDataStore(ds);
                }
            }
            case 3 -> {
                
                //Cuando nos hemos quedado sin renos y vamos a buscar a santa
                if (! (boolean)ds.get("buscasanta")) {
                    dialogo("Santa, pasame tu ubicacion");
                    myAgent.send(preguntaSanta.createReply(ACLMessage.REQUEST));
                    
                    ACLMessage msg = myAgent.blockingReceive(); 
                    parseCoords(msg.getContent());
                }
            }
            
            case 4 -> {
                if ( (boolean)ds.get("fin")){
                    dialogo("Te he encontrado Santa!");
                    myAgent.send(preguntaSanta.createReply(ACLMessage.INFORM));
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

        if (step == 2) {
            ds.put("buscareno", true);
        } else if (step == 3) {
            step = 4;
            ds.put("buscasanta", true);
        }

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
