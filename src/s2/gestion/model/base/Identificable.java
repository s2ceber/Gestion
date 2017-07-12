package s2.gestion.model.base;


import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.openxava.jpa.XPersistence;

@MappedSuperclass
public abstract class Identificable extends LongID{
	
    @Version
    private Integer versionOptBlq;

    @Override
    public String toString() {
	return "ID:" + getId();
    }

    
    public static <T extends Identificable> List<T> getAll(Class<T>clazz){
	String JPQL ="from "+ clazz.getSimpleName();
	List<T> resultList = XPersistence.getManager().createQuery(JPQL, clazz).getResultList();
	return resultList;
    }
}