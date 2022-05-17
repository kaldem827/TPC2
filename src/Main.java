import config.DB;
import util.Util;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Util dev = new Util();

        DB db = new DB();
        db.open();
        int choice = 0;


        while (choice < 6){
            dev.prompt();
            choice =  scanner.nextInt();

            if(choice <= 0){
                System.out.println("Escolha invalida!!");
            }
            if(choice == 1){

                HashMap<String, Object> map = new HashMap<>();
                System.out.print("Digite o nome to producto: ");
                String nome = scanner.next();
                System.out.print("Digite a quantidade: ");
                int qtd = scanner.nextInt();
                Producto prod = new Producto(nome, qtd);
                map.put("ID", prod.id);
                map.put("Nome", prod.name);
                map.put("Quantidade", prod.quantity);
                db.save(map);
                System.out.println("Producto adicionado!! Digite OK para continuar...");
                scanner.next();
            }
            if(choice == 2){
                boolean exists = false;
                System.out.println("Digite o nome do producto: ");
                String nome = scanner.next();
                for (HashMap<String, Object> prod : db.getListaProductos()) {
                    if(prod.get("Nome").toString().equalsIgnoreCase(nome)){
                        System.out.println("**************Detalhes**************");
                        System.out.println("ID: " + prod.get("ID"));
                        System.out.println("Nome: " + prod.get("Nome"));
                        System.out.println("Quantidade: " + prod.get("Quantidade"));
                        break;
                    }
                }
                if(!exists){
                    System.out.println("Producto nao econtrado!!!.(Verifique a escrita)");
                }
            }
            if(choice == 3){
                boolean exists = false;

                Producto prod;
                System.out.print("Digite o nome do producto: ");
                String nome = scanner.next().toLowerCase();
                for(var pr : db.getListaProductos()){
                    if(pr.get("Nome").toString().toLowerCase().equals(nome)){
                        System.out.print("Digite a quantidade: ");
                        int qtd = scanner.nextInt();
                        pr.replace("Quantidade", qtd);
                        System.out.println("Producto atualizado!!!");
                        exists = true;
                        break;
                    }
                }

                if(!exists){
                    System.out.println("Producto nao econtrado!!!.(Verifique a escrita)");
                }
            }
            if(choice == 4){
                boolean exists = false;

                Producto prod;
                System.out.print("Digite o nome do producto: ");
                String nome = scanner.next().toLowerCase();
                for(int i = 0; i < db.getListaProductos().size(); i++){
                    if(db.getListaProductos().get(i).get("Nome").toString().toLowerCase().equals(nome)){
                        db.remove(i);
                        System.out.println("Producto removido!!!");
                        exists = true;

                        break;
                    }
                }

                if(!exists){
                    System.out.println("Producto nao econtrado!!!.(Verifique a escrita)");
                }
            }
            if(choice == 5){

                if(db.getListaProductos().isEmpty()){
                    System.out.println("Sem productos guardados!!");
                }else
                    for(var prod : db.getListaProductos()){
                        System.out.println("ID: " + prod.get("ID"));
                        System.out.println("Nome: " + prod.get("Nome"));
                        System.out.println("Quantidade: " + prod.get("Quantidade"));
                        System.out.println();
                    }
            }
            System.out.println();
        }
        System.out.println("Terminando");

        db.close();
    }

}
