package main.classes;

public class Salary {
    
    private double grossWage;
    private double netWage;
    private SalaryDeduction salaryDeduction;
    
    public Salary (double grossWage, double netWage, SalaryDeduction salaryDeduction) {
        this.grossWage = grossWage;
        this.netWage = netWage;
        this.salaryDeduction = salaryDeduction;
    }
    
    public double getGrossWage(){
        return this.grossWage;
    }
    public void setGrossWage(double grossWage){
        this.grossWage = grossWage;
    }
    
    public double getNetWage(){
        return this.netWage;
    }
    public void setNetWage(double netWage){
        this.netWage = netWage;
    }
    
    public SalaryDeduction getSalaryDeduction(){
        return this.salaryDeduction;
    }
    public void setSalaryDeduction(SalaryDeduction salaryDeduction){
        this.salaryDeduction = salaryDeduction;
    }

}
