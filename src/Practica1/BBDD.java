package Practica1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BBDD {
	public Connection conexion;

	public BBDD() {
		try {
			this.conexion=DriverManager.getConnection("jdbc:sqlite:sample.db");
			Statement state=conexion.createStatement();
			state.setQueryTimeout(30);
			System.out.println("hecho brutal!");	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void listar()  {//validado
		System.out.println("---------------------------"); 
		String sql= "select * from empleados;";
		Statement sta;
		try {
			sta = conexion.createStatement();
			ResultSet rs = (ResultSet)sta.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getInt(1) +"-"+ rs.getString(2) + "-"+ 
						rs.getString(3) +"-" +rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("---------------------------"); 	
	}
	
	public void actualizar(Empleado emp) {//correcto
		String sql= "update empleados "
				 + " set salari = salari + 10000 "
				 + " where id = ?;";
		PreparedStatement sta;
		try {
			sta = conexion.prepareStatement(sql);
			sta.setInt(1, emp.getId());
			sta.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertar(Empleado emp)  {//validado
		String sql= "insert into empleados (name, apellido, salari) "
					+ " values (?,?,?);";
		PreparedStatement sta;
		try {
			sta = conexion.prepareStatement(sql);
			sta.setString(1, emp.getNombre());
			sta.setString(2, emp.getApellidos());
			sta.setInt(3, emp.getSalario());
			sta.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void borrar(Empleado emp)  {
		String sql= "delete from empleados where id =?;";
		PreparedStatement sta;
		try {
			sta = conexion.prepareStatement(sql);
			sta.setInt(1, emp.getId());
			sta.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
