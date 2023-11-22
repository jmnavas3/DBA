package pr2;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;


/**
 *
 * @author galvez
 */
public class BehavSee extends Behaviour {
    
    @Override
    public void action() {
        DataStore ds = this.getDataStore();
        Enviroment env = (Enviroment) ds.get("enviroment");
        env.see();
        ds.put("enviroment", env);
        this.setDataStore(ds);
    }
    
    @Override
    public boolean done() {
        DataStore ds = this.getDataStore();
        Enviroment env = (Enviroment) ds.get("enviroment");
        return env.checkGoal();
    }
}
