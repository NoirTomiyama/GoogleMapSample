package com.example.tommy.mapsapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // 問題を管理するリスト
    private ArrayList<Question> question_list = new ArrayList<>();
    // 描画更新用Handler
    private Handler handler;
    // 1問あたりの制限時間(sec)
    private int time = 10;
    // 問題数
    private int question_num;
    // 現在の問題
    private Question current_question = null;
    // 残り時間を表示するプログレスバー
    private ProgressBar progress;
    // 残りの制限時間(sec*10)
    private int rest_time;
    // 現在の問題番号
    private int current = 0;
    // 正解を選んだ数
    private int correct_num;

    // TODO [01] ここから
    // TODO [01] ここまで

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();

        // TODO [02]ここから
        // TODO [02] ここまで
    }

    // 問題を作成する
    private void makeQuestions() {
        // TODO [03] ここから
        // TODO [03] ここまで
    }

    // 問題の表示を始める
    private void startQuestion() {
        question_num = question_list.size();
        nextQuestion();
    }

    private void nextQuestion() {
        if (current >= question_num) {
            current = -1;
            // 次の問題がもう無い時
            // 結果画面に移動
            Intent i = new Intent(this, ResultActivity.class);
            i.putExtra("QUESTION", question_num);
            i.putExtra("CORRECT", correct_num);
            startActivity(i);
            // そのままにしておくと画面が積み重なっていくので終了させる
            finish();
            return;
        }
        // TODO [04] ここから
        // TODO [04] ここまで
    }

    // ボタンがタップされた時に呼ばれるイベントリスナー
    public void click(View v) {
        // TODO [05] ここから
        // TODO [05] ここまで
    }

    /**
     * 1問ごとの制限時間を管理するスレッドを起動する
     */
    private void startTimeLimitThread() {
        rest_time = time * 100;
        progress.setProgress(rest_time);
        // このThreadが担当する問題番号
        final int local_current = current;
        Thread t = new Thread() {
            @Override
            public void run() {
                while (rest_time >= 0) {
                    if (local_current != current) {
                        // すでにボタンをタップして次の問題に進んでいる
                        return;
                    }
                    try {
                        // 10ミリ秒待機
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            rest_time -= 1;
                            progress.setProgress(rest_time);
                        }
                    });
                }
                // まだ問題に解答していない場合
                if (local_current == current) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            nextQuestion();
                        }
                    });
                }
            }
        };
        t.start();
    }

    /**
     * 問題を管理するクラス
     */
    class Question {
        /**
         * 画面に表示する画像のリソースID
         */
        private final int image_id;
        /**
         * 問題文として表示する文字列
         */
        private final String question_text;
        /**
         * 正解の答え
         */
        private final String answer;
        /**
         * 不正解の答え①
         */
        private final String wrong_1;
        /**
         * 不正解の答え②
         */
        private final String wrong_2;

        public Question(int image_id, String question_text, String answer, String wrong_1, String wrong_2) {
            this.image_id = image_id;
            this.question_text = question_text;
            this.answer = answer;
            this.wrong_1 = wrong_1;
            this.wrong_2 = wrong_2;
        }

        /**
         * シャッフルした問題の選択肢を返すメソッド
         */
        public String[] getChoices() {
            // ボタンの位置をランダムにする
            String choices_tmp[] = {answer, wrong_1, wrong_2};
            // 配列からリストへ
            List<String> list = Arrays.asList(choices_tmp);
            // リストをシャッフル
            Collections.shuffle(list);
            // リストをStringの配列にする
            return list.toArray(new String[3]);
        }
    }
}
