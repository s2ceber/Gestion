package s2.gestion.model.ventas;

import java.math.BigDecimal;

import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Documentable;
import s2.gestion.model.ficheros.Articulo;

/**
 * @author Alberto Modelo para los detalles de los documentos de venta
 *
 */
@MappedSuperclass
// @Table(name = "documento_venta_detalle")

public @Getter @Setter class DocumentoVentaDetalleBase extends Documentable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey=@ForeignKey(name="fk_articulo"))
    private Articulo articulo;
    
    private BigDecimal unidades;
    private BigDecimal precio;
    private BigDecimal importe;
    private BigDecimal dto1;
    private BigDecimal dto2;
    private BigDecimal dto3;
    private BigDecimal dto4;

}
