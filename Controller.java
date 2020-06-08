package sample;

import javafx.scene.control.*;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Controller{

    public Button add, load, encrypt, delete, wipe, decrypt;
    public Label labelka;
    public TextArea textNote;
    public TextField nameNote;

    public void addNote(){
        try{
            File folder = new File("..\\Notatki");

            if(!folder.exists()){
                folder.mkdir();
            }

            String name = nameNote.getText();
            String nazwa = "";

            nazwa = name + " " + LocalDate.now() + " " + LocalTime.now().getHour() + "-" + LocalTime.now().getMinute() + "-" + LocalTime.now().getSecond();
            nazwa.trim();


            File notatka = new File(folder.getAbsolutePath() + "\\" + nazwa + ".txt");
            notatka.createNewFile();

            FileWriter nowa = new FileWriter(folder.getAbsolutePath() + "\\" + nazwa + ".txt", true);

            Scanner sc = new Scanner(textNote.getText());
            while(sc.hasNext()){
                nowa.write(sc.nextLine() + "\n");
            }
            nowa.close();
            nameNote.setText("");
            labelka.setText(nazwa);
            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadNote(){
            try {
                File folder = new File("..\\Notatki");
                String[] pliki = folder.list();
                String dostepne = "Your notes: \n";

                for (String plik : pliki) {
                    plik = plik.substring(0, plik.length() - 4);
                    dostepne = dostepne + plik + "\n";
                }

                if (nameNote.getText().equals("")) {
                    labelka.setText("");
                    textNote.setText(dostepne);
                } else {
                    labelka.setText(nameNote.getText());
                    String nazwa = nameNote.getText();
                    File wczytany = new File(folder.getAbsolutePath() + "\\" + nazwa + ".txt");
                    Scanner sc = new Scanner(wczytany);
                    String wczytana = "";
                    while (sc.hasNext()) {
                        wczytana = wczytana + sc.nextLine() + "\n";
                    }
                    textNote.setText(wczytana);
                    sc.close();
                    nameNote.setText("");
                }
            } catch (FileNotFoundException a) {
                labelka.setText("Failed to load missing note.");
            }
    }

    public void deleteNote(){
            String nazwa = nameNote.getText();
            File folder = new File("..\\Notatki");
            File kasowany = new File(folder.getAbsolutePath() + "\\" + nazwa + ".txt");
            if(kasowany.delete()) {
                labelka.setText("Note succesfully deleted.");
                nameNote.setText("");
            } else {
                labelka.setText("Note doesn't exist.");
            }
        String[] pliki = folder.list();
        String dostepne = "Your notes: \n";

        for (String plik : pliki) {
            plik = plik.substring(0, plik.length() - 4);
            dostepne = dostepne + plik + "\n";
        }
        textNote.setText(dostepne);
    }


    public void wipeNote(){
        nameNote.setText("");
        labelka.setText("");
        textNote.setText("");
    }


    public void encryptNote(){
        String enkrypcja = textNote.getText();
        String[] linijka = enkrypcja.split("\n");
        String poenkrypcji = "";
        for(String pasek: linijka) {
            char[] litera = pasek.toCharArray();
            for (int i = 0; i < pasek.length(); i++) {
                char pepe = (char) (litera[i] + 3);
                poenkrypcji = poenkrypcji + pepe;
            }
            poenkrypcji = poenkrypcji + "\n";
        }
        textNote.setText(poenkrypcji);
    }

    public void decryptNote(){
        String dekrypcja = textNote.getText();
        String[] linijka = dekrypcja.split("\n");
        String podekrypcji = "";
        for(String pasek: linijka) {
            char[] litera = pasek.toCharArray();
            for (int i = 0; i < pasek.length(); i++) {
                char pepe = (char) (litera[i] - 3);
                podekrypcji = podekrypcji + pepe;
            }
            podekrypcji = podekrypcji + "\n";
        }
        textNote.setText(podekrypcji);
    }
}
