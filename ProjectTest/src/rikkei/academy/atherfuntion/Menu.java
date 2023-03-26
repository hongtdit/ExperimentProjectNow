package rikkei.academy.atherfuntion;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    String header;

    List<String> choices = new ArrayList<>();

    int width;
    int maxWidth = 50;
    int leftPadding;
    boolean preset = false;

    public Menu() {
        this.preset = false;
    }

    public Menu(int width) {
        this.width = width;
        this.preset = true;
    }

    public void addHeader(String header) {
        this.header = header;
        setMaxWidth();
    }

    public void addChoice(String choice) {
        choices.add(choice);
        setMaxWidth();
    }

    public void addPaddingLeft(int padding) {
        this.leftPadding = padding;
    }

    public void print() {

        try {
            printUpLine();

            printHeader();

            printMiddleLine();

            printBody();

            printBottomLine();
        } catch (Exception e) {
            System.out.println("Add more width");
        }

    }

    private void printBody() {

        String paddingLeft = "";
        for (int i = 0; i < leftPadding; i++) {
            paddingLeft += " ";
        }

        for (String c : choices) {
            String temp = String.format("|" + paddingLeft + "%-" + (maxWidth - leftPadding) + "s|", c);
            System.out.println(temp);
        }

    }

    private void printHeader() {

        int space = (maxWidth - header.length()) / 2;
        String head = "";
        if (space == 0) {
            head = String.format("|" + header + "|");
        } else {
            head = String.format("|%" + space + "s" + "%-" + (maxWidth - space) + "s|", "", header);
        }
        System.out.println(head);
    }


    private void setMaxWidth() {
        int max = 0;
        if (preset) {
            maxWidth = width > 100 ? width : maxWidth;
        } else {
            if (header.length() > max) {
                max = header.length();
            }
            for (String choice : choices) {
                if (choice.length() > max) {
                    max = choice.length();
                }
            }
            maxWidth = max > 100 ? width : maxWidth;
        }
    }

    private void printUpLine() {
        System.out.print(".");
        for (int i = 0; i < maxWidth; i++) {
            System.out.print("-");
        }
        System.out.println(".");
    }

    private void printMiddleLine() {
        System.out.print("|");
        for (int i = 0; i < maxWidth; i++) {
            System.out.print("-");
        }
        System.out.println("|");
    }

    private void printBottomLine() {
        System.out.print("'");
        for (int i = 0; i < maxWidth; i++) {
            System.out.print("-");
        }
        System.out.println("'");
    }
}
