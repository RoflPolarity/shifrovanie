import java.util.ArrayList;
import java.util.List;

public class EnglishAphabet {
    private List<Character> alph = new ArrayList<>();
    private String alphArray = "abcdefghijklmnopqrstuvwxyz";
    EnglishAphabet(){for (int i = 0; i< alphArray.length(); i++) {alph.add(alphArray.charAt(i));}}

    public int getNum(char elem){return alph.indexOf(elem);}

    public char getCharByNum(int num){return alph.get(num);}

    public int getN(){return alph.size();}
}
