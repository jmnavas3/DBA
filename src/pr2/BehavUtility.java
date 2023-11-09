/*/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pr2;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;
import jade.core.Agent;

/**
 *
 * @author joy111
 */

public class BehavUtility extends Behaviour{
    
    @Override
    public void action(){
        DataStore ds = this.getDataStore();
        Enviroment env = (Enviroment) ds.get("enviroment");
        
        String action = "nothing";
        
        int goalX, goalY, agentX, agentY;
        
        goalX = env.getGoalPositionX();
        goalY = env.getGoalPositionY();
        agentX = env.getAgentPositionX();
        agentY = env.getAgentPositionY();
        
        /* Agente basico */
        if(agentX > goalX){
            action = "moveLeft";
        }
        else if(agentX < goalX){
            action = "moveRight";
        }
        else if(agentY > goalY){
            action = "moveUp";
        }
        else{
            action = "moveDown";
        }
        
        env.setUtility(action);  
        
        ds.put("enviroment", env);
        
        this.setDataStore(ds);
    }
    
    @Override
    public boolean done(){
        
        DataStore ds = this.getDataStore();
        
        Enviroment env = (Enviroment) ds.get("enviroment");
        
        return env.checkGoal();
    }
    
}
