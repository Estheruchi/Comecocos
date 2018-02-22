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
 * @author Estheruchi
 */
public class Modelo {

    private static final String[] POSICIONES = {"45,0", "260,0", "490,0", "700,0", "700,155",
        "700,305", "700,452", "490,452", "260,452", "45,452", "45,305", "45,155"};
    private static final int JUGADOR1 = 1, JUGADOR2 = 2;
    private static final int CASTILLO = 0;
    private final static int MAX=5,MIN=1;

    private Timer timerAzul;
    private Timer timerRojo;
    
    private int[] estado; //castillo-azul-rojo
    
    private Controlador control;
    private Dado dado;
    private int turno = 0;
    private Ficha nAzul; //1
    private Ficha nRojo;//2
    private Ficha castillo;//3
    private int indNuevo;
    private int avance;
    private int puntosJug1=0;
    private int puntosJug2=0;
    private int tiempo=5;
    private int posicionActual,posicionFutura;
    private int posXActual,posYActual;
    private int posXFutura,posYFutura;
    
    



    public Modelo(Controlador control) {
        this.control = control;
        this.dado = new Dado(this);
        estado = new int[3];
        turno = JUGADOR1;
        escuchar();
        generarPersonajes();
        //crearTimerAzul();
        

    }

    public void escuchar() {
        dado.addMouseListener(control);
    }

    /**
     * Genera un aleatorio entre el maximo y el minimo. Dibuja el dado. Desplaza el personaje en
     * funcion del turno y cambia el turno.
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
     * @return ese indice
     */
    public int darNumero() {
        int n = (int) Math.floor(Math.random() * 11);
        return n;
    }
    
    /**
     * genera un indice y comprueba que no haya otra ficha en esa posicion, si la hay genera otro
     * indice distinto. 
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
     * @param indice recibe el indice del personaje y comprueba que no este repetido.
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
     * @param personaje recibe la ficha(que es el personaje a colocar).
     */
    public void colocarFicha(int personaje){
        int indice=-1;
        Ficha nombre=null;
        switch (personaje) {
            case CASTILLO:
                indice=0;
                nombre=castillo;
                break;
            case JUGADOR1:
                indice=1;
                nombre=nAzul;
                break;
                
            case JUGADOR2:
                indice=2;
                nombre=nRojo;
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
     * Mueve las fichas en funcion del turn y segun el avance(dado). Comprueba si ha habido choque
     * antes de mover.
     */
    public void desplazar() {
        int nuevaPosicion;
        String arrayXY[];
        int posAMover;
        switch (turno) {
            case JUGADOR1: //nAzul
                nuevaPosicion = estado[1] + avance;
                posicionActual=estado[1];
                System.out.println("1º POSICION: "+posicionActual); 
                comprobarCoordenadas(nuevaPosicion);
                
                String[] posicionAct = separarCoordenadas(posicionActual);
                posXActual=Integer.parseInt(posicionAct[0]);
                posYActual= Integer.parseInt(posicionAct[1]);
                
                mover(1);
                posAMover=estado[1];
                posicionFutura=estado[1];
                
                System.out.println("2º posicion futura: "+posicionFutura);
                arrayXY = separarCoordenadas(posAMover);
                        
                String[] posicionFut = separarCoordenadas(posicionFutura);
                posXFutura=Integer.parseInt(posicionFut[0]);
                posYFutura= Integer.parseInt(posicionFut[1]);
                
                //nAzul.setPosicion(Integer.parseInt(arrayXY[0]), Integer.parseInt(arrayXY[1]));
                crearTimerAzul();
                
                break;

            case JUGADOR2:
                nuevaPosicion = estado[2] + avance;
                posicionActual=estado[2];
                System.out.println("1º POSICION: "+posicionActual);
                comprobarCoordenadas(nuevaPosicion);
                mover(2);
                posAMover=estado[2];
                posicionFutura=estado[2];
                System.out.println("2º posicion futura: "+posicionFutura);
                arrayXY = separarCoordenadas(posAMover);
                //nRojo.setPosicion(Integer.parseInt(arrayXY[0]), Integer.parseInt(arrayXY[1]));
                break;
        }
    }

    /**
     * 
     * @param indice recibe el personaje que ha realizado el choque y comprueba que otra ficha participa
     * en el choque.
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
     * Va comprobando el indice de uno en uno para evitar que se salga del tablero(array).
     * @param personaje recibe el personaje a mover (ninja azul o rojo)
     */
    public void mover(int personaje) { //estado(1)-> ninjaAzul , estado(2) ->ninjaRojo
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

    /**
     * Comprueba que el indice no se salga del array.
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
     * @param personaje recibe el personaje 
     */
    public void hayChoque(int personaje) {
            switch (personaje) {
                case CASTILLO:
                    cambiarTurno();
                    System.out.println("ha chocado con castillo");
                    generarPosicion();
                    colocarFicha(0);
                    
                    break;
                case JUGADOR1:
                    System.out.println("come al jugador 1");
                    puntosJug2++;
                    control.pintarPuntos(puntosJug2,JUGADOR2);
                    generarPosicion();
                    colocarFicha(1);
                    break;
                case JUGADOR2:
                    System.out.println("come al jugador 2");
                    generarPosicion();
                    puntosJug1++;
                    control.pintarPuntos(puntosJug1,JUGADOR1);
                    colocarFicha(2);
                    break;
            }
    }

    public void darImagen(JLabel nuevaImagen) {
        control.darImagen(nuevaImagen);
    }
    
    

    //******************************************************************************************
    //ANIMACION DE MOVIMIENTO DE LAS FICHAS
    
    public void crearTimerAzul(){
        timerAzul=new Timer(tiempo, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animarFichaAzul();
                
            }
        });
        timerAzul.start();
    }
    
        public void crearTimerRojo(){
        timerRojo=new Timer(tiempo, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //animarFichaRoja();
            }
        });
        timerRojo.start();
    }
        
    public void animarFichaAzul(){

       if(posicionActual>=0 && posicionActual<3){
           //posXActual+=2;
           moverX(1);
       }
       else if(posicionActual>=3 && posicionActual<6){
           posYActual+=2;
       }
       else if (posicionActual>=6 && posicionActual<9){
           //posXActual-=2;
           moverX(2);
       }
       else if(posicionActual>=9 && posicionActual!=0){
           posYActual-=2;
       }
       
              
        nAzul.setLocation(posXActual, posYActual);
        control.refrescar();

    }
    
    public void animarFichaRoja(){
        
    }
    
    public void moverX(int direccion) { //1 para la drecha, 2 para la izquierda
        switch (direccion) {
            case 1:
                if(posXActual llega) {
                    if(y llega){
                    
                    }
                }
                
                
                break;
            case 2:
                while (posXActual != posXFutura) {
                    posXActual -= 2;
                }
                break;
        }

    }

}
