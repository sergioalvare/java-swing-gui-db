//Sergio Alvare Pelaez

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;

import java.text.SimpleDateFormat;

public class ConexionBBDDEmpleados
{
	private Connection conexion;
	private String cadenaConexion;
	private String usuario, clave;
	private Statement st;
	private ResultSet resultado;
	
	public ConexionBBDDEmpleados()

	{


	}
	
	//public Connection obtenerConexion() throws SQLException,ClassNotFoundException
	public Connection obtenerConexion()
	{
	try{
	
		Class.forName("com.mysql.jdbc.Driver");
		conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebadb8","root","root");
	}	
	catch(Exception e){
	e.printStackTrace();
	
	}
	
	return conexion;
	}
	
	
	
	
	
	
	public Connection obtenerConexion(String servidor, int puerto, String base_de_datos, String usuario, String password)
	{
	try{
	
		Class.forName("com.mysql.jdbc.Driver");
		String argumentoConexion="jdbc:mysql://"+servidor+":"+String.valueOf(puerto)+"/"+base_de_datos;
		//conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebadb8","root","root");
		conexion=DriverManager.getConnection(argumentoConexion,usuario,password);
	}	
	catch(Exception e){
	e.printStackTrace();
	
	}
	
	return conexion;
	}
	
	
	
	
	
	
	
	
	public void cierraConexion(Connection cn){
	try{
	if(cn!=null && !cn.isClosed()){
	cn.close();
	}
	}
	catch(SQLException e){
	e.printStackTrace();
	}
	}
	
	
	public void updateEmpleadoBBDDEmpleados(String servidor, int puerto, String base_de_datos, String usuario, String password, int valorID_from_tabla, Empleado objetoEmpleado)throws SQLException
	{
		List<Empleado> listaEmpleados=new ArrayList<>();
		//Empleado objetoEmpleado;
		
		
		
		//INSERT INTO Personal (nombre, apellido, departamento, salario, email, fecha_de_entrada, fecha_de_salida)
//VALUES ('John22', 'Doe22', 'Depart22', 1900022, 'john22@example.com', "2012-12-12", "2012-12-12");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String timestamp1_string  = dateFormat.format(timestamp1);
		
	//if(nombre!=null && apellido!=null && departamento!=null && salario!=null && email!=null && fecha_de_entrada!=null && fecha_de_salida!=null &&)
	//{
		

		obtenerConexion();
		System.out.println("Conexion hecha para aniadir fila en la BBDD");
		st=conexion.createStatement();
		st.execute("UPDATE Personal SET nombre=\""+objetoEmpleado.getNombre()+"\", apellido=\""+objetoEmpleado.getApellido()+"\", departamento=\""+objetoEmpleado.getDepartamento()+"\", salario="+objetoEmpleado.getSalario()+", email=\""+objetoEmpleado.getEmail()+"\", fecha_de_entrada=\""+dateFormat.format(objetoEmpleado.getFecha_de_entrada())+"\", fecha_de_salida=\""+objetoEmpleado.getFecha_de_salida()+"\" WHERE ID="+valorID_from_tabla);
		cierraConexion(conexion);
	//}
		
	}
	
	
	public void aniadirEmpleadoBBDDEmpleados(Empleado objetoEmpleado)throws SQLException
	{
		List<Empleado> listaEmpleados=new ArrayList<>();
		//Empleado objetoEmpleado;
		
		
		
		//INSERT INTO Personal (nombre, apellido, departamento, salario, email, fecha_de_entrada, fecha_de_salida)
//VALUES ('John22', 'Doe22', 'Depart22', 1900022, 'john22@example.com', "2012-12-12", "2012-12-12");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String timestamp1_string  = dateFormat.format(timestamp1);
		
	//if(nombre!=null && apellido!=null && departamento!=null && salario!=null && email!=null && fecha_de_entrada!=null && fecha_de_salida!=null &&)
	//{
		String peticion_aniadir="INSERT INTO Personal (nombre, apellido, departamento, salario, email, fecha_de_entrada, fecha_de_salida) VALUES ('"+objetoEmpleado.getNombre()+"', '"+objetoEmpleado.getApellido()+"', '"+objetoEmpleado.getDepartamento()+"', "+Float.toString(objetoEmpleado.getSalario())+", '"+objetoEmpleado.getEmail()+"', '"+dateFormat.format(objetoEmpleado.getFecha_de_entrada())+"', '"+dateFormat.format(objetoEmpleado.getFecha_de_salida())+"')";

		obtenerConexion();
		System.out.println("Conexion hecha para aniadir fila en la BBDD");
		st=conexion.createStatement();
		st.execute(peticion_aniadir);//Coge todos los campos de la tabla
		cierraConexion(conexion);
	//}
		
	}
	
	


	/////////////////////////////
	
	public List<Empleado> leerBBDDEmpleados()throws SQLException
	{
		List<Empleado> listaEmpleados=new ArrayList<>();
		Empleado objetoEmpleado;
		obtenerConexion();
		System.out.println("Conexion hecha");
		st=conexion.createStatement();
		resultado=st.executeQuery("SELECT * FROM Personal");//Coge todos los campos de la tabla
		while(resultado.next())//mientras que haya tuplas
		{
			//System.out.println(""+"\t"+resultado.getInt("id")+"\t"+resultado.getString("nombre")+"\t"+resultado.getString("apellido")+"\t"+resultado.getString("departamento"));//getDate(),getFloat()
			objetoEmpleado=new Empleado();
			objetoEmpleado.setId(resultado.getInt("id"));
			objetoEmpleado.setNombre(resultado.getString("nombre"));
			objetoEmpleado.setApellido(resultado.getString("apellido"));
			objetoEmpleado.setDepartamento(resultado.getString("departamento"));
			objetoEmpleado.setSalario(resultado.getFloat("salario"));
			objetoEmpleado.setEmail(resultado.getString("email"));
			objetoEmpleado.setFecha_de_entrada(resultado.getTimestamp("fecha_de_entrada"));
			objetoEmpleado.setFecha_de_salida(resultado.getTimestamp("fecha_de_salida"));
			System.out.print(objetoEmpleado);
			System.out.println("\n print objetoEmpleado hecho");
			listaEmpleados.add(objetoEmpleado);

		}
		cierraConexion(conexion);
		return listaEmpleados;
	}
	
	
	
	public List<Empleado> leerBBDDEmpleados(String servidor, int puerto, String base_de_datos, String usuario, String password)throws SQLException
	{
		List<Empleado> listaEmpleados=new ArrayList<>();
		Empleado objetoEmpleado;
		obtenerConexion(servidor, puerto, base_de_datos, usuario, password);
		System.out.println("Conexion hecha");
		st=conexion.createStatement();
		resultado=st.executeQuery("SELECT * FROM Personal");//Coge todos los campos de la tabla
		while(resultado.next())//mientras que haya tuplas
		{
			//System.out.println(""+"\t"+resultado.getInt("id")+"\t"+resultado.getString("nombre")+"\t"+resultado.getString("apellido")+"\t"+resultado.getString("departamento"));//getDate(),getFloat()
			objetoEmpleado=new Empleado();
			objetoEmpleado.setId(resultado.getInt("id"));
			objetoEmpleado.setNombre(resultado.getString("nombre"));
			objetoEmpleado.setApellido(resultado.getString("apellido"));
			objetoEmpleado.setDepartamento(resultado.getString("departamento"));
			objetoEmpleado.setSalario(resultado.getFloat("salario"));
			objetoEmpleado.setEmail(resultado.getString("email"));
			objetoEmpleado.setFecha_de_entrada(resultado.getTimestamp("fecha_de_entrada"));
			objetoEmpleado.setFecha_de_salida(resultado.getTimestamp("fecha_de_salida"));
			System.out.print(objetoEmpleado);
			System.out.println("\n print objetoEmpleado hecho");
			listaEmpleados.add(objetoEmpleado);

		}
		cierraConexion(conexion);
		return listaEmpleados;
	}
	
	
	
	
	public void BBDDEmpleados_borrar_referencia(String servidor, int puerto, String base_de_datos, String usuario, String password, int valorID, Empleado emp)throws SQLException
	{
		List<Empleado> listaEmpleados=new ArrayList<>();
		Empleado objetoEmpleado;
		obtenerConexion(servidor, puerto, base_de_datos, usuario, password);
		System.out.println("BBDDEmpleados_borrar_referencia: conexion hecha");
		st=conexion.createStatement();
		//resultado=st.executeQuery("DELETE FROM Personal");
		//resultado=st.executeQuery("WHERE ID="+valorID);//la no presencia de comillas alrededor de valorID es lo que permite
		
		st.execute("DELETE FROM Personal WHERE ID="+valorID);		
		
		//que valorID no sea interpretado como string en este execute
		System.out.println("BBDDEmpleados_borrar_referencia: referencia borrada");
		
		cierraConexion(conexion);
	}
	
	
	
	
	
	
	public List<Empleado> leerBBDDEmpleados_busqueda_parametrizable(String servidor, int puerto, String base_de_datos, String usuario, String password, String opcion_busqueda, String valor_busqueda)throws SQLException
	{
		List<Empleado> listaEmpleados=new ArrayList<>();
		Empleado objetoEmpleado;
		obtenerConexion(servidor, puerto, base_de_datos, usuario, password);
		System.out.println("Conexion hecha");
		st=conexion.createStatement();
		//resultado=st.executeQuery("SELECT * FROM Personal WHERE "+opcion_busqueda+"=\""+valor_busqueda+"\"");
		resultado=st.executeQuery("SELECT * FROM Personal WHERE "+opcion_busqueda+" LIKE \"%"+valor_busqueda+"%\"");
		
		while(resultado.next())//mientras que haya tuplas
		{
			//System.out.println(""+"\t"+resultado.getInt("id")+"\t"+resultado.getString("nombre")+"\t"+resultado.getString("apellido")+"\t"+resultado.getString("departamento"));//getDate(),getFloat()
			objetoEmpleado=new Empleado();
			objetoEmpleado.setId(resultado.getInt("id"));
			objetoEmpleado.setNombre(resultado.getString("nombre"));
			objetoEmpleado.setApellido(resultado.getString("apellido"));
			objetoEmpleado.setDepartamento(resultado.getString("departamento"));
			objetoEmpleado.setSalario(resultado.getFloat("salario"));
			objetoEmpleado.setEmail(resultado.getString("email"));
			objetoEmpleado.setFecha_de_entrada(resultado.getTimestamp("fecha_de_entrada"));
			objetoEmpleado.setFecha_de_salida(resultado.getTimestamp("fecha_de_salida"));
			System.out.print(objetoEmpleado);
			System.out.println("\n print objetoEmpleado hecho");
			listaEmpleados.add(objetoEmpleado);

		}
		cierraConexion(conexion);
		return listaEmpleados;
	}
	
	
	
	
	
}