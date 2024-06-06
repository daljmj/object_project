import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class AppInstallQuizPage extends JPanel {
    private MainApp mainApp;
    private List<QuizQuestion> questions;
    private int currentQuestionIndex = 0;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup group;

    public AppInstallQuizPage(MainApp mainApp) {
        this.mainApp = mainApp;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());

        // 상단 패널 설정
        JPanel topPanel = new JPanel(new BorderLayout());
        
        // 위에 큰 제목
        JLabel titleLabel = new JLabel("앱 설치 방법 퀴즈를 풀어보세요!", JLabel.CENTER);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 60)); // 폰트 크기 50으로 설정
        titleLabel.setBackground(Color.decode("#CBDBFF")); // 배경색 설정
        titleLabel.setOpaque(true); // 배경이 투명하지 않게 설정

        // 홈 화면 돌아가기 버튼
        Font btnFont = new Font("함초롬돋움", Font.BOLD, 28); // 버튼 폰트 크기 설정
        Color btnColor = new Color(70, 130, 180); // 버튼 배경색 설정

        JButton backButton = new JButton("메인 화면으로 돌아가기");
        backButton.setForeground(Color.BLACK); // 글자 색상 설정
        backButton.setFont(btnFont);
        backButton.setBackground(Color.decode("#FFDBEA")); // 버튼 배경색 설정
        backButton.addActionListener(e -> {
            resetQuiz();
            mainApp.showMainPage();
        });

        // 상단 패널에 제목과 홈 버튼 추가
        topPanel.add(titleLabel, BorderLayout.CENTER);
        topPanel.add(backButton, BorderLayout.EAST);

        // 퀴즈 질문 생성
        questions = new ArrayList<>();
        questions.add(new QuizQuestion("AppInstallQuiz1.png", new String[]{"1번 아이콘", "2번 아이콘", "3번 아이콘", "4번 아이콘"}, 3, "1번은 카메라, 2번은 설정, 3번은 인터넷, 4번은 앱을 설치할 수 있는 앱스토어입니다!"));
        questions.add(new QuizQuestion("AppInstallQuiz2.png", new String[]{"좌측 상단의 화살표를 눌러요.", "시계 모양을 눌러요.", "설치 버튼을 눌러요.", "좌측 하단의 도구 버튼을 눌러요."}, 2, "파란색 설치 버튼을 눌러야 시계 앱이 설치됩니다!"));
        questions.add(new QuizQuestion("AppInstallQuiz3.png", new String[]{"제거 버튼을 눌러요.", "시계 버튼을 눌러요.", "열기 버튼을 눌러요.", "상단의 돋보기 버튼을 눌러요."}, 2, "열기 버튼을 누르면 설치된 앱에 접속할 수 있어요!"));
        
        // 질문 라벨 설정
        questionLabel = new JLabel();
        questionLabel.setHorizontalAlignment(JLabel.CENTER);

        JScrollPane questionScrollPane = new JScrollPane(questionLabel);
        questionScrollPane.setBorder(null);
        questionScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        questionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // 옵션 패널 설정
        JPanel optionsPanel = new JPanel(new GridBagLayout());
        group = new ButtonGroup();
        optionButtons = new JRadioButton[4];
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 0, 20, 0); // 위 아래로 20픽셀의 여백을 추가
        Font buttonFont = new Font("함초롬돋움", Font.PLAIN, 30); // 폰트 크기 키우기
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setHorizontalAlignment(JRadioButton.CENTER);
            optionButtons[i].setFont(buttonFont); // 폰트 적용
            group.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i], gbc);
        }

        JPanel buttonPanel = new JPanel(new GridLayout(1,3,0,0));

        // 버튼 설정
        JButton nextButton = new JButton("다음 문제로 이동 ▶");
        nextButton.setForeground(Color.BLACK); // 글자 색상 설정
        nextButton.setFont(btnFont);
        nextButton.setBackground(Color.decode("#CBDBFF")); // 버튼 배경색 설정
        nextButton.addActionListener(e -> showNextQuestion());

        JButton previousButton = new JButton("◀ 이전 문제로 이동");
        previousButton.setForeground(Color.BLACK); // 글자 색상 설정
        previousButton.setFont(btnFont);
        previousButton.setBackground(Color.decode("#CBDBFF")); // 버튼 배경색 설정
        previousButton.addActionListener(e -> showPreviousQuestion());

        JButton submitButton = new JButton("정답을 확인해보세요.");
        submitButton.setForeground(Color.BLACK); // 글자 색상 설정
        submitButton.setFont(btnFont);
        submitButton.setBackground(Color.decode("#FFF6BA")); // 버튼 배경색 설정
        submitButton.addActionListener(e -> checkAnswer());


        // 버튼 패널에 버튼 추가
        buttonPanel.add(previousButton);
        buttonPanel.add(submitButton);
        buttonPanel.add(nextButton);

        // 네비게이션 패널 설정
        JPanel navigationPanel = new JPanel(new GridLayout(1, 3, 10, 0)); // 1행 3열로 설정, 버튼 간 10픽셀 간격
        navigationPanel.add(previousButton);
        navigationPanel.add(submitButton);
        navigationPanel.add(nextButton);

        // 중앙 패널 설정
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(questionScrollPane, BorderLayout.NORTH);
        centerPanel.add(optionsPanel, BorderLayout.CENTER);

        // 패널 추가
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
            group.clearSelection(); // 라디오 버튼 선택 해제
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
        for (JRadioButton button : optionButtons) {
            button.setSelected(false); // 모든 라디오 버튼의 선택 해제
        }
        showNextQuestion();
    }
}

