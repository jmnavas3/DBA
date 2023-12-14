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
        
<<<<<<< HEAD
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

=======
        String action = "nothing";
        double pared = 10000.0;
        double visitada = 10.0;
        int nextPosX, nextPosY;
        int agentX = env.getAgentPositionX();
        int agentY = env.getAgentPositionY();
        int[] pairNextPos = {0,0};
>>>>>>> 774dfb96572aa37b2cb0846759119f8e5bc5f385
        int[][] sensors = env.getSensors();
        
<<<<<<< HEAD
        for(int i=-1; i < sensors.length-1; i++){
            for(int j=-1; j < sensors.length-1; j++){
                if(sensors[i+1][j+1] == -1){
                    env.setUtility(pared, agentX+i, agentY+j);
                } else{
                    env.setUtility(env.distance(agentX+i, agentY+j),agentX+i, agentY+j);
                }
=======
        for(int i=-1; i < sensors.length-1; i++){          
            for(int j=-1; j < sensors.length-1; j++){      
                
                if(sensors[i+1][j+1] == -1){ // pared se penaliza
                    env.addUtility(pared, agentX+i, agentY+j);
                }
                else{
                    env.addUtility(env.distance(agentX+i, agentY+j),agentX+i, agentY+j);
                }
                
                if(i==0 && j==0){   // posición del agente se penaliza
                    env.addUtility(visitada, agentX+i, agentY+j);
                }
>>>>>>> 774dfb96572aa37b2cb0846759119f8e5bc5f385
            }
        }

        pairNextPos = env.getMinPosUtility(agentX, agentY);
        nextPosX = pairNextPos[0];
        nextPosY = pairNextPos[1];
        
<<<<<<< HEAD
        // Criterio de decisión simple 
        if(agentX > goalX && sensors[0][1] != -1){
=======
        System.out.println("next pos: "+nextPosX+"," +nextPosY);
        
        if(nextPosX < agentX && nextPosY == agentY){
>>>>>>> 774dfb96572aa37b2cb0846759119f8e5bc5f385
            action = "moveUp";
        } else if (nextPosX > agentX && nextPosY == agentY){
            action = "moveDown";
        } else if (nextPosX == agentX && nextPosY < agentY){
            action = "moveLeft";
        } else if (nextPosX == agentX && nextPosY > agentY){
            action = "moveRight";
<<<<<<< HEAD
        } else {
            action = "moveRight";
=======
        } else if(nextPosX < agentX && nextPosY < agentY){
            action = "moveUpLeft";
        } else if (nextPosX < agentX && nextPosY > agentY){
            action = "moveUpRight";
        } else if (nextPosX > agentX && nextPosY < agentY){
            action = "moveDownLeft";
        } else if (nextPosX > agentX && nextPosY > agentY){
            action = "moveDownRight";
>>>>>>> 774dfb96572aa37b2cb0846759119f8e5bc5f385
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
