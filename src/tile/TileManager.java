package tile;

import main.SimulatorPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    SimulatorPanel sp;
    Tile[] tile;
    String path;
    int mapTileNumber[][];

    public TileManager(SimulatorPanel sp) {
        this.path = "/resources/tiles/";
        this.sp = sp;
        this.tile = new Tile[10];
        mapTileNumber = new int[sp.maxScreenCol][sp.maxScreenRow];

        getTileImage();
        loadMap();
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/resources/maps/map.txt");
            BufferedReader bf = new BufferedReader(new InputStreamReader(is));

            int row = 0;
            String line;

            while ((line = bf.readLine()) != null && row < sp.maxScreenRow) {
                String[] numbers = line.split(" ");

                for (int col = 0; col < numbers.length && col < sp.maxScreenCol; col++) {
                    int num = Integer.parseInt(numbers[col].trim());
                    mapTileNumber[col][row] = num;
                    System.out.println("Row: " + row + ", Col: " + col + ", Tile: " + num);
                }
                row++;
            }

            bf.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream(this.path + "sky.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream(this.path + "grass.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void draw(Graphics2D g2) {

       int col = 0;
       int row = 0;
       int x = 0;
       int y = 0;

       while(col < sp.maxScreenCol && row < sp.maxScreenRow) {

          int tileNum = mapTileNumber[col][row];
//           System.out.println(tileNum);

           g2.drawImage(tile[tileNum].image, x, y, sp.tileSize, sp.tileSize, null);
           col++;
           x += sp.tileSize;

           if (col == sp.maxScreenCol) {
               col = 0;
               x = 0;
               row++;
               y += sp.tileSize;
           }

       }
    }
}

