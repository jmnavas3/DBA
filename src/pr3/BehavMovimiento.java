package pr3;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;


/**
 *
 * @author galvez
 */
public class BehavMovimiento extends Behaviour {
    private Behaviour behavSee = new BehavSee();
    private Behaviour behavUtility = new BehavUtility();
    private Behaviour behavMoveAgent = new BehavMoveAgent();
    
    @Override
    public void action() {
        DataStore ds = this.getDataStore();
        
        if((boolean)ds.get("buscareno")){
            myAgent.addBehaviour(behavSee);
            myAgent.addBehaviour(behavUtility);
            myAgent.addBehaviour(behavMoveAgent);
        }
        
        
        this.setDataStore(ds);
    }
    
    @Override
    public boolean done() {
        DataStore ds = this.getDataStore();
        Enviroment env = (Enviroment) ds.get("enviroment");
        return env.checkGoal();
    }
}
