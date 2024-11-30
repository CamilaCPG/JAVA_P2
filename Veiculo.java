public class Veiculo {
    private String marcaVeiculo;
    private String modeloVeiculo;
    private double valorVeiculo;

    public Veiculo(String marca, String modelo, double valor) {
        this.marcaVeiculo = marca;
        this.modeloVeiculo = modelo;
        this.valorVeiculo = valor;
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