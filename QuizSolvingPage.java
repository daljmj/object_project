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
        quizContents.add(new QuizContent("첫번째 시험지", "첫번째 시험지를 풀어보세요."));
        quizContents.add(new QuizContent("두번째 시험지", "두번째 시험지를 풀어보세요."));
        quizContents.add(new QuizContent("세번째 시험지", "세번째 시험지를 풀어보세요."));
        quizContents.add(new QuizContent("네번째 시험지", "네번째 시험지를 풀어보세요."));
        quizContents.add(new QuizContent("다섯번째 시험지", "다섯번째 시험지를 풀어보세요."));

        JPanel contentPanel = new JPanel(new GridLayout(quizContents.size(), 1, 10, 10));
        contentPanel.setBackground(Color.WHITE); // Set background color
        setBackground(Color.decode("#CBDBFF"));

        for (QuizContent content : quizContents) {
            JButton contentButton = new JButton(content.getTitle());
            contentButton.setToolTipText(content.getDescription());
            contentButton.setFont(new Font("맑은 고딕", Font.BOLD, 40)); // Set font
            contentButton.setBackground(Color.WHITE); // Set button color to white
            contentButton.setForeground(Color.black); // Set text color
            contentButton.setFocusPainted(false); // Remove focus border
            contentButton.setBorder(BorderFactory.createLineBorder(Color.decode("#2196F3"), 2)); // Add border
            contentButton.addActionListener(e -> {
                switch (content.getTitle()) {
                    case "첫번째 시험지":
                        mainApp.showCameraQuizPage();
                        break;
                    case "두번째 시험지":
                        mainApp.showMessageQuizPage();
                        break;
                    case "세번째 시험지":
                        mainApp.showAppInstallQuizPage();
                        break;
                    case "네번째 시험지":
                        mainApp.showFontSizeQuizPage();
                        break;
                    case "다섯번째 시험지":
                        mainApp.showEmergeTelQuizPage();
                        break;
                }
            });
            contentPanel.add(contentButton);
        }

        JButton backButton = new JButton("메인 화면으로 돌아가기");
        backButton.addActionListener(e -> mainApp.showMainPage());
        backButton.setFont(new Font("맑은 고딕", Font.PLAIN, 30)); // 텍스트 크기 설정
        backButton.setBackground(Color.decode("#CBDBFF")); // Set button color
        backButton.setForeground(Color.black); // Set text color
        backButton.setFocusPainted(false); // Remove focus border
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        JLabel titleLabel = new JLabel("오늘의 퀴즈", JLabel.CENTER);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 100));
        titleLabel.setBackground(Color.decode("#CBDBFF")); // Set background color
        titleLabel.setForeground(Color.black); // Set text color
        titleLabel.setOpaque(true); // Make background color visible

        // Add components to the panel with BorderLayout
        add(titleLabel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);

        setBackground(Color.WHITE); // Set background color for the panel
    }
}
