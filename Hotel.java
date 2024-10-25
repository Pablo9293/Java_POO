import java.util.ArrayList;
import java.util.List;
  //Atributos
public class Hotel {
    private String nome;
    private String endereco;
    private List<Quarto> quartos;
  // Construtor
    public Hotel(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.quartos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }
    // metodo para adicionar o objeto tipo quarto.
    public void adicionarQuarto(Quarto quarto) {
        quartos.add(quarto);
    }
     
    public List<Quarto> listarQuartosDisponiveis() {
        List<Quarto> quartosDisponiveis = new ArrayList<>();
        for (Quarto quarto : quartos) {
            if (quarto.isDisponivel()) { // Método da classe Quarto.
                quartosDisponiveis.add(quarto);
            }
        }
        return quartosDisponiveis;
    }

    public List<Quarto> getQuartos() { // Método para retornar a lista de quartos do hotel.
        return quartos;
    }
}
