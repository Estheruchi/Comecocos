/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comecocos;

/**
 *
 * @author Estheruchi
 */
public class Modelo {

    private Controlador control;
    private Dado dado;

    public Modelo(Controlador control) {
        this.control = control;
        this.dado = new Dado(this);
        escuchar();
    }

    public void escuchar() {
        dado.addMouseListener(control);
    }

    public void lanzarDado() {
        int n = (int) Math.floor(Math.random() * (5 - 1 + 1) + 1);
        dado.setImagen(n);
        dado.dibujarDado();
    }

    public void darImagen(Dado nuevaImagen) {
        control.darImagen(nuevaImagen);
    }
}
