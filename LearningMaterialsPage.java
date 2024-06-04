import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;



public class LearningMaterialsPage extends JPanel {
    private MainApp mainApp;
    private List<LearningContent> learningMaterials;

    public LearningMaterialsPage(MainApp mainApp) {
        //MainApp 객체를 매개변수로
        //setupUI() 메서드를 호출해 UI 설정
        this.mainApp = mainApp;
        setupUI();
    }

    //setupUI 메서드에서는 BorderLayout을 설정한 후, 학습 자료 목록을 생성하고 화면에 표시
    private void setupUI() {
        setLayout(new BorderLayout());

        // setBackground(Color.WHITE);

        UIManager.put("Button.background", Color.decode("#ffffff"));

        // 학습자료 목록 생성
        // learningMaterials 리스트에는 LearningContent 객체가 포함
        // LearningContent 객체는 학습자료의 제목과 설명
        learningMaterials = new ArrayList<>();
        learningMaterials.add(new LearningContent("DAY1. 카메라 이용하기", "스마트폰 카메라를 사용하는 방법에 대한 상세 가이드."));
        learningMaterials.add(new LearningContent("DAY2. 문자 보내기", "문자 메시지를 보내는 방법에 대한 상세 가이드."));
        learningMaterials.add(new LearningContent("DAY3. 앱 설치하기", "스마트폰에서 앱을 설치하는 방법에 대한 상세 가이드."));
        learningMaterials.add(new LearningContent("DAY4. 글자 크기 키우기", "스마트폰의 글자를 키우는 방법에 대한 상세 가이드."));
        learningMaterials.add(new LearningContent("DAY5. 비상 연락망 저장하기", "스마트폰에서 비상 연락망을 저장하는 방법에 대한 상세 가이드."));

        setBackground(Color.decode("#CBDBFF"));

        // materialsPanel은 JButton을 담는 JPanel
        // 각 버튼은 학습자료의 제목을 표시하고, 해당 버튼을 클릭하면 해당 학습자료의 설명이 다이얼로그로 표시
        JPanel materialsPanel = new JPanel(new GridLayout(0, 2, 0, 0)); // 열의 개수를 2로 설정
        for (LearningContent material : learningMaterials) {
            JButton materialButton = new JButton(material.getTitle());
            materialButton.setToolTipText(material.getDescription());
            materialButton.setFont(new Font("함초롬돋움", Font.PLAIN, 30)); // 텍스트 크기 설정
            materialButton.addActionListener(e -> {
                switch (material.getTitle()) {
                    case "DAY1. 카메라 이용하기":
                        mainApp.showCameraPage();
                        break;
                    case "DAY2. 문자 보내기":
                        mainApp.showMessagePage();
                        break;
                    case "DAY3. 앱 설치하기":
                        mainApp.showAppInstallPage();
                        break;
                    case "DAY4. 글자 크기 키우기":
                        mainApp.showFontSizePage();
                        break;
                    case "DAY5. 비상 연락망 저장하기":
                        mainApp.showEmergeTelPage();
                        break;
                }
            });
            materialsPanel.add(materialButton);
        }



        // 버튼 패널을 스크롤 가능한 패널로 감싸기
        JScrollPane scrollPane = new JScrollPane(materialsPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // 수평 스크롤바 비활성화

        // 스크롤 패널을 MainPage에 추가
        add(scrollPane, BorderLayout.CENTER);

        UIManager.put("Button.background", Color.decode("#CBDBFF"));

        // 메인 화면으로 돌아가기 위한 버튼
        // mainApp 객체의 showMainPage() 메서드를 호출하여 메인 화면을 표시
        JButton backButton = new JButton("메인 화면으로 돌아가기");
        backButton.addActionListener(e -> mainApp.showMainPage());
        backButton.setFont(new Font("맑은 고딕", Font.PLAIN, 30)); // 텍스트 크기 설정


        JLabel titleLabel = new JLabel("학습자료집", JLabel.CENTER);
        // 제목의 폰트를 설정하여 크기를 키웁니다.
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 100));

        // JLabel을 이용하여 학습자료집 제목을 표시하고, materialsPanel과 backButton을 BorderLayout의 NORTH, CENTER, SOUTH에 각각 추가
        add(titleLabel, BorderLayout.NORTH);
        add(materialsPanel, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
    }
}