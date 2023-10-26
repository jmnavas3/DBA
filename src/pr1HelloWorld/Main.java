package pr1HelloWorld;

import jade.core.Profile;
import jade.core.Runtime;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

/**
 * Entorno de ejecución de Jade para la práctica 1. Asignatura: DBA.
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

            AgentController a1 = crearAgente(cc, "ejer1", "HelloWorldAgent");
            a1.start();
            a1.kill();

            AgentController a2 = crearAgente(cc, "ejer2", "BehaviourAgent");
            a2.start();

            AgentController a3 = crearAgente(cc, "ejer3", "CyclicAgent");
            a3.start();

//            AgentController a4 = crearAgente(cc, "ejer4", "MultiBehaviourAgent");
//            a4.start();

        } catch (Exception e) {
            System.out.println(e.getMessage());
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
