package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;

import it.polito.tdp.poweroutages.model.Nerc;

public class TestPowerOutagesDAO {

	public static void main(String[] args) {
		
		try {
			Connection connection = ConnectDB.getConnection();
			connection.close();
			System.out.println("Connection Test PASSED");
			
			PowerOutageDAO dao = new PowerOutageDAO() ;
			Nerc nar=new Nerc(3,"MAAC");
			System.out.println(dao.lista(nar)) ;
			
			dao.lista(nar);
		} catch (Exception e) {
			System.err.println("Test FAILED");
		}

	}

}
