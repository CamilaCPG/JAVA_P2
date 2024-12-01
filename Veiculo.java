public class Veiculo {
    private String idVeiculo;
    private String marcaVeiculo;
    private String modeloVeiculo;
    private double valorVeiculo;

    public Veiculo(String id, String marca, String modelo, double valor) {
        this.idVeiculo = id;
        this.marcaVeiculo = marca;
        this.modeloVeiculo = modelo;
        this.valorVeiculo = valor;
    }

    public String getIdVeiculo() {
        return idVeiculo;
    }

    public String getMarcaVeiculo() {
        return marcaVeiculo;
    }

    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    public double getValorVeiculo() {
        return valorVeiculo;
    }
}
