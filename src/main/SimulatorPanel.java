package main;

import entity.Square;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class SimulatorPanel extends JPanel implements Runnable {

    // configuracoes da tela
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
//    public final int maxScreenCol = 16;
//    public final int maxScreenRow = 12;
    public final int maxScreenCol = 32;
    public final int maxScreenRow = 20;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

//    int FPS = 190;
    int FPS = 200;
    //int FPS = 60;


    public int getFPS() {
        return FPS;
    }

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
        inputVariable();

    }

    public void startThread() {
        this.requestFocus();
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

    public int groundLevelY() {
        return screenHeight - tileSize;
    }


    public void inputVariable(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a valocidade: ");
        float velocity = scanner.nextFloat();

        System.out.print("Digite o angulo do lançamento");
        float angleG = scanner.nextFloat();

        System.out.print("Digite a altura do lançamento");
        float heigth = scanner.nextFloat();

        // Calculando angulo em R
        double angleR = (angleG * Math.PI) / 180;

        // Calculando velocidades horizontais e verticais
        double vx = velocity * Math.cos(angleR);
        double vy = velocity * Math.sin(angleR);

        // Calculando valor do tempo
        double g = 9.8;
        double a = -g /2;
        double b = vy;
        double c = heigth;

        double delta = b*b - 4*a*c;

        if (delta < 0) {
            System.out.print("Não há solução, tempo menor que 0");
            return;
        }

        double t1 = (-b + Math.sqrt(delta)) / (2 * a);
        double t2 = (-b - Math.sqrt(delta)) / (2 * a);

        double t;
        if (t1 > 0 && t2 > 0) {
            t = Math.max(t1, t2);
        } else if (t1 > 0) {
            t = t1;
        } else if (t2 > 0) {
            t = t2;
        } else {
            System.out.println("Nenhum tempo positivo válido encontrado.");
            return;
        }

        square.launch(velocity, angleG, heigth);

        System.out.println("Tempo total de voo: " + t + " segundos");

        // Distancia horizontal
        double distancia = vx * t;
        System.out.println("Distância horizontal percorrida: " + distancia + " metros");

        scanner.close();
    }

}
