import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Clientes {
    private static final String CLIENTE_FILE = "Clientes.txt";
    private final Scanner scanner;

    public Clientes(Scanner scanner) {
        this.scanner = scanner;
    }

    public void cadastrarCliente() {

        LocalDateTime dataCadastro = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = dataCadastro.format(formatter);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CLIENTE_FILE, true))) {
            String id = UUID.randomUUID().toString(); // Gera um ID único
            System.out.println("\nDigite seu nome: ");
            String nome = scanner.nextLine();

            System.out.println("\nDigite seu telefone: ");
            String telefone = scanner.nextLine();

            writer.write(id + ";" + nome + ";" + telefone + ";" + dataFormatada + "\n"); // Salva o cliente com ID
            System.out.println("\nCadastro feito com sucesso!");
        } catch (IOException e) {
            System.out.println("\nErro ao cadastrar contato. Tente novamente!");
        }
    }

    public void consultarCliente() {
        System.out.println("\nDigite o nome para consultar: ");
        String nomeBusca = scanner.nextLine();
        boolean encontrado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(CLIENTE_FILE))) {
            String nome;

            while ((nome = reader.readLine()) != null) {
                String telefone = reader.readLine();

                if (nome.equalsIgnoreCase(nomeBusca)) {
                    System.out.println("\nNome: " + nome);
                    System.out.println("\nTelefone: " + telefone);

                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("\nCliente não encontrado.");
            }
        } catch (IOException e) {
            System.out.println("\nErro ao consultar cliente. Tente novamente!");
        }
    }

    public void alterarCliente() {
        System.out.println("\nDigite o nome do cliente: ");
        String nomeBusca = scanner.nextLine();
        List<String> clientes = new ArrayList<>();
        boolean encontrado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(CLIENTE_FILE))) {
            String nome;

            while ((nome = reader.readLine()) != null) {
                String telefone = reader.readLine();

                if (nome.equalsIgnoreCase(nomeBusca)) {
                    System.out.println("\nO que você deseja alterar?");
                    System.out.println("1. Nome");
                    System.out.println("2. Telefone");
                    System.out.println("3. Nome e Telefone");
                    System.out.println("4. Veículo");
                    System.out.println("\nSua escolha: ");
                    int escolha = Integer.parseInt(scanner.nextLine());

                    switch (escolha) {
                        case 1:
                            System.out.println("\nNovo nome: ");
                            nome = scanner.nextLine();
                            break;

                        case 2:
                            System.out.println("\nNovo telefone: ");
                            telefone = scanner.nextLine();
                            break;

                        case 3:
                            System.out.println("\nNovo nome: ");
                            nome = scanner.nextLine();
                            System.out.println("\nNovo telefone: ");
                            telefone = scanner.nextLine();
                            break;

                        default:
                            System.out.println("\nOpção inválida. Nenhuma alteração feita.");
                            break;
                    }

                    encontrado = true;
                }

                clientes.add(nome);
                clientes.add(telefone);
            }
        } catch (IOException e) {
            System.out.println("\nErro ao alterar. Tente novamente!");
        }

        if (encontrado) {
            try (FileWriter writer = new FileWriter(CLIENTE_FILE, false)) {
                for (String linha : clientes) {
                    writer.write(linha + "\n");
                }
                System.out.println("\nAlterações feitas com sucesso!");
            } catch (IOException e) {
                System.out.println("\nErro ao salvar alterações. Tente novamente!");
            }
        } else {
            System.out.println("\nContato não encontrado.");
        }
    }

    public void excluirCliente() {
        System.out.println("\nDigite o nome do cliente a ser excluído: ");
        String nomeBusca = scanner.nextLine();
        List<String> clientes = new ArrayList<>();
        boolean encontrado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(CLIENTE_FILE))) {
            String nome;

            while ((nome = reader.readLine()) != null) {
                String telefone = reader.readLine();

                if (!nome.equalsIgnoreCase(nomeBusca)) {
                    clientes.add(nome);
                    clientes.add(telefone);

                } else {
                    encontrado = true;

                }
            }
        } catch (IOException e) {
            System.out.println("\nErro ao excluir cliente. Tente novamente!");
        }

        if (encontrado) {
            try (FileWriter writer = new FileWriter(CLIENTE_FILE, false)) {
                for (String linha : clientes) {
                    writer.write(linha + "\n");
                }
                System.out.println("\nCliente excluído com sucesso!");
            } catch (IOException e) {
                System.out.println("\nErro ao fazer a exclusão. Tente novamente!");
            }
        } else {
            System.out.println("\nCliente não encontrado.");
        }
    }
}
