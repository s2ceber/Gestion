package s2.gestion.model.ventas;

import java.util.*;
/**
 * @author Alberto
 * Modelo para la cabecera de los documentos de venta
 *
 */

import javax.persistence.*;

import lombok.*;
import s2.gestion.model.base.*;
import s2.gestion.model.compras.*;

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
