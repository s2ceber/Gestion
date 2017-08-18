package s2.gestion.model.ventas;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
public @Getter @Setter class OrigenTraspaso {
    private DocumentoType documentoType;
    private Long idOrigen;
}
