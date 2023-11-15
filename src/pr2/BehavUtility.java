/*/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pr2;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;
import java.util.Iterator;
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
        double pared = 10000.0;
        double visitada = 10.0;
        
        goalX = env.getGoalPositionX();
        
      //  System.out.println("posicion X objetivo: " + env.getGoalPositionX());
        goalY = env.getGoalPositionY();
        
     //   System.out.println("posicion Y objetivo: " + env.getGoalPositionY());
        
        agentX = env.getAgentPositionX();
     //   System.out.println("posicion X agente: " + env.getAgentPositionX());
        
        agentY = env.getAgentPositionY();
     //   System.out.println("posicion Y agente: " + env.getAgentPositionY());
        
        int[][] sensors = env.getSensors();
        int valorUtilidad;
        
        for(int i=-1; i < sensors.length-1; i++){          
            for(int j=-1; j < sensors.length-1; j++){      
                
                if(sensors[i+1][j+1] == -1){
                    env.addUtility(pared, agentX+i, agentY+j);
                }
                else{
                    env.addUtility(env.distance(agentX+i, agentY+j),agentX+i, agentY+j);
                }
                // Si es la posicion del agente se penaliza
                if(i==0 && j==0){
                    env.addUtility(visitada, agentX+i, agentY+j);
                }
            }
                
        }
        int[] pairNextPos = env.getMinPosUtility(agentX, agentY);
        int nextPosX = pairNextPos[0];
        int nextPosY = pairNextPos[1];
        
        System.out.println("next pos: "+nextPosX+"," +nextPosY);
        
        /* Agente que usa el mapa de utilidad */
        if(nextPosX < agentX && nextPosY == agentY){
            action = "moveUp";
        } else if (nextPosX > agentX && nextPosY == agentY){
            action = "moveDown";
        } else if (nextPosX == agentX && nextPosY < agentY){
            action = "moveLeft";
        } else if (nextPosX == agentX && nextPosY > agentY){
            action = "moveRight";
        } else if(nextPosX < agentX && nextPosY < agentY){
            action = "moveUpLeft";
        } else if (nextPosX < agentX && nextPosY > agentY){
            action = "moveUpRight";
        } else if (nextPosX > agentX && nextPosY < agentY){
            action = "moveDownLeft";
        } else if (nextPosX > agentX && nextPosY > agentY){
            action = "moveDownRight";
        }
        
        /* Agente basico 
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
        }*/
        
        env.setAction(action);  
        
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
