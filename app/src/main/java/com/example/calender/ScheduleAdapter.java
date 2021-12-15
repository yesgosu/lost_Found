package com.example.calender;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calender.model.Schedule;

import java.util.function.Consumer;

public class ScheduleAdapter extends ListAdapter<Schedule, ScheduleAdapter.ViewHolder> {

    private final Consumer<Schedule> onScheduleClickListener;

    public ScheduleAdapter(Consumer<Schedule> onScheduleClickListener) {
        super(DIFF_CALLBACK);
        this.onScheduleClickListener = onScheduleClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Schedule s = getCurrentList().get(position);

        holder.tvTitle.setText(s.title);
        holder.tvDate.setText(s.content);
        holder.itemView.setOnClickListener(v -> onScheduleClickListener.accept(s));
    }


    public static DiffUtil.ItemCallback<Schedule> DIFF_CALLBACK = new DiffUtil.ItemCallback<Schedule>() {
        @Override
        public boolean areItemsTheSame(@NonNull Schedule oldItem, @NonNull Schedule newItem) {
            return oldItem.numberId == newItem.numberId;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Schedule oldItem, @NonNull Schedule newItem) {
            return oldItem.ga.equals(newItem.ga) &&
                    oldItem.content.equals(newItem.content) &&
                    oldItem.title.equals(newItem.title);
        }
    };


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvTitle;
        public final TextView tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
}
