package it.corso.java.rubrica.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import it.corso.java.rubrica.model.Contatto;

public class RubricaBusiness {
	
	private Connection con; 
	private static RubricaBusiness rb; 
	
	
	
	public static RubricaBusiness getInstance() {
		if(rb == null) {
			rb = new RubricaBusiness(); 
		}
		return rb; 
	}
	
	
	//Gestione connessione del database
	private Connection getConnection() throws SQLException {
		if(con == null) {
			
			MysqlDataSource dataSource = new MysqlDataSource(); 
			dataSource.setServerName("127.0.0.1");
			dataSource.setPortNumber(3306);
			dataSource.setUser(""); //to complete
			dataSource.setPassword(""); //to complete
			dataSource.setDatabaseName(""); //to complete
			
			con = dataSource.getConnection();
		}
		return con; 
	}
	
	
	//Metodo per aggiungere il contatto nel database.
	public int aggiungiContatto(Contatto c) throws SQLException {
		
		String sql = "INSERT INTO Contatti(nome, cognome, telefono) VALUES(?,?,?)";
		PreparedStatement ps = getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		
		//Ora prevengo l'SQL injection!
		ps.setString(1, c.getNome());
		ps.setString(2, c.getCognome());
		ps.setString(3, c.getTelefono());
		
		ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		
		return rs.getInt(1); 
	}
	
	
	//Metodo per fare la RICERCA dei contatti!
	public List<Contatto> ricercaContatti() throws SQLException{
		String sql = "SELECT id, nome, cognome, telefono FROM contatti";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		List<Contatto> contatti = new ArrayList<Contatto>();
		while(rs.next()) {
			Contatto c = new Contatto(); 
			c.setId(rs.getInt(1));
			c.setNome(rs.getString(2));
			c.setCognome(rs.getString(3));
			c.setTelefono(rs.getString(4));
			
			contatti.add(c); 
		}
		return contatti;
	}

}
