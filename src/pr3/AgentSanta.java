package pr3;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;


public class AgentSanta extends Agent {
    
    @Override
    protected void setup() {
        
        Behaviour behavSantaResponse = new BehavSantaResponse();
        addBehaviour(behavSantaResponse);

    }


}
