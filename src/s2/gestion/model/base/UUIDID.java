package s2.gestion.model.base;

import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import org.hibernate.annotations.Type;
import org.openxava.annotations.Hidden;

import com.fasterxml.uuid.Generators;

@MappedSuperclass
public class UUIDID {
    @Id
    @Hidden
    @Type(type = "pg-uuid")
    private UUID id;

    public UUID getId() {
	return id;
    }

    public void setId(UUID id) {
	this.id = id;
    }

    @PrePersist
    public void initID() throws Exception {
	if (this.id == null)
	    this.id = Generators.timeBasedGenerator().generate();
    }
}
