package it.corso.java.rubrica.model;

public class Contatto {
	
	private int id; 
	private String nome; 
	private String cognome; 
	private String telefono;
	
	//Costruttore
	public Contatto() {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
	}

	//Get e Set
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	} 
	
	
	
	

}
