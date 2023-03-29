public class Casilla {
    private String alias; //Nombre estado casilla
    private int [] ubicacion; //ubicacion en el tablero
    private String [] estados; //Estados en los que puede estar la casilla

    /**
     *  EL array estado será siempre el mismo por lo que no se pone como parámetro
     * @param alias El estado de la casilla (se usa para imprimir datos y comprobar donde se dispara...)
     * @param ubicacion En que columna y fila del tablero
     *     LEYENDA de las casillas = ? = tablero encriptado , o = agua , x = tetramino golpeado
     */
    public Casilla(String alias, int[] ubicacion) {
        this.alias = alias;
        this.ubicacion = ubicacion;
        String [] estadosAux={"~","■","o","x"};
        this.estados = estadosAux;
    }

    public Casilla() {
        this("~",new int[2]);
    }

    /**
     * Este método  cuando se invoque se pasará un int llamado selector que será la posición donde está el
     * estado que se desea asignar. 1-agua, 2-barco...
     * @param selector
     * @return true si el valor de select es válido y false si no
     */
    public boolean asignarValor(int selector){

        boolean funcion;

        if ( selector> 0 && selector< estados.length){

            try{
                setAlias(estados[selector]);
                funcion=true;
            }catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                funcion=false;}
        }
        else {
            funcion=false;
        }
        return funcion;
    }


    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int[] getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(int[] ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String[] getEstados() {
        return estados;
    }

    public void setEstados(String[] estados) {
        this.estados = estados;
    }
}
