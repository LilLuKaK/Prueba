package vista;

import javax.swing.JPanel;
import javax.swing.JComboBox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;

import controlador.Principal;
import modelo.BaseDeDatos;
import modelo.Ciudades;

import java.awt.SystemColor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.AncestorEvent;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Consulta extends JPanel {

	private JScrollPane scrollPane;
	private JComboBox cmbPaises;
	private JComboBox cmbDistritos;
	private JTable TablaCiudades;
	DefaultTableModel modelotabla = new DefaultTableModel();
	
	private JTextField txtDistritos;
	private JTextField txtPaises;
	private JTextField TxTercero;
	private JTextField TxSegundo;
	private JTextField txPrimero;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JTextField txtBuscar;
	private JTextField txtCiudad;
	private JTextField txtConsultas;
	private JRadioButton RadioCiudades;
	private JRadioButton RadioPaises;
	private JRadioButton RadioDistritos;
	private JSeparator separator_1;
	private JTextField txtModificaciones;
	private JTextField txtPoblacion;
	private JRadioButton RadioPoblacion;
	
	public Consulta() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setLayout(null);
		panel.setBounds(10, 11, 495, 306);
		add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 240, 240));
		panel_1.setLayout(null);
		panel_1.setBounds(519, 11, 495, 306);
		add(panel_1);
		
		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				modelotabla.setRowCount(0);
				String selected = txtBuscar.getText();
				BaseDeDatos bd = new BaseDeDatos();
				ResultSet rs = bd.consultarBusqueda(selected);
				try {
					modelotabla.setRowCount(0);
					while(rs.next()) {
						modelotabla.addRow(new Object[]{
							rs.getString("co.continent"),
							rs.getString("co.name"),
							rs.getString("cl.language"),
							rs.getString("ci.district"),
							rs.getString("ci.name"),
							rs.getInt("ci.population")
						});
					}
					TablaCiudades.setModel(modelotabla);
					for (Ciudades c : cargarArrayCiudades(cmbDistritos.getSelectedItem().toString())) {
						modelotabla.addRow(new Object[] {
							c.getContinente(),
							c.getPais(),
							c.getIdioma(),
							c.getDistrito(),
							c.getCiudad(),
							c.getPoblacion()
						});
					}
				}catch(SQLException er) {
					er.printStackTrace();
				}
			}
		});
		txtBuscar.setBounds(118, 61, 117, 31);
		panel.add(txtBuscar);
		txtBuscar.setColumns(10);
		txtBuscar.setVisible(false);
		
		JComboBox cmbDistritos = new JComboBox();
		cmbDistritos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					modelotabla.setRowCount(0);
					modelotabla.setColumnIdentifiers(new Object[] {
						"Continente",
						"Pais",
						"Idioma",
						"Distrito",
						"Ciudad",
						"Poblacion"
					});
					TablaCiudades.setModel(modelotabla);
					for (Ciudades c : cargarArrayDistritos(cmbDistritos.getSelectedItem().toString())) {
						modelotabla.addRow(new Object[] {
							c.getContinente(),
							c.getPais(),
							c.getIdioma(),
							c.getDistrito(),
							c.getCiudad(),
							c.getPoblacion()
						});
					}
				} catch (NullPointerException e2) {

				}
			}
		});
		cmbDistritos.setLayout(new BorderLayout());
		cmbDistritos.setBorder(BorderFactory.createEmptyBorder());
		cmbDistritos.setBackground(Color.WHITE);
		cmbDistritos.setBounds(118, 145, 117, 31);
		cmbDistritos.setVisible(false);
		panel.add(cmbDistritos);
		BaseDeDatos bd = new BaseDeDatos();
		bd.cargaDistritos(cmbDistritos);
		
		JComboBox cmbPaises = new JComboBox();
		cmbPaises.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					modelotabla.setRowCount(0);
					modelotabla.setColumnIdentifiers(new Object[] {
						"Continente",
						"Pais",
						"Idioma",
						"Distrito",
						"Ciudad",
						"Poblacion"
					});
					TablaCiudades.setModel(modelotabla);
					for (Ciudades c : cargarArrayPaises(cmbPaises.getSelectedItem().toString())) {
						modelotabla.addRow(new Object[] {
							c.getContinente(),
							c.getPais(),
							c.getIdioma(),
							c.getDistrito(),
							c.getCiudad(),
							c.getPoblacion()
						});
					}
				} catch (NullPointerException e2) {

				}
			}
		});
		cmbPaises.setLayout(new BorderLayout());
		cmbPaises.setBackground(Color.WHITE);
		cmbPaises.setBounds(118, 103, 117, 31);
		cmbPaises.setVisible(false);
		panel.add(cmbPaises);
		bd.cargaPaises(cmbPaises);
		
		JSlider slider = new JSlider(0, 2000000, 50000);
		slider.setMinorTickSpacing(50000);
		slider.setFont(new Font("Arial", Font.PLAIN, 10));
		slider.setMajorTickSpacing(250000);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					modelotabla.setRowCount(0);
					modelotabla.setColumnIdentifiers(new Object[] {
						"Continente",
						"Pais",
						"Idioma",
						"Distrito",
						"Ciudad",
						"Poblacion"
					});
					TablaCiudades.setModel(modelotabla);
					for (Ciudades c : cargarArrayPoblacion(slider.getValue())) {
						modelotabla.addRow(new Object[] {
							c.getContinente(),
							c.getPais(),
							c.getIdioma(),
							c.getDistrito(),
							c.getCiudad(),
							c.getPoblacion()
						});
					}
				} catch (NullPointerException e2) {

				}
			}
		});
		slider.setBounds(10, 218, 475, 44);
		slider.setVisible(false);
		panel.add(slider);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 328, 1004, 381);
		add(scrollPane);
		
		TablaCiudades = new JTable();
		TablaCiudades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txPrimero.setText("");
				TxSegundo.setText("");
				TxTercero.setText("");
				txPrimero.setText(TablaCiudades.getValueAt(TablaCiudades.getSelectedRow(),1).toString());
				TxSegundo.setText(TablaCiudades.getValueAt(TablaCiudades.getSelectedRow(),3).toString());
				TxTercero.setText(TablaCiudades.getValueAt(TablaCiudades.getSelectedRow(),5).toString());
			}
		});
		scrollPane.setViewportView(TablaCiudades);
		
		txtDistritos = new JTextField();
		txtDistritos.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDistritos.setText("Distritos:");
		txtDistritos.setFont(new Font("Arial", Font.PLAIN, 18));
		txtDistritos.setEditable(false);
		txtDistritos.setColumns(10);
		txtDistritos.setBorder(BorderFactory.createEmptyBorder());
		txtDistritos.setBackground(new Color(240, 240, 240));
		txtDistritos.setBounds(33, 145, 86, 31);
		panel.add(txtDistritos);
		
		txtPaises = new JTextField();
		txtPaises.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPaises.setText("Paises:");
		txtPaises.setFont(new Font("Arial", Font.PLAIN, 18));
		txtPaises.setEditable(false);
		txtPaises.setColumns(10);
		txtPaises.setBorder(BorderFactory.createEmptyBorder());
		txtPaises.setBackground(new Color(240, 240, 240));
		txtPaises.setBounds(33, 103, 86, 31);
		panel.add(txtPaises);
		
		txtCiudad = new JTextField();
		txtCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCiudad.setText("Ciudades:");
		txtCiudad.setFont(new Font("Arial", Font.PLAIN, 18));
		txtCiudad.setEditable(false);
		txtCiudad.setColumns(10);
		txtCiudad.setBorder(BorderFactory.createEmptyBorder());
		txtCiudad.setBackground(SystemColor.menu);
		txtCiudad.setBounds(33, 61, 86, 31);
		panel.add(txtCiudad);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 40, 475, 2);
		panel.add(separator);
		
		txtConsultas = new JTextField();
		txtConsultas.setText("CONSULTAS");
		txtConsultas.setHorizontalAlignment(SwingConstants.CENTER);
		txtConsultas.setFont(new Font("Arial", Font.PLAIN, 28));
		txtConsultas.setEditable(false);
		txtConsultas.setColumns(10);
		txtConsultas.setBorder(BorderFactory.createEmptyBorder());
		txtConsultas.setBackground(SystemColor.menu);
		txtConsultas.setBounds(10, 11, 475, 31);
		panel.add(txtConsultas);

		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.setFont(new Font("Arial", Font.PLAIN, 13));
		btnModificar.setBounds(109, 159, 103, 31);
		btnModificar.setBorder(BorderFactory.createEmptyBorder());
    	btnModificar.setVisible(false);
		panel_1.add(btnModificar);
		
		JButton btnBorrar = new JButton("BORRAR");
		btnBorrar.setFont(new Font("Arial", Font.PLAIN, 13));
		btnBorrar.setBounds(211, 159, 103, 31);
		btnBorrar.setBorder(BorderFactory.createEmptyBorder());
    	btnBorrar.setVisible(false);
		panel_1.add(btnBorrar);
		
		RadioCiudades = new JRadioButton("");
		RadioCiudades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (RadioCiudades.isSelected()==true) {
                	RadioPaises.setSelected(false);
                	RadioDistritos.setSelected(false);
                	RadioPoblacion.setSelected(false);
			        cmbPaises.setVisible(false);
			        cmbDistritos.setVisible(false);
			        slider.setVisible(false);
                	txtBuscar.setVisible(true);
                	txPrimero.setText("");
                	TxSegundo.setText("");
                	TxTercero.setText("");
                	btnModificar.setVisible(false);
                	btnBorrar.setVisible(false);
					modelotabla.setRowCount(0);
			    } else {
			    	txtBuscar.setVisible(false);
			    }
			}
		});
		RadioCiudades.setBounds(10, 61, 21, 31);
		panel.add(RadioCiudades);
		
		RadioPaises = new JRadioButton("");
		RadioPaises.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (RadioPaises.isSelected()) {
                	RadioCiudades.setSelected(false);
                	RadioDistritos.setSelected(false);
                	RadioPoblacion.setSelected(false);
			        txtBuscar.setVisible(false);
			        cmbDistritos.setVisible(false);
			        slider.setVisible(false);
                	cmbPaises.setVisible(true);
                	txPrimero.setText("");
                	TxSegundo.setText("");
                	TxTercero.setText("");
                	btnModificar.setVisible(false);
                	btnBorrar.setVisible(false);
					modelotabla.setRowCount(0);
			    } else {
			    	cmbPaises.setVisible(false);
			    }
			}
		});
		RadioPaises.setBounds(10, 103, 21, 31);
		panel.add(RadioPaises);
		
		RadioDistritos = new JRadioButton("");
		RadioDistritos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (RadioDistritos.isSelected()) {
                	RadioPaises.setSelected(false);
                	RadioCiudades.setSelected(false);
                	RadioPoblacion.setSelected(false);
			        txtBuscar.setVisible(false);
			        cmbPaises.setVisible(false);
			        slider.setVisible(false);
                	cmbDistritos.setVisible(true);
                	txPrimero.setText("");
                	TxSegundo.setText("");
                	TxTercero.setText("");
                	btnModificar.setVisible(false);
                	btnBorrar.setVisible(false);
					modelotabla.setRowCount(0);
			    } else {
			    	cmbDistritos.setVisible(false);
			    }
			}
		});
		RadioDistritos.setBounds(10, 145, 21, 31);
		panel.add(RadioDistritos);
		
		txtPoblacion = new JTextField();
		txtPoblacion.setText("Población:");
		txtPoblacion.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPoblacion.setFont(new Font("Arial", Font.PLAIN, 18));
		txtPoblacion.setEditable(false);
		txtPoblacion.setColumns(10);
		txtPoblacion.setBorder(BorderFactory.createEmptyBorder());
		txtPoblacion.setBackground(SystemColor.menu);
		txtPoblacion.setBounds(33, 187, 86, 31);
		panel.add(txtPoblacion);
		
		

		RadioPoblacion = new JRadioButton("");
		RadioPoblacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (RadioPoblacion.isSelected()) {
                	RadioPaises.setSelected(false);
                	RadioDistritos.setSelected(false);
                	RadioCiudades.setSelected(false);
			        txtBuscar.setVisible(false);
			        cmbPaises.setVisible(false);
			        cmbDistritos.setVisible(false);
			        slider.setVisible(true);
                	txPrimero.setText("");
                	TxSegundo.setText("");
                	TxTercero.setText("");
                	btnModificar.setVisible(false);
                	btnBorrar.setVisible(false);
					modelotabla.setRowCount(0);
			    } else {
			        slider.setVisible(false);
			    }
			}
		});
		RadioPoblacion.setBounds(10, 187, 21, 31);
		panel.add(RadioPoblacion);
		
		TxTercero = new JTextField();
		TxTercero.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	TxTercero.setEnabled(true);
            	btnModificar.setVisible(true);
            	btnBorrar.setVisible(true);
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                if (TxTercero.getText().isEmpty()) {
                	TxTercero.setEnabled(false);
                	TxTercero.setText("");
                }
            }
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}
        });
		TxTercero.setBackground(new Color(255, 255, 255));
		TxTercero.setColumns(10);
		TxTercero.setBounds(300, 80, 117, 31);
		TxTercero.setVisible(true);
		TxTercero.setEnabled(false);
		TxTercero.setBorder(BorderFactory.createEmptyBorder());
		panel_1.add(TxTercero);
		
		TxSegundo = new JTextField();
		TxSegundo.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	TxSegundo.setEnabled(true);
            	btnModificar.setVisible(true);
            	btnBorrar.setVisible(true);
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                if (TxSegundo.getText().isEmpty()) {
                	TxSegundo.setEnabled(false);
                	TxSegundo.setText("");
                }
            }
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}
        });
		TxSegundo.setBackground(new Color(255, 255, 255));
		TxSegundo.setColumns(10);
		TxSegundo.setBounds(173, 80, 117, 31);
		TxSegundo.setVisible(true);
		TxSegundo.setEnabled(false);
		TxSegundo.setBorder(BorderFactory.createEmptyBorder());
		panel_1.add(TxSegundo);
		
		txPrimero = new JTextField();
		txPrimero.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	txPrimero.setEnabled(true);
            	btnModificar.setVisible(true);
            	btnBorrar.setVisible(true);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (txPrimero.getText().isEmpty()) {
                	txPrimero.setEnabled(false);
                	txPrimero.setText("");
                }
            }
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}
        });
		txPrimero.setBackground(new Color(255, 255, 255));
		txPrimero.setColumns(10);
		txPrimero.setBounds(46, 80, 117, 31);
		txPrimero.setVisible(true);
		txPrimero.setEnabled(false);
		txPrimero.setBorder(BorderFactory.createEmptyBorder());
		panel_1.add(txPrimero);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(10, 40, 475, 2);
		panel_1.add(separator_1);
		
		txtModificaciones = new JTextField();
		txtModificaciones.setText("MODIFICACIONES");
		txtModificaciones.setHorizontalAlignment(SwingConstants.CENTER);
		txtModificaciones.setFont(new Font("Arial", Font.PLAIN, 28));
		txtModificaciones.setEditable(false);
		txtModificaciones.setColumns(10);
		txtModificaciones.setBorder(BorderFactory.createEmptyBorder());
		txtModificaciones.setBackground(SystemColor.menu);
		txtModificaciones.setBounds(10, 11, 475, 31);
		panel_1.add(txtModificaciones);
		
	}
	
	public ArrayList<Ciudades> cargarArrayPaises(String selected){
		ArrayList<Ciudades> arrNuevo = new ArrayList<>();
		BaseDeDatos bd = new BaseDeDatos();
		ResultSet rs=null;
		bd.conectar();
		rs=bd.consultarPaises(selected);
		Ciudades c; 
		try {
			while(rs.next()) {
				c = new Ciudades(rs.getString("continent"),rs.getString("country.name"),rs.getString("language"),rs.getString("district"),rs.getString("city.name"),rs.getInt("city.population"));
				arrNuevo.add(c);
			}
		} catch (Exception e) {
		}finally {
			bd.desconectar();
		}
		
		return arrNuevo;
	}
	
	public ArrayList<Ciudades> cargarArrayCiudades(String selected){
		ArrayList<Ciudades> arrNuevo = new ArrayList<>();
		BaseDeDatos bd = new BaseDeDatos();
		ResultSet rs=null;
		bd.conectar();
		rs=bd.consultarCiudades(selected);
		Ciudades c; 
		try {
			while(rs.next()) {
				c = new Ciudades(rs.getString("continent"),rs.getString("country.name"),rs.getString("language"),rs.getString("district"),rs.getString("city.name"),rs.getInt("city.population"));
				arrNuevo.add(c);
			}
		} catch (Exception e) {
		}finally {
			bd.desconectar();
		}
		
		return arrNuevo;
	}
	
	public ArrayList<Ciudades> cargarArrayDistritos(String selected){
		ArrayList<Ciudades> arrNuevo = new ArrayList<>();
		BaseDeDatos bd = new BaseDeDatos();
		ResultSet rs=null;
		bd.conectar();
		rs=bd.consultarCiudades(selected);
		Ciudades c; 
		try {
			while(rs.next()) {
				c = new Ciudades(rs.getString("continent"),rs.getString("country.name"),rs.getString("language"),rs.getString("district"),rs.getString("city.name"),rs.getInt("city.population"));
				arrNuevo.add(c);
			}
		} catch (Exception e) {
		}finally {
			bd.desconectar();
		}
		
		return arrNuevo;
	}
	
	public ArrayList<Ciudades> cargarArrayPoblacion(Integer selected){
		ArrayList<Ciudades> arrNuevo = new ArrayList<>();
		BaseDeDatos bd = new BaseDeDatos();
		ResultSet rs=null;
		bd.conectar();
		rs=bd.consultarPoblacion(selected);
		Ciudades c; 
		try {
			while(rs.next()) {
				c = new Ciudades(rs.getString("continent"),rs.getString("country.name"),rs.getString("language"),rs.getString("district"),rs.getString("city.name"),rs.getInt("city.population"));
				arrNuevo.add(c);
			}
		} catch (Exception e) {
		}finally {
			bd.desconectar();
		}
		
		return arrNuevo;
	}
}
