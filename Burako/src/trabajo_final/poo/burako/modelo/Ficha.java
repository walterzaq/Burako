package trabajo_final.poo.burako.modelo;

public class Ficha {
    private int numero;
    private Color color;

    public Ficha(int numero,Color color){
        this.numero = numero;
        this.color = color;
    }

    public int getNumero() {
        return numero;
    }

    public Color getColor() {
        return color;
    }

    /* Este metodo x ahora no lo implemento. Despues fijate de usarlo para cohesionar bien las clases despues.
    public verificarEsComodin();
    */

    public String toString(){
        String string = "hola";
        if(color == Color.COMODIN){
            string = "0-CO";
        }
        else if (color == Color.AMARILLO && numero == 1) {string = "1-AM";}
        else if (color == Color.AMARILLO && numero == 2) {string = "2-AM";}
        else if (color == Color.AMARILLO && numero == 3) {string = "3-AM";}
        else if (color == Color.AMARILLO && numero == 4) {string = "4-AM";}
        else if (color == Color.AMARILLO && numero == 5) {string = "5-AM";}
        else if (color == Color.AMARILLO && numero == 6) {string = "6-AM";}
        else if (color == Color.AMARILLO && numero == 7) {string = "7-AM";}
        else if (color == Color.AMARILLO && numero == 8) {string = "8-AM";}
        else if (color == Color.AMARILLO && numero == 9) {string = "9-AM";}
        else if (color == Color.AMARILLO && numero == 10) {string = "10-AM";}
        else if (color == Color.AMARILLO && numero == 11) {string = "11-AM";}
        else if (color == Color.AMARILLO && numero == 12) {string = "12-AM";}
        else if (color == Color.AMARILLO && numero == 13) {string = "13-AM";}
        else if (color == Color.ROJO && numero == 1) {string = "1-RO";}
        else if (color == Color.ROJO && numero == 2) {string = "2-RO";}
        else if (color == Color.ROJO && numero == 3) {string = "3-RO";}
        else if (color == Color.ROJO && numero == 4) {string = "4-RO";}
        else if (color == Color.ROJO && numero == 5) {string = "5-RO";}
        else if (color == Color.ROJO && numero == 6) {string = "6-RO";}
        else if (color == Color.ROJO && numero == 7) {string = "7-RO";}
        else if (color == Color.ROJO && numero == 8) {string = "8-RO";}
        else if (color == Color.ROJO && numero == 9) {string ="9-RO";}
        else if (color == Color.ROJO && numero == 10) {string = "10-RO";}
        else if (color == Color.ROJO && numero == 11) {string = "11-RO";}
        else if (color == Color.ROJO && numero == 12) {string = "12-RO";}
        else if (color == Color.ROJO && numero == 13) {string = "13-RO";}
        else if (color == Color.AZUL && numero == 1) {string = "1-AZ";}
        else if (color == Color.AZUL && numero == 2) {string = "2-AZ";}
        else if (color == Color.AZUL && numero == 3) {string = "3-AZ";}
        else if (color == Color.AZUL && numero == 4) {string = "4-AZ";}
        else if (color == Color.AZUL && numero == 5) {string = "5-AZ";}
        else if (color == Color.AZUL && numero == 6) {string = "6-AZ";}
        else if (color == Color.AZUL && numero == 7) {string = "7-AZ";}
        else if (color == Color.AZUL && numero == 8) {string = "8-AZ";}
        else if (color == Color.AZUL && numero == 9) {string = "9-AZ";}
        else if (color == Color.AZUL && numero == 10) {string = "10-AZ";}
        else if (color == Color.AZUL && numero == 11) {string = "11-AZ";}
        else if (color == Color.AZUL && numero == 12) {string = "12-AZ";}
        else if (color == Color.AZUL && numero == 13) {string = "13-AZ";}
        else if (color == Color.NEGRO && numero == 1) {string = "1-NE";}
        else if (color == Color.NEGRO && numero == 2) {string = "2-NE";}
        else if (color == Color.NEGRO && numero == 3) {string = "3-NE";}
        else if (color == Color.NEGRO && numero == 4) {string = "4-NE";}
        else if (color == Color.NEGRO && numero == 5) {string = "5-NE";}
        else if (color == Color.NEGRO && numero == 6) {string = "6-NE";}
        else if (color == Color.NEGRO && numero == 7) {string = "7-NE";}
        else if (color == Color.NEGRO && numero == 8) {string = "8-NE";}
        else if (color == Color.NEGRO && numero == 9) {string = "9-NE";}
        else if (color == Color.NEGRO && numero == 10) {string = "10-NE";}
        else if (color == Color.NEGRO && numero == 11) {string = "11-NE";}
        else if (color == Color.NEGRO && numero == 12) {string = "12-NE";}
        else if (color == Color.NEGRO && numero == 13) {string = "13-NE";}
        return string;
    }
}
