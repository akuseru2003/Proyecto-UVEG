package Serrano_Axel_R5_U3;

import javax.swing.*;
import java.awt.*;

/**
 * Interfaz gráfica y lógica principal para la simulación de la carrera atlética.
 */
public class AthleticRaceInterface extends JFrame {
    // Arreglo global de corredores con tamaño fijo de 5.
    private Runner[] runners = new Runner[5];
    private int runnerCount = 0;

    // Componentes de la interfaz.
    private final JTextField nameField;
    private final JTextArea registeredArea;
    private final JTextArea resultsArea;
    private final JButton registerBtn;
    private final JButton startBtn, restartBtn, finishBtn;

    public AthleticRaceInterface() {
        // Configuración básica de la ventana.
        setTitle("Simulación de Carrera Atlética");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLayout(new GridLayout(3, 1, 10, 10));

        // Sección 1: Registro.
        JPanel registerPanel = new JPanel(new GridBagLayout());
        registerPanel.setBorder(BorderFactory.createTitledBorder("Registro de Corredores"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0;
        registerPanel.add(new JLabel("Nombre del corredor:"), gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        nameField = new JTextField(15);
        registerPanel.add(nameField, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        registerBtn = new JButton("Registrar");
        registerPanel.add(registerBtn, gbc);

        // Sección 2: Corredores Registrados.
        JPanel registeredPanel = new JPanel(new BorderLayout());
        registeredPanel.setBorder(BorderFactory.createTitledBorder("Lista de Corredores Registrados"));
        registeredArea = new JTextArea();
        registeredArea.setEditable(false);
        registeredPanel.add(new JScrollPane(registeredArea), BorderLayout.CENTER);

        // Sección 3: Resultados y Controles.
        JPanel resultsPanel = new JPanel(new BorderLayout());
        resultsPanel.setBorder(BorderFactory.createTitledBorder("Resultados del Orden de Llegada"));
        resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        resultsPanel.add(new JScrollPane(resultsArea), BorderLayout.CENTER);

        JPanel controlsPanel = new JPanel(new FlowLayout());
        startBtn = new JButton("Iniciar");
        restartBtn = new JButton("Reiniciar");
        finishBtn = new JButton("Terminar");
        controlsPanel.add(startBtn);
        controlsPanel.add(restartBtn);
        controlsPanel.add(finishBtn);
        resultsPanel.add(controlsPanel, BorderLayout.SOUTH);

        // Agregar paneles a la ventana principal.
        add(registerPanel);
        add(registeredPanel);
        add(resultsPanel);

        // Eventos de los botones.
        registerBtn.addActionListener(e -> registerRunner());
        startBtn.addActionListener(e -> startRace());
        restartBtn.addActionListener(e -> restartRace());
        finishBtn.addActionListener(e -> System.exit(0));

        // Centrar ventana.
        setLocationRelativeTo(null);
    }

    /**
     * Lógica para registrar un corredor.
     */
    private void registerRunner() {
        String name = nameField.getText().trim();
        
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un nombre válido.", "Campo Vacío", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (runnerCount >= 5) {
            JOptionPane.showMessageDialog(this, "Límite alcanzado: Máximo 5 corredores permitidos.", "Registro Completo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Runner runner = new Runner(name);
        runners[runnerCount] = runner;
        runnerCount++;
        
        registeredArea.append(runnerCount + ". " + runner.getName() + "\n");
        nameField.setText("");
    }

    /**
     * Lógica para iniciar la carrera.
     */
    private void startRace() {
        if (runnerCount < 5) {
            JOptionPane.showMessageDialog(this, "Se requieren exactamente 5 corredores registrados para iniciar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        resultsArea.setText("¡La carrera ha comenzado!\nEsperando resultados...\n\n");
        
        for (Runner runner : runners) {
            if (runner != null) {
                new ThreadRunner(runner, resultsArea).start();
            }
        }
    }

    /**
     * Lógica para reiniciar la carrera y limpiar datos.
     */
    private void restartRace() {
        runners = new Runner[5];
        runnerCount = 0;
        registeredArea.setText("");
        resultsArea.setText("");
        nameField.setText("");
        JOptionPane.showMessageDialog(this, "Datos reiniciados correctamente.", "Reinicio", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Punto de entrada principal.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AthleticRaceInterface().setVisible(true);
        });
    }
}
