package s2.gestion.model.ventas;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.openxava.annotations.Hidden;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Identificable;

/**
 * @author Alberto
 * Modelo para las series de documentos de venta
 *
 */
@Entity
@Table(name = "serie_documento")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public @Getter @Setter class SerieDocumento extends Identificable{
    private String nombre;
    
    private String descripcion;
    
    @Hidden
    private Integer numero;

    @PrePersist
    private void prePersist(){
	if (numero==null) numero=0;
    }
    
}
