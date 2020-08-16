package ReglasDelNegocio;

import java.util.ArrayList;
import java.util.List;

public class MotorDeInferencia {
    private List<Regla> reglas = new ArrayList<Regla>();
    private MemoriaDeTrabajo memoria = new MemoriaDeTrabajo();
    
    public MotorDeInferencia(){
        cargarReglas();
    }
    
    public void inferir(Clausula goal){
        if(esHecho(goal)){
            return;
        }
        
        if(goal.getOperador().equals("<") || goal.getOperador().equals(">")){
            String sValor = getValor(goal.getNombre());
            if(sValor==null){
                return;
            }
            int valor = Integer.parseInt(sValor);
            if(goal.getOperador().equals("<") && valor < Integer.parseInt(goal.getValor())){
                memoria.agregarHecho(goal);
            }
            if(goal.getOperador().equals(">") && valor > Integer.parseInt(goal.getValor())){
                memoria.agregarHecho(goal);
            }
            return;
        }
        
        List<Regla> conjuntoDeReglasParaComprobar = new ArrayList<Regla>();
        
        for(Regla r : reglas){
            if(r.getConsecuente().equals(goal)){
                conjuntoDeReglasParaComprobar.add(r);
            }
        }
        
        Boolean reglaComprobada = false;
        
        for(Regla r : conjuntoDeReglasParaComprobar){
            List<Clausula> antescedentes = r.getAntescedentes();
            
            for(int i = 0; i<antescedentes.size(); i++){
                Clausula antescedente = antescedentes.get(i);
                inferir(antescedente);
                if(!esHecho(antescedente)){
                    break;
                }
                if(i==antescedentes.size()-1){
                    memoria.agregarHecho(r.getConsecuente());
                    reglaComprobada = true;
                }
            }
            
            if(reglaComprobada){
                break;
            }
        }
    }
    
    public Boolean componeRegla(Clausula goal){
        for(Regla r : reglas){
            if(r.getConsecuente().equals(goal)){
                return true;
            }
        }
        return false;
    }
    
    public String getValor(String hecho){
        return memoria.getValor(hecho);
    }
    
    private Boolean esHecho(String goal){
        String valor = memoria.getValor(goal);
        return valor==null?false:true;
    }
    
    private Boolean esHecho(Clausula goal){
        for(Clausula c : memoria.getHechos()){
            if(c.equals(goal)){
                return true;
            }
        }
        return false;
    }
    
    public void agregarHecho(String nombre, String operador, String valor){
        memoria.agregarHecho(nombre, operador, valor);
    }
    
    public void cargarReglas(){
        Regla temp;
        
        temp = new Regla();
        temp.agregarAntescedente("Edad", ">", "18");
        temp.agregarConsecuente("MayorDeEdad", "=", "s");
        reglas.add(temp);
        
        temp = new Regla();
        temp.agregarAntescedente("MayorDeEdad", "=", "s");
        temp.agregarAntescedente("MesesAntiguedad", "<", "6");
        temp.agregarConsecuente("TipoDeCliente", "=", "1");
        reglas.add(temp);
        
        temp = new Regla();
        temp.agregarAntescedente("MayorDeEdad", "=", "s");
        temp.agregarAntescedente("MesesAntiguedad", "=", "6");
        temp.agregarConsecuente("TipoDeCliente", "=", "2");
        reglas.add(temp);
        
        temp = new Regla();
        temp.agregarAntescedente("MayorDeEdad", "=", "s");
        temp.agregarAntescedente("MesesAntiguedad", ">", "6");
        temp.agregarConsecuente("TipoDeCliente", "=", "3");
        reglas.add(temp);
    }
}
