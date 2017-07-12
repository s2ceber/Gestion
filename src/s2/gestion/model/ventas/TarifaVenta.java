package s2.gestion.model.ventas;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.openxava.annotations.Tab;
import org.openxava.annotations.View;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Documentable;

/**
 * @author Alberto
 * Modelo para las tarifas de venta
 *
 */
@Entity
@Table(name = "tarifa_venta")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
@View(members="nombre, ivaIncluido;documentos;nota")
@Tab(properties="nombre, ivaIncluido, nota")
public @Getter @Setter class TarifaVenta extends Documentable{
    private String nombre;
    
    private Boolean ivaIncluido;

}
