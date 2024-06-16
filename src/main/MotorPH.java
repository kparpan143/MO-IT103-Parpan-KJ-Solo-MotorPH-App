package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.classes.Employee;
import main.view.EmployeeDetailsView;
import main.view.SalaryComputationView;
import main.view.SearchEmployeeView;

public class MotorPH {
    
	public static void main(String[] args) {
		// new SalaryComputationView (new Employee(1, "empName", "bday", 2.0, 10.0), 2024, "June");
		// new EmployeeDetailsView(new Employee(1, "empName", "bday", 2.0, 10.0));

		new SearchEmployeeView();
	}
}
