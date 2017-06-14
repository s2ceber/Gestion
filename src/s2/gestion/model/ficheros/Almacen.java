package s2.gestion.model.ficheros;

import javax.persistence.*;

import s2.gestion.model.base.Documentable;

/**
 * @author Alberto
 * Modelo para los almacenes de la empresa
 *
 */
@Entity
@Table(name = "almacen")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class Almacen extends Documentable{

}
