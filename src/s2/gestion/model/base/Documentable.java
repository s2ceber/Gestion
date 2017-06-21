package s2.gestion.model.base;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@MappedSuperclass
public @Getter @Setter abstract class Documentable extends Identificable {
    @Stereotype("FILES") @Column(length=32)
    private String documentos;
    
    @Column(columnDefinition="text") 
    @Basic(fetch = FetchType.LAZY) //
    @Stereotype("HTML_TEXT") // 
    private String nota;
}
