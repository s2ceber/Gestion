package s2.gestion.model.ventas;

import java.util.*;

import javax.persistence.*;

import s2.gestion.model.compras.*;

/**
 * @author Alberto
 * Modelo para la cabecera de los albaranes de venta
 *
 */
@Entity
@Table(name = "albaran_venta")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class AlbaranVenta extends DocumentoVentaBase{


}
