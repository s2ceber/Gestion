package s2.gestion.model.ficheros;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Identificable;

@Entity
@Table(name = "contador")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public @Getter @Setter class Contador extends Identificable {
    private String facturaVentas;

    @ColumnDefault("2")
    private Integer numeroDecimales;
}
