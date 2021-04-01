package com.aldincimpo;

import java.util.ArrayList;

public class Question {

//Eigenschaften
    public String txt;
    public ArrayList<Choice> choices;

//Konstruktor
    public Question(String txt, ArrayList<Choice> choices) {
        this.txt = txt;
        this.choices = choices;
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
        System.out.println("Quiz mit 3 Fragen");
        System.out.println("Geben  Sie  die  zutreffende  Antwort  als  Zahlan. Wenn  mehrere  Antworten  zutreffen , gebenSie  bitte  alle  Antworten  in  einer  Zeile  anund  trennen  diese  durch  Leerzeichen ,z.B.'2 4'.");
        System.out.println();

        System.out.println(this.txt);
        System.out.println();

        int i = 1;
        for (Choice choice :
                choices) {
            System.out.println("(" + i + ")" + this.choices);
            i++;
        }
    }
    public boolean analyzeAnswer(String answer){
        String digit = "123456789";
        boolean correctAnswer = true;

        for (int i = 0; i < answer.length(); i++) {
            if(digit.contains(answer.charAt(i) + "")){
              if(choices.get((int)answer.charAt(i)).ok == false){
                    correctAnswer = false;
              }
            }
        }
        return correctAnswer;
    }




}
