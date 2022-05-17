public class Producto {
     String name;
     String id;
     int quantity;

     String description;
    public Producto(){

    }

    public Producto(String nome, int quantidade) {
        this.name = nome;
        this.id = ID.genID(10);
        this.quantity = quantidade;
    }
}
