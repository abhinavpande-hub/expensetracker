package com.example.expensetracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder>{

    ArrayList<Expense> list;
    Context context;
    DBHelper db;
    Runnable refresh;

    public ExpenseAdapter(Context context, ArrayList<Expense> list, DBHelper db, Runnable refresh){
        this.context=context;
        this.list=list;
        this.db=db;
        this.refresh=refresh;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v=LayoutInflater.from(context).inflate(R.layout.activity_main3,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position){

        Expense e=list.get(position);

        holder.title.setText(e.title);
        holder.amount.setText("₹"+e.amount);

        holder.delete.setOnClickListener(v->{
            db.deleteExpense(e.id);
            refresh.run();
        });
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView title,amount;
        ImageButton delete;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            title=itemView.findViewById(R.id.tvTitle);
            amount=itemView.findViewById(R.id.tvAmount);
            delete=itemView.findViewById(R.id.btnDelete);
        }
    }
}
