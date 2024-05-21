package business;

public class Security {

    private static final Integer DECALAGE = 2003;

    public static String cryptingMethod(String word){
        word = word.toUpperCase();
        Integer charactersNb = word.length();
        Character [] cryptCharacters = new Character[charactersNb];

        for(Integer iChar = 0; iChar < charactersNb;iChar++){
            int ascii = word.charAt(iChar);
            ascii += DECALAGE + iChar;
            cryptCharacters[iChar] = (char) ascii;
        }

        swap(cryptCharacters);
        return assembleWord(cryptCharacters);
    }

    public static String decryptingMethod(String word){
        Integer charactersNb = word.length();
        Character [] cryptCharacters = word.chars().mapToObj(ch -> (char) ch).toArray(Character[]::new);
        swap(cryptCharacters);
        for(Integer iChar = 0; iChar < charactersNb;iChar++){
            int ascii = cryptCharacters[iChar];
            ascii = ascii - DECALAGE - iChar;
            cryptCharacters[iChar] = (char) ascii;
        }
        return assembleWord(cryptCharacters);
    }

    private static void swap(Character [] characters){
        Integer charactersNb = characters.length;
        for(Integer iChar = 0; iChar < charactersNb/2;iChar++){
            char buffer = characters[iChar];
            characters[iChar] = characters[charactersNb-iChar-1];
            characters[charactersNb-iChar-1] = buffer;
        }
    }
    private static String assembleWord(Character [] characters){
        StringBuilder deCryptWord = new StringBuilder();
        for (Character character : characters) {
            deCryptWord.append(character);
        }
        return deCryptWord.toString();
    }
}
