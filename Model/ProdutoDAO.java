package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ConnectionFactory.connection;

public class ProdutoDAO {
	// vari�vel que vai fazer a conex�o com o banco usando a classe connection
	private Connection con;
	
	// Construtor que sempre cria uma nova conex�o
	public ProdutoDAO() {this.con = new connection().getConnection();}
	
	// CRUD
	// Inser��o
	public void adiciona(Produto produto) {
		// Forma de inser��o no banco informando os campos
		// onde "?" ser�o os campos informados mais abaixo
		String sql = "insert into produto (codigo, descricao, quantidade, preco, fabricante) values (?,?,?,?,?)";
		
		try {
			// forma de preparar a conex�o
			PreparedStatement stmt = con.prepareStatement(sql);
			
			// Substitui��o dos "?" por valores na ordem descrita na linha 22
			stmt.setInt(1, produto.getCodigo());
			stmt.setString(2, produto.getDescricao());
			stmt.setInt(3, produto.getQuantidade());
			stmt.setDouble(4, produto.getPreco());
			stmt.setString(5, produto.getFabricante());
			
			//Ap�s a altera��o dos campos, ele ser� executado com
			stmt.execute();
			
			// fechando as inst�ncias abertas
			stmt.close();
			con.close();
			
			// Apenas uma forma de confirma��o
			System.out.println("Produto registrado.");
			
		} // fim do bloco try
		
		// catch
		catch (SQLException e) {throw new RuntimeException(e);}
		
	} // fim do m�todo de adi��o de dados
	
	// altera��o
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
			// Objeto criado fora do la�o de repeti��o por retornar apenas 1 produto
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
	
	// retorno m�ltiplo
	public ArrayList<Produto> getProdutos() {
		// proxima linha ser� ordenada pelo codigo
		String sql = "select * from produto order by codigo";
		try {
			// Criando uma arraylist para retornar campos do banco de dados
			ArrayList lista = new ArrayList();
			
			PreparedStatement stmt = this.con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			// Enquanto houver algum elemento do conjunto
			while (rs.next()) {
				// Criado um objeto do tipo produto dentro do la�o, afinal ele dever� retornar uma lista
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
	} // fim da fun��o getProdutos
	
}
