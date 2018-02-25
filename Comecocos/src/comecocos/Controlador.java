package comecocos;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

/**
 *
 * @author Esther, Javier y Victor
 */
public class Controlador extends MouseAdapter {

    private Modelo modelo;
    private Vista vista;

    /**
     * Creamos dentro del Controlador tanto la Vista como el Modelo
     */
    public Controlador() {
        vista = new Vista(this);
        modelo = new Modelo(this);
    }

    /**
     * Método que llama a lanzar dado del modelo y lo actualiza en la Vista
     * @param e 
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Tirando...");
        modelo.lanzarDado();
        vista.refrescar();
    }

    public void darImagen(JLabel nuevaImagen) {
        vista.tomaImagen(nuevaImagen);
    }

    public void iluminarJugador(int turno) {
        vista.iluminarJugador(turno);
    }

    public void resetearJugador(int turno) {
        vista.resetearJugador(turno);
    }

    public void pintarPuntos(int puntos,int jugador) {
        vista.pintarPuntos(puntos,jugador);
    }
    
    public void refrescar(){
        vista.refrescar();
    }

}
