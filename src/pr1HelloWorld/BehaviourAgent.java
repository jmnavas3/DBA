package pr1HelloWorld;

import jade.core.behaviours.OneShotBehaviour;

/**
 * 2. Agente con comportamientos (OneShotBehaviour) que muestra un mensaje una
 * sóla vez
 *
 * @author jmnavas
 */
public class BehaviourAgent extends MyAgent {

    @Override
    protected void setup() {
        this.addBehaviour(agentBehaviour());
    }

    public OneShotBehaviour agentBehaviour() {
        return new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.println("Hola Mundo!");
            }
        };
    }
}
