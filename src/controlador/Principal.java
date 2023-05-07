package controlador;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Insertar;
import modelo.BaseDeDatos;
import modelo.Ciudades;
import vista.Tabla;

public class Principal {

	public static void main(String[] args) {
		Tabla miTabla = new Tabla();
		miTabla.cargaVentana(miTabla);
	}
	
//	public ArrayList<Insertar> cargarInsertar(String selected){
//		ArrayList<Insertar> arrNuevo = new ArrayList<>();
//		BaseDeDatos bd = new BaseDeDatos();
//		ResultSet rs=null;
//		bd.conectar();
//		rs=bd.consultarPaises(selected);
//		Ciudades c; 
//		try {
//			while(rs.next()) {
//				c = new Ciudades(rs.getString("city.name"),rs.getString("country.name"),rs.getString("district"),rs.getInt("city.population"));
//				arrNuevo.add(c);
//			}
//		} catch (Exception e) {
//		}finally {
//			bd.desconectar();
//		}
//		
//		return arrNuevo;
//	}
}
