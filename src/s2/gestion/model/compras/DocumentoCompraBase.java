package s2.gestion.model.compras;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.DescriptionsList;

import lombok.*;
import s2.gestion.model.base.*;
/**
 * @author Alberto
 * Modelo para la cabecera de los documentos de compra
 *
 */
@MappedSuperclass
//@Table(name = "documento_compra")

public abstract @Getter @Setter class DocumentoCompraBase extends Documentable{
	private Integer numero;
	private Date fecha;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(name="fk_proveedor"))
	@DescriptionsList(descriptionProperties="codigo, nombre")
	private Proveedor proveedor;

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(name="fk_forma_pago"))
	@DescriptionsList(descriptionProperties="codigo, nombre")
	private FormaPagoCompra formaPago;

}
