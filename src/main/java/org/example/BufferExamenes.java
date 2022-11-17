package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class BufferExamenes {
    private Queue<String> colaExamenes;
    public BufferExamenes() {
        colaExamenes = new LinkedList<String>();
    }


    public synchronized void fabricarNuevoExamen(String codigo) {
        // Aquí se fabrica un nuevo examen.
        // Un hilo de la clase ProductorExamenes se encargará de fabricarlo y
        // pasarlo como argumento a este método.
        colaExamenes.add(codigo);
        notify();
        // Añade el código pasado como argumento a la cola y libera el hilo que está
        // intentando consumir un nuevo examen.
    }

    public synchronized String consumirExamen() {
        // Este método se encargará de suministrar un examen a cada hilo de
        // tipo Examinador que lo solicite.
        // Para suministrar el examen habrá antes que esperar hasta que haya
        // algún examen para consumir en la cola.
        // Haz aquí una pausa hasta que se haya fabricado algún examen.
        // Si la cola sigue sin estar vacía, saca un examen y entrégalo como
        // retorno de esta función.

        while(colaExamenes.isEmpty()) {
            try {
                wait(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        if (!colaExamenes.isEmpty()) {
            String examen = colaExamenes.remove();
            return examen;
        } else {
            return null;
        }
    }
}
