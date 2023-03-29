import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class Juego {
    /**
     * @author Pedro Rodríguez Serrano y Carlos Rodríguez Ruiz
     * @version 0.9
     * (No está del todo_terminado por limitaciones de tiempo, por eso es la version 0.9.
     * A veces da errores o bucles infinitos. Si eso ocurre hay que reiniciar el juego.
     * Tampoco hemos logrado implementar un método que calcule la vida de los barcos)
     * @param args
     * @throws InterruptedException (Es algo meramente estético para imprimir datos)
     */

    public static void main(String[] args) throws InterruptedException {
        //prepara al programa para recibir pausas temporales sin que se caiga la ejecucion
        Scanner tc = new Scanner(System.in);

        // Juego activo (mientras sea true, aunque se termine una partida se podrá seguir jugando)
        boolean juegoActivo= true;


        do {

            // ahora se iniciará una nueva partida y se resetean datos y variables de anterior partida

            //Número de turnos jugados y turnos jugados cpu (ahorra recursos a la hora de buscar barcos)
            int turnosJugados=0;
            int turnosJugadosCpu=0;

            //turnos jugados seguidos sirve para poner una bonificacion de puntos a la racha de puntos
            int turnosJugadosSeguidos=0;

            //turno usuario indica si toca al jugador (y se ejecutará do{}while del turno jugador)
            boolean turnoUsuario=true;
            //turno usuario indica si toca a la máquina (y se ejecutará do{}while del turno maquina)
            boolean turnoMaquina=false;
            // incica que la partida ha terminado (Es decir que uno de los dos ha ganado.
            // Luego se te preguntará si deseas continuar. Si dices que no, se saldrá del buclejuago activo)
            boolean partidaActiva =true;

            // Creacion jugador humano
            Jugador humano = new Jugador();
            //Se inicializan datos de jugador (nombre, tipo...)
            humano.setTipo(1); // jugador tipo 1 es humano, 0 maquina
            humano.fijarNombre(); //método que valida nombre
            humano.mensajeInicial();// Mensaje de introduccion (elemento estético)
            humano.inicializarTetraminos();// Para que el tablero no sea null

            //Te pedirá la ubicacion desde la que se generará un tetramino aleatorio
            // Se repite 4 veces, que es la longitud donde  se almacenan los tetraminos del jugador
            for (int i=0; i<humano.getTetraminos().length; i++){
                //Imprime tablero para que veas coordenadas

                humano.getTableroJugador().imprimirTablero(humano.getTipo(), humano.getNombreJugador());
                System.out.println("id humano="+humano.getTipo());

                System.out.println("Introduzca fila de la raiz del barco numero "+i);
                // comprobará con un try si la poscion no genera excepcion, si la genera te pone una coordenada aleatoria válida
                int fila=0;
               try {
                  fila = tc.nextInt();
               }catch (InputMismatchException inputMismatchException) {
                   System.out.println("Parámetro no válido...\n" +
                           "Se pondrá un parámetro aleatorio");
                   Random rand = new Random();
                  fila = rand.nextInt(10) ;
                   Thread.sleep(1 * 1000);
                   //Para que haya una pequeña pausa (es algo meramente estético)

               }



                System.out.println("Introduzca columna de la raiz del barco numero "+i);

               //Funciona igual que la anterior, pero para facilitar al trabajo al usuario,
                // te pide una letra y la convertirá a numero con un método de la clase tablero
                int column=0;
                try { //Por si introduces mal un número
                    String letra =tc.next();
                    //pide letra y la convierte a un int
                    column= humano.getTableroJugador().letraNum(letra);
                }catch (InputMismatchException inputMismatchException) {
                    System.out.println("Parámetro no válido...\n" +
                            "Se pondrá un parámetro aleatorio");
                    Random rand = new Random();
                    column = rand.nextInt(10) ;
                    Thread.sleep(1 * 1000);
                    //Para que haya una pequeña pausa (es algo meramente estético)
                }
                //Variable que comprueba si es válida la posicion (no hay ningun barco ya puesto)
                boolean resultado = humano.getTetraminos()[i].ponerRaizBarcos(humano,fila,column);

                //Si es falso, descuenta intento y repite bucle
                if (resultado==false){
                    System.out.println("Posicion incorrecta");
                    i--;

                } else if (resultado) {
                    System.out.println("posicion Correcta");
                }
            }
            //Imprime tablero jugador con cambios hechos
            humano.getTableroJugador().imprimirTablero(humano.getTipo(), humano.getNombreJugador());

            //Creacion jugador C P U
            Jugador CPU = new Jugador();
            CPU.inicializarTetraminos();
            CPU.setTipo(0);
            //Las variables bajo sospecha sirven para que dispare cerca si ha acertado.
            // Esto solo será si ha acertado y lleva más de 1 turno seguido
            int filaBajoSospecha=0;
            int columnaBajoSospecha=0;

            //Funcionará igual que con el jugador, pero con valores aleatorios y sin conversion de string a int
            for (int i=0; i<CPU.getTetraminos().length; i++){

                Random rand = new Random();
                int fila = rand.nextInt(10) ;
                int column=rand.nextInt(10) ;

                boolean resultado = CPU.getTetraminos()[i].ponerRaizBarcos(CPU,fila,column);

                if (resultado==false){
                    i--;

                } else if (resultado) {

                }
            }

            do { //una vez inicializados los objetos y las variables, comienza el juego

                do{
                    //Comprobará si quedan barcos en pie apartir de turno 12 (porque es imposible hundirlos antes de 12 intentos)
                    if (turnosJugados>12){
                        //Va a recorrer teclado en busca de puntos (no hemos logrado usar el método para comprobar vida)
                       if (CPU.buscarBarcos()){
                           System.out.println("Felicidades!!! Has ganado");
                           Thread.sleep(1 * 1000);//Para que haya una pequeña pausa (es algo meramente estético)
                           System.out.println("obtubiste "+humano.getPuntos()+" puntos");
                           //Termina partida y pregunta si quieres volver a jugar
                           partidaActiva=false;
                       }
                    }
                    //imprime tablero en el que vas a hacer los ataques
                    CPU.getTableroJugador().imprimirTablero(CPU.getTipo(), CPU.getNombreJugador());
                    try{
                        System.out.println("Numero de disparos hechos: "+turnosJugados);

                        Thread.sleep(1 * 1000);
                        //Para que halla una pequeña pausa (es algo meramente estético)

                        System.out.println("Turnos seguidos: "+turnosJugadosSeguidos);
                        //Igual que arriba cuando se pedía coordenadas pero no pone raiz si no hace ataque
                        System.out.println("Introduzca fila ataque");
                        int fila = tc.nextInt();
                        System.out.println("Introduzca columna");
                        String letra =tc.next();
                        int column= humano.getTableroJugador().letraNum(letra);

                        //método devuelve true si ha hundido un cuadrado
                        boolean funcion = CPU.recibirAtaque(fila,column);

                        //Si ha hundido barco:
                        if (funcion==true){
                            System.out.println("Felicidades");
                            turnosJugados++;
                            turnosJugadosSeguidos++;
                            humano.calcularPuntos(turnosJugadosSeguidos);
                            Thread.sleep(1 * 1000);
                            //Para que haya una pequeña pausa (es algo meramente estético)
                            System.out.println("Tus puntos actuales son: "+humano.getPuntos());
                            /*for (int i=0; i<CPU.getTetraminos().length; i++){
                               if (CPU.getTetraminos()[i].hundido()){
                                   System.out.println("😁El tetramino numero "+CPU.getTetraminos()[i].getId_figura()+" ha sido hundido 🫡");
                               }
                            }*/
                        }
                        else {
                            System.out.println("Que pena, parece que fallaste...\n");
                            Thread.sleep(1 * 1000);
                            //Para que haya una pequeña pausa (es algo meramente estético)
                            System.out.println("Tus puntos actuales son: "+humano.getPuntos());
                            turnosJugados++;
                            turnosJugadosSeguidos=0;
                            turnoMaquina=true;
                            turnoUsuario=false;
                        }
                    }catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                        System.out.println("Losento, pero la posicion no es válida");
                    }

                }while (turnoUsuario==true);


                do{
                    boolean acertoCasilla=false;// cuando sea true, se ejecutará un método que buscará posiciones cercanas


                    turnosJugadosCpu++;
                    if (turnosJugadosCpu>12){
                        if (humano.buscarBarcos()){
                            System.out.println("Oh no... Has perdido... ");
                            Thread.sleep(1 * 1000);
                            //Para que haya una pequeña pausa (es algo meramente estético)
                            System.out.println("Tus puntos se quedan en "+humano.getPuntos());
                            partidaActiva=false;
                        }
                    }

                    if (acertoCasilla){ //igual que el normal, pero parte de una casilla en la que acertó
                        try{
                            Random rand = new Random();
                            int fila = CPU.maquinaCreeQueSabeColumna(filaBajoSospecha) ;
                            int column= CPU.maquinaCreeQueSabeColumna(columnaBajoSospecha) ;
                            //En la clase aparecerá como fila el parámetro para ahorrar código
                            // pero como es un array cuadrado no pasa nada
                            boolean funcion = humano.recibirAtaque(fila,column);

                            if (funcion==true){
                                humano.getTableroJugador().imprimirTablero(humano.getTipo(), humano.getNombreJugador());
                                System.out.println("Oh no!!! te han derribado un barco!!!");
                                Thread.sleep(1 * 1000);
                                // Para que de tiempo a leerlo (es algo meramente estético)
                                acertoCasilla=true;
                                filaBajoSospecha=fila;
                                columnaBajoSospecha=column;

                            }
                            else {
                                humano.getTableroJugador().imprimirTablero(humano.getTipo(),humano.getNombreJugador());
                                System.out.println("Uf... parece que falló la máquina");
                                Thread.sleep(1 * 1000);
                                // Para que de tiempo a leerlo (es algo meramente estético)
                                filaBajoSospecha=0;
                                columnaBajoSospecha=0;
                                acertoCasilla=false;
                                turnoUsuario=true;
                                turnoMaquina=false;
                            }
                        }catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                            // vuelve a intentarlo
                        }
                    }

                    try{
                        //crea las posiciones aleatorias
                        Random rand = new Random();
                        int fila = rand.nextInt(10) ;
                        int column=rand.nextInt(10) ;
                        boolean funcion = humano.recibirAtaque(fila,column);

                        if (funcion==true){
                            humano.getTableroJugador().imprimirTablero(humano.getTipo(), humano.getNombreJugador());
                            System.out.println("Oh no!!! te han derribado un barco!!!");
                            Thread.sleep(1 * 1000);
                            //Para que haya una pequeña pausa (es algo meramente estético)
                            // Para que de tiempo a leerlo (es algo meramente estético)
                            acertoCasilla=true;
                            filaBajoSospecha=fila;
                            columnaBajoSospecha=column;
                          /*  for (int i=0; i<CPU.getTetraminos().length; i++){
                                if (CPU.getTetraminos()[i].hundido()){
                                    System.out.println("😱 Oh no!!! El tetramino numero "+CPU.getTetraminos()[i].getId_figura()+" ha sido hundido!!!");
                                }
                            }*/

                        }
                        else {
                            humano.getTableroJugador().imprimirTablero(humano.getTipo(), humano.getNombreJugador());
                            System.out.println("Uf... parece que falló la máquina");
                            Thread.sleep(1 * 1000);
                            //Para que haya una pequeña pausa (es algo meramente estético)
                            // Para que de tiempo a leerlo (es algo meramente estético)
                            filaBajoSospecha=0;
                            columnaBajoSospecha=0;
                            acertoCasilla=false;
                            turnoUsuario=true;
                            turnoMaquina=false;
                        }
                    }catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                      // vuelve a intentarlo
                    }


                }while (turnoMaquina);



            }while (partidaActiva);
//Si sale es porque partida ha terminado
            System.out.println("desea jugar otra vez? \n" +
                    "s para si \n " +
                    "n para no \n");
            String opcion=tc.next();
            Thread.sleep(1 * 1000);
            //Para que haya una pequeña pausa (es algo meramente estético)
            if (opcion.equals("s")||opcion.equals("S")){
                System.out.println("Iniciando nueva partida...");
            } else if (opcion.equals("n")||opcion.equals("N")) {
                System.out.println("hasta la proxima");
                juegoActivo=true;
            }


        } while (juegoActivo);
        System.out.println("Fin del juego");

    }
}