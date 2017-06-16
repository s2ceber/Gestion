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

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Documentable;
import s2.gestion.model.compras.FormaPagoCompra;

@MappedSuperclass
// @Table(name = "documento_venta")
public @Getter @Setter class DocumentoVentaBase extends Documentable {
    private Integer numero;
    private String serieDocumento;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey=@ForeignKey(name="fk_cliente"))
    private Cliente cliente;
    private Date fecha;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey=@ForeignKey(name="fk_forma_pago"))
    private FormaPagoCompra formaPago;

}
