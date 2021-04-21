package testes;

import java.util.Scanner;
import Model.Produto;
import Model.ProdutoDAO;
import java.util.ArrayList;

public class teste {
	public static void main (String [] args) {
		int escolha = 10;
		
			Scanner sc = new Scanner(System.in);
			
			System.out.println("\n0 - Sair do programa \n1 - Adicionar valor \n2 - Exibir toda a tabela "
			+ "\n3 - Fazer uma busca específica \n4 - Fazer uma alteração \n5 - Deletar \n-> ");
			escolha = sc.nextInt();
				
			if (escolha == 1) { // Adicionar um valor
				// criação de um objeto da classe Produto (pacote Model)
				Produto produto = new Produto();
				// Usando um dos métodos para fazer a inserção via teclado
				produto.insere();
				// Criando um objeto para chamar a conexão / fazer a inserção dos campos digitados
				ProdutoDAO dao = new ProdutoDAO();
				// Executando o método "adiciona" da classe ProdutoDAO (pacote Model)
				dao.adiciona(produto);
			}
				
			else if (escolha == 2) { // exibir a lista
				ProdutoDAO dao = new ProdutoDAO();
				// Utilizando toString
				// System.out.println(dao.getProdutos()); 
				
				// atribuindo o retorno de getProdutos(ProdutoDAO)) para a nova lista
				ArrayList<Produto> lista = new ArrayList();
				lista = dao.getProdutos();
				
				// utilizando um ForEach para exibir os valores
				// produto é o que será exibido / lista é o "limite" do ForEach
				for(Produto produto : lista) {
					System.out.println(produto); 
				}
			}
			
			else if (escolha == 3) { // Busca específica
				System.out.println("Digite um código existente na tabela");
				int busca = sc.nextInt();
				
				ProdutoDAO dao = new ProdutoDAO();
				System.out.println(dao.getProduto(busca)); 
			}
				
			else if (escolha == 4) { // Alteração
				System.out.println("No campo -> código <- digite a tupla (registro) que ira ser alterada(o)");
				// criação de um objeto da classe Produto (pacote Model)
				Produto produto = new Produto();
		     	// Usando um dos métodos para fazer a inserção via teclado
				produto.insere();
				// Criando um objeto para chamar a conexão / fazer a inserção dos campos digitados
				ProdutoDAO dao = new ProdutoDAO();
				// Executando o método "altera" da classe ProdutoDAO (pacote Model)
				dao.altera(produto); 
			}
				
			else if (escolha == 5) { // Delete
				System.out.println("Digite o código a ser apagado: ");
				int busca = sc.nextInt();
					
				ProdutoDAO dao = new ProdutoDAO();
				dao.apaga(busca);
			}
			
			sc.close();
	}	
}
