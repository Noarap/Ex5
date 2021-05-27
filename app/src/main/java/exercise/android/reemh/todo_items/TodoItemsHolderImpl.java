package exercise.android.reemh.todo_items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TodoItemsHolderImpl implements TodoItemsHolder {
  private List<TodoItem> _todoList;

  //constructor
  public TodoItemsHolderImpl()
  {
    this._todoList = new ArrayList<>();
  }

  private void sortItems()
  {
    Collections.sort(this._todoList, new CompareItems());
  }

  @Override
  public List<TodoItem> getCurrentItems() {return this._todoList;}

  public void setCurrentItems(List<TodoItem> newLst)
  {
    this._todoList = newLst;
  }

  public void itemEdited(TodoItem item)
  {
    int j = 0;
    for (TodoItem i:this._todoList) {
      if (i.creationDate.equals(item.creationDate))
      {
        this._todoList.set(j, item);
        return;
      }
      j ++;
    }
  }

  @Override
  public void addNewInProgressItem(String description)
  {
    TodoItem newItem = new TodoItem(Status.IN_PROGRESS, description);
    this._todoList.add(newItem);
    sortItems();
  }

  @Override
  public void markItemDone(TodoItem item)
  {
    for (TodoItem curItem:this._todoList)
    {
      if (curItem.equals(item))
      {
        curItem.setStatus(Status.DONE);
      }
    }
    sortItems();
  }

  @Override
  public void markItemInProgress(TodoItem item)
  {
    for (TodoItem curItem:this._todoList)
    {
      if (curItem.equals(item))
      {
        curItem.setStatus(Status.IN_PROGRESS);
      }
    }
    sortItems();
  }

  @Override
  public void deleteItem(TodoItem item)
  {this._todoList.remove(item);}
}
