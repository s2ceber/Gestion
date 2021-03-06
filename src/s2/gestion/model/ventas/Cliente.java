package s2.gestion.model.ventas;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.openxava.annotations.AsEmbedded;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.ListProperties;
import org.openxava.annotations.NoFrame;
import org.openxava.annotations.ReferenceView;
import org.openxava.annotations.View;
import org.openxava.annotations.Views;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Contacto;
import s2.gestion.model.base.Direccion;
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
    @View(members = "#nombre, nif; contacto; direccion; tarifaVenta, formaPago; contactos{contactos} direcciones{direcciones} otros{documentos; nota}") 
 })
public @Getter @Setter class Cliente extends Documentable {
    private String nombre;
    private String nif;

    @Embedded
    @ReferenceView("basico")
    @NoFrame
    private Contacto contacto;
    
    @Embedded
    @NoFrame
    private Direccion direccion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_tarifa_venta"))
    @DescriptionsList(descriptionProperties = "nombre, nota")
    private TarifaVenta tarifaVenta;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_forma_pago"))
    @DescriptionsList(descriptionProperties = "codigo, nombre")
    private FormaPagoVenta formaPago;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
    @AsEmbedded
    @OrderColumn
    @ListProperties(value="contacto.aliasContacto, contacto.telefono, contacto.email")    
    private List<ContactoCliente> contactos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
    @AsEmbedded
    @OrderColumn()
    @ListProperties(value="direccion.direccion, direccion.poblacion, direccion.codigoPostal, direccion.provincia")
    private List<DireccionCliente> direcciones;
}
