package pr3;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.core.Agent;
import jade.core.behaviours.DataStore;


public class BehavSantaResponse extends Behaviour {
    
    DataStore ds;
    ACLMessage respuesta;
    AID agente = new AID("pathFinder", AID.ISLOCALNAME);
    public final Coords posicion = new Coords(3,3);
    int step = 0;
    
    @Override
    public void action() {
        ds = this.getDataStore();
        
        switch (step) {
            case 0 -> {
                ACLMessage msg = myAgent.blockingReceive();
                int perf;
        
                int aleatorio = (int)Math.floor(Math.random()*10);
                dialogo("Voy a ver si eres digno o no (resultado entre 1 y 8), el resultado es: " + aleatorio);

                if ( msg.getPerformative() == ACLMessage.REQUEST ) {
                    if (aleatorio > 1 && aleatorio < 9) {
                        dialogo("Eres digno, toma el codigo y daselo a Rudolph");
                        respuesta = msg.createReply(ACLMessage.AGREE);
                        respuesta.setContent("CodigoRudolph");
                        step = 1;
                    } else {
                        dialogo("No eres digno");
                        respuesta = msg.createReply(ACLMessage.REFUSE);
                    }

                    myAgent.send(respuesta);
                }
            }
            
            case 1 -> {
            //Aqui representa cuando Santa espera a que le soliciten su posicion
            
                ACLMessage despedida = myAgent.blockingReceive();
                
                if (despedida.getPerformative() == ACLMessage.REQUEST){
                    respuesta = despedida.createReply(ACLMessage.INFORM);
                    respuesta.setContent(posicion.toString());
                    dialogo("Aqui tienes mi posicion: "+ respuesta.getContent());
                    myAgent.send(respuesta);
                    
                    step = 2;
                }
                else{
                    dialogo("el ultimo mensaje a santa no se ha recibido correctamente");
                    ds.put("termino", true);
                    this.setDataStore(ds);
                }
            
            }
            
             case 2 -> {
                ACLMessage hohoho = myAgent.blockingReceive();
                if(hohoho.getPerformative() == ACLMessage.INFORM){
                    dialogo("HOHOHO!");
                    ds.put("termino", true);
                    this.setDataStore(ds);
                }
             }
            
        }
    }
    

    public void responder(ACLMessage mensaje, int perf) {
        respuesta = mensaje.createReply(perf);
        respuesta.setContent("CodigoRudolph");
        myAgent.send(respuesta);
    }


    public void dialogo(String frase) {
        System.out.println(myAgent.getAID().getLocalName() + ": " + frase);
    }


    public void messageError(String info) {
        dialogo(info);
         myAgent.doDelete();
    }

    
    @Override
    public boolean done() {
        ds = this.getDataStore();
        return (boolean) ds.get("termino");
    }
    
}
