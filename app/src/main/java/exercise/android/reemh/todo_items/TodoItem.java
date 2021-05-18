package exercise.android.reemh.todo_items;

import java.io.Serializable;
import java.util.Comparator;

enum Status {DONE, IN_PROGRESS}

public class TodoItem implements Serializable {

    private Status _status;
    private String _description;
    private int _itemTimeCtr;
    public static int timeCtr = 1;

    //constructor
    public TodoItem()
    {
        _itemTimeCtr = timeCtr;
        timeCtr++;
    }

    public TodoItem(Status status, String description)
    {
        this._status = status;
        this._description = description;
        _itemTimeCtr = timeCtr;
        timeCtr++;
    }

    //getters
    public Status getStatus() {return this._status;}
    public String getDescription() {return this._description;}
    public int getItemTimeCtr() {return this._itemTimeCtr;}

    //setters
    public void setStatus(Status newStatus) {this._status = newStatus;}
    public void setDescription(String newDescription) {this._description = newDescription;}
}
