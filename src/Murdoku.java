import java.util.Scanner;

public class Murdoku {

    static final int TAM = 7;

    // Identificadores de las zonas
    static final int PICNIC = 1;
    static final int JARDIN = 2;
    static final int ESTANQUE = 3;
    static final int CENADOR = 4;

    // Identificadores de los elementos
    static final int VACIO = 0;
    static final int SILLA = 1;
    static final int ALFOMBRA = 2;
    static final int ARBOL = 3;
    static final int FLORES = 4;
    static final int PIEDRA = 5;
    static final int MESA = 6;

    static Scanner teclado = new Scanner(System.in);

    // Tablero donde se colocan los personajes
    static char[][] tablero = new char[TAM][TAM];

    // Elementos fijos del escenario
    static int[][] elementos = {
            { 0, 1, 1, 0, 4, 0, 4 },
            { 3, 2, 2, 0, 0, 0, 4 },
            { 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 5, 0, 0, 3 },
            { 1, 0, 0, 0, 0, 0, 1 },
            { 0, 0, 0, 0, 0, 0, 1 },
            { 2, 2, 1, 0, 1, 6, 0 }
    };

    // Zona a la que pertenece cada casilla
    static int[][] zonas = {
            { 1, 1, 1, 1, 2, 2, 2 },
            { 1, 1, 1, 1, 2, 2, 2 },
            { 1, 1, 3, 3, 2, 2, 2 },
            { 1, 1, 3, 3, 3, 2, 2 },
            { 1, 1, 3, 3, 4, 4, 4 },
            { 1, 1, 1, 3, 4, 4, 4 },
            { 1, 1, 1, 1, 4, 4, 4 }
    };

    // Datos de los personajes
    static String[] nombres = {
            "Avery", "Bruno", "Christian",
            "Daphne", "Ethan", "Fabian", "Vicky"
    };

    static char[] letras = {
            'A', 'B', 'C', 'D', 'E', 'F', 'V'
    };

    // Indica qué personajes ya están colocados
    static boolean[] colocados = new boolean[7];

    public static void main(String[] args) {

    }

    // Devuelve el símbolo correspondiente a un elemento
    public static char simboloElemento(int elemento) {

        switch (elemento) {

            case SILLA:
                return 'S';

            case ALFOMBRA:
                return '=';

            case ARBOL:
                return 'T';

            case FLORES:
                return '*';

            case PIEDRA:
                return 'P';

            case MESA:
                return 'M';

            default:
                return '.';

        }

    }

    // Comprueba si una casilla puede ser ocupada
    public static boolean esTransitable(int fila, int columna) {

        int elemento = elementos[fila][columna];

        return elemento == VACIO
                || elemento == SILLA
                || elemento == ALFOMBRA;

    }

    public static void retirarPersonaje() {

        char letra;
        int indice = -1; // índice centinela

        System.out.println("Introduce letra de personaje a eliminar: ");
        letra = teclado.next().toUpperCase().charAt(0);

        for (int i = 0; i < letras.length; i++) {

            if (letras[i] == letra) { // si la letra introducida se encuentra en el array
                indice = i; // se guarda la posicion i en índice
            }
        }

        if (indice == -1) { // si tras recorrer el array el índice sigue valiendo -1
            // significa que nunca entró en el if anterior por lo que la letra no existe
            System.out.println("La letra no existe");
        } else if (!colocados[indice]) { // si el índice vale de 0 a 6 pero esa posición da false en el array de
                                         // colocados , el personaje no está en el tablero
            System.out.println("El personaje no esta en el tablero");
        } else { // si se ha encontrado la letra o el personaje en el tablero ,se recorre el
                 // mismo para quitar el personaje
            for (int i = 0; i < TAM; i++) {
                for (int j = 0; j < TAM; j++) {

                    if (tablero[i][j] == letra) { // si dentro de las filas y columnas del tablero está la letra

                        tablero[i][j] = '.';// se sustituye la letra por un punto
                    }
                }
            }

            colocados[indice] = false; // al finalizar el bucle se convierte a false indicando que el personaje ha sido
                                       // eliminado
            System.out.println("Personaje eliminado");

        }
    }

    public static boolean finalizarPartida() {

        boolean estanColocados = true;
        boolean finalizado = false;

        for (int i = 0; i < colocados.length; i++) {

            if (!colocados[i]) { // si no hay ningún personaje colocado en cualquier posición del array
                estanColocados = false; // la variable pasa a valer false
            }
        }
        if (!estanColocados) { // al no haber personajes se imprime un mensaje indicándolo
            System.out.println("No hay personajes");
        } 

        return finalizado;
    }

}