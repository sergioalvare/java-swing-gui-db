//Sergio Alvare Pelaez

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
//import javax.swing.table.AbstractTableModel;
import javax.swing.table.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.JButton; 
import javax.swing.JPasswordField;

//import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import java.sql.*;

import java.util.*;

import java.text.SimpleDateFormat;

import javax.swing.JCheckBox;

import javax.swing.ImageIcon;

import javax.swing.Box;

import javax.swing.JMenuBar;

import javax.swing.JMenuItem;

import javax.swing.JMenu;

//import java.io.File;
 
public class Ventana extends JPanel
{
	DefaultTableModel dt;
	Empleado empleadoPanel;
	
	JLabel label1;
	JTextField palabra1;
	
	JLabel label2;
	JTextField palabra2;
	
	JLabel label3;
	JTextField palabra3;
	
	JLabel label4;
	JTextField palabra4;
	
	JLabel label5;
	JTextField palabra5;
	
	JLabel label6;
	JTextField palabra6;

	JLabel label7;
	JTextField palabra7;
	
	JCheckBox casilla1;
	
	JButton botonUpdateEmpleado;
	JButton botonNuevoEmpleado;
	JButton botonBorrar;
	JButton bot;
	JButton configConexion;
	
	String servidor;
	int puerto;
	String base_de_datos; 
	String usuario;
	String password;
	
	
	
	
	String buscarPorNombre;
	String buscarPorApellido;
	String buscarPorEmail;
	
	
	
	
	int id_fila_seleccionada;
	
	JTable tabla;
	
	JTextField usuarioField;
	JTextField passField;
	
	JTextField servidorField;
	JTextField puertoField;
	JTextField basededatosField;
	
	
	
	
	JTextField buscarPorNombreField;
	JTextField buscarPorApellidoField;
	JTextField buscarPorDepartamentoField;
	JTextField buscarPorSalarioField;
	JTextField buscarPorEmailField;
	JTextField buscarPorFechaDeEntradaField;
	JTextField buscarPorFechaDeSalidaField;


	
	
	//Constructor:

	public Ventana() //constructor
	{
		setLayout(new BorderLayout());
	
		add(creaPanelOeste(), BorderLayout.WEST);
		add(creaPanelEste(),BorderLayout.EAST);
		
		botonNuevoEmpleado.setEnabled(false);
		botonUpdateEmpleado.setEnabled(false);
		botonBorrar.setEnabled(false);
		bot.setEnabled(false);
		configConexion.setEnabled(false);
		
		
		palabra1.setEnabled(false);
		palabra2.setEnabled(false);
		palabra3.setEnabled(false);
		palabra4.setEnabled(false);
		palabra5.setEnabled(false);
		palabra6.setEnabled(false);
		palabra7.setEnabled(false);
		
		
		
		usuarioField = new JTextField("root");
		passField = new JTextField("root");
		
		servidorField = new JTextField("localhost");
		puertoField = new JTextField("3306");
		basededatosField = new JTextField("pruebadb8");
		
		
		buscarPorNombreField=new JTextField("");
		buscarPorApellidoField=new JTextField("");
		buscarPorDepartamentoField=new JTextField("");
		buscarPorSalarioField=new JTextField("");
		buscarPorEmailField=new JTextField("");
		buscarPorFechaDeEntradaField=new JTextField("");
		buscarPorFechaDeSalidaField=new JTextField("");
		

		
		//ImageIcon icon = Utils.createImageIcon("icono.gif");
		
	}
	


	public JPanel creaPanelOeste() //La ventana consiste en ""paneles anidados"
	{
		//JPanel mipanel=new JPanelOeste();
		dt=new DefaultTableModel();
		
		dt.addColumn("id");
		dt.addColumn("nombre");
		dt.addColumn("apellido");
		dt.addColumn("departamento");
		dt.addColumn("salario");
		dt.addColumn("email");
		dt.addColumn("fecha_de_entrada");
		dt.addColumn("fecha_de_salida");

		tabla=new JTable(dt);
		
		tabla.addMouseListener(new GestorTabla());
		
		//tabla.setPreferredScrollableViewportSize(new Dimension(950, 1000));
		JScrollPane jp=new JScrollPane(tabla);
		JPanel mipan=new JPanel();
		//mipan.setLayout(new GridLayout(1,1));
		//mipan.setPreferredSize(new Dimension(950, 50));
		mipan.add(jp);
		//jp.setSize(new Dimension(600, 400));
		
		//mipan.setLayout(new BorderLayout());
		//mipan.add(jp, BorderLayout.CENTER);
		jp.setPreferredSize(new Dimension(1000, 800));
	
	
		
		//mipan.add(new JButton("pulsar"));
		
		return mipan;
		
	}
	//setmaximumsize o set preferencesize
	//setalligmentx: right, left, center.
	
	public JPanel creaPanelBotones()
	{
		JPanel mipan=new JPanel();
		mipan.setLayout(new GridLayout(6,1));
		
		bot=new JButton("Show all");
		bot.addActionListener(new GestorVer());
		mipan.add(bot);
		
		botonUpdateEmpleado=new JButton("Update reference");
		botonUpdateEmpleado.addActionListener(new GestorUpdate());
		mipan.add(botonUpdateEmpleado);
		
		botonNuevoEmpleado=new JButton("Add reference");
		botonNuevoEmpleado.addActionListener(new GestorAniadir());
		mipan.add(botonNuevoEmpleado);
		
		botonBorrar=new JButton("Delete reference");
		botonBorrar.addActionListener(new GestorBorrarReferencia());
		mipan.add(botonBorrar);
		
		configConexion=new JButton("SQL config");
		configConexion.addActionListener(new GestorSQLconfig());
		mipan.add(configConexion);
		
		
		casilla1= new JCheckBox("Enable buttons");
        //teethButton.setMnemonic(KeyEvent.VK_T);
		casilla1.addActionListener(new GestorEditar());
        casilla1.setSelected(false);
		mipan.add(casilla1);
		
		
		
		
		//mipan.add(bot);
		return mipan;
	}
	
	public JPanel creaPanelTexto()
	{
		JPanel mipan=new JPanel();
		mipan.setLayout(new GridLayout(15,1));
		
		label1=new JLabel("Nombre");
		palabra1=new JTextField("Nombre");
		
		label2=new JLabel("Apellido");
		palabra2=new JTextField("Apellido");
		
		label3=new JLabel("Departamento");
		palabra3=new JTextField("Departamento");
		
		label4=new JLabel("Salario");
		palabra4=new JTextField("1000");
		
		label5=new JLabel("Email");
		palabra5=new JTextField("Email");
		
		Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
		
		//SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		//String string  = dateFormat.format(new Date());
		
		//SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timestamp1_string  = dateFormat.format(timestamp1);

		label6=new JLabel("Fecha entrada");
		palabra6=new JTextField(timestamp1_string);
		
		label7=new JLabel("Fecha salida");
		palabra7=new JTextField(timestamp1_string);
		
		mipan.add(label1);
		mipan.add(palabra1);
		
		mipan.add(label2);
		mipan.add(palabra2);
		
		mipan.add(label3);
		mipan.add(palabra3);
		
		mipan.add(label4);
		mipan.add(palabra4);
		
		mipan.add(label5);
		mipan.add(palabra5);
		
		mipan.add(label6);
		mipan.add(palabra6);
		
		mipan.add(label7);
		mipan.add(palabra7);
		
		return mipan;
	}
	
	public JPanel creaPanelEste()
	{
		JPanel mipan=new JPanel();
		mipan.setLayout(new GridLayout(2,1));
		
		mipan.add(creaPanelTexto(), BorderLayout.NORTH);
		mipan.add(creaPanelBotones(), BorderLayout.SOUTH);
		

		
		return mipan;
	}
	
	public class GestorTabla extends MouseAdapter//clase adaptadora...
	{
		public void mouseReleased(MouseEvent e)
		{
			//Revisar documentacion de DefaultTableModel de JTable
			id_fila_seleccionada=(int)tabla.getValueAt(tabla.getSelectedRow(),0);
			Object aux_nombre=tabla.getValueAt(tabla.getSelectedRow(),1);
			Object aux_apellido=tabla.getValueAt(tabla.getSelectedRow(),2);
			Object aux_departamento=tabla.getValueAt(tabla.getSelectedRow(),3);
			float aux_salario=(float)tabla.getValueAt(tabla.getSelectedRow(),4);
			Object aux_email=tabla.getValueAt(tabla.getSelectedRow(),5);
			Object aux_fecha_de_entrada=tabla.getValueAt(tabla.getSelectedRow(),6);
			Object aux_fecha_de_salida=tabla.getValueAt(tabla.getSelectedRow(),7);
			
			
			palabra1.setText(""+aux_nombre);
			palabra2.setText(""+aux_apellido);
			palabra3.setText(""+aux_departamento);
			palabra4.setText(Float.toString(aux_salario));
			palabra5.setText(""+aux_email);
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			palabra6.setText(dateFormat.format(aux_fecha_de_entrada));
			palabra7.setText(dateFormat.format(aux_fecha_de_salida));
		}
	}
	
	public class GestorVer implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				List<Empleado> listaEmpleados=new ArrayList<>();
				ConexionBBDDEmpleados conexion= new ConexionBBDDEmpleados();
				

				
				usuario=usuarioField.getText();
				password=passField.getText();
				servidor=servidorField.getText();
				base_de_datos=basededatosField.getText(); 
				puerto=Integer.parseInt(puertoField.getText());
				
				
				//listaEmpleados=conexion.leerBBDDEmpleados();
				listaEmpleados=conexion.leerBBDDEmpleados(servidor, puerto, base_de_datos, usuario, password);
				imprimirTabla(listaEmpleados);
				System.out.println("AAAAAA");
				System.out.print(listaEmpleados);
				System.out.println("BBBBBB");
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}
			//Carga, aniadir vectores al addrow
			
			/*
			Empleado emp=new Empleado();
			
			Vector v;
			while(v.next())
			{
				
			}
			*/
			

		}
	}
	
	
	public class GestorSQLconfig implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{

		/*
		usuarioField = new JTextField("root");
		passField = new JTextField("root");
		
		servidorField = new JTextField("localhost");
		puertoField = new JTextField("3306");
		basededatosField = new JTextField("pruebadb8");
		*/
		
		//jdbc:mysql:// localhost : 3306 / pruebadb8","root","root"

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Usuario:"));
		myPanel.add(usuarioField);
		//myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Pass:"));
		myPanel.add(passField);
		
		myPanel.add(new JLabel("Servidor:"));
		myPanel.add(servidorField);
		
		myPanel.add(new JLabel("Base de datos:"));
		myPanel.add(basededatosField);
		
		//myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Puerto:"));
		myPanel.add(puertoField);
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Login", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
		 System.out.println("Usuario: " + usuarioField.getText());
		 System.out.println("Pass: " + passField.getText());
		 System.out.println("Servidor: " + servidorField.getText());
		 System.out.println("Base de datos: " + basededatosField.getText());
		 System.out.println("Puerto: " + Integer.parseInt(puertoField.getText()));
		 
		 
		 usuario=usuarioField.getText();
		 password=passField.getText();
		 servidor=servidorField.getText();
		 base_de_datos=basededatosField.getText(); 
		 puerto=Integer.parseInt(puertoField.getText());
		
		 

		}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
		public class GestorUpdate implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(palabra1.getText().trim().length()==0 || palabra2.getText().trim().length()==0 || palabra3.getText().trim().length()==0 || 
			palabra4.getText().trim().length()==0 || palabra5.getText().trim().length()==0 || palabra6.getText().trim().length()==0 ||
			palabra7.getText().trim().length()==0)
			{
				//int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Save your Previous Note First?","Warning",dialogButton);
				JOptionPane.showMessageDialog(null, "All fields are required", "Incomplete data", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				try
			{
				List<Empleado> listaEmpleados=new ArrayList<>();
				ConexionBBDDEmpleados conexion= new ConexionBBDDEmpleados();
				
				Empleado empleadoPanel=new Empleado();
				empleadoPanel.setNombre(palabra1.getText());
				empleadoPanel.setApellido(palabra2.getText());
				empleadoPanel.setDepartamento(palabra3.getText());
				//empleadoPanel.setSalario(Float.valueOf(palabra4.getText()));//Float.valueOf(String)
				//empleadoPanel.setSalario(palabra4.getText());
				//float f = Float.parseFloat("25");
				//String s = Float.toString(25.0f);
				empleadoPanel.setSalario(Float.parseFloat(palabra4.getText()));
				empleadoPanel.setEmail(palabra5.getText());
				empleadoPanel.setFecha_de_entrada(Timestamp.valueOf(palabra6.getText()));
				empleadoPanel.setFecha_de_salida(Timestamp.valueOf(palabra7.getText()));
				
				
				
				//int valorID=(int)tabla.getValueAt(tabla.getSelectedRow(),0);
				//conexion.updateEmpleadoBBDDEmpleados(empleadoPanel);//Recibe como parametro un objeto Empleado
				conexion.updateEmpleadoBBDDEmpleados(servidor, puerto, base_de_datos, usuario, password, id_fila_seleccionada, empleadoPanel);

				//Debe calcular el id siguiente al del ultimo empleado en la base de datos,
				//por tanto puede ser util reaprovechar la funcion leerBBDDEmpleados anidandola en aniadirEmpleadoBBDDEmpleados.
				//Escribe en la BBDD el objeto empleadoPanel
				
				
				
				usuario=usuarioField.getText();
				password=passField.getText();
				servidor=servidorField.getText();
				base_de_datos=basededatosField.getText(); 
				puerto=Integer.parseInt(puertoField.getText());
				listaEmpleados=conexion.leerBBDDEmpleados(servidor, puerto, base_de_datos, usuario, password);
				//listaEmpleados=conexion.leerBBDDEmpleados();
				imprimirTabla(listaEmpleados);
				System.out.println("AAAAAA");
				System.out.print(listaEmpleados);
				System.out.println("BBBBBB");
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}
			//Carga, aniadir vectores al addrow
			
			/*
			Empleado emp=new Empleado();
			
			Vector v;
			while(v.next())
			{
				
			}
			*/
			}
			
			

		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public class GestorAniadir implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(palabra1.getText().trim().length()==0 || palabra2.getText().trim().length()==0 || palabra3.getText().trim().length()==0 || 
			palabra4.getText().trim().length()==0 || palabra5.getText().trim().length()==0 || palabra6.getText().trim().length()==0 ||
			palabra7.getText().trim().length()==0)
			{
				//int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Save your Previous Note First?","Warning",dialogButton);
				JOptionPane.showMessageDialog(null, "All fields are required", "Incomplete data", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				try
			{
				List<Empleado> listaEmpleados=new ArrayList<>();
				ConexionBBDDEmpleados conexion= new ConexionBBDDEmpleados();
				
				Empleado empleadoPanel=new Empleado();
				empleadoPanel.setNombre(palabra1.getText());
				empleadoPanel.setApellido(palabra2.getText());
				empleadoPanel.setDepartamento(palabra3.getText());
				empleadoPanel.setSalario(Float.parseFloat(palabra4.getText()));
				empleadoPanel.setEmail(palabra5.getText());
				empleadoPanel.setFecha_de_entrada(Timestamp.valueOf(palabra6.getText()));
				empleadoPanel.setFecha_de_salida(Timestamp.valueOf(palabra7.getText()));
				//empleadoPanel.setSalario(Float.valueOf(palabra4.getText()));//Float.valueOf(String)
				//empleadoPanel.setSalario(palabra4.getText());
				//float f = Float.parseFloat("25");
				//String s = Float.toString(25.0f);

				
				
				
				
				
				conexion.aniadirEmpleadoBBDDEmpleados(empleadoPanel);//Recibe como parametro un objeto Empleado
				//Debe calcular el id siguiente al del ultimo empleado en la base de datos,
				//por tanto puede ser util reaprovechar la funcion leerBBDDEmpleados anidandola en aniadirEmpleadoBBDDEmpleados.
				//Escribe en la BBDD el objeto empleadoPanel
				
				//listaEmpleados=conexion.leerBBDDEmpleados();
				usuario=usuarioField.getText();
				password=passField.getText();
				servidor=servidorField.getText();
				base_de_datos=basededatosField.getText(); 
				puerto=Integer.parseInt(puertoField.getText());
				listaEmpleados=conexion.leerBBDDEmpleados(servidor, puerto, base_de_datos, usuario, password);
				imprimirTabla(listaEmpleados);
				System.out.println("AAAAAA");
				System.out.print(listaEmpleados);
				System.out.println("BBBBBB");
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}
			//Carga, aniadir vectores al addrow
			
			/*
			Empleado emp=new Empleado();
			
			Vector v;
			while(v.next())
			{
				
			}
			*/
			}
			
			

		}
	}
	
	
	
	
	public class GestorBorrarReferencia implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				List<Empleado> listaEmpleados=new ArrayList<>();
				ConexionBBDDEmpleados conexion= new ConexionBBDDEmpleados();
				int valorID=(int)tabla.getValueAt(tabla.getSelectedRow(),0);
				
				usuario=usuarioField.getText();
				password=passField.getText();
				servidor=servidorField.getText();
				base_de_datos=basededatosField.getText(); 
				puerto=Integer.parseInt(puertoField.getText());
				
				Empleado empleadoPanel=new Empleado();
				empleadoPanel.setNombre(palabra1.getText());
				empleadoPanel.setApellido(palabra2.getText());
				empleadoPanel.setDepartamento(palabra3.getText());
				empleadoPanel.setSalario(Float.parseFloat(palabra4.getText()));
				empleadoPanel.setEmail(palabra5.getText());
				empleadoPanel.setFecha_de_entrada(Timestamp.valueOf(palabra6.getText()));
				empleadoPanel.setFecha_de_salida(Timestamp.valueOf(palabra7.getText()));
				
				
				conexion.BBDDEmpleados_borrar_referencia(servidor, puerto, base_de_datos, usuario, password, valorID, empleadoPanel);
				//	public void BBDDEmpleados_borrar_referencia(String servidor, int puerto, String base_de_datos, String usuario, String password, int valorID)throws SQLException

				System.out.println("Referencia borrada");
				
				conexion= new ConexionBBDDEmpleados();
				listaEmpleados=conexion.leerBBDDEmpleados();
				imprimirTabla(listaEmpleados);
				System.out.print(listaEmpleados);
				System.out.println("Tabla reimpresa");
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}
			//Carga, aniadir vectores al addrow
			
			/*
			Empleado emp=new Empleado();
			
			Vector v;
			while(v.next())
			{
				
			}
			*/
			

		}
	}
	
	
	
	
	
	
	
	public class GestorEditar implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{

		if(casilla1.isSelected()==true)
		{
			//Habilitar los botones y los campos y poner en blanco los campos
			System.out.println("Checkbox marcado");
			botonNuevoEmpleado.setEnabled(true);
			botonUpdateEmpleado.setEnabled(true);
			botonBorrar.setEnabled(true);
			bot.setEnabled(true);
			configConexion.setEnabled(true);
			
			palabra1.setEnabled(true);
			palabra2.setEnabled(true);
			palabra3.setEnabled(true);
			palabra4.setEnabled(true);
			palabra5.setEnabled(true);
			palabra6.setEnabled(true);
			palabra7.setEnabled(true);
		}
		else
		{
			//Deshabilitar los botones y los campos y poner en gris los campos
			System.out.println("Checkbox desmarcado");
			botonNuevoEmpleado.setEnabled(false);
			botonUpdateEmpleado.setEnabled(false);
			botonBorrar.setEnabled(false);
			bot.setEnabled(false);
			configConexion.setEnabled(false);
			
			palabra1.setEnabled(false);
			palabra2.setEnabled(false);
			palabra3.setEnabled(false);
			palabra4.setEnabled(false);
			palabra5.setEnabled(false);
			palabra6.setEnabled(false);
			palabra7.setEnabled(false);
		}
			

		}
	}
	
	
	
	
	
	public class GestorMenuItem_m1 implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
			try
			{

				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("Nombre:"));
				
				buscarPorNombreField.setPreferredSize( new Dimension( 200, 24 ) );
				myPanel.add(buscarPorNombreField);
					
				int result = JOptionPane.showConfirmDialog(null, myPanel, "Buscar por nombre", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) 
				{
					
					 System.out.println("Busqueda por Nombre: " + buscarPorNombreField.getText());
					 
					 /*
					 System.out.println("Puerto: " + Integer.parseInt(puertoField.getText()));
					 */
						 
					 buscarPorNombre=buscarPorNombreField.getText();
					 
					 //leerBBDDEmpleados_busqueda_parametrizable("nombre", buscarPorNombre);
					//listaEmpleados=conexion.leerBBDDEmpleados(servidor, puerto, base_de_datos, usuario, password);
					//imprimirTabla(listaEmpleados);
					
					
					usuario=usuarioField.getText();
					password=passField.getText();
					servidor=servidorField.getText();
					base_de_datos=basededatosField.getText(); 
					puerto=Integer.parseInt(puertoField.getText());
					
					
					
					List<Empleado> listaEmpleados=new ArrayList<>();
					ConexionBBDDEmpleados conexion= new ConexionBBDDEmpleados();
					listaEmpleados=conexion.leerBBDDEmpleados_busqueda_parametrizable(servidor, puerto, base_de_datos, usuario, password, "nombre", buscarPorNombre);
					imprimirTabla(listaEmpleados);
				}
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}

		}
	}
	
	
	public class GestorMenuItem_m2 implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
			try
			{

				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("Apellido:"));
				
				buscarPorApellidoField.setPreferredSize( new Dimension( 200, 24 ) );
				myPanel.add(buscarPorApellidoField);
					
				int result = JOptionPane.showConfirmDialog(null, myPanel, "Buscar por apelido", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) 
				{
					
					 System.out.println("Busqueda por Apellido: " + buscarPorApellidoField.getText());
					 
					 /*
					 System.out.println("Puerto: " + Integer.parseInt(puertoField.getText()));
					 */
						 
					 buscarPorApellido=buscarPorApellidoField.getText();
					 
					 //leerBBDDEmpleados_busqueda_parametrizable("nombre", buscarPorNombre);
					//listaEmpleados=conexion.leerBBDDEmpleados(servidor, puerto, base_de_datos, usuario, password);
					//imprimirTabla(listaEmpleados);
					
					
					usuario=usuarioField.getText();
					password=passField.getText();
					servidor=servidorField.getText();
					base_de_datos=basededatosField.getText(); 
					puerto=Integer.parseInt(puertoField.getText());
					
					
					
					List<Empleado> listaEmpleados=new ArrayList<>();
					ConexionBBDDEmpleados conexion= new ConexionBBDDEmpleados();
					listaEmpleados=conexion.leerBBDDEmpleados_busqueda_parametrizable(servidor, puerto, base_de_datos, usuario, password, "apellido", buscarPorApellido);
					imprimirTabla(listaEmpleados);
				}
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}

		}
	}
	
	
	public class GestorMenuItem_m5 implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
			try
			{

				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("Email:"));
				
				buscarPorEmailField.setPreferredSize( new Dimension( 200, 24 ) );
				myPanel.add(buscarPorEmailField);
					
				int result = JOptionPane.showConfirmDialog(null, myPanel, "Buscar por email", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) 
				{
					
					 System.out.println("Busqueda por Email: " + buscarPorEmailField.getText());
					 
					 /*
					 System.out.println("Puerto: " + Integer.parseInt(puertoField.getText()));
					 */
						 
					 buscarPorEmail=buscarPorEmailField.getText();
					 
					 //leerBBDDEmpleados_busqueda_parametrizable("nombre", buscarPorNombre);
					//listaEmpleados=conexion.leerBBDDEmpleados(servidor, puerto, base_de_datos, usuario, password);
					//imprimirTabla(listaEmpleados);
					
					
					usuario=usuarioField.getText();
					password=passField.getText();
					servidor=servidorField.getText();
					base_de_datos=basededatosField.getText(); 
					puerto=Integer.parseInt(puertoField.getText());
					
					
					
					List<Empleado> listaEmpleados=new ArrayList<>();
					ConexionBBDDEmpleados conexion= new ConexionBBDDEmpleados();
					listaEmpleados=conexion.leerBBDDEmpleados_busqueda_parametrizable(servidor, puerto, base_de_datos, usuario, password, "email", buscarPorEmail);
					imprimirTabla(listaEmpleados);
				}
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}

		}
	}
	
	
	
	
	
	void imprimirTabla(List<Empleado> listaEmpleados)
	{
		System.out.println("\n lanzado imprimir tabla");
		
		System.out.println("/////");
		System.out.print(listaEmpleados);
		System.out.println("/////");
		
		//SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		while(dt.getRowCount()>0)
		{
			dt.removeRow(0);
		}
		
		for(Empleado i:listaEmpleados)
		{
			System.out.println("\n lanzado imprimir tabla bucle");
			Object[] fila=new Object[8];
			/*
			fila[0]="prueba1";
			fila[1]="prueba2";
			fila[2]="aa";
			fila[3]="bb";
			fila[4]="cc";
			fila[5]="dd";
			fila[6]="ee";
			fila[7]="ff";
			*/
			
			/*
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String string  = dateFormat.format(new Date());
			System.out.println(string);
			*/
			
			fila[0]=i.getId();
			fila[1]=i.getNombre();
			fila[2]=i.getApellido();
			fila[3]=i.getDepartamento();
			fila[4]=i.getSalario();
			fila[5]=i.getEmail();
			fila[6]=i.getFecha_de_entrada();
			fila[7]=i.getFecha_de_salida();
			dt.addRow(fila);
		}

		
	}
	
	
	
	
	
	public JMenuBar createMenuBar() {
		
		//https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/MenuLookDemoProject/src/components/MenuLookDemo.java
		//Cuidado con el tema static, podria quitarse y hacerse como en el ejemplo... revisarlo
		
		JMenu m;
		JMenuBar mb;
		JMenuItem m1, m2, m3, m4, m5, m6, m7;
		
		mb=new JMenuBar();
		m= new JMenu("Buscar");
		m1= new JMenuItem("Buscar por nombre");
		m2= new JMenuItem("Buscar por apellido");
		//m3= new JMenuItem("Buscar por departamento");
		//m4= new JMenuItem("Buscar por salario");
		m5= new JMenuItem("Buscar por email");
		//m6= new JMenuItem("Buscar por fecha de entrada");
		//m7= new JMenuItem("Buscar por fecha de salida");
		
		m1.addActionListener(new GestorMenuItem_m1());
		m2.addActionListener(new GestorMenuItem_m2());
		m5.addActionListener(new GestorMenuItem_m5());
		
		m.add(m1);
		m.add(m2);
		//m.add(m3);
		//m.add(m4);
		m.add(m5);
		//m.add(m6);
		//m.add(m7);
		
		mb.add(m);
		
 /*
        //Create the menu bar.
        menuBar = new JMenuBar();
 
        //Build the first menu.
        menu = new JMenu("A Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);
 
       
 
        return menuBar;
		*/
		return mb;
    }
	
	
	
	
	public static void main(String[] args)
	{
		
		
		//Construyendo desde fuera hacia adentro:
		
		//Crear JTable:
		JFrame Marco = new JFrame("Interfaz");
		//Marco.setIconImage(new ImageIcon("tux.png").getImage());
		Marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Ventana Ventana1=new Ventana();
		//meter los menubar menuitems
		Marco.getContentPane().add(Ventana1);
		
		//////////
		Marco.setJMenuBar(Ventana1.createMenuBar());
		//////////
		
		Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
		Marco.setSize(dim.width,dim.height);
		//Ventana1.setLocation(,);
		
		//Clase abstracta toolkit


		//Conexion a la base de datos para modificar la tabla Tabla1:

	    //Marco.setIconImage(new ImageIcon("tux.png").getImage());
		Marco.setVisible(true);

		
	}
	


	
}


  