import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File f1 = null, f2 = null;
        String mode = "enc";
        int key = 0;
        String message = "";
        boolean flagRead = false;
        boolean flagWrite = false;
        int counter = 0;
        for (String arg : args) {
            switch (arg) {
                case "-mode":
                    mode = args[counter + 1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[counter + 1]);
                    break;
                case "-data":
                    message = args[counter + 1];
                    break;
                case "-in":
                    f1 = new File(args[counter + 1]);
                    flagRead = true;
                    break;
                case "-out":
                    f2 = new File(args[counter + 1]);
                    flagWrite = true;
                    break;
            }
            ++counter;
        }

        switch (mode) {
            case "enc":
                if (flagRead) {
                    try {
                        Scanner read = new Scanner(f1);
                        if (flagWrite) {
                            try {
                                PrintWriter write = new PrintWriter(f2);
                                while (read.hasNextLine()) {
                                    write.println(String.valueOf(encrypt(read.nextLine().toCharArray(), key)));
                                }
                                write.close();
                            } catch (IOException e) {
                                System.out.print("Write File Does Not Exist!!");
                            }
                        } else {
                            while (read.hasNextLine()) {
                                System.out.println(String.valueOf(encrypt(read.nextLine().toCharArray(), key)));
                            }
                        }
                        read.close();
                    } catch (FileNotFoundException e) {
                        System.out.print("Read File Does Not Exist!!");
                    }
                } else {
                    if (flagWrite) {
                        try {
                            PrintWriter write = new PrintWriter(f2);
                            write.println(String.valueOf(encrypt(message.toCharArray(), key)));
                            write.close();
                        } catch (IOException e) {
                            System.out.print("Write File Does Not Exist!!");
                        }
                    } else {
                        System.out.println(String.valueOf(encrypt(message.toCharArray(), key)));
                    }
                }
                break;
            case "dec":
                if (flagRead) {
                    try {
                        Scanner read = new Scanner(f1);
                        if (flagWrite) {
                            try {
                                PrintWriter write = new PrintWriter(f2);
                                while (read.hasNextLine()) {
                                    write.println(String.valueOf(decrypt(read.nextLine().toCharArray(), key)));
                                }
                                write.close();
                            } catch (IOException e) {
                                System.out.print("Write File Does Not Exist!!");
                            }
                        } else {
                            while (read.hasNextLine()) {
                                System.out.println(String.valueOf(decrypt(read.nextLine().toCharArray(), key)));
                            }
                        }
                        read.close();
                    } catch (FileNotFoundException e) {
                        System.out.print("Read File Does Not Exist!!");
                    }
                } else {
                    if (flagWrite) {
                        try {
                            PrintWriter write = new PrintWriter(f2);
                            write.println(String.valueOf(decrypt(message.toCharArray(), key)));
                            write.close();
                        } catch (IOException e) {
                            System.out.print("Write File Does Not Exist!!");
                        }
                    } else {
                        System.out.println(String.valueOf(decrypt(message.toCharArray(), key)));
                    }
                }
                break;
        }
    }

    private static char[] encrypt(char[] message, int key) {
        char[] encryptedMessage = new char[message.length];
        int counter = 0;
        for (char letter : message) {
            int encode = (letter + key) % 256;
            encryptedMessage[counter] = ((char) encode);
            ++counter;
        }
        return (encryptedMessage);
    }

    private static char[] decrypt(char[] message, int key) {
        char[] decryptedMessage = new char[message.length];
        int counter = 0;
        for (char letter : message) {
            int decode = (letter - key) % 256;
            decryptedMessage[counter] = ((char) decode);
            ++counter;
        }
        return (decryptedMessage);
    }
}
