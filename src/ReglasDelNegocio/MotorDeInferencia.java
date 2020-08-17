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
        
        
        
        temp = new Regla();
        temp.agregarAntescedente("edad", ">", "17");
        temp.agregarAntescedente("edad", "<", "70");
        temp.agregarConsecuente("edad_adecuada", "=", "s");
        reglas.add(temp);
        
        temp = new Regla();
        temp.agregarAntescedente("prestamos_anteriores", "=", "n");
        temp.agregarConsecuente("deudas_vigentes", "=", "n");
        reglas.add(temp);
        
        
        temp = new Regla();
        temp.agregarAntescedente("prestamos_anteriores", "=", "s");
        temp.agregarAntescedente("prestamos_pagados", "=", "s");
        temp.agregarConsecuente("deudas_vigentes", "=", "n");
        reglas.add(temp);
        
         temp = new Regla();
        temp.agregarAntescedente("me_odias", "=", "n");
        temp.agregarConsecuente("eres_empatico", "=", "s");
        reglas.add(temp);
        
          temp = new Regla();
        temp.agregarAntescedente("sabes_como_me_siento", "=", "s");
        temp.agregarConsecuente("eres_empatico", "=", "s");
        reglas.add(temp);
        
        temp = new Regla();
        temp.agregarAntescedente("aceptas_un_reto", "=", "s");
        temp.agregarAntescedente("rindete", "=", "n");
        temp.agregarAntescedente("vamos_rindete", "=", "n");
        temp.agregarAntescedente("por_ultima_vez_rindete", "=", "n");
        temp.agregarConsecuente("te_rindes_facilmente", "=", "n");
        reglas.add(temp);
        
         temp = new Regla();
        temp.agregarAntescedente("te_rindes_facilmente", "=", "n");
        temp.agregarAntescedente("eres_empatico", "=", "s");
        temp.agregarConsecuente("emocionalmente_estable", "=", "s");
        reglas.add(temp);
        
        temp = new Regla();
        temp.agregarAntescedente("tienes_amigos", "=", "s");
        temp.agregarAntescedente("te_quieres", "=", "s");
        temp.agregarConsecuente("emocionalmente_estable", "=", "s");
        reglas.add(temp);
        
         temp = new Regla();
        temp.agregarAntescedente("tienes_negocio_propio", "=", "s");
        temp.agregarConsecuente("emprendedor", "=", "s");
        reglas.add(temp);
        
        temp = new Regla();
        temp.agregarAntescedente("tienes_negocio_propio", "=", "s");
        temp.agregarAntescedente("tu_negocio_da_plata", "=", "s");
        temp.agregarAntescedente("tu_negocio_tiene_futuro", "=", "s");
        temp.agregarAntescedente("#_meses_antiguedad_negocio", ">" ,"24");
        temp.agregarConsecuente("negocio_rentable", "=", "s");
        reglas.add(temp);
        
        temp = new Regla();
        temp.agregarAntescedente("emprendedor", "=", "s");
        temp.agregarAntescedente("ganas_mucho_invirtiendo", "=", "s");
        temp.agregarAntescedente("emocionalmente_estable", "=", "s");
        temp.agregarAntescedente("negocio_rentable", "=" ,"s");
        temp.agregarConsecuente("ingresos_estables", "=", "s");
        reglas.add(temp);
        
        temp = new Regla();
        temp.agregarAntescedente("trabajas_en_una_empresa", "=", "s");
        temp.agregarAntescedente("#_meses_antiguedad_trabajo", ">", "17");
        temp.agregarAntescedente("tienes_carta_de_recomendacion", "=", "s");
        temp.agregarConsecuente("ingresos_estables", "=", "s");
        reglas.add(temp);
        
        temp = new Regla();
        temp.agregarAntescedente("trabajas_en_una_empresa", "=", "s");
        temp.agregarAntescedente("#_meses_antiguedad_trabajo", ">", "17");
        temp.agregarAntescedente("te_gusta_tu_trabajo", "=", "s");
        temp.agregarConsecuente("ingresos_estables", "=", "s");
        reglas.add(temp);
        
         temp = new Regla();
        temp.agregarAntescedente("trabajas_en_una_empresa", "=", "s");
        temp.agregarAntescedente("#_meses_antiguedad_trabajo", ">", "17");
        temp.agregarAntescedente("tu_jefe_hablaria_bien_de_ti", "=", "s");
        temp.agregarConsecuente("ingresos_estables", "=", "s");
        reglas.add(temp);
        
         temp = new Regla();
        temp.agregarAntescedente("eres_inversor", "=", "s");
        temp.agregarAntescedente("ganas_mucho_invirtiendo", "=", "s");
        temp.agregarAntescedente("tienes_muchos_activos", "=", "s");
        temp.agregarAntescedente("#_meses_antiguedad_invirtiendo", ">", "24");
        temp.agregarConsecuente("ingresos_estables", "=", "s");
        reglas.add(temp);
        
         temp = new Regla();
        temp.agregarAntescedente("edad_adecuada", "=", "s");
        temp.agregarAntescedente("deudas_vigentes", "=", "n");
        temp.agregarAntescedente("ingresos_estables", "=", "s");
        temp.agregarConsecuente("apto_para_simulacion", "=", "s");
        reglas.add(temp);

        temp = new Regla();
        temp.agregarAntescedente("puede_pagar", "=", "s");
        temp.agregarConsecuente("cuotas_pagables", "=", "s");
        
         temp = new Regla();
        temp.agregarAntescedente("ves_fantasmas", "=", "n");
        temp.agregarAntescedente("escuchas_voces", "=", "n");
        temp.agregarConsecuente("esta_loco", "=", "n");
        reglas.add(temp);
      
           temp = new Regla();
        temp.agregarAntescedente("1_+_1", "=", "2");
        temp.agregarAntescedente("esta_loco", "=", "n");
        temp.agregarConsecuente("es_inteligente", "=", "s");
        reglas.add(temp);
        
        temp = new Regla();
        temp.agregarAntescedente("apto_para_simulacion", "=", "s");
        temp.agregarAntescedente("cuotas_pagables", "=", "s");
        temp.agregarAntescedente("es_inteligente", "=", "s");
        temp.agregarConsecuente("apto_para_evaluacion", "=", "s");
        reglas.add(temp);
        
         temp = new Regla();
        temp.agregarAntescedente("prestamos_anteriores", "=", "n");
        temp.agregarAntescedente("alguna_vez_te_has_atrasado", "=", "n");
        temp.agregarConsecuente("moroso", "=", "n");
        reglas.add(temp);
        
         temp = new Regla();
        temp.agregarAntescedente("prestamos_anteriores", "=", "s");
        temp.agregarAntescedente("alguna_vez_te_has_atrasado", "=", "s");
        temp.agregarConsecuente("moroso", "=", "s");
        reglas.add(temp);
        
        temp = new Regla();
        temp.agregarAntescedente("prestamos_anteriores", "=", "n");
        temp.agregarConsecuente("moroso", "=", "n");
        reglas.add(temp);
        
        temp = new Regla();
        temp.agregarAntescedente("moroso", "=", "n");
        temp.agregarConsecuente("deudas_aceptables", "=", "s");
        reglas.add(temp);
        
        temp = new Regla();
        temp.agregarAntescedente("moroso", "=", "s");
        temp.agregarAntescedente("#_retrasos", "<", "5");
         temp.agregarAntescedente("prometes_ser_buen_pagador", "=", "s");
        temp.agregarConsecuente("deudas_aceptables", "=", "s");
        reglas.add(temp);
        
        temp = new Regla();
        temp.agregarAntescedente("deudas_aceptables", "=", "s");
        temp.agregarAntescedente("has_sido_aval", "=", "n");
        temp.agregarConsecuente("buen_historial_crediticio", "=", "s");
        reglas.add(temp);
        
           temp = new Regla();
        temp.agregarAntescedente("deudas_aceptables", "=", "s");
        temp.agregarAntescedente("has_sido_aval", "=", "s");
        temp.agregarAntescedente("el_avalado_es_deudor", "=", "n");
        temp.agregarConsecuente("buen_historial_crediticio", "=", "s");
        reglas.add(temp);
        
           temp = new Regla();
        temp.agregarAntescedente("deudas_aceptables", "=", "s");
        temp.agregarAntescedente("has_sido_aval", "=", "s");
        temp.agregarAntescedente("el_avalado_es_deudor", "=", "s");
        temp.agregarAntescedente("el_avalado_sigue_endeudado", "=", "s");
        temp.agregarConsecuente("buen_historial_crediticio", "=", "s");
        reglas.add(temp);
        
        temp = new Regla();
        temp.agregarAntescedente("vas_al_casino", "=", "n");
        temp.agregarAntescedente("eres_comprador_compulsivo", "=", "n");
        temp.agregarConsecuente("tienes_vicios", "=", "n");
        reglas.add(temp);
 
         temp = new Regla();
        temp.agregarAntescedente("edad", "<", "35");
        temp.agregarAntescedente("tienes_alguna_enfermedad_terminal", "=", "n");
        temp.agregarConsecuente("esperanza_de_vida_larga", "=", "s");
        reglas.add(temp);
        
         temp = new Regla();
        temp.agregarAntescedente("edad", "<", "60");
        temp.agregarAntescedente("tienes_alguna_enfermedad_terminal", "=", "n");
        temp.agregarAntescedente("te_duele_el_cuerpo_frecuentemente", "=", "n");
        temp.agregarConsecuente("esperanza_de_vida_larga", "=", "s");
        reglas.add(temp);
        
         temp = new Regla();
        temp.agregarAntescedente("edad", "<", "60");
        temp.agregarAntescedente("tienes_alguna_enfermedad_terminal", "=", "n");
        temp.agregarAntescedente("te_duele_el_cuerpo_frecuentemente", "=", "s");
        temp.agregarAntescedente("te_has_hecho_un_chequeo", "=", "s");
        temp.agregarAntescedente("estas_completamente_sano", "=", "s");
        temp.agregarConsecuente("esperanza_de_vida_larga", "=", "s");
        reglas.add(temp);
        
           temp = new Regla();
        temp.agregarAntescedente("edad", "<", "70");
        temp.agregarAntescedente("tienes_alguna_enfermedad_terminal", "=", "n");
        temp.agregarAntescedente("te_duele_el_cuerpo_frecuentemente", "=", "s");
        temp.agregarAntescedente("te_has_hecho_un_chequeo", "=", "s");
        temp.agregarAntescedente("estas_completamente_sano", "=", "s");
        temp.agregarAntescedente("te_chequeas_regularmente", "=", "s");
        temp.agregarConsecuente("esperanza_de_vida_larga", "=", "s");
        reglas.add(temp);
        
         temp = new Regla();
        temp.agregarAntescedente("tienes_propiedades_inmobiliarias", "=", "s");
        temp.agregarAntescedente("#_de_tus_propiedades", ">", "2");
        temp.agregarAntescedente("tienes_vicios", "=", "n");
        temp.agregarAntescedente("esperanza_de_vida_larga", "=", "s");
        temp.agregarConsecuente("posible_buen_pagador", "=", "s");
        reglas.add(temp);
        
         temp = new Regla();
        temp.agregarAntescedente("tienes_ahorros", "=", "s");
        temp.agregarAntescedente("tus_ahorros_son_mas_de_30000", ">", "30000");
        temp.agregarAntescedente("tienes_vicios", "=", "n");
        temp.agregarAntescedente("esperanza_de_vida_larga", "=", "s");
        temp.agregarConsecuente("posible_buen_pagador", "=", "s");
        reglas.add(temp);
        
         temp = new Regla();
        temp.agregarAntescedente("dni", "<", "100000000");
        temp.agregarConsecuente("documentos_correctos", "=", "s");
        reglas.add(temp);
        
         temp = new Regla();
        temp.agregarAntescedente("buen_historial_crediticio", "=", "s");
        temp.agregarAntescedente("posible_buen_pagador", "=", "s");
        temp.agregarConsecuente("puede_aperturar_cuenta_corriente", "=", "s");
        reglas.add(temp);
        
         temp = new Regla();
        temp.agregarAntescedente("documentos_correctos", "=", "s");
        temp.agregarAntescedente("edad_adecuada", "=", "s");
        temp.agregarConsecuente("puede_aperturar_cuenta_de_ahorros", "=", "s");
        reglas.add(temp);
        
        temp = new Regla();
        temp.agregarAntescedente("apto_para_evaluacion", "=", "s");
        temp.agregarAntescedente("documentos_correctos", "=", "s");
        temp.agregarAntescedente("buen_historial_crediticio", "=", "s");
        temp.agregarAntescedente("posible_buen_pagador", "=", "s");
        temp.agregarConsecuente("pre_aprobado", "=", "s");
        reglas.add(temp);
        
        temp = new Regla();
        temp.agregarAntescedente("MayorDeEdad", "=", "s");
        temp.agregarAntescedente("MesesAntiguedad", ">", "6");
        temp.agregarConsecuente("TipoDeCliente", "=", "3");
        reglas.add(temp);
        
        temp = new Regla();
        temp.agregarAntescedente("MayorDeEdad", "=", "s");
        temp.agregarAntescedente("MesesAntiguedad", "=", "6");
        temp.agregarConsecuente("TipoDeCliente", "=", "2");
        reglas.add(temp);
        
        temp = new Regla();
        temp.agregarAntescedente("MayorDeEdad", "=", "s");
        temp.agregarAntescedente("MesesAntiguedad", "<", "6");
        temp.agregarConsecuente("TipoDeCliente", "=", "1");
        reglas.add(temp);
        
        temp = new Regla();
        temp.agregarAntescedente("Edad", ">", "18");
        temp.agregarConsecuente("MayorDeEdad", "=", "s");
        reglas.add(temp);
    }
}
