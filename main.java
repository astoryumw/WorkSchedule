/* An app that automatically creates a random work schedule based on days employees can work. */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

class main {
    public static void main(String[] args) {
        main m = new main();
        System.out.println("Welcome to the scheduler program.");
        System.out.println("Opening a file with the employees..");
        // m.employee_list();
        String[][] myArray = m.employeeList();
        /* for (int i=0; i<myArray.length; i++) {
            System.out.print(Arrays.toString(myArray[i]));
        } */
        // send myArray over to another method that will select the days people work randomly
        HashMap<String, String> myHashMap = m.scheduleMaker(myArray);
        ArrayList<String> myList = m.orderSchedule(myHashMap);
        m.saveFile(myList);
        // System.out.println(myList);
    }

    // copy the employee list into a 2D array and return
    public String[][] employeeList() {
        String[][] myList = new String[7][2];
        try {
            File myFile = new File("employees.txt");
            Scanner myScanner = new Scanner(myFile);
            int i = 0;
            while (myScanner.hasNextLine()) {
                String item = Arrays.toString(myScanner.nextLine().split(", "));
                // System.out.println(item);
                // System.out.println(item.substring(1,item.indexOf(", ")));
                myList[i][0] = item.substring(1,item.indexOf(", "));
                // System.out.println(item.substring(item.indexOf(", ")+2, item.length()-1));
                myList[i][1] = item.substring(item.indexOf(", ")+2, item.length()-1);
                // myList[i] = myScanner.nextLine();

                i++;
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException aioobe) { // always get this, does not print out last item
            aioobe.printStackTrace();
        }
        return myList;
    }

    // randomly select employee, check if they can't work that day
    // if they can, select them, if not find someone else
    public HashMap<String, String> scheduleMaker(String[][] employees) {
        System.out.println("Making a random schedule based on when they cannot work..");
        // it works
        // for (int i=0; i<employees.length; i++) {
        //     System.out.print(Arrays.toString(employees[i]));
        // }
        ArrayList<String> days = new ArrayList<String>();
        days.add("Sunday"); days.add("Monday"); days.add("Tuesday"); days.add("Wednesday"); days.add("Thursday"); days.add("Friday"); days.add("Saturday");
        ArrayList<String> employeeLists = new ArrayList<String>();
        // employeeLists.add("Austin"); employeeLists.add("Sean"); employeeLists.add("Marina"); employeeLists.add("Daniel"); employeeLists.add("Danny"); employeeLists.add("Christian"); employeeLists.add("Elizabeth");
        Random rand = new Random();
        int randomNumber = rand.nextInt(7);
        // System.out.println(Arrays.deepToString(employees[randomNumber])); // check if they can work that day
        // System.out.println(employees[randomNumber][0]);
        // System.out.println(employees[randomNumber][1]);
        HashMap<String, String> schedule = new HashMap<String, String>();
        // System.out.println(days.get(0));
        for (int i=0; i<7; i++) {
            // System.out.println(days.get(i));
            while (days.get(i).toString().equals(employees[randomNumber][1]) || employeeLists.contains(employees[randomNumber][0])) { // while day selected is day they cannot work, find new person
                // System.out.println(randomNumber);
                randomNumber = rand.nextInt(7);
            }
            employeeLists.add(employees[randomNumber][0]);
            schedule.put(days.get(i),employees[randomNumber][0]);
            // System.out.println(schedule);
        }
        return schedule;
    }

    // create a method that can take the hashtable in, reorder it as a arraylist then return
    public ArrayList<String> orderSchedule(HashMap<String, String> schedule) {
        ArrayList<String> myList = new ArrayList<>();
        int i=1;
        // for (String key: schedule.keySet()) {
        //     // System.out.println(key);
        //     // System.out.println(schedule.get(key));
        //     myList.add(i + ". " + key + ": " + schedule.get(key));
        //     i++;
        // }
        // order arraylist
        // Collections.sort(Integer.parseInt(myList.substring(0)));
        // System.out.println(myList);
        ArrayList<String> week = new ArrayList<>();
        week.add("Sunday"); week.add("Monday"); week.add("Tuesday"); week.add("Wednesday"); week.add("Thursday"); week.add("Friday"); week.add("Saturday");
        
        for (int u=0; u<7; u++) {
            for (String key: schedule.keySet()) {
                // System.out.println(schedule.keySet());
                if (key.equals("Sunday") && u==0) {
                    myList.add(key + ": " + schedule.get(key));
                }
                if (key.equals("Monday") && u==1) {
                    myList.add(key + ": " + schedule.get(key));
                }
                if (key.equals("Tuesday") && u==2) {
                    myList.add(key + ": " + schedule.get(key));
                }
                if (key.equals("Wednesday") && u==3) {
                    myList.add(key + ": " + schedule.get(key));
                }
                if (key.equals("Thursday") && u==4) {
                    myList.add(key + ": " + schedule.get(key));
                }
                if (key.equals("Friday") && u==5) {
                    myList.add(key + ": " + schedule.get(key));
                }
                if (key.equals("Saturday") && u==6) {
                    myList.add(key + ": " + schedule.get(key));
                }
            }
        }
        // System.out.println(myList);
        System.out.println("Ordering the schedule..");
        return myList;
    }
 
    // use this to save the schedule as a text file
    public void saveFile(ArrayList<String> schedule) {
        // System.out.println(schedule);
        try {
            FileWriter fw = new FileWriter("schedule.txt");
            for (String str: schedule) {
                fw.write(str + System.lineSeparator());
            }
            fw.close(); // always have to close file
            System.out.println("File successfully written.");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}