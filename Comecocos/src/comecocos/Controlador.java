/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comecocos;

import java.awt.event.MouseAdapter;

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
}
