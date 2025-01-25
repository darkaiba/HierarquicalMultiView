
package websensorhtml;

public class Evento {
    private int evento1;
    private int evento2;

    public Evento() {
    }

    public int getEvento1() {
        return evento1;
    }

    public void setEvento1(int evento1) {
        this.evento1 = evento1;
    }

    public int getEvento2() {
        return evento2;
    }

    public void setEvento2(int evento2) {
        this.evento2 = evento2;
    }

    @Override
    public String toString() {
        return "\n{" + "'from':" + evento1 + ", 'to':" + evento2 + "}";
    }
}
