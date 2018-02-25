/*
 PURSUIT NINJA - Esther, Javier y Victor.
** Importante --> Dejar que el movimiento de la ficha termine para volver a lanzar el dado.
** Errores que no se han conseguido solucionar del programa :
** 1.- En ocasiones muy concretas el movimiento de uno de los jugadores no se realiza correctamente,
** esto da lugar a un desajuste de posiciones que hace no se puedan comer. Sin embargo en las sucesivas 
** tiradas todo funciona como debería.
** 2.- Hemos limitado las tiradas a un máximo de 4 porque en la tirada en que sale 5 no se realiza 
** el triple movimiento de manera correcta. Hemos intentado ajustar tanto los índices como las posiciones X e Y 
** de cada ficha pero se produce un error visual cada vez que se tenía que realizar el triple movimiento de la ficha.
** Sin embargo el programa funcionaba de manera correcta porque la ficha se comenzaba a desplazar donde debía.
** 
 */
package comecocos;

/**
 *
 * @author Esther, Javier y Victor
 */
public class Comecocos {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        Controlador c = new Controlador();
    }

}
