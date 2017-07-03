package s2.gestion.model.ficheros;

import java.math.BigDecimal;

import javax.persistence.*;

import org.openxava.annotations.Tab;
import org.openxava.annotations.View;

import lombok.Getter;
import lombok.Setter;
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
@View(members="nombre, tipo; documentos;nota")
@Tab(properties="nombre, tipo, nota")
public @Getter @Setter class TipoIva extends Documentable{
    @Column(nullable=false)
    private String nombre;
    @Column(nullable=false)
    private BigDecimal tipo;
}
