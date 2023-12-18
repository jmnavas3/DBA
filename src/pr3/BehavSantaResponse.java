package pr3;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.core.Agent;
import jade.core.behaviours.DataStore;


/**
 *
 * @author galvez
 */
public class BehavSantaResponse extends Behaviour {
    
    DataStore ds ;
    ACLMessage respuesta;   // Mensaje que lleva como contenido la palabra clave
    public final Coords posicion = new Coords(3,3);
    int step = 0;
    
    @Override
    public void action() {
        ds = this.getDataStore();
        
        ACLMessage msg = myAgent.blockingReceive();
        System.out.println("pathFinder: " + msg.getContent());
        
        int aleatorio = (int)Math.floor(Math.random()*10);
        System.out.println("Santa: Creando una respuesta para decir si eres digno o no (si es entre 1 y 8 eres digno), el resultado es: " + aleatorio);
        
        ACLMessage respuestaSantaMala = new ACLMessage();
        
        if( msg.getPerformative() == ACLMessage.REQUEST ){
            
            if (1 <= aleatorio && aleatorio <= 8){
                System.out.println("Santa: Eres digno, ve con Rudolph ");
                //responder(msg, ACLMessage.AGREE);
                respuesta = msg.createReply(ACLMessage.AGREE);
                respuesta.setContent("CodigoRudolph");
                myAgent.send(respuesta);
                //ds.put("terminar", true);
            } else {
                respuestaSantaMala.addReceiver(new AID("pathFinder", AID.ISLOCALNAME));
                respuestaSantaMala.setContent("No eres digno");
                myAgent.send(respuestaSantaMala);
                //ds.put("terminar", true);
            }
            this.setDataStore(ds);
        }
        
        ACLMessage despedida = myAgent.blockingReceive();
        if (despedida.getPerformative() == ACLMessage.REQUEST){
            System.out.println("¿Me preguntas donde estoy");
            respuesta = despedida.createReply(ACLMessage.AGREE);
            respuesta.setContent(posicion.toString());
            myAgent.send(respuesta);
            
            ds.put("terminar", true);
        }
        else{
            System.out.println("el ultimo mensaje a santa no se ha recibido correctamente");
            ds.put("terminar", true);
        }

        
        
    }
    
    public void responder(ACLMessage mensaje, int perf) {
        respuesta = mensaje.createReply(perf);
        respuesta.setContent("CodigoRudolph");
        myAgent.send(respuesta);
    }
    
    @Override
    public boolean done() {
        //ds = this.getDataStore();
        return (boolean) ds.get("terminar"); //Deberia de ser despues de
    }
    
}
