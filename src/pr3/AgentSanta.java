package pr3;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;


public class AgentSanta extends Agent {
    
    public DataStore ds;
    //public final Coords posicion = new Coords(3,3);
    
    @Override
    protected void setup() {
        ds = new DataStore();
        
        ds.put("termino", false);
        
        Behaviour behavSantaResponse = new BehavSantaResponse();
        behavSantaResponse.setDataStore(ds);
        addBehaviour(behavSantaResponse);

    }
    /*
    public Coords getCoords(){
        return posicion;
    }
    */


}
