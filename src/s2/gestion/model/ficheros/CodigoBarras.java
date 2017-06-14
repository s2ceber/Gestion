package s2.gestion.model.ficheros;

import javax.persistence.*;

import s2.gestion.model.base.Documentable;

/**
 * @author Alberto
 * Modelo para los codigos de barras de los articulos
 *
 */
@Entity
@Table(name = "codigo_barras")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class CodigoBarras extends Documentable{

}
