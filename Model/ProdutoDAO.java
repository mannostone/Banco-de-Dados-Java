package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ConnectionFactory.connection;

public class ProdutoDAO {
	// variável que vai fazer a conexão com o banco usando a classe connection
	private Connection con;
	
	// Construtor que sempre cria uma nova conexão
	public ProdutoDAO() {this.con = new connection().getConnection();}
	
	// CRUD
	// Inserção
	public void adiciona(Produto produto) {
		// Forma de inserção no banco informando os campos
		// onde "?" serão os campos informados mais abaixo
		String sql = "insert into produto (codigo, descricao, quantidade, preco, fabricante) values (?,?,?,?,?)";
		
		try {
			// forma de preparar a conexão
			PreparedStatement stmt = con.prepareStatement(sql);
			
			// Substituição dos "?" por valores na ordem descrita na linha 22
			stmt.setInt(1, produto.getCodigo());
			stmt.setString(2, produto.getDescricao());
			stmt.setInt(3, produto.getQuantidade());
			stmt.setDouble(4, produto.getPreco());
			stmt.setString(5, produto.getFabricante());
			
			//Após a alteração dos campos, ele será executado com
			stmt.execute();
			
			// fechando as instâncias abertas
			stmt.close();
			con.close();
			
			// Apenas uma forma de confirmação
			System.out.println("Produto registrado.");
			
		} // fim do bloco try
		
		// catch
		catch (SQLException e) {throw new RuntimeException(e);}
		
	} // fim do método de adição de dados
	
	// alteração
	public void altera (Produto produto) {
		String sql = "update produto set descricao = ?, quantidade = ?, preco = ?, fabricante = ? where codigo = ?";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			// alterando as "?" na ordem descrita acima
			stmt.setString(1, produto.getDescricao());
			stmt.setInt(2, produto.getQuantidade());
			stmt.setDouble(3, produto.getPreco());
			stmt.setString(4, produto.getFabricante());
			stmt.setInt(5, produto.getCodigo());
			
			//executando a instancia
			stmt.execute();
			
			// fechando as instancias em aberto
			con.close();
			stmt.close();
			
			System.out.println("Produto alterado com sucesso!");
		} // fim do bloco try
		catch (SQLException e) {
			throw new RuntimeException(e);
		} // fim do bloco catch
	} // fim do bloco altera
	
	// Delete
	public void apaga (int id) {
		String sql = "delete from produto where codigo = ?";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			//executando a instancia
			stmt.execute();
			
			// fechando as instancias em aberto
			con.close();
			stmt.close();
			
			System.out.println("Produto deletado com sucesso!");
		} // fim do bloco try
		catch (SQLException e) {
			throw new RuntimeException(e);
		} // fim do bloco catch
	} // fim do bloco apaga
	
	// retorno simples
	public Produto getProduto (int id) {
		String sql = "select * from produto where codigo = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			// Objeto criado fora do laço de repetição por retornar apenas 1 produto
			Produto produto = new Produto();
			
			while (rs.next()) {				
				produto.setCodigo(rs.getInt("codigo"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setQuantidade(rs.getInt("quantidade"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setFabricante(rs.getString("fabricante"));
			}
			rs.close();
			stmt.close();
			con.close();
			
			return produto;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// retorno múltiplo
	public ArrayList<Produto> getProdutos() {
		// proxima linha será ordenada pelo codigo
		String sql = "select * from produto order by codigo";
		try {
			// Criando uma arraylist para retornar campos do banco de dados
			ArrayList lista = new ArrayList();
			
			PreparedStatement stmt = this.con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			// Enquanto houver algum elemento do conjunto
			while (rs.next()) {
				// Criado um objeto do tipo produto dentro do laço, afinal ele deverá retornar uma lista
				Produto produto = new Produto();
				// setando os valores vindo do banco de dados para o objeto criado
				// pega os valores dos atributos om nome correspondente entre ""
				produto.setCodigo(rs.getInt("codigo"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setQuantidade(rs.getInt("quantidade"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setFabricante(rs.getString("fabricante"));
				
				// comando para adicionar o objeto que esta armazenando os dados vindo do SQL a lista criada
				lista.add(produto);
			} // fim do bloco while
			
			// encerramento das instancias
			rs.close();
			stmt.close();
			con.close();
			// lista com todos os objetos armazenados
			return lista;
		} //fim do bloco try
		
		catch (SQLException e){
			throw new RuntimeException();
		} // fim do bloco catch
	} // fim da função getProdutos
	
}
