package Serrano_Axel_R5_U3;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * Hilo que simula el tiempo que tarda un corredor en completar la carrera.
 */
public class ThreadRunner extends Thread {
    private Runner runner;
    private JTextArea resultsArea;

    /**
     * Constructor del hilo.
     * @param runner El corredor asociado a este hilo.
     * @param resultsArea El área de texto donde se mostrará el resultado.
     */
    public ThreadRunner(Runner runner, JTextArea resultsArea) {
        this.runner = runner;
        this.resultsArea = resultsArea;
    }

    @Override
    public void run() {
        try {
            // El hilo se pausa por el tiempo equivalente a la velocidad del corredor en segundos.
            Thread.sleep(runner.getSpeed() * 1000);
            
            // Se actualiza la interfaz de forma segura desde el hilo de eventos de Swing.
            SwingUtilities.invokeLater(() -> {
                resultsArea.append(runner.getName() + ": Tiempo " + runner.getSpeed() + " seg.\n");
            });
        } catch (InterruptedException e) {
            // En caso de interrupción, se imprime la traza de error.
            e.printStackTrace();
        }
    }
}
