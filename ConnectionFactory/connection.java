package ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {
	// toda vez que chamado, executar� todo o comando do bloco
	public Connection getConnection() {
		// vari�veis usadas para fazer a conex�o com o Banco de Dados
		String url = "jdbc:postgresql://localhost:5432/loja";
		String user = "postgres";
		String pass = "19981008";
		// tentativa de conex�o
		try {
			// toda vez que executado criar� uma nova conex�o com o banco de dados
			return DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			// Quem importa-lo ter� que tratar esse possivel erro
			throw new RuntimeException();
		}		
	} // fim do bloco getConnection
}
