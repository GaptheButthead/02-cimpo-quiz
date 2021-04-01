package com.aldincimpo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        ArrayList<Question> QuestionPool = setupQuestionPool();

        System.out.println("Quiz mit 3 Fragen");
        System.out.println("Geben  Sie  die  zutreffende  Antwort  als  Zahlen." +
                " Wenn  mehrere  Antworten  zutreffen , geben Sie" +
                "  bitte  alle  Antworten  in  einer  Zeile  an und" +
                "  trennen  diese  durch  Leerzeichen ,z.B.'2 4'.");
        System.out.println();

        var r = new Random();
        var scanner = new Scanner(System.in);
        int question;
        String answer;
        int correctAnswerCounter=0;
        int i = 1;

        while(i<=3){

            question = r.nextInt(QuestionPool.size());

            //Frage mit Antwortmöglichkeiten
            QuestionPool.get(question).show();
            System.out.print("Ihre Antwort: " );;
            answer = scanner.nextLine();
            if(QuestionPool.get(question).analyzeAnswer(answer)){
                System.out.println("Korrekte Antwort, sehr gut!");
                correctAnswerCounter++;
            }else{
                System.out.println("Falsch!");

                QuestionPool.get(question).showCorrectAnswer();
            }
            i++;

        }
    }


    public static ArrayList<Question> setupQuestionPool() {
        var pool = new ArrayList<Question>();

        var q = new Question("Welches ist der längste Fluss der Erde?");
        q.addCorrectChoice("Nil");
        q.addWrongChoice("Amazonas");
        q.addWrongChoice("Jangtsekiang");
        q.addWrongChoice("Missisippi");
        pool.add(q);

        q = new Question("Welche Zahlen sind Primzahlen?");
        q.addCorrectChoice("13");
        q.addCorrectChoice("127");
        q.addWrongChoice("755");
        q.addWrongChoice("2001");
        pool.add(q);

        q = new Question("Wie lautet der Codename für Android 9");
        q.addCorrectChoice("Pie");
        q.addWrongChoice("Nougat");
        q.addWrongChoice("KitKat");
        q.addWrongChoice("Orea");
        pool.add(q);

        q = new Question("Welches ist das flächenmäßig größte Land der Erde?");
        q.addCorrectChoice("Russland");
        q.addWrongChoice("Kanada");
        q.addWrongChoice("China");
        q.addWrongChoice("Indien");
        pool.add(q);

        q = new Question("Welche Ausdrücke ergeben 24?");
        q.addCorrectChoice("2 * 3 * 4");
        q.addCorrectChoice("12 + 2^2 * 3");
        q.addCorrectChoice("2^4 + 2^3");
        q.addWrongChoice("100 - 12 * 6 - 3 * 3");
        pool.add(q);

        // sicherstellen, dass jede Frage mindestens 2 Antworten
        // und mindestens eine richtige Antwort hat
        for(var qst: pool)
            qst.validate();

        return pool;
    }

}
