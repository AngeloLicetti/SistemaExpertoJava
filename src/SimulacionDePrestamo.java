import com.github.cschen1205.ess.engine.EqualsClause;
import com.github.cschen1205.ess.engine.RuleInferenceEngine;
import static com.github.cschen1205.ess.engine.Console.showInputDialog;

public class SimulacionDePrestamo {
    static RuleInferenceEngine rie;
    public SimulacionDePrestamo(RuleInferenceEngine rie){
        this.rie = rie;
        String monto_solicitado = showInputDialog("Monto solicitado?");
        rie.addFact(new EqualsClause("monto_solicitado", monto_solicitado));
        String numero_cuotas = showInputDialog("numero_cuotas?");
        rie.addFact(new EqualsClause("numero_cuotas", numero_cuotas));
        
        double cuota_calculada = 1.14*Double.parseDouble(monto_solicitado)/Integer.parseInt(numero_cuotas);
        String puede_pagar = showInputDialog("Puede pagar " + cuota_calculada + " mensualemnte?");
        rie.addFact(new EqualsClause("puede_pagar", puede_pagar));
    }
}
