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
        choices.add(new Choice(txt, false));
    }

    public void addCorrectChoice(String txt){
        choices.add(new Choice(txt, true));
    }


    public void validate(){
        for (Choice choice : choices) {
            if(choices.size() < 2){
                throw new IllegalArgumentException("Es müssen mind. 2 Antworten da sein.");
            }
        }

    }


    public void show(){
        //Shufflen der Antworten!
        Collections.shuffle(choices);


        System.out.println(this.txt);
        System.out.println();


        for (Choice choice :
                choices) {
            System.out.println("(" + (choices.indexOf(choice) + 1) + ") " + choice.txt);

        }
    }
    public boolean analyzeAnswer(String answer){
        //Zahlen Pool anlegen
        String digit = "123456789";
        boolean correctAnswer = true;
        boolean answerHasDigits = false;


        for (int i = 0; i < answer.length(); i++) {
            if(digit.contains(answer.charAt(i) + "")){
                answerHasDigits = true;
                break;
            }
        }

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
                        correctAnswer = false;
                    }

                }else{
                    // Zahl größer als möglichen Antworten
                    correctAnswer = false;
                    // alternativ
                    // throw new IllegalArgumentException("Antwort nicht möglich, eingegebene Zahl ist zu hoch");
                }
            }
        }
        //Zurückbringen
        return correctAnswer;
    }


    public void showCorrectAnswer(){
        System.out.println("Richtige Antwort: ");

        for (Choice choice :
                choices) {
            if(choice.ok == true) {
                System.out.println("(" + (choices.indexOf(choice) + 1) + ") " + choice.txt);
            }
        }

    }

}
