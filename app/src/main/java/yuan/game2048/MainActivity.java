package yuan.game2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import yuan.game2048.draw.GameView;

/**
 * 描述：游戏界面
 *
 * @author yuanye
 * @date 2019/7/23 14:02
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
                tvScore.setText("获得" + gameView.getScore() + "   分");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_reStart: //重新开始游戏
                gameView.startGame();
                tvScore.setText("获得 0  分");
                break;
        }
    }
}
