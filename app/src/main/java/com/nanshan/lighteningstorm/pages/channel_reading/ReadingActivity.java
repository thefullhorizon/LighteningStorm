package com.nanshan.lighteningstorm.pages.channel_reading;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.nanshan.lighteningstorm.R;
import com.nanshan.lighteningstorm.pages.general.BaseActivity;

import butterknife.BindView;

public class ReadingActivity extends BaseActivity {

    @BindView(R.id.my_library)
    TextView myLibrary;

    @Override
    public int getLayout() {
        return R.layout.activity_reading;
    }

    @Override
    public void initializing(Bundle savedInstanceState) {

        myLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ReadingActivity.this);
                builder.setTitle("藏经阁")
                        .setMessage("2018年度阅读规划\n" +

                                "《怪诞心理学》\n" +
                                "《行为心理学》\n" +

                                "《如何有效阅读一本书》\n" +
                                "《如何高效学习》\n"+
                                "《好好学习》\n"+

                                "《好好说话》\n"+
                                "《学会提问》\n"+
                                "《关键对话》\n")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                builder.create().show();
            }
        });

    }


}
