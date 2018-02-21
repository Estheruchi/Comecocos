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

    private static final String[] POSICIONES = {"45,0", "260,0", "490,0", "700,0", "700,155",
        "700,305", "700,452", "490,452", "260,452", "45,452", "45,305", "45,155"};
    private static final int JUGADOR1 = 1, JUGADOR2 = 2;
    private static final int CASTILLO = 0;

    private int[] estado; //castillo-azul-rojo

    private Controlador control;
    private Dado dado;
    private int turno = 0;
    private Ficha nAzul; //1
    private Ficha nRojo;//2
    private Ficha castillo;//3
    private int indNuevo;
    private int avance;
    private int hasChochado; //0,1,2
    private boolean haCambiado;

    public Modelo(Controlador control) {
        this.control = control;
        this.dado = new Dado(this);
        estado = new int[3];
        turno = JUGADOR1;
        hasChochado = -1;
        haCambiado = false;
        escuchar();
        generarPersonajes();
    }

    public void escuchar() {
        dado.addMouseListener(control);
    }

    public void lanzarDado() {
        avance = (int) Math.floor(Math.random() * (5 - 1 + 1) + 1);
        dado.setImagen(avance);
        dado.dibujarDado();
        desplazar();
        cambiarTurno();
    }

    public void generarPersonajes() {
        nAzul = new Ficha(this, 1);
        nRojo = new Ficha(this, 2);
        castillo = new Ficha(this, 3);
        estlabecerPosiciones();
    }

    public String[] separarCoordenadas(int n) {
        String[] splitter;
        splitter = POSICIONES[n].split(",");
        return splitter;

    }

    public void generarPosicion() {
        indNuevo = darNumero();
        System.out.println("GENERADO: " + indNuevo);
        if (comprobarCoordenadas(indNuevo)) {
            System.out.println("Encontrado, genero otro");
            generarPosicion();
        }
    }

    public void estlabecerPosiciones() {
        posicionCastillo();
        posicionNinjaAzul();
        posicionNinjaRojo();
    }

    public void posicionCastillo() {
        generarPosicion();
        int n = indNuevo;
        String[] posicion = separarCoordenadas(n);
        estado[0] = n;
        castillo.setPosicion(Integer.parseInt(posicion[0]), Integer.parseInt(posicion[1]));
    }

    public void posicionNinjaAzul() {
        generarPosicion();
        int n = indNuevo;
        String[] posicion = separarCoordenadas(n);
        estado[1] = n;
        nAzul.setPosicion(Integer.parseInt(posicion[0]), Integer.parseInt(posicion[1]));
    }

    public void posicionNinjaRojo() {
        generarPosicion();
        int n = indNuevo;
        String[] posicion = separarCoordenadas(n);
        estado[2] = n;
        nRojo.setPosicion(Integer.parseInt(posicion[0]), Integer.parseInt(posicion[1]));
    }

    public boolean comprobarCoordenadas(int indice) {
        for (int i = 0; i < estado.length; i++) {
            if (estado[i] == indice) {
                hasChochado = i;
                haCambiado = true;
                return true;
            }
        }
        return false;
    }

    public void cambiarTurno() {
        if (turno == JUGADOR1) {
            turno = JUGADOR2;
        } else {
            turno = JUGADOR1;
        }
    }

    public void desplazar() {
        int nuevaPosicion;
        String arrayXY[];
        switch (turno) {
            case JUGADOR1: //nAzul
                nuevaPosicion = estado[1] + avance;
                if (comprobarCoordenadas(nuevaPosicion)) {
                    hayChoque();
                }

                mover(1);
               // estado[1] = nuevaPosicion;
                arrayXY = separarCoordenadas(nuevaPosicion);
                nAzul.setPosicion(Integer.parseInt(arrayXY[0]), Integer.parseInt(arrayXY[1]));
                break;

            case JUGADOR2:
                nuevaPosicion = estado[2] + avance;
                if (comprobarCoordenadas(nuevaPosicion)) {
                    hayChoque();
                }
                
                mover(2);
               // estado[2] = nuevaPosicion;
                arrayXY = separarCoordenadas(nuevaPosicion);
                nRojo.setPosicion(Integer.parseInt(arrayXY[0]), Integer.parseInt(arrayXY[1]));
                break;
        }

    }

    public void mover(int personaje) { //estado(1) ninjaAzul estado(2) ninjaRojo
        int n = estado[personaje];
        while (avance != 0) {
            n++;
            if (comprobarLimite(n)) {
                n = 0;
            }
            
            estado[personaje] = n ;
            avance--;
        }
    }

    public boolean comprobarLimite(int n) {
        if (n >= POSICIONES.length) {
            return true;
        } else {
            return false;
        }
    }

    public void hayChoque() {
        if (haCambiado) {
            switch (hasChochado) {
                case CASTILLO:
                    //turno=turno --> se repite turno
                    break;
                case JUGADOR1:
                    //come jugador1
                    break;
                case JUGADOR2:
                    //come jugador2
                    break;
            }
        }
    }

    public int darNumero() {
        int n = (int) Math.floor(Math.random() * 11);
        return n;
    }

    public void darImagen(JLabel nuevaImagen) {
        control.darImagen(nuevaImagen);
    }
}
