package pr3;


public class BehavMoveAgent extends BehavGoal{
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
}
