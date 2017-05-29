package s2.gestion.model.compras;

import java.math.*;

import javax.persistence.*;

import lombok.*;
import s2.gestion.model.base.*;
import s2.gestion.model.ficheros.*;
/**
 * @author Alberto
 * Modelo para los detalles de los documentos de compra
 *
 */
@Entity
@Table(name = "documento_compra_detalle")

public @Getter @Setter class DocumentoCompraDetalleBase extends Documentable {
	private Articulo articulo;
	private BigDecimal unidades;
	private BigDecimal precio;
	private BigDecimal importe;
	private BigDecimal dto1;
	private BigDecimal dto2;
	private BigDecimal dto3;
	private BigDecimal dto4;

}
