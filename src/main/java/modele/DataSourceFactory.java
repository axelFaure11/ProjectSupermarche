package modele;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import servlets.DatabaseInitializer;


/**
 *
 * @author rbastide
 */
public class DataSourceFactory {

	public enum DriverType {
		embedded, server
	};
	
	// Choic du type de driver : embedded ou serveur
	private static final DriverType TYPE = DriverType.embedded;
	/**
	 * Renvoie la source de données (server ou embbeded)
	 * @return  la source de données
	 */
	public static DataSource getDataSource() {
		DataSource result;

		switch (TYPE) {
			case server: // Derby mode serveur, doit être démarré indépendamment
				org.apache.derby.jdbc.ClientDataSource ds = new org.apache.derby.jdbc.ClientDataSource();
				ds.setDatabaseName("Supermarche");
				ds.setServerName("localhost");
				// port on which Network Server is listening
				ds.setPortNumber(1527);
				result = ds;
				break;
			default: // Derby mode embedded, démarré automatiquement avec l'application
				org.apache.derby.jdbc.EmbeddedDataSource es = new org.apache.derby.jdbc.EmbeddedDataSource();
				es.setCreateDatabase("create");
				es.setDatabaseName("Supermarche");
				result = es;
		}

		return result;
	}

}
