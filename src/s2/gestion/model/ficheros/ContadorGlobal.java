package s2.gestion.model.ficheros;

import java.util.List;

import javax.persistence.*;

import org.openxava.annotations.Hidden;
import org.openxava.jpa.XPersistence;

import com.openxava.naviox.model.User;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alberto Modelos para los contadores de los documentos de compra y venta
 *
 */
@Entity
@Table(name = "v_contador_global")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_entidad")
@NamedQuery(name = "ContadorGlobal.getDefaultByUser", query = "SELECT cg FROM ContadorGlobal cg where cg.user=:user")
public @Getter @Setter class ContadorGlobal {
    @Id
    @Hidden //
    @Column(unique = true, nullable = false) //
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    private Long id;

    @Version
    private Integer versionOptBlq;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ejercicio_nombre", foreignKey = @ForeignKey(name = "fk_ejercicio"))
    private Ejercicio ejercicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oxusers_name", foreignKey = @ForeignKey(name = "fk_users"))
    private User user;

    /**
     * Obtiene los datos de contadores
     * 
     * @return
     */
    public static ContadorGlobal getDefault(User user) {
	List<ContadorGlobal> contadores = XPersistence.getManager()
		.createNamedQuery("ContadorGlobal.getDefaultByUser", ContadorGlobal.class).setMaxResults(1)
		.setParameter("user", user).getResultList();

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
