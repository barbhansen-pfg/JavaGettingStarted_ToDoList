/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ToDo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class App {
    private static FileWriter file;

    public String getGreeting() {
        return "ToDo List Manager";
    }


    public void displayFirstMenu() {
        System.out.println("\n\t\tMain Menu");
        System.out.println("\n1. Open To Do List");//open up from file
        System.out.println("2. Display To Do Items");
        System.out.println("3. Edit To Do Item");
        System.out.println("4. Delete To Do Item");
        System.out.println("5. Save To Do List");
        System.out.println("6. Add Item To Do List");
        System.out.println("7. Exit");

        return;
    }

    private String callScanner(String prompt) {
        System.out.println(prompt);
        String response = scanner.nextLine();

        return response;
    }

    private void displayTaskList(ArrayList<Task> listOfTasks) {
        showTaskList(listOfTasks);
        askIfFinished();
    }

    private void askIfFinished() {
        String prompt = "\nWould you like to return to main menu (Y/N)? ";
        String responseReturned = callScanner(prompt);
        if (responseReturned.toUpperCase().equals("Y"))
            return;

        prompt = "\nWould you like to exit (Y/N)?";
        responseReturned = callScanner(prompt);
        if (responseReturned.toUpperCase().equals("Y"))
            System.exit(0);
        else
            return;
    }

    private void showTaskList(ArrayList<Task> listOfTasks) {
        System.out.println("\n\tTasks" + "\t\t\t Due Date" + "\t In Progress?" + "\t Completed?");
        int count = 1;
        for (Task task : listOfTasks) {
            System.out.println(count + " - " + task.getName() + "\t\t " + task.getDueDate() + "\t " + task.getIsInProgress() + "\t\t\t " +task.getIsCompleted());
            //need to figure out how to handle long task names
            //should change the in progress and finished to not use boolean values
            count++;
        }
    }

    private void deleteTask(ArrayList<Task> listOfTasks) {
        showTaskList(listOfTasks);
        String prompt = "\nType the number of the item you wish to delete and hit enter. ";
        String responseReturned = callScanner(prompt);
        System.out.println("You chose to delete task #" + responseReturned);
        int responseReturnedInt = Integer.parseInt(responseReturned);
        int sizeOfTaskList = listOfTasks.size();
        if (sizeOfTaskList < responseReturnedInt) {
            System.out.println("invalid choice");
            askIfFinished();
        }
        else {
            listOfTasks.remove(responseReturnedInt - 1);
            showTaskList(listOfTasks);
            askIfFinished();
        }
    }

    private void editTask(ArrayList<Task> listOfTasks) {
        showTaskList(listOfTasks);
        String prompt = "\nType the number of the item you wish to edit and hit enter. ";
        String responseReturned = callScanner(prompt);
        System.out.println("You chose to delete task #" + responseReturned);
        int responseReturnedInt = Integer.parseInt(responseReturned);
        int sizeOfTaskList = listOfTasks.size();
        if (sizeOfTaskList < responseReturnedInt) {
            System.out.println("invalid choice");
            askIfFinished();
        }
        else {
            prompt= "\nType name of task and hit enter.";
            String editedTaskName = callScanner(prompt);
            Task task = listOfTasks.get(responseReturnedInt - 1);
            task.setName(editedTaskName);
            showTaskList(listOfTasks);
            askIfFinished();
        }
    }

    private static void addTask(App app, ArrayList<Task> listOfTasks) {
        String prompt;
        prompt = "\nType in the name of the task that you would like to add.";
        String newTaskNameReturned = app.callScanner(prompt);
        prompt = "\nType in the due date of the task in format YYYYMMDD.";
        String newTaskDueDate = app.callScanner(prompt);
        int newTaskDueDateYear = Integer.parseInt(newTaskDueDate.substring(0, 4));
        System.out.println(newTaskDueDateYear);
        int newTaskDueDateMo = Integer.parseInt(newTaskDueDate.substring(4, 6));
        System.out.println(newTaskDueDateMo);
        int newTaskDueDateDay = Integer.parseInt(newTaskDueDate.substring(6, 8));
        System.out.println(newTaskDueDateDay);
        prompt = "\nIs this new task in progress (y/n)?";
        String newTaskInProgress = app.callScanner(prompt);
        Boolean taskInProgress;
        if (newTaskInProgress.toUpperCase().equals("Y"))
            taskInProgress = true;
        else
            taskInProgress = false;
        prompt = "\nIs this new finished (y/n)?";
        String newTaskFinished = app.callScanner(prompt);
        Boolean taskFinished;
        if (newTaskFinished.toUpperCase().equals("Y"))
            taskFinished = true;
        else
            taskFinished = false;
        Task newTask = new Task(newTaskNameReturned, LocalDate.of(newTaskDueDateYear, newTaskDueDateMo, newTaskDueDateDay),taskInProgress, taskFinished);
        listOfTasks.add(newTask);
        app.displayTaskList(listOfTasks);
        return;
    }

        private static String convertListToJson (ArrayList < Task > taskList) {
            ObjectMapper obj = new ObjectMapper();
            String result = null;
            try {
                result = obj.writeValueAsString(taskList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            try {
                file = new FileWriter("/Git/JavaGettingStarted_ToDoList/TasklistArray.txt");
                file.write(result);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    file.flush();
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }


    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        App app = new App();

        Task task1 = new Task("Iron pants",LocalDate.of(2021,3,1),true,false);
        Task task2 = new Task("Rake Leaves",LocalDate.of(2020,5,15),false,true);
        Task task3 = new Task("Till garden",LocalDate.of(2021,1,31),false,true);
        ArrayList<Task> listOfTasks = new ArrayList<>();
        listOfTasks.add(task1);
        listOfTasks.add(task2);
        listOfTasks.add(task3);
        System.out.println(listOfTasks.size());
        System.out.println(listOfTasks.get(0).getName() + " " + listOfTasks.get(0).getDueDate() + " " + listOfTasks.get(0).getIsCompleted() + " " + listOfTasks.get(0).getIsInProgress());

        String responseReturned;
        do {
            app.displayFirstMenu();
            String prompt = "\nPlease input your option:";
            responseReturned = app.callScanner(prompt);
            System.out.println("\nYour response was " + responseReturned);
            switch (responseReturned) {
                case "1": //this option is for opening the file
                    System.out.println("You chose to open the to do list");
                    break;
                case "2":
                    app.displayTaskList(listOfTasks);
                    break;
                case "3":
                    app.editTask(listOfTasks);
                    break;
                case "4":
                    app.deleteTask(listOfTasks);
                    break;
                case "5":
                    String jsonListObject = convertListToJson(listOfTasks);
                    System.out.println("The file has been saved to C:Git>JavaGettingStarted_ToDo_list>TasklistArray.txt");
                    app.askIfFinished();
                    break;
                case "6":
                    addTask(app, listOfTasks);
                    break;
                case "7":
                    System.out.println("You chose to exit");
                    break;
                default:
                    System.out.println("You chose an invalid option - Please enter a number between 1 and 6");
                    break;
            }
        } while (!responseReturned.equals("7"));
        app.scanner.close();
    }




}
