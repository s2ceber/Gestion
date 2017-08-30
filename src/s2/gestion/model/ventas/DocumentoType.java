package s2.gestion.model.ventas;

public enum DocumentoType {
    PRESUPUESTO, PEDIDO, ALBARAN, FACTURA;
    public static DocumentoType get(DocumentoVentaBase<?, ?> documento){
	if (documento instanceof PresupuestoVenta){
	    return DocumentoType.PRESUPUESTO;
	}else if (documento instanceof PedidoVenta ){
	    return DocumentoType.PEDIDO;
	}else if (documento instanceof AlbaranVenta ){
	    return DocumentoType.ALBARAN;
	}else if (documento instanceof FacturaVenta ){
	    return DocumentoType.FACTURA;
	}else{
	    throw new IllegalArgumentException("Tipo de documento no válido");
	}
    }
}
