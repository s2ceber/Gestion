package s2.gestion.model.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.openxava.annotations.Hidden;

@MappedSuperclass
public class LongID {
    @Id @Hidden @Column(unique = true, nullable = false) //
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
