package s2.gestion.model.ficheros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.openxava.jpa.XPersistence;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Identificable;
import s2.gestion.util.NoInitializeXavaException;

/**
 * @author Alberto Modelos para los contadores de los documentos de compra y venta
 *
 */
@Entity
@Table(name = "contador_global")
public @Getter @Setter class ContadorGlobal extends Identificable {
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_ejercicio"))
    private Ejercicio ejercicio;

    /**
     * Obtiene los datos de contadores
     * 
     * @return
     */
    public static ContadorGlobal getDefault() {
	String sql = "select * from public.contador_global limit 1;";
	List<ContadorGlobal> contadores = XPersistence.getManager().createNativeQuery(sql, ContadorGlobal.class).getResultList();
	
//	List<ContadorGlobal> contadores = XPersistence.getManager().createQuery(jpql, ContadorGlobal.class)
//		.setMaxResults(1).getResultList();
	if (contadores.size() != 1) {
	    throw new NoInitializeXavaException("noInitContadores");
	}
	return contadores.get(0);
    }

    public static void setDefault(ContadorGlobal contadorGlobal) {
	ContadorGlobal myContador;

	myContador = getDefault();
	myContador.setEjercicio(contadorGlobal.getEjercicio());

	XPersistence.getManager().merge(myContador);
    }
}
