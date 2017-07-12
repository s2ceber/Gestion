package s2.gestion.model.ventas;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.persistence.UniqueConstraint;

import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.Tab;
import org.openxava.annotations.View;
import org.openxava.jpa.XPersistence;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Identificable;
import s2.gestion.model.ficheros.Articulo;

@Entity
@Table(name = "tarifa_precio", uniqueConstraints = @UniqueConstraint(columnNames = { "articulo_id",
	"tarifaventa_id" }, name = "uk_cliente_tarifaventa"))
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_entidad")
@View(members = "nombre;documentos;nota")
@Tab(properties = "nombre, documentos")
public @Getter @Setter class TarifaPrecio extends Identificable {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_articulo"))
    @DescriptionsList(descriptionProperties = "nombre, nif", forViews = "DEFAULT", forTabs = "")
    private Articulo articulo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_tarifa_venta"))
    @DescriptionsList(descriptionProperties = "nombre", forViews = "DEFAULT", forTabs = "")
    private TarifaVenta tarifaVenta;

    private BigDecimal precio;

    public static TarifaPrecio findByArticuloAndTarifaVenta(Articulo articulo, TarifaVenta tarifaVenta) {
	String JPQL = "from TarifaPrecio tp where tp.articulo=:articulo and tp.tarifaVenta=:tarifaVenta";
	TypedQuery<TarifaPrecio> query = XPersistence.getManager().createQuery(JPQL, TarifaPrecio.class)
		.setParameter("articulo", articulo).setParameter("tarifaVenta", tarifaVenta).setMaxResults(1);

	return query.getSingleResult();
    }

    public static void createTarifas(Articulo articulo) {
	List<TarifaVenta> tarifas = TarifaVenta.getAll();
	String JPQL = "from TarifaPrecio tp where tp.articulo=:articulo";
	List<TarifaPrecio> tarifaPreciosBD = XPersistence.getManager().createQuery(JPQL, TarifaPrecio.class)
		.setParameter("articulo", articulo).getResultList();
	if (tarifaPreciosBD.isEmpty()) {
	    createTarifas(articulo, tarifas);
	} else if (tarifaPreciosBD.size() != tarifas.size()) {
	    String JPQL1 = "select tv from TarifaPrecio tp rigth join tp.tarifaVenta tv where tp.articulo=:articulo and tv.id is null";
	    tarifas = XPersistence.getManager().createQuery(JPQL1, TarifaVenta.class).setParameter("articulo", articulo)
		    .getResultList();
	    createTarifas(articulo, tarifas);
	}
    }

    private static void createTarifas(Articulo articulo, List<TarifaVenta> tarifas) {
	for (TarifaVenta tarifaVenta : tarifas) {
	    TarifaPrecio tp = new TarifaPrecio();
	    tp.setArticulo(articulo);
	    tp.setPrecio(BigDecimal.ZERO);
	    tp.setTarifaVenta(tarifaVenta);
	    XPersistence.getManager().persist(tp);
	}
    }
}
