package br.com.mesttra.cidades.execucao;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.mesttra.cidades.dao.CidadeDAO;
import br.com.mesttra.cidades.pojo.CidadePOJO;

public class ProgramaCidades {

	private static void insercao(CidadeDAO cidadeDao, Scanner teclado) {
		System.out.println(" -- Inserção de Cidade -- ");
		System.out.println(" ## Digite o DDD ## ");
		int ddd = teclado.nextInt();
		teclado.nextLine();
		
		System.out.println(" ## Digite o nome da cidade ## ");
		String nome = teclado.nextLine();
		
		System.out.println(" ## Digite a quantidade de habitantes ## ");
		int nroHabitantes = teclado.nextInt();
		
		System.out.println(" ## Digite a renda per capita ## ");
		double rendaPerCapita = teclado.nextDouble();
		
		System.out.println(" ## Digite 0 caso seja uma capital e 1 caso não seja ## ");
		boolean capital =  teclado.nextInt() == 0;
		teclado.nextLine();
		
		System.out.println(" ## Digite a sigla do estado ## ");
		String estado = teclado.nextLine();
		
		System.out.println(" ## Digite o nome do Prefeito(a) ## ");
		String nomePrefeito = teclado.nextLine();
		
		CidadePOJO cidadeInserida = new CidadePOJO(ddd, nome, nroHabitantes, rendaPerCapita, capital, estado, nomePrefeito);
		if (cidadeDao.insereCidade(cidadeInserida)) {
			System.out.println(" ## Cidade inserida com sucesso ## ");
		}
	}
	

	private static void remocao(CidadeDAO cidadeDao, Scanner teclado) {
		System.out.println(" -- Remocao de Cidade -- ");
		System.out.println(" ## Digite o DDD ## ");
		
		int ddd = teclado.nextInt();
		if (cidadeDao.removeCidade(ddd)) {
			System.out.println(" ## Cidade removida com sucesso ## ");
		}
	}
	
	private static void listagem(CidadeDAO cidadeDao) {
		System.out.println(" -- Listagem de Cidades -- ");
		List<CidadePOJO> cidadesNoBd = cidadeDao.listaCidades();
		for (CidadePOJO cidade : cidadesNoBd) {
			System.out.println(cidade);
		}
	}
	
	
	public static void main(String[] args) {
		
		CidadeDAO cidadeDao = new CidadeDAO();
		Scanner teclado = new Scanner(System.in);
		
		int opcao = 0;
		do {
			System.out.println(" -- GESTAO DE CIDADES -- ");
			System.out.println(" ## Digite a opcao escolhida ## ");
			System.out.println("1  - Para inserir uma cidade");
			System.out.println("2  - Para remover uma cidade pelo ddd");
			System.out.println("3  - Listar cidades");
			System.out.println("-1 - Para encerrar o programa");
			opcao = teclado.nextInt();
			
			switch (opcao) {
				case 1:
					try {
						insercao(cidadeDao, teclado);
					} catch (InputMismatchException e) {
						System.err.println("Erro ao ler os dados da cidade, refaça a operação.");
						teclado.nextLine();
					}
					break;
				case 2:
					try {
						remocao(cidadeDao, teclado);
					} catch (InputMismatchException e) {
						System.err.println("Erro ao ler o ddd, digit um número inteiro.");
						teclado.nextLine();
					}
					break;
				case 3:
						listagem(cidadeDao);
					break;
				default:
					break;
			}	
			
		} while (opcao != -1);
		
		
		System.out.println("Obrigado por utilizar o nosso sistema!");
		
	}



	
}
