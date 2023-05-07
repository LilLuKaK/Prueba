package vista;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.mysql.jdbc.PreparedStatement;

import modelo.BaseDeDatos;
import controlador.Principal;
import modelo.Ciudades;

import javax.swing.JComboBox;
import javax.swing.JSlider;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Insertar extends JPanel {
	private JTextField txtInsertar;
	private JTextField txtCiudad;
	private JTextField txtPais;
	private JTextField txtDistrito;
	private JTextField txtPoblacin;
	private JTextField txtBuscar;
	private JComboBox cmbPaises;
	private JComboBox cmbDistritos;
	private JSlider slider;
	private JButton btnInsertar;

	public Insertar() {
		setLayout(null);
		BaseDeDatos bd = new BaseDeDatos();
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 40, 843, 2);
		add(separator);
		
		txtInsertar = new JTextField();
		txtInsertar.setText("INSERTAR");
		txtInsertar.setHorizontalAlignment(SwingConstants.CENTER);
		txtInsertar.setFont(new Font("Arial", Font.PLAIN, 28));
		txtInsertar.setEditable(false);
		txtInsertar.setColumns(10);
		txtInsertar.setBorder(BorderFactory.createEmptyBorder());
		txtInsertar.setBackground(SystemColor.menu);
		txtInsertar.setBounds(10, 11, 843, 31);
		add(txtInsertar);
		
		txtCiudad = new JTextField();
		txtCiudad.setText("Ciudad:");
		txtCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCiudad.setFont(new Font("Arial", Font.PLAIN, 25));
		txtCiudad.setEditable(false);
		txtCiudad.setColumns(10);
		txtCiudad.setBorder(BorderFactory.createEmptyBorder());
		txtCiudad.setBackground(SystemColor.menu);
		txtCiudad.setBounds(212, 127, 135, 31);
		add(txtCiudad);
		
		txtPais = new JTextField();
		txtPais.setText("Pais:");
		txtPais.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPais.setFont(new Font("Arial", Font.PLAIN, 25));
		txtPais.setEditable(false);
		txtPais.setColumns(10);
		txtPais.setBorder(BorderFactory.createEmptyBorder());
		txtPais.setBackground(SystemColor.menu);
		txtPais.setBounds(212, 169, 135, 31);
		add(txtPais);
		
		txtDistrito = new JTextField();
		txtDistrito.setText("Distrito:");
		txtDistrito.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDistrito.setFont(new Font("Arial", Font.PLAIN, 25));
		txtDistrito.setEditable(false);
		txtDistrito.setColumns(10);
		txtDistrito.setBorder(BorderFactory.createEmptyBorder());
		txtDistrito.setBackground(SystemColor.menu);
		txtDistrito.setBounds(212, 211, 135, 31);
		add(txtDistrito);
		
		txtPoblacin = new JTextField();
		txtPoblacin.setText("Población:");
		txtPoblacin.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPoblacin.setFont(new Font("Arial", Font.PLAIN, 25));
		txtPoblacin.setEditable(false);
		txtPoblacin.setColumns(10);
		txtPoblacin.setBorder(BorderFactory.createEmptyBorder());
		txtPoblacin.setBackground(SystemColor.menu);
		txtPoblacin.setBounds(212, 253, 135, 31);
		add(txtPoblacin);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(346, 127, 169, 31);
		add(txtBuscar);
		txtBuscar.setColumns(10);
		
		cmbPaises = new JComboBox();
		ResultSet rs;
		rs = bd.consultar();
		try {
			while(rs.next()) {
				cmbPaises.addItem(rs.getString("name"));
			}
			bd.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cmbPaises.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
                String selected = (String) comboBox.getSelectedItem();
				BaseDeDatos bd = new BaseDeDatos();
				ResultSet rs;
				rs = bd.consultarCities(selected);
				cmbDistritos.removeAllItems();
				try {
					while(rs.next()) {
						cmbDistritos.addItem(rs.getString("name"));
					}
					bd.desconectar();
				} catch (SQLException ee) {
					ee.printStackTrace();
				}
			}
		});
		cmbPaises.setBounds(346, 169, 169, 31);
		cmbPaises.setVisible(true);
		add(cmbPaises);
		
		cmbDistritos = new JComboBox();
		cmbDistritos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
                String selected = (String) comboBox.getSelectedItem();
				BaseDeDatos bd = new BaseDeDatos();
				ResultSet rs;
				rs = bd.consultarDistritos(selected);
				if (txtBuscar.getText().isEmpty()) {
					if (cmbDistritos.getSelectedIndex() == -1) {
			        	btnInsertar.setVisible(false);
					}
		        } else {
		        	btnInsertar.setVisible(true);
		        }
			}
		});
		cmbDistritos.setBounds(346, 211, 169, 31);
		cmbDistritos.setVisible(true);
		add(cmbDistritos);
		
		JSlider slider = new JSlider(0, 2000000, 50000);
		slider.setMinorTickSpacing(50000);
		slider.setFont(new Font("Arial", Font.PLAIN, 10));
		slider.setMajorTickSpacing(250000);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setBounds(222, 286, 475, 44);
		add(slider);
		
		btnInsertar = new JButton("INSERTAR");
		btnInsertar.setVisible(false);
		btnInsertar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnInsertar.setBounds(346, 362, 169, 63);
		btnInsertar.setBorder(BorderFactory.createEmptyBorder());
		add(btnInsertar);

	}
	
	public void cargaComboPaises(JComboBox cmbPaises){
		BaseDeDatos bd = new BaseDeDatos();
		ResultSet rs;
		rs = bd.consultar();
		try {
			while(rs.next()) {
				cmbPaises.addItem(rs.getString("name"));
			}
			bd.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
