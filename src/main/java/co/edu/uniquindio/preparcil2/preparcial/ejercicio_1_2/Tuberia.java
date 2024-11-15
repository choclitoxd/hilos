package co.edu.uniquindio.preparcil2.preparcial.ejercicio_1_2;

public class Tuberia {
    private char buffer[];
    private int siguiente = 0;
    private int indiceMayor = -1;
    public Tuberia(int bufferSize) {
        buffer = new char[bufferSize];
    }
    // Flags para saber el estado del buffer
    private boolean estaLlena = false;
    private boolean estaVacia = true;

    // M�todo para retirar letras del buffer
    public synchronized char[] recoger()
    {
        // No se puede consumir si el buffer est� vac�o
        while(estaVacia || indiceMayor < 1 )
        {
            try {
                wait(); // Se sale cuando estaVacia cambia a false
            } catch( InterruptedException e ) {
                ;
            }
        }
        // Decrementa la cuenta, ya que va a consumir una letra
        siguiente = siguiente-2;
        indiceMayor = indiceMayor-2;
        // Comprueba si se retir� la �ltima letra
        if( siguiente == 0 ){
            estaVacia = true;
        }
        // El buffer no puede estar lleno, porque acabamos
        // de consumir
        estaLlena = false;
        notify();
        // Devuelve la letra al thread consumidor
        return new char[]{buffer[siguiente],buffer[siguiente+1]};
    }


    // M�todo para a�adir letras al buffer
    public synchronized void lanzar( char c )
    {
        // Espera hasta que haya sitio para otra letra
        while( estaLlena == true )
        {
            try {
                wait(); // Se sale cuando estaLlena cambia a false
            } catch( InterruptedException e ) {
            }
        }
        // A�ade una letra en el primer lugar disponible
        buffer[siguiente] = c;
        // Cambia al siguiente lugar disponible
        siguiente++;
        indiceMayor++;
        // Comprueba si el buffer est� lleno
        if( siguiente == buffer.length )
            estaLlena = true;
        estaVacia = false;
        notify();
    }
}