import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProgressPage extends JPanel {
    private MainApp mainApp;
    private List<TodayQuestion> questions;
    private int currentQuestionIndex = 0;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private JLabel imageLabel;  // 이미지 라벨 멤버 변수로 선언

    public ProgressPage(MainApp mainApp) {
        this.mainApp = mainApp;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());

        // 전체 폰트 설정
        Font defaultFont = new Font("함초롬돋움", Font.PLAIN, 20);
        setUIFont(new javax.swing.plaf.FontUIResource(defaultFont));

        // 퀴즈 질문 생성
        questions = new ArrayList<>();
        questions.add(new TodayQuestion("오늘은 6월 12일, 공부 첫날! 공부하셨나요?", new String[]{"네! 퀴즈까지 다 했어요", "아니요, 내일은 할거에요!"}, 0, "June.png"));
        questions.add(new TodayQuestion("오늘은 2일차, 6월 13일! 공부하셨나요?", new String[]{"네, 뿌듯해요^^", "아니요, 또 미뤘어요."}, 2, "Junetwo.png"));

        // 패널 설정
        JPanel centerPanel = new JPanel(new BorderLayout());
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        
        // 테두리 색상과 두께 설정
        Border lineBorder = BorderFactory.createLineBorder(Color.decode("#CBDBFF"), 3);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(lineBorder, "1주차");
        titledBorder.setTitleColor(Color.BLUE); // 타이틀 텍스트 색상 설정
        optionsPanel.setBorder(titledBorder);


        ButtonGroup group = new ButtonGroup();
        optionButtons = new JRadioButton[2];

        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i] = new JRadioButton();
            group.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }

        questionLabel = new JLabel("", JLabel.CENTER);

        // 버튼 생성
        JButton nextButton = new JButton("내일 진도 체크");
        nextButton.addActionListener(e -> showNextQuestion());
        nextButton.setBackground(Color.decode("#FFF6BA"));

        JButton backButton = new JButton("메인 화면으로 돌아가기");
        backButton.addActionListener(e -> mainApp.showMainPage());

        // 하단에 사진을 추가하는 패널 생성
        imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("June.png"));
        Image image = icon.getImage().getScaledInstance(600, 500, Image.SCALE_SMOOTH); // 크기 조정
        ImageIcon scaledIcon = new ImageIcon(image);
        imageLabel.setIcon(scaledIcon);

        centerPanel.add(questionLabel, BorderLayout.NORTH);
        centerPanel.add(optionsPanel, BorderLayout.CENTER);
        centerPanel.add(imageLabel, BorderLayout.SOUTH);

        add(backButton, BorderLayout.WEST);
        add(nextButton, BorderLayout.EAST);
        add(centerPanel, BorderLayout.CENTER);

        showNextQuestion();
    }

    private void showNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            TodayQuestion question = questions.get(currentQuestionIndex);
            questionLabel.setText(question.getQuestion());
            String[] options = question.getOptions();
            for (int i = 0; i < optionButtons.length; i++) {
                optionButtons[i].setText(options[i]);
            }

            // 이미지 업데이트
            ImageIcon icon = new ImageIcon(getClass().getResource(question.getImagePath()));
            Image image = icon.getImage().getScaledInstance(600, 500, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(image);
            imageLabel.setIcon(scaledIcon);

            currentQuestionIndex++;
        } else {
            JOptionPane.showMessageDialog(this, "1주차 공부 체크 완료!", "완료", JOptionPane.INFORMATION_MESSAGE);
            mainApp.showMainPage();
        }
    }

    private void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(key, f);
        }
    }
}

class TodayQuestion {
    private String question;
    private String[] options;
    private int correctIndex;
    private String imagePath;  // 이미지 경로 변수 추가

    public TodayQuestion(String question, String[] options, int correctIndex, String imagePath) {
        this.question = question;
        this.options = options;
        this.correctIndex = correctIndex;
        this.imagePath = imagePath;  // 이미지 경로 초기화
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    public String getImagePath() {
        return imagePath;  // 이미지 경로 반환
    }
}
