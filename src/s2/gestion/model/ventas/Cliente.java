package s2.gestion.model.ventas;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.openxava.annotations.AsEmbedded;
import org.openxava.annotations.View;
import org.openxava.annotations.Views;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Documentable;

/**
 * @author Alberto Modelo para los datos basicos de los clientes
 *
 */
@Entity
@Table(name = "cliente")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_cliente")
@Views({ 
    @View(members = "nombre, nif, contactos{contactos} direcciones{direcciones} otros{documentos; nota}") 
 })
public @Getter @Setter class Cliente extends Documentable {
    private String nombre;
    private String nif;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
    @AsEmbedded
    @OrderColumn
    private List<ContactoCliente> contactos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
    @AsEmbedded
    @OrderColumn()
    private List<DireccionCliente> direcciones;
}
