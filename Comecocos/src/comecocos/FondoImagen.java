package comecocos;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Esther, Javier y Victor
 */
public class FondoImagen extends JPanel {
    @Override
    public void paint(Graphics g) {
        ImageIcon imgFondo = new ImageIcon(getClass().getResource("/Imagenes/fondo.jpg"));
        g.drawImage(imgFondo.getImage(), 0, 0, 900, 600, this);
        setOpaque(false);
        super.paint(g); 
        super.paintComponent(g);
    }
}
