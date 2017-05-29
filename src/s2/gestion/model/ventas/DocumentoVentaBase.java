package s2.gestion.model.ventas;

import java.util.*;
/**
 * @author Alberto
 * Modelo para la cabecera de los documentos de venta
 *
 */

import javax.persistence.*;

import lombok.*;
import s2.gestion.model.base.*;
import s2.gestion.model.compras.*;
@Entity
@Table(name = "documento_venta")
public @Getter @Setter class DocumentoVentaBase extends Documentable {
	private Integer numero;
	private String serieDocumento;
	private Cliente cliente;
	private Date fecha;
	private FormaPagoCompra formaPago;

}
