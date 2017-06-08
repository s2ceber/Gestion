package s2.gestion.model.compras;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alberto
 * Modelo para los detalles de los albaranes de compra
 *
 */
@Entity
@Table(name = "albaran_compra_detalle")
public @Getter @Setter class AlbaranCompraDetalle extends DocumentoCompraDetalleBase {
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(foreignKey=@ForeignKey(name="fk_albaranCompra"))
    private AlbaranCompra albaranCompra;
}
