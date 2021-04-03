package com.aldincimpo;

import java.util.ArrayList;
import java.util.Collections;

public class Question {

    //Eigenschaften
    public String txt;
    public ArrayList<Choice> choices;

    //Konstruktor
    public Question(String txt ) {
        this.txt = txt;

        //Neue ArrayList wo die Antworten rein kommen
        this.choices = new ArrayList<Choice>();
    }

    public void addWrongChoice(String txt){
        //Korrekte Antwort hinzufügen Methode
        choices.add(new Choice(txt, false));
    }
    //Korrekte Antwort hinzufügen Methode
    public void addCorrectChoice(String txt){
        //Neues Choice Objekt der Liste hinzufügen
        choices.add(new Choice(txt, true));
    }


    public void validate(){
        //for each LESEND über die Antwortmöglichkeiten
        for (Choice choice : choices) {
            //Wenn weniger als 2 Antwortmöglichkeiten, dann Gegenreaktion!
            if(choices.size() < 2){
                throw new IllegalArgumentException("Es müssen mind. 2 Antworten da sein.");
            }
        }

    }


    public void show(){
        //Shufflen der Antwortenmöglichkeiten!
        for (int i = 0; i < 30; i++) {
            Collections.shuffle(choices);
        }



        System.out.println(this.txt);
        System.out.println();

        //for each LESEND über die Antwortmöglichkeiten
        for (Choice choice :
                choices) {
            System.out.println("(" + (choices.indexOf(choice) + 1) + ") " + choice.txt);

        }
    }
    public boolean analyzeAnswer(String answer){
        //nötigen Variablen anlegen
        String digit = "123456789";
        boolean answerHasDigits = false;
        int correctAnswers = 0;


        //Schauen ob überhaupt eine Ziffer in der Antwort enthalten ist
        for (int i = 0; i < answer.length(); i++) {
            if(digit.contains(answer.charAt(i) + "")){
                answerHasDigits = true;
                break;
            }
        }

        //Wenn nicht, dann Gegenreaktion
        if(!answerHasDigits){
            throw new IllegalArgumentException("Nur Zahlen dürfen enthalten sein!");
        }

        //Schleife über Antwort Zeichen
        for (int i = 0; i < answer.length(); i++) {

            //Wenn Ziffer enthalten ist
            if(digit.contains(answer.charAt(i) + "")){

                //und die Ziffer im gültigen Antwortbereich liegt
                if(Integer.parseInt(answer.charAt(i)+"")-1 <= choices.size()-1){

                    // und die Antwort falsch ist wird der return auch insgesamt falsch
                    if(!choices.get(Integer.parseInt(answer.charAt(i) + "") -1).ok){
                        correctAnswers++;
                    }

                }else{
                    // Zahl größer als möglichen Antworten
                    return false;
                    // Sonst: mit Try and Catch Block möglich, noch nicht gelernt.
                    // throw new IllegalArgumentException("Antwort nicht möglich, eingegebene Zahl ist zu hoch");
                }
            }
        }

        //Prüfen, wieviele Antworten richtig sein müssen, damit die Frage insgesamt richtig ist.

        int needsToBeCorrect = 0;
        //for each LESEND über die Antwortmöglichkeiten
        for (Choice choice :
                choices) {
            //Wenn die Antwort ok ist, dann wird needsToBeCorrect um 1 erhöht
            if(choice.ok == true){
                needsToBeCorrect++;
            }
        }

        //Nun wird auch geschaut, ob alle Antworten auch richtig angegeben wurden
        if(needsToBeCorrect == correctAnswers){
            return true;
        }else{
            return false;
        }

    }


    public void showCorrectAnswer(){

        //Standard Ausgabe
        System.out.println("Richtige Antwort(en): ");


        //for each schleife LESEND über choice List
        for (Choice choice :
                choices) {
            //Ausgeben der richtigen Antworten
            if(choice.ok == true) {
                System.out.println("(" + (choices.indexOf(choice) + 1) + ") " + choice.txt);
            }
        }

    }

}
