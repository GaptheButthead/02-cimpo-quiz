package com.aldincimpo;

import java.util.*;

public class Main {

    public static void main(String[] args) {
// TODO: AUFGABE WURDE MIT MARTIN BRANDL GEMEINSAM GELÖST!


        //Question Pool ArrayList erzeugen
        ArrayList<Question> QuestionPool = setupQuestionPool();
        //BeispielText einfügen aus Angabe
        System.out.println("Quiz mit 3 Fragen");
        System.out.println("Geben  Sie  die  zutreffende  Antwort  als  Zahlen." + "\n" +
                " Wenn  mehrere  Antworten  zutreffen , geben Sie" + "\n" +
                "  bitte  alle  Antworten  in  einer  Zeile  an und" + "\n" +
                "  trennen  diese  durch  Leerzeichen ,z.B.'2 4'.");
        System.out.println();


        //neue Random Variable plus scanner deklarieren und init.
        var r = new Random();
        var scanner = new Scanner(System.in);
        int question;
        String answer;
        int correctAnswerCounter =0;

        //Solange i<=3 Fragen, soll folgendes ausgeführt werden:
        int i = 1;
        while(i<=3){

            //Fragen werden zufällig durchgemischt
            question = r.nextInt(QuestionPool.size());

            //Frage mit Antwortmöglichkeiten
            QuestionPool.get(question).show();
            System.out.print("Ihre Antwort: " );;
            //Antwort wird gescannt und in einer Variable gespeichert
            answer = scanner.nextLine();

            //Wenn Korrekte Antwort, dann erhöhe correct Answer Counter um 1
            if(QuestionPool.get(question).analyzeAnswer(answer)){
                System.out.println("Korrekte Antwort, sehr gut!");
                correctAnswerCounter++;
            }else{
                System.out.println("Falsch!");

                //Zeigen der korrekten Antwort
                QuestionPool.get(question).showCorrectAnswer();
            }
            //i++ nach jedem Durchgang und das Entfernen der Frage, damit sie nicht
            //zwei mal im selben Durchgang vorkommt!
            i++;
            QuestionPool.remove(question);
        }
        //Finale Ausgabe mit Ergebnis
        System.out.println("Sie haben " + correctAnswerCounter + " von 3 Fragen richtig beantwortet");

    }


    //Beispiel Text einfügen aus Angabe
    public static ArrayList<Question> setupQuestionPool() {
        var pool = new ArrayList<Question>();
        Collections.shuffle(pool);
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
        q.addWrongChoice("Oreo");
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

        //Neue Fragen
        q = new Question("Was versteht man unter Cloud Computing?");
        q.addCorrectChoice("Bereitstellung von IT-Leistungen über das Internet");
        q.addWrongChoice("Computer die auf einer Wolke schweben");
        q.addWrongChoice("Lokale Rechner, die gemeinsam für ein Problem arbeiten");
        q.addWrongChoice("Keiner dieser Antworten");
        pool.add(q);


        q = new Question ("Bitcoin's all time high Wert betrug wie viel?");
        q.addWrongChoice("20.000");
        q.addWrongChoice("40.000");
        q.addWrongChoice("50.000");
        q.addCorrectChoice("60.000");
        pool.add(q);


        q = new Question("Welcher Streaming Service hat zum ersten Mal 100 Millionen abgesch. Abonements dieses Jahr erreicht?");
        q.addCorrectChoice("Disney+");
        q.addWrongChoice("Netflix");
        q.addWrongChoice("Amazon Prime Video");
        q.addWrongChoice("Nickelodeon");
        pool.add(q);


        // sicherstellen, dass jede Frage mindestens 2 Antworten
        // und mindestens eine richtige Antwort hat
        for(var qst: pool)
            qst.validate();

        return pool;
    }

}
