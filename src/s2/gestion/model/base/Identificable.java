package s2.gestion.model.base;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.openxava.model.Identifiable;

@MappedSuperclass
public abstract class Identificable extends Identifiable{

//    @Id
//    @Hidden
//    @Column(unique = true, nullable = false) //
//    @GeneratedValue(strategy = GenerationType.IDENTITY) //
//    private Long id;

    @Version
    private Integer versionOptBlq;

    @Override
    public String toString() {
	return "ID:" + getId();
    }

}