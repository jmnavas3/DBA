package pr3;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;
import java.util.HashSet;


public class AgentSanta extends Agent {
    public DataStore sharedDataStore;
    public int step = 0;
    private boolean finishConversation = false;

    @Override
    protected void setup() {
        
        Behaviour behavMessage = new MessageBehav();
        addBehaviour(behavMessage);
    /*
        ACLMessage msg = new ACLMessage();
        msg.addReceiver(new AID("agentReceive", AID.ISLOCALNAME));
        msg.setContent("Hola agente");
        send(msg);
        
        */
    /*
        ACLMessage msg= new ACLMessage();
        
        msg.addReceiver(new AID("Rudolph",AID.ISLOCALNAME));
        //peticion.setConversationId("id");
        msg.setContent("HOLA");
        
        send(msg);
    */
       // System.out.println("Peticion recibida: " + peticion.getContent());
    
    
        /*
        addBehaviour(new OneShotBehaviour(){
        
            @Override
            public void action(){
                
                ACLMessage msg = new ACLMessage();
                msg.setContent("Contenido del mensaje");
                msg.addReceiver(new AID("Rudolph", AID.ISLOCALNAME));   //el valor del AID tiene que coincidir con el nombre dado al crear el agente
                
                send(msg);
                System.out.println("Enviando mensaje");
                
            }
        });
        */
   
        
        //System.out.println("Valor de step :" + step);
            
    }   //Setup



       /* 
        // Comunicacion por protocolos 
        switch(this.step){
            case 0 -> {
                System.out.println("Comienza Santa");
        
                ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
      
                msg.addReceiver(new AID("Rudolph", AID.ISLOCALNAME));
                
                msg.setConversationId("conversation_ID");

                //msg.setContent("Hola agente");

                send(msg);
                
                this.step = 1;
            
            }
            
            case 1 -> {
                System.out.println("Ha enviado el mensaje y espera a recibirlo");
                
                ACLMessage msg = this.blockingReceive();
                
                if (msg.getConversationId().equals("greeting-conversation") && msg.getPerformative() == ACLMessage.AGREE) {
                    
                    ACLMessage replay = msg.createReply(ACLMessage.INFORM);
                    
                    replay.setContent("Estoy diciendo hola mendiante una conversacion protocolizada");
                    
                    System.out.println("Content of the replay message: " + msg.getContent());
                   
                   // this.finishConversation = true;
                    this.send(replay);
                    System.out.println("End of conversation");
                    this.step = 2;
                    
                } else {
                        System.out.println("Error in the coversation protocol");
                        this.doDelete();
                }
            }
                
            case 2 -> {
                ACLMessage msg = this.blockingReceive();
                
                if (msg.getConversationId().equals("greeting-conversation") && msg.getPerformative() == ACLMessage.AGREE) {
                    System.out.println("Agent receive: " + msg.getContent());
                }
                else {
                    System.out.println("Error in the conversation protocol - step" + 2);
                }
                this.doDelete();
            }
            
            
            
        }
                
     // Fin ComProtocolos
     */
   
        
} //Action
    
    
    


