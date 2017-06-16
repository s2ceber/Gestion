package s2.gestion.model.compras;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * @author Alberto
 * Modelo para los detalles de los presupuestos de compra
 *
 */
@Entity
@Table(name = "presupuesto_compra_detalle")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class PresupuestoCompraDetalle extends DocumentoCompraDetalleBase{


}
