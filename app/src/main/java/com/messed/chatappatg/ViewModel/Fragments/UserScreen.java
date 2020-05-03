package com.messed.chatappatg.ViewModel.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
import com.messed.chatappatg.R;
import com.messed.chatappatg.View.UserScreenAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


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
        // Required empty public constructor
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
        database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(getActivity());
        final ArrayList<String> userinfo = new ArrayList<>();
        final ArrayList<String> userkey = new ArrayList<>();
        progressDialog.setMessage("Loading Contacts");
        progressDialog.show();
        reference = database.getReference().child(firebaseAuth.getUid()).child("friends");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String s;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    for (DataSnapshot dataSnapshot1 : data.getChildren()) {
                        s = dataSnapshot1.getKey();
                        userinfo.add(s);
                        userkey.add(dataSnapshot1.getValue().toString());
                        Log.e(TAG, "onDataChange: " + dataSnapshot1.getKey());
                    }
                }
                UserScreenAdapter nob = new UserScreenAdapter(userinfo, getActivity(), userkey);
                progressDialog.dismiss();
                recyclerView.setAdapter(nob);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        String currentDateTimeString = sdf.format(d);
        Log.e(TAG, "onCreateView: " + currentDateTimeString);
        getSelfName();
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
}
/* --------------------------My code to send temporay data please ignore----------------------------------------------------
        String key1="CjkIhPnG8dO6eWrUN2yAnI00Rjx2";//amar
        String key2="ARInkkUHPDcQ90OBRCUGwenrzs12";//birbal
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("Akbar",key1);
        hashMap.put("amar",key2);
        reference=database.getReference().child(firebaseAuth.getUid()).child("friends");
        reference.push().setValue(hashMap);*/