package s2.gestion.model.ventas;

import javax.persistence.*;

import s2.gestion.model.base.Documentable;

/**
 * @author Alberto
 * Modelo para las tarifas de venta
 *
 */
@Entity
@Table(name = "tarifa_venta")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class TarifaVenta extends Documentable{

}
