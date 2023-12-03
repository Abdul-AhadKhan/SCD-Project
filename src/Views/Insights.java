package Views;

import Controllers.TeacherController;
import Models.ScrollPaneWin11;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.HashMap;

public class Insights {

    public Insights(String className) throws SQLException, ClassNotFoundException {

        teacherController = new TeacherController();


        JFrame frame = new JFrame("Students Joined Classroom");
        frame.setSize(700, 800);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(700, 700));
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new ScrollPaneWin11();
        scrollPane.setViewportView(panel);


        HashMap<Integer, Integer> studentsjoined = teacherController.studentsJoinedClassroom(className);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int month: studentsjoined.keySet()){
            dataset.setValue(studentsjoined.get(month), "Months", String.valueOf(month));
        }

        JFreeChart jFreeChart = ChartFactory.createBarChart("Student Joined Classroom", "Months", "Number of Students", dataset, PlotOrientation.VERTICAL, true, true, false);

        CategoryPlot categoryPlot = jFreeChart.getCategoryPlot();
        categoryPlot.setRangeGridlinePaint(Color.BLACK);

        BarRenderer renderer = new BarRenderer();
        renderer.setMaximumBarWidth(0.1); // Set the maximum width as needed (adjust the value accordingly)
        categoryPlot.setRenderer(renderer);


        ChartPanel chartPanel = new ChartPanel(jFreeChart);

        panel.removeAll();
        panel.add(chartPanel);
        panel.updateUI();

        panel.add(Box.createVerticalStrut(20));


        dataset = new DefaultCategoryDataset();

        HashMap<Integer, Integer> rating = teacherController.rating(className);

        for (int ratingValue: rating.keySet()){
            dataset.setValue(rating.get(ratingValue), "Rating", String.valueOf(ratingValue));
        }

        jFreeChart = ChartFactory.createBarChart("Ratings", "Rating", "", dataset, PlotOrientation.VERTICAL, true, true, false);

        categoryPlot = jFreeChart.getCategoryPlot();
        categoryPlot.setRangeGridlinePaint(Color.BLACK);

        renderer = new BarRenderer();
        renderer.setMaximumBarWidth(0.1); // Set the maximum width as needed (adjust the value accordingly)
        categoryPlot.setRenderer(renderer);

        chartPanel =  new ChartPanel(jFreeChart);

        panel.add(chartPanel);
        panel.updateUI();


        frame.add(scrollPane);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new Insights("DBMS");
    }

    private TeacherController teacherController;
}
