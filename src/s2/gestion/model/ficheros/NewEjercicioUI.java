package s2.gestion.model.ficheros;

import java.util.ArrayList;

public class NewEjercicioUI {
    public NewEjercicioUI() {
	leerPlantillas();
    }
    private void leerPlantillas() {
	plantillas=new ArrayList<String>();
	plantillas.add("ejemplo1");
	plantillas.add("otro año");
	
    }
    private String nombre;
    
    private ArrayList<String> plantillas;
    
    private Boolean copiarDatosDeClientes=true;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getPlantillas() {
        return plantillas;
    }

    public void setPlantillas(ArrayList<String> plantillas) {
        this.plantillas = plantillas;
    }

    public Boolean getCopiarDatosDeClientes() {
        return copiarDatosDeClientes;
    }

    public void setCopiarDatosDeClientes(Boolean copiarDatosDeClientes) {
        this.copiarDatosDeClientes = copiarDatosDeClientes;
    }

}
