package s2.gestion.model.base;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

public @Getter @Setter abstract class Documentable extends Identificable {
    @Lob @Basic(fetch = FetchType.LAZY) //
    @Stereotype("SIMPLE_HTML_TEXT") // 
    private String nota;

    @Stereotype("FILES") @Column(length=32)
    private String documentos;
}
