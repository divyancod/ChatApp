package com.messed.chatappatg.View.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.messed.chatappatg.R;

import java.util.ArrayList;

public class ChatScreenAdapterInside extends RecyclerView.Adapter<ChatScreenAdapterInside.ChatHolder> {

    static final String TAG = "ChatScreenAdapterInside";

    ArrayList<String> type, info,time;

    public ChatScreenAdapterInside(ArrayList<String> type, ArrayList<String> info,ArrayList<String> time) {
        this.type = type;
        this.info = info;
        this.time=time;
    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.chat_inside_recycler, parent, false);
        return new ChatHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {
        //------------------------------------color wise testing-------------------------------------------//
        if (type.get(position).equals("received")) {
            holder.t1.setTextColor(Color.RED);
            holder.t1.setText("You "+type.get(position));
        }
        else if (type.get(position).equals("send")) {
            holder.t1.setTextColor(Color.BLUE);
            holder.t1.setText("You "+type.get(position));
            //holder.t2.setGravity(Gravity.RIGHT);unable to work on gravity to check before submitting
        }
        else
        {
            holder.t1.setTextColor(Color.BLUE);
            holder.t1.setText("By "+type.get(position));
        }

        holder.t2.setText(info.get(position));
        holder.t3.setText(time.get(position));
    }

    @Override
    public int getItemCount() {
        return type.size();
    }

    public class ChatHolder extends RecyclerView.ViewHolder {
        TextView t1, t2,t3;

        public ChatHolder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.textview01);
            t2 = itemView.findViewById(R.id.textview02);
            t3=itemView.findViewById(R.id.typetxt3);
        }
    }
}
