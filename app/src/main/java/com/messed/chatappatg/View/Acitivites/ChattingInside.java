package com.messed.chatappatg.View.Acitivites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.messed.chatappatg.R;
import com.messed.chatappatg.View.Adapters.ChatScreenAdapterInside;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ChattingInside extends AppCompatActivity {

    TextView t1;
    RecyclerView recyclerView;

    FirebaseAuth firebaseAuth;
    DatabaseReference reference;
    FirebaseDatabase database;
    EditText e1;
    ImageButton b1;
    ProgressDialog progressDialog;
    String inpute1, user, userkey, selfname;
    static final String TAG = "ChattingInside";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_inside);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getSupportActionBar().hide();
        user = getIntent().getStringExtra("username");
        userkey = getIntent().getStringExtra("userkey");
        progressDialog = new ProgressDialog(this);
        //Toast.makeText(this, userkey, Toast.LENGTH_SHORT).show();
        t1 = findViewById(R.id.textview001);
        t1.setText(user);
        e1 = findViewById(R.id.messageInput);
        b1 = findViewById(R.id.sendButton);
        recyclerView = findViewById(R.id.recyclerviewchat);
        database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        reference = database.getReference(firebaseAuth.getUid());
        //------------------------------------getting self name through firebaseauth-------------------------------------//
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
//-------------------------------------------------------------------------------------------------------------------------------
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        funCall();
//----------------------------------------send button----------------------------------------------------------------------------//
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inpute1 = e1.getText().toString().trim();
                e1.setText("");
                if (!inpute1.isEmpty()) {
                    reference = database.getReference(firebaseAuth.getUid()).child("message").child(user);
                    HashMap<String, String> hashMap = new HashMap<>();
                    //---date--//
                    Date d = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                    String msgtime = sdf.format(d);
                    //---date---//
                    hashMap.put("info", inpute1);
                    hashMap.put("type", "send");
                    hashMap.put("time", msgtime);
                    Log.e(TAG, "onClick: " + inpute1);
                    reference.push().setValue(hashMap);
                    hashMap.clear();
                    reference = database.getReference(userkey).child("message").child(selfname);
                    hashMap.put("info", inpute1);
                    hashMap.put("type", "received");
                    hashMap.put("time", msgtime);
                    reference.push().setValue(hashMap);
                    funCall();
                }
            }
        });
    }

    //------------------------------------------loader function here------------------------------------------------/
    public void funCall() {
        progressDialog.setMessage("Loading Chats");
        progressDialog.show();
        reference = database.getReference(firebaseAuth.getUid()).child("message").child(user);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Log.e(TAG, "onDataChange: "+dataSnapshot.child("Akbar").child("type").getValue());
                ArrayList<String> type1 = new ArrayList<>();
                ArrayList<String> info1 = new ArrayList<>();
                ArrayList<String> time1 = new ArrayList<>();
                String time, type, info;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Log.e(TAG, "onDataChange ------>: " + data.getValue());
                    Log.e(TAG, "onDataChange: " + data.child("type").getValue());
                    type = data.child("type").getValue().toString();
                    info = data.child("info").getValue().toString();
                    time = data.child("time").getValue().toString();
                    info1.add(info);
                    type1.add(type);
                    time1.add(time);
                }
                ChatScreenAdapterInside nob = new ChatScreenAdapterInside(type1, info1, time1);
                recyclerView.setAdapter(nob);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        progressDialog.dismiss();
    }
}
