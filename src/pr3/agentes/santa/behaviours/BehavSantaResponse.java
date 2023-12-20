package pr3;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.core.behaviours.DataStore;

public class BehavSantaResponse extends Behaviour {

    DataStore ds;
    ACLMessage respuesta;
    public final Coords posicion = new Coords(3, 3);

    private static enum Paso {ESPERAR_PROPUESTA, DAR_UBICACION, HOHOHO};
    private Paso step = Paso.ESPERAR_PROPUESTA;

    @Override
    public void action() {
        ds = this.getDataStore();

        switch (step) {
            case ESPERAR_PROPUESTA -> {
                ACLMessage msg = myAgent.blockingReceive();
                int aleatorio = (int) Math.floor(Math.random() * 10);
                dialogo("Voy a ver si eres digno o no (resultado entre 1 y 8), el resultado es: " + aleatorio);
                if (msg.getPerformative() == ACLMessage.PROPOSE) {
                    if (aleatorio > 0 && aleatorio < 9) {
                        dialogo("Eres digno, toma el codigo y daselo a Rudolph");
                        respuesta = msg.createReply(ACLMessage.ACCEPT_PROPOSAL);
                        respuesta.setContent("CodigoRudolph");
                        step = Paso.DAR_UBICACION;
                    } else {
                        dialogo("No eres digno");
                        respuesta = msg.createReply(ACLMessage.REJECT_PROPOSAL);
                    }
                    myAgent.send(respuesta);
                }
            }

            case DAR_UBICACION -> {
                ACLMessage ubicacion = myAgent.blockingReceive();
                if (ubicacion.getPerformative() == ACLMessage.REQUEST) {
                    respuesta = ubicacion.createReply(ACLMessage.INFORM);
                    respuesta.setContent(posicion.toString());
                    dialogo("Aqui tienes mi posicion: " + respuesta.getContent());
                    myAgent.send(respuesta);
                    step = Paso.HOHOHO;
                } else {
                    messageError("Error en paso " + step);
                }
            }

            case HOHOHO -> {
                ACLMessage hohoho = myAgent.blockingReceive();
                if (hohoho.getPerformative() == ACLMessage.INFORM) {
                    dialogo("HOHOHO!");
                    ds.put("termino", true);
                    this.setDataStore(ds);
                }
            }
        }
    }

    public void dialogo(String frase) {
        System.out.println(myAgent.getAID().getLocalName() + ": " + frase);
    }

    public void messageError(String info) {
        dialogo(info);
        myAgent.doDelete();
    }

    @Override
    public boolean done() {
        ds = this.getDataStore();
        return (boolean) ds.get("termino");
    }

}
