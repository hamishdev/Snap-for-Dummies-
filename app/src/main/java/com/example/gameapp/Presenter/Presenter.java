package com.example.gameapp.Presenter;

import java.io.Serializable;

public interface Presenter extends Serializable {

    void onCreate();
    void onPause();
    void onResume();
    void onDestroy();
}
