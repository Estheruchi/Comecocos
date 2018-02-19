/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comecocos;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;

/**
 *
 * @author Estheruchi
 */
public class Vista extends JFrame {

    private static final int ALTO = 635;
    private static final int ANCHO = 910;
    private static String titulo = "Comecocos";

    private Controlador control;
    private FondoImagen fondo;

    public Vista(Controlador control) {
        this.control = control;
        crearInterfaz();
        crearFondo();
        this.setVisible(true);
    }

    public void crearInterfaz() {
        this.setLayout(null);
        this.setSize(ANCHO, ALTO);
        this.setLocation(50, 50);
        this.setTitle(titulo);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.GRAY);
    }

    public void crearTablero() {

    }

    public void crearFondo() {
        fondo = new FondoImagen();
        this.add(fondo);
        fondo.setLayout(null);
        fondo.setBounds(0, 0, 900, 600);

    }

    public void tomaImagen(Dado nuevaImagen) {
        fondo.add(nuevaImagen);
        this.refrescar();
    }

    public void refrescar() {
        this.repaint();
    }
}
