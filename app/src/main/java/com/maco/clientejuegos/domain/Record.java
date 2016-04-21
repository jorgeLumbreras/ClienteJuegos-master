package com.maco.clientejuegos.domain;

public class Record {

	private int tiempo;
	private String email;
	
	public Record(String email, int tiempo) {
		// TODO Auto-generated constructor stub
		this.tiempo=tiempo;
		this.email=email;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
