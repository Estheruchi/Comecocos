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

    private static final int ALTO = 600;
    private static final int ANCHO = 600;
    private static String titulo = "Comecocos";

    private Controlador control;
    private GridBagConstraints gbc;

    public Vista(Controlador control) {
        this.control = control;
        crearInterfaz();
        this.setVisible(true);
    }

    public void crearInterfaz() {
        this.setLayout(new GridBagLayout());
        this.setSize(ANCHO, ALTO);
        this.setLocation(50, 50);
        this.setTitle(titulo);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.GRAY);
    }

    public void crearTablero() {
        gbc = new GridBagConstraints();
    }
}
