import java.util.ArrayList;
import java.util.List;

public class EnglishAphabet {
    private List<Character> alph = new ArrayList<>();
    private char[] alphArray = {'a','b','c','d','e','f','g','h',
            'i','j','k','l','m','n','o','p','q','r','s',
            't','u','v','w','x','y','z'};
    EnglishAphabet(){for (char c : alphArray) {alph.add(c);}}

    public int getNum(char elem){return alph.indexOf(elem);}

    public char getCharByNum(int num){return alph.get(num);}

    public int getN(){return alph.size();}
}
