import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class CadastroVeiculo {

    private List<Veiculo> veiculos;
    private final String arquivos = "veiculos.txt";

    public CadastroVeiculo() {
        this.veiculos = new ArrayList<>();
        carregarVeiculos();
    }

    public static void mostraMenu() {
        System.out.println("\nMENU");
        System.out.println("1. INCLUIR VEÍCULO");
        System.out.println("2. LISTAR VEÍCULO");
        System.out.println("3. ALTERAR VEÍCULO");
        System.out.println("4. EXCLUIR VEÍCULO");
        System.out.println("5. SAIR");
    }

    public void selecionaOpcao() {
        int opcao;

        Scanner sc = new Scanner(System.in);

        while (true) {
            mostraMenu();
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1:
                    incluirVeiculo(sc);
                    break;

                case 2:
                    listarVeiculos();
                    break;

                case 3:
                    alterarVeiculo(sc);

                    break;

                case 4:
                    excluirVeiculo(sc);

                    break;

                case 5:
                    System.out.println("Finalizando...");
                    sc.close();
                    return;

                default:
                    System.out.println("Selecione uma opção válida");
                    break;
            }

        }

    }

    private void incluirVeiculo(Scanner sc) {
        System.out.print("Marca do veículo: ");
        String marca = sc.nextLine();
        System.out.print("Modelo do veículo: ");
        String modelo = sc.nextLine();
        double valor = 0.0;
        boolean valorValido = false;
        while (!valorValido) {
            System.out.print("Valor do veículo (use ponto para separar decimais): ");
            try {
                valor = Double.parseDouble(sc.nextLine());
                if (valor <= 0) {
                    System.out.println("Erro: O valor deve ser maior que zero.");
                } else {
                    valorValido = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Insira um número válido no formato decimal.");
            }
        }

        Veiculo veiculo = new Veiculo(marca, modelo, valor);
        veiculos.add(veiculo);

        salvarVeiculosNoArquivo();
        System.out.println("Veículo incluído com sucesso!");
    }

    private void listarVeiculos() {
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
        } else {
            DecimalFormat df = new DecimalFormat("#0.00");
            System.out.println("\n=== Lista de Veículos ===");
            for (int i = 0; i < veiculos.size(); i++) {
                Veiculo veiculo = veiculos.get(i);
                System.out.println((i + 1) + ". " + veiculo.getMarcaVeiculo() + " - " + veiculo.getModeloVeiculo()
                        + " - R$ " + df.format(veiculo.getValorVeiculo()));
            }
        }
    }

    private void alterarVeiculo(Scanner sc) {
        listarVeiculos();
        System.out.print("Digite o número do veículo que deseja alterar: ");
        int indice = sc.nextInt() - 1;
        sc.nextLine();

        if (indice < 0 || indice >= veiculos.size()) {
            System.out.println("Veículo inválido.");
            return;
        }

        System.out.print("Nova marca: ");
        String novaMarca = sc.nextLine();
        System.out.print("Novo modelo: ");
        String novoModelo = sc.nextLine();
        double novoValor = 0.0;
        boolean valorValido = false;

        while (!valorValido) {
            System.out.print("Novo valor (use ponto para separar decimais): ");
            try {
                novoValor = Double.parseDouble(sc.nextLine());
                if (novoValor <= 0) {
                    System.out.println("Erro: O valor deve ser maior que zero.");
                } else {
                    valorValido = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Insira um número válido no formato decimal.");
            }
        }

        Veiculo veiculoAtualizado = new Veiculo(novaMarca, novoModelo, novoValor);
        veiculos.set(indice, veiculoAtualizado);

        salvarVeiculosNoArquivo();
        System.out.println("Veículo alterado com sucesso!");
    }

    private void excluirVeiculo(Scanner sc) {
        listarVeiculos();
        System.out.print("Digite o número do veículo que deseja excluir: ");
        int indice = sc.nextInt() - 1;

        if (indice < 0 || indice >= veiculos.size()) {
            System.out.println("Veículo inválido.");
            return;
        }

        veiculos.remove(indice);
        salvarVeiculosNoArquivo();
        System.out.println("Veículo excluído com sucesso!");
    }

    private void salvarVeiculosNoArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivos))) {
            for (Veiculo veiculo : veiculos) {
                writer.write(
                        veiculo.getMarcaVeiculo() + ";" + veiculo.getModeloVeiculo() + ";" + veiculo.getValorVeiculo());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    private void carregarVeiculos() {
        File arquivo = new File(arquivos);

        if (!arquivo.exists()) {
            return;
        }

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivos))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(";");
                String marca = partes[0];
                String modelo = partes[1];
                double valor = Double.parseDouble(partes[2]);

                Veiculo veiculo = new Veiculo(marca, modelo, valor);
                veiculos.add(veiculo);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
        }
    }
}