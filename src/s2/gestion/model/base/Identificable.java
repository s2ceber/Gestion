package s2.gestion.model.base;


import java.lang.invoke.MethodHandles;
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

    
    public static <T extends Identificable> List<T> getAll(){
	@SuppressWarnings("unchecked")
	Class<T> clazz = (Class<T>) MethodHandles.lookup().lookupClass();
	String JPQL ="from "+ clazz.getName();
	List<T> resultList = XPersistence.getManager().createQuery(JPQL, clazz).getResultList();
	return resultList;
    }
}