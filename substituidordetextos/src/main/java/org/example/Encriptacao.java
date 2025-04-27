package org.example;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class encriptacao {

    KeyGenerator keygenerator = null;
    Cipher cifraAES = null;
    SecretKey chaveAES = null;
    static String IV = "AAAAAAAAAAAAAAAA";
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public EncriptaDecriptaAES(int valorKey) throws NoSuchAlgorithmException,
            UnsupportedEncodingException, NoSuchProviderException, NoSuchPaddingException {

        keygenerator = KeyGenerator.getInstance("AES");
        keygenerator.init(valorKey);
        chaveAES = keygenerator.generateKey();

        // Isso nao parece dar certo!
        // System.out.println(((chaveAES.toString()).getBytes("UTF-8")).length);

        System.out.println();
        System.out.println("Tamanho da chave: " + chaveAES.getEncoded().length);
        System.out.println("Chave: " + bytesToHex(chaveAES.getEncoded()));

        // Cria a cifra
        cifraAES = Cipher.getInstance("AES/CBC/PKCS5Padding");

        System.out.println("Tamanho do bloco: " + cifraAES.getBlockSize());
        System.out.println();

    }

    public void encrypt(String srcPath, String destPath) throws UnsupportedEncodingException,
            InvalidKeyException, InvalidAlgorithmParameterException, FileNotFoundException,
            IOException, IllegalBlockSizeException, BadPaddingException {


        File rawFile = new File(srcPath);
        File imagemEncriptada = new File(destPath);
        InputStream inStream = null;
        OutputStream outStream = null;
        cifraAES.init(Cipher.ENCRYPT_MODE, chaveAES,
                //Inicializa a cifra para o processo de encriptacao
                new IvParameterSpec(IV.getBytes("UTF-8")));

        //Inicializa o input e o output streams
        inStream = new FileInputStream(rawFile);
        outStream = new FileOutputStream(imagemEncriptada);

        byte[] buffer = new byte[256];
        int len;

        while ((len = inStream.read(buffer)) > 0) {
            //Para criptografar/descriptografar varios blocos usa-se o metodo update().
            outStream.write(cifraAES.update(buffer, 0, len));
            outStream.flush();
        }

        //Depois de tudo feito chamamos o metodo doFinal().
        outStream.write(cifraAES.doFinal());
        inStream.close();
        outStream.close();

    }

    public void decrypt(String srcPath, String destPath) throws InvalidKeyException,
            InvalidAlgorithmParameterException, UnsupportedEncodingException, FileNotFoundException,
            IOException, IllegalBlockSizeException, BadPaddingException {


        File encryptedFile = new File(srcPath);
        File decryptedFile = new File(destPath);
        InputStream inStream = null;
        OutputStream outStream = null;

        //Inicializa o cipher para decriptografar
        cifraAES.init(Cipher.DECRYPT_MODE, chaveAES, new IvParameterSpec(IV.getBytes("UTF-8")));

        //Inicializa o input e o output streams
        inStream = new FileInputStream(encryptedFile);
        outStream = new FileOutputStream(decryptedFile);

        byte[] buffer = new byte[256];
        int len;

        while ((len = inStream.read(buffer)) > 0) {
            outStream.write(cifraAES.update(buffer, 0, len));
            outStream.flush();
        }

        outStream.write(cifraAES.doFinal());
        inStream.close();
        outStream.close();

    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException,
            NoSuchPaddingException, UnsupportedEncodingException, NoSuchProviderException,
            InvalidKeyException, InvalidAlgorithmParameterException, IOException,
            FileNotFoundException, IllegalBlockSizeException, BadPaddingException {

        //Se mudar o pc, alterar esta linha para o caminho certo
        String directoryPath = "";

        long tempInicial = 0;
        long tempFinal = 0;
        long dif = 0;

        //Passa como parametro o tamanho da chave de 128, 192 ou 256 bits
        EncriptaDecriptaAES chave = new EncriptaDecriptaAES(256);

        System.out.println("Iniciando Codificacao...");
        tempInicial = System.nanoTime();

        for (int i = 1; i <= 10; i++) {
            // String imgOriginal = "imagem_" + i + ".jpg";
            String imgOriginal = "imagem_1.jpg";

            //Nome do arquivo encriptado
            String imgEncriptada = "imgEncripAES_" + i + ".jpg";
            chave.encrypt(directoryPath + imgOriginal, directoryPath + imgEncriptada);

        }

        tempFinal = System.nanoTime();
        dif = (tempFinal - tempInicial);
        double segundos = (double)dif / 1000000000.0;

        System.out.println("Tempo de codificacao: " + segundos + " segundos.");
        System.out.println("Codificacao Finalizada...");

        // tempInicial = 0;
        // tempFinal = 0;
        // dif = 0;

        System.out.println();
        System.out.println("Iniciando Decodificacao...");
        tempInicial = System.nanoTime();

        for (int i = 1; i <= 10; i++) {
            String imgEncriptada = "imgEncripAES_" + i + ".jpg"; //Nome do arquivo encriptado
            String imgDecriptada = "imgDecripAES_" + i + ".jpg"; //Nome do arquivo descriptado
            chave.decrypt(directoryPath + imgEncriptada, directoryPath + imgDecriptada);
        }

        tempFinal = System.nanoTime();
        dif = (tempFinal - tempInicial);
        segundos = (double)dif / 1000000000.0;

        System.out.println("Tempo de decodificacao: " + segundos + " segundos.");
        System.out.println("Decodificacao Finalizada...");

    }
}
