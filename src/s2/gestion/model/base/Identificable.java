package s2.gestion.model.base;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class Identificable extends UUIDID{
	
    @Version
    private Integer versionOptBlq;

    @Override
    public String toString() {
	return "ID:" + getId();
    }

}