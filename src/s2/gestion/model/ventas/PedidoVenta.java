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
import org.openxava.annotations.ListProperties;
import org.openxava.jpa.XPersistence;

/**
 * @author Alberto
 * Modelo para la cabecera de los pedidos de venta
 *
 */
@Entity
@Table(name = "pedido_venta")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class PedidoVenta extends DocumentoVentaBase<PedidoVentaDetalle> {
    @OneToMany(fetch=FetchType.LAZY, mappedBy="pedidoVenta", cascade = CascadeType.REMOVE)
    @ListProperties("codigo, nombre,unidades,tipoIva, precio, dto1, dto2, dto3, dto4, importe[pedidoVenta.total, pedidoVenta.importeIva, pedidoVenta.totalConIva]")
    @AsEmbedded()
    @OrderColumn
    private List<PedidoVentaDetalle> lineasDetalles;

    public List<PedidoVentaDetalle> getLineasDetalles() {
        return lineasDetalles;
    }

    public void setLineasDetalles(List<PedidoVentaDetalle> lineasDetalles) {
        this.lineasDetalles = lineasDetalles;
    }

    public void traspasar(PresupuestoVenta origen){ //TODO será mejor médoto estático
	if (origen.isTraspasado()) return;
	PedidoVenta pv=new PedidoVenta();
	
	pv.setCliente(origen.getCliente());
	pv.setDocumentos(origen.getDocumentos());
	pv.setFecha(new java.util.Date());
	pv.setFormaPago(origen.getFormaPago());
	pv.setNota(origen.getNota());
//	pv.setNumero(numero);
	pv.setSerieDocumento(origen.getSerieDocumento());
	pv.setTarifaVenta(origen.getTarifaVenta());
	for (PresupuestoVentaDetalle detalleOrigen : origen.getDetallesNoTraspasados()) {
	    PedidoVentaDetalle pvd=new PedidoVentaDetalle();
	    pvd.traspasar(detalleOrigen);
	    pvd.setPedidoVenta(this);
	    getLineasDetalles().add(pvd);
	}
	XPersistence.getManager().persist(this);
    }
}
