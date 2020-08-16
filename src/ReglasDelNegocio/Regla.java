package ReglasDelNegocio;

import java.util.ArrayList;
import java.util.List;

class Regla {
    private List<Clausula> antescedentes = new ArrayList<Clausula>();
    private Clausula consecuente;
    
    public Regla(){
        
    }
    
    public void agregarAntescedente(String nombre, String operador, String valor){
        antescedentes.add(new Clausula(nombre, operador, valor));
    }
    
    public void agregarConsecuente(String nombre, String operador, String valor){
        consecuente = new Clausula(nombre, operador, valor);
    }
    
    public Clausula getConsecuente(){
        return consecuente;
    }
    
    public List<Clausula> getAntescedentes(){
        return antescedentes;
    }
}
