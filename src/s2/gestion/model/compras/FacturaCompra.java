package s2.gestion.model.compras;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * @author Alberto
 * Modelo para la cabecera de las facturas de compra
 *
 */
@Entity
@Table(name = "factura_compra")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class FacturaCompra extends DocumentoCompraBase{

	
	

}
