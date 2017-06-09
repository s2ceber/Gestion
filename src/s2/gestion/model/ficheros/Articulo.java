package s2.gestion.model.ficheros;

import java.math.BigDecimal;

import javax.persistence.*;

import org.openxava.annotations.View;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Documentable;

/**
 * @author Alberto Modelo para los articulos
 *
 */
@Entity
@Table(name = "articulo")
@View(members="codigo, nombre, precio; nota")
public @Getter @Setter class Articulo extends Documentable {
    private String codigo;
    private String nombre;
    private BigDecimal precio;
}
