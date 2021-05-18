package exercise.android.reemh.todo_items;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

  public TodoItemsHolder holder;
  public AdapterItems adapterItems;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (holder == null) {
      holder = new TodoItemsHolderImpl();
    }
    adapterItems = new AdapterItems(this, this.holder);

    //find all views
    TextView editTextView =findViewById(R.id.editTextInsertTask);
    RecyclerView recyclerView = findViewById(R.id.recyclerTodoItemsList);
    FloatingActionButton buttonCreateItemView = findViewById(R.id.buttonCreateTodoItem);

    recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
    recyclerView.setAdapter(adapterItems);
    editTextView.setText("");

    buttonCreateItemView.setOnClickListener(view -> {
      String description = editTextView.getText().toString();
      if (description.length() > 0)
      {
        this.holder.addNewInProgressItem(description);
        editTextView.setText("");
        this.adapterItems.notifyDataSetChanged();
      }
      else
      {
        return;
      };
    });
  }
}

