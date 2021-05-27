package exercise.android.reemh.todo_items;

import java.io.Serializable;
import java.util.Date;

enum Status {DONE, IN_PROGRESS}

public class TodoItem implements Serializable {

    private Status _status;
    private String _description;
    public static int timeCtr = 1;
    public Date creationDate;
    public Date modificationDate;

    //constructor
    public TodoItem()
    {
        timeCtr++;
        creationDate = new Date();
        modificationDate = creationDate;
    }

    public TodoItem(Status status, String description)
    {
        this._status = status;
        this._description = description;
        timeCtr++;
        creationDate = new Date();
        modificationDate = creationDate;
    }

    //getters
    public Status getStatus() {return this._status;}
    public String getDescription() {return this._description;}

    //setters
    public void setStatus(Status newStatus) {this._status = newStatus;}
    public void setDescription(String newDescription) {this._description = newDescription;}
}
