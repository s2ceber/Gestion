package s2.gestion.model.ventas;

import java.math.BigDecimal;

import org.openxava.annotations.Hidden;
import org.openxava.annotations.ReadOnly;
import org.openxava.annotations.Stereotype;

import lombok.Getter;
import lombok.Setter;

public @Getter @Setter class Traspaso {
    @Hidden
    private Long id;
    @ReadOnly
    private String codigo;
    @ReadOnly
    private BigDecimal importe;
    @ReadOnly
    private BigDecimal importeIva;
    @ReadOnly
    private String nombre;
    @ReadOnly
    private BigDecimal unidades;
    @ReadOnly
    private BigDecimal unidatesTraspasadas;
    private BigDecimal unidatesATraspasar;
    @Stereotype("MONEY") @ReadOnly
    private BigDecimal precio;
    
    
}
