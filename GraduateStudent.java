package LabStudent;

public class GraduateStudent extends Student
{
    String supervisor = "";
    boolean isPhD = false;
    String undergraduateSchool = " ";

    private String[] type = {"0", "1" , "2"};
    public GraduateStudent()
    {

    }//base

    @Override
    public String toString()
    {
        if (isPhD == true)
        {
            return "\n" + "Graduate Program: " + this.program + "\n" + "Year: " + this.year + "\n" + "Average Grade: " + this.avgGrade
            + "\n" + "Working on PhD" + "\n" + "Supervisor: " + this.supervisor + "\n" + "Undergraduate School: " + this.undergraduateSchool + "\n"
            +"Last Name: " + this.lastName + "\n";
        }//PhD
        else
        {
            return "\n" + "Graduate Program: " + this.program + "\n" + "Year: " + this.year + "\n" + "Average Grade: " + this.avgGrade
            + "\n" + "Working on Masters" + "\n" + "Supervisor: " + this.supervisor + "\n" + "Undergraduate School: " + this.undergraduateSchool + "\n"
            +"Last Name: " + this.lastName + "\n";
        }//Masters
    }//toString

    @Override
    public String printToFile()
    {
        if (this.isPhD == true)
        {
            return this.program + " " + this.year + " " + this.avgGrade + " " +
            this.supervisor + " " +  "1" + " " + this.undergraduateSchool + " " + this.lastName +  "\n";
        }

        if (this.isPhD == false)
        {
            return this.program + " " + this.year + " " + this.avgGrade + " " +
            this.supervisor + " " +  "0" + " " + this.undergraduateSchool + " " + this.lastName + "\n";
        }
        
        return "Could not Load Grad Student";

    }//printToFile

    @Override
    public void printStudent()
    {
        System.out.printf(this.toString()); 
    }//printStudent

//Getters
    public String getSupervisor()
    {
        return this.supervisor;
    }

    public boolean getIsPhD()
    {
        return this.isPhD;
    }

    public String getUndergraduateSchool()
    {
        return this.undergraduateSchool;
    }

//Setters

    public void setSupervisor (String supervisor) 
    {
    this.supervisor = supervisor;
    }

    public void setIsPhd (Boolean isPhD) 
    {
    this.isPhD = isPhD;
    }

    public void setUndergraduateSchool (String undergraduateSchool)
    {
        this.undergraduateSchool = undergraduateSchool;
    }

}//GraduateStudent
