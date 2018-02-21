/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comecocos;

import javax.swing.JLabel;

/**
 *
 * @author Estheruchi
 */
public class Modelo {

    private static String[] posiciones = {"45,0", "260,0", "490,0", "700,0", "700, 155",
        "700, 305", "700, 452", "45,155", "45,305", "45,452", "260,452", "490,452"};
    private String[] estado = {"0,0", "0,0", "0,0"}; //castillo-azul-rojo
    private Controlador control;
    private Dado dado;

    private Ficha nAzul; //1
    private Ficha nRojo;//2
    private Ficha castillo;//3

    public Modelo(Controlador control) {
        this.control = control;
        this.dado = new Dado(this);
        escuchar();
        generarPersonajes();
    }

    public void escuchar() {
        dado.addMouseListener(control);
    }

    public void lanzarDado() {
        int n = (int) Math.floor(Math.random() * (5 - 1 + 1) + 1);
        dado.setImagen(n);
        dado.dibujarDado();
    }

    public void generarPersonajes() {
        nAzul = new Ficha(this, 1);
        nRojo = new Ficha(this, 2);
        castillo = new Ficha(this, 3);
        generarPosiciones();
    }

    public void generarPosiciones() {
        //castillo.
        /* posicion castillo = String[darNumero()];
            posicion azul = String[darNumero()] -> COMPROBAR
            posicion rojo = String[darNumero()] -> COMPROBAR
         */
        //castillo.setPosicion(300, 100);
        //nAzul.setPosicion(45, 155);
        //nRojo.setPosicion(200, 100);
    }

    public int darNumero() {
        int n = (int) Math.floor(Math.random() * 13);
        return n;
    }

    public void darImagen(JLabel nuevaImagen) {
        control.darImagen(nuevaImagen);
    }
}
