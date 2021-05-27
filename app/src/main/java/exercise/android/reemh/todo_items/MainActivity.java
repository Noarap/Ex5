package exercise.android.reemh.todo_items;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

  public TodoItemsHolderImpl holder;
  public AdapterItems adapterItems;
  public BroadcastReceiver broadcastReceiver;
  public TodoApplication todoApplication;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    todoApplication = new TodoApplication(this);

    if (holder == null) {
      holder = new TodoItemsHolderImpl();
    }
    todoApplication.updateList();
    holder.setCurrentItems(todoApplication.todoItemList);
    adapterItems = new AdapterItems(this, this.holder);

    //find all views
    TextView editTextView =findViewById(R.id.editTextInsertTask);
    RecyclerView recyclerView = findViewById(R.id.recyclerTodoItemsList);
    FloatingActionButton buttonCreateItemView = findViewById(R.id.buttonCreateTodoItem);

    recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
    recyclerView.setAdapter(adapterItems);
    if (savedInstanceState == null)
    {
      editTextView.setText("");
    }
    else
    {
      editTextView.setText(savedInstanceState.getString("edited"));
    }

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

    broadcastReceiver = new BroadcastReceiver() {
      @Override
      public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("edited"))
        {
           holder.itemEdited((TodoItem) intent.getSerializableExtra("item"));
           todoApplication.todoItemList = holder.getCurrentItems();
           todoApplication.saveList();
           adapterItems.notifyDataSetChanged();
        }
      }
    };

    registerReceiver(broadcastReceiver, new IntentFilter("edited"));
  }

  @Override
  public void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable("edited", this.holder);
  }

  @Override
  protected void onResume() {
    super.onResume();
    todoApplication.updateList();
    holder.setCurrentItems(todoApplication.todoItemList);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    this.unregisterReceiver(broadcastReceiver);
  }

  @Override
  protected void onStop() {
    super.onStop();
    todoApplication.todoItemList = this.holder.getCurrentItems();
    todoApplication.saveList();
  }

}

