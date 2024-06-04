import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class QuizSolvingPage extends JPanel {
    private MainApp mainApp;
    private List<QuizContent> quizContents;

    public QuizSolvingPage(MainApp mainApp) {
        this.mainApp = mainApp;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());

        // 퀴즈 콘텐츠 목록을 생성
        quizContents = new ArrayList<>();
        quizContents.add(new QuizContent("DAY1. 카메라 이용하기", "스마트폰 카메라를 사용하는 방법에 대한 퀴즈."));
        quizContents.add(new QuizContent("DAY2. 문자 보내기", "문자 메시지를 보내는 방법에 대한 퀴즈."));
        quizContents.add(new QuizContent("DAY3. 앱 설치하기", "스마트폰에서 앱을 설치하는 방법에 대한 퀴즈."));
        quizContents.add(new QuizContent("DAY4. 글자 크기 키우기", "스마트폰의 글자를 키우는 방법에 대한 퀴즈."));
        quizContents.add(new QuizContent("DAY5. 비상 연락망 저장하기", "스마트폰에서 비상 연락망을 저장하는 방법에 대한 퀴즈."));

        JPanel contentPanel = new JPanel(new GridLayout(quizContents.size(), 1, 10, 10));
        for (QuizContent content : quizContents) {
            JButton contentButton = new JButton(content.getTitle());
            contentButton.setToolTipText(content.getDescription());
            contentButton.addActionListener(e -> {
                switch (content.getTitle()) {
                    case "DAY1. 카메라 이용하기":
                        mainApp.showCameraQuizPage();
                        break;
                    case "DAY2. 문자 보내기":
                        mainApp.showMessageQuizPage();
                        break;
                    case "DAY3. 앱 설치하기":
                        mainApp.showAppInstallQuizPage();
                        break;
                    case "DAY4. 글자 크기 키우기":
                        mainApp.showFontSizeQuizPage();
                        break;
                    case "DAY5. 비상 연락망 저장하기":
                        mainApp.showEmergeTelQuizPage();
                        break;
                }
            });
            contentPanel.add(contentButton);
        }

        JButton backButton = new JButton("메인 화면으로 돌아가기");
        backButton.addActionListener(e -> mainApp.showMainPage());

        add(new JLabel("오늘의 퀴즈", JLabel.CENTER), BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
    }
}
