package modele.DAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.DriverManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author rebecca
 */
public class DB {
    /**
	 * Pour la connexion à la base de donnée
	 */
	private Connection cnx;
	/**
	 * Récupère les résultats des requetes type Select
	 */
	private ResultSet rs;
	/**
	 * Pour préparer les requetes de type Insert
	 */
	private PreparedStatement pstm;
	/**
	 * Récupérer les résultats de type insert, update, delete
	 */
	private int ok;
	

	private void getConnection()
	{
		String url = "jdbc:mysql://localhost:3306/tpjdbcConcours";
		String user = "root";
		String password = "root";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			this.cnx = DriverManager.getConnection(url, user, password);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void initPrepare(String sql)
	{
		try
		{
			getConnection();
			this.pstm = this.cnx.prepareStatement(sql);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public int executeMaj()
	{
		try
		{
			this.ok = this.pstm.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return this.ok;
	}
	
	public ResultSet executeSelect()
	{
		try
		{
			this.rs = pstm.executeQuery();
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return this.rs;
	}
	
	public PreparedStatement getPstm()
	{
		return this.pstm;
	}
	
	public void closeConnection()
	{
		try
		{
			if(this.cnx != null)
				this.cnx.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
