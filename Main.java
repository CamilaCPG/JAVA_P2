import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Clientes cliente = new Clientes(sc);
        CadastroVeiculo cVeiculo = new CadastroVeiculo();
        Menu menu = new Menu(sc);

        menu.menu(cliente, cVeiculo);
    }
}

// TODO: se necessário, mesclar a main do arquivo de veículos com esse
