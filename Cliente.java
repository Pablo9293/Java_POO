import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


// Atributos.
public class Cliente {
    private String nome;
    private String email;
    private String telefone;
    private List<Reserva> reservas;

    public Cliente(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.reservas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }
    
    public String getEmail() {
        return email;
    }
    public String getTelefone() {
        return telefone;
    }
    public List<Reserva> getReservas() {
        return reservas;
    }

    public void fazerReserva(Quarto quarto, LocalDate checkIn, LocalDate checkOut) {
        Reserva reserva = new Reserva(quarto, this, checkIn, checkOut);
        reservas.add(reserva);
        quarto.adicionarReserva(reserva);
    }

    public void cancelarReserva(Reserva reserva) {
        reservas.remove(reserva);
        reserva.getQuarto().getReservas().remove(reserva); // Remover da lista de reservas do quarto
    }
}
