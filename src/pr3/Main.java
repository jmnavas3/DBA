package pr3;

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
            
//            //Tenemos que darle los parametros al perfil para iniciar los agentes.
//            p.setParameter(Profile.MAIN_HOST, "localhost");
//            p.setParameter(Profile.CONTAINER_NAME, "agentesP1");
            
            
            ContainerController cc = entorno.createAgentContainer(p);

            AgentController a1 = crearAgente(cc, "pathFinder", "AgentP3");
            a1.start();
            
            AgentController rudolph = crearAgente(cc, "Rudolph", "AgentRudolph");
            rudolph.start();
            
            /*AgentController  buscador = crearAgente(cc, "pathFinder", "AgentP3");
            buscador.start();
            */
            
            /*
            AgentController santa = crearAgente(cc,"santa", null);
            santa.start();
            */

            /*
            AgentController rudolf = crearAgente(cc,"AgentSanta", "AgentSanta");
            rudolf.start();
            */
            
            
            
           // AgentController santa = cc.createNewAgent("Santa", AgentSanta.class.getCanonicalName(), null);
           // santa.start();
            
//            AgentController rudolph = cc.createNewAgent("Rudolph", AgentRudolph.class.getCanonicalName(), null);
//            rudolph.start();
            
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
