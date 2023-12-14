package pr3;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;

/**
 *
 * @author galvez
 */
public class BehavMoveAgent extends Behaviour{
    DataStore ds;
    
    @Override
    public void action() {
        ds = this.getDataStore();
        Enviroment env = (Enviroment) ds.get("enviroment");
        
        String nextAction = env.getAction();
        env.doMoveAction(nextAction);
        env.showMapStatus();
        
        ds.put("enviroment", env);
        this.setDataStore(ds);
    }
    
   @Override
    public boolean done() {
        ds = this.getDataStore();
        Enviroment env = (Enviroment) ds.get("enviroment");
        boolean buscandoRenos = (boolean) ds.get("buscareno");
        boolean buscandoSanta = (boolean) ds.get("buscasanta");
        boolean goalReached = env.checkGoal();
        boolean lastGoalReached = false;
        
        if (buscandoRenos && goalReached){
            System.out.println("Reno encontrado!");
            ds.put("buscareno", false);
        }
        
        if (buscandoSanta && goalReached) {
            lastGoalReached = true;
            System.out.println("Santa encontrado!");
            ds.put("buscasanta", false);
        }
        
        return lastGoalReached;
    }
}
