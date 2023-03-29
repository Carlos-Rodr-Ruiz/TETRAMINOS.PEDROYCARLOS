import java.util.Scanner;

public class Jugador {
    private Tablero tableroJugador;
    private int tipo; //0 = humano , 1= maquina
    private String nombreJugador;
    private Tetraminos [] tetraminos;
    private int puntos;

    /**
     * Método para asignar nombre que el usuario desee mientras no sea el de la CPU. Pasará nombre
     * a mayusculas y quitará espacios
     */
    public void fijarNombre(){

        Scanner tc = new Scanner(System.in);
        String nombre;
        boolean nombreValido=false;

        while (nombreValido==false){

            System.out.println("Introduzca su nombre:");
            nombre=tc.next();
            if (nombre.equals("C P U")){

                System.out.println("Nombre reservado para la Maquina");
                nombreValido=false;

            }

            else {

                StringBuilder aux = new StringBuilder(nombre);
                nombre=aux.toString().trim();
                nombre.toUpperCase();
                this.nombreJugador = nombre;
                nombreValido=true;

            }

        }

    }

    public Jugador(Tablero tableroJugador, int tipo, String nombreJugador, Tetraminos[] tetraminos, int puntos) {
        this.tableroJugador = tableroJugador;
        this.tipo = tipo;
        this.nombreJugador = nombreJugador;
        this.tetraminos = tetraminos;
        inicializarTetraminos();
    }

    /**
     * calcula los puntos. Por defecto añadirá 100 ptos, pero recompensará con un bonus por cada
     * acierto seguido
     * @param intentosSeguidos cuantos intentos seguidos lleva para recompensar al jugador
     */
    public void calcularPuntos(int intentosSeguidos){

        puntos+=100;
        puntos+=50*intentosSeguidos;

    }

    public Jugador() {
        this(new Tablero(),1,"",new Tetraminos[4],0);
    }

    public Tablero getTableroJugador() {
        return tableroJugador;
    }

    /**
     * Inicializa el array de tetraminos
     */
    public void inicializarTetraminos(){

        String ANSI_VOLVER_A_BLANCO = "\u001B[0m";
        String ANSI_VERDE = "\u001B[32m";
        String ANSI_AMARILLO = "\u001B[33m";
        String BLUE = "\033[0;34m";
        String RED = "\033[0;31m";
        String cyan="\u001B[46m";

        for (int i=0; i<tetraminos.length; i++){
            if (i==0){ // barco 1, amarillo
                tetraminos[i]=new Tetraminos(100,new Casilla[4],i,ANSI_AMARILLO);
            }
            if (i==1){ // barco 2, verde
                tetraminos[i]=new Tetraminos(100,new Casilla[4],i,ANSI_VERDE);
            }
            if (i==2){ // barco 3, blanco
                tetraminos[i]=new Tetraminos(100,new Casilla[4],i,ANSI_VOLVER_A_BLANCO);
            }
            if (i==3){ // barco 4, cyAN
                tetraminos[i]=new Tetraminos(100,new Casilla[4],i,cyan);
            }
        }

    }

    /**
     * Método que recibe un ataque en el tablero, señalando primero la fila y luego la columna y este devuelve true si aciertas a un tetramino
     * @param fila
     * @param colum
     * @return
     */
    public boolean recibirAtaque(int fila, int colum){
        boolean golpe = false;
        if (tableroJugador.getTablero()[fila][colum].getAlias().equals("■")){
            tableroJugador.getTablero()[fila][colum].setAlias("x");
            for (int i=0; i<tetraminos.length; i++){
              tetraminos[i].calcularVida();
            }

            golpe=true;
        }
        else if (tableroJugador.getTablero()[fila][colum].getAlias().equals("~")){
            tableroJugador.getTablero()[fila][colum].setAlias("o");
        }
        return golpe;
    }

    /**
     * Metodo que crea un mensaje inicial para darle mas inmersion al juego
     * @throws InterruptedException
     */
    public void mensajeInicial() throws InterruptedException {
    String ANSI_VOLVER_A_BLANCO = "\u001B[0m";
    String ANSI_VERDE = "\u001B[32m";
    String ANSI_AMARILLO = "\u001B[33m";
    String BLUE = "\033[0;34m";
    String RED = "\033[0;31m";
    String cyan="\u001B[46m";
    Thread.sleep(1 * 50);
    System.out.print(ANSI_VERDE+"I");
    Thread.sleep(1 * 50);
    System.out.print(ANSI_VERDE+"n");
    Thread.sleep(1 * 50);
    System.out.print(ANSI_VERDE+"i");
    Thread.sleep(1 * 50);
    System.out.print(ANSI_VERDE+"c");
    Thread.sleep(1 * 50);
    System.out.print(ANSI_VERDE+"i");
    Thread.sleep(1 * 50);
    System.out.print(ANSI_VERDE+"a");
    Thread.sleep(1 * 50);
    System.out.print(ANSI_VERDE+"n");
    Thread.sleep(1 * 50);
    System.out.print(ANSI_VERDE+"d");
    Thread.sleep(1 * 50);
    System.out.print(ANSI_VERDE+"o");
    Thread.sleep(1 * 50);
    System.out.print(ANSI_VERDE+" s");
    Thread.sleep(1 * 50);
    System.out.print(ANSI_VERDE+"e");
    Thread.sleep(1 * 50);
    System.out.print(ANSI_VERDE+"s");
    Thread.sleep(1 * 50);
    System.out.print(ANSI_VERDE+"i");
    Thread.sleep(1 * 50);
    System.out.print(ANSI_VERDE+"ó");
    Thread.sleep(1 * 50);
    System.out.print(ANSI_VERDE+"n" );
    Thread.sleep(1 * 50);
    Thread.sleep(1 * 500);
    System.out.print(".");
    Thread.sleep(1 * 500);
    System.out.print(".");
    Thread.sleep(1 * 500);
    System.out.println(".");
    Thread.sleep(1 * 500);

    System.out.print("🫡"+ANSI_VERDE+"Saludos general ");
    Thread.sleep(1 * 100);
    System.out.println(ANSI_VERDE+getNombreJugador()+ANSI_VOLVER_A_BLANCO+"🪖");
    Thread.sleep(1 * 1500);
    System.out.println("😥"+RED+"Un malvado androide pretende conquistar el mundo😱 y nuestra mision es acabar con su fuerza militar. \n" +
            "Siga las instrucciones que se le darán a continuacion y haga todo los posible por salvar al mundo. \n" +
            "El futuro está en sus manos!!! \n");
    Thread.sleep(1 * 1500);
    System.out.println(ANSI_VOLVER_A_BLANCO+"A continuacion se mostrará su tablero. \n"+
            "En el tendrá que poner la raiz del barco desde la que se generarán los tetraminos. \n" +
            "Se recomienda no poner los barcos muy juntos, pues puede generar un bucle infinito que no\n" +
            "hemos podido correjir por limitaciones de tiempo. Si durante el turno de la máquina se genera \n" +
            "un bucle aleatorio, reinicie la ejecución (Lo sabrá porque no se ejecutará nada aunque hallan pasado \n" +
            "mas de 2 segundos. Tambien avisamos que se usan pequeñas interrupciones temporales para facilitar \n" +
            "que el jugador lea los mensajes. Sin mas que decir: ¡¡¡A HUNDIR LOS TETRAMINOS GENERAL!!!)");
    Thread.sleep(1 * 2000);
}

    /**
     * Este método busca barcos en el tablero del jugador y devuelve un valor booleano que indica si hay algún barco
     * que todavía esté en pie (es decir, no ha sido completamente destruido).
     * @return barcos
     */
    public boolean buscarBarcos(){
        boolean barcos=true;
        int barcosEnPie=0;
        for (int y = 0; y < tableroJugador.getTablero().length; y++) { // de arriba a abajo

            for (int x = 0; x < tableroJugador.getTablero()[y].length; x++) { // de arriba a abajo

                if (((tableroJugador.getTablero()[y][x]).getAlias()).equals("■") ) {
                   barcosEnPie++;
                }
            }

            if (barcosEnPie==0){
            barcos=true;}
            else {
                barcos=false;
            }
        }

        return barcos;
    }

    /**
     * Método para que la IA tenga un mínimo de inteligencia y dispare cerca si ha dado a algún tetraminido
     * @param fila
     * @return
     */
    public int maquinaCreeQueSabeColumna(int fila){
        //La máquina disparará cerca de donde ha acertado
        boolean aleatorioCorrecto = false;

        do {
            int filaNueva = (int) Math.random()*2+0 ;
            // 1 mueve una casilla a la izq, 0 a la derecha, 2 se queda igual



            switch (filaNueva){
                case 0:
                    if (tableroJugador.comprobarCasilla(filaNueva+1,filaNueva+1)==true){
                        fila=filaNueva+1;
                        aleatorioCorrecto=true;
                    }
                    break;
                case 1:
                    if (tableroJugador.comprobarCasilla(filaNueva-1,filaNueva-1)==true){
                        fila=filaNueva-1;
                        aleatorioCorrecto=true;
                    }
                    break;
                default: aleatorioCorrecto=true; // Si es 3 se queda igual
            }
        }while (aleatorioCorrecto == false);

        return fila;
    }



    public void setTableroJugador(Tablero tableroJugador) {
        this.tableroJugador = tableroJugador;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public Tetraminos[] getTetraminos() {
        return tetraminos;
    }

    public void setTetraminos(Tetraminos[] tetraminos) {
        this.tetraminos = tetraminos;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
