package steps;

import java.util.HashMap;
import java.util.List;

public class BaseStepDef {
    public HashMap<String, List<String>> vars;

    public BaseStepDef(HashMap<String, List<String>> var) {
        this.vars = var;
    }
}