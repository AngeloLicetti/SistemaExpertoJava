package ReglasDelNegocio;

import java.util.ArrayList;
import java.util.List;

class MemoriaDeTrabajo {
    private List<Clausula> hechos =new ArrayList<Clausula>();
    
    public void agregarHecho(Clausula hecho){
        hechos.add(hecho);
    }
    
    public void agregarHecho(String nombre, String operador, String valor){
        hechos.add(new Clausula(nombre, operador, valor));
    }
    
    public List<Clausula> getHechos(){
        return hechos;
    }
    
    public String getValor(String hecho){
        String valor = null;
        for(Clausula c : hechos){
            if(c.getNombre().equals(hecho)){
                valor = c.getValor();
                break;
            }
        }
        return valor;
    }
}
