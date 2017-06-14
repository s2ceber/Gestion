package s2.gestion.model.compras;

import java.math.*;

import javax.persistence.*;

import s2.gestion.model.ficheros.*;

/**
 * @author Alberto
 * Modelo para los detalles de las facturas de compra
 *
 */
@Entity
@Table(name = "factura_compra_detalle")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class FacturaCompraDetalle extends DocumentoCompraDetalleBase{


}
