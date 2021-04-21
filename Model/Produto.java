package Model;

import java.util.Scanner;

public class Produto {
	/* create table produto (
	codigo integer primary key,
	descricao varchar (50),
	quantidade integer,
	preco numeric (8, 2),
	fabricante varchar (30)
); */
	
	// representação da tabela produto em java
	private int codigo;
	private String descricao;
	private int quantidade;
	private double preco;
	private String fabricante;
	
	// Construtor criado e utilizando o método insere(produto)
	public Produto (){}
	
	// Construtor com parâmetros
	public Produto (int codigo, String descricao, int quantidade, double preco, String fabricante) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.preco = preco;
		this.fabricante = fabricante;
	} // fim do construtor
	
	//Setters
	public void setCodigo(int codigo) {this.codigo = codigo;}
	public void setDescricao(String descricao) {this.descricao = descricao;}
	public void setQuantidade(int quantidade) {this.quantidade = quantidade;}
	public void setPreco(double preco) {this.preco = preco;}
	public void setFabricante(String fabricante) {this.fabricante = fabricante;}
	
	//Getters
	public int getCodigo() {return codigo;}
	public String getDescricao() {return descricao;}
	public int getQuantidade() {return quantidade;}
	public double getPreco() {return preco;}
	public String getFabricante() {return fabricante;}

	// toString gerado automaticamente
	@Override
	public String toString() {
		return "Produto [codigo = " + codigo + ", descricao = " + descricao + ", quantidade = " + quantidade + ", preco = R$"
				+ preco + ", fabricante = " + fabricante + "]\n";
	}
	
	// Método adicional para a entrada de valores via teclado
	public void insere () {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite o código do produto: ");
		codigo = sc.nextInt();
		
		System.out.println("Digite a descrição / nome do produto: ");
		descricao = sc.next();
		
		System.out.println("Digite a quantidade em estoque desse produto: ");
		quantidade = sc.nextInt();
		
		System.out.println("Digite o preço desse produto: ");
		preco = sc.nextDouble();
		
		System.out.println("Quem é o fabricante desse produto?: ");
		fabricante = sc.next();
		
		// encerramento do Scanner
		sc.close();
		
	} // fim do método insere
	
}
