package s2.gestion.model.ficheros;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.Tab;
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
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_entidad")
@View(members = "codigo, nombre; precio, tipoIva;nota")
@Tab(properties="codigo, nombre, precio, tipoIva.tipo, nota")
public @Getter @Setter class Articulo extends Documentable {
    private String codigo;
    private String nombre;
    private BigDecimal precio;

    @ManyToOne(fetch = FetchType.EAGER)
    @DescriptionsList(descriptionProperties="nombre, tipo", forViews="DEFAULT", forTabs="")
    private TipoIva tipoIva;
   
}
