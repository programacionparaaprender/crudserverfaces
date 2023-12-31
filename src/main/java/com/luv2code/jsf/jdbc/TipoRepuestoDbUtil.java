package com.luv2code.jsf.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.LinkedList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class TipoRepuestoDbUtil {

	private static TipoRepuestoDbUtil instance;
	private DataSource dataSource;
	//private String jndiName = "java:comp/env/jdbc/student_tracker";
	private String jndiName = "java:comp/env/jdbc/student_tracker_sqlserver";
	
	
	public static TipoRepuestoDbUtil getInstance() throws Exception {
		if (instance == null) {
			instance = new TipoRepuestoDbUtil();
		}
		
		return instance;
	}
	
	private TipoRepuestoDbUtil() throws Exception {		
		dataSource = getDataSource();
	}

	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();
		
		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		
		return theDataSource;
	}
	
	public TipoRepuesto getTipoRepuesto(int pkId) throws Exception {

		List<TipoRepuesto> tiporepuestos = new LinkedList<TipoRepuesto>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from tipo_repuesto where pk_id=" + pkId;

			myStmt = myConn.createStatement();

			
			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("pk_id");
				String nombre = myRs.getString("nombre");
				String descripcion = myRs.getString("descripcion");
		        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
		        String dateString = myRs.getString("fecha_registro");
		        try {
		        	Date fecha_registro = parser.parse(dateString);
		         // create new student object
					TipoRepuesto tempStudent = new TipoRepuesto(id, nombre, descripcion,
							fecha_registro);
		         // add it to the list of students
					tiporepuestos.add(tempStudent);
		        } catch (ParseException e) {
		            e.printStackTrace();
		        }

				
			}
			TipoRepuesto tiporepuesto = tiporepuestos.get(0);
			return tiporepuesto;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public List<TipoRepuesto> getTipoRepuestos() throws Exception {

		List<TipoRepuesto> tiporepuestos = new LinkedList<TipoRepuesto>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from tipo_repuesto order by nombre";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("pk_id");
				String nombre = myRs.getString("nombre");
				String descripcion = myRs.getString("descripcion");
		        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
		        String dateString = myRs.getString("fecha_registro");
		        try {
		        	Date fecha_registro = parser.parse(dateString);
		         // create new student object
					TipoRepuesto tempStudent = new TipoRepuesto(id, nombre, descripcion,
							fecha_registro);
		         // add it to the list of students
					tiporepuestos.add(tempStudent);
		        } catch (ParseException e) {
		            e.printStackTrace();
		        }

				
			}
			
			return tiporepuestos;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
		
	public List<Student> getStudents() throws Exception {

		List<Student> students = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from student order by last_name";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");

				// create new student object
				Student tempStudent = new Student(id, firstName, lastName,
						email);

				// add it to the list of students
				students.add(tempStudent);
			}
			
			return students;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}

	public void eliminarTipoRepuesto(int pkId) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			myConn = getConnection();
			String sql = "delete from tipo_repuesto  WHERE pk_id=" + pkId;
			myStmt = myConn.prepareStatement(sql);
			myStmt.execute();			
		}
		finally {
			close (myConn, myStmt);
		}
	}
	
	public void actualizarTipoRepuesto(TipoRepuesto tipoRepuesto) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "UPDATE tipo_repuesto SET nombre=?, descripcion=?  WHERE pk_id=?";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, tipoRepuesto.getNombre());
			myStmt.setString(2, tipoRepuesto.getDescripcion());
			myStmt.setInt(3, tipoRepuesto.getPkId());
			myStmt.execute();			
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public void addTipoRepuesto(TipoRepuesto tipoRepuesto) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "insert into tipo_repuesto (nombre, descripcion, fecha_registro) values (?, ?, getdate())";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, tipoRepuesto.getNombre());
			myStmt.setString(2, tipoRepuesto.getDescripcion());
			
			myStmt.execute();			
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public void addStudent(Student theStudent) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "insert into student (first_name, last_name, email) values (?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			
			myStmt.execute();			
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public Student getStudent(int studentId) throws Exception {
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from student where id=?";

			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, studentId);
			
			myRs = myStmt.executeQuery();

			Student theStudent = null;
			
			// retrieve data from result set row
			if (myRs.next()) {
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");

				theStudent = new Student(id, firstName, lastName,
						email);
			}
			else {
				throw new Exception("Could not find student id: " + studentId);
			}

			return theStudent;
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public void updateStudent(Student theStudent) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "update student "
						+ " set first_name=?, last_name=?, email=?"
						+ " where id=?";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			myStmt.setInt(4, theStudent.getId());
			
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public void deleteStudent(int studentId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "delete from student where id=?";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, studentId);
			
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}		
	}	
	
	private Connection getConnection() throws Exception {

		Connection theConn = dataSource.getConnection();
		
		return theConn;
	}
	
	private void close(Connection theConn, Statement theStmt) {
		close(theConn, theStmt, null);
	}
	
	private void close(Connection theConn, Statement theStmt, ResultSet theRs) {

		try {
			if (theRs != null) {
				theRs.close();
			}

			if (theStmt != null) {
				theStmt.close();
			}

			if (theConn != null) {
				theConn.close();
			}
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}	
}
