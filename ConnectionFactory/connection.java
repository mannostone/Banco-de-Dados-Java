package ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {
	// toda vez que chamado, executará todo o comando do bloco
	public Connection getConnection() {
		// variáveis usadas para fazer a conexão com o Banco de Dados
		String url = "jdbc:postgresql://localhost:5432/loja";
		String user = "postgres";
		String pass = "19981008";
		// tentativa de conexão
		try {
			// toda vez que executado criará uma nova conexão com o banco de dados
			return DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			// Quem importa-lo terá que tratar esse possivel erro
			throw new RuntimeException();
		}		
	} // fim do bloco getConnection
}
