package pr3;

import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import pr3.AgentSanta;
import pr3.AgentP3;


/**
 *
 * @author jmnavas
 */
public class Main {

    private static final String PACKAGE_NAME = Main.class.getPackageName();

    public static void main(String[] args) {
        try {
            Runtime entorno = Runtime.instance();
            Profile p = new ProfileImpl();
            ContainerController cc = entorno.createAgentContainer(p);

            // Crear agente buscador de la P3
            AgentController agentP3 = cc.createNewAgent("AgentP3",AgentP3.class.getCanonicalName(), null);
            agentP3.start();
            // Crear agente de Santa
            AgentController agentSanta = cc.createNewAgent("AgentSanta",AgentSanta.class.getCanonicalName(), null);
            agentSanta.start();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static AgentController crearAgente(ContainerController cc, String nombre, String clase) throws Exception {
        try {
            return cc.createNewAgent(nombre, className(clase), null);
        } catch (Exception e) {
            throw new Exception("Agent from class " + className(clase) + " not created: " + e.getMessage());
        }
    }

    public static String className(String className) {
        return PACKAGE_NAME.concat("." + className);
    }
    
}
