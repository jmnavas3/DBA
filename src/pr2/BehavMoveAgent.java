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
        env.showMapStatus();
        
        ds.put("enviroment", env);
        this.setDataStore(ds);
    }
    
   @Override
    public boolean done() {
        DataStore ds = this.getDataStore();
        Enviroment env = (Enviroment) ds.get("enviroment");
        boolean goalReached = env.checkGoal();
        
        if (goalReached)
            System.out.print("The agent has reached the goal");
        
        return goalReached;
    }
}
