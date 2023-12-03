package Views;

import Controllers.StudentController;
import Models.ScrollPaneWin11;
import Models.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentComments extends JFrame {

    /**
     * Creates new form Comments
     */
    public StudentComments(String username, String title, String className) throws SQLException, ClassNotFoundException {
        this.username = username;
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
    private void initComponents() throws SQLException, ClassNotFoundException {

        comment = new JTextField();
        commentButton = new JButton();
        jScrollPane1 = new ScrollPaneWin11();
        commentPanel = new JPanel();

        studentController = new StudentController();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Comments");
        setResizable(false);

        comment.setFont(new Font("Times New Roman", 0, 14)); // NOI18N

        commentButton.setBackground(new Color(91, 8, 136));
        commentButton.setFont(new Font("Times New Roman", 0, 14)); // NOI18N
        commentButton.setForeground(new Color(255, 255, 255));
        commentButton.setText("Comment");
        commentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    commentButtonActionPerformed(evt);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        commentPanel.setBackground(new Color(255, 255, 255));
        commentPanel.setMaximumSize(new Dimension(380, 417));
        commentPanel.setPreferredSize(new Dimension(380, 417));
        commentPanel.setMinimumSize(new Dimension(380, 417));



        jScrollPane1.setViewportView(commentPanel);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(comment)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(commentButton))
                                        .addComponent(jScrollPane1))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(comment)
                                        .addComponent(commentButton, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                                .addContainerGap())
        );

        HashMap<Integer, String> comments = studentController.getTeacherComments(title, className);
        if (!comments.isEmpty()){

            commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.Y_AXIS));

            for (int id: comments.keySet()){

                JPanel panel = new JPanel();
                panel.setBorder(new RoundedBorder(10));
                panel.setBackground(Color.WHITE);

                JTextArea area = new JTextArea();
                area.setColumns(20);
                area.setRows(5);
                area.setEditable(false);
                area.setCaretColor(Color.WHITE);
                area.setLineWrap(true);
                area.setFont(new Font("Times New Roman", 0, 14));
                area.setText(comments.get(id) + "\nBy Class Teacher");
                area.setForeground(new Color(91, 8, 136));
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setViewportView(area);

                JButton button = new JButton("See Reply");
                button.setBackground(new Color(91, 8, 136));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Times New Roman", 0, 14));
                button.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        String reply = null;
                        try {
                            reply = studentController.getReply(id);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        SeeReply s = new SeeReply(reply);
                        s.setLocationRelativeTo(null);
                        s.setVisible(true);
                    }
                });


                GroupLayout jPanelLayout = new GroupLayout(panel);
                panel.setLayout(jPanelLayout);
                jPanelLayout.setHorizontalGroup(
                        jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                                                .addGroup(jPanelLayout.createSequentialGroup()
                                                        .addComponent(button)
                                                        .addGap(0, 0, Short.MAX_VALUE)))
                                        .addContainerGap())
                );
                jPanelLayout.setVerticalGroup(
                        jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(button)
                                        .addContainerGap(7, Short.MAX_VALUE))
                );

                commentPanel.add(panel);
                panel.setMaximumSize(new Dimension(commentPanel.getMaximumSize().width, 170));
                commentPanel.add(Box.createVerticalStrut(10));
            }
        }


        HashMap<Integer, ArrayList<String>> studentComments = studentController.getStudentComments(title, className);
        if (!studentComments.isEmpty()){

            commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.Y_AXIS));

            for (int id: studentComments.keySet()){

                JPanel panel = new JPanel();
                panel.setBorder(new RoundedBorder(10));
                panel.setBackground(Color.WHITE);

                JTextArea area = new JTextArea();
                area.setColumns(20);
                area.setRows(5);
                area.setEditable(false);
                area.setCaretColor(Color.WHITE);
                area.setLineWrap(true);
                area.setFont(new Font("Times New Roman", 0, 14));
                ArrayList<String> commentDetails = studentComments.get(id);
                area.setText(commentDetails.get(0) + "\n------------------------------------------------" + "\n" + commentDetails.get(1));
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setViewportView(area);

                JButton button = new JButton("See Reply");
                button.setBackground(new Color(91, 8, 136));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Times New Roman", 0, 14));
                button.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        String reply = null;
                        try {
                            reply = studentController.getReply(id);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        SeeReply s = new SeeReply(reply);
                        s.setLocationRelativeTo(null);
                        s.setVisible(true);
                    }
                });


                GroupLayout jPanelLayout = new GroupLayout(panel);
                panel.setLayout(jPanelLayout);
                jPanelLayout.setHorizontalGroup(
                        jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                                                .addGroup(jPanelLayout.createSequentialGroup()
                                                        .addComponent(button)
                                                        .addGap(0, 0, Short.MAX_VALUE)))
                                        .addContainerGap())
                );
                jPanelLayout.setVerticalGroup(
                        jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(button)
                                        .addContainerGap(7, Short.MAX_VALUE))
                );

                commentPanel.add(panel);
                panel.setMaximumSize(new Dimension(commentPanel.getMaximumSize().width, 170));
                commentPanel.add(Box.createVerticalStrut(10));
            }
        }

        pack();
    }// </editor-fold>

    private void commentButtonActionPerformed(ActionEvent evt) throws SQLException {

        if (!comment.getText().trim().isEmpty()){

            int commentId = studentController.comment(username, title, className, comment.getText().trim());

            commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.Y_AXIS));

            JPanel panel = new JPanel();
            panel.setBorder(new RoundedBorder(10));
            panel.setBackground(Color.WHITE);

            JTextArea area = new JTextArea();
            area.setColumns(20);
            area.setRows(5);
            area.setEditable(false);
            area.setCaretColor(Color.WHITE);
            area.setLineWrap(true);
            area.setFont(new Font("Times New Roman", 0, 14));
            area.setText(comment.getText());
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setViewportView(area);

            JButton button = new JButton("See Reply");
            button.setBackground(new Color(91, 8, 136));
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Times New Roman", 0, 14));
            button.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    String reply = null;
                    try {
                        reply = studentController.getReply(commentId);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    SeeReply s = new SeeReply(reply);
                    s.setLocationRelativeTo(null);
                    s.setVisible(true);
                }
            });


            GroupLayout jPanelLayout = new GroupLayout(panel);
            panel.setLayout(jPanelLayout);
            jPanelLayout.setHorizontalGroup(
                    jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                                            .addGroup(jPanelLayout.createSequentialGroup()
                                                    .addComponent(button)
                                                    .addGap(0, 0, Short.MAX_VALUE)))
                                    .addContainerGap())
            );
            jPanelLayout.setVerticalGroup(
                    jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(button)
                                    .addContainerGap(7, Short.MAX_VALUE))
            );

            commentPanel.add(panel);
            panel.setMaximumSize(new Dimension(commentPanel.getMaximumSize().width, 170));
            commentPanel.add(Box.createVerticalStrut(10));

        }
        else{
            JOptionPane.showConfirmDialog(null, "No Comment Written", "Error", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
        }
    }

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentComments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentComments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentComments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentComments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new StudentComments("", "", "").setVisible(true);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    // Variables declaration - do not modify
    private JTextField comment;
    private JButton commentButton;
    private JPanel commentPanel;
    private JScrollPane jScrollPane1;
    private String title;
    private String className;
    private String username;
    private StudentController studentController;
    // End of variables declaration
}