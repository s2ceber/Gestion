package s2.gestion.model.base;

import javax.persistence.*;

import org.openxava.annotations.*;

@MappedSuperclass

public abstract class Identificable {

    @Id @Hidden @Column(unique = true, nullable = false) //
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    private Long id;
    
    public Long getId() {
	return id;
    }
    
    public void setId(Long id) {
	this.id = id;
    }
    
    @Override
    public String toString() {
        return "ID:"+getId();
    }
    
}