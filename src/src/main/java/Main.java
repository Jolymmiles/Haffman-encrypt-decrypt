import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите словарь(слово):");
        String test = scanner.nextLine();
        HuffmanCode newCode = new HuffmanCode();
        int[] charFreqs = new int[9999];
        for (char c : test.toCharArray())
            charFreqs[c]++;
        HuffmanTree tree = newCode.buildTree(charFreqs);
        newCode.printCodes(tree, new StringBuffer());
        System.out.println("Введите что расшифровать: ");
        String myS = scanner.nextLine();
        System.out.println(newCode.decrypt(myS));
    }



}