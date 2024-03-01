package app;

import java.util.Scanner;

import dao.IngressoDAO;
import model.Ingresso;

public class Aplicacao {
	
  public static Scanner sc = new Scanner(System.in);
  public static IngressoDAO ingressoDAO = new IngressoDAO();

  public static void criarIngresso() {
    System.out.print("Digite o id do seu ingresso: ");
    int id = sc.nextInt();
    System.out.print("\n");

    System.out.print("Digite o nome do evento: ");
    String nome = sc.next();
    System.out.print("\n");

    System.out.print("Digite uma descrição para seu evento: ");
    String descricao = sc.next();
    System.out.print("\n");

    Ingresso ingresso = new Ingresso(id, nome, descricao);

    if(ingressoDAO.insert(ingresso) == true) {
      System.out.println("Usuário criado");
    }
  }
	public static void main(String[] args) throws Exception {
    int opcao;
		
		System.out.println("Escolha uma opção");
		System.out.println("1 - Inserir\n2 - Listar\n3 - Atualizar\n4 - Excluir\n5 - Sair");
    System.out.print("> ");
    opcao = sc.nextInt();

    while (opcao != 5) {
      switch (opcao) {
        case 1:
          criarIngresso();
          break;
      
        default:
          break;
      }
    }
	}
}