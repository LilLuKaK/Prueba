package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Tabla extends JFrame {

	private JPanel contentPane;

	public static void cargaVentana(JFrame tabla) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tabla.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Tabla() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 1024);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnOpcion = new JMenu("Opcion");
		menuBar.add(mnOpcion);
		
		JMenuItem consultar = new JMenuItem("Consultar");
		consultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Consulta c = new Consulta();
				nuevoPanel(c);
			}
		});
		mnOpcion.add(consultar);
		
		JMenuItem insertar = new JMenuItem("Insertar");
		insertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Insertar i = new Insertar();
				nuevoPanel(i);
			}
		});
		mnOpcion.add(insertar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("WELLCOME");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 99));
		contentPane.add(lblNewLabel, BorderLayout.CENTER);
		
	}
	
	public void nuevoPanel(JPanel panelActual) {
		contentPane.removeAll();
		contentPane.add(panelActual);
		contentPane.repaint();
		contentPane.revalidate();
	}
}




//package vista;
//
//import java.awt.EventQueue;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Iterator;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import javax.swing.table.DefaultTableModel;
//
//import vista.Consultar;
//import modelo.BaseDeDatos;
//import controlador.Principal;
//import modelo.Ciudades;
//
//import javax.swing.JScrollPane;
//import javax.swing.BorderFactory;
//import javax.swing.JComboBox;
//import javax.swing.JTable;
//import javax.swing.JTextField;
//import java.awt.event.ItemListener;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.ItemEvent;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.Font;
//import javax.swing.SwingConstants;
//import java.awt.Color;
//import javax.swing.JMenuBar;
//import javax.swing.JMenu;
//import javax.swing.JMenuItem;
//
//public class Tabla extends JFrame {
//
//	private JPanel contentPane;
//	private JTable TablaCiudades;
//	private JTextField txPrimero;
//	private JTextField TxSegundo;
//	private JTextField TxTercero;
//	private JComboBox cmbPaises;
//	private JComboBox cmbDistritos;
//	private JScrollPane scrollPane;
//	DefaultTableModel modelotabla = new DefaultTableModel();
//	private JTextField txtDistritos;
//	private JTextField txtPaises;
//	private JPanel panel_1;
//	private JMenuBar menuBar;
//
//	public static void cargaVentana(Tabla frame) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					frame.cargaPaises();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	public Tabla() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 1024, 720);
//		
//		menuBar = new JMenuBar();
//		setJMenuBar(menuBar);
//		
//		JMenu menu = new JMenu("Opciones");
//		menuBar.add(menu);
//		
//		JMenuItem consultar = new JMenuItem("Consultar");
//		consultar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				Consultar c = new Consultar();
//				nuevoPanel(c);
//			}
//		});
//		menu.add(consultar);
//		
//		JMenuItem insertar = new JMenuItem("Insertar");
//		menu.add(insertar);
//		contentPane = new JPanel();
//		contentPane.setBackground(new Color(255, 255, 255));
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//		
//		scrollPane = new JScrollPane();
//		scrollPane.setBounds(10, 324, 988, 346);
//		contentPane.add(scrollPane);
//		
//		TablaCiudades = new JTable();
//		TablaCiudades.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				txPrimero.setText("");
//				TxSegundo.setText("");
//				TxTercero.setText("");
//				txPrimero.setText(TablaCiudades.getValueAt(TablaCiudades.getSelectedRow(),0).toString());
//				TxSegundo.setText(TablaCiudades.getValueAt(TablaCiudades.getSelectedRow(),1).toString());
//				TxTercero.setText(TablaCiudades.getValueAt(TablaCiudades.getSelectedRow(),2).toString());
//			}
//		});
//		scrollPane.setViewportView(TablaCiudades);
//		
//		txPrimero = new JTextField();
//		txPrimero.setBounds(912, 73, 86, 20);
//		contentPane.add(txPrimero);
//		txPrimero.setColumns(10);
//		
//		TxSegundo = new JTextField();
//		TxSegundo.setColumns(10);
//		TxSegundo.setBounds(912, 42, 86, 20);
//		contentPane.add(TxSegundo);
//		
//		TxTercero = new JTextField();
//		TxTercero.setColumns(10);
//		TxTercero.setBounds(912, 11, 86, 20);
//		contentPane.add(TxTercero);
//		
//		JPanel panel = new JPanel();
//		panel.setBounds(10, 11, 484, 306);
//		contentPane.add(panel);
//		panel.setLayout(null);
//		
//		cmbPaises = new JComboBox();
//		cmbPaises.setBackground(new Color(255, 255, 255));
//		cmbPaises.setBounds(95, 224, 117, 31);
//		panel.add(cmbPaises);
//		
//		cmbDistritos = new JComboBox();
//		cmbDistritos.setBackground(new Color(255, 255, 255));
//		cmbDistritos.setBounds(95, 266, 117, 31);
//		cmbDistritos.setBorder(BorderFactory.createEmptyBorder());
//		panel.add(cmbDistritos);
//		
//		txtDistritos = new JTextField();
//		txtDistritos.setBounds(10, 266, 86, 31);
//		panel.add(txtDistritos);
//		txtDistritos.setBackground(new Color(240, 240, 240));
//		txtDistritos.setEditable(false);
//		txtDistritos.setFont(new Font("Arial", Font.PLAIN, 20));
//		txtDistritos.setText("Distritos:");
//		txtDistritos.setBorder(BorderFactory.createEmptyBorder());
//		txtDistritos.setColumns(10);
//		
//		txtPaises = new JTextField();
//		txtPaises.setBounds(10, 224, 86, 31);
//		panel.add(txtPaises);
//		txtPaises.setText("Paises:");
//		txtPaises.setFont(new Font("Arial", Font.PLAIN, 20));
//		txtPaises.setEditable(false);
//		txtPaises.setColumns(10);
//		txtPaises.setBorder(BorderFactory.createEmptyBorder());
//		txtPaises.setBackground(new Color(240, 240, 240));
//		
//		panel_1 = new JPanel();
//		panel_1.setBounds(514, 11, 484, 306);
//		contentPane.add(panel_1);
//		panel_1.setLayout(null);
//		cmbDistritos.addItemListener(new ItemListener() {
//			public void itemStateChanged(ItemEvent e) {
//				
//				try {
//					Principal p = new Principal();
//					modelotabla.setRowCount(0);
//					
//					modelotabla.setColumnIdentifiers(new Object[] {
//							"Continente",
//							"Pais",
//							"Idioma",
//							"Distrito",
//							"Ciudad",
//							"Poblacion"
//					});
//					
//					TablaCiudades.setModel(modelotabla);
//					for (Ciudades c : p.cargarArray(cmbDistritos.getSelectedItem().toString())) {
//						
//		
//						modelotabla.addRow(new Object[] {
//								c.getContinente(),
//								c.getPais(),
//								c.getIdioma(),
//								c.getDistrito(),
//								c.getCiudad(),
//								c.getPoblacion()
//						});
//					}
//				} catch (NullPointerException e2) {
//
//				}
//				
//			}
//		});
//		cmbPaises.addItemListener(new ItemListener() {
//			public void itemStateChanged(ItemEvent e) {
//				BaseDeDatos bd = new BaseDeDatos();
//				cmbDistritos.removeAllItems();
//				ResultSet rs=null;
//				rs = bd.consultarDistritos(cmbPaises.getSelectedItem().toString());
//				bd.conectar();
//				try {
//					while(rs.next()) {
//						cmbDistritos.addItem(rs.getString("district"));
//					}
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}finally {
//					bd.desconectar();
//				}
//			}
//		});
//	}
//	
//	public void nuevoPanel(JPanel panelActual) {
//		contentPane.removeAll();
//		contentPane.add(panelActual);
//		contentPane.repaint();
//		contentPane.revalidate();
//	}
//	
//	public ArrayList<Ciudades> cargaPaises(){
//		ArrayList<Ciudades> nuevo = new ArrayList<>();
//		BaseDeDatos bd = new BaseDeDatos();
//		bd.conectar();
//		ResultSet rs;
//		rs = bd.consultar();
//		try {
//			while(rs.next()) {
//				cmbPaises.addItem(rs.getString("name"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			bd.desconectar();
//		}
//		cmbPaises.setSelectedItem("Spain");
//		return nuevo;
//	}
//}
