import java.util.Random;

public class ID {
    private static String abc = "abcdef";
    private static Random ran = new Random();;

    private ID(){

    }

    public static String genID(int base){
        StringBuilder v = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            char c = abc.charAt(ran.nextInt(abc.length()));
            v.append(ran.nextInt(base)).append(c);
        }
        return v.toString();
    }
}
