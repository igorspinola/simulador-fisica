package main;

import entity.Square;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class SimulatorPanel extends JPanel implements Runnable {

    // configuracoes da tela
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

//    int FPS = 190;
    int FPS = 200;

    TileManager tileM = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Thread simulatorThread;
    Square square = new Square(100, 100, 20, tileSize, this, keyHandler);

    public SimulatorPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startThread() {
       simulatorThread = new Thread(this);
       simulatorThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; // 0.0166 segundos
        double nextDrawTime = System.nanoTime() + drawInterval;

        long currentTime;
        long lastTime = System.nanoTime();
        long timer = 0;
        int drawCount = 0;

        while(simulatorThread != null) {
            currentTime = System.nanoTime();

            Toolkit.getDefaultToolkit().sync();
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            update();
            repaint();
            drawCount++;

//            if(timer >= 1000000000) {
//                System.out.println("FPS: " + drawCount);
//                timer = 0;
//                drawCount = 0;
//            }

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 100000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public void update() {
        square.update();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        setBackground(new Color(106,137,254,255));

        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);

        square.draw(g2);

        g2.dispose();

    }
}
