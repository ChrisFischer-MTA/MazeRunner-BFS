/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazerunner;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Admin
 */
public class Image {

    BufferedImage image;
    char[][] translated;

    public Image(String filename) throws IOException {
        image = ImageIO.read(new File(filename));
        parseImage();
    }

    public void parseImage() {
        // Create an array to store the parsed image.
        translated = new char[image.getWidth()][image.getHeight()];

        // Create a temporary variable to assign for the array. 
        int tmp = -1;

        // Loop through the image and assign the color codes appropriately.
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                switch (this.getColorCode(j, i)) {
                    case -6075996:
                        translated[j][i] = 'S';
                        // Start
                        break;
                    case -1237980:
                        translated[j][i] = 'F';
                        // Finish;
                        break;
                    case -1:
                        translated[j][i] = '.';
                        // Moveable Space
                        break;
                    default:
                        translated[j][i] = 'X';
                        // Blocked Space
                        break;
                }
            }
        }
    }

    
    public int getHeight() {
        return image.getHeight();
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getColorCode(int Width, int Height) {
        return image.getRGB(Width, Height);
    }

}
