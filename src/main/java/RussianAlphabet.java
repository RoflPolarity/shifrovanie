import java.util.ArrayList;
import java.util.List;

public class RussianAlphabet {
    private List<Character> alph = new ArrayList<>();
    private char[] alphArray = {'а','б','в','г','д','е','ё','ж','з','и','й','к',
            'л','м','н','о','п','р','с','т','у','ф','х','ц','ч','ш','щ','ъ','ы','ь','э','ю','я'};

    RussianAlphabet(){for (char c : alphArray) {alph.add(c);}}

    public int getNum(char elem){return alph.indexOf(elem);}

    public char getCharByNum(int num){return alph.get(num);}

    public int getN(){return alph.size();}
}
