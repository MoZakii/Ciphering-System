package CipherImplementation.Vigenere;


import java.util.Random;

public class VigenereAutoKey extends Vigenere {
    public VigenereAutoKey(String plainText, String key) {
        super(plainText, key);
    }
    public VigenereAutoKey(String plainText){
        super(plainText, null);
        Key=GenerateRandomKey();
    }
    @Override
    String GetNewKey() {
        String temp=Key;
        for(int i=0;i<PlainText.length();++i)
        {
            char c=PlainText.charAt(i);
            if(Character.isAlphabetic(c))
            {
                temp+=c;
            }
        }

        temp=temp.toLowerCase();
        return temp;
    }

    @Override
    protected String GenerateRandomKey() {
        char GeneratedKey[] = new char[PlainText.length()/2] ;
        Random Rd = new Random() ;
        for ( int i = 0 ; i < PlainText.length()/2 ; i ++ ){
            int randomInt = Rd.nextInt(26)  ;
            GeneratedKey[i] = (char)(randomInt+'A') ;  ;
        }
        return String.valueOf(GeneratedKey) ;
    }

    @Override
    public String getKey() {
        return Key;
    }

    @Override
    public String Encrypt() {
        char[] encryptedData;
        char[] key;
        int k=0;


        Key=GetNewKey();

        encryptedData=new char[PlainText.length()];
        key=Key.toCharArray();

        for(int i=0;i<PlainText.length();++i)
        {
            k%=key.length;
            char tmp=PlainText.charAt(i);
            if(Character.isAlphabetic(tmp))
            {
                tmp = Caeser.EncryptChar(tmp,Character.toUpperCase(key[k]));
                k++;
            }
            encryptedData[i]=tmp;
        }
        String temp="";
        for(int i=0;i<encryptedData.length;++i)
        {
            temp+=encryptedData[i];
        }
        EncryptedText=temp;
        return temp;
    }

    @Override
    public String Decrypt() {
        int k=0;
        char[] key=Key.toCharArray();
        char[] decryptedData=new char[EncryptedText.length()];
        for(int i=0;i<EncryptedText.length();++i)
        {
            char tmp=EncryptedText.charAt(i);
            if(Character.isAlphabetic(tmp))
            {
                k%=key.length;
                tmp= Caeser.DecryptChar(tmp,key[k]);
                k++;
            }
            decryptedData[i]=tmp;
        }
        String temp="";
        for(int i=0;i<decryptedData.length;++i)
        {
            temp+=decryptedData[i];
        }
        return temp;
    }

}
