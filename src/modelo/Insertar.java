package modelo;

public class Insertar {
	private String pais;
	private String ciudad;
	private String distrito;
	private int poblacion;
	
	public Insertar(String pais, String ciudad, String distrito, int poblacion) {
		super();
		this.pais = pais;
		this.ciudad = ciudad;
		this.distrito = distrito;
		this.poblacion = poblacion;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public int getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(int poblacion) {
		this.poblacion = poblacion;
	}
	
}
