package s2.gestion.model.compras;

import java.util.*;

import javax.persistence.*;

/**
 * @author Alberto
 * Modelo para la cabecera de los presupuestos de compra
 *
 */
@Entity
@Table(name = "presupuesto_compra")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class PresupuestoCompra extends DocumentoCompraBase{


}
