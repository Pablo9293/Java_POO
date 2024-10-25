

import java.util.List;
import java.util.Scanner;

public class Administrador {   
    private String nome;
    private int id;

    // Construtor
    public Administrador(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    public void adicionarHotel(List<Hotel> hoteis, Scanner scanner) {
        System.out.print("Nome do Hotel: ");
        String nome = scanner.nextLine();
        
        if (buscarHotel(hoteis, nome) != null) {
            System.out.println("Erro: Já existe um hotel com este nome.");
            return;
        }

        System.out.print("Endereço do Hotel: ");
        String endereco = scanner.nextLine();

        Hotel hotel = new Hotel(nome, endereco);
        hoteis.add(hotel);
        
        System.out.println("Hotel " + nome + " adicionado com sucesso.");
    }

    public void removerHotel(List<Hotel> hoteis, Scanner scanner) {
        System.out.print("Digite o nome do hotel a ser removido: ");
        String nome = scanner.nextLine();
        
        // Busca o hotel pelo nome
        Hotel hotel = buscarHotel(hoteis, nome);
        if (hotel != null) {
            hoteis.remove(hotel);
            System.out.println("Hotel " + nome + " removido com sucesso.");
        } else {
            System.out.println("Erro: Hotel não encontrado.");
        }
    }

    public void gerenciarQuartos(Hotel hotel) {
        // Lógica para gerenciar quartos (adicionar/remover quartos no hotel)
        System.out.println("Gerenciando os quartos do hotel " + hotel.getNome());
    }

    public Hotel buscarHotel(List<Hotel> hoteis, String nome) {
        for (Hotel hotel : hoteis) {
            if (hotel.getNome().equalsIgnoreCase(nome)) {
                return hotel;
            }
        }
        return null;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }
}
