package s2.gestion.model.ficheros;

import javax.persistence.*;

import s2.gestion.model.base.Documentable;

/**
 * @author Alberto
 * Modelo para los tipos de iva
 *
 */
@Entity
@Table(name = "tipo_iva")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class TipoIva extends Documentable{

}
