package com.messed.chatappatg.ViewModel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.messed.chatappatg.Model.UserChatList;

import java.util.ArrayList;
import java.util.List;

public class UserChatListResponse {
    static final String TAG="Response";
    public static MutableLiveData<List<UserChatList>> requestUserChatList()
    {
       final MutableLiveData<List<UserChatList>> liveData = new MutableLiveData<>();

        FirebaseAuth firebaseAuth;
        DatabaseReference reference;
        final FirebaseDatabase database;
        database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        reference = database.getReference().child(firebaseAuth.getUid()).child("friends");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String user,userkey;
                List<UserChatList> userinfo=new ArrayList<>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    for (DataSnapshot dataSnapshot1 : data.getChildren()) {
                        user = dataSnapshot1.getKey();
                        userkey=dataSnapshot1.getValue().toString();
                        userinfo.add(new UserChatList(user,userkey));
                        //userkey.add(dataSnapshot1.getValue().toString());
                        Log.e(TAG, "onDataChange: " + dataSnapshot1.getKey());
                    }
                }
                liveData.setValue(userinfo);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return liveData;
    }
}
