package com.messed.chatappatg.View.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.messed.chatappatg.Model.UserChatList;
import com.messed.chatappatg.R;
import com.messed.chatappatg.View.Adapters.ChatListAdapter;
import com.messed.chatappatg.ViewModel.UserChatListViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserScreen extends Fragment {

    FirebaseAuth firebaseAuth;
    DatabaseReference reference, dbref;
    FirebaseDatabase database;
    static final String TAG = "UserScreen";
    int user;
    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    String selfname;
    TextView t1;

    public UserScreen() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_screen, container, false);
        recyclerView = view.findViewById(R.id.rv01);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        t1=view.findViewById(R.id.tvonline);
        progressDialog=new ProgressDialog(getActivity());
        showChat();
        //getSelfName();

        return view;
    }

    //-----------------------getting self name-------------------------------------------------------------------------------
    void getSelfName()
    {

        reference = database.getReference(firebaseAuth.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                selfname = dataSnapshot.child("username").getValue().toString();
                Log.e(TAG, "here: "+selfname );
                t1.setText("Currently logged in as "+selfname.toUpperCase());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    //----------
    public void showChat()
    {
        progressDialog.setMessage("Loading Chats");
        progressDialog.show();
        UserChatListViewModel userChatListViewModel=new UserChatListViewModel();
        userChatListViewModel.getChatList().observe(UserScreen.this, new Observer<List<UserChatList>>() {
            @Override
            public void onChanged(List<UserChatList> userChatLists) {
                Log.e(TAG, "onChanged: "+"running till here" );
                ChatListAdapter chatListAdapter=new ChatListAdapter(userChatLists,getActivity());
                recyclerView.setAdapter(chatListAdapter);
                progressDialog.dismiss();
            }
        });
    }
}
/* --------------------------My code to send temporay data please ignore----------------------------------------------------
        String key1="CjkIhPnG8dO6eWrUN2yAnI00Rjx2";//amar
        String key2="ARInkkUHPDcQ90OBRCUGwenrzs12";//birbal
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("Akbar",key1);
        hashMap.put("amar",key2);
        reference=database.getReference().child(firebaseAuth.getUid()).child("friends");
        reference.push().setValue(hashMap);*/