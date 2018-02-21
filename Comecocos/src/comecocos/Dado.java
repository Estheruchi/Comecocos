/*

 */
package comecocos;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Estheruchi
 */
public class Dado extends JLabel {

    private static String RUTA = "/imagenes/";
    private static final int ALTO = 50;
    private static final int ANCHO = 50;

    private String fichero;
    private Modelo modelo;

    public Dado(Modelo modelo) {
        this.modelo = modelo;
        crearDado();
    }

    public void crearDado() {
        this.setBounds(425, 225, ANCHO, ALTO);
        setImagen(0);
        dibujarDado();
        this.setVisible(true);
    }

    public void dibujarDado() {
        URL url = this.getClass().getResource(RUTA + fichero);
        ImageIcon icon = new ImageIcon(url);
        this.setIcon(icon);
        modelo.darImagen(this);
    }

    public void setImagen(int n) {
        switch (n) {
            case 1:
                fichero = "1.png";
                break;
            case 2:
                fichero = "2.png";
                break;
            case 3:
                fichero = "3.png";
                break;
            case 4:
                fichero = "4.png";
                break;
            case 5:
                fichero = "5.png";
                break;
            default:
                fichero = "6.png";
        }
    }
}
