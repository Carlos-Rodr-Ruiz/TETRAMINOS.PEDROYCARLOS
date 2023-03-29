public class Tetraminos {
    private int vida;//100 = 4 barcos, 0= 0 barcos en pie
    private Casilla [] fichasTetramino; //Todos los puntos de este tetramino en el mapa
    private int id_figura; //se crearán con un for en la clase usuario, y su id será la i del for
    private String colorBarco; //Se asignará por defecto en el método de jugador dependiendo del id


    /**
     * Este método rellena fichasTetramino con casillas con
     * el constructor por defecto (tiene el valor agua)
     */
    public void inicializarCasillas(){


        for (int i=0; i<fichasTetramino.length; i++){

                fichasTetramino[i]=new Casilla();

        }

    }

    /**
     * Constructor:
     * @param vida por si acaso se quiere manipular la vida, pero normalmente tendrá la vida por defecto:
     *            '100'. Es decir, todos los tetraminos estan con la vida al 100%
     * @param fichasTetramino  Por si acaso se quisiera añadir mas figuras por jugador o menos
     *                         (en nuestro código siempre tiene un lenght de 4)
     * @param id_figura Este parámetro no ha sido casi utilizado, pues la idea original era que tu pudieras
     *                  manipular el algoritmo de creaccion figuras para decirle la figura, pero no se pudo
     * @param colorBarco Este elemento tampoco pudo ser implementado, pero en teoria se imprimiría con ese color
     *                   y cada tetramino tendría un valor
     */
    public Tetraminos(int vida, Casilla[] fichasTetramino, int id_figura, String colorBarco) {
        this.vida = vida;
        this.fichasTetramino = fichasTetramino;
        inicializarCasillas();
        this.id_figura = id_figura;
        this.colorBarco = colorBarco;
    }

    /**
     * Constructor por defecto que pondrá la vida a 100, un array de 4 casillas, un id sin definir y un color vacio
     */
    public Tetraminos() {
        this(100,new Casilla[4],-1,"");
    }

    /**
     * @deprecated : Este método, en el diseño conceptual, serviría para cambiar casillas que no
     *              perteneciesen al tetramino, pero acabó siendo sustituido por otro método
     * @param tableroParametro
     * @param filDisparo
     * @param colDisparo
     * @return
     */
    public boolean tocado(Tablero tableroParametro, int filDisparo, int colDisparo){
        boolean tocado=false; //si ha fallado, false, sino se invoca hundido
        System.out.println("Losiento, este método no sirve");
        return tocado;
    }


    /**
     * Este método originalmente serviría para escanear los tetraminos y comprobar la vida de ese
     * tetramino. (por cada cuadrado sumaría 25 y por cada x restaría 25) Sin embargo, no hemos
     * logrado que funcione. (Probablemente sea porque no se le asigna bien el alias en los otros
     * métodso)
     */
    public void calcularVida(){
        vida=0;
        for (int i=0; i<fichasTetramino.length; i++){
            if (fichasTetramino[i].getAlias().equals("x")){
                vida-=25;
            }
            else if (fichasTetramino[i].getAlias().equals("■")){
                vida+=25;
            }
            else {
                vida=vida;
            }

        }
    }

    /**
     * @deprecated : Método obsoleto que tendría la funcion de comprobar si hay espacio para colocar el
     * tetramino. Pero como se acabó optando por crear tetraminos por un algoritmo, este método nunca fue
     * creado
     *
     * @param tableroPara : Para modificar el tablero y sustituir los alias de agua por barcos
     * @param filDisparo : te pediría una fila para poner en tetramino partiendo de esa raiz
     * @param colDisparo : te pediría una columna para poner en tetramino partiendo de esa raiz
     * @return si hay espacio, devuelve true, sino false
     */
    public boolean hayEspacio(Tablero tableroPara, int filDisparo, int colDisparo){

        boolean espacio=false; //si hay espacio, devuelve true, sino false

        return espacio;

    }

   /* public boolean espacioTetraminoCompleto(Tablero tableroPara, int filDisparo, int colDisparo){

        boolean espacio=false; //si hay espacio, devuelve true, sino false

    for (int i=0; i<fichasTetramino.length;i++){
        //Comprobará 4 veces, y a la minima que una no quepa, mandará un false para que podamos borrar el tetramino
    }
        return espacio;

    }*/

    /**
     * Este método recalcula vida por si acaso está desactualizada y comprobará si tiene vida o no
     * @return Este método retornará true (Es decir, está hundido = true)
     * si la vida del tetramino es 0
     */

    public boolean hundido(){
        boolean result=false;
        calcularVida();
        if (vida==0){
            result=true;
        }
        return result;
    }

   /* public boolean colocarFigura(Tablero tableroPara, int filDisparo, int colDisparo){
        boolean result=false;
        //se invocarán anteriores métodos para comprobar si hay espacio
        if (hayEspacio(tableroPara,filDisparo,colDisparo) &&
        espacioTetraminoCompleto(tableroPara,filDisparo,colDisparo)){

        }
        else {
            result=false;
        }
        return result;
    }*/


    /**
     * Este método servirá para que el jugador mande las coodenadas desde donde quiere
     * que surja el tetramino. Luego, si todo_va bien, llamará al siguiente método
     * @param jugador el jugador se pasa a si mismo para poder actualizar su tablero
     * @param fila fila de la coordenada escojida
     * @param colum columna de la coordenada escojida (ya habrá sido convertida a int)
     * @return true si la coordenada era correcta, false si era incorrecta
     */

    public boolean ponerRaizBarcos(Jugador jugador,int fila, int colum){
        boolean correcta=false;

        System.out.println("comprobando coordenada "+fila+" "+colum);

        try {

            if (jugador.getTableroJugador().getTablero()[fila][colum].getAlias().equals("■")){
                correcta=false;
            }
            else {
                algoritmoCreacionFiguras(jugador.getTableroJugador(),jugador,fila,colum);
                //jugador.getTableroJugador().getTablero()[fila][colum].setAlias("■");
                correcta=true;
            }

        }catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
            correcta =false;
        }



        return correcta;
    }

    /**
     * Si el método anterior es true, este será invocado, posicionará un barco en esa casilla y
     * entrará en un for que se repetirá 3 veces (para poner los 3 puntos restantes) y creará 2
     * numeros aleatorios. El primero elejirá si el siguiente cuadrado se pondrá modificando la
     * fila (arriba o abajo)  o modificando la columna (izq o derecha). Si elije modificar la fila,
     * entrará en un switch en el cual se creará otro num aleatorio para ver si apunta hacia arriba
     * o abajo. Si elije columna igual pero de izq a derecha. Dentro del case, se sumará o restará
     * 1 a la fila o la columna y lo guarda en un aux.(Este será el puntero temporal). Luego invocará
     * al siguiente método para comprobar si es correcta o no la casilla. Si lo es proseguirá, sino,
     * vuelve a borra el puntero provisional (aux) y cambia al puntero orijinal. Luego borra el intento
     * y vuelve a iniciar el anterior proceso hasta que alguno sea true. Si se han puesto 2 tetraminos
     * muy juntos puede generar un error y entrar en un bucle infinito, pero no hemos logrado correjirlo.
     * Cuando sea true, pasará al tetramino correspondiente del jugador la ubicacion del nuevo punto, le
     * fijará el estado como cuadrado, moverá el puntero definitivo a esa posicion (col inicial o fil
     * inicial que será desde donde partirá las siguiente aux)
     * @param tablero : el tablero del jugador (para ahorrar código) modificará el tablero
     * @param jugador : el jugador para poder acceder a sus métodos y atributos
     * @param filaInicial : fila en la que se situa el barco raiz
     * @param colInicial : columna en la que se situa el barco raiz
     */
    public void algoritmoCreacionFiguras( Tablero tablero, Jugador jugador, int filaInicial, int colInicial) {
        int colActual = colInicial;// Selecciona en que columna se encuentra ahora mismo(Ahora será el resultado de
        // la casilla coordenadas, pero luego se cambiará)
        int filActual = filaInicial;
        int XoY, subeObaja;
        int filAux = filActual;
        int colAux = colActual;
        jugador.getTableroJugador().getTablero()[filaInicial][colInicial].setAlias("■");

        for (int z = 0; z < 3; z++) { // se repetirá 4 veces

            // Va a crear un número aleatorio entre 0 y 1 (1 arriba o abajo y 0 para los lados)

            XoY = (int) (Math.random() * 1.9);

            switch (XoY) {

                case 0:// eje x
                    //"Eje x seleccionado");
                    subeObaja = (int) (Math.random() * 1.9);
                    switch (subeObaja) {
                        case 0:
                            colAux = (colActual - 1);
                            if (comprobarCasilla(  filAux, colAux, jugador) == true) {
                                vida+=25;
                                int [] ubicacion= {filActual,colActual};
                                fichasTetramino[z].setUbicacion(ubicacion);
                                //Posicion correcta");
                                colActual = colAux; // Si es correcta, el puntero se pone ahi
                                //y los valores aux son " + filAux + "," + colAux);
                                jugador.getTableroJugador().getTablero()[filActual][colActual].setAlias("■");
                                //y los valores aux pasan a ser " + filAux + "," + colAux);
                                //Cuatrado puesto en posicion: " + filActual + " , " + colActual);
                                break;
                            }
                            if (comprobarCasilla( filAux, colAux, jugador) == false) {
                                //Ups, parece que la posicion no es válida ");
                                colAux = colActual;
                                z--;
                            }
                            break;
                        case 1:
                            //El azar elijió que se mueva a la izquierda");
                            colAux = (colActual + 1);
                            //colAux se cambió a +1");
                            if (comprobarCasilla( filAux, colAux,jugador) == true) {
                                vida+=25;
                                int [] ubicacion= {filActual,colActual};
                                fichasTetramino[z].setUbicacion(ubicacion);
                                //Posicion correcta");
                                //y los valores aux son " + filAux + "," + colAux);
                                colActual = colAux; // Si es correcta, el puntero se pone ahi
                                //y los valores aux pasan a ser " + filAux + "," + colAux);
                                jugador.getTableroJugador().getTablero()[filActual][colActual].setAlias("■");
                                //Cuatrado puesto en posicion: " + filActual + " , " + colActual);
                                break; // Para evitar el bucle infinito y no se meta en la de abajo, porque como ahora
                                // en esa casilla SI hay un barco siempre dará false
                            }
                            if (comprobarCasilla( filAux, colAux,jugador) == false) {
                                //Ups, parece que la posicion no es válida ");
                                colAux = colActual;
                                z--;
                            }
                            break;
                    }
                    break;

                case 1:// eje y
                    //Eje y seleccionado");
                    subeObaja = (int) (Math.random() * 1.9);

                    switch (subeObaja) {

                        case 0:
                            //El resultado del azar es : " + subeObaja);
                            //El azar elijió que suba");
                            //El puntero está en " + filActual + "," + colActual);
                            //"y los valores aux son " + filAux + "," + colAux);
                            filAux = (filActual + 1);

                            //"filaAux se cambió a +1");
                            if (comprobarCasilla( filAux, colAux,jugador) == true) {
                                vida+=25;
                                int [] ubicacion= {filActual,colActual};
                                fichasTetramino[z].setUbicacion(ubicacion);
                                //Posicion correcta");
                                //y los valores aux son " + filAux + "," + colAux);
                                filActual = filAux; // Si es correcta, el puntero se pone ahi
                                //y los valores aux pasan a ser " + filAux + "," + colAux);
                                jugador.getTableroJugador().getTablero()[filActual][colActual].setAlias("■");
                                //Cuatrado puesto en posicion: " + filActual + " , " + colActual);
                                break;
                            }
                            if (comprobarCasilla( filAux, colAux,jugador) == false) {
                                //Ups, parece que la posicion no es válida ");
                                filAux = filActual;
                                z--;
                            }

                            break;
                        case 1:
                            //El azar elijió que baje");
                            filAux = (filActual - 1);
                            vida+=25;
                            int [] ubicacion= {filActual,colActual};
                            fichasTetramino[z].setUbicacion(ubicacion);
                            //filaAux se cambió a -1");
                            //El puntero está en " + filActual + "," + colActual);
                            //y los valores aux son " + filAux + "," + colAux);
                            if (comprobarCasilla( filAux, colAux,jugador) == true) {
                                //Posicion correcta");
                                filActual = filAux; // Si es correcta, el puntero se pone ahi
                                //y los valores aux son " + filAux + "," + colAux);
                                jugador.getTableroJugador().getTablero()[filActual][colActual].setAlias("■");
                                //y los valores aux pasan a ser " + filAux + "," + colAux);
                                //Cuatrado puesto en posicion: " + filActual + " , " + colActual);
                                break;
                            }
                            if (comprobarCasilla(  filAux, colAux,jugador) == false) {
                                //Ups, parece que la posicion no es válida ");
                                filAux = filActual;
                                z--;
                            }
                            break;

                    }

            }


        }

    }

    /**
     * Devolverá true si en esa posición no hay un barco o da excepcion
     * @param filAux fila aux del método algoritmoGenerarFigura
     * @param colAux col aux del método algoritmoGenerarFigura
     * @param jugador pasará el jugador para acceder a sus métodos
     * @return true si la coordenada es valida, false sino
     */
    public boolean comprobarCasilla(int filAux, int colAux, Jugador jugador) {

        boolean returnValor = false;



        try{ //para evitar la excepcion
                if (jugador.getTableroJugador().getTablero()[filAux][colAux].getAlias().equals("~")) { // Si no hay agua tampoco puede

                    returnValor = true;
                }
                if (jugador.getTableroJugador().getTablero()[filAux][colAux].getAlias().equals("■")) {

                    returnValor = false;
                }



        }catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){

            returnValor=false;

        }
        return returnValor;

    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public Casilla[] getFichasTetramino() {
        return fichasTetramino;
    }

    public void setFichasTetramino(Casilla[] fichasTetramino) {
        this.fichasTetramino = fichasTetramino;
    }

    public int getId_figura() {
        return id_figura;
    }

    public void setId_figura(int id_figura) {
        this.id_figura = id_figura;
    }

    public String getColorBarco() {
        return colorBarco;
    }

    public void setColorBarco(String colorBarco) {
        this.colorBarco = colorBarco;
    }
}
