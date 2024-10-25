import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaReservas {
    // listas que armazenam os hoteis e clientes cadastrados.
    private List<Hotel> hoteis;
    private List<Cliente> clientes;
    private Scanner scanner;
    // construtor.
    public SistemaReservas(Scanner scanner) {
        this.hoteis = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.scanner = scanner;
    }

    
    
    public List<Hotel> getHoteis()  {
        return hoteis;
    }

    // Metodo adiciona um novo hotel.
    public void adicionarHotel() {
        
        System.out.print("Digite o nome do hotel: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o endereço do hotel: ");
        String endereco = scanner.nextLine();

        Hotel hotel = new Hotel(nome, endereco);
        hoteis.add(hotel);
        System.out.println("Hotel adicionado com sucesso!");
    }

    // Metodo listar hoteis cadastrados.
    public void listarHoteis() {
        if (hoteis.isEmpty()) {
            System.out.println("Nenhum hotel cadastrado.");
            return;
        }
        for (Hotel hotel : hoteis) {
            System.out.println("Hotel: " + hotel.getNome() + ", Endereço: " + hotel.getEndereco());
        }
    }

    // Metodo adicionar quarto em um hotel.
    public void adicionarQuarto() {
        
        System.out.print("Digite o nome do hotel onde deseja adicionar o quarto: ");
        String nomeHotel = scanner.nextLine();
        // busca o hotel pelo nome.
        Hotel hotelSelecionado = buscarHotel(nomeHotel);
        if (hotelSelecionado == null) {
            System.out.println("Hotel não encontrado.");
            return;
        }

        System.out.print("Digite o número do quarto: ");
        int numero = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite o tipo do quarto (Single, Double, etc.): ");
        String tipo = scanner.nextLine();
        System.out.print("Digite o preço do quarto: ");
        double preco = scanner.nextDouble();
        // cria um novo quarto e adiciona no hotel selecionado.
        Quarto quarto = new Quarto(numero, tipo, preco, hotelSelecionado);
        hotelSelecionado.adicionarQuarto(quarto);
        System.out.println("Quarto adicionado com sucesso!");
    }

    // Netodo listar quartos
    public void listarQuartosDisponiveis() {
        
        System.out.print("Digite o nome do hotel: ");
        String nomeHotel = scanner.nextLine();

        Hotel hotel = buscarHotel(nomeHotel);
        if (hotel == null) {
            System.out.println("Hotel não encontrado.");
            return;
        }

        List<Quarto> quartosDisponiveis = hotel.listarQuartosDisponiveis();
        if (quartosDisponiveis.isEmpty()) {
            System.out.println("Nenhum quarto disponível.");
        } else {
            // Exibe cada quarto disponivel
            for (Quarto quarto : quartosDisponiveis) {
                System.out.println("Quarto Nº: " + quarto.getNumero() + ", Tipo: " + quarto.getTipo() + ", Preço: "
                        + quarto.getPreco());
            }
        }
    }

    // Metodo para fazer reserva
    public void fazerReserva() {
        

        System.out.print("Digite o nome do cliente: ");
        String nomeCliente = scanner.nextLine();
        Cliente cliente = buscarClienteOuCadastrar(nomeCliente);

        System.out.print("Digite o nome do hotel: ");
        String nomeHotel = scanner.nextLine();
        Hotel hotel = buscarHotel(nomeHotel);
        if (hotel == null) {
            System.out.println("Hotel não encontrado.");
            return;
        }

        System.out.print("Digite o número do quarto: ");
        int numeroQuarto = scanner.nextInt();
        scanner.nextLine(); // Consumir o newline

        Quarto quarto = buscarQuartoNoHotel(hotel, numeroQuarto);
        if (quarto == null) {
            System.out.println("Quarto não encontrado.");
            return;
        }

        System.out.print("Digite a data de check-in (YYYY-MM-DD): ");
        LocalDate checkIn = LocalDate.parse(scanner.nextLine());
        System.out.print("Digite a data de check-out (YYYY-MM-DD): ");
        LocalDate checkOut = LocalDate.parse(scanner.nextLine());

        // Verificar se o quarto está disponível nas datas solicitadas.
        if (quarto.isDisponivel(checkIn, checkOut)) {
            cliente.fazerReserva(quarto, checkIn, checkOut);
            System.out.println("Reserva feita com sucesso!");
        } else {
            System.out.println("Quarto não disponível nas datas solicitadas.");
        }
    }

    // Metodo buscar hotel pelo nome.
    public Hotel buscarHotel(String nomeHotel) {
        for (Hotel hotel : hoteis) {
            if (hotel.getNome().equalsIgnoreCase(nomeHotel)) {
                return hotel;
            }
        }
        return null;
    }
      // Metodo buscar cliente pelo nome.
    public Cliente buscarCliente(String nomeCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getNome().equalsIgnoreCase(nomeCliente)) {
                return cliente;
            }
        }
        return null;
    }
    // Metodo para buscar um cliente ou cadastrar.
    public Cliente buscarClienteOuCadastrar(String nomeCliente) {
        Cliente cliente = buscarCliente(nomeCliente);
        if (cliente == null) {
            
            System.out.print("Digite o email do cliente: ");
            String email = scanner.nextLine();
            System.out.print("Digite o telefone do cliente: ");
            String telefone = scanner.nextLine();
            cliente = new Cliente(nomeCliente, email, telefone);
            clientes.add(cliente);
            System.out.println("Cliente cadastrado com sucesso!");
        }
        return cliente;
    }
     // metodo para buscar um quarto no hotel.
    public Quarto buscarQuartoNoHotel(Hotel hotel, int numeroQuarto) {
        for (Quarto quarto : hotel.getQuartos()) {
            if (quarto.getNumero() == numeroQuarto) {
                return quarto;
            }
        }
        return null;
    }
     // metodo para camcelar reserva.
    public void cancelarReserva() {
        
        System.out.print("Digite o nome do cliente: ");
        String nomeCliente = scanner.nextLine();

        Cliente cliente = buscarCliente(nomeCliente);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        if (cliente.getReservas().isEmpty()) {
            System.out.println("O cliente não possui reservas.");
            return;
        }

        System.out.println("Reservas do cliente:");
        for (Reserva reserva : cliente.getReservas()) {
            System.out.println("Quarto Nº: " + reserva.getQuarto().getNumero() +
                    " | Hotel: " + reserva.getQuarto().getHotel().getNome() +
                    " | Check-in: " + reserva.getDataCheckIn() +
                    " | Check-out: " + reserva.getDataCheckOut());
        }

        System.out.print("Digite o número do quarto da reserva a ser cancelada: ");
        int numeroQuarto = scanner.nextInt();

        Reserva reserva = buscarReservaCliente(cliente, numeroQuarto);
        if (reserva != null) {
            cliente.cancelarReserva(reserva);
            System.out.println("Reserva cancelada com sucesso.");
        } else {
            System.out.println("Reserva não encontrada.");
        }
    }

    
    public void listarReservasCliente() {
        
        System.out.print("Digite o nome do cliente: ");
        String nomeCliente = scanner.nextLine();

        Cliente cliente = buscarCliente(nomeCliente);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        if (cliente.getReservas().isEmpty()) {
            System.out.println("O cliente não possui reservas.");
        } else {
            System.out.println("Reservas do cliente " + cliente.getNome() + ":");
            for (Reserva reserva : cliente.getReservas()) {
                System.out.println("Hotel: " + reserva.getQuarto().getHotel().getNome() +
                        " | Quarto Nº: " + reserva.getQuarto().getNumero() +
                        " | Check-in: " + reserva.getDataCheckIn() +
                        " | Check-out: " + reserva.getDataCheckOut());
            }
        }
    }
   // Metodo  busca uma reserva de um cliente.
   
    public Reserva buscarReservaCliente(Cliente cliente, int numeroQuarto) {
        for (Reserva reserva : cliente.getReservas()) {
            if (reserva.getQuarto().getNumero() == numeroQuarto) {
                return reserva;
            }
        }
        return null;
    }
}



