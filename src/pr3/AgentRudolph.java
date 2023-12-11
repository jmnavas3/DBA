package pr3;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.DataStore;

import jade.lang.acl.ACLMessage;
import jade.core.AID;
import javax.swing.JOptionPane;


public class AgentRudolph extends Agent {
    public DataStore sharedDataStore;
    //private int step = 0;
    
    @Override
    protected void setup() {

        ACLMessage msg = blockingReceive();
        System.out.print("recibiendo mensaje: " + msg.getContent());
        
        ACLMessage msg2 = new ACLMessage();
        msg2.addReceiver(new AID("Santa",AID.ISLOCALNAME));
        msg2.setContent("Mensaje NUEVO");
        send(msg2);
        
        //ACLMessage replay = msg.createReply();
        //replay.setContent("Nuevo mensaje");
        //send(replay);
    
    
        
        
        
        /*
        
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action(){
                
            ACLMessage msg = receive();
            if(msg!=null){
                System.out.println("Mensaje recibido" + msg.getContent());
                JOptionPane.showMessageDialog(null," Mensaje recibido " + msg.getContent());
            }
            else{ block();
            }
                
            }
            });
        
        */
        
    


        /*
        ACLMessage msg = this.blockingReceive();
        System.out.println("Received this message: " + msg.getContent());
        ACLMessage replay = msg.createReply();
        replay.setContent("Good, nice to meet you!");
        this.send(replay);
        
        
        System.out.println("Aqui comienza rudolf");
        
    //    ACLMessage msg = this.blockingReceive();
        
    //    System.out.println("Received this message: " + msg.getContent());
        
    //    ACLMessage replay = msg.createReply();
        
    //    replay.setContent("Good, nice to meet you!");
        
    //    this.send(replay);
        
        
        switch (this.step){
            case 0 -> {
                System.out.println("Entra en el switch");
                ACLMessage msg = this.blockingReceive();
                System.out.println(msg);
                if (msg.getPerformative() == ACLMessage.REQUEST){
                    ACLMessage replay = msg.createReply(ACLMessage.AGREE);
                    
                    this.send(replay);
                    
                    this.step = 1;                    
                }
                else{
                    System.out.println("Error en el protocolo de conversacion 0");
                    this.doDelete();
                }
                
                
            }
                
            case 1 -> {
                ACLMessage msg = this.blockingReceive();
                
                System.out.println(msg);
                
                if(msg.getPerformative() == ACLMessage.INFORM){
                    ACLMessage replay = msg.createReply(ACLMessage.INFORM);
                    replay.setContent("Hola, caso 1 aceptado");
                    this.send(replay);
                }
                else{
                    System.out.println("Error en el protocolo de conversacion 1");
                    this.doDelete();
                }
                
            }
                
            
            
            
                
            } //Switch
        
        
        
        
        } //Setup
    
}   //Agent

        /*
        sharedDataStore = new DataStore();
        Enviroment env = new Enviroment();
        env.setAgentPosition(0,1);
        env.setGoalPosition(9,4);
        
        sharedDataStore.put("enviroment", env);
        
        // Behaviour that moves the agent
        Behaviour behavMoveAgent = new BehavMoveAgent();
        behavMoveAgent.setDataStore(sharedDataStore);
        
        
        // Behaviour that calculate the next move
        Behaviour behavUtility = new BehavUtility();
        behavUtility.setDataStore(sharedDataStore);
        
        
        // Behaviour that update sensors
        Behaviour behavSee = new BehavSee();
        behavSee.setDataStore(sharedDataStore);
        
        addBehaviour(behavSee);
        addBehaviour(behavUtility);
        addBehaviour(behavMoveAgent);
        */

    }//Setup


}   //Agent
