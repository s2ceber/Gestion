package s2.gestion.model.compras;

import java.util.*;

import javax.persistence.*;

import lombok.*;
import s2.gestion.model.base.*;
/**
 * @author Alberto
 * Modelo para la cabecera de los documentos de compra
 *
 */
@MappedSuperclass
//@Table(name = "documento_compra")

public @Getter @Setter class DocumentoCompraBase extends Documentable{
	private Integer numero;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(name="fk_proveedor"))
	private Proveedor proveedor;
	private Date fecha;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(name="fk_forma_pago"))
	private FormaPagoCompra formaPago;

}
