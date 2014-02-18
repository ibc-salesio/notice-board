import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private static final long serialVersionUID = 1L;

     public BufferedImage image;

    public void load(File file) {
        try {
        	this.image = ImageIO.read(file);
        } catch (IOException ex) {
            ex.printStackTrace();
            this.image = null;
        }
    }
    
	@Override
    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        double imageWidth = image.getWidth();
        double imageHeight = image.getHeight();
        double panelWidth = this.getWidth();
        double panelHeight = this.getHeight();


        double sx = (panelWidth / imageWidth);
        double sy = (panelHeight / imageHeight);

        AffineTransform af = AffineTransform.getScaleInstance(sx, sy);
        g2D.drawImage(image, af, this);


    }
};