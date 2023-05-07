package modelo;

public class Ciudades {
	private String continente;
	private String pais;
	private String idioma;
	private String distrito;
	private String ciudad;
	private int poblacion;
	
	public Ciudades(String continente, String pais, String idioma, String distrito, String ciudad, int poblacion) {
		this.continente = continente;
		this.pais = pais;
		this.idioma = idioma;
		this.distrito = distrito;
		this.ciudad = ciudad;
		this.poblacion = poblacion;
	}

	public String getContinente() {
		return continente;
	}

	public void setContinente(String continente) {
		this.continente = continente;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(int poblacion) {
		this.poblacion = poblacion;
	}

}
