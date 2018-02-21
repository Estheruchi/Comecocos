/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comecocos;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Estheruchi
 */
public class Ficha extends JLabel {

    private static String RUTA = "/imagenes/";
    private static final int ALTO = 142;
    private static final int ANCHO = 140;

    private String fichero;
    private Modelo modelo;
    private int personaje;
    private int posX, posY;

    public Ficha(Modelo modelo, int personaje) {
        this.modelo = modelo;
        this.personaje = personaje;
        crear();
    }

    public void crear() {
        setImagen(personaje);
        dibujar();
        this.setVisible(true);
    }

    public void dibujar() {
        URL url = this.getClass().getResource(RUTA + fichero);
        ImageIcon icon = new ImageIcon(url);
        this.setIcon(icon);
        modelo.darImagen(this);
    }

    public void setPosicion(int x, int y) {
        posX = x;
        posY = y;
        this.setBounds(posX, posY, ANCHO, ALTO);
    }

    public void setImagen(int n) {
        switch (n) {
            case 1:
                fichero = "ninjaAzul.png";
                break;
            case 2:
                fichero = "ninjaRojo.png";
                break;
            case 3:
                fichero = "castillo.png";
                break;
        }
    }
}
