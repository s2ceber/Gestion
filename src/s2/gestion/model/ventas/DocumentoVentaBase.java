package s2.gestion.model.ventas;

/**
 * @author Alberto
 * Modelo para la cabecera de los documentos de venta
 *
 */
import java.util.Date;

import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.openxava.annotations.DescriptionsList;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Documentable;

@MappedSuperclass
// @Table(name = "documento_venta")
public @Getter @Setter class DocumentoVentaBase extends Documentable {
    private Integer numero;
    private String serieDocumento;
    private Date fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_cliente"))
    @DescriptionsList(descriptionProperties="nombre, nif", forTabs="NONE")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_forma_pago"))
    @DescriptionsList(descriptionProperties="codigo, nombre", forTabs="NONE")    
    private FormaPagoVenta formaPago;

}
