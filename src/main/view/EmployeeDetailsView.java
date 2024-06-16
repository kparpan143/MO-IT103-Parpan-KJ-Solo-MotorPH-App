package main.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Year;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import main.MotorPH;
import main.classes.Employee;

public class EmployeeDetailsView {
    
    // Variables needed for the Employee Details View
    private JLabel lblHeader, lblHeader2, lblEmployeeNumber, lblEmployeeName, lblBirthdate, lblHourlyRate, lblBasicSalary, lblYear, lblMonth;
    private JTextField txtEmployeeNumber, txtEmployeeName, txtBirthdate, txtHourlyRate, txtBasicSalary, txtYear;
    private JButton calculateButton;
    
    private final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    
    
	public EmployeeDetailsView (Employee employee) {
		JFrame frame = new JFrame();
        frame.setSize(450, 400);
        frame.setTitle("MotorPH Employee App");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Create the components
        // Create labels and textfields
        lblHeader = new JLabel("MotorPH Employee Details");
        lblHeader.setFont(new Font("Calibri", Font.BOLD, 20));
        lblHeader2 = new JLabel("Calculate Salary");
        lblHeader2.setFont(new Font("Calibri", Font.BOLD, 20));
        lblEmployeeNumber = new JLabel("Employee Number:");
        txtEmployeeNumber = new JTextField(20);
        txtEmployeeNumber.setEnabled(false);
        txtEmployeeNumber.setText(String.valueOf(employee.getEmployeeNumber()));
        
        lblEmployeeName = new JLabel("Employee Name:");
        txtEmployeeName = new JTextField(20);
        txtEmployeeName.setEnabled(false);
        txtEmployeeName.setText(String.valueOf(employee.getEmployeeName()));
        
        lblBirthdate = new JLabel("Employee Birthdate:");
        txtBirthdate = new JTextField(20);
        txtBirthdate.setEnabled(false);
        txtBirthdate.setText(String.valueOf(employee.getBirthday()));
        
        lblHourlyRate = new JLabel("Hourly Rate:");
        txtHourlyRate = new JTextField(20);
        txtHourlyRate.setEnabled(false);
        txtHourlyRate.setText(String.valueOf(employee.getHourlyRate()));
        
        lblBasicSalary = new JLabel("Basic Salary:");
        txtBasicSalary = new JTextField(20);
        txtBasicSalary.setEnabled(false);
        txtBasicSalary.setText(String.valueOf(employee.getBasicSalary()));
        
        lblYear = new JLabel("Enter Year:");
        txtYear = new JTextField(5);
        txtYear.setFont(new Font(txtYear.getFont().getName(), Font.PLAIN, 15));
        lblMonth = new JLabel("Select Month:");
        final JComboBox<String> cbMonth = new JComboBox<String>(MONTHS);
        
        calculateButton = new JButton("Calculate Salary");
        calculateButton.setPreferredSize(new Dimension(200, 50));
        
        // Define the panel to hold the components  
        JPanel panel = new JPanel();
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);
       
        // Add the components to the frame
        panel.add(lblHeader);
        panel.add(lblHeader2);
        panel.add(lblEmployeeNumber);
        panel.add(txtEmployeeNumber);
        panel.add(lblEmployeeName);
        panel.add(txtEmployeeName);
        panel.add(lblBirthdate);
        panel.add(txtBirthdate);
        panel.add(lblHourlyRate);
        panel.add(txtHourlyRate);
        panel.add(lblBasicSalary);
        panel.add(txtBasicSalary);
        panel.add(lblYear);
        panel.add(txtYear);
        panel.add(lblMonth);
        panel.add(cbMonth);
        panel.add(calculateButton);
        
        // Put constraint on components       
        // Employee Details Section
        // Set label and textfield position: top and bottom
        layout.putConstraint(SpringLayout.NORTH, lblHeader, 20, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblHeader, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        
        layout.putConstraint(SpringLayout.NORTH, lblEmployeeNumber, 20, SpringLayout.SOUTH, lblHeader);
        layout.putConstraint(SpringLayout.WEST, lblEmployeeNumber, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, txtEmployeeNumber, 20, SpringLayout.SOUTH, lblHeader);
        layout.putConstraint(SpringLayout.WEST, txtEmployeeNumber, 18, SpringLayout.EAST, lblEmployeeNumber);

        layout.putConstraint(SpringLayout.NORTH, lblEmployeeName, 10, SpringLayout.SOUTH, lblEmployeeNumber);
        layout.putConstraint(SpringLayout.WEST, lblEmployeeName, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, txtEmployeeName, 6, SpringLayout.SOUTH, txtEmployeeNumber);
        layout.putConstraint(SpringLayout.WEST, txtEmployeeName, 30, SpringLayout.EAST, lblEmployeeName);

        layout.putConstraint(SpringLayout.NORTH, lblBirthdate, 10, SpringLayout.SOUTH, lblEmployeeName);
        layout.putConstraint(SpringLayout.WEST, lblBirthdate, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, txtBirthdate, 6, SpringLayout.SOUTH, txtEmployeeName);
        layout.putConstraint(SpringLayout.WEST, txtBirthdate, 10, SpringLayout.EAST, lblBirthdate);

        layout.putConstraint(SpringLayout.NORTH, lblHourlyRate, 10, SpringLayout.SOUTH, lblBirthdate);
        layout.putConstraint(SpringLayout.WEST, lblHourlyRate, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, txtHourlyRate, 6, SpringLayout.SOUTH, txtBirthdate);
        layout.putConstraint(SpringLayout.WEST, txtHourlyRate, 53, SpringLayout.EAST, lblHourlyRate);

        layout.putConstraint(SpringLayout.NORTH, lblBasicSalary, 10, SpringLayout.SOUTH, lblHourlyRate);
        layout.putConstraint(SpringLayout.WEST, lblBasicSalary, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, txtBasicSalary, 6, SpringLayout.SOUTH, txtHourlyRate);
        layout.putConstraint(SpringLayout.WEST, txtBasicSalary, 50, SpringLayout.EAST, lblBasicSalary);
        
        
        // Calculate Salary Section
        layout.putConstraint(SpringLayout.NORTH, lblHeader2, 40, SpringLayout.NORTH, lblBasicSalary);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblHeader2, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        
        layout.putConstraint(SpringLayout.NORTH, lblYear, 20, SpringLayout.SOUTH, lblHeader2);
        layout.putConstraint(SpringLayout.WEST, lblYear, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, txtYear, 10, SpringLayout.SOUTH, lblYear);
        layout.putConstraint(SpringLayout.WEST, txtYear, 10, SpringLayout.WEST, panel);

        layout.putConstraint(SpringLayout.NORTH, lblMonth, 20, SpringLayout.SOUTH, lblHeader2);
        layout.putConstraint(SpringLayout.WEST, lblMonth, 30, SpringLayout.EAST, lblYear);
        layout.putConstraint(SpringLayout.NORTH, cbMonth, 10, SpringLayout.SOUTH, lblMonth);
        layout.putConstraint(SpringLayout.WEST, cbMonth, 30, SpringLayout.EAST, txtYear);
        
        // Set button position
        layout.putConstraint(SpringLayout.NORTH, calculateButton, 20, SpringLayout.SOUTH, lblHeader2);
        layout.putConstraint(SpringLayout.WEST, calculateButton, 20, SpringLayout.EAST, cbMonth);


        //Add panel to frame
        frame.add(panel);
        frame.setVisible(true);

        // Add an ActionListener to the button
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	int year = Integer.parseInt(txtYear.getText());
                	int month = cbMonth.getSelectedIndex();

                    if (year > (Year.now().getValue() - 5) && year <= Year.now().getValue()) {
                    	new SalaryComputationView(employee, year, month);
                    } else {
                    	JOptionPane.showMessageDialog(null, "Invalid Year Input", "ERROR!", JOptionPane.ERROR_MESSAGE);
                    }
                	
                } catch (NumberFormatException e1) {
                	JOptionPane.showMessageDialog(null, "Invalid Year Input", "ERROR!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
	}
}
