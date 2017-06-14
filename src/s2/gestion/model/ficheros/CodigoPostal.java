package s2.gestion.model.ficheros;

import javax.persistence.*;

import s2.gestion.model.base.Documentable;

/**
 * @author Alberto
 * Modelo para los codigos postales 
 *
 */
@Entity
@Table(name = "codigo_postal")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class CodigoPostal extends Documentable{

}
