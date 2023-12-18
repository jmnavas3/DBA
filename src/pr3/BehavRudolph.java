package pr3;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;
import java.util.ArrayList;


public class BehavRudolph extends Behaviour {   //Este ya no se utiliza
    
    @Override
    public void action() {
        DataStore ds = this.getDataStore();
        ArrayList<Coords> renos = (ArrayList<Coords>) ds.get("renos");
        Coords coordenadas = renos.remove(0);
        System.out.println(coordenadas);
        ds.put("renos", renos);
        this.setDataStore(ds);
    }
    
    @Override
    public boolean done() {
        DataStore ds = this.getDataStore();
        ArrayList<Coords> env = (ArrayList<Coords>) ds.get("renos");
        if (env.isEmpty()) System.out.println("Busca a Santa!");
        return env.isEmpty();
    }
}
