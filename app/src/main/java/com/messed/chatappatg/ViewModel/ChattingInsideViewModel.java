package com.messed.chatappatg.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.messed.chatappatg.Model.ChatInside;
import com.messed.chatappatg.View.Acitivites.ChattingInside;

import java.util.List;

public class ChattingInsideViewModel extends ViewModel {
    private static final String TAG = "CHattinginsideviewmodel";
    ChattingInsideResponse chattingInsideResponse;
    ChattingInsideResponse groupchattingInsideResponse;
    LiveData<List<ChatInside>> liveData;
    LiveData<List<ChatInside>> grouplivedata;
    String user;

    //-------------------------------single user chat---------------------------------------
    public ChattingInsideViewModel(String user) {
        this.user = user;
        chattingInsideResponse = new ChattingInsideResponse();
    }

    //--------------------group chat constructor------------------------
    public ChattingInsideViewModel() {
        groupchattingInsideResponse = new ChattingInsideResponse();
    }

    public LiveData<List<ChatInside>> getChats() {
        if (liveData == null) {
            liveData = chattingInsideResponse.requestChatMessage(user);
        }
        return liveData;
    }

    //---------------group----------------------------------------------------------
    public LiveData<List<ChatInside>> getGroupChats() {
        if (grouplivedata == null) {
            grouplivedata = groupchattingInsideResponse.requestGroupChatMessage();
        }
        return grouplivedata;
    }
}
