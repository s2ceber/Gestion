package s2.gestion.model.ficheros;

import javax.persistence.*;

import s2.gestion.model.base.Documentable;

/**
 * @author Alberto
 * Modelo para las empresas 
 *
 */
@Entity
@Table(name = "empresa")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class Empresa extends Documentable{

}
