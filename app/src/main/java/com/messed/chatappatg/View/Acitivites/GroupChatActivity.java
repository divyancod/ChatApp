package com.messed.chatappatg.View.Acitivites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.messed.chatappatg.Model.ChatInside;
import com.messed.chatappatg.R;
import com.messed.chatappatg.View.Adapters.ChattingInsideAdapter;
import com.messed.chatappatg.ViewModel.ChattingInsideViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class GroupChatActivity extends AppCompatActivity {

    EditText e1;
    ImageButton b1;
    RecyclerView recyclerView;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference reference;
    String selfname;


    ChattingInsideAdapter chattingInsideAdapter;


    static final String TAG = "GroupChatActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getSupportActionBar().hide();
        e1 = findViewById(R.id.messageInput1);
        b1 = findViewById(R.id.sendButton1);
        recyclerView = findViewById(R.id.recyclerviewgroup);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        getAllChats();
        //-----------------------getting self name-------------------------------------------------------------------------------
        reference = database.getReference(firebaseAuth.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                selfname = dataSnapshot.child("username").getValue().toString();
                Log.e(TAG, "onDataChange====: " + dataSnapshot.child("username").getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //--------------------------------------------------------------------------------------------------------------

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = e1.getText().toString().trim();
                if (!s.isEmpty()) {
                    reference = database.getReference("group").child("message");
                    HashMap<String, String> hashMap = new HashMap<>();
                    Date d = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                    String msgtime = sdf.format(d);
                    hashMap.put("sender", selfname);
                    hashMap.put("info", s);
                    hashMap.put("time", msgtime);
                    reference.push().setValue(hashMap);
                    e1.setText("");
                    Log.e(TAG, "onClick: runned till here");
                }
            }
        });
    }

//---------------------adapter call function here---------------------------------------------------------------------//

    public void getAllChats() {
        final ChattingInsideViewModel chattingInsideViewModel = new ChattingInsideViewModel();
        chattingInsideViewModel.getGroupChats().observe(GroupChatActivity.this, new Observer<List<ChatInside>>() {
            @Override
            public void onChanged(List<ChatInside> chatInsides) {
                Log.e(TAG, "onChanged: " + "runiing till here");
                chattingInsideAdapter = new ChattingInsideAdapter(chatInsides, GroupChatActivity.this);
                recyclerView.setAdapter(chattingInsideAdapter);
            }
        });
    }
}
