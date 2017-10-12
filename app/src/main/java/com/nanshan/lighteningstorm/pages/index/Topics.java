package com.nanshan.lighteningstorm.pages.index;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import com.nanshan.lighteningstorm.R;
import com.nanshan.lighteningstorm.pages.general.BaseActivity;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class Topics extends BaseActivity {

    @BindView(R.id.october_topic)
    TextView octoberTopic;

    @Override
    public int getLayout() {
        return R.layout.activity_topics;
    }

    @Override
    public void initializing(Bundle savedInstanceState) {

        List<String> topics = new ArrayList<>();
        topics.add("transfer Java to Kotlin\n");
        topics.add("python scrawler\n");

        String octoberContent = "";
        for (String item : topics){
            octoberContent += item;
        }
        SpannableString spannableString = new SpannableString(octoberContent);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(R.color.color_grass_green));
        spannableString.setSpan(colorSpan, 0, topics.get(0).length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        spannableString.setSpan(new ClickableSpan() {
//
//            @Override
//            public void updateDrawState(TextPaint ds) {
//                ds.bgColor = getResources().getColor(R.color.transparent);
////                ds.setColor(getResources().getColor(R.color.text_view_color));
//                ds.setUnderlineText(false);
//            }
//
//            @Override
//            public void onClick(View widget) {
//                CommonUtil.toast("python -> scrawler -> data analysis visualization -> money");
//            }
//        }, topics.get(0).length(), octoberContent.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//        octoberTopic.setMovementMethod(LinkMovementMethod.getInstance());
        octoberTopic.setText(spannableString);

    }

    @OnClick(R.id.cardview_dts)
    public void cl_cardview_dts(){

    }


}
