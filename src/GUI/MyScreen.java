package GUI;


import RSA.Alice;
import RSA.Bob;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.locks.ReentrantLock;

public class MyScreen {

    private JScrollPane innerworkings, bobPanel, alicePanel;
    private JLabel bobLabel, innerstuffLabel, aliceLabel;
    private JList<String> bobPaneMessages, alicePaneMessages, innerworkingTexts;
    private DefaultListModel<String> bobPaneLM, alicePaneLM, innerworkingLM;
    private JTextField aliceField, bobField;
    //private JButton generateAddresses;
    private MyScreen myScreen;
    private JPanel contentPane;
    private ReentrantLock lock;
    private Alice a;
    private Bob b;
    private JFrame frame;

    public MyScreen() {
        myScreen = this;
        lock = new ReentrantLock();
        frame = new JFrame("Hybrid Cryptography");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 1000);
        contentPane = new JPanel();
        contentPane.setOpaque(true);
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);
        innerworkings = new JScrollPane();
        innerworkingLM = new DefaultListModel<>();
        innerworkingTexts = new JList<>();
        innerworkingTexts.setModel(innerworkingLM);
        innerworkings.setViewportView(innerworkingTexts);
        innerworkings.setSize(frame.getWidth()/3, 800);
        innerworkings.setLocation(frame.getWidth()/3, 100);
        contentPane.add(innerworkings);


        alicePanel = new JScrollPane();
        alicePaneLM = new DefaultListModel<>();
        alicePaneMessages = new JList<>();
        alicePaneMessages.setModel(alicePaneLM);
        alicePanel.setViewportView(alicePaneMessages);
        alicePanel.setSize(frame.getWidth()/3, 600);
        alicePanel.setLocation(0, 100);
        contentPane.add(alicePanel);

        bobPanel = new JScrollPane();
        bobPaneLM = new DefaultListModel<>();
        bobPaneMessages = new JList<>();
        bobPaneMessages.setModel(bobPaneLM);
        bobPanel.setViewportView(bobPaneMessages);
        bobPanel.setSize(frame.getWidth()/3, 600);
        bobPanel.setLocation(2 * frame.getWidth()/3, 100);
        contentPane.add(bobPanel);

        aliceLabel = new JLabel("ALICE");
        aliceLabel.setLocation(alicePanel.getWidth()/2, 0);
        aliceLabel.setSize(95, 100);
        contentPane.add(aliceLabel);

        innerstuffLabel = new JLabel("CALCULATIONS", JLabel.CENTER);
        innerstuffLabel.setLocation(innerworkings.getWidth()/2  + innerworkings.getLocation().x, 0);
        innerstuffLabel.setSize(110, 100);
        contentPane.add(innerstuffLabel);

        bobLabel = new JLabel("BOB");
        bobLabel.setLocation(bobPanel.getWidth()/2 + bobPanel.getLocation().x, 0);
        bobLabel.setSize(95, 100);
        contentPane.add(bobLabel);

        aliceField = new JTextField();
        aliceField.setSize(alicePanel.getWidth(), 20);
        aliceField.setLocation(0, alicePanel.getHeight() + 100);
        contentPane.add(aliceField);

        bobField = new JTextField();
        bobField.setSize(bobPanel.getWidth(), 20);
        bobField.setLocation(bobPanel.getLocation().x, bobPanel.getHeight() + 100);
        contentPane.add(bobField);

       /* generateAddresses = new JButton("Generate Public Keys");
        generateAddresses.setSize(200, 50);
        generateAddresses.setLocation(innerstuffLabel.getLocation().x - 120, innerworkings.getHeight() + 100);
        contentPane.add(generateAddresses);
*/
        frame.setContentPane(contentPane);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        aliceField.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                alicePaneLM.addElement("[ALICE]: " + aliceField.getText());
                Thread helper = new Thread(new EncryptionDecryptionController(myScreen, b.getRSAinstance().getN(), b.getRSAinstance().getE(),aliceField.getText(), true));
                helper.start();
                aliceField.setText("");

            }
        });

        bobField.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bobPaneLM.addElement("[BOB]: " + bobField.getText());
                Thread helper = new Thread(new EncryptionDecryptionController(myScreen, a.getRSAinstance().getN(), a.getRSAinstance().getE(), bobField.getText(), false));
                helper.start();
                bobField.setText("");
            }
        });

        Thread helper = new Thread(new Controller(myScreen));
        helper.start();

      /*  generateAddresses.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                Thread helper = new Thread(new Controller(myScreen));
                helper.start();
            }
        });*/

    }

    public void addToListModel(final String s){
        SwingUtilities.invokeLater(() -> {
            lock.lock();
            try{
                innerworkingLM.addElement(s);
            } finally {
                lock.unlock();
            }
        });

    }

    public void addToBobListModel(final String s){
        SwingUtilities.invokeLater(() -> {
            lock.lock();
            try {
                bobPaneLM.addElement(s);
            } finally {
                lock.unlock();
            }
        });
    }

    public void addToAliceListModel(final String s){
        SwingUtilities.invokeLater(() -> {
            lock.lock();
            try {
                alicePaneLM.addElement(s);
            } finally {
                lock.unlock();
            }
        });
    }

    public void setA(Alice a){
        this.a = a;
    }

    public void setB(Bob b){
        this.b = b;
    }

    public Bob getB() {
        return b;
    }

    public Alice getA() {
        return a;
    }

    /* public void run(boolean flag){
//        innerworkingTexts.setModel(innerworkingLM);
//        innerworkings.setViewportView(innerworkingTexts);
//        //Thread.sleep(300);
        if(flag) {
            innerworkingLM.addElement("GENERATING ALICE PUBLIC KEY");
            a = new Alice();
        }
        else {
            innerworkingLM.addElement("GENERATING BOB PUBLIC KEY");
            b = new Bob();
        }
    }*/
}
