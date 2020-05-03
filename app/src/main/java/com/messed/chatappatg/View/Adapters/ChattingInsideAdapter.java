package com.messed.chatappatg.View.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.messed.chatappatg.Model.ChatInside;
import com.messed.chatappatg.R;

import java.util.ArrayList;
import java.util.List;

public class ChattingInsideAdapter extends RecyclerView.Adapter<ChattingInsideAdapter.ChatInsideHolder> {

    static final String TAG = "ChatScreenAdapterInside";

    List<ChatInside> chatInsides;
    Context context;

    public ChattingInsideAdapter(List<ChatInside> chatInsides, Context context) {
        this.chatInsides=chatInsides;
        this.context=context;
    }

    @NonNull
    @Override
    public ChatInsideHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.chat_inside_recycler, parent, false);
        return new ChatInsideHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ChatInsideHolder holder, int position) {
        //------------------------------------color wise testing-------------------------------------------//
        if (chatInsides.get(position).getType().equals("received")) {
            holder.t1.setTextColor(Color.RED);
            holder.t1.setText("You "+chatInsides.get(position).getType());

        }
        else if (chatInsides.get(position).getType().equals("send")) {
           holder.t1.setTextColor(Color.BLUE);
           holder.t1.setText("You "+chatInsides.get(position).getType());
           holder.t2.setGravity(Gravity.RIGHT);
        }
        else
        {
            holder.t1.setTextColor(Color.MAGENTA);
            holder.t1.setText("By "+chatInsides.get(position).getType());
        }

        holder.t2.setText(chatInsides.get(position).getInfo());
        holder.t3.setText(chatInsides.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return chatInsides.size();
    }

    public class ChatInsideHolder extends RecyclerView.ViewHolder {
        TextView t1, t2,t3;
        CardView c1;

        public ChatInsideHolder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.textview01);
            t2 = itemView.findViewById(R.id.textview02);
            t3=itemView.findViewById(R.id.typetxt3);
            c1=itemView.findViewById(R.id.cardviewchat);

        }
    }
}
