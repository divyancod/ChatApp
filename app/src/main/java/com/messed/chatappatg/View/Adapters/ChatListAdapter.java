package com.messed.chatappatg.View.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.messed.chatappatg.Model.ChatInside;
import com.messed.chatappatg.Model.UserChatList;
import com.messed.chatappatg.View.Acitivites.ChattingInside;
import com.messed.chatappatg.R;
import com.messed.chatappatg.ViewModel.ChattingInsideViewModel;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatListScreenHolder> {

    List<UserChatList> userChatLists;
    Context context;
    static final String TAG = "ChatScreenAdapter";

    public ChatListAdapter(List<UserChatList> userChatLists,Context context) {
        this.userChatLists=userChatLists;
        this.context=context;
    }

    @NonNull
    @Override
    public ChatListScreenHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.chat_recycler, parent, false);
        return new ChatListScreenHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChatListScreenHolder holder, final int position) {
        //holder.t1.setText(user.get(position));
        holder.t1.setText(userChatLists.get(position).getUsername());
        holder.c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, holder.t1.getText(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ChattingInside.class);
                intent.putExtra("username", holder.t1.getText());
                //intent.putExtra("userkey", userkey.get(position));
                intent.putExtra("userkey",userChatLists.get(position).getUserkey());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userChatLists.size();
    }

    public class ChatListScreenHolder extends RecyclerView.ViewHolder {
        TextView t1;
        CardView c1;

        public ChatListScreenHolder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.textview1);
            c1 = itemView.findViewById(R.id.cardview001);
        }
    }
}


