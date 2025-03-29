import java.util.Scanner;

public class Quiz {
    public static void main(String[] args) {
        QuizModel model = new QuizModel();
        QuizView view = new QuizView();
        QuizController controller = new QuizController(model, view);
        controller.startQuiz();
    }

    public static class QuizModel {
        private String[] questions;
        private String[][] options;
        private String[] correctAnswers;

        public QuizModel() {
            questions = new String[] {
                    "What is the capital of France?",
                    "Which programming language is used for Android development?",
                    "Who developed Java?",
                    "Which planet is the largest in our solar system?",
            };

            options = new String[][] {
                    { "Paris", "London", "Berlin", "Rome" },
                    { "Python", "Java", "C++", "Swift" },
                    { "James Gosling", "Dennis Ritchie", "Bjarne Stroustrup", "Guido Van Rossum" },
                    { "Earth", "Saturn", "Mars", "Jupiter" },
            };

            correctAnswers = new String[] { "Paris", "C++", "James Gosling", "Jupiter" };
        }

        public String getQuestion(int index) {
            return questions[index];
        }

        public String[] getOptions(int index) {
            return options[index];
        }

        public String getCorrectAnswer(int index) {
            return correctAnswers[index];
        }

        public int getTotalQuestions() {
            return questions.length;
        }
    }

    public static class QuizView {
        private Scanner scanner;

        public QuizView() {
            this.scanner = new Scanner(System.in);
        }

        public void displayQuestion(String question, String[] options) {
            System.out.println(question);
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
        }

        public int getUserAnswer() {
            System.out.print("Enter your answer (1-4): ");
            int answer = scanner.nextInt();
            while (answer < 1 || answer > 4) {
                System.out.print("Invalid answer. Please enter a number between 1 and 4: ");
                answer = scanner.nextInt();
            }
            return answer;
        }

        public void displayScore(int score, int totalQuestions) {
            System.out.println("Your final score is: " + score + "/" + totalQuestions);
        }

        public void closeScanner() {
            scanner.close();
        }
    }

    public static class QuizController {
        private QuizModel model;
        private QuizView view;

        public QuizController(QuizModel model, QuizView view) {
            this.model = model;
            this.view = view;
        }

        public void startQuiz() {
            int score = 0;
            for (int i = 0; i < model.getTotalQuestions(); i++) {
                String question = model.getQuestion(i);
                String[] options = model.getOptions(i);
                String correctAnswer = model.getCorrectAnswer(i);
                view.displayQuestion(question, options);
                int userAnswerIndex = view.getUserAnswer();
                String userAnswer = options[userAnswerIndex - 1];
                if (userAnswer.equalsIgnoreCase(correctAnswer)) {
                    score++;
                }
            }
            view.displayScore(score, model.getTotalQuestions());
            view.closeScanner();
        }
    }
}
