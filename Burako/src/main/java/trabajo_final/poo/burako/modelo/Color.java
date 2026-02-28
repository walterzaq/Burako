package trabajo_final.poo.burako.modelo;

public class Color {
    private String tipo;

    public Color(String tipo){
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString(){
        return tipo;
    }
}
