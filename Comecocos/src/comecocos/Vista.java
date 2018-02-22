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
import javax.swing.JLabel;

/**
 *
 * @author Estheruchi
 */
public class Vista extends JFrame {

    private static final int ALTO = 635;
    private static final int ANCHO = 910;
    private static String titulo = "NINJA PURSUIT";

    private Controlador control;
    private FondoImagen fondo;
    private JLabel etiquetaJug1, etiquetaJug2, etiquetaTitulo, puntosJug1, puntosJug2;

    public Vista(Controlador control) {
        this.control = control;
        crearInterfaz();
        crearFondo();
        crearEtiquetas();
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

    public void tomaImagen(JLabel nuevaImagen) {
        fondo.add(nuevaImagen);
        this.refrescar();
    }

    public void refrescar() {
        this.repaint();
    }

    public void crearEtiquetas() {
        CustomLetra cl = new CustomLetra();

        etiquetaJug1 = new JLabel("JUGADOR 1");
        fondo.add(etiquetaJug1);
        etiquetaJug1.setBounds(250, 340, 300, 40);
        etiquetaJug1.setFont(cl.MyFont(1, 31f));
        etiquetaJug1.setForeground(Color.WHITE);

        etiquetaJug2 = new JLabel("JUGADOR 2");
        fondo.add(etiquetaJug2);
        etiquetaJug2.setBounds(480, 340, 300, 40);
        etiquetaJug2.setFont(cl.MyFont(1, 31f));
        etiquetaJug2.setForeground(Color.white);

        etiquetaTitulo = new JLabel("NINJA PURSUIT");
        fondo.add(etiquetaTitulo);
        etiquetaTitulo.setBounds(310, 170, 300, 40);
        etiquetaTitulo.setFont(cl.MyFont(1, 40f));
        etiquetaTitulo.setForeground(Color.BLACK);

        puntosJug1 = new JLabel("0");
        fondo.add(puntosJug1);
        puntosJug1.setBounds(310, 380, 300, 40);
        puntosJug1.setFont(cl.MyFont(1, 45f));
        puntosJug1.setForeground(Color.blue);

        puntosJug2 = new JLabel("0");
        fondo.add(puntosJug2);
        puntosJug2.setBounds(550, 380, 300, 40);
        puntosJug2.setFont(cl.MyFont(1, 40f));
        puntosJug2.setForeground(Color.blue);

    }

    public void iluminarJugador(int turno) {
        switch (turno) {
            case 1:
                etiquetaJug1.setForeground(Color.red);
                break;
            case 2:
                etiquetaJug2.setForeground(Color.red);
                break;
            
        }
    }
    
    public void resetearJugador(int turno){
          switch (turno) {
            case 1:
                etiquetaJug1.setForeground(Color.white);
                break;
            case 2:
                etiquetaJug2.setForeground(Color.white);
                break;
            
        }
    }

    public void setPuntosJug1(JLabel puntosJug1) {
        this.puntosJug1 = puntosJug1;
    }

    public void setPuntosJug2(JLabel puntosJug2) {
        this.puntosJug2 = puntosJug2;
    }

    public void pintarPuntos(int puntos,int jugador) {
        switch (jugador) {
            case 1:
                puntosJug1.setText(""+puntos);
                break;
            case 2:
                puntosJug2.setText(""+puntos);
                break;
            
        }
    }
    
    
}
