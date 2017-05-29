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
@Entity
@Table(name = "documento_compra")

public @Getter @Setter class DocumentoCompraBase extends Documentable{
	private Integer numero;
	private Proveedor proveedor;
	private Date fecha;
	private FormaPagoCompra formaPago;

}
