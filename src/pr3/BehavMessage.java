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
        
                preguntaSanta.setContent("Hola santa. ¿He sido un chico bueno?");
                myAgent.send(preguntaSanta);
                
                preguntaSanta = myAgent.blockingReceive();
                //dialogo(respuestaSanta.getContent());
        
                if(preguntaSanta.getContent().equals("No has sido bueno")){
                    // Si no has sido bueno deja se borra el agente
                    //myAgent.doDelete();
                    dialogo("Deberia terminar aqui");
                }
                else if(preguntaSanta.getContent().equals("CodigoRudolph")){      //El codigo: CodigoRudolph debe ser igual al que necesite Rudolph para dar renos
                    dialogo("Tengo el codigo y empiezo a buscar renos");
                    codigo = preguntaSanta.getContent();
                    //dialogo(codigo);
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
                if (! (boolean)ds.get("buscareno")) {
                    // pedimos las coordenadas a rudolph
                    ACLMessage coord_request = new ACLMessage(ACLMessage.REQUEST);
                    coord_request.addReceiver(rudolph);
                    coord_request.setConversationId(codigo);
                    dialogo("quedan renos que buscar?");
                    myAgent.send(coord_request);
                    // esperamos a que rudolph nos diga algo (coordenadas, buscasanta)
                    ACLMessage msg = myAgent.blockingReceive();

                    if (msg.getConversationId().equals(codigo) && msg.getPerformative() == ACLMessage.AGREE)
                    {
                        parseCoords(msg.getContent());
                        ds.put("coordenadas", coordenadas);
                    } else if (msg.getPerformative() == ACLMessage.DISCONFIRM) { // buscar a santa
                        dialogo("De acuerdo, me voy a buscarlo!");
                        step = 3;
                        // esto habría que hacerlo una vez que se comunique con santa
                        //ds.put("buscasanta", true);
                    } else {
                        messageError("Error en paso " + step);
                    }
                    
                    this.setDataStore(ds);
                }
            }
            case 3 -> {
                
                //Cuando nos hemos quedado sin renos y vamos a buscar a santa
                if (! (boolean)ds.get("buscasanta")) {
                    dialogo("Voy a pedir las coords a Santa...");
                    myAgent.send(preguntaSanta.createReply(ACLMessage.REQUEST));
                    System.out.println("A quien va dirigido preguntaSanta: " + preguntaSanta.getSender().toString());
                    
                    
                    //ACLMessage coord_santa = new ACLMessage(ACLMessage.REQUEST);
                    //coord_santa.addReceiver(santa);
                    //myAgent.send(coord_santa);
                    
                    ACLMessage msg = myAgent.blockingReceive();
                    
                    //coord_request.setConversationId(codigo);
                    coordenadas = new Coords(msg.getContent());
                    
                    if ( !coordenadas.validar() ) {
                        messageError("Estas coordenadas están mal");
                    }
                    else{
                        
                        dialogo("Gracias, voy a buscarte " + coordenadas.mostrar());
                        //ds.put("buscasanta", coordenadas.validar());
                        env = (Enviroment) ds.get("enviroment");
                        env.setGoalPosition(coordenadas.x, coordenadas.y);
                        ds.put("buscasanta", true);
                        ds.put("enviroment", env);
                        step = 4;
                        ds.put("coordenadas", coordenadas);
                        this.setDataStore(ds);
                    }
                    
                    //parseCoords(msg.getContent());
                }
            }
            
            case 4 -> {
                if ( (boolean)ds.get("fin")){
                    System.out.println("He terminado de buscar a santa");
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
