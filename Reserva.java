import java.time.LocalDate;
  // Atributos.
public class Reserva {
    private Quarto quarto;
    public Cliente cliente;
    private LocalDate dataCheckIn;
    private LocalDate dataCheckOut;
   // construtor.
    public Reserva(Quarto quarto, Cliente cliente, LocalDate checkIn, LocalDate checkOut) {
        this.quarto = quarto;
        this.cliente = cliente;
        this.dataCheckIn = checkIn;
        this.dataCheckOut = checkOut;
    }

    public Quarto getQuarto() {
        return quarto;
    }
    

    public LocalDate getDataCheckIn() {
        return dataCheckIn;
    }

    public LocalDate getDataCheckOut() {
        return dataCheckOut;
    }
    //Metodo verifica se as datas de uma nova reserva nao batem com uma outra reserva no sistema sistema.
    public boolean overlaps(LocalDate checkIn, LocalDate checkOut) {
        return (checkIn.isBefore(dataCheckOut) && checkOut.isAfter(dataCheckIn));
    }
}
