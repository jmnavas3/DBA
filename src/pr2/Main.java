package pr2;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

/**
 *
 * @author jmnavas
 */
public class Main {

    private static final String PACKAGE_NAME = Main.class.getPackageName();

    public static void main(String[] args) {
        try {
            jade.core.Runtime entorno = jade.core.Runtime.instance();
            Profile p = new ProfileImpl();
            ContainerController cc = entorno.createAgentContainer(p);

            AgentController a1 = crearAgente(cc, "pathFinder", "AgentP2");
            a1.start();
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
