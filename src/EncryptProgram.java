//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class EncryptProgram {


    private Scanner scanner;
    private Random random;
    private ArrayList<Character> list;
    private ArrayList<Character> shuffledList;
    private char character;
    private String line;
    private char[] letters;
    private String inputKey;


    EncryptProgram() {
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        this.list = new ArrayList();
        this.shuffledList = new ArrayList();
        this.character = ' ';
        this.newKey();
        this.askQuestion();
    }

    private void askQuestion() {
        while (true) {
            System.out.println("*************************************************");
            System.out.println("What do you want to do?");
            System.out.println("(N)ewKey, (G)etKey, (E)ncrypt, (D)ecrypt, (S)etKey, (Q)uit");
            char response = Character.toUpperCase(this.scanner.nextLine().charAt(0));
            switch (response) {
                case 'D':
                    this.decrypt();
                    break;
                case 'E':
                    this.encrypt();
                    break;
                case 'S':
                    this.setKey();
                    break;
                case 'G':
                    this.getKey();
                    break;
                case 'N':
                    this.newKey();
                    break;
                case 'Q':
                    this.quit();
                default:
                    System.out.println("Not a valid answer");
                    break;
            }
        }
    }

    void newKey() {
        this.character = ' ';
        this.list.clear();
        this.shuffledList.clear();

        for (int i = 32; i < 127; ++i) {
            this.list.add(this.character);
            ++this.character;
        }

        this.shuffledList = new ArrayList(this.list);
        Collections.shuffle(this.shuffledList);
        System.out.println("A new key has been generated!");
    }

    private void getKey() {
        System.out.println("Key: ");
        Iterator var1 = this.list.iterator();

        Character x;
        while (var1.hasNext()) {
            x = (Character) var1.next();
            System.out.print(x);
        }

        System.out.println();
        var1 = this.shuffledList.iterator();

        while (var1.hasNext()) {
            x = (Character) var1.next();
            System.out.print(x);
        }

        System.out.println();
    }

    private void encrypt() {
        System.out.println("Enter a message to be encrypted: ");
        String message = this.scanner.nextLine();
        this.letters = message.toCharArray();

        int j;
        for (int i = 0; i < this.letters.length; ++i) {
            for (j = 0; j < this.list.size(); ++j) {
                if (this.letters[i] == (Character) this.list.get(j)) {
                    this.letters[i] = (Character) this.shuffledList.get(j);
                    break;
                }
            }
        }

        System.out.println("Encrypted: ");
        char[] var6 = this.letters;
        j = var6.length;

        for (int var4 = 0; var4 < j; ++var4) {
            char x = var6[var4];
            System.out.print(x);
        }

        System.out.println();
    }

    private void decrypt() {
        System.out.println("Enter a message to be decrypted: ");
        String message = this.scanner.nextLine();
        this.letters = message.toCharArray();

        int j;
        for (int i = 0; i < this.letters.length; ++i) {
            for (j = 0; j < this.shuffledList.size(); ++j) {
                if (this.letters[i] == (Character) this.shuffledList.get(j)) {
                    this.letters[i] = (Character) this.list.get(j);
                    break;
                }
            }
        }

        System.out.println("Decrypted: ");
        char[] var6 = this.letters;
        j = var6.length;

        for (int var4 = 0; var4 < j; ++var4) {
            char x = var6[var4];
            System.out.print(x);
        }

        System.out.println();
    }

    private void quit() {
        System.out.println("Thank you pal, have a nice day!");
        System.exit(0);
    }

    private void setKey() {
        System.out.println("Type your key: ");
        String inputKey = scanner.nextLine();

        if (inputKey.length() != 95) { // Garante que a chave tenha o tamanho correto.
            System.out.println("Invalid key! The key must have exactly 95 characters.");
            return;
        }

        this.list.clear();
        this.shuffledList.clear();

        // Popula a lista com caracteres padrão (32 a 126).
        for (int i = 32; i < 127; i++) {
            this.list.add((char) i);
        }

        // Adiciona a nova chave como a lista embaralhada.
        for (char c : inputKey.toCharArray()) {
            this.shuffledList.add(c);
        }

        System.out.println("Your new key is set!");
        System.out.println("Your key is: " + inputKey);
        decrypt();
    }




}

