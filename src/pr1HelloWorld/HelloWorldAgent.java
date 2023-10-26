
package pr1HelloWorld;

/**
 * 1. Agente básico que muestra mensaje por consola
 * @author jmnavas
 */
public class HelloWorldAgent extends MyAgent {
    @Override
    protected void setup() {
        System.out.println("Hola Mundo!");
    }
}
