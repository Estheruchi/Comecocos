/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comecocos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Esther, Javier y Victor
 */
public class Modelo {

    private static final String[] POSICIONES = {"45,0", "260,0", "490,0", "700,0", "700,155",
        "700,305", "700,452", "490,452", "260,452", "45,452", "45,305", "45,155"};
    private static final int JUGADOR1 = 1, JUGADOR2 = 2;
    private static final int CASTILLO = 0;
    private final static int MAX = 4, MIN = 1;
    private final static int TIEMPO = 1;
    //private Timer timerFichaRoja;
    private Timer timerFichaAzul;
    private Timer timerFichaRojo;

    private int[] estado; //castillo-azul-rojo

    private Controlador control;
    private Dado dado;
    private int turno = 0;
    private Ficha nAzul; //1
    private Ficha nRojo;//2
    private Ficha castillo;//3
    private int indNuevo;
    private int avance;
    private int puntosJug1 = 0;
    private int puntosJug2 = 0;
    
    private int posicionActual, posicionFutura;
    private int posXActual, posYActual;
    private int posXFutura, posYFutura;
    
    String[] posicionAct;
    String[] posicionFut;

    public Modelo(Controlador control) {
        this.control = control;
        this.dado = new Dado(this);
        estado = new int[3];
        turno = JUGADOR1;
        escuchar();
        generarPersonajes();
        //crearTimerFichaRoja();
        //timerFichaRoja.stop();

    }

    public void escuchar() {
        dado.addMouseListener(control);
    }

    /**
     * Genera un aleatorio entre el maximo y el minimo. Dibuja el dado. Desplaza
     * el personaje en funcion del turno y cambia el turno.
     */
    public void lanzarDado() {
        avance = (int) Math.floor(Math.random() * (MAX - MIN + 1) + MIN);
        dado.setImagen(avance);
        dado.dibujarDado();
        desplazar();
        cambiarTurno();
    }

    /**
     * Inicia todos los personajes mandandoles el modelo y que personaje es.
     *
     */
    public void generarPersonajes() {
        nAzul = new Ficha(this, 1);
        nRojo = new Ficha(this, 2);
        castillo = new Ficha(this, 3);
        iniciar();
    }

    /**
     * Coloca cada personaje en el tablero.
     */
    public void iniciar() {
        posicionCastillo();
        posicionNinjaAzul();
        posicionNinjaRojo();
    }

    //COLOCAMOS LA 1º VEZ
    public void posicionCastillo() {
        generarPosicion();
        colocarFicha(0);
    }

    public void posicionNinjaAzul() {
        generarPosicion();
        colocarFicha(1);
    }

    public void posicionNinjaRojo() {
        generarPosicion();
        colocarFicha(2);
    }

    /**
     *
     * @param n es el indice del array de coordenadas
     * @return devuelve en un array de enteros X e Y
     */
    public String[] separarCoordenadas(int n) {
        String[] splitter;
        splitter = POSICIONES[n].split(",");
        return splitter;
    }

    /**
     * Genera un indice del 0 al 11
     *
     * @return ese indice
     */
    public int darNumero() {
        int n = (int) Math.floor(Math.random() * 11);
        return n;
    }

    /**
     * genera un indice y comprueba que no haya otra ficha en esa posicion, si
     * la hay genera otro indice distinto.
     */
    public void generarPosicion() {
        indNuevo = darNumero();
        System.out.println("GENERADO: " + indNuevo);
        if (comprobarPrimerasCoordenadas(indNuevo)) {
            System.out.println("Encontrado, genero otro");
            generarPosicion();
        }
    }

    /**
     *
     * @param indice recibe el indice del personaje y comprueba que no este
     * repetido.
     * @return si se ha repetido o no.
     */
    public boolean comprobarPrimerasCoordenadas(int indice) {
        for (int i = 0; i < estado.length; i++) {
            if (estado[i] == indice) {
                return true;
            }
        }
        return false;
    }

    /**
     * Depende de generarPoscion();
     *
     * @param personaje recibe la ficha(que es el personaje a colocar).
     */
    public void colocarFicha(int personaje) {
        int indice = -1;
        Ficha nombre = null;
        switch (personaje) {
            case CASTILLO:
                indice = 0;
                nombre = castillo;
                break;
            case JUGADOR1:
                indice = 1;
                nombre = nAzul;
                break;

            case JUGADOR2:
                indice = 2;
                nombre = nRojo;
                break;
        }

        String[] posicion = separarCoordenadas(indNuevo);
        estado[indice] = indNuevo;
        nombre.setPosicion(Integer.parseInt(posicion[0]), Integer.parseInt(posicion[1]));
    }
    // HASTA AQUI COLOCAR LAS FICHAS EN EL TABLERO
    //****************************************************************************************

    //A PARTIR DE AQUI PARA JUGAR Y DESPLAZAR LAS FICHAS
    /**
     * Mueve las fichas en funcion del turn y segun el avance(dado). Comprueba
     * si ha habido choque antes de mover.
     */
    public void desplazar() {
        int nuevaPosicion;
        String arrayXY[];
        int posAMover;
        switch (turno) {
            case JUGADOR1: //nAzul
                nuevaPosicion = estado[1] + avance;
                posicionActual = estado[1];
                System.out.println("1º POSICION AZUL: " + posicionActual);
                comprobarCoordenadas(nuevaPosicion);

                posicionAct = separarCoordenadas(posicionActual);
                posXActual = Integer.parseInt(posicionAct[0]);
                posYActual = Integer.parseInt(posicionAct[1]);

                mover(1);
                posAMover = estado[1];
                posicionFutura = estado[1];

                System.out.println("2º posicion futura AZUL: " + posicionFutura);
                arrayXY = separarCoordenadas(posAMover);

                posicionFut = separarCoordenadas(posicionFutura);
                posXFutura = Integer.parseInt(posicionFut[0]);
                posYFutura = Integer.parseInt(posicionFut[1]);

                //nAzul.setPosicion(Integer.parseInt(arrayXY[0]), Integer.parseInt(arrayXY[1]));
                crearTimerFichaAzul();
                timerFichaAzul.start();

                break;

            case JUGADOR2:
                nuevaPosicion = estado[2] + avance;
                posicionActual = estado[2];
                System.out.println("1º POSICION ROJO: " + posicionActual);
                comprobarCoordenadas(nuevaPosicion);
                
                posicionAct = separarCoordenadas(posicionActual);
                posXActual = Integer.parseInt(posicionAct[0]);
                posYActual = Integer.parseInt(posicionAct[1]);
                
                mover(2);
                posAMover = estado[2];
                posicionFutura = estado[2];
                System.out.println("2º posicion futura ROJO: " + posicionFutura);
                arrayXY = separarCoordenadas(posAMover);
                
                posicionFut = separarCoordenadas(posicionFutura);
                posXFutura = Integer.parseInt(posicionFut[0]);
                posYFutura = Integer.parseInt(posicionFut[1]);
                
                crearTimerFichaRoja();
                timerFichaRojo.start();
                break;
        }
    }

    /**
     *
     * @param indice recibe el personaje que ha realizado el choque y comprueba
     * que otra ficha participa en el choque.
     * @return si ha chocado o no.
     */
    public boolean comprobarCoordenadas(int indice) {
        for (int i = 0; i < estado.length; i++) {
            if (estado[i] == indice) {
                hayChoque(i);
                return true;
            }
        }
        return false;
    }

    /**
     * cambia el turno.
     */
    public void cambiarTurno() {
        if (turno == JUGADOR1) {
            turno = JUGADOR2;
            control.iluminarJugador(JUGADOR2);
            control.resetearJugador(JUGADOR1);
        } else {
            control.iluminarJugador(JUGADOR1);
            turno = JUGADOR1;
            control.resetearJugador(JUGADOR2);
        }

    }

    /**
     * Va comprobando el indice de uno en uno para evitar que se salga del
     * tablero(array).
     *
     * @param personaje recibe el personaje a mover (ninja azul o rojo)
     */
    public void mover(int personaje) { //estado(1)-> ninjaAzul , estado(2) ->ninjaRojo
        int n = estado[personaje];
        while (avance != 0) {
            n++;
            if (comprobarLimite(n)) {
                n = 0;
            }

            estado[personaje] = n;
            avance--;
        }
    }

    /**
     * Comprueba que el indice no se salga del array.
     *
     * @param n recibe el indice a comprobar.
     * @return si se ha salido o no
     */
    public boolean comprobarLimite(int n) {
        if (n >= POSICIONES.length) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Comprueba que personajes chocan
     *
     * @param personaje recibe el personaje
     */
    public void hayChoque(int personaje) {
        switch (personaje) {
            case CASTILLO:
                cambiarTurno();
                System.out.println("HAS CHOCADO CON CASTILLO !!!!!!!!!!!!!!!!");
                generarPosicion();
                colocarFicha(0);

                break;
            case JUGADOR1:
                System.out.println("JUGADOR 1 COMIDO!!!!!!!!!!!!!");
                puntosJug2++;
                control.pintarPuntos(puntosJug2, JUGADOR2);
                generarPosicion();
                colocarFicha(1);
                break;
            case JUGADOR2:
                System.out.println("JUGADOR 2 COMIDO!!!!!!!!!!!!!!!");
                generarPosicion();
                puntosJug1++;
                control.pintarPuntos(puntosJug1, JUGADOR1);
                colocarFicha(2);
                break;
        }
    }

    public void darImagen(JLabel nuevaImagen) {
        control.darImagen(nuevaImagen);
    }

    //******************************************************************************************
    //ANIMACION DE MOVIMIENTO DE LAS FICHAS
    public void crearTimerFichaAzul() {
        timerFichaAzul = new Timer(TIEMPO, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animarFichaAzul();
            }
        });
    }
    
    public void crearTimerFichaRoja() {
        timerFichaRojo = new Timer(TIEMPO, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animarFichaRoja();
            }
        });
    }

    
    /**
     * Realiza el movimiento de cada ficha según su indice y posicion de X e Y.
     */
    public void animarFichaAzul() {
        
        if (posicionActual >= 0 && posicionActual < 3) {
            movimientoFichaAzul(1);

            if(nAzul.getX() >= 700)
            {
                movimientoFichaAzul(2);
                timerFichaAzul.start();
            }
 
        } else if (posicionActual >= 3 && posicionActual < 6) {
            movimientoFichaAzul(2);
            
            if(nAzul.getY()>=452)
            {
                movimientoFichaAzul(3);
                timerFichaAzul.start();
            }
        } else if (posicionActual >= 6 && posicionActual < 9) {
            movimientoFichaAzul(3);
            
            if(nAzul.getX() == 45)
            {
                movimientoFichaAzul(4);
                timerFichaAzul.start();
            }
        } else if (posicionActual >= 9 && posicionActual != 0) {
            
            movimientoFichaAzul(4);
            
            if(nAzul.getY()==0)
            {
                movimientoFichaAzul(1);
                timerFichaAzul.start();
            }   
        }
        control.refrescar();
    }
    
    /**
     * Método que mueve la ficha hacia de derecha, abajo, izquierda y arriba.
     * @param direccion en que se mueve 
     */

    public void movimientoFichaAzul(int direccion) {
        switch (direccion) {
            case 1: //esquina superior derecha
                if (posXActual >= posXFutura) {
                    posXActual = posXFutura;
                    timerFichaAzul.stop();
                } else {
                    posXActual += 1;
                }
                break;
            case 2: //desde la esquina sup derecha hasta la esquina inf derecha
                if (posYActual >= posYFutura) {
                    posicionActual = posicionFutura;
                    timerFichaAzul.stop();
                } else {
                    posYActual += 1;
                }
                break;
            case 3: //desde la esquina inf derecha hasta la esquina inf izquierda
                if (posXActual <= posXFutura) {
                    posicionActual = posicionFutura;
                    timerFichaAzul.stop();
                } else {
                    
                    posXActual -= 1;
                }
                break;
            case 4: //desde la esquina inf izquierda a la esquina sup izquierda
                if (posYActual <= posYFutura) {
                    posicionActual = posicionFutura;
                    timerFichaAzul.stop();
                } else {
                    
                    posYActual -= 1;
                }
                break;

        }
        nAzul.setLocation(posXActual, posYActual);
    }
    
    
    public void animarFichaRoja() {
        
        if (posicionActual >= 0 && posicionActual < 3) {
            movimientoFichaRoja(1);

            if(nRojo.getX() >= 700)
            {
                movimientoFichaRoja(2);
                timerFichaRojo.start();
            }
 
        } else if (posicionActual >= 3 && posicionActual < 6) {
            movimientoFichaRoja(2);
            
            if(nRojo.getY()>=452)
            {
                movimientoFichaRoja(3);
                timerFichaRojo.start();
            }
        } else if (posicionActual >= 6 && posicionActual < 9) {
            movimientoFichaRoja(3);
            
            if(nRojo.getX() == 45)
            {
                movimientoFichaRoja(4);
                timerFichaRojo.start();
            }
        } else if (posicionActual >= 9 && posicionActual != 0) {
            
            movimientoFichaRoja(4);
            
            if(nRojo.getY()==0)
            {
                movimientoFichaRoja(1);
                timerFichaRojo.start();
            }

            
        }
        control.refrescar();
    }

    public void movimientoFichaRoja(int direccion) {
        switch (direccion) {
            case 1: //esquina superior derecha
                if (posXActual >= posXFutura) {
                    posXActual = posXFutura;
                    timerFichaRojo.stop();
                } else {
                    posXActual += 1;
                }
                break;
            case 2: //desde la esquina sup derecha hasta la esquina inf derecha
                if (posYActual >= posYFutura) {
                    posicionActual = posicionFutura;
                    timerFichaRojo.stop();
                } else {
                    posYActual += 1;
                }
                break;
            case 3: //desde la esquina inf derecha hasta la esquina inf izquierda
                if (posXActual <= posXFutura) {
                    posicionActual = posicionFutura;
                    timerFichaRojo.stop();
                } else {
                    
                    posXActual -= 1;
                }
                break;
            case 4: //desde la esquina inf izquierda a la esquina sup izquierda
                if (posYActual <= posYFutura) {
                    posicionActual = posicionFutura;
                    timerFichaRojo.stop();
                } else {
                    
                    posYActual -= 1;
                }
                break;

        }
        nRojo.setLocation(posXActual, posYActual);
    }
    
}
