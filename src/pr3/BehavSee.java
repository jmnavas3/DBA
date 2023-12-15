package pr3;

public class BehavSee extends BehavGoal {
    @Override
    public void action() {
        ds = this.getDataStore();
        if ((boolean) ds.get("buscareno")) {
            Enviroment env = (Enviroment) ds.get("enviroment");
            env.see();
            ds.put("enviroment", env);
            this.setDataStore(ds);
        }
    }
}
