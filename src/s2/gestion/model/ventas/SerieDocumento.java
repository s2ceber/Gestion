package s2.gestion.model.ventas;

import javax.persistence.*;

import s2.gestion.model.base.Documentable;

/**
 * @author Alberto
 * Modelo para las series de documentos de venta
 *
 */
@Entity
@Table(name = "serie_documento")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class SerieDocumento extends Documentable{

}
