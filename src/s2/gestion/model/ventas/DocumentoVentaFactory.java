package s2.gestion.model.ventas;

import java.util.ArrayList;

public class DocumentoVentaFactory {
    @SuppressWarnings("unchecked")
    public static <T extends DocumentoVentaBase<T, D>, D extends DocumentoVentaDetalleBase<T, D>> T getDocumento(
	    DocumentoType type) {
	T documento;
	switch (type) {
	case ALBARAN:
	    documento = (T) new AlbaranVenta();
	    break;
	case FACTURA:
	    documento = (T) new FacturaVenta();
	    break;
	case PEDIDO:
	    documento = (T) new PedidoVenta();
	    break;
	case PRESUPUESTO:
	    documento = (T) new PresupuestoVenta();
	    break;
	default:
	    throw new AssertionError("No existe ese tipo de documento");
	}
	documento.setDetalles(new ArrayList<>());
	return documento;

    }

    @SuppressWarnings("unchecked")
    public static <T extends DocumentoVentaBase<T, U>, U extends DocumentoVentaDetalleBase<T, U>> U getDetalle(
	    DocumentoType type) {
	switch (type) {
	case ALBARAN:
	    return (U) new AlbaranVentaDetalle();
	case FACTURA:
	    return (U) new FacturaVentaDetalle();
	case PEDIDO:
	    return (U) new PedidoVentaDetalle();
	case PRESUPUESTO:
	    return (U) new PresupuestoVentaDetalle();
	default:
	    throw new AssertionError("No existe ese tipo de documento");
	}
    }
}
