package com.messed.chatappatg.ViewModel.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.messed.chatappatg.ViewModel.Activities.GroupChatActivity;
import com.messed.chatappatg.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatScreen extends Fragment {

    FirebaseAuth firebaseAuth;
    DatabaseReference reference;
    FirebaseDatabase database;
    static final String TAG = "ChatScreen";
    CardView cardView;

    public ChatScreen() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_screen, container, false);
        database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        cardView = view.findViewById(R.id.cardview1);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GroupChatActivity.class));
            }
        });
        return view;
    }
}
