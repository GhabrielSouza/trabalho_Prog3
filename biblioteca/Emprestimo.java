package biblioteca;

public class Emprestimo {
    private Reserva reserva;

    public Emprestimo(Reserva reserva) {
        this.reserva = reserva;
    }

    @Override
    public String toString() {
        return "Emprestimo da reserva: " + reserva.toString();
    }
}