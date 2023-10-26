package pr1HelloWorld;

import jade.core.behaviours.TickerBehaviour;

/**
 * 3. Agente con comportamientos (TickerBehaviour) que muestra un mensaje cada 2
 * segundos. Hay que quitarlo desde la GUI de Jade.
 *
 * @author jmnavas
 */
public class CyclicAgent extends MyAgent {

    @Override
    protected void setup() {
        this.addBehaviour(agentBehaviour());
    }

    public TickerBehaviour agentBehaviour() {
        return new TickerBehaviour(this, 2000) {
            @Override
            protected void onTick() {
                System.out.println("Hola Mundo! " + this.getTickCount());
            }
        };
    }

    // TODO: comportamiento que haga un stop() para detener el agente
}
