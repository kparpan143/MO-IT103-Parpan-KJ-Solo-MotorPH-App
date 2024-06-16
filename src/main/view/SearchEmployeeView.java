package main.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import main.MotorPH;
import main.classes.Employee;

public class SearchEmployeeView {

    // Constant Variables
    private static final String EMPLOYEE_DETAILS_CSV = System.getProperty("user.dir") + "/src/main/resources/employee_details.csv";
    
    // Variables needed for the Search Employee View
    private BufferedReader employeeDetailsReader = null;
    private JLabel lblHeader, lblEmployeeNumber;
    private JTextField txtEmployeeNumber;
    private JButton searchButton;
    
	public SearchEmployeeView () {
		
		JFrame frame = new JFrame();
        frame.setSize(450, 200);
        frame.setTitle("MotorPH Employee App");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Create the components
        // Create labels and textfields
        lblHeader = new JLabel("MotorPH Employee App");
        lblHeader.setFont(new Font("Calibri", Font.BOLD, 20));
        lblEmployeeNumber = new JLabel("Enter Employee Number:");
        txtEmployeeNumber = new JTextField(30);
        searchButton = new JButton("Search");
       
        // Define the panel to hold the components  
        JPanel panel = new JPanel();
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);
       
        // Add the components to the frame
        panel.add(lblHeader);
        panel.add(lblEmployeeNumber);
        panel.add(txtEmployeeNumber);
        panel.add(searchButton);
 
        // Put constraint on components       
        // Set label and textfield position: top and bottom
        layout.putConstraint(SpringLayout.NORTH, lblHeader, 20, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblHeader, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        layout.putConstraint(SpringLayout.NORTH, lblEmployeeNumber, 20, SpringLayout.SOUTH, lblHeader);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblEmployeeNumber, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        layout.putConstraint(SpringLayout.NORTH, txtEmployeeNumber, 10, SpringLayout.SOUTH, lblEmployeeNumber);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, txtEmployeeNumber, 0, SpringLayout.HORIZONTAL_CENTER, panel);
       
        // Set button position
        layout.putConstraint(SpringLayout.NORTH, searchButton, 10, SpringLayout.SOUTH, txtEmployeeNumber);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, searchButton, 0, SpringLayout.HORIZONTAL_CENTER, panel);


        //Add panel to frame
        frame.add(panel);
        frame.setVisible(true);

        // Add an ActionListener to the button
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Employee employee = getEmployee();
            	
            	if (employee == null) {
            		JOptionPane.showMessageDialog(null, "Employee Not Found.", "ERROR!", JOptionPane.ERROR_MESSAGE);
                }
            	else {
            		new EmployeeDetailsView(employee);
            	}
            }
        });
	}

    // Method used for getting Employee
    private Employee getEmployee () {

		resetData();
		readCSVFiles();
    	Employee employee = null;
        String employeeDetailsRow = "";
    	
        try {
            int employeeNumber = Integer.parseInt(txtEmployeeNumber.getText());
            
            // read each row of the CSV file
            while ((employeeDetailsRow = employeeDetailsReader.readLine()) != null) {
                // replace commas inside string
                String employeeDetails = employeeDetailsRow.replaceAll(",(?!(([^\"]*\"){2})*[^\"]*$)", ";x;");
                String[] splitEmployeeDetails = employeeDetails.split(",");

                // check if employee number matches row data
                if (Integer.parseInt(splitEmployeeDetails[0]) == employeeNumber) {
                    String employeeName = cleanString(splitEmployeeDetails[2] + " " + splitEmployeeDetails[1]);
                    String birthday = cleanString(splitEmployeeDetails[3]);
                    double hourlyRate = Double.parseDouble(cleanString(splitEmployeeDetails[splitEmployeeDetails.length - 1]));
                    double basicSalary = Double.parseDouble(cleanString(splitEmployeeDetails[splitEmployeeDetails.length - 6]));
                    
                    // Instantiate Employee Object
                   employee = new Employee(employeeNumber, employeeName, birthday, hourlyRate, basicSalary);
                }
            }
        } catch (IOException e) {
        		resetData();
                return null;
        } catch (NumberFormatException e1) {
    		resetData();
          	return null;
        }
        return employee;
    }

    // Method used for reseting data used in the system
    private void resetData () {
        employeeDetailsReader = null;
    }

    // Method used for reading CSV files
    private void readCSVFiles() {
        try {
            employeeDetailsReader = new BufferedReader(new FileReader(EMPLOYEE_DETAILS_CSV));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MotorPH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Method used for removing unnecessary characters in a string
    private String cleanString (String input) {
    
        input = input.replaceAll(";x;", "");
        input = input.replaceAll("\"", "");
        
        return input;
    }
}
