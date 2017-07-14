package s2.gestion.model.ficheros;

import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.openxava.annotations.AsEmbedded;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.ListAction;
import org.openxava.annotations.ListProperties;
import org.openxava.annotations.Tab;
import org.openxava.annotations.View;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Documentable;
import s2.gestion.model.ventas.TarifaPrecio;

/**
 * @author Alberto Modelo para los articulos
 *
 */
@Entity
@Table(name = "articulo")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_entidad")
@View(members = "codigo, nombre; precio, tipoIva;nota; tarifaPrecios")
@Tab(properties="codigo, nombre, precio, tipoIva.tipo, nota")
public @Getter @Setter class Articulo extends Documentable {
    private String codigo;
    private String nombre;
    private BigDecimal precio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_tipo_iva"))
    @DescriptionsList(descriptionProperties="nombre, tipo", forViews="DEFAULT", forTabs="")
    private TipoIva tipoIva;
      
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "articulo", cascade = CascadeType.ALL)
    @AsEmbedded
    @ListProperties(value="tarifaVenta.nombre, precio")
    @ListAction("ArticuloPrecio.actualizarTarifas")
    private Collection<TarifaPrecio> tarifaPrecios;
}
