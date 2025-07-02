import javax.swing.*;
import java.awt.*;

public class SimulatorPanel extends JPanel implements Runnable {

    // configuracoes da tela
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    Thread simulatorThread;

    public SimulatorPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void startThread() {
       simulatorThread = new Thread(this);
       simulatorThread.start();
    }

    @Override
    public void run() {
        while(simulatorThread != null) {

            update();
            repaint();

        }

    }
    public void update() {

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);

        g2.fillRect(100, 100, tileSize, tileSize);

        g2.dispose();

    }
}
