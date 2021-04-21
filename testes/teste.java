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
			+ "\n3 - Fazer uma busca espec�fica \n4 - Fazer uma altera��o \n5 - Deletar \n-> ");
			escolha = sc.nextInt();
				
			if (escolha == 1) { // Adicionar um valor
				// cria��o de um objeto da classe Produto (pacote Model)
				Produto produto = new Produto();
				// Usando um dos m�todos para fazer a inser��o via teclado
				produto.insere();
				// Criando um objeto para chamar a conex�o / fazer a inser��o dos campos digitados
				ProdutoDAO dao = new ProdutoDAO();
				// Executando o m�todo "adiciona" da classe ProdutoDAO (pacote Model)
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
				// produto � o que ser� exibido / lista � o "limite" do ForEach
				for(Produto produto : lista) {
					System.out.println(produto); 
				}
			}
			
			else if (escolha == 3) { // Busca espec�fica
				System.out.println("Digite um c�digo existente na tabela");
				int busca = sc.nextInt();
				
				ProdutoDAO dao = new ProdutoDAO();
				System.out.println(dao.getProduto(busca)); 
			}
				
			else if (escolha == 4) { // Altera��o
				System.out.println("No campo -> c�digo <- digite a tupla (registro) que ira ser alterada(o)");
				// cria��o de um objeto da classe Produto (pacote Model)
				Produto produto = new Produto();
		     	// Usando um dos m�todos para fazer a inser��o via teclado
				produto.insere();
				// Criando um objeto para chamar a conex�o / fazer a inser��o dos campos digitados
				ProdutoDAO dao = new ProdutoDAO();
				// Executando o m�todo "altera" da classe ProdutoDAO (pacote Model)
				dao.altera(produto); 
			}
				
			else if (escolha == 5) { // Delete
				System.out.println("Digite o c�digo a ser apagado: ");
				int busca = sc.nextInt();
					
				ProdutoDAO dao = new ProdutoDAO();
				dao.apaga(busca);
			}
			
			sc.close();
	}	
}
