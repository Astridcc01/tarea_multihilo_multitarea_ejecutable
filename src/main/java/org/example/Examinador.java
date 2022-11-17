package org.example;

import java.util.Random;

public class Examinador implements Runnable {
    private Thread hilo;
    BufferExamenes buffer;

    public Thread getHilo() {
        return hilo;
    }

    public Examinador(String alumno, BufferExamenes generador) {
        // Construye el hilo. El nombre será el nombre del alumno.
        hilo = new Thread(this, "Alumno " + alumno);
        // Establece el valor de la propiedad buffer
        this.buffer=generador;
        // Inicia el hilo.
        hilo.start();
    }

    @Override
    public void run() {
        Random rn = new Random();
        String codigoExamen = this.buffer.consumirExamen();
        if (codigoExamen != null) {
            String array[] = {"A", "B", "C", "D","-"};
            int cont = 1;
            // Simula aquí un examen de 10 preguntas
            // cuyas respuestas se seleccionarán al azar
            // entre A, B, C, D o – (sin contestar).
            while (cont<=10){
                int index = rn.nextInt(array.length);
                System.out.println(codigoExamen + " ; "+getHilo().getName()+";"
                        +" Pregunta: "+cont+";"+" Respuesta: "+array[index]);
                cont++;
            }

        }
        else {
            System.out.println("Agotado tiempo de espera y " + "no hay más exámenes");
        }
    }
}
