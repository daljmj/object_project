import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class MessageQuizPage extends JPanel {
    private MainApp mainApp;
    private List<QuizQuestion> questions;
    private int currentQuestionIndex = 0;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup group;

    public MessageQuizPage(MainApp mainApp) {
        this.mainApp = mainApp;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());

        // 퀴즈 질문 생성
        questions = new ArrayList<>();
        questions.add(new QuizQuestion("MessageQuiz1.png", new String[]{"우측 상단의 점 세 개를 눌러요.", "> 모양을 눌러요.", "비행기 모양을 눌러요.", "우측 하단의 엔터키(화살표 모양)을 눌러요."}, 2, "비행기 모양을 클릭하면 문자를 보낼 수 있습니다! \n화살표 모양을 누르면 다음 줄로 바뀌어서 문자를 작성할 수 있어요."));
        questions.add(new QuizQuestion("MessageQuiz2.png", new String[]{"우측 상단의 점 세 개를 눌러요.", "문자를 길게 눌러요.", "비행기 모양 버튼을 눌러요.", "||| 모양을 눌러요."}, 1, "보낸 문자 메시지를 삭제하고 싶다면 해당 문자를 길게 꾹 누른 뒤 삭제 버튼을 누르면 됩니다!"));
        
        // 질문 라벨 설정
        questionLabel = new JLabel();
        questionLabel.setHorizontalAlignment(JLabel.CENTER);

        // 옵션 패널 설정
        JPanel optionsPanel = new JPanel(new GridBagLayout());
        group = new ButtonGroup();
        optionButtons = new JRadioButton[4];
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 0, 20, 0); // 위 아래로 20픽셀의 여백을 추가
        Font buttonFont = new Font("Arial", Font.PLAIN, 18); // 폰트 설정
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setHorizontalAlignment(JRadioButton.CENTER);
            optionButtons[i].setFont(buttonFont); // 폰트 적용
            group.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i], gbc);
        }

        // 버튼 설정
        JButton nextButton = new JButton("다음 문제");
        nextButton.addActionListener(e -> showNextQuestion());

        JButton previousButton = new JButton("이전 문제");
        previousButton.addActionListener(e -> showPreviousQuestion());

        JButton submitButton = new JButton("정답 확인");
        submitButton.addActionListener(e -> checkAnswer());

        JButton backButton = new JButton("메인 화면으로 돌아가기");
        backButton.addActionListener(e -> {
            resetQuiz();
            mainApp.showMainPage();
        });

        // 네비게이션 패널 설정
        JPanel navigationPanel = new JPanel(new FlowLayout());
        navigationPanel.add(previousButton);
        navigationPanel.add(submitButton);
        navigationPanel.add(nextButton);

        // 상단 패널 설정
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(backButton);

        // 중앙 패널 설정
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(questionLabel, BorderLayout.NORTH);
        centerPanel.add(optionsPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(navigationPanel, BorderLayout.SOUTH);

        showNextQuestion();
    }

    // 다음 질문을 표시하는 메서드
    private void showNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            QuizQuestion question = questions.get(currentQuestionIndex);
            questionLabel.setIcon(question.getQuestion());
            String[] options = question.getOptions();
            for (int i = 0; i < optionButtons.length; i++) {
                optionButtons[i].setText(options[i]);
                optionButtons[i].setSelected(false);
            }
            currentQuestionIndex++;
        } else {
            JOptionPane.showMessageDialog(this, "퀴즈를 완료했습니다!", "완료", JOptionPane.INFORMATION_MESSAGE);
            resetQuiz();
            mainApp.showMainPage();
        }
    }

    // 이전 질문을 표시하는 메서드
    private void showPreviousQuestion() {
        if (currentQuestionIndex > 1) {
            currentQuestionIndex -= 2;
            showNextQuestion();
        } else if (currentQuestionIndex == 1) {
            currentQuestionIndex--;
            showNextQuestion();
        }
    }

     // 정답을 확인하는 메서드
    private void checkAnswer() {
        if (currentQuestionIndex > 0) {
            QuizQuestion question = questions.get(currentQuestionIndex - 1);
            int selectedOption = -1;
            for (int i = 0; i < optionButtons.length; i++) {
                if (optionButtons[i].isSelected()) {
                    selectedOption = i;
                    break;
                }
            }
            if (selectedOption == question.getCorrectIndex()) {
                JOptionPane.showMessageDialog(this, "정답입니다! \n해설: " + question.getExplanation(), "정답", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "틀렸습니다. \n해설: " + question.getExplanation(), "오답", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

     // 퀴즈를 초기화하는 메서드
    private void resetQuiz() {
        currentQuestionIndex = 0;
        group.clearSelection();
        showNextQuestion();
    }
}