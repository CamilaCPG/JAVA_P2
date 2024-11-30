import java.io.*;
import java.util.*;

/* TODO: Implementar no public void Menu, além de Clientes cliente, também em relação aos Veículos */
public class Menu {
    private final Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void Menu(Clientes cliente) {
        try (scanner) {
            int opcao;

            do {
                System.out.println("\nBem-vindo(a)! Escolha uma opção:");
                System.out.println(
                        "1. Cadastrar cliente\n2. Consultar cliente\n3. Alterar informações\n4. Excluir cliente\n5. Gerenciar veículos\n6. Sair");
                System.out.println("\nSua opção: ");
                opcao = Integer.parseInt(scanner.nextLine().trim());

                switch (opcao) {
                    case 1:
                        cliente.cadastrarCliente();
                        break;

                    case 2:
                        cliente.consultarCliente();
                        break;

                    case 3:
                        cliente.alterarCliente();
                        break;

                    case 4:
                        cliente.excluirCliente();
                        break;

                    case 5:
                        cadastroVeiculo.selecionaOpcao();
                        break;

                    case 6:
                        System.out.println("\nVolte sempre!");
                        break;

                    default:
                        System.out.println("\nOpção inválida. Tente novamente!");
                        break;
                }
            } while (opcao != 6);
        } catch (InputMismatchException e) {
            System.out.println("\nHouve algum erro. Tente novamente!\n");
        }
    }
}