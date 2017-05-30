package s2.gestion.model.compras;

import javax.persistence.*;

import s2.gestion.model.base.Documentable;

/**
 * @author Alberto
 * Modelo para las formas de pago de las facturas de los proveedores
 *
 */
@Entity
@Table(name = "forma_pago_compra")
public class FormaPagoCompra extends Documentable{

}
