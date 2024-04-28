package business;

public class Security {

    private static final int DECALAGE = 0;

    public static String cryptingMethod(String word){
        int charactersNb = word.length();
        Character [] cryptCharacters = new Character[charactersNb];

        for(int iChar = 0; iChar < charactersNb;iChar++){
            int ascii = word.charAt(iChar);
            ascii += DECALAGE + iChar;
            cryptCharacters[iChar] = (char) ascii;
        }

        swap(cryptCharacters);
        return assembleWord(cryptCharacters);
    }

    public static String decryptingMethod(String word){
        int charactersNb = word.length();
        Character [] cryptCharacters = word.chars().mapToObj(ch -> (char) ch).toArray(Character[]::new);
        swap(cryptCharacters);
        for(int iChar = 0; iChar < charactersNb;iChar++){
            int ascii = cryptCharacters[iChar];
            ascii = ascii - DECALAGE - iChar;
            cryptCharacters[iChar] = (char) ascii;
        }
        return assembleWord(cryptCharacters);
    }

    private static void swap(Character [] characters){
        int charactersNb = characters.length;
        for(int iChar = 0; iChar < charactersNb/2;iChar++){
            char buffer = characters[iChar];
            characters[iChar] = characters[charactersNb-iChar-1];
            characters[charactersNb-iChar-1] = buffer;
        }
    }
    private static String assembleWord(Character [] characters){
        StringBuilder deCryptWord = new StringBuilder();
        int charactersNb = characters.length;
        for(int iChar = 0; iChar < charactersNb;iChar++){
            deCryptWord.append(characters[iChar]);
        }
        return deCryptWord.toString();
    }
}
