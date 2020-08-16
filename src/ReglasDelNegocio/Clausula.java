package ReglasDelNegocio;
public class Clausula {

    private String nombre;
    private String operador;
    private String valor;
    
    public Clausula(String nombre, String operador, String valor){
        this.nombre = nombre;
        this.operador = operador;
        this.valor = valor;
    }
    
    
    public String getValor() {
        return valor;
    }
    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getOperador(){
        return operador;
    }
    
    @Override
    public boolean equals(Object clausula){
        if (clausula == this) { 
            return true; 
        } 
        if (!(clausula instanceof Clausula)) { 
            return false; 
        }
        
        Clausula c = (Clausula) clausula;
        
        boolean ret = false;
        if(this.nombre.equals(c.nombre) && this.operador.equals(c.operador) && this.valor.equals(c.valor)){
            ret = true;
        }
        return ret;
    }
}
