package pr3;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;


public abstract class BehavGoal extends Behaviour{
    protected DataStore ds;
    boolean lastGoalReached = false;
   
   @Override
    public boolean done() {
        ds = this.getDataStore();
        Enviroment env = (Enviroment) ds.get("enviroment");
        boolean buscandoRenos = (boolean) ds.get("buscareno");
        boolean buscandoSanta = (boolean) ds.get("buscasanta");
        boolean goalReached = env.checkGoal();
        
        if (buscandoRenos && goalReached){
            System.out.println("Reno encontrado!");
            ds.put("buscareno", false);
        }
        else if (buscandoSanta && goalReached) {
            lastGoalReached = true;
            System.out.println("Santa encontrado!");
            ds.put("buscasanta", false);
            ds.put("fin", true);
        }
        else if ((boolean) ds.get("fin")){
            lastGoalReached = true;
        }
        
        // si llega al objetivo, reestablecemos el entorno
        if (goalReached) {
            Enviroment new_env = new Enviroment();
            new_env.setAgentPosition(
                    env.getAgentPositionX(), 
                    env.getAgentPositionY());
            ds.put("enviroment", new_env);
        }
        
        this.setDataStore(ds);
        
        return lastGoalReached;
    }
}
