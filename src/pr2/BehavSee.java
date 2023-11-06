/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pr2;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;
import jade.core.behaviours.OneShotBehaviour;


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
