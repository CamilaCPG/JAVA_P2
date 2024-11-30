import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Clientes cliente = new Clientes(scanner);
        Menu menu = new Menu(scanner);

        menu.Menu(cliente, cadastroVeiculo);

        scanner.close();
    }
}

// TODO: se necessário, mesclar a main do arquivo de veículos com esse