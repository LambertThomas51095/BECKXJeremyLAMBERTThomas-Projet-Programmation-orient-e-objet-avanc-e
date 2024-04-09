package business;

public class Security {
    // classe servant à crypter et décrypter.. => classe outils spécialisé en sécurité

    public static String cryptingeMethod1(String word){
        System.out.println("crypte");
        int charactersNb = word.length();
        Character [] cryptCharacter = new Character[charactersNb];
        // +7
        for(int iChar = 0; iChar < charactersNb;iChar++){
            int ascii = (int) word.charAt(iChar);
            ascii += 7;
            cryptCharacter[iChar] = (char) ascii;
        }
        // effet mirroir... véirifer si nbLettres paires ou pas si oui ok si pas -1
        for(int iChar = 0; iChar < charactersNb/2;iChar++){
            char buffer = cryptCharacter[iChar];
            cryptCharacter[iChar] = cryptCharacter[charactersNb-iChar-1];
            cryptCharacter[charactersNb-iChar-1] = buffer;
            System.out.println(cryptCharacter[iChar] + " <-> " + cryptCharacter[charactersNb-iChar-1]);
        }
        // +13
        for(int iChar = 0; iChar < charactersNb;iChar++){
            int ascii = (int) word.charAt(iChar);
            ascii += 13;
            cryptCharacter[iChar] = (char) ascii;
        }
        // créer le string
        StringBuilder cryptWord = new StringBuilder();
        for(int iChar = 0; iChar < charactersNb;iChar++){
            cryptWord.append(cryptCharacter[iChar]);
        }

        return cryptWord.toString();
    }

    public static String decryptingeMethod1(String word){
        System.out.println("decrypte");
        int charactersNb = word.length();
        Character [] cryptCharacter = new Character[charactersNb];
        // -13
        for(int iChar = 0; iChar < charactersNb;iChar++){
            int ascii = (int) word.charAt(iChar);
            ascii -= 13;
            cryptCharacter[iChar] = (char) ascii;
        }

        // effet mirroir...
        for(int iChar = 0; iChar < charactersNb/2;iChar++){
            char buffer = cryptCharacter[iChar];
            cryptCharacter[iChar] = cryptCharacter[charactersNb-iChar-1];
            cryptCharacter[charactersNb-iChar-1] = buffer;
            System.out.println(cryptCharacter[iChar] + " <-> " + cryptCharacter[charactersNb-iChar-1]);
        }
        // -7

        for(int iChar = 0; iChar < charactersNb;iChar++){
            int ascii = (int) word.charAt(iChar);
            ascii -= 7;
            cryptCharacter[iChar] = (char) ascii;
        }


        // créer le string
        StringBuilder deCryptWord = new StringBuilder();
        for(int iChar = 0; iChar < charactersNb;iChar++){
            deCryptWord.append(cryptCharacter[iChar]);
        }

        return deCryptWord.toString();
    }
}
