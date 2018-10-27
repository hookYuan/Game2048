package com.yuanye.gameapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yuanye.gameapplication.draw.GameView;

/**
 * 游戏界面
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private GameView gameView;
    private TextView tvScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameView = (GameView) findViewById(R.id.gameView);
        tvScore = (TextView) findViewById(R.id.tv_score);
        gameView.setOnStepListener(new GameView.OnStepListener() {
            @Override
            public void onStep() {
                tvScore.setText(gameView.getScore() + "   分");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_reStart: //重新开始游戏
                gameView.startGame();
                tvScore.setText("0  分");
                break;
        }
    }
}
