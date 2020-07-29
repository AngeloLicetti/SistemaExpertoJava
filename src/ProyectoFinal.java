import com.github.cschen1205.ess.engine.*;
import com.github.cschen1205.ess.enums.*;
import com.github.cschen1205.ess.js.*;
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
        
        
    public static void main(String[] args) {
        rie.clearFacts();
        if(inferir("apto_para_simulacion")){
            iniciarSimulacion();
        }
    }
    
    public static void iniciarSimulacion(){
        String monto_solicitado = showInputDialog("Monto solicitado?");
        rie.addFact(new EqualsClause("monto_solicitado", monto_solicitado));
        String numero_cuotas = showInputDialog("numero_cuotas?");
        rie.addFact(new EqualsClause("numero_cuotas", numero_cuotas));
        
        double cuota_calculada = 1.14*Double.parseDouble(monto_solicitado)/Integer.parseInt(numero_cuotas);
        String puede_pagar = showInputDialog("Puede pagar " + cuota_calculada + " mensualemnte?");
        rie.addFact(new EqualsClause("puede_pagar", puede_pagar));
        
        if(inferir("apto_para_evaluacion")){
            iniciarEvaluacion();
        }
    }
    
    public static void iniciarEvaluacion(){
        inferir("pre_aprobado");
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

    private static String showInputDialog(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(question + " ");
        return scanner.next();
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
