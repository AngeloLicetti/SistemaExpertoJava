package ReglasDelNegocio;
class Hecho {
    private String nombre;
    private String operador;
    private String valor;
    private int iValor;
    
    public Hecho(String nombre, String operador, String valor){
        this.nombre = nombre;
        this.operador = operador;
        this.valor = valor;
    }
    
    public Hecho(String nombre, String operador, int iValor){
        this.nombre = nombre;
        this.operador = operador;
        this.iValor = iValor;
    }
}
