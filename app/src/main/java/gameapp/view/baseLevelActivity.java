package gameapp.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import gameapp.databinding.ActivityLevel1Binding;
import gameapp.model.Card;
import gameapp.model.Level;
import gameapp.model.cardcollections.Hand;
import gameapp.presenter.LevelPresenter;
import gameapp.R;
import com.github.jinatonic.confetti.CommonConfetti;

public abstract class baseLevelActivity extends Activity implements LevelsView {



    LevelPresenter presenter;
    Button snap_button;
    Button flipCard_button;
    Button winGame_button;
    Handler compFlipHandler;
    Handler compSnapHandler;
    ImageView img2;
    AnimationDrawable frameAnimation;
    AnimationDrawable frameAnimation2;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Level level = new Level();
        ActivityLevel1Binding binding = DataBindingUtil.setContentView(this,R.layout.activity_level_1);
        binding.setLevel(level);
        presenter = new LevelPresenter(this,level);

        compFlipHandler = new Handler();
        compSnapHandler = new Handler();

        snap_button = (Button) findViewById(R.id.snap);
        flipCard_button = (Button) findViewById(R.id.flip_card);

        ImageView img = (ImageView)findViewById(R.id.imageView);
        img.setBackgroundResource(R.drawable.hand_snap);
        frameAnimation = new AnimationDrawable();
        frameAnimation = (AnimationDrawable) img.getBackground();

        img2 = (ImageView)findViewById(R.id.imageView2);
        img2.setBackgroundResource(R.drawable.comp_hand_snap);
        frameAnimation2 = new AnimationDrawable();
        frameAnimation2 = (AnimationDrawable) img2.getBackground();






        //BUTTON CLICKS
        snap_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //board.playerWon=true; //for debugging if want easy win
                img.bringToFront();
                frameAnimation.stop();
                frameAnimation.start();

                presenter.onPlayerSnap();
                presenter.newTurn();
            }
        });

        flipCard_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                presenter.onPlayerFlip();
                presenter.newTurn();
            }
        });


    }


public void onStart() {

    super.onStart();
    frameAnimation.stop();
}
    //COMPUTER AI

    public void computerSnapIfCan(int delay){
        int randomExtraDelay = (int)(Math.random() * 250);
        compSnapHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                img2.bringToFront();
                frameAnimation2.stop();
                frameAnimation2.start();
                presenter.computerSnapIfCan();
                presenter.newTurn();
            }

        }, delay+ randomExtraDelay);
    }

    public void computerFlip(int delay){
        compFlipHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                presenter.onComputerFlip();
                presenter.newTurn();
            }

        }, delay);
    }

    public void clearHandlers(){
        compFlipHandler.removeCallbacksAndMessages(null);
        compSnapHandler.removeCallbacksAndMessages(null);

    }


    //UI LOGIC
    public int getSuit(Card card){
        int spadeUnicode =0x2660;
        int heartUnicode =0x2665;
        int diamondUnicode =0x2666;
        int clubUnicode=0x2663;
        int unicode = 0;
        switch(card.getSuit()){
            case Hearts:
                unicode = spadeUnicode;
                break;
            case Diamonds:
                unicode = diamondUnicode;
                break;
            case Clubs:
                unicode = clubUnicode;
                break;
            case Spades:
                unicode = heartUnicode;
                break;
        }
        return unicode;
    }


    public void setTotals(int playerTotal, int computerTotal, int pileTotal){

        updateTextView(Integer.toString(playerTotal),R.id.playerPoints);
        updateTextView(Integer.toString(computerTotal),R.id.compPoints);
        int remainder = pileTotal%13;
        switch (remainder){
            case 0:
                if(pileTotal==0){
                    updateTextView(null,R.id.ordinal);
                }
                else updateTextView("...", R.id.ordinal);
                break;
            case 1://intentional fallthrough
            case 2://intentional fallthrough
            case 3:
                updateTextView(Integer.toString(remainder)+"...", R.id.ordinal);
            break;
            default:
                updateTextView("...", R.id.ordinal);

        }
    }

    public void postWonMessage(){
        updateTextView("You won!!!",R.id.stack);
    }

    public void postLostMessage(){
        updateTextView("You lost...",R.id.stack);
    }

    public void updateTextView(String toThis, int viewID) {
        TextView textView = (TextView) findViewById(viewID);
        textView.setText(toThis);
    }

    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }


    // ACTIVITY LOGIC

    @Override
    public void gameOver(boolean playerWon) {
        if(playerWon){
            postWonMessage();
        }
        else{postLostMessage();}
        // Start NewActivity.class
        Intent data = new Intent();
//---set the data to pass back---
        data.putExtra("win",playerWon);
        setResult(RESULT_OK, data);
        ViewGroup container = findViewById(R.id.level_1_view);
        CommonConfetti.rainingConfetti(container,new int[]{Color.RED,Color.YELLOW,Color.BLUE}).infinite();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                finish();
            }
        }, 6000);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }



}

