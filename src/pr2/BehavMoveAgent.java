/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pr2;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;
import jade.core.behaviours.OneShotBehaviour;

/**
 *
 * @author galvez
 */
public class BehavMoveAgent extends Behaviour{
    
    @Override
    public void action() {
        DataStore ds = this.getDataStore();
        
        Enviroment env = (Enviroment) ds.get("enviroment");
        
        String nextAction = env.getAction();
        
        env.showMapStatus();
        env.doMoveAction(nextAction);
        
        
        ds.put("enviroment", env);
        
        this.setDataStore(ds);
    }
    
   @Override
    public boolean done() {
        // Return true when the agents moves to the goal.
        DataStore ds = this.getDataStore();
        
        Enviroment env = (Enviroment) ds.get("enviroment");
        
        boolean goalReached = env.checkGoal();
        
        if (goalReached)
            System.out.print("The agent has reached the goal");
        
        return goalReached;
    }
}
