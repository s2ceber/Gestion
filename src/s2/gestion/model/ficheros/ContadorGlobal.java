package s2.gestion.model.ficheros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.openxava.jpa.XPersistence;

import com.openxava.naviox.model.User;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Identificable;

/**
 * @author Alberto Modelos para los contadores de los documentos de compra y venta
 *
 */
@Entity
@Table(name = "v_contador_global", indexes = @Index(name = "uk_user", columnList = "user", unique = true))
@NamedQuery(name = "ContadorGlobal.getDefaultByUser", query = "SELECT cg FROM ContadorGlobal cg where cg.user=:user")
public @Getter @Setter class ContadorGlobal extends Identificable {
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_ejercicio"))
    private Ejercicio ejercicio;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_user"))
    private User user;

    /**
     * Obtiene los datos de contadores
     * 
     * @return
     */
    public static ContadorGlobal getDefault(User user) {
	// String sql = "select * from public.contador_global limit 1;";

	// XPersistence.getManager().createNativeQuery(sql, ContadorGlobal.class).getResultList();
	List<ContadorGlobal> contadores = XPersistence.getManager()
		.createNamedQuery("ContadorGlobal.getDefaultByUser", ContadorGlobal.class).setMaxResults(1)
		.setParameter("user", user).getResultList();
	// List<ContadorGlobal> contadores = XPersistence.getManager().createQuery(jpql, ContadorGlobal.class)
	// .setMaxResults(1).getResultList();
	if (contadores.size() != 1) {
	    return null;
	}
	return contadores.get(0);
    }

    public static void setDefault(ContadorGlobal contadorGlobal) {
	ContadorGlobal myContador = getDefault(contadorGlobal.getUser());

	if (myContador == null) {
	    myContador = contadorGlobal;
	} else {
	    myContador.setEjercicio(contadorGlobal.getEjercicio());
	}
	XPersistence.getManager().merge(myContador);
    }
}
