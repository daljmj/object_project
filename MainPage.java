import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MainPage extends JPanel {
    private MainApp mainApp;

    public MainPage(MainApp mainApp) {
        this.mainApp = mainApp;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());

        // 이미지 파일 경로 설정
        String imagePath = "title.png";

        // 이미지를 표시할 JLabel 생성
        JLabel titleLabel = new JLabel();
        try {
            // 이미지 파일을 읽어옴
            Image image = ImageIO.read(new File(imagePath));

            // 이미지 크기 조정
            int width = 1750; // 원하는 너비
            int height = 350; // 원하는 높이
            Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            // 조정된 이미지로 ImageIcon 생성하여 JLabel에 설정
            ImageIcon icon = new ImageIcon(resizedImage);
            titleLabel.setIcon(icon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // JLabel을 BorderLayout의 NORTH에 추가하여 제목 부분에 이미지가 표시되도록 설정
        add(titleLabel, BorderLayout.NORTH);

        // 버튼을 담을 패널 생성
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JButton learningMaterialsButton = new JButton("<html><center><bold>학습자료집</bold><br><br><font size='10'>학습자료집은 뚝딱폰의 전체 자료를 제공합니다.</font></center></html>");
        learningMaterialsButton.setPreferredSize(new Dimension(200, 50)); // 너비 200픽셀, 높이 50픽셀로 설정
        learningMaterialsButton.setPreferredSize(new Dimension(400, 400)); // 너비 200픽셀, 높이 50픽셀로 설정
        learningMaterialsButton.setBackground(Color.decode("#CBDBFF")); // 배경색을 파란색으로 설정
        learningMaterialsButton.setFont(new Font("함초롬돋움", Font.PLAIN, 80)); // 글꼴은 '맑은 고딕', 크기는 20픽셀로 설정

        JButton todayQuizButton = new JButton("<html><center>오늘의 퀴즈<br><br><font size='10'>랜덤으로 제공되는 퀴즈를 풀 수 있습니다.</font></center></html>");
        todayQuizButton.setPreferredSize(new Dimension(400, 400)); // 너비 200픽셀, 높이 50픽셀로 설정
        todayQuizButton.setBackground(Color.decode("#FFDBEA"));
        todayQuizButton.setFont(new Font("함초롬돋움", Font.PLAIN, 80)); // 글꼴은 '맑은 고딕', 크기는 20픽셀로 설정

        JButton progressButton = new JButton("<html><center>전체 진도 현황<br><br><font size='10'>나의 전체 진도 현황을 확인할 수 있습니다.</font></center></html>");
        progressButton.setPreferredSize(new Dimension(400, 400)); // 너비 200픽셀, 높이 50픽셀로 설정
        progressButton.setBackground(Color.decode("#FFF6BA")); // 배경색을 파란색으로 설정
        progressButton.setFont(new Font("함초롬돋움", Font.PLAIN, 80)); // 글꼴은 '맑은 고딕', 크기는 20픽셀로 설정


        learningMaterialsButton.addActionListener(e -> mainApp.showLearningMaterialsPage());
        todayQuizButton.addActionListener(e -> mainApp.showQuizSolvingPage());
        progressButton.addActionListener(e -> mainApp.showProgressPage());

        // 버튼을 패널에 추가
        buttonPanel.add(learningMaterialsButton);
        buttonPanel.add(todayQuizButton);
        buttonPanel.add(progressButton);

        // 패널을 MainPage에 추가
        add(buttonPanel, BorderLayout.CENTER);
    }
}
