package biblioteca;

public interface IReservouLivro {
    public void adicionar(ILivroReservado livroReservado);
    public void notificar(Reserva reserva);
    public void remover(ILivroReservado livroReservado);
} 
