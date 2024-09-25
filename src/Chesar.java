import java.io.*;
import java.util.ArrayList;

public class Chesar {
    private static ArrayList<Character> encrypteMessage = new ArrayList<Character>();
    private static ArrayList<Character> decrypteMessage = new ArrayList<Character>();

    public static void getEncrypteMessage(String File, int shift, boolean right) throws Exception {
        if (shift > 25) throw new Exception("Сдвиг не больше 25 символов");
        try(FileInputStream fin = new FileInputStream(File))
        {
            byte[] buffer = new byte[4096];
            int count;
            while((count=fin.read(buffer))!=-1){
                for(int i=0; i<count;i++){
                    if (!(buffer[i] >= 97 && buffer[i] <= 122) &&
                            !(buffer[i] >= 65 && buffer[i] <= 90)) {
                        encrypteMessage.add((char)buffer[i]);
                    }
                    else if ((buffer[i] + shift) > 122 && right && buffer[i] <= 122) {
                        encrypteMessage.add((char)(buffer[i] - (26 - shift)));
                    }
                    else if ((buffer[i] + shift) > 90 && right && buffer[i] <= 90) {
                        encrypteMessage.add((char)(buffer[i] - (26 - shift)));
                    }
                    else if ((buffer[i] - shift) < 97 && !right && buffer[i] >= 97) {
                        encrypteMessage.add((char)(buffer[i] + (26 - shift)));
                    }
                    else if ((buffer[i] - shift) < 65 && !right && buffer[i] >= 65) {
                        encrypteMessage.add((char)(buffer[i] + (26 - shift)));
                    }
                    else if (right){
                        encrypteMessage.add((char)(buffer[i] + shift));
                    }
                    else if (!right) {
                        encrypteMessage.add((char)(buffer[i] - shift));
                    }
                }
            }
            printEncrypteMessage(encrypteMessage);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private static void printEncrypteMessage(ArrayList<Character> encrypteMessage) {
        try(FileOutputStream fos=new FileOutputStream("EncrMes.txt"))
        {
            String str = "";
            // перевод строки в байты
            for (char a:encrypteMessage) {
                str += String.valueOf(a);
            }
            byte[] buffer = str.getBytes();

            fos.write(buffer, 0, buffer.length);
            System.out.println("The encryption file has been written");
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void GetDescriptionMessage(String File, int shift, boolean right) throws Exception {
        if (shift > 25) throw new Exception("Сдвиг не больше 25 символов");
        try(FileInputStream fin = new FileInputStream(File))
        {
            byte[] buffer = new byte[4096];
            int count;
            while((count=fin.read(buffer))!=-1){
                for(int i=0; i<count;i++){
                    if (!(buffer[i] >= 97 && buffer[i] <= 122) &&
                            !(buffer[i] >= 65 && buffer[i] <= 90)) {
                        decrypteMessage.add((char)buffer[i]);
                    }
                    else if ((buffer[i] + shift) > 122 && !right && buffer[i] <= 122) {
                        decrypteMessage.add((char)(buffer[i] - (26 - shift)));
                    }
                    else if ((buffer[i] + shift) > 90 && !right && buffer[i] <= 90) {
                        decrypteMessage.add((char)(buffer[i] - (26 - shift)));
                    }
                    else if ((buffer[i] - shift) < 97 && right && buffer[i] >= 97) {
                        decrypteMessage.add((char)(buffer[i] + (26 - shift)));
                    }
                    else if ((buffer[i] - shift) < 65 && right && buffer[i] >= 65) {
                        decrypteMessage.add((char)(buffer[i] + (26 - shift)));
                    }
                    else if (!right){
                        decrypteMessage.add((char)(buffer[i] + shift));
                    } else if (right) {
                        decrypteMessage.add((char)(buffer[i] - shift));
                    }
                }
            }
            printDecrypteMessage(decrypteMessage);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private static void printDecrypteMessage(ArrayList<Character> encrypteMessage) {
        try(FileOutputStream fos=new FileOutputStream("DescMes.txt"))
        {
            String str = "";
            // перевод строки в байты
            for (char a:encrypteMessage) {
                str += String.valueOf(a);
            }
            byte[] buffer = str.getBytes();

            fos.write(buffer, 0, buffer.length);
            System.out.println("The descryption file has been written");
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
