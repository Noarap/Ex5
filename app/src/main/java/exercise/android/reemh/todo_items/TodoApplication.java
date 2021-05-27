package exercise.android.reemh.todo_items;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class TodoApplication extends Application {

    Context context;
    SharedPreferences sp;
    public List<TodoItem> todoItemList;

    public TodoApplication(Context context)
    {
        this.context = context;
        this.sp = getDefaultSharedPreferences(context);
        this.updateList();
    }

    public void saveList()
    {
        String itemsFromJson = new Gson().toJson(todoItemList);
        sp.edit().putString("json_items", itemsFromJson).apply();
    }

    public void updateList()
    {
        todoItemList = new ArrayList<>();
        String itemsFromJson = sp.getString("json_items", "");
        boolean isEmpty = itemsFromJson.equals("");
        if (isEmpty)
        {
            return;
        }
        Type typeOfList = new TypeToken<ArrayList<TodoItem>>(){}.getType();
        todoItemList = new Gson().fromJson(itemsFromJson, typeOfList);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


}
