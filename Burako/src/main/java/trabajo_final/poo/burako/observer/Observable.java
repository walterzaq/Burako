package trabajo_final.poo.burako.observer;
import trabajo_final.poo.burako.modelo.Evento;

public interface Observable {
    void agregarObservador(Observador observador);
    void quitarObservador(Observador observador);
    void notificarObservadores(Evento evento);
}
