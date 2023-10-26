package pr1HelloWorld;

import java.util.Iterator;
import jade.core.Agent;

/**
 * 1. Agente abstracto que implementa métodos comunes que uso en los agentes de
 * la práctica 1
 *
 * @author jmnavas
 */
public abstract class MyAgent extends Agent {

    /**
     * Muestra información del agente: nombre local, GUID, direcciones.
     */
    public void agentInfo() {
        System.out.println("Local-name: " + getAID().getLocalName());
        System.out.println("GUID: " + getAID().getName());
        System.out.println("Addresses");

        Iterator it = getAID().getAllAddresses();
        while (it.hasNext()) {
            System.out.println("- " + it.next());
        }
    }

    @Override
    public void takeDown() {
        System.out.println("fin " + getAID().getName().toUpperCase());
        doSeparator();
    }

    public static final void doSeparator() {
        System.out.println("--------------------------\n");
    }
}
