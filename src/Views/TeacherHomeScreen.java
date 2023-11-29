package Views;

import Controllers.TeacherController;
import Models.ScrollPaneWin11;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class TeacherHomeScreen extends javax.swing.JFrame {


    public TeacherHomeScreen(String username) throws SQLException, ClassNotFoundException {
        this.username = username;
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() throws SQLException, ClassNotFoundException {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new ScrollPaneWin11();
        jPanel2 = new javax.swing.JPanel();

        teacherController = new TeacherController();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(148,11,146));

        jLabel1.setFont(new java.awt.Font("Snap ITC", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("TechTeach");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/scd/finalproject/icons/icons8-add-30.png"))); // NOI18N
        jLabel2.setText("Create Class");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    jLabel2MouseClicked(evt);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 438, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setMinimumSize(new java.awt.Dimension(725, 426));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jPanel2.setMinimumSize(new java.awt.Dimension(723, 424));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));
        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        ArrayList<String> classes = teacherController.getClasses(username);
        jScrollPane1.setPreferredSize(new Dimension(0, 0));
        jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.PAGE_AXIS));
        //jPanel2.add(Box.createVerticalStrut(10));
        if (classes.size() >= 1) {

            for (String classname: classes) {

                JPanel panel = new JPanel();

                panel.setPreferredSize(new Dimension(500, 100));
                panel.setMinimumSize(new Dimension(500, 100));
                panel.setMaximumSize(new Dimension(jPanel2.getMaximumSize().width, 100));
                panel.setBackground(Color.WHITE);
                panel.setLayout(new BorderLayout());

                JLabel className = new JLabel(classname);
                className.setFont(new Font("Times New Roman", 1, 24));
                JLabel teacherName = new JLabel(username);
                teacherName.setFont(new Font("Times New Roman", 0, 18));


                className.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            TeacherClass teacherClass = new TeacherClass(className.getText().trim(), teacherName.getText().trim());
                            teacherClass.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowClosed(WindowEvent e) {
                                    try {
                                        TeacherHomeScreen teacherHomeScreen = new TeacherHomeScreen(username);
                                        teacherHomeScreen.setVisible(true);
                                    } catch (SQLException | ClassNotFoundException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }
                            });
                            teacherClass.setVisible(true);
                        } catch (SQLException | ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        className.setForeground(new Color(91, 8, 136));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        className.setForeground(Color.BLACK);
                    }
                });

                teacherName.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            TeacherClass teacherClass = new TeacherClass(className.getText().trim(), teacherName.getText().trim());
                            teacherClass.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowClosed(WindowEvent e) {
                                    try {
                                        TeacherHomeScreen teacherHomeScreen = new TeacherHomeScreen(username);
                                        teacherHomeScreen.setVisible(true);
                                    } catch (SQLException | ClassNotFoundException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }
                            });
                            teacherClass.setVisible(true);

                        } catch (SQLException | ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        teacherName.setForeground(new Color(91, 8, 136));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        teacherName.setForeground(Color.BLACK);
                    }
                });

                panel.add(className, BorderLayout.BEFORE_FIRST_LINE);
                panel.add(teacherName, BorderLayout.LINE_START);
                panel.setBorder(new RoundedBorder(10));

                jPanel2.add(panel);
                jPanel2.add(Box.createVerticalStrut(10));
            }
        }
        pack();
    }// </editor-fold>

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) throws SQLException, ClassNotFoundException {

        CreateClass createClass = new CreateClass(username);

        createClass.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    TeacherHomeScreen teacherHomeScreen = new TeacherHomeScreen(username);
                    teacherHomeScreen.setVisible(true);
                } catch (SQLException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        createClass.setVisible(true);
    }


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TeacherHomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeacherHomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeacherHomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeacherHomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TeacherHomeScreen("Ahad").setVisible(true);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    class RoundedBorder implements Border {
        private int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private TeacherController teacherController;
    private String username;
    // End of variables declaration
}
