package com.messed.chatappatg.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.messed.chatappatg.Model.UserChatList;

import java.util.List;

public class UserChatListViewModel extends ViewModel {

    UserChatListResponse userChatListResponse;
    LiveData<List<UserChatList>> liveData;

    public UserChatListViewModel()
    {
        userChatListResponse =new UserChatListResponse();
    }
    public LiveData<List<UserChatList>> getChatList()
    {
        if(liveData==null)
        {
            liveData= userChatListResponse.requestUserChatList();
        }
        return liveData;
    }
}
