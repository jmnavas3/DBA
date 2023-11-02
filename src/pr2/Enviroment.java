/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pr2;

/**
 *
 * @author galvez
 */
public class Enviroment {
    private MapDto map;
    private int goalPositionX, goalPositionY;
    private int agentPositionX, agentPositionY;
    private int[] sensors;
    
    public Enviroment(MapDto map){
        
    }
    
    // Update agent position 
    public void doMoveAction(String action){
        switch (action) { 
            case "moveUp":
                agentPositionY = agentPositionY - 1;
                break;
            
            case "moveDown":
                agentPositionY = agentPositionY + 1;
                break;
                
            case "moveLeft":
                agentPositionX = agentPositionX - 1;
                break;
                
            case "moveRight":
                agentPositionX = agentPositionX + 1;
                break;
          }
    }
    
    // Update sensors
    public void see(){
        
    }
    
    public void setAgentPosition(int x, int y){
        
    }
    
    public void setGoalPosition(int x, int y){
        
    }
    
    public void setMap(MapDto map){
        
    }
    
    public String calculateUtility(){
        
        return "action";
    }
    
    // Function that returns true if the agent is in the goal position
    public boolean checkGoal(){
        boolean agentIsInGoalPos = false;
        if(agentPositionX == goalPositionX && agentPositionY == goalPositionY){
            agentIsInGoalPos = true;
        }
        return agentIsInGoalPos;
    }
}

