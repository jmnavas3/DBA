package pr3;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;


public class AgentSanta extends Agent {
    public DataStore sharedDataStore;
    public int step = 0;
    private boolean finishConversation = false;

    @Override
    protected void setup() {
    /*
        ACLMessage msg = new ACLMessage();
        msg.addReceiver(new AID("agentReceive", AID.ISLOCALNAME));
        msg.setContent("Hola agente");
        send(msg);
        
        */
        ACLMessage peticion= new ACLMessage(ACLMessage.REQUEST);
        peticion.addReceiver(new AID("Rudolph",AID.ISLOCALNAME));
        //peticion.setConversationId("id");
        send(peticion);
        System.out.println("Peticion recibida: " + peticion.getContent());
    
    
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
        
        
        /*
        switch (step) {
                    
            case 0 -> {
                System.out.println("Valor de step: " + step);
                ACLMessage msg = new ACLMessage();
                msg.addReceiver(new AID("Rudolph",AID.ISLOCALNAME));
                msg.setContent("Contenido del mensaje");
                msg.setConversationId("ID");
                send(msg);
                step = 1;
                System.out.println("Valor de step: " + step);
            }
                    
            case 1 -> {
                System.out.println("Entra en el case 1");
                ACLMessage msg = blockingReceive();
              /*if(msg.getConversationId().equals("ID")) {
                    System.out.println("El mensaje es:" + msg.getContent());
                }
                else
                    System.out.println("Error en la conversacion");
                        
                System.out.println("El mensaje es: " + msg.getContent());
            }
        }
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
      public int getStep(){
        return step;
    };
    
    public void setStep(int s){
        this.step = s;
    }; 
        
} //Action
    
    
    


