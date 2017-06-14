package s2.gestion.model.ventas;

import java.math.*;

import javax.persistence.*;

import s2.gestion.model.ficheros.*;

/**
 * @author Alberto
 * Modelo para los detalles de los presupuestos de venta
 *
 */
@Entity
@Table(name = "presupuesto_venta_detalle")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class PresupuestoVentaDetalle extends DocumentoVentaDetalleBase{


}
