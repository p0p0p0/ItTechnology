package com.safetyhuge.chanlian.safetyhuge.fragment.RelesaseFragmentInfo;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.IssueProjectActivity;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.SharedPrefsUtil;
import com.socks.library.KLog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil.getMapForJson;

/**
 * 单人悬赏
 * 作者：王海宾 on 2017/4/1 0001 10:26
 * 邮箱：995696826@qq.com
 */

public class IssueTaskFragment extends Fragment {
    @Bind(R.id.relesase1_et)
    EditText mRelesase1Et;
    @Bind(R.id.relesase1_item_text)
    TextView mRelesase1ItemText;
    private String mTime;
    private Context mContext;
    private String mS;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_relesase_single, container, false);
        ButterKnife.bind(this, view);
        String  id = (String) SharedPrefsUtil.get(ECApplication.context,"xs01","");
        KLog.e("ididid",id);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.relesase1_item_finder, R.id.release1_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relesase1_item_finder:
                //时间选择器
                InputMethodManager imm = (InputMethodManager) ECApplication.context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                TimePickerView pvTime = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date1, View v) {//选中事件回调
                        KLog.e("date", date1);
                        // 转换为24小时制式的字串
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        mTime = df.format(date1);
                        mRelesase1ItemText.setText(mTime);
                    }
                }).setType(TimePickerView.Type.YEAR_MONTH_DAY)//默认全部显示
                        .setLabel("年", "月", "日", "", "", "")
                        .setCancelText("取消")
                        .setSubmitText("确认")
                        .build();
                pvTime.show();
                break;
            case R.id.release1_next:
                mS = mRelesase1Et.getText().toString();
                if (mS.isEmpty()) {
                    Toast.makeText(ECApplication.context, "请输入预算", Toast.LENGTH_SHORT).show();
                } else if (mS.equals("0")) {
                    Toast.makeText(ECApplication.context, "金额不能为0", Toast.LENGTH_SHORT).show();
                } else if (mTime == null) {
                    Toast.makeText(ECApplication.context, "请选择日期", Toast.LENGTH_SHORT).show();
                } else {
                    next(mS, mTime);
                }
                break;
        }
    }
    private void next(String cash, String day) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "NextSrewardTaskSept1");
        hashMap.put("txt_task_cash", cash);
        hashMap.put("txt_task_day", day);
        KLog.e("cash",cash);
        KLog.e("day",day);
        OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params(hashMap).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                KLog.json(s);
                Map<String, Object> map = getMapForJson(s);
                String error = (String) map.get("error");
                String code = (String) map.get("code");
                if (code.equals("888")) {
                    mContext = getActivity();
                    Intent intent = new Intent(mContext, IssueProjectActivity.class);
                    intent.putExtra("yusuan",mS);
                    intent.putExtra("time",mTime);
                    intent.putExtra("action","NextSrewardTaskSept2");
                    intent.putExtra("flag",1);
                    mContext.startActivity(intent);
                } else {
                    KLog.e("error", error);
                    Toast.makeText(ECApplication.context, error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
