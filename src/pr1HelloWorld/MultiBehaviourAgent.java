package pr1HelloWorld;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 4. Agente con varios comportamientos que calcula la media de la suma de los
 * elementos numéricos introducidos.
 *
 * @author jmnavas
 */
public class MultiBehaviourAgent extends MyAgent {

    private List<Integer> numberList;
    private int listSize;
    java.io.BufferedReader scanner;

    public MultiBehaviourAgent() {
        this.numberList = new ArrayList<>();
        this.scanner = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
    }

    @Override
    protected void setup() {
        this.addBehaviour(firstBehaviour());
        this.addBehaviour(secondBehaviour());
        this.addBehaviour(thirdBehaviour());
    }

    /**
     * Establece un límite para la lista de números
     *
     * @return OneShotBehaviour
     */
    public Behaviour firstBehaviour() {
        return new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.print("Cuantos números quieres sumar? ");
                try {
                    listSize = Integer.parseInt(scanner.readLine());
                } catch (Exception e) {
                    Logger.getLogger(MultiBehaviourAgent.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        };
    }

    /**
     * Solicita números hasta que la lista esté llena
     *
     * @return OneShotBehaviour
     */
    public Behaviour secondBehaviour() {
        return new OneShotBehaviour() {
            @Override
            public void action() {
                int number;
                try {
                    for (int i = 0; i < listSize; i++) {
                        System.out.print("Número " + (i + 1) + ": ");
                        number = Integer.parseInt(scanner.readLine());
                        numberList.add(number);
                    }
                } catch (Exception e) {
                    Logger.getLogger(MultiBehaviourAgent.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        };
    }

    /**
     * Suma, muestra la media de la suma y termina el programa
     *
     * @return OneShotBehaviour
     */
    public Behaviour thirdBehaviour() {
        return new OneShotBehaviour() {
            @Override
            public void action() {
                int suma = 0;
                double media;
                for (int n : numberList) {
                    suma += n;
                }
                media = (double) suma / listSize;

                System.out.println("Suma: " + suma);
                System.out.println("Media: " + media);
            }
        };
    }

    // TODO: ver behaviours.CompositeBehaviour
}
