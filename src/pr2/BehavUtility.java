/*/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pr2;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;

/**
 *
 * @author joy111
 */

public class BehavUtility extends Behaviour{
    
    @Override
    public void action(){
        DataStore ds = this.getDataStore();
        Enviroment env = (Enviroment) ds.get("enviroment");
        
        env.criteria();
        
        /*String action = "nothing";
        
        int goalX, goalY, agentX, agentY;
        int pared = 10000;
        
        goalX = env.getGoalX();
        goalY = env.getGoalY();
        
        agentX = env.getAgentX();
        agentY = env.getAgentY();
        
//        System.out.println("posicion X objetivo: " + env.getGoalPositionX());
//        System.out.println("posicion Y objetivo: " + env.getGoalPositionY());
//        System.out.println("posicion X agente: " + env.getAgentPositionX());
//        System.out.println("posicion Y agente: " + env.getAgentPositionY());

        int[][] sensors = env.getSensors();
        int valorUtilidad;
        
        for(int i=-1; i < sensors.length-1; i++){
            for(int j=-1; j < sensors.length-1; j++){
                if(sensors[i+1][j+1] == -1){
                    env.setUtility(pared, agentX+i, agentY+j);
                } else{
                    env.setUtility(env.distance(agentX+i, agentY+j),agentX+i, agentY+j);
                }
            }
        }
        
        // Criterio de decisión simple 
        if(agentX > goalX && sensors[0][1] != -1){
            action = "moveUp";
        }
        else if(agentX < goalX && sensors[2][1] != -1){
            action = "moveDown";
        }
        else if(agentY > goalY && sensors[1][0] != -1){
            action = "moveLeft";
        }
        else if (agentY < goalY && sensors[1][2] != -1){
            action = "moveRight";
        } else {
            action = "moveRight";
        }
        env.setAction(action);  
        */
        
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
