package trabajo_final.poo.burako.modelo;

public class Ficha {
    private int numero;
    private Color color;
    private boolean esComodin;

    public Ficha(int numero,Color color,boolean esComodin){
        this.numero = numero;
        this.color = color;
        this.esComodin = esComodin;
    }

    public boolean comprobarEsComodin() {
        return esComodin;
    }

    public void setEsComodin(boolean esComodin) {
        this.esComodin = esComodin;
    }

    public int getNumero() {
        return numero;
    }

    public Color getColor() {
        return color;
    }

    public String toString(){
        return Integer.toString(numero) + "-" + color;
    }
}
