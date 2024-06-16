package main.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import main.MotorPH;
import main.classes.*;

public class SalaryComputationView  {

    // Constant Variables
    private static final String ATTENDANCE_CSV = System.getProperty("user.dir") + "/src/main/resources/attendance.csv";
    
    // Variables needed for the Salary Computation View
    private JLabel lblHeader, lblYear, lblMonth, lblEmployeeNumber, lblEmployeeName, lblGrossWage, lblNetWage, lblDeductions, lblSSS, lblPagibig, lblPhilHealth, lblWitholdingTax;
    private JTextField txtEmployeeNumber, txtEmployeeName, txtYear, txtMonth, txtGrossWage, txtNetWage, txtDeductions, txtSSS, txtPagibig, txtPhilHealth, txtWitholdingTax;
    
    private final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private BufferedReader attendanceReader = null;
    
	public SalaryComputationView (Employee employee, int year, int month) {

        List<Attendance> attendances = getMonthlyAttendance(employee, month, year);
        if (!attendances.isEmpty()) {
            Salary salary = calculateSalary(employee, attendances);
            
    		JFrame frame = new JFrame();
            frame.setSize(400, 400);
            frame.setTitle("MotorPH Employee App");
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);

            // Create the components
            // Create labels and textfields
            lblHeader = new JLabel("MotorPH Salary Computation");
            lblHeader.setFont(new Font("Calibri", Font.BOLD, 20));
            
            lblEmployeeNumber = new JLabel("Employee Number:");
            txtEmployeeNumber = new JTextField(20);
            txtEmployeeNumber.setEnabled(false);
            txtEmployeeNumber.setText(String.valueOf(employee.getEmployeeNumber()));
            
            lblEmployeeName = new JLabel("Employee Name:");
            txtEmployeeName = new JTextField(20);
            txtEmployeeName.setEnabled(false);
            txtEmployeeName.setText(String.valueOf(employee.getEmployeeName()));
            
            lblYear = new JLabel("Year:");
            txtYear = new JTextField(10);
            txtYear.setEnabled(false);
            txtYear.setText(String.valueOf(year));
            
            lblMonth = new JLabel("Month:");
            txtMonth = new JTextField(10);
            txtMonth.setEnabled(false);
            txtMonth.setText(MONTHS[month]);
            
            lblGrossWage = new JLabel("Gross Wage:");
            txtGrossWage = new JTextField(20);
            txtGrossWage.setEnabled(false);
            txtGrossWage.setText(String.valueOf(salary.getGrossWage()));

            lblDeductions = new JLabel("Deductions:");
            txtDeductions = new JTextField(20);
            txtDeductions.setEnabled(false);
            txtDeductions.setText(String.valueOf(salary.getSalaryDeduction().getTotalDeductions()));

            lblSSS = new JLabel("SSS Contribution:");
            txtSSS = new JTextField(20);
            txtSSS.setEnabled(false);
            txtSSS.setText(String.valueOf(salary.getSalaryDeduction().getSSSContribution()));

            lblPagibig = new JLabel("Pagibig Contribution:");
            txtPagibig = new JTextField(20);
            txtPagibig.setEnabled(false);
            txtPagibig.setText(String.valueOf(salary.getSalaryDeduction().getPagibigContribution()));

            lblPhilHealth = new JLabel("PhilHealth Contribution:");
            txtPhilHealth = new JTextField(20);
            txtPhilHealth.setEnabled(false);
            txtPhilHealth.setText(String.valueOf(salary.getSalaryDeduction().getPhilHealthContribution()));

            lblWitholdingTax = new JLabel("Witholding Tax:");
            txtWitholdingTax = new JTextField(20);
            txtWitholdingTax.setEnabled(false);
            txtWitholdingTax.setText(String.valueOf(salary.getSalaryDeduction().getWitholdingTax()));

            lblNetWage = new JLabel("Net Wage:");
            txtNetWage = new JTextField(20);
            txtNetWage.setEnabled(false);
            txtNetWage.setText(String.valueOf(salary.getNetWage()));
            
            // Define the panel to hold the components  
            JPanel panel = new JPanel();
            SpringLayout layout = new SpringLayout();
            panel.setLayout(layout);
           
            // Add the components to the frame
            panel.add(lblHeader);
            panel.add(lblEmployeeNumber);
            panel.add(txtEmployeeNumber);
            panel.add(lblEmployeeName);
            panel.add(txtEmployeeName);
            panel.add(lblYear);
            panel.add(txtYear);
            panel.add(lblMonth);
            panel.add(txtMonth);
            panel.add(lblGrossWage);
            panel.add(txtGrossWage);
            panel.add(lblDeductions);
            panel.add(txtDeductions);
            panel.add(lblSSS);
            panel.add(txtSSS);
            panel.add(lblPagibig);
            panel.add(txtPagibig);
            panel.add(lblPhilHealth);
            panel.add(txtPhilHealth);
            panel.add(lblWitholdingTax);
            panel.add(txtWitholdingTax);
            panel.add(lblNetWage);
            panel.add(txtNetWage);
            
            // Put constraint on components       
            // Employee Details Section
            // Set label and textfield position: top and bottom
            layout.putConstraint(SpringLayout.NORTH, lblHeader, 20, SpringLayout.NORTH, panel);
            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblHeader, 0, SpringLayout.HORIZONTAL_CENTER, panel);
            
            layout.putConstraint(SpringLayout.NORTH, lblEmployeeNumber, 20, SpringLayout.SOUTH, lblHeader);
            layout.putConstraint(SpringLayout.WEST, lblEmployeeNumber, 10, SpringLayout.WEST, panel);
            layout.putConstraint(SpringLayout.NORTH, txtEmployeeNumber, 20, SpringLayout.SOUTH, lblHeader);
            layout.putConstraint(SpringLayout.EAST, txtEmployeeNumber, -10, SpringLayout.EAST, panel);

            layout.putConstraint(SpringLayout.NORTH, lblEmployeeName, 10, SpringLayout.SOUTH, lblEmployeeNumber);
            layout.putConstraint(SpringLayout.WEST, lblEmployeeName, 10, SpringLayout.WEST, panel);
            layout.putConstraint(SpringLayout.NORTH, txtEmployeeName, 6, SpringLayout.SOUTH, txtEmployeeNumber);
            layout.putConstraint(SpringLayout.EAST, txtEmployeeName, -10, SpringLayout.EAST, panel);

            layout.putConstraint(SpringLayout.NORTH, lblYear, 10, SpringLayout.SOUTH, lblEmployeeName);
            layout.putConstraint(SpringLayout.WEST, lblYear, 10, SpringLayout.WEST, panel);
            layout.putConstraint(SpringLayout.NORTH, txtYear, 6, SpringLayout.SOUTH, txtEmployeeName);
            layout.putConstraint(SpringLayout.WEST, txtYear, 10, SpringLayout.EAST, lblYear);

            layout.putConstraint(SpringLayout.NORTH, lblMonth, 10, SpringLayout.SOUTH, lblEmployeeName);
            layout.putConstraint(SpringLayout.WEST, lblMonth, 20, SpringLayout.EAST, txtYear);
            layout.putConstraint(SpringLayout.NORTH, txtMonth, 6, SpringLayout.SOUTH, txtEmployeeName);
            layout.putConstraint(SpringLayout.WEST, txtMonth, 10, SpringLayout.EAST, lblMonth);

            layout.putConstraint(SpringLayout.NORTH, lblGrossWage, 20, SpringLayout.SOUTH, lblMonth);
            layout.putConstraint(SpringLayout.WEST, lblGrossWage, 10, SpringLayout.WEST, panel);
            layout.putConstraint(SpringLayout.NORTH, txtGrossWage, 16, SpringLayout.SOUTH, txtMonth);
            layout.putConstraint(SpringLayout.EAST, txtGrossWage, -10, SpringLayout.EAST, panel);

            layout.putConstraint(SpringLayout.NORTH, lblDeductions, 10, SpringLayout.SOUTH, lblGrossWage);
            layout.putConstraint(SpringLayout.WEST, lblDeductions, 10, SpringLayout.WEST, panel);
            layout.putConstraint(SpringLayout.NORTH, txtDeductions, 6, SpringLayout.SOUTH, txtGrossWage);
            layout.putConstraint(SpringLayout.EAST, txtDeductions, -10, SpringLayout.EAST, panel);

            layout.putConstraint(SpringLayout.NORTH, lblSSS, 10, SpringLayout.SOUTH, lblDeductions);
            layout.putConstraint(SpringLayout.WEST, lblSSS, 10, SpringLayout.WEST, panel);
            layout.putConstraint(SpringLayout.NORTH, txtSSS, 6, SpringLayout.SOUTH, txtDeductions);
            layout.putConstraint(SpringLayout.EAST, txtSSS, -10, SpringLayout.EAST, panel);

            layout.putConstraint(SpringLayout.NORTH, lblPagibig, 10, SpringLayout.SOUTH, lblSSS);
            layout.putConstraint(SpringLayout.WEST, lblPagibig, 10, SpringLayout.WEST, panel);
            layout.putConstraint(SpringLayout.NORTH, txtPagibig, 6, SpringLayout.SOUTH, txtSSS);
            layout.putConstraint(SpringLayout.EAST, txtPagibig, -10, SpringLayout.EAST, panel);

            layout.putConstraint(SpringLayout.NORTH, lblPhilHealth, 10, SpringLayout.SOUTH, lblPagibig);
            layout.putConstraint(SpringLayout.WEST, lblPhilHealth, 10, SpringLayout.WEST, panel);
            layout.putConstraint(SpringLayout.NORTH, txtPhilHealth, 6, SpringLayout.SOUTH, txtPagibig);
            layout.putConstraint(SpringLayout.EAST, txtPhilHealth, -10, SpringLayout.EAST, panel);

            layout.putConstraint(SpringLayout.NORTH, lblWitholdingTax, 10, SpringLayout.SOUTH, lblPhilHealth);
            layout.putConstraint(SpringLayout.WEST, lblWitholdingTax, 10, SpringLayout.WEST, panel);
            layout.putConstraint(SpringLayout.NORTH, txtWitholdingTax, 6, SpringLayout.SOUTH, txtPhilHealth);
            layout.putConstraint(SpringLayout.EAST, txtWitholdingTax, -10, SpringLayout.EAST, panel);

            layout.putConstraint(SpringLayout.NORTH, lblNetWage, 10, SpringLayout.SOUTH, lblWitholdingTax);
            layout.putConstraint(SpringLayout.WEST, lblNetWage, 10, SpringLayout.WEST, panel);
            layout.putConstraint(SpringLayout.NORTH, txtNetWage, 6, SpringLayout.SOUTH, txtWitholdingTax);
            layout.putConstraint(SpringLayout.EAST, txtNetWage, -10, SpringLayout.EAST, panel);


            //Add panel to frame
            frame.add(panel);
            frame.setVisible(true);
        }
        else {
        	JOptionPane.showMessageDialog(null, "No attendance record found for " + month + " " + year, "ERROR!", JOptionPane.ERROR_MESSAGE);
        }
	}

    // Method used for getting monthly attendance depending on the selected month
    private List<Attendance> getMonthlyAttendance (Employee employee, int month, int year) {
    	resetData();
    	readCSVFiles();
    	
        // Initialize variables need for this method
        String attendanceRow = "";
        List<String> allEmployeeAttendance = new ArrayList<String>();
        List<Attendance> monthlyEmployeeAttendance = new ArrayList<Attendance>();
        
        try {
            // loop through attendance csv
            while ((attendanceRow = attendanceReader.readLine()) != null) {
                String[] splitAttendance = attendanceRow.split(",");
                
                // check if data row is equal to the entered employee number
                // if true, add row to attendances variable
                if (Integer.parseInt(splitAttendance[0]) == employee.getEmployeeNumber()) {
                    allEmployeeAttendance.add(attendanceRow);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(SalaryComputationView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Get all attendances with same month and year
        for (int i = 0; i < allEmployeeAttendance.size(); i++) {
            String date = allEmployeeAttendance.get(i).split(",")[1];
            String timeIn = allEmployeeAttendance.get(i).split(",")[2];
            String timeOut = allEmployeeAttendance.get(i).split(",")[3];
            
            int dateMonth = Integer.parseInt(date.split("/")[0]);
            int dateDay = Integer.parseInt(date.split("/")[1]);
            int dateYear = Integer.parseInt(date.split("/")[2]);
                    
            if(dateMonth == (month + 1) && dateYear == year) {
                
            	Attendance attendance = new Attendance(year, month + 1, dateDay, timeIn, timeOut);
            	
                monthlyEmployeeAttendance.add(attendance);
            }
        }
        
        return monthlyEmployeeAttendance;
    }

    // Method used for calculating employee salary
    private Salary calculateSalary (Employee employee, List<Attendance> attendances) {
        double totalHoursWorked =  calculateHoursWorked(attendances);
        double grossWage = calculateGrossWage (employee, totalHoursWorked);
        double sss = Double.parseDouble(String.format("%.2f", getSSSContribution(employee)));
        double philHealth = Double.parseDouble(String.format("%.2f", getPhilHealthContribution(employee)));
        double pagibig = Double.parseDouble(String.format("%.2f", getPagibigContribution(employee)));
        double witholdingTax = Double.parseDouble(String.format("%.2f", (getWitholdingTax(grossWage, getSSSContribution(employee),  getPhilHealthContribution(employee), getPagibigContribution(employee)))));
        
        SalaryDeduction salaryDeduction = new SalaryDeduction(sss, philHealth, pagibig, witholdingTax);
        
        double netWage = calculateNetWage (grossWage, salaryDeduction);
        
        return new Salary(grossWage, netWage, salaryDeduction);
    }
    
    // Method used for calculating hours per week depending on the month number provided
    private double calculateHoursWorked (List<Attendance> attendances) {
    	double hoursWorked = 0.0;
        // loop through employee weekly attendance record
        for (int a = 0; a < attendances.size(); a++) {
            double hoursWorkedPerDay = getHoursWorkedPerDay(attendances.get(a));
            hoursWorked += hoursWorkedPerDay;
        }
        return hoursWorked;
    }
    
    // Method used for calculating the Weekly Gross Wage
    private double calculateGrossWage (Employee employee, double totalHoursWorked) {
        return totalHoursWorked * employee.getHourlyRate();
    }
    
    // Method used for calculating the Weekly Net Wage
    private double calculateNetWage (double grossWage, SalaryDeduction salaryDeduction) {
        return grossWage - salaryDeduction.getTotalDeductions();
    }

    // Method used for getting hours worked per day
    private double getHoursWorkedPerDay (Attendance attendance) {
        String attDate = attendance.getMonth() + "/" + attendance.getDate() + "/" + attendance.getYear();
        
        // Convert String date and time to LocalDateTime
        LocalDateTime localDateTimeIn = getDateTime(attDate, attendance.getTimeIn());
        LocalDateTime localDateTimeOut = getDateTime(attDate, attendance.getTimeOut());
        
        // Set Grace Period as indicated in the requirements
        LocalTime gracePeriod = LocalTime.of(8, 11);
        // Check if time in is beyond grace period
        if (localDateTimeIn.toLocalTime().isBefore(gracePeriod)){
            // reset time in to 8AM to remove deduction
            localDateTimeIn = getDateTime(attDate, "08:00");
        }
        
        // Calculate Time Difference between timein and timeout
        long timeDiff = localDateTimeIn.toLocalTime().until(localDateTimeOut.toLocalTime(), ChronoUnit.MINUTES);
        double timeDiffDouble = Double.parseDouble(Long.toString(timeDiff)) / 60;
        
        if (timeDiffDouble <= 0) {
            // return zero for invalid data
            return 0.00;
        }
        else {
            // return total hours worked - 1 hour of breaktime
            timeDiffDouble = Double.parseDouble(String.format("%.2f", timeDiffDouble -1));
            
            return timeDiffDouble;
        }
    }
    
    // Method used for converting String date and String time
    private LocalDateTime getDateTime (String date, String time) {
        String[] splitDate = date.split("/");
        String[] splitTime = time.split(":");
        
        // Convert String date to LocalDate
        LocalDate localDate = LocalDate.of(Integer.parseInt(splitDate[2]), Integer.parseInt(splitDate[0]), Integer.parseInt(splitDate[1]));
        // Convert String time to LocalTime
        LocalTime localTime = LocalTime.of(Integer.parseInt(splitTime[0]), Integer.parseInt(splitTime[1]));
        // Combine date and time
        LocalDateTime dateTime = LocalDateTime.of(localDate, localTime);
        
        return dateTime;
    }


    // Method used for getting SSS Contribution
    // hard coded values are based on the Google Sheet Provided
    private double getSSSContribution(Employee employee) {
        if (employee.getBasicSalary() < 3250) {
            return 135.00;
        }
        else if (employee.getBasicSalary() >= 3250 && employee.getBasicSalary() <= 3750) {
            return 157.50;
        }
        else if (employee.getBasicSalary() >= 3750 && employee.getBasicSalary() <= 4250) {
            return 180.00;
        }
        else if (employee.getBasicSalary() >= 4250 && employee.getBasicSalary() <= 4750) {
            return 202.50;
        }
        else if (employee.getBasicSalary() >= 4750 && employee.getBasicSalary() <= 5250) {
            return 225.00;
        }
        else if (employee.getBasicSalary() >= 5250 && employee.getBasicSalary() <= 5750) {
            return 247.50;
        }
        else if (employee.getBasicSalary() >= 5750 && employee.getBasicSalary() <= 6250) {
            return 270.00;
        }
        else if (employee.getBasicSalary() >= 6250 && employee.getBasicSalary() <= 6750) {
            return 292.50;
        }
        else if (employee.getBasicSalary() >= 6750 && employee.getBasicSalary() <= 7250) {
            return 315.00;
        }
        else if (employee.getBasicSalary() >= 7250 && employee.getBasicSalary() <= 7750) {
            return 337.50;
        }
        else if (employee.getBasicSalary() >= 7750 && employee.getBasicSalary() <= 8250) {
            return 360.00;
        }
        else if (employee.getBasicSalary() >= 8250 && employee.getBasicSalary() <= 8750) {
            return 382.50;
        }
        else if (employee.getBasicSalary() >= 8750 && employee.getBasicSalary() <= 9250) {
            return 405.00;
        }
        else if (employee.getBasicSalary() >= 9250 && employee.getBasicSalary() <= 9750) {
            return 427.50;
        }
        else if (employee.getBasicSalary() >= 9750 && employee.getBasicSalary() <= 10250) {
            return 450.00;
        }
        else if (employee.getBasicSalary() >= 10250 && employee.getBasicSalary() <= 10750) {
            return 472.50;
        }
        else if (employee.getBasicSalary() >= 10750 && employee.getBasicSalary() <= 11250) {
            return 495.00;
        }
        else if (employee.getBasicSalary() >= 11250 && employee.getBasicSalary() <= 11750) {
            return 517.50;
        }
        else if (employee.getBasicSalary() >= 11750 && employee.getBasicSalary() <= 12250) {
            return 540.00;
        }
        else if (employee.getBasicSalary() >= 12250 && employee.getBasicSalary() <= 12750) {
            return 562.50;
        }
        else if (employee.getBasicSalary() >= 12750 && employee.getBasicSalary() <= 13250) {
            return 585.00;
        }
        else if (employee.getBasicSalary() >= 13250 && employee.getBasicSalary() <= 13750) {
            return 607.50;
        }
        else if (employee.getBasicSalary() >= 13750 && employee.getBasicSalary() <= 14250) {
            return 630.00;
        }
        else if (employee.getBasicSalary() >= 14250 && employee.getBasicSalary() <= 14750) {
            return 652.50;
        }
        else if (employee.getBasicSalary() >= 14750 && employee.getBasicSalary() <= 15250) {
            return 675.00;
        }
        else if (employee.getBasicSalary() >= 15250 && employee.getBasicSalary() <= 15750) {
            return 697.50;
        }
        else if (employee.getBasicSalary() >= 15750 && employee.getBasicSalary() <= 16250) {
            return 720.00;
        }
        else if (employee.getBasicSalary() >= 16250 && employee.getBasicSalary() <= 16750) {
            return 742.50;
        }
        else if (employee.getBasicSalary() >= 16750 && employee.getBasicSalary() <= 17250) {
            return 765.00;
        }
        else if (employee.getBasicSalary() >= 17250 && employee.getBasicSalary() <= 17750) {
            return 787.50;
        }
        else if (employee.getBasicSalary() >= 17750 && employee.getBasicSalary() <= 18250) {
            return 810.00;
        }
        else if (employee.getBasicSalary() >= 18250 && employee.getBasicSalary() <= 18750) {
            return 832.50;
        }
        else if (employee.getBasicSalary() >= 18750 && employee.getBasicSalary() <= 19250) {
            return 855.00;
        }
        else if (employee.getBasicSalary() >= 19250 && employee.getBasicSalary() <= 19750) {
            return 877.50;
        }
        else if (employee.getBasicSalary() >= 19750 && employee.getBasicSalary() <= 20250) {
            return 900.00;
        }
        else if (employee.getBasicSalary() >= 20250 && employee.getBasicSalary() <= 20750) {
            return 922.50;
        }
        else if (employee.getBasicSalary() >= 20750 && employee.getBasicSalary() <= 21250) {
            return 945.00;
        }
        else if (employee.getBasicSalary() >= 21250 && employee.getBasicSalary() <= 21750) {
            return 967.50;
        }
        else if (employee.getBasicSalary() >= 21750 && employee.getBasicSalary() <= 22250) {
            return 990.00;
        }
        else if (employee.getBasicSalary() >= 22250 && employee.getBasicSalary() <= 22750) {
            return 1012.50;
        }
        else if (employee.getBasicSalary() >= 22750 && employee.getBasicSalary() <= 23250) {
            return 1035.00;
        }
        else if (employee.getBasicSalary() >= 23250 && employee.getBasicSalary() <= 23750) {
            return 1057.50;
        }
        else if (employee.getBasicSalary() >= 23750 && employee.getBasicSalary() <= 24250) {
            return 1080.00;
        }
        else if (employee.getBasicSalary() >= 24250 && employee.getBasicSalary() <= 24750) {
            return 1102.50;
        }
        else {
            return 1125.00;
        }
    }

    // Method used for getting PhilHealth Contribution
    // hard coded values are based on the Google Sheet Provided
    private double getPhilHealthContribution(Employee employee) {
        if (employee.getBasicSalary() <= 10000.00) {
            return 150.00;
        }
        else if (employee.getBasicSalary() > 10000 && employee.getBasicSalary() < 60000) {
            return (employee.getBasicSalary() * 0.03) / 2;
        }
        else {
            return 900;
        }
    }

    // Method used for getting Pagibig Contribution
    // hard coded values are based on the Google Sheet Provided
    private double getPagibigContribution(Employee employee) {
        if (employee.getBasicSalary() >= 1000 && employee.getBasicSalary() <= 1500) {
            return employee.getBasicSalary() * 0.01;
        }
        else if (employee.getBasicSalary() > 1500){
            if ((employee.getBasicSalary() * 0.02) <= 100) {
                return employee.getBasicSalary() * 0.02;
            }
            else {
                return 100;
            }
        }
        else {
            return 0.0;
        }
    }

    // Method used for getting Witholding Tax
    // hard coded values are based on the Google Sheet Provided
    private double getWitholdingTax(double grossWage, double sss, double philHealth, double pagibig) {
        double deductedSalary = grossWage - sss - philHealth - pagibig;
        
        if (deductedSalary <= 20832){
            return 0.0;
        }
        else if (deductedSalary >= 20833 && deductedSalary < 33333) {
            return (deductedSalary - 20833) * 0.2;
        }
        else if (deductedSalary >= 33333 && deductedSalary < 66667) {
            return ((deductedSalary - 33333) * 0.25) + 2500;
        }
        else if (deductedSalary >= 66667 && deductedSalary < 166667) {
            return ((deductedSalary - 66667) * 0.3) + 10833;
        }
        else if (deductedSalary >= 166667 && deductedSalary < 666667) {
            return ((deductedSalary - 166667) * 0.32) + 40833.33;
        }
        else {
            return ((deductedSalary - 666667) * 0.35) + 200833.33;
        }
    }


    // Method used for reseting data used in the system
    private void resetData () {
        attendanceReader = null;
    }

    // Method used for reading CSV files
    private void readCSVFiles() {
        try {
            attendanceReader = new BufferedReader(new FileReader(ATTENDANCE_CSV));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MotorPH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
