package s2.gestion.calculators;

import java.util.UUID;

import org.openxava.calculators.ICalculator;

public class UUIDCalculator implements ICalculator {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public Object calculate() throws Exception {
	return UUID.randomUUID();
    }

}
