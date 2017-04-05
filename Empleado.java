//Sergio Alvare Pelaez

import java.sql.*;

class Empleado
{
	private int id;
	private String nombre;
	private String apellido;
	private String departamento;
	private float salario;
	private String email;
	private Timestamp fecha_de_entrada;
	private Timestamp fecha_de_salida;
	
	public Empleado()
	{
	}
	
	public Empleado(int nuevo_id, String nuevo_nombre, String nuevo_apellido, String nuevo_departamento, float nuevo_salario, String nuevo_email, Timestamp nuevo_fecha_de_entrada, Timestamp nuevo_fecha_de_salida)
	{
		this.id=nuevo_id;
		this.nombre=nuevo_nombre;
		this.apellido=nuevo_apellido;
		this.departamento=nuevo_departamento;
		this.salario=nuevo_salario;
		this.email=nuevo_email;
		this.fecha_de_entrada=nuevo_fecha_de_entrada;
		this.fecha_de_salida=nuevo_fecha_de_salida;
		
	}
	
	////////////////////////////////////
	
	public int getId()
	{
		return this.id;
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	public String getApellido()
	{
		return this.apellido;
	}
	
	public String getDepartamento()
	{
		return this.departamento;
	}
	
	public float getSalario()
	{
		return this.salario;
	}
	
	public String getEmail()
	{
		return this.email;
	}
	
	public Timestamp getFecha_de_entrada()
	{
		return this.fecha_de_entrada;
	}
	
	public Timestamp getFecha_de_salida()
	{
		return this.fecha_de_salida;
	}
	
	//////////////////////////////////////////
	
	public void setId(int nuevo_id)
	{
		this.id=nuevo_id;
	}
	
	public void setNombre(String nuevo_nombre)
	{
		this.nombre=nuevo_nombre;
	}
	public void setApellido(String nuevo_apellido)
	{
		this.apellido=nuevo_apellido;
	}
	
	public void setDepartamento(String nuevo_departamento)
	{
		this.departamento=nuevo_departamento;
	}
	
	public void setSalario(float nuevo_salario)
	{
		this.salario=nuevo_salario;
	}
	
	public void setEmail(String nuevo_email)
	{
		this.email=nuevo_email;
	}
	
	public void setFecha_de_entrada(Timestamp nuevo_fecha_de_entrada)
	{
		this.fecha_de_entrada=nuevo_fecha_de_entrada;
	}
	
	public void setFecha_de_salida(Timestamp nuevo_fecha_de_salida)
	{
		this.fecha_de_salida=nuevo_fecha_de_salida;
	}
	
}