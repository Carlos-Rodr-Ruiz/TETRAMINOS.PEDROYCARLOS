public class Tablero {
private Casilla [] [] tablero;
private int id_propietario;


    public Tablero(Casilla[] [] tablero, int id_propietario) {
        this.tablero = tablero;
        inicializarTablero();
        this.id_propietario = id_propietario;
    }
    public Tablero(int id_propietario) {
        this.tablero = new Casilla[10][10];
        inicializarTablero();
        this.id_propietario = id_propietario;
    }
    public Tablero() {
       this(new Casilla[10][10],0);
    }

    public Casilla[][] getTablero() {
        return tablero;
    }

    public void setTablero(Casilla[][] tablero) {
        this.tablero = tablero;
    }

    public int getId_propietario() {
        return id_propietario;
    }

    public void setId_propietario(int id_propietario) {
        this.id_propietario = id_propietario;
    }

    /**
     * Método para inicializar el tablero(array de dos dimensiones), con sus casillas correspondientes
     */
    public void inicializarTablero(){
        for (int i=0; i<tablero.length; i++){
            for (int j=0; j<tablero.length; j++){
                tablero[i][j]=new Casilla();
                int [] ubicaAux = {j,i};
                tablero[i][j].setUbicacion(ubicaAux);
            }
        }
    }

    /**
     * Imprime el tablero con colores y formatos para diferenciar los distintos elementos.
     * @param id_propietario el id del propietario del tablero (0 si es la máquina, 1 si es el usuario).
     * @param nombrePropietario el nombre del propietario del tablero.
     */
    public void imprimirTablero(int id_propietario, String nombrePropietario) {
        String ANSI_VOLVER_A_BLANCO = "\u001B[0m";
        String ANSI_VERDE = "\u001B[32m";
        String ANSI_AMARILLO = "\u001B[33m";
        String BLUE = "\033[0;34m";
        String RED = "\033[0;31m";
        String cyan="\u001B[46m";

    /*enum bgColor {
      black("\u001B[30m"),
      red("\u001B[31m"),
      green("\u001B[32m"),
      yellow("\u001B[33m"),
      blue("\u001B[34m"),
      magenta("\u001B[35m"),
      cyan("\u001B[36m"),
      white("\u001B[37m"),
      reset("\u001B[0m");*/

        // Imprimimos el encabezado del tablero, dependiendo del propietarioEste es el tablero del jugador.
        if (id_propietario==1){

            System.out.println("Tablero de "+nombrePropietario);
            System.out.println(" ");

            // Imprimimos la primera fila con las letras del alfabeto para identificar las columnas.
            for (int i = -1 ; i < tablero.length; i++) { // de arriba a abajo

                if (i<0){
                    System.out.print("   ");
                }

                if (i < 10 && 0<=i) {
                    System.out.print(" " + numeroaLetra(i) + " ");
                }
                if (i >= 10) {
                    System.out.print(" " + numeroaLetra(i));
                }

            }
            // Imprimimos el resto del tablero fila por fila.
            for (int y = 0; y < tablero.length; y++) { // de arriba a abajo
                if (y < 10) {
                    System.out.println("");
                    System.out.print(y  + "  ");
                }

                if (y >= 10) {
                    System.out.println("");
                    System.out.print(y  + " ");
                }
                // Imprimimos cada celda del tablero con su color y contenido correspondiente.
                for (int x = 0; x < tablero[y].length; x++) { // de arriba a abajo

                    if (((tablero[y][x]).getAlias()).equals("~")) {
                        System.out.print(cyan+" " + (tablero[y][x]).getAlias() + " " + ANSI_VOLVER_A_BLANCO);
                    }
                    if (((tablero[y][x]).getAlias()).equals("■")) {
                        System.out.print(RED+" " + (tablero[y][x]).getAlias() + " " + ANSI_VOLVER_A_BLANCO);
                    }
                    if (((tablero[y][x]).getAlias()).equals("x")) {
                        System.out.print(RED+" " + (tablero[y][x]).getAlias() + " " + ANSI_VOLVER_A_BLANCO);
                    }
                    if (((tablero[y][x]).getAlias()).equals("o")) {
                        System.out.print(ANSI_AMARILLO+" " + (tablero[y][x]).getAlias() + " " + ANSI_VOLVER_A_BLANCO);
                    }

                }

            }
            System.out.println("");
        }

        // Imprimimos el encabezado del tablero, dependiendo del propietario(Este es el tablero de la IA).
        else if(id_propietario==0){
            System.out.println("Tablero maquina");
            System.out.println(" ");
            for (int i = -1 ; i < tablero.length; i++) { // de arriba a abajo

                if (i<0){
                    System.out.print("   ");
                }

                if (i < 10 && 0<=i) {
                    System.out.print(" " + numeroaLetra(i) + " ");
                }
                if (i >= 10) {
                    System.out.print(" " + numeroaLetra(i));
                }

            }

            for (int y = 0; y < tablero.length; y++) { // de arriba a abajo
                if (y < 10) {
                    System.out.println("");
                    System.out.print(y  + "  ");
                }

                if (y >= 10) {
                    System.out.println("");
                    System.out.print(y  + " ");
                }


                for (int x = 0; x < tablero[y].length; x++) { // de arriba a abajo

                    if (((tablero[y][x]).getAlias()).equals("~") ||((tablero[y][x]).getAlias()).equals("■") ) {
                        System.out.print(cyan+" " + "?" + " " + ANSI_VOLVER_A_BLANCO);
                    }
                    if (((tablero[y][x]).getAlias()).equals("x")) {
                        System.out.print(RED+" " + (tablero[y][x]).getAlias() + " " + ANSI_VOLVER_A_BLANCO);
                    }
                    if (((tablero[y][x]).getAlias()).equals("o")) {
                        System.out.print(ANSI_AMARILLO+" " + (tablero[y][x]).getAlias() + " " + ANSI_VOLVER_A_BLANCO);
                    }

                }

            }
            System.out.println("");
        }
    }

    /**
     * Este métodoconvierte un número entero del rango [0, 9] en una letra mayúscula correspondiente a una columna en un tablero.
     * Se utilizan para las cordenadas del tablero.
     * @param num
     * @return
     */
    public String numeroaLetra(int num){
        String letra="";

        if (num==0){
            letra="A";
        }
        else if (num==1) {
            letra="B";
        }
        else if (num==2) {
            letra="C";
        }
        else if (num==3) {
            letra="D";
        }
        else if (num==4) {
            letra="E";
        }
        else if (num==5) {
            letra="F";
        }
        else if (num==6) {
            letra="G";
        }
        else if (num==7) {
            letra="H";
        }
        else if (num==8) {
            letra="I";
        }
        else {
            letra="J";
        }

        return letra;
    }

    /**
     * Este método hace lo contrario al anterior, pues convierte una letra mayúscula en un número entero.
     * Se utilizan para las cordenadas del tablero.
     * @param letra
     * @return
     */
    public int letraNum(String letra){
      letra = letra.toUpperCase();
        int num=0;

        switch (letra){
            case "A":
                num=0;
                break;
            case "B":
                num=1;
                break;
            case "C":
                num=2;
                break;
            case "D":
                num=3;
                break;
            case "E":
                num=4;
                break;
            case "F":
                num=5;
                break;
            case "G":
                num=6;
                break;
            case "H":
                num=7;
                break;
            case "I":
                num=8;
                break;
            case "J":
                num=9;
                break;
            default:
                num=9;
                break;
        }

        return num;
    }

    /**
    * Comprueba si una casilla en el tablero tiene un valor asignado.
    * @param filAux La fila de la casilla a comprobar.
    * @param colAux La columna de la casilla a comprobar.
    * @return True si la casilla tiene un valor asignado, False si la casilla está vacía o está fuera de los límites del tablero.
     */
    public boolean comprobarCasilla(int filAux, int colAux) {

        boolean returnValor = false;



        try{

           if(!tablero[filAux][colAux].equals("")) { //si no hay esxcepcion al buscar en esa casilla, será true
               returnValor=true;
           }
           else {
               returnValor=true;
           }

        }catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){

            returnValor=false;

        }
        return returnValor;

    }

}
