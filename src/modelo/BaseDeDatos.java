package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

public class BaseDeDatos {
	Connection conexion;
	Statement consulta = null;
	
	public Statement conectar() {
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/world", "root", "");
			consulta = conexion.createStatement();
		} catch (CommunicationsException err) {
			System.err.println("\nError: BASE DE DATOS NO INICIADA");
			 System. exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return consulta;
	}
	
	public void desconectar() {
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet consultar() {
		Statement consultaActual = conectar();
		ResultSet rs = null;
		try {
			rs = consultaActual.executeQuery("select name from country");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet consultarBusqueda(String selected) {
		Statement consultaActual = conectar();
		ResultSet rs = null;
		try {
			rs = consultaActual.executeQuery("select continent,co.name,language,district,ci.name,ci.population "
					+ "							from city ci,country co,countrylanguage cl"
					+ "							where ci.countrycode=co.code "
					+ "							and co.code=cl.countrycode "
					+ "							and ci.countrycode=cl.countrycode "
					+ "							and isofficial='T' and ci.name like '%"+selected+"%'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void cargaPaises(JComboBox cmbPaises) {
		Statement consultaActual = conectar();
	    ResultSet rs = null;
		try {
			rs = consultaActual.executeQuery("select name from country order by name;");
			while(rs.next()) {
				cmbPaises.addItem(rs.getObject("name"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void cargaDistritos(JComboBox cmbPaises) {
		Statement consultaActual = conectar();
	    ResultSet rs = null;
		try {
			rs = consultaActual.executeQuery("select district from city group by district;");
			while(rs.next()) {
				cmbPaises.addItem(rs.getObject("district"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet consultarPaises(String selected) {
		Statement consultaActual = conectar();
		ResultSet rs = null;
		try {
//			rs = consultaActual.executeQuery("select distinct district from city, country where countrycode = code AND code = (SELECT code from country where name = '" + selected + "')");
			rs = consultaActual.executeQuery("select continent,country.name,language,district,city.name,city.population "
					+ "							from city,country,countrylanguage "
					+ "							where city.countrycode=country.code and country.code=countrylanguage.countrycode and city.countrycode=countrylanguage.countrycode "
					+ "							and isofficial='T' and country.name= '"+selected+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet consultarCiudades(String selected) {
		Statement consultaActual = conectar();
		ResultSet rs = null;
		try {
			rs = consultaActual.executeQuery("select continent,country.name,language,district,city.name,city.population "
					+ "							from city,country,countrylanguage "
					+ "							where city.countrycode=country.code and country.code=countrylanguage.countrycode and city.countrycode=countrylanguage.countrycode "
					+ "							and isofficial='T' and district= '"+selected+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet consultarPoblacion(Integer selected) {
		Statement consultaActual = conectar();
		ResultSet rs = null;
		try {
			rs = consultaActual.executeQuery("select continent,country.name,language,district,city.name,city.population "
					+ "							from city,country,countrylanguage "
					+ "							where city.countrycode=country.code and country.code=countrylanguage.countrycode and city.countrycode=countrylanguage.countrycode "
					+ "							and isofficial='T' and city.population > '"+selected+"' order by population");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
//	public ResultSet consultarInsertar(String selected) {
//		Statement consultaActual = conectar();
//		ResultSet rs = null;
//		try {
//			rs = consultaActual.executeQuery("select distinct district from city, country where countrycode = code AND code = (SELECT code from country where name = '" + selected + "')");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return rs;
//	}
	
	public ResultSet consultarCities(String selected) {
		Statement consultaActual = conectar();
		ResultSet rs = null;
		try {
			rs = consultaActual.executeQuery("select city.name from city, country where countrycode = code AND code = (SELECT code from country where name = '" + selected + "')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet consultarDistritos(String selected) {
		Statement consultaActual = conectar();
		ResultSet rs = null;
		try {
			rs = consultaActual.executeQuery("select district from city, country where city.countrycode = country.code and country.name = '" + selected + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet insertar(String selected) {
		Statement consultaActual = conectar();
		ResultSet rs = null;
		try {
			rs = consultaActual.executeQuery("select district from city, country where city.countrycode = country.code and country.name = '" + selected + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}