package ToDo;

import java.time.LocalDate;

public class Task {
    private String name;
    private LocalDate dueDate;
    private boolean inProgress;
    private boolean completed;

    public Task() {
    }

    public Task(String name) {
        this.name = name;
    }

    //public Task(String ironing, boolean b, boolean b1) {
    //}
    public Task(String name, LocalDate dueDate, boolean inProgress, boolean completed) {
        this.name = name;
        this.dueDate = dueDate;
        this.inProgress = inProgress;
        this.completed = completed;
    }


    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    //public LocalDate getDueDate() {
    //  return dueDate;
    //}
    public String getDueDate() {

        return dueDate.toString();
    }

    //public void setDueDate(LocalDate dueDate) {
        //this.dueDate = dueDate;
    //}
    public void setDueDate(String dueDate) {

        this.dueDate = LocalDate.parse(dueDate);
    }

    public boolean getInProgress() {

        return inProgress;
    }

    public void setInProgress(boolean inProgress) {

        this.inProgress = inProgress;
    }

    public boolean getCompleted() {

        return completed;
    }

    public void setCompleted(boolean completed) {

        this.completed = completed;
    }

}
