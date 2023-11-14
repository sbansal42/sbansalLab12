import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Encrypter {

    private int shift;
    private String encrypted;

    /**
     * Default Constructor
     */
    public Encrypter() {
        this.shift = 1;
        this.encrypted = "";
    }

    /**
     * Non-default Constructor
     * @param s - custom shift amount
     */
    public Encrypter(int s) {
        this.shift = s;
        this.encrypted = "";
    }

    /**
     * Encrypts the content of a file and writes the result to another file.
     *
     * @param inputFilePath      the path to the file containing the text to be encrypted
     * @param encryptedFilePath the path to the file where the encrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void encrypt(String inputFilePath, String encryptedFilePath) throws Exception {
        //TODO: Call the read method, encrypt the file contents, and then write to new file
        String isDecrypted = readFile(inputFilePath);
        String isEncrypted = caesarCipher(isDecrypted, shift);
        writeFile(isEncrypted, encryptedFilePath);
    }

    /**
     * Decrypts the content of an encrypted file and writes the result to another file.
     *
     * @param messageFilePath    the path to the file containing the encrypted text
     * @param decryptedFilePath the path to the file where the decrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void decrypt(String messageFilePath, String decryptedFilePath) throws Exception {
        //TODO: Call the read method, decrypt the file contents, and then write to new file
        String isEncrypted = readFile(messageFilePath);
        String isDecrypted = caesarCipher(isEncrypted, -shift);
        writeFile(isDecrypted, decryptedFilePath);
    }

    /**
     * Reads the content of a file and returns it as a string.
     *
     * @param filePath the path to the file to be read
     * @return the content of the file as a string
     * @throws Exception if an error occurs while reading the file
     */
    private static String readFile(String filePath) throws Exception {
        StringBuilder message = new StringBuilder();
        //TODO: Read file from filePath
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                message.append(scanner.nextLine()).append("\n");
            }
        }
        return message.toString();
     }

    /**
     * Writes data to a file.
     *
     * @param data     the data to be written to the file
     * @param filePath the path to the file where the data will be written
     */
    private static void writeFile(String data, String filePath) throws IOException {
        //TODO: Write to filePath
    	try (FileWriter written = new FileWriter(new File(filePath))) {
            written.write(data);
        }
    }
    private static String caesarCipher(String text, int shift) {
        StringBuilder cipher = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                int originalPos = c - 'a';
                int newPos = (originalPos + shift) % 26;
                char newCharacter = (char) ('a' + newPos);
                cipher.append(newCharacter);
            } else {
                cipher.append(c);
            }
        }
        return cipher.toString();
    }
    /**
     * Returns a string representation of the encrypted text.
     *
     * @return the encrypted text
     */
    @Override
    public String toString() {
        return encrypted;
    }
}
