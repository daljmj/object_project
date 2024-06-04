import java.awt.*;
import javax.swing.*;

public class QuizQuestion {
    private ImageIcon question;
    private String[] options;
    private int correctIndex;
    private String explanation;

    public QuizQuestion(String imagePath, String[] options, int correctIndex, String explanation) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        this.question = new ImageIcon(scaledImage);
        this.options = options;
        this.correctIndex = correctIndex;
        this.explanation = explanation;
    }

    public ImageIcon getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    public String getExplanation() {
        return explanation;
    }
}
