package LabStudent;

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class Student 
{
    String program = "";
    String year = "";
    String avgGrade = "0";
    String lastName = "";
    public Student()
    {

    }//base

    @Override
    public String toString()
    { 
      return "\n" + "Program: " + this.program + "\n" + "Year: " + this.year + "\n" + "Average Grade: " + this.avgGrade + "\n" + "Last Name: " + this.lastName + "\n";
    }//toString

    public void printStudent()
    {
        System.out.printf("Program: %s Year: %s Average Grade: %s\n", this.program, this.year, this.avgGrade); 
    }

    public String printToFile()
    {
      return this.program + " " + this.year + " " + this.avgGrade + " " + this.lastName + "\n";
    }//printToFile
    

    public boolean equals(Object other)
    {
        if (other == null)
        {
            //if object null
        return false;
        }

        if (this.getClass() != other.getClass())
        {
            //if class type is not the same
            return false;
        }

        if (this.avgGrade != ((Student)other).avgGrade)
        {
            //if avgGrade is not equal
            return false;
        }

        if (!this.program.equals(((Student)other).program))
        {
            //if program is not equal
            return false;
        }

        if (!this.year.equals(((Student)other).year))
        {
            //if year is not equal
            return false;
        }        

   return true;
    }//equals


//Getters    
    public String getProgram()
    {
        return this.program;
    }

    public String getYear()
    {
        return this.year;
    }

    public String getAvgGrade()
    {
        return this.avgGrade;
    }

    public String getLastName()
    {
        return this.lastName;
    }
//Setters

    public void setProgram (String program) 
    {
    this.program = program;
    }

    public void setYear (String year) 
    {
    this.year = year;
    }

    public void setAvgGrade (String avgGrade)
    {
        this.avgGrade = avgGrade;
    }

    public void setLastName (String lastName)
    {
        this.lastName = lastName;
    }
    
public static void oldMain ()//String[] args
{
    Scanner input = new Scanner(System.in);
    //create arraylist
    ArrayList <Student> students = new ArrayList<Student>();
    //HashMap<String,ArrayList<Integer>> hashMap = new HashMap<String,ArrayList<Integer>>();
    int counter = 0;
    int option = 0;
    String temp = "";
    String grade = "";
    double averageGrades = 0;
    double numStudents = 0;
    String programSearch = "";
    String word1 = "";
    int condition = 0;
    double tempDouble = 0.00;
    //String word2 = "";

    while (counter == 0)

    {
        condition = 0;
        averageGrades = 0;
        numStudents = 0;
        temp = "";
        option = 0;
        grade = "";
        programSearch = "";
        System.out.println(
            //output menu
            "\nWelcome to your Student Database!\n"
            + "Here are the commands:\n"
            + "1: Enter information for a new Student\n"
            + "2: Enter information for a new Graduate Student\n"
            + "3: Show all student information with program, year, and average grade on separate lines \n"
            + "4: Print the average of the average grades for class and the total number of students \n"
            + "5: Enter a specific program and show all student information for that program \n"
            + "6: Load Student Information from a new File\n"
            + "7: Save Student Information to an output file \n"
            + "8: End the program \n"
            );
        option = input.nextInt();
            switch(option) 
            {
                case 1:
                  System.out.println("Enter Information for a new Student\n");
                  Student newStudent = new Student();
                  System.out.println("Enter the Program name followed by the year");
                  input.nextLine();
                  temp = input.nextLine();
                  String words[] = temp.split(" ", 2);
                  //tokenize input
                  word1 = words[0];
                  while (word1.isEmpty());
                  {
                    if (condition == 1)
                    {
                    System.out.println("Please enter proper input");
                    temp = input.next();
                    String newWords[] = temp.split(" ", 2);
                    word1 = newWords[0];
                    }
                    condition++;
                  }//check if input is valid
                  newStudent.setProgram(words[0]);
                  newStudent.setYear(words[1]);
                  System.out.println("Enter Average Grade, or leave blank");
                  //input.nextLine();
                  grade = input.nextLine();
                  boolean bool2 = grade.isEmpty();
                  if (bool2 == true)
                  {
                    grade = "0.00";
                  }
                  tempDouble = Double.parseDouble(grade);
                  while (tempDouble > 100.00 || tempDouble < 0.00)
                  {
                    System.out.println("Enter a valid grade between 0 and 100");
                    //input.nextLine();
                    grade = input.nextLine();
                    tempDouble = Double.parseDouble(grade);
                  }//check if 0-100
                  newStudent.setAvgGrade(grade);
                  students.add(newStudent);
                  System.out.println("New Student Created");
                  newStudent.printStudent();
                  break;
                case 3:
                  System.out.println("Showing all Student Information");

                  for (int element = 0; element < students.size(); element++)
                  {
                    System.out.printf("%s", students.get(element).toString());
                  }
                  break;
                case 4:
                  System.out.println("Average grades and number of students");
                  for (int element = 0; element < students.size(); element++)
                  {
                    averageGrades += Double.parseDouble(students.get(element).getAvgGrade());
                  }//sum average grades
                  numStudents = students.size();
                  averageGrades = averageGrades/numStudents;
                  System.out.printf("Average Grade: %.2f\n", averageGrades);
                  System.out.printf("Number Students: %.2f\n", numStudents);
                break;
                case 5:
                  System.out.println("Showing student information for a single program");
                  System.out.println("What is the program name?");
                  programSearch = input.next();

                  for (int element = 0; element < students.size(); element++)
                  {
                      if (students.get(element).getProgram().equalsIgnoreCase(programSearch))
                      {
                        System.out.printf("Found Student Number %d\n", element + 1);
                        students.get(element).printStudent();
                      }//print if match programSearch
                  }//loop through students
                break;
                case 8:
                  System.out.println("Exiting program. Thank you for using this Program");
                  counter = 10;
                break;
                case 2:
                System.out.println("Enter Information for a new Graduate Student\n");
                  GraduateStudent gradStudent = new GraduateStudent();
                  System.out.println("Enter the Program name followed by the year");
                  input.nextLine();
                  temp = input.nextLine();
                  String wordsGrad[] = temp.split(" ", 2);
                  //tokenize input
                  String word1Grad = wordsGrad[0];
                  while (word1Grad.isEmpty());
                  {
                    if (condition == 1)
                    {
                    System.out.println("Please enter proper input");
                    temp = input.next();
                    String newWords[] = temp.split(" ", 2);
                    word1Grad = newWords[0];
                    }
                    condition++;
                  }//check if input is valid
                  gradStudent.setProgram(wordsGrad[0]);
                  gradStudent.setYear(wordsGrad[1]);
                  System.out.println("Enter Average Grade, or leave blank");
                  //input.nextLine();
                  grade = input.nextLine();
                  if (grade.isEmpty())
                  {
                    grade = "0.00";
                  }
                  tempDouble = Double.parseDouble(grade);
                  while (tempDouble > 100.00 || tempDouble < 0.00)
                  {
                    System.out.println("Enter a valid grade between 0 and 100");
                    //input.nextLine();
                    grade = input.nextLine();
                    tempDouble = Double.parseDouble(grade);
                  }//check if 0-100
                  gradStudent.setAvgGrade(grade);
                  System.out.println("What is your supervisor's name?");
                  gradStudent.setSupervisor(input.nextLine());
                  System.out.println("Are you working towards your PhD? True or False");
                  Boolean bool = Boolean.parseBoolean(input.nextLine());
                  gradStudent.setIsPhd(bool);
                  System.out.println("What is your undergradutate school? Can leave blank");
                  String check = input.nextLine();
                  bool = check.isEmpty();
                  if (bool == true)
                  {
                    gradStudent.setUndergraduateSchool("NotEntered");
                  }else
                  {
                    gradStudent.setUndergraduateSchool(check);
                  }//check if undergraduateschool is empty
                  students.add(gradStudent);
                  System.out.println("New Graduate Student Created");
                  gradStudent.printStudent();
                break;
                case 6:
                counter = 0;
                System.out.println("What is the name of the file?");
                input.nextLine();
                String filename = input.nextLine();
                try 
                {
                  File f = new File(filename);
                  Scanner scanner = new Scanner(f);
                  if (f.length() == 0)
                  { 
                    System.out.println("Warning! File is empty!");
                  }
                    while (scanner.hasNextLine())
                    {
                      
                      temp = scanner.nextLine();
                      String split[] = temp.split(" ");
                      if (split.length == 6)
                      {
                        GraduateStudent gradStudent2 = new GraduateStudent();
                        gradStudent2.setProgram(split[0]);
                        gradStudent2.setYear(split[1]);
                        gradStudent2.setAvgGrade(split[2]);
                        gradStudent2.setSupervisor(split[3]);
                        if (split[4] == "1")
                        {
                          gradStudent2.setIsPhd(true);
                        }
                        if (split[4] == "0")
                        {
                          gradStudent2.setIsPhd(false);
                        }
                        gradStudent2.setUndergraduateSchool(split[5]);
                        students.add(gradStudent2);
                      }//length 6 (Grad)

                      if (split.length == 3)
                      {
                        Student newStudent2 = new Student();
                        newStudent2.setProgram(split[0]);
                        newStudent2.setYear(split[1]);
                        newStudent2.setAvgGrade(split[2]);
                        students.add(newStudent2);
                      }//if length 3

                    }
                    scanner.close();
                } catch (Exception e) 
                {
                  System.out.println("Could not open file.");
                }
                break;
                case 7:
                System.out.println("What is the name of the file?");
                input.nextLine();
                filename = input.nextLine();
                File f = new File(filename);
                if (f.length() != 0)
                { 
                  System.out.println("Warning! File will be overwritten!");
                }
                try 
                {
                  PrintWriter fileWriter = new PrintWriter(f);
                  for (int element = 0; element < students.size(); element++)
                  {
                    fileWriter.printf(students.get(element).printToFile());
                  }
                  fileWriter.close();
                } catch (Exception e) 
                {
                  System.out.println("Failed to Write");
                }
                break;
                default:
                System.out.println("Please Enter a valid number from 1-8");
                  // code block
                 
            }//switch
    }//while
    
    input.close();
}//main




}//Student


