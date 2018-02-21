/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comecocos;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

/**
 *
 * @author Estheruchi
 */
public class Controlador extends MouseAdapter {

    private Modelo modelo;
    private Vista vista;

    public Controlador() {
        vista = new Vista(this);
        modelo = new Modelo(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Tirando...");
        modelo.lanzarDado();
        vista.refrescar();
    }

    public void darImagen(JLabel nuevaImagen) {
        vista.tomaImagen(nuevaImagen);
    }

}
