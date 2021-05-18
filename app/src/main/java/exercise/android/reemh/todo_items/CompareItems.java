package exercise.android.reemh.todo_items;

import java.util.Comparator;

public class CompareItems implements Comparator<TodoItem>
{
    @Override
    public int compare(TodoItem it1, TodoItem it2) {
        if (it1.getStatus() == Status.IN_PROGRESS)
        {
            if (it2.getStatus() == Status.DONE)
            {
                return -1;
            }
            else {
                if (it1.getItemTimeCtr() < it2.getItemTimeCtr())
                {
                    return 1;
                }
                else
                {
                    return -1;
                }
            }
        }
        else
        {
            if (it1.getItemTimeCtr()<it2.getItemTimeCtr())
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    }
}

