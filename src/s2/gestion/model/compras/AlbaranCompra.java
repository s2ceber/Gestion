package s2.gestion.model.compras;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.AsEmbedded;
import org.openxava.annotations.ListProperties;
import org.openxava.annotations.NewAction;
import org.openxava.annotations.View;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alberto Modelo para la cabecera de los albaranes de compra
 *
 */
@Entity
@Table(name = "albaran_compra")
@View(members="numero, fecha, proveedor, formaPago;articulos{albaranCompraDetalles} otrosDatos{documentos;nota}")
public @Getter @Setter class AlbaranCompra extends DocumentoCompraBase {
    @OneToMany(fetch=FetchType.LAZY, mappedBy="albaranCompra")
    @ListProperties("articulo, codigo, nombre,unidades, precio, importe, dto1, dto2, dto3, dto4")
    @AsEmbedded()
    @NewAction("AlbaranCompra.addArticulo")
    private List<AlbaranCompraDetalle> albaranCompraDetalles;

    public BigDecimal getTotal(){
	BigDecimal total=BigDecimal.ZERO;
	for (AlbaranCompraDetalle albaranCompraDetalle : albaranCompraDetalles) {
	    total.add(albaranCompraDetalle.getTotal());
	}
	return total;
    }
}
