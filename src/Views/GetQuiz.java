package Views;

import Controllers.TeacherController;
import Models.Question;
import Models.RoundedBorder;
import Models.ScrollPaneWin11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GetQuiz extends JFrame {

    /**
     * Creates new form MakeQuiz
     */
    public GetQuiz(String title, String className, List<Question> questions) throws SQLException, ClassNotFoundException, IOException, InterruptedException {
        this.questions = questions;
        this.title = title;
        this.className = className;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() throws SQLException, ClassNotFoundException, IOException, InterruptedException {

        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        jScrollPane2 = new ScrollPaneWin11();
        jPanel2 = new JPanel();

        teacherController = new TeacherController();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Get Quiz");

        jPanel1.setBackground(new Color(148, 11, 146));

        jLabel1.setFont(new Font("Snap ITC", 1, 18)); // NOI18N
        jLabel1.setForeground(new Color(255, 255, 255));
        jLabel1.setText("TechTeach - Quiz");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel1)
                                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new Color(255, 255, 255));




        jScrollPane2.setViewportView(jPanel2);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                                .addContainerGap())
        );


        jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.Y_AXIS));

        for (int i = 0; i < questions.size(); i++) {

            JPanel panel = new JPanel();
            panel.setBackground(Color.WHITE);
            panel.setBorder(new RoundedBorder(10));

            JTextArea question = new JTextArea();
            question.setFont(new Font("Times New Roman", 0, 14));
            question.setEditable(false);
            question.setCaretColor(Color.WHITE);
            question.setText(questions.get(i).question);
            question.setColumns(20);
            question.setLineWrap(true);
            question.setRows(5);
            JScrollPane scrollPane = new JScrollPane(question);

            JRadioButton optionA = new JRadioButton();
            optionA.setFont(new Font("Times New Roman", 0, 14));
            optionA.setText(questions.get(i).answers.answer_a);
            if (questions.get(i).correct_answers.answer_a_correct){
                optionA.setFont(new Font("Times New Roman", 1, 14));
                optionA.setSelected(true);
            }

            JRadioButton optionB = new JRadioButton();
            optionB.setFont(new Font("Times New Roman", 0, 14));
            optionB.setText(questions.get(i).answers.answer_b);
            if (questions.get(i).correct_answers.answer_b_correct){
                optionB.setFont(new Font("Times New Roman", 1, 14));
                optionB.setSelected(true);
            }

            JRadioButton optionC = new JRadioButton();
            optionC.setFont(new Font("Times New Roman", 0, 14));
            optionC.setText(questions.get(i).answers.answer_c);
            if (questions.get(i).correct_answers.answer_c_correct){
                optionC.setFont(new Font("Times New Roman", 1, 14));
                optionC.setSelected(true);
            }

            JRadioButton optionD = new JRadioButton();
            optionD.setFont(new Font("Times New Roman", 0, 14));
            optionD.setText(questions.get(i).answers.answer_d);
            if (questions.get(i).correct_answers.answer_d_correct){
                optionD.setFont(new Font("Times New Roman", 1, 14));
                optionD.setSelected(true);
            }

            JRadioButton optionE = new JRadioButton();
            optionE.setFont(new Font("Times New Roman", 0, 14));
            optionE.setText(questions.get(i).answers.answer_e);
            if (questions.get(i).correct_answers.answer_e_correct){
                optionE.setFont(new Font("Times New Roman", 1, 14));
                optionE.setSelected(true);
            }

            JRadioButton optionF = new JRadioButton();
            optionF.setFont(new Font("Times New Roman", 0, 14));
            optionF.setText(questions.get(i).answers.answer_f);
            if (questions.get(i).correct_answers.answer_f_correct){
                optionF.setFont(new Font("Times New Roman", 1, 14));
                optionF.setSelected(true);
            }


            GroupLayout jPanelLayout = new GroupLayout(panel);
            panel.setLayout(jPanelLayout);
            jPanelLayout.setHorizontalGroup(
                    jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
                                            .addGroup(jPanelLayout.createSequentialGroup()
                                                    .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(optionF, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(optionE)
                                                            .addComponent(optionA, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(optionB, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(optionC, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(optionD, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .addGap(0, 0, Short.MAX_VALUE)))
                                    .addGap(14, 14, 14))
            );
            jPanelLayout.setVerticalGroup(
                    jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                    .addGap(16, 16, 16)
                                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(optionA)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(optionB)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(optionC)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(optionD)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(optionE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(optionF)
                                    .addContainerGap(8, Short.MAX_VALUE))
            );


            jPanel2.add(Box.createVerticalStrut(10));
            jPanel2.add(panel);
        }

        jPanel2.add(Box.createVerticalStrut(20));
        JButton postQuiz = new JButton("Post Quiz");
        jPanel2.add(Box.createVerticalStrut(20));
        postQuiz.setBackground(new Color(148, 11, 146));
        postQuiz.setForeground(Color.WHITE);

        postQuiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (teacherController.postQuiz(title, className, questions)){
                        JOptionPane.showConfirmDialog(null, "Quiz Posted Succcessfully", "Success", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        jPanel2.add(postQuiz);
        pack();
    }// </editor-fold>


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException |
                 InstantiationException ex) {
            java.util.logging.Logger.getLogger(GetQuiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new GetQuiz("","", null).setVisible(true);
                } catch (SQLException | InterruptedException | IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    // Variables declaration - do not modify
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JScrollPane jScrollPane2;
    private String title;
    private String className;
    private List<Question> questions;
    private TeacherController teacherController;
    // End of variables declaration
}