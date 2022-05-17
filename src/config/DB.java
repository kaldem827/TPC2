package config;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DB {
    ArrayList<HashMap<String, Object>> listaProductos;
    File file;
    FileWriter fileWriter;
    FileReader fileReader;
    BufferedReader bufferedReader;
    StringBuffer stringBuffer;
    boolean isOpen;

    List<String> fromFile;
    public DB(){
        listaProductos = new ArrayList<>();
    }
    public void open(){
        isOpen = true;
        file = new File("c:/users/acer's/desktop/produtos.txt");
        try{
            if(!isOpen){
                throw new Exception("Database not opened");
            }

            if(!file.createNewFile()) {
                fromFile = new ArrayList<>();
                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);
                stringBuffer = new StringBuffer();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    if(!line.isBlank()){
                        fromFile.add(line);
                    }

                }
                listaProductos = stringToMap();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<HashMap<String, Object>> getListaProductos(){

        try{
            if(!isOpen){
                throw new Exception("Database not opened");
            }
        }catch (Exception e){

        }

        return listaProductos;
    }
    public void close(){
        System.out.println(listaProductos);

        try{
            if(!isOpen){
                throw new Exception("Database not opened");
            }

            System.out.println("Todos Productos salvos!!!");
            fileWriter = new FileWriter(file);

            for (var prod :
                    listaProductos) {
                fileWriter.write(prod.toString() + "\n");
            }

            isOpen = false;
            fileReader.close();
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void save(HashMap<String, Object> map){

        boolean canSave = true;
            for(var l : listaProductos){
                if(Objects.equals(l.get("Nome").toString(), map.get("Nome").toString())){
                    canSave = false;
                    break;
                }
            }
        if(canSave) listaProductos.add(map);
    }

    public void remove(int index){
        listaProductos.remove(index);
    }

    private ArrayList<HashMap<String, Object>> stringToMap(){
        ArrayList<HashMap<String, Object>> newList = new ArrayList<>();
        String values;
        String[] keyValueP = new String[fromFile.size()];
        int c = 0;
        for (var h :
                this.fromFile) {

            keyValueP[c] = h;
            c++;
        }


        for (int i = 0; i < keyValueP.length; i++) {
            HashMap<String, Object> temp = new HashMap<>();

            String value = keyValueP[i].substring(1, keyValueP[i].length() - 1);
            String[] k = value.split(",");

            temp.put(k[0].split("=")[0].trim(), k[0].split("=")[1].trim());
            temp.put(k[1].split("=")[0].trim(), k[1].split("=")[1].trim());
            temp.put(k[2].split("=")[0].trim(), k[2].split("=")[1].trim());
            newList.add(temp);
        }
            return newList;
    }
}
