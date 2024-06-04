import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class QuizPage extends JPanel {
    private MainApp mainApp;
    private List<QuizQuestion> questions;
    private int currentQuestionIndex = 0;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup group;

    public QuizPage(MainApp mainApp) {
        this.mainApp = mainApp;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());

        // 퀴즈 질문 생성
        questions = new ArrayList<>();
        questions.add(new QuizQuestion("CameraQuiz1.png", new String[]{"하단의 큰 하얀색 동그라미를 눌러요.", "우측 하단의 쌍방향 버튼을 눌러요.", "인식된 큐알코드를 눌러요.", "카메라 화면에 뜬 영어가 적힌 링크를 눌러요."}, 3, "큐알코드는 코드 인식 시 특정 페이지로 이동하는 기능을 가지고 있어요. \n이 큐알코드에 카메라를 가져다 대면 해당 페이지로 이동하는 링크가 떠요. 이 링크를 누르면 이동할 수 있답니다."));
        questions.add(new QuizQuestion("CameraQuiz2.png", new String[]{"하단의 큰 하얀색 동그라미를 눌러요.", "우측 하단의 쌍방향 버튼을 눌러요.", "좌측 하단의 동그라미를 눌러요.", "동영상 버튼을 눌러요."}, 0, "하단 중앙의 큰 하얀색 동그라미 버튼이 사진을 찍는 버튼이에요.\n우측 하단의 쌍방향 버튼을 누르면 셀카( 내 얼굴)를 찍는 기능으로 바뀌어요. \n좌측 하단의 동그라미를 누르면 이전에 찍은 사진을 볼 수 있어요."));
        questions.add(new QuizQuestion("CameraQuiz3.png", new String[]{"하단의 큰 하얀색 동그라미를 눌러요.", "우측 하단의 쌍방향 버튼을 눌러요.", "하단의 사진 옆 ‘동영상’ 버튼을 눌러요..", "좌측 하단의 이전 사진 동그라미를 눌러요."}, 2, "하단의 사진 옆 동영상 버튼을 클릭하면 동영상 기능으로 바뀌어요.  \n그 뒤 사진 기능과 동일하게 중앙 하단의 동그라미 버튼을 누르면 녹화가 진행된답니다."));
        
        // 질문 라벨 설정
        questionLabel = new JLabel();
        questionLabel.setHorizontalAlignment(JLabel.CENTER);

        // 옵션 패널 설정
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        group = new ButtonGroup();
        optionButtons = new JRadioButton[4];
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i] = new JRadioButton();
            group.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
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