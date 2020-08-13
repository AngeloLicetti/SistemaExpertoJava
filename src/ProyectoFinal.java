import com.github.cschen1205.ess.engine.*;
import com.github.cschen1205.ess.enums.*;
import com.github.cschen1205.ess.js.*;
import static com.github.cschen1205.ess.engine.Console.showInputDialog;
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Vector;

public class ProyectoFinal {
    
    static JSRuleInferenceEngine jsRie = cargarArchivoDeReglas();
    static RuleInferenceEngine rie = jsRie.getRie();
    static SimulacionDePrestamo simulacion;
        
    public static void main(String[] args) {
        rie.clearFacts();
        String opcion = "";
        while(!opcion.equals("0")){
            System.out.println("Elija una opcion");
            System.out.println("_________________");
            System.out.println("1 - Aperturar cuenta de ahorros");
            System.out.println("2 - Aperturar cuenta corriente");
            System.out.println("3 - Simulacion de prestamo");
            System.out.println("0 - Salir");
            System.out.println("_________________");
            opcion = showInputDialog("Tu opción:");
            switch(opcion){
                case "1":
                    aperturar_cuenta_ahorros();
                    break;
                case "2":
                    aperturar_cuenta_corriente();
                    break;
                case "3":
                    simulacion();
                    break;
            }
            System.out.println("_________________");
        }
    }
    
    public static void aperturar_cuenta_ahorros(){
        inferir("puede_aperturar_cuenta_de_ahorros");
    }
    
    public static void aperturar_cuenta_corriente(){
        inferir("puede_aperturar_cuenta_corriente");
    }
    
    public static void simulacion(){
        if(inferir("apto_para_simulacion")){
            simulacion = new SimulacionDePrestamo(rie);
            inferir("pre_aprobado");
        }
    }
    
    public static boolean inferir(String goal){
        System.out.println("Vamos a inferir si: " + goal);
        System.out.println("-----------------------------------------------------:");
        boolean ret = BackwardChainWithNullMemory(goal);
        System.out.println("-----------------------------------------------------:");
        return ret;
    }
    
    public static boolean BackwardChainWithNullMemory(String goal)
    {
        boolean ret;
        Vector<Clause> unproved_conditions= new Vector<>();

        Clause conclusion=null;
        while(conclusion==null)
        {
            conclusion=rie.infer(goal, unproved_conditions);
            if(conclusion==null)
            {
                if(unproved_conditions.size()==0)
                {
                    break;
                }
                
                Clause c = unproved_conditions.get(0);
                
                Vector<Clause> unproved_conditions2= new Vector<>();
                String primerAntescedente = c.getVariable();
                rie.infer(primerAntescedente, unproved_conditions2);
                if(unproved_conditions2.size()>0)
                {
                    BackwardChainWithNullMemory(primerAntescedente);
                }
                else{
                    unproved_conditions.clear();
                    String value = showInputDialog("Por favor responde esto: "+c.getVariable()+"?");
                    rie.addFact(new EqualsClause(c.getVariable(), value));
                    System.out.println("-----------------------------------------------------:");
                }
            }
        }
        String sConclusion;
        if(conclusion==null){
            sConclusion = goal + " = n";
            ret = false;
        }
        else{
            sConclusion = conclusion.toString();
            ret = true;
        }
        System.out.println("CONCLUSIÓN: " + sConclusion);
        System.out.println("Memoria de trabajo: ");
        System.out.println(rie.getFacts());
        
        return ret;
    }
    
    private static JSRuleInferenceEngine cargarArchivoDeReglas(){
        JSRuleInferenceEngine engine = new JSRuleInferenceEngine();
        try{
            String currentDirectory = System.getProperty("user.dir");
            String fileName = currentDirectory + "\\src\\archivoDeReglas.js";
            File tempFile = new File(fileName);
            boolean exists = tempFile.exists();
            String jsContent = new String ( Files.readAllBytes( Paths.get(fileName) ) );
            engine.loadString(jsContent);
            engine.buildRules();
        }
        catch (Exception e){
            printStackTrace();
        }
        return engine;
    }
}
