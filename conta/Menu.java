package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {

	public static void main(String[] args) {
		
		ContaController contas = new ContaController();
		
		Scanner leiaa = new Scanner(System.in);
		
		int opcaoo, numero, agencia, tipo, aniversario = 0, numeroDestino;
		String titular;
		float saldo, limite, valor;
		
		System.out.println("\nCriar Contas\n");
		
		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "Rafael Galvão", 1000f, 100.0f);
		contas.cadastrar(cc1);
		
		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 124, 1, "Maria da Silva", 2000f, 100.0f);
		contas.cadastrar(cc2);
		
		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Gabrieli Dutra", 4000f, 12);
		contas.cadastrar(cp1);
		
		ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Juliana Ramos", 8000f, 15);
		contas.cadastrar(cp2);
		
		contas.listarTodas();
		
		
		  // Teste da Classe Conta Corrente
		ContaCorrente cC1 = new ContaCorrente(1, 123, 1, "Rafael Galvão", 0.0f, 1000.0f);
		cc1.visualizar();
		cc1.sacar(12000.0f);
		cc1.depositar(5000.0f);
		cc1.visualizar();
				
	   // Teste da Classe Conta Poupança
		
		ContaPoupanca cP1 = new ContaPoupanca(2, 123, 2, "Gabrieli Dutra", 100000.0f, 15);
		cp1.visualizar();
	    cp1.sacar(1000.0f);
		cp1.depositar(5000.0f);
		cp1.visualizar();
		
        Scanner leia = new Scanner(System.in);
		
		
		int opcao;
		
		while (true) {
			
			
			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND
					         + "/********************************************************************************|" );
			System.out.println("|********************************************************************************|");
			System.out.println("||                                                                              ||");
			System.out.println("||                          BANCO GENERATION.ltda                               ||");
			System.out.println("||                                                                              ||");
			System.out.println("|********************************************************************************|");
			System.out.println("||  1 - Criar Conta                  ||    5 - Apagar Conta                     ||");
			System.out.println("||  2 - Listar todas as Contas       ||    6 - Sacar                            ||");
			System.out.println("||  3 - Buscar Conta por Numero      ||    7 - Depositar                        ||");
			System.out.println("||  4 - Atualizar Dados da Conta     ||    8 - Transferir valores entre Contas  ||");
			System.out.println("||******************************************************************************||");
			System.out.println("||                               9 - Sair                                       ||");
			System.out.println("||******************************************************************************||");
			System.out.println("||                        Entre com a opção desejada                            ||");
			System.out.println("\\****************************************************************************** //");
			System.out.println("" + Cores.TEXT_RESET);
			
			try {
				opcao = leia.nextInt();
			}catch(InputMismatchException e){
				System.out.println("\nDigite valores inteiros!");
				leia.nextLine();
				opcao=0;
			}

			if (opcao == 9) {
				System.out.println("\nBANCO GENERATION.ltda - O seu futuro começa aqui!");
				leia.close();
				System.exit(0);
			}

			switch (opcao) {
			case 1:
				System.out.println(Cores.TEXT_WHITE + "Criar Conta\n");
				
				System.out.println("\nDigite o Numero da Agência: ");
				agencia = leia.nextInt();
				System.out.println("\nDigite o nome do Titular: ");
				leia.skip("\\R?");
				titular = leia.nextLine();
				
				do {
					System.out.println("\nDigite o tipo da conta (1-CC ou 2-CP): ");
					tipo = leia.nextInt();
				}while(tipo < 1 && tipo > 2);
				
				System.out.println("\nDigite o saldo da conta (R$): ");
				saldo = leia.nextFloat();
				
				switch(tipo) {
				case 1 -> {
					System.out.println("\nDigite o limite de credito (R$): ");
					limite = leia.nextFloat();
					contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
				}
				}

				keyPress();
				break;
			case 2:
				System.out.println(Cores.TEXT_WHITE + "Listar todas as Contas\n\n");
				contas.listarTodas();
				keyPress();
				break;
			case 3:
				System.out.println(Cores.TEXT_WHITE + "Consultar dados da Conta - por número\n\n");
				
				System.out.println("Digite o numero da conta: ");
				numero = leia.nextInt();
				
				contas.procurarPorNumero(numero);

				keyPress();
				break;
			case 4:
				System.out.println(Cores.TEXT_WHITE + "Atualizar dados da Conta\n\n");
				
				System.out.println("Digite o numero da conta: ");
				numero = leia.nextInt();
				
				if (contas.buscarNaCollection(numero) != null) {
					System.out.println("Digite o numero da Agência: ");
					agencia = leia.nextInt();
					System.out.println("Digite o nome do titular: ");
					leia.skip("\\R?");
					titular = leia.nextLine();
					
					System.out.println("Digite o saldo da conta (R$): ");
					saldo = leia.nextFloat();
					
					tipo = contas.retornaTipo(numero);
					
					switch (tipo) {
					case 1 -> {
						System.out.println("Digite o limite de Credito (R$): ");
						limite = leia.nextFloat();
						contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
					}
					case 2 -> {
						System.out.println("Digite o dia do aniversario da conta: ");
						aniversario = leia.nextInt();
						contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
					}
					default -> {
						System.out.println("Tipo de conta invalido!");
					}
					}
				}else 
					System.out.println("Conta não encontrada!");

				keyPress();
				break;
			case 5:
				System.out.println(Cores.TEXT_WHITE + "Apagar Conta\n\n");
				
				System.out.println("Digite o numero da conta: ");
				numero = leia.nextInt();
				
				contas.deletar(numero);

				keyPress();
				break;
			case 6:
				System.out.println(Cores.TEXT_WHITE + "Sacar\n\n");
				
				System.out.println("Digite o numero da conta: ");
				numero = leia.nextInt();
				
				do {
					System.out.println("Digite o valor do saque (R$): ");
					valor = leia.nextFloat();
				}while(valor <= 0);
				
				contas.sacar(numero, valor);

				keyPress();
				break;
			case 7:
				System.out.println(Cores.TEXT_WHITE + "Depositar\n\n");
				
				System.out.println("Digite o numero da conta: ");
				numero = leia.nextInt();
				
				do {
					System.out.println("Digite o valor do Deposito (R$): ");
					valor = leia.nextFloat();
				}while(valor <= 0);
				
				contas.depositar(numero, valor);

				keyPress();
				break;
			case 8:
				System.out.println(Cores.TEXT_WHITE + "Transferência entre contas\n\n");
				
				System.out.println("Digite o numero da conta de Origem: ");
				numero = leia.nextInt();
				System.out.println("Digite o numero da conta de Destino: ");
				numeroDestino = leia.nextInt();
				
				do {
					System.out.println("Digite o valor da transferencia (R&): ");
					valor = leia.nextFloat();
				}while(valor <= 0);
				
				contas.transferir(numero, numeroDestino, valor);

				keyPress();
				break;
			default:
				System.out.println("\nOpção Inválida" + Cores.TEXT_RESET);
				
				keyPress();
				break;
			}
		}
	}

	public static void keyPress() {

		try {

			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();

		} catch (IOException e) {

			System.out.println("Você pressionou uma tecla diferente de enter!");

		}
	}
}
