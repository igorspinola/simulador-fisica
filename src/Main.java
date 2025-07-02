import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame("Simulador de mecânica");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        SimulatorPanel simulatorPanel = new SimulatorPanel();
        window.add(simulatorPanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        simulatorPanel.startThread();

    }
}