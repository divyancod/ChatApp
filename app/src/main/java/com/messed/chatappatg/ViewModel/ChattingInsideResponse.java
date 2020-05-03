package com.messed.chatappatg.ViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.messed.chatappatg.Model.ChatInside;

import java.util.ArrayList;
import java.util.List;

public class ChattingInsideResponse {
    static final String TAG = "ChattingInsideResponse";

    //----------------------------------------------------for single message fetch--------------------------------------
    public static MutableLiveData<List<ChatInside>> requestChatMessage(String user) {
        final MutableLiveData<List<ChatInside>> liveData = new MutableLiveData<>();

        FirebaseAuth firebaseAuth;
        DatabaseReference reference;
        FirebaseDatabase database;
        database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        reference = database.getReference(firebaseAuth.getUid());
        reference = database.getReference(firebaseAuth.getUid()).child("message").child(user);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<ChatInside> chatInside = new ArrayList<>();
                String time, type, info;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    type = data.child("type").getValue().toString();
                    info = data.child("info").getValue().toString();
                    time = data.child("time").getValue().toString();
                    chatInside.add(new ChatInside(type, info, time));
                }
                liveData.setValue(chatInside);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return liveData;
    }


    //-----------------------------for group message fetch-----------------------------------------------------------
    public static MutableLiveData<List<ChatInside>> requestGroupChatMessage() {
        final MutableLiveData<List<ChatInside>> liveData = new MutableLiveData<>();

        FirebaseAuth firebaseAuth;
        DatabaseReference reference;
        FirebaseDatabase database;
        database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        reference = database.getReference("group").child("message");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<ChatInside> groupChatInside = new ArrayList<>();
                String time, type, info;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    type = data.child("sender").getValue().toString();
                    info = data.child("info").getValue().toString();
                    time = data.child("time").getValue().toString();
                    groupChatInside.add(new ChatInside(type, info, time));

                }
                liveData.setValue(groupChatInside);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return liveData;
    }

}
