import java.awt.*;
import javax.swing.*;

public class EmergeTelPage extends JPanel {
    private MainApp mainApp;
    private ImageIcon[] imageIcons; // 이미지 아이콘 배열
    private int currentIndex = 0; // 현재 보여지는 이미지의 인덱스
    private JLabel imageLabel; // 이미지를 표시할 JLabel
    private JButton backButton; // 뒤로 가기 버튼
    private JButton prevImageButton; // 이전 이미지 버튼 
    private JButton nextImageButton; // 다음 이미지 버튼 
    private JButton quizButton; // 퀴즈 페이지로 이동하는 버튼

    public EmergeTelPage(MainApp mainApp) {
        this.mainApp = mainApp;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());

        //위에 큰 제목
        JLabel titleLabel = new JLabel("    비상 연락망 저장하는 방법", JLabel.CENTER);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 100));
        titleLabel.setBackground(Color.decode("#CBDBFF")); // 배경색 설정
        titleLabel.setOpaque(true); // 배경이 투명하지 않게 설정

        // 홈 버튼 패널 생성 (우측 상단에 위치)
        JPanel northPanel = new JPanel(new BorderLayout());
        backButton = new JButton("<html><center><font size='7'>홈화면<br>돌아가기</font></center></html>");
        backButton.addActionListener(e -> mainApp.showMainPage());
        backButton.setBackground(Color.decode("#FFDBEA")); // 배경색 설정
        backButton.setForeground(Color.BLACK); // 글자색 설정
        northPanel.add(backButton, BorderLayout.EAST);
        northPanel.add(titleLabel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);

        // 이미지 아이콘 배열 초기화
        imageIcons = new ImageIcon[8]; // 이미지의 개수

        // 이미지 아이콘 배열에 이미지 아이콘들을 추가합니다.
        for (int i = 0; i < imageIcons.length; i++) {
            imageIcons[i] = new ImageIcon("emergetel" + (i + 1) + ".png"); // 이미지 파일의 경로 지정
        }

        // 이미지를 표시할 JLabel 생성 및 초기 이미지 설정
        imageLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.decode("#ffffff")); // 배경색 설정
                g.fillRect(0, 0, getWidth(), getHeight());
                // 설정된 이미지 아이콘을 그리기
                if (getIcon() != null) {
                    getIcon().paintIcon(this, g, (getWidth() - getIcon().getIconWidth()) / 2, (getHeight() - getIcon().getIconHeight()) / 2);
                }
            }
        };
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER); // 이미지를 가운데로 정렬
        imageLabel.setOpaque(true); // JLabel을 불투명하게 설정
        add(imageLabel, BorderLayout.CENTER);

        // 이미지 크기 조정 및 초기 이미지 설정
        setImageIcon(currentIndex);

        // 버튼 패널을 하단에 추가
        JPanel southPanel = new JPanel(new GridLayout(1, 3));

        // 이전 이미지로 이동하는 버튼 생성 (하단 좌측에 위치)////////////////////////////////
        prevImageButton = new JButton("◀ 이전 방법으로 이동하기");
        prevImageButton.setPreferredSize(new Dimension(300, 100)); // 버튼 크기 조정
        prevImageButton.setFont(new Font("맑은 고딕", Font.BOLD, 20)); // 버튼 글꼴 크기 조정
        prevImageButton.setBackground(Color.decode("#CBDBFF")); // 배경색 설정
        prevImageButton.setForeground(Color.BLACK); // 글자색 설정
        prevImageButton.addActionListener(e -> prevImage());
        prevImageButton.setEnabled(false); // 초기 상태에서 첫 번째 이미지이므로 비활성화
        southPanel.add(prevImageButton);

        // 다음 이미지로 이동하는 버튼 생성 (하단 가운데에 위치)
        nextImageButton = new JButton("다음 방법으로 이동 ▶");
        nextImageButton.setPreferredSize(new Dimension(300, 100)); // 버튼 크기 조정
        nextImageButton.setFont(new Font("맑은 고딕", Font.BOLD, 20)); // 버튼 글꼴 크기 조정
        nextImageButton.setBackground(Color.decode("#CBDBFF")); // 배경색 설정
        nextImageButton.setForeground(Color.BLACK); // 글자색 설정
        nextImageButton.addActionListener(e -> nextImage());
        southPanel.add(nextImageButton);

        // 퀴즈 페이지로 이동하는 버튼 생성 (하단 우측에 위치)
        quizButton = new JButton("퀴즈 페이지로 이동");
        quizButton.setPreferredSize(new Dimension(300, 100)); // 버튼 크기 조정
        quizButton.setFont(new Font("맑은 고딕", Font.BOLD, 20)); // 버튼 글꼴 크기 조정
        quizButton.setBackground(Color.decode("#CBDBFF")); // 배경색 설정
        quizButton.setForeground(Color.BLACK); // 글자색 설정
        quizButton.addActionListener(e -> mainApp.showEmergeTelQuizPage()); // 퀴즈 페이지로 이동
        quizButton.setEnabled(false); // 초기 상태에서는 비활성화
        southPanel.add(quizButton);

        add(southPanel, BorderLayout.SOUTH);
    }

    private void prevImage() {
        currentIndex--;
        if (currentIndex <= 0) {
            currentIndex = 0; // 첫 번째 이미지로 설정
            prevImageButton.setEnabled(false); // 이전 이미지 버튼 비활성화
        }
        nextImageButton.setEnabled(true); // 다음 이미지 버튼 활성화
        setImageIcon(currentIndex);
        updateQuizButtonState();
    }

    private void nextImage() {
        currentIndex++;
        if (currentIndex >= imageIcons.length - 1) {
            currentIndex = imageIcons.length - 1; // 마지막 이미지로 설정
            nextImageButton.setEnabled(false); // 다음 이미지 버튼 비활성화
        }
        prevImageButton.setEnabled(true); // 이전 이미지 버튼 활성화
        setImageIcon(currentIndex);
        updateQuizButtonState();
    }

     // 퀴즈 페이지로 이동하는 버튼 상태 업데이트
     private void updateQuizButtonState() {
        if (currentIndex == imageIcons.length - 1) {
            // 현재 이미지가 마지막 이미지인 경우 퀴즈 페이지로 이동하는 버튼 활성화
            quizButton.setEnabled(true);
        } else {
            // 그렇지 않은 경우 비활성화
            quizButton.setEnabled(false);
        }
    }

    // 이미지 아이콘을 설정하고 크기를 조정하는 메서드
    private void setImageIcon(int index) {
        ImageIcon originalIcon = imageIcons[index];
        // 이미지 아이콘의 크기를 조정하여 새로운 이미지 아이콘 생성
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(1200, 800, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        imageLabel.setIcon(scaledIcon);
        imageLabel.repaint(); // Repaint to update background
    }
}
