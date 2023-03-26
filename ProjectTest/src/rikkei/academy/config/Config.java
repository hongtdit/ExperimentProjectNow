package rikkei.academy.config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Config<T> {
    public static String PATH = "D:\\MD2\\CASE-MD2-MUSIC\\src\\rikkei\\academy\\data\\account\\";
    public static Scanner scanner(){
        Scanner scanner = new Scanner(System.in);
        return scanner;
    }

    public static int getIntegerInput() {
        String choiceString = "";
        while (!choiceString.matches("\\d+")) {
            choiceString = scanner().nextLine();
            if (!choiceString.matches("\\d+")) {
                System.out.println("Please enter a valid input");
            }
        }

        return Integer.parseInt(choiceString);
    }

    public static String getStringInput() {
        String input = " ";
        while (input.trim().isEmpty() || input.contains(" ")) {
            input = scanner().nextLine();
            if (input.trim().isEmpty() || input.contains(" ")) {
                System.out.println("Please enter a valid input");
            }
        }

        return input;
    }

    public List<T> readFile(String pathFile){
        List<T> tList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(pathFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            tList = (List<T>) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();

        }catch (Exception e){
            e.getMessage();
        }
        return tList;
    }
    public void writeFile(String pathFile, List<T> tList){
        try {
            FileOutputStream fileOutputStream =new FileOutputStream(pathFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(tList);
            fileOutputStream.close();
            objectOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
