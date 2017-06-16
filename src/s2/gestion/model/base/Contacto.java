package s2.gestion.model.base;

import javax.persistence.Embeddable;

import org.openxava.annotations.View;
import org.openxava.annotations.Views;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Views({ @View(members = "aliasContacto, telefono, email"), @View(name = "basico", members = "telefono, email") })
public @Getter @Setter class Contacto {
    private String aliasContacto;

    private String telefono;

    private String email;
}
