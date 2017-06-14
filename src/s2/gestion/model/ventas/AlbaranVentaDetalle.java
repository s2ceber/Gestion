package s2.gestion.model.ventas;

import java.math.*;

import javax.persistence.*;

import s2.gestion.model.ficheros.*;

/**
 * @author Alberto
 * Modelo para el detalle de los albaranes de venta
 *
 */
@Entity
@Table(name = "albaran_venta_detalle")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class AlbaranVentaDetalle extends DocumentoVentaDetalleBase{


}
