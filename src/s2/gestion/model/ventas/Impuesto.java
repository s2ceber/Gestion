package s2.gestion.model.ventas;

import java.math.BigDecimal;

import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

public @Getter @Setter class Impuesto{
	@Transient
	private BigDecimal tipo;
	@Transient
	private BigDecimal importe;
}
