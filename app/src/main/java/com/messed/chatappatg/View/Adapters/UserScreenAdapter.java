package com.messed.chatappatg.View.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.messed.chatappatg.View.Acitivites.ChattingInside;
import com.messed.chatappatg.R;

import java.util.ArrayList;

public class UserScreenAdapter extends RecyclerView.Adapter<UserScreenAdapter.ChatScreenHolder> {

    ArrayList<String> user;
    ArrayList<String> userkey;
    Context context;
    static final String TAG = "ChatScreenAdapter";

    public UserScreenAdapter(ArrayList<String> user, Context context, ArrayList<String> userkey) {
        this.user = user;
        //Log.e(TAG, "ChatScreenAdapter: "+user.get(0) );
        this.context = context;
        this.userkey = userkey;
    }

    @NonNull
    @Override
    public ChatScreenHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.chat_recycler, parent, false);
        return new ChatScreenHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChatScreenHolder holder, final int position) {
        holder.t1.setText(user.get(position));
        holder.c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, holder.t1.getText(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ChattingInside.class);
                intent.putExtra("username", holder.t1.getText());
                intent.putExtra("userkey", userkey.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return user.size();
    }

    public class ChatScreenHolder extends RecyclerView.ViewHolder {
        TextView t1;
        CardView c1;

        public ChatScreenHolder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.textview1);
            c1 = itemView.findViewById(R.id.cardview001);
        }
    }
}


