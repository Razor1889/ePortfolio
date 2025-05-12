package LabStudent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class GUI 
{
    //vars
    static JFrame frame = new JFrame("Student Terminal");
    static ArrayList <Student> students = new ArrayList<Student>();
    static HashMap<String,ArrayList<Integer>> hashMap = new HashMap<String,ArrayList<Integer>>();
    static ArrayList <Integer> intArray = new ArrayList<Integer>();

    //Button Functions
static JButton studentButton = new JButton( new AbstractAction("Create New Student") 
{
    @Override
    public void actionPerformed( ActionEvent e ) 
    {
    JPanel studentPanel = new JPanel();
    studentPanel.setLayout(null);

    JLabel programLabel = new JLabel("Program");
    programLabel.setBounds(10,20,80,25);
    studentPanel.add(programLabel);
    JTextField programText = new JTextField(20);
    programText.setBounds(100,20,165,25);
    studentPanel.add(programText);
    //program

    JLabel yearLabel = new JLabel("Year");
    yearLabel.setBounds(10,50,80,25);
    studentPanel.add(yearLabel);
    JTextField yearText = new JTextField(20);
    yearText.setBounds(100,50,165,25);
    studentPanel.add(yearText);
    //year

    JLabel gradeLabel = new JLabel("Grade");
    gradeLabel.setBounds(10,80,80,25);
    studentPanel.add(gradeLabel);
    JTextField gradeText = new JTextField(20);
    gradeText.setBounds(100,80,165,25);
    studentPanel.add(gradeText);
    //grade
    
    JLabel nameLabel = new JLabel("Last Name");
    nameLabel.setBounds(10,110,80,25);
    studentPanel.add(nameLabel);
    JTextField nameText = new JTextField(20);
    nameText.setBounds(100,110,165,25);
    studentPanel.add(nameText);
    //lastname

    JLabel message = new JLabel("");
    message.setBounds(60, 140, 300, 25);
    studentPanel.add(message);
    JButton submit = new JButton( new AbstractAction("Submit") {
        @Override
        public void actionPerformed( ActionEvent e ) 
        {
            String program = programText.getText();
            String year = yearText.getText();
            String grade = gradeText.getText();
            String name = nameText.getText();

            if (program.isEmpty() || year.isEmpty() || name.isEmpty())
            {
                message.setText("Input is required for all the fields except grade.");
            }//if field is empty
            
            if (grade.isEmpty())
            {
                grade = "0";
            }//if grade empty

            if ((Double.valueOf(grade) > 100) || (Double.valueOf(grade) < 0))
            {
                message.setText("Grade needs to be within 0-100");
            }

            if (!program.isEmpty() && !year.isEmpty() && !name.isEmpty() &&
                (Double.valueOf(grade) >= 0) && (Double.valueOf(grade) <= 100))
            {
                message.setText("Student Created");
                Student newStudent = new Student();
                newStudent.setProgram(program);
                newStudent.setYear(year);
                newStudent.setAvgGrade(grade);
                newStudent.setLastName(name);
                students.add(newStudent);

                String key = program + year + name;
                key = key.toLowerCase();
                Integer size = students.size();
                intArray.add(size);
                hashMap.put(key, intArray);
            }//reset label if all values given

        }//error check
    });

    
    JButton exitStudent = new JButton( new AbstractAction("Exit")
    {
        @Override
        public void actionPerformed( ActionEvent e )
        {
            studentPanel.setVisible(false);
            frame.getContentPane().remove(studentPanel);
        }
    });

    studentPanel.add(submit);
    submit.setBounds(60, 170, 100, 40);
    studentPanel.add(exitStudent);
    exitStudent.setBounds(60, 210, 100, 40);
    frame.add(studentPanel);
    frame.setVisible(true);

    }//actionNewStudent
});//Create New Student Option

static JButton gradstudentButton = new JButton( new AbstractAction("Create New Graduate Student") 
{
    @Override
    public void actionPerformed( ActionEvent e ) 
    {
    JPanel studentPanel = new JPanel();
    studentPanel.setLayout(null);

    JLabel programLabel = new JLabel("Program");
    programLabel.setBounds(10,20,80,25);
    studentPanel.add(programLabel);
    JTextField programText = new JTextField(20);
    programText.setBounds(200,20,165,25);
    studentPanel.add(programText);
    //program

    JLabel yearLabel = new JLabel("Year");
    yearLabel.setBounds(10,50,80,25);
    studentPanel.add(yearLabel);
    JTextField yearText = new JTextField(20);
    yearText.setBounds(200,50,165,25);
    studentPanel.add(yearText);
    //year

    JLabel gradeLabel = new JLabel("Grade");
    gradeLabel.setBounds(10,80,80,25);
    studentPanel.add(gradeLabel);
    JTextField gradeText = new JTextField(20);
    gradeText.setBounds(200,80,165,25);
    studentPanel.add(gradeText);
    //grade
    
    JLabel nameLabel = new JLabel("Last Name");
    nameLabel.setBounds(10,110,80,25);
    studentPanel.add(nameLabel);
    JTextField nameText = new JTextField(20);
    nameText.setBounds(200,110,165,25);
    studentPanel.add(nameText);
    //lastname

    JLabel supLabel = new JLabel("Supervisor");
    supLabel.setBounds(10,140,80,25);
    studentPanel.add(supLabel);
    JTextField supText = new JTextField(20);
    supText.setBounds(200,140,165,25);
    studentPanel.add(supText);

    JLabel phdLabel = new JLabel("Working on Ph.D? Yes or No");
    phdLabel.setBounds(10,170,180,25);
    studentPanel.add(phdLabel);
    JTextField phdText = new JTextField(20);
    phdText.setBounds(200,170,165,25);
    studentPanel.add(phdText);

    JLabel schoolLabel = new JLabel("Undergraduate School?");
    schoolLabel.setBounds(10,200,150,25);
    studentPanel.add(schoolLabel);
    JTextField schoolText = new JTextField(20);
    schoolText.setBounds(200,200,165,25);
    studentPanel.add(schoolText);

    JLabel message = new JLabel("");
    message.setBounds(60, 230, 300, 25);
    studentPanel.add(message);
    JButton submit = new JButton( new AbstractAction("Submit") {
        @Override
        public void actionPerformed( ActionEvent e ) 
        {
            String program = programText.getText();
            String year = yearText.getText();
            String grade = gradeText.getText();
            String name = nameText.getText();
            String sup = supText.getText();
            String phd = phdText.getText();
            String school = schoolText.getText();

            if (program.isEmpty() || year.isEmpty() || name.isEmpty() || sup.isEmpty() ||
            phd.isEmpty() || school.isEmpty())
            {
                message.setText("Input is required for all the fields except grade.");
            }//if field is empty
            
            if (grade.isEmpty())
            {
                grade = "0";
            }//if grade empty

            if ((Double.valueOf(grade) > 100) || (Double.valueOf(grade) < 0))
            {
                message.setText("Grade needs to be within 0-100");
            }

            if (phd.equalsIgnoreCase("Yes"))
            {
                phd = "true";
            }
            else if (phd.equalsIgnoreCase("No"))
            {
                phd = "false";
            }
            else
            {
                message.setText("Ph.D input must be Yes or No");
            }

            if (!program.isEmpty() && !year.isEmpty() && !name.isEmpty() &&
                (Double.valueOf(grade) >= 0) && (Double.valueOf(grade) <= 100) && !sup.isEmpty()
                && !phd.isEmpty() && !school.isEmpty())
            {
                message.setText("Grad Student Created");
                GraduateStudent newStudent = new GraduateStudent();
                newStudent.setProgram(program);
                newStudent.setYear(year);
                newStudent.setAvgGrade(grade);
                newStudent.setLastName(name);
                newStudent.setSupervisor(sup);
                newStudent.setIsPhd(Boolean.valueOf(phd));
                newStudent.setUndergraduateSchool(school);
                students.add(newStudent);

                String key = program + year + name;
                key = key.toLowerCase();
                Integer size = students.size();
                intArray.add(size);
                hashMap.put(key, intArray);
            }//reset label if all values given

        }//error check
    });

    JButton exitStudent = new JButton( new AbstractAction("Exit")
    {
        @Override
        public void actionPerformed( ActionEvent e )
        {
            studentPanel.setVisible(false);
            frame.getContentPane().remove(studentPanel);
        }
    });

    studentPanel.add(exitStudent);
    exitStudent.setBounds(60, 310, 100, 40);
    studentPanel.add(submit);
    submit.setBounds(60, 260, 100, 40);
    frame.add(studentPanel);
    frame.setVisible(true);

    }//actionNewStudent
});//Create New Grad Student Option

static JButton exit = new JButton( new AbstractAction("Exit")
{
        @Override
        public void actionPerformed( ActionEvent e )
        {
            System.exit(0);
        }
});//Exit Option

static JButton print = new JButton( new AbstractAction("Print Student Info")
    {
    @Override
    public void actionPerformed( ActionEvent e )
    {
        JPanel outPanel = new JPanel();
        String full = "";

        for (Student temp: students)
        {
            full += temp.toString();
        }//loop through investments with current

        JTextArea textArea = new JTextArea(full);
        outPanel.add(textArea);
        JScrollPane scrollPane = new JScrollPane(textArea); 
        textArea.setEditable(false);
        outPanel.add(scrollPane);

        JButton exitStudent = new JButton( new AbstractAction("Exit")
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                outPanel.setVisible(false);
                frame.getContentPane().remove(outPanel);
            }
        });
        
        outPanel.add(exitStudent);
        exitStudent.setBounds(60, 310, 100, 40);      
        frame.add(outPanel);
        frame.setVisible(true);
    }
});//Print Option

static JButton average = new JButton( new AbstractAction("Overall Average and Number of Students")
    {
    @Override
    public void actionPerformed( ActionEvent e )
    {
        JPanel outPanel = new JPanel();
        Double full = 0.0;

        for (Student temp: students)
        {
            full += Double.valueOf(temp.getAvgGrade());
        }
        full = full/students.size();

        String outString = "Overall Average is: " + full + "\n" + "Number of Students: " + students.size() + "\n";

        JTextArea textArea = new JTextArea(outString);
        outPanel.add(textArea);
        JScrollPane scrollPane = new JScrollPane(textArea); 
        textArea.setEditable(false);
        textArea.add(scrollPane);

        JButton exitStudent = new JButton( new AbstractAction("Exit")
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                outPanel.setVisible(false);
                frame.getContentPane().remove(outPanel);
            }
        });
        
        outPanel.add(exitStudent);
        exitStudent.setBounds(60, 310, 100, 40);      
        frame.add(outPanel);
        frame.setVisible(true);
    }
});//Average Option

static JButton read = new JButton( new AbstractAction("Read Input From File")
{
@Override
public void actionPerformed( ActionEvent e )
{
    JPanel readPanel = new JPanel();
    readPanel.setLayout(null);

    JLabel fileLabel = new JLabel("Filename");
    fileLabel.setBounds(10,20,80,25);
    readPanel.add(fileLabel);
    JTextField fileText = new JTextField(20);
    fileText.setBounds(100,20,165,25);
    readPanel.add(fileText);
    //filename

    JLabel message = new JLabel("Last Name format Not Given in Lab Handout, assuming same as Lab 3");
    message.setBounds(10,50,500,25);
    readPanel.add(message);

    JButton submit = new JButton( new AbstractAction("Submit")
    {
        @Override
        public void actionPerformed( ActionEvent e )
        {
            String filename = fileText.getText();
            try 
                {
                    File f = new File(filename);
                    Scanner scanner = new Scanner(f);
                    if (f.length() == 0)
                    { 
                    message.setText("Warning! File is Empty!");;
                    }
                    while (scanner.hasNextLine())
                    {
                        
                        String temp = scanner.nextLine();
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
                } catch (Exception f) 
                {
                    message.setText("Could not open file");
                }
        }
    });

    JButton exit = new JButton( new AbstractAction("Exit")
    {
        @Override
        public void actionPerformed( ActionEvent e )
        {
            readPanel.setVisible(false);
            frame.getContentPane().remove(readPanel);
        }
    });

        readPanel.add(submit);
        submit.setBounds(10, 80, 80, 40);
        readPanel.add(exit);
        exit.setBounds(10, 140, 80, 40);      
        frame.add(readPanel);
        frame.setVisible(true);
}
});//read

static JButton write = new JButton( new AbstractAction("Write Output To A File")
{
@Override
public void actionPerformed( ActionEvent e )
{
    JPanel writePanel = new JPanel();
    writePanel.setLayout(null);

    JLabel fileLabel = new JLabel("Filename");
    fileLabel.setBounds(10,20,80,25);
    writePanel.add(fileLabel);
    JTextField fileText = new JTextField(20);
    fileText.setBounds(100,20,165,25);
    writePanel.add(fileText);
    //filename

    JLabel message = new JLabel("Last Name format Not Given in Lab Handout, assuming same as Lab 3");
    message.setBounds(10,50,500,25);
    writePanel.add(message);

    JButton submit = new JButton( new AbstractAction("Submit")
    {
        @Override
        public void actionPerformed( ActionEvent e )
        {
            String filename = fileText.getText();
            File f = new File(filename);
            if (f.length() != 0)
            { 
              message.setText("Warning! File Will Be Overwritten!");
            }
            try 
            {
              PrintWriter fileWriter = new PrintWriter(f);
              for (int element = 0; element < students.size(); element++)
              {
                fileWriter.printf(students.get(element).printToFile());
              }
              fileWriter.close();
            } catch (Exception a) 
            {
              message.setText("Failed to Write.");
            }
        }
    });

    JButton exit = new JButton( new AbstractAction("Exit")
    {
        @Override
        public void actionPerformed( ActionEvent e )
        {
            writePanel.setVisible(false);
            frame.getContentPane().remove(writePanel);
        }
        });

        writePanel.add(submit);
        submit.setBounds(10, 80, 80, 40);
        writePanel.add(exit);
        exit.setBounds(10, 140, 80, 40);      
        frame.add(writePanel);
        frame.setVisible(true);
}
});//write

static JButton search = new JButton( new AbstractAction("Search Via HashMap Key")
{
@Override
public void actionPerformed( ActionEvent e )
{
    JPanel searchPanel = new JPanel();
    searchPanel.setLayout(null);

    JLabel searchLabel = new JLabel("KeyWord");
    searchLabel.setBounds(10,20,80,25);
    searchPanel.add(searchLabel);
    JTextField searchText = new JTextField(20);
    searchText.setBounds(100,20,165,25);
    searchPanel.add(searchText);
    //filename

    JLabel message = new JLabel("");
    message.setBounds(10,50,500,25);
    searchPanel.add(message);

    JButton submit = new JButton( new AbstractAction("Submit")
    {
        @Override
        public void actionPerformed( ActionEvent e )
        {
            String complete = " \n";
            Integer count = 0;
            ArrayList<Integer> arr = new ArrayList<Integer>();
            String keyword = searchText.getText();
            keyword = keyword.toLowerCase();
            for (String key: hashMap.keySet())
            {
                key = key.toLowerCase();
                if (key.contains(keyword))
                {
                    arr.add(count);
                }
                count++;
            }//get all elements which match the key

            for (Integer current: arr)
            {
                for (Integer element = 0; element < students.size(); element++)
                {
                    if (current == element)
                    {
                        complete += students.get(element).toString();
                    }
                }
            }

            JTextArea textArea = new JTextArea(complete);
            textArea.setBounds(10, 180, 400, 400);
            JScrollPane scrollPane = new JScrollPane(textArea); 
            textArea.setEditable(false);
            textArea.add(scrollPane);
            searchPanel.add(textArea);
        }
    });

    JButton exit = new JButton( new AbstractAction("Exit")
    {
        @Override
        public void actionPerformed( ActionEvent e )
        {
            searchPanel.setVisible(false);
            frame.getContentPane().remove(searchPanel);
        }
        });

        searchPanel.add(submit);
        submit.setBounds(10, 80, 80, 40);
        searchPanel.add(exit);
        exit.setBounds(10, 140, 80, 40);      
        frame.add(searchPanel);
        frame.setVisible(true);
}
});//search


    public static void main(String[] args)
    {
        
        frame.setSize(1250,750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        frame.setLayout(new GridLayout(1, 3));
        frame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        JPanel panel = new JPanel();
        //panel.setLayout(new GridLayout(rows, columns, hgap, vgap));
        panel.setLayout(new GridLayout(4, 2, 5, 10));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        panel.add(studentButton);
        panel.add(gradstudentButton);
        panel.add(print);
        panel.add(average);
        panel.add(read);
        panel.add(write);
        panel.add(search);
        panel.add(exit);

        frame.add(panel);
        frame.setVisible(true);
    }//main


}//GUI
