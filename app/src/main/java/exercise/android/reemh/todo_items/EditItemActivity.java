package exercise.android.reemh.todo_items;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditItemActivity extends AppCompatActivity {
    private TodoItem todoItem;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        this.todoItem = (TodoItem)intent.getSerializableExtra("item");

        setContentView(R.layout.edit_item);
        EditText descriptionView = findViewById(R.id.taskDescription);
        TextView statusView = findViewById(R.id.taskStatus);
        TextView dateCreatedView = findViewById(R.id.dateCreated);
        TextView dateModifiedView = findViewById(R.id.dateModified);

        descriptionView.setText(todoItem.getDescription());
        String creationTimeTitle = "Time Created: " + todoItem.creationDate;
        dateCreatedView.setText(creationTimeTitle);
        dateModifiedView.setText(setTimeToLatestAndGetTitle());
        todoItem.modificationDate = new Date();

        String statusTitle = "In Process";
        if (this.todoItem.getStatus() == Status.DONE)
        {
            statusTitle = "Done!";
        }
        statusView.setText(statusTitle);

        descriptionView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                todoItem.setDescription(editable.toString());
                setTimeToLatestAndGetTitle();
                todoItem.modificationDate = new Date();
            }
        });

    }

    private String setTimeToLatestAndGetTitle() {

        Date date = new Date();
        long minutes = (date.getTime() - todoItem.modificationDate.getTime()) / (1000 * 60);
        long hours = minutes / 60;

        if (minutes < 60) {
            return ("Modified in minutes: " + minutes + " minutes ago");
        } else if (hours < 24) {
            return ("Modified in hours: " + hours + " hours ago");
        }

        String timeFormat = new SimpleDateFormat("yyyy-dd-MM", Locale.getDefault()).format(todoItem.modificationDate);
        return ("Date Modified: " + timeFormat + (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.setAction("edited");
        intent.putExtra("item",this.todoItem);
        sendBroadcast(intent);
    }
}
