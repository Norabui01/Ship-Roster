//Name: Ngoc Bui;
//Date: 11/15/2022;

import java.util.*;
import java.io.*;
import javax.swing.*;

public class Ship {
    private String name;
    private int year;
    private String country;

    public Ship(String nam, int yr, String ctr){
        name = nam;
        year = yr;
        country = ctr;

    }

    public String toString() {
        return name + ", " + year + ", flag: " + country;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getCountry() {
        return country;
    }

    public static void main(String[] args){
        String intro_prompt = "<html><h3>Welcome to Ship class program!</h3>" +
                              "First, this program will print out your ships roster from the csv file.<br>" +
                              "Then, you can search for ships of each country multiple<br> " +
                              "times until you want to quit.<br>" +
                              "Enjoy searching! Please click OK to start.";

        ImageIcon icon = new ImageIcon("big_ship.png");
        JOptionPane.showMessageDialog(null, intro_prompt,
                "              ".repeat(7) + "Introduction", 1, icon);

        ArrayList<Ship> myShips = new ArrayList<>();

        String fileName = "justShips.csv";
        File inFile = new File(fileName);

        if(!inFile.exists()) {
            String errorMessage = "!!!!!!!!!!!!!!!!!!!!\n" +
                    "The file " + fileName + "is not presented\n" +
                    "Check the spelling or location of file!\n" +
                    "!!!!!!!!!!!!!!!!!!!!\n";
            JOptionPane.showMessageDialog(null, errorMessage,
                    "            ".repeat(4) + "File missing", 1);
            System.exit(0);
        }
        {
            String message = "<html><h3>Can see the file!</h3>" +
                             "It is a good start, please click OK to continue.";
            JOptionPane.showMessageDialog(null, message,
                    "          ".repeat(4) + "Good start", 1);
        }

        try{
            Scanner inScan = new Scanner(inFile).useDelimiter("[\n,]");
            while(inScan.hasNext()){
                String tempName = inScan.next();
                int tempYear = inScan.nextInt();
                String tempCountry = inScan.next();
                myShips.add(new Ship(tempName, tempYear, tempCountry));
            }
            inScan.close();
        }
        catch(IOException ioe){
            String errorMessage = "!!!!!!!!!!!!!!!!!!!!!!!!!!\n" +
                                  "Trouble opening file: " + fileName +
                                  "!!!!!!!!!!!!!!!!!!!!!!!!!!";
            JOptionPane.showMessageDialog(null, errorMessage, "File missing", 2);
            System.exit(0);
        }

        String result = "Our fleets: ";
        for (int num = 0; num < myShips.size(); num++){
            result +=String.format("\n %2d : ", num + 1) + myShips.get(num).toString();
        }

        JOptionPane.showMessageDialog(null, result,
                "      ".repeat(6) + "Fleet Roster", 1);

        String[] options = {"Quit", "New country"};

        while(true) {
            String input = "<html><h3>Please enter the exact name of a country:</h3>" +
                           "We have 8 different countries which are: USA, UK, <br>" +
                           "USS, Japan, Germany, PortaRico, Brasil, Narnia.";
            String user_country = JOptionPane.showInputDialog(null, input,
                    "      ".repeat(8) + "Enter", 1);
            String result_country = "<html><h3>The ships of " + user_country + " are: </h3>";
            for (int num = 0; num < myShips.size(); num++) {
                if (myShips.get(num).getCountry().equalsIgnoreCase(user_country)){
                    result_country += "\n" + myShips.get(num).toString();
                }
            }

            icon = new ImageIcon("big_ship.png");
            int user_option = JOptionPane.showOptionDialog(null, result_country,
                    "       ".repeat(9) +"Ships of country", 1, 1,
                    icon, options, options[0]);
            if (options[user_option].equals("Quit")){
                break;
            }

        }

        String terminate_prompt = "<html><h3>Hi, the program is end! Hope you like it!</h3>" +
                                  "P/s: Do you think it is good enough to get full credit?";

        JOptionPane.showMessageDialog(null, terminate_prompt,
                "         ".repeat(10) + "Good Bye", 1, icon);


    }
}

