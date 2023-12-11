/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pr3;

import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author joy111
 */
public class BehavMessage extends CyclicBehaviour {

    private int step = 0;

    @Override
    public void action() {

        switch (step) {

            case 0 -> {
                ACLMessage msg = new ACLMessage();

                msg.addReceiver(new AID("Rudolph", AID.ISLOCALNAME));

                msg.setContent("Hola Rudolph");
                msg.setConversationId("apto");

                myAgent.send(msg);

                step = 1;

                System.out.println("Valor de step: " + step);
            }

            case 1 -> {
                ACLMessage msg = myAgent.blockingReceive();
                
                if ( msg.getConversationId().equals("apto") ) {
                    System.out.println("Mensaje de Rudolph: " + msg.getContent());
                } else
                    System.out.println("Error en la conversacion");

            }
        }
    }

}
