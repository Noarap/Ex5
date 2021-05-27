package exercise.android.reemh.todo_items;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ItemHolderView extends RecyclerView.ViewHolder
{
    public CheckBox checkBox;
    public TextView description;
    public Button deleteButton;
    public FloatingActionButton editTask;

    public ItemHolderView(@NonNull View itemView) {
        super(itemView);
        description = itemView.findViewById(R.id.description);
        checkBox = itemView.findViewById(R.id.checkbox);
        deleteButton = itemView.findViewById(R.id.buttonDelete);
        editTask = itemView.findViewById(R.id.editButton);
    }


}
