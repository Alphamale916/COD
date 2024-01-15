import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
    private String question;
    private String[] options;
    private int correctOption;

    public QuizQuestion(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

class Quiz {
    private QuizQuestion[] questions;
    private int currentQuestionIndex;
    private int score;
    private Timer timer;

    public Quiz(QuizQuestion[] questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
    }

    public void startQuiz() {
        System.out.println("Welcome to the Quiz!");

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up! Moving to the next question.");
                displayNextQuestion();
            }
        }, 20000); // 20 seconds for each question

        displayNextQuestion();
    }

    private void displayNextQuestion() {
        if (currentQuestionIndex < questions.length) {
            QuizQuestion currentQuestion = questions[currentQuestionIndex];

            System.out.println("\nQuestion: " + currentQuestion.getQuestion());

            String[] options = currentQuestion.getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }

            System.out.print("Enter your choice (1-" + options.length + "): ");
            Scanner scanner = new Scanner(System.in);
            int userChoice = scanner.nextInt();

            // Check if the user's choice is correct
            if (userChoice == currentQuestion.getCorrectOption()) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect! The correct answer was: " + currentQuestion.getCorrectOption());
            }

            currentQuestionIndex++;

            // Display the result or move to the next question
            if (currentQuestionIndex < questions.length) {
                timer.cancel(); // cancel the current timer
                startQuiz(); // move to the next question
            } else {
                endQuiz();
            }
        }
    }

    private void endQuiz() {
        System.out.println("\nQuiz completed!");
        System.out.println("Your final score is: " + score + " out of " + questions.length);
        timer.cancel(); // cancel the timer

        // Display correct/incorrect answers
        for (int i = 0; i < questions.length; i++) {
            QuizQuestion question = questions[i];
            System.out.println("Q" + (i + 1) + ": " + question.getQuestion());
            System.out.println("Your Answer: " + question.getOptions()[question.getCorrectOption() - 1]);
        }
    }
}

public class QuizApplication {
    public static void main(String[] args) {
        QuizQuestion[] questions = {
                new QuizQuestion("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 3),
                new QuizQuestion("Which planet is known as the Red Planet?", new String[]{"Earth", "Mars", "Jupiter", "Venus"}, 2),
                new QuizQuestion("What is the largest mammal on Earth?", new String[]{"Elephant", "Whale", "Giraffe", "Hippopotamus"}, 2)
        };

        Quiz quiz = new Quiz(questions);
        quiz.startQuiz();
    }
}
