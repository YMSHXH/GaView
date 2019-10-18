package com.ysm.gaview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    String string = "拉拉裤DNF老妇女卢萨卡肺宁颗粒阿道夫辣椒粉拉开发你的是" +
            "否能拉水电费可能的水立方刊发哪里哪里能发了啥都能发了啥快递费你" +
            "死定了分内的事弗兰克斯地方都死了电缆的法律的看法年历史的看你发" +
            "的离开电脑撒发了肯定是哪里发你的斯洛伐克第三方哪里圣诞款付了款" +
            "否能拉水电费可能的水立方刊发哪里哪里能发了啥都能发了啥快递费你" +
            "死定了分内的事弗兰克斯地方都死了电缆的法律的看法年历史的看你发" +
            "的离开电脑撒发了肯定是哪里发你的斯洛伐克第三方哪里圣诞款付了款" +
            "否能拉水电费可能的水立方刊发哪里哪里能发了啥都能发了啥快递费你" +
            "死定了分内的事弗兰克斯地方都死了电缆的法律的看法年历史的看你发" +
            "的离开电脑撒发了肯定是哪里发你的斯洛伐克第三方哪里圣诞款付了款" +
            "否能拉水电费可能的水立方刊发哪里哪里能发了啥都能发了啥快递费你" +
            "死定了分内的事弗兰克斯地方都死了电缆的法律的看法年历史的看你发" +
            "的离开电脑撒发了肯定是哪里发你的斯洛伐克第三方哪里圣诞款付了款" +
            "都是哪里放开那都是浪费";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        FrameLayout frame = findViewById(R.id.frame);

        View recoverView = LayoutInflater.from(Main2Activity.this).inflate(R.layout.view_new, null);
        View view1 = LayoutInflater.from(Main2Activity.this).inflate(R.layout.view_new, null);
        View view2 = LayoutInflater.from(Main2Activity.this).inflate(R.layout.view_new, null);


        TextView text1 = recoverView.findViewById(R.id.textview);
        TextView text2 = view1.findViewById(R.id.textview);
        TextView text3 = view2.findViewById(R.id.textview);


        text1.setText(string+"\n111");
        text2.setText(string+"\n222");
        text3.setText(string+"\n333");


        frame.addView(recoverView);
        frame.addView(view1);
        frame.addView(view2);

    }
}
