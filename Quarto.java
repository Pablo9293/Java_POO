import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
  //atributos.
public class Quarto {
    private int numero;
    private String tipo;
    private double preco;
    private Hotel hotel;
    private List<Reserva> reservas;
  // construtor.
    public Quarto(int numero, String tipo, double preco, Hotel hotel) {
        this.numero = numero;
        this.tipo = tipo;
        this.preco = preco;
        this.hotel = hotel;
        this.reservas = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPreco() {
        return preco;
    }
    public Hotel getHotel() {
        return hotel;
    }
    public List<Reserva> getReservas() {
        return reservas;
    }
     // Metodo para verificar se o quarto eata disponivel pela data.
    public boolean isDisponivel(LocalDate checkIn, LocalDate checkOut) {
        for (Reserva reserva : reservas) {
            if (reserva.overlaps(checkIn, checkOut)) {
                return false;
            }
        }
        return true;
    }
    // Metodo adiciona uma reserva na lista de reservas.
    public void adicionarReserva(Reserva reserva) {
        reservas.add(reserva);
    }
    // metodo verifica se o quarto tem alguma reserva.
    public boolean isDisponivel() {
        return reservas.isEmpty();
    }
}
