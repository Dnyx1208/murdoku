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

    //Variable que indica si se ha ganado la partida
    static boolean win = false;

    public static void main(String[] args) {
        tableroOg();
        mostrarPersonajes();
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

    public static void tableroOg(){
        for (int i = 0; i < elementos.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < elementos[0].length; j++) {
                System.out.print(simboloElemento(elementos[i][j]) + " ");
            }
            System.out.print(" |");
            System.out.println();
        }
    }

    public static void mostrarPersonajes(){
        System.out.println("Avery: Estaba sobre una alfombra");
        System.out.println("Bruno: Estaba sentado en una silla en el cenador");
        System.out.println("Christian: Estaba en zona de picnic");
        System.out.println("Daphne: Estaba junto a unas flores y junto a un árbol");
        System.out.println("Ethan: Estaba sentado en una silla");
        System.out.println("Fabian: Estaba en el estanque al norte de Ethan");
        System.out.println("Vicky: Estaba sola con el asesino");
    }

    public static void menuAccion(){
        do {
            System.out.println("¿Que deseas hacer?");
            System.out.println("------------------");
            System.out.println("1: Colocar un Personaje");
            System.out.println("2: Retirar un Personaje");
            System.out.println("3: intentar Ganar");
            int choose = teclado.nextInt();
            switch (choose) {
                case 1:
                    
                    break;
                case 2:
                    retirarPersonaje();
                    break;
                case 3:
                
                    break;
                default:
                    System.out.println("Opcion no válida");
                    break;
            }
        } while (win);
    }

    public static void colocarPersonaje(){
        char letra;
        int indice = -1; // índice centinela
        int fila = -1;
        int col = -1;

        System.out.println("Introduce letra de personaje a colocar: ");
        letra = teclado.next().toUpperCase().charAt(0);

        for (int i = 0; i < letras.length; i++) {

            if (letras[i] == letra) { 
                indice = i;
            }
        }

        if (indice == -1) {
            System.out.println("La letra no existe");
        } else if (colocados[indice]) {
            System.out.println("El personaje introducido ya esta colocado");
        } else {
            System.out.println("Inserta las coordenadas donde lo quieres colocar");
            System.out.print("Fila: ");
            fila = teclado.nextInt();
            System.out.println();
            System.out.print("Columna: ");
            col = teclado.nextInt();

            if (tablero[fila][col] == 'X') {
                System.out.println("No se puede colocar un personaje en una X");
            } else {
                if (!esTransitable(fila, col)) {
                    System.out.println("No se puede colocar un personaje en una casilla no transitable");
                } else {
                    for (int i = 0; i < tablero.length; i++) {
                        for (int j = 0; j < tablero.length; j++) {
                            tablero[j][col] = 'X';
                            tablero[fila][j] = 'X';
                        }
                    }
                    for (int i = 0; i < tablero.length; i++) {
                        for (int j = 0; j < tablero.length; j++) {
                            tablero[fila][col] = letra;
                        }
                    }
                }
            }
        }
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

}