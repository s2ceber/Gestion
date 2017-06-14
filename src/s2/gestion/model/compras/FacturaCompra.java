package s2.gestion.model.compras;

import java.util.*;

import javax.persistence.*;

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
