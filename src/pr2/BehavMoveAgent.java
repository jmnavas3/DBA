/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pr2;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;

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
        
        env.doMoveAction(nextAction);
        env.showPath();
        
        ds.put("enviroment", env);
        
        this.setDataStore(ds);
    }
    
   @Override
    public boolean done() {
        // Return true when the agents moves to the goal.
        DataStore ds = this.getDataStore();
        
        Enviroment env = (Enviroment) ds.get("enviroment");
        
        return env.checkGoal();
    }
}
