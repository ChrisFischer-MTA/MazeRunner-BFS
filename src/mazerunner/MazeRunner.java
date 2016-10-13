package mazerunner;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MazeRunner {

    public static void main(String[] args) throws IOException {
        Image input = new Image("input.png");
        char[][] te = BFS_new.runBFS(input.translated);
        createImage(te);
    }

    public static void createImage(char[][] image) throws IOException {
        int[][] translated = new int[image.length][image[0].length];
        for (int i = 0; i < image[0].length; i++) {
            for (int j = 0; j < image.length; j++) {
                switch (image[j][i]) {
                    case 'U':
                        translated[j][i] = -1237980;
                        break;
                    /*  case 'S':
                        translated[j][i] = -6075996;
                        // Start
                        break;
                   case 'F':
                        translated[j][i] = -1237980;
                        // Finish;
                        break; */
                    case 'L':
                        translated[j][i] = 18217234;
                        // Moveable Space
                        break;
                     default:
                        translated[j][i] = -1047910;
                        // Blocked Space
                        break;
                }
            }
        }

        int xLenght = translated.length;
        int yLength = translated[0].length;
        BufferedImage b = new BufferedImage(xLenght, yLength, 3);

        for (int x = 0; x < xLenght; x++) {
            for (int y = 0; y < yLength; y++) {
                int rgb = (int) translated[x][y] << 16 | (int) translated[x][y] << 8 | (int) translated[x][y];
                b.setRGB(x, y, rgb);
            }
        }
        System.out.println(ImageIO.write(b, "PNG", new File("output.jpg")));
    }
}
