package trabajo_final.poo.burako.modelo;

public class Puntaje {
    private String nombre;
    private int puntos;

    public Puntaje(int puntos, String nombre) {
        this.puntos = puntos;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

}
