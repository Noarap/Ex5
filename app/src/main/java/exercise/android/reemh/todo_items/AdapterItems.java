package exercise.android.reemh.todo_items;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterItems extends RecyclerView.Adapter<ItemHolderView>
{
    public Context context;
    public TodoItemsHolder itemsHolder;
    public LayoutInflater layoutInflater;

    public AdapterItems(Context context, TodoItemsHolder itemHolder)
        {
            this.context = context;
            this.itemsHolder = itemHolder;
            this.layoutInflater = LayoutInflater.from(context);
        }

    @NonNull
    @Override
    public ItemHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = this.layoutInflater.inflate(R.layout.single_item, parent, false);
        return new ItemHolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolderView holder, int position) {
        TodoItem item = this.itemsHolder.getCurrentItems().get(position);
        holder.description.setText(item.getDescription());
        holder.checkBox.setChecked(item.getStatus() == Status.DONE);
        if(item.getStatus() == Status.DONE)
        {
            holder.description.setPaintFlags(holder.description.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        else
        {
            holder.description.setPaintFlags(0);
        }
        holder.checkBox.setOnClickListener(view -> {
            if (item.getStatus() == Status.DONE)
            {
                this.itemsHolder.markItemInProgress(item);
            }
            else
            {
                this.itemsHolder.markItemDone(item);
            }
            notifyDataSetChanged();
        });
        holder.deleteButton.setOnClickListener(view -> {
            this.itemsHolder.deleteItem(item);
            notifyDataSetChanged();
        });

        holder.editTask.setOnClickListener(v ->{
            Intent intent = new Intent(this.context, EditItemActivity.class);
            intent.putExtra("item", item);
            this.context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return itemsHolder.getCurrentItems().size();
    }
}
