package com.safetyhuge.chanlian.safetyhuge.fragment.RelesaseFragmentInfo;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.IssueProjectActivity;
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
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
 * 多人悬赏
 * 作者：王海宾 on 2017/4/1 0001 11:40
 * 邮箱：995696826@qq.com
 */

public class PluralTaskFragment extends Fragment {
    @Bind(R.id.relesase2_et)
    EditText mRelesase2Et;
    @Bind(R.id.release2_layout1)
    LinearLayout mRelease2Layout1;
    @Bind(R.id.release2_layout2)
    LinearLayout mRelease2Layout2;
    @Bind(R.id.relesase2_item_text_button)
    TextView mRelesase2ItemTextButton;
    @Bind(R.id.release2_next)
    Button mRelease2Next;
    @Bind(R.id.release2_et1)
    EditText mRelease2Et1;
    @Bind(R.id.release2s_et1)
    EditText mRelease2sEt1;
    @Bind(R.id.release2_et2)
    EditText mRelease2Et2;
    @Bind(R.id.release2s_et2)
    EditText mRelease2sEt2;
    @Bind(R.id.release2_et3)
    EditText mRelease2Et3;
    @Bind(R.id.release2s_et3)
    EditText mRelease2sEt3;
    private String mTime;
    private int mCount = 0;
    private String mS,mS1,mSs1,mS2,mSs2,mS3,mSs3;
    Context mContext;
    boolean  b = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_relesase_repeatedly, container, false);
        ButterKnife.bind(this, view);
        InitView();
        return view;
    }

    private void InitView() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.repeateriy_button1, R.id.repeateriy_button2, R.id.repeateriy_button3, R.id.relesase2_item_finder, R.id.release2_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.repeateriy_button1:
                if (mCount == 0) {
                    mRelease2Layout1.setVisibility(View.VISIBLE);
                } else {
                    b=true;
                    mRelease2Layout2.setVisibility(View.VISIBLE);
                }
                mCount++;
                break;
            case R.id.repeateriy_button2:
                if (b==true){
                    mRelease2Et3.setText("");
                    mRelease2sEt3.setText("");
                    mRelease2Layout2.setVisibility(View.GONE);
                    b=false;
                }else{
                    mRelease2Et2.setText("");
                    mRelease2sEt2.setText("");
                    mRelease2Layout1.setVisibility(View.GONE);
                    mCount = 0;
                }
                break;
            case R.id.repeateriy_button3:
                mRelease2Et3.setText("");
                mRelease2sEt3.setText("");
                mCount = 0;
                mRelease2Layout2.setVisibility(View.GONE);
                b=false;
                break;
            case R.id.relesase2_item_finder:
                InputMethodManager imm = (InputMethodManager) ECApplication.context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                //时间选择器
                TimePickerView pvTime = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date1, View v) {//选中事件回调
                        KLog.e("date", date1);
                        // 转换为24小时制式的字串
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        mTime = df.format(date1);
                        mRelesase2ItemTextButton.setText(mTime);
                    }
                }).setType(TimePickerView.Type.YEAR_MONTH_DAY)//默认全部显示
                        .setLabel("年", "月", "日", "", "", "")
                        .setCancelText("取消")
                        .setSubmitText("确认")
                        .build();
                pvTime.show();
                break;
            case R.id.release2_next:
                //预算
                mS = mRelesase2Et.getText().toString();
                //一等奖名次 赏金
                mS1 = mRelease2Et1.getText().toString();
                mSs1 = mRelease2sEt1.getText().toString();
                //二等奖名次 赏金
                mS2 = mRelease2Et2.getText().toString();
                mSs2 = mRelease2sEt2.getText().toString();
                //三等奖名次 赏金
                mS3 = mRelease2Et3.getText().toString();
                mSs3 = mRelease2sEt3.getText().toString();
                if (!mS.isEmpty()) {
                    if (!mS.equals("0")) {
                        if (!mS1.isEmpty() || !mSs1.isEmpty()) {
                            if (!mS1.isEmpty()) {
                                if (!mS1.equals("0")) {
                                    if (!mSs1.isEmpty()) {
                                        if (!mSs1.equals("0")) {
                                            if (mRelease2Layout1.getVisibility() == View.VISIBLE) {
                                                KLog.e("二等奖");
                                                if (!mS2.isEmpty() || !mSs2.isEmpty()) {
                                                    if (!mS2.isEmpty()) {
                                                        if (!mS2.equals("0")) {
                                                            if (!mSs2.isEmpty()) {
                                                                if (!mSs2.equals("0")) {
                                                                    if (mRelease2Layout2.getVisibility() == View.VISIBLE) {
                                                                        KLog.e("三等奖");
                                                                        if (!mS3.isEmpty() || !mSs3.isEmpty()) {
                                                                            if (!mS3.isEmpty()) {
                                                                                if (!mS3.equals("0")) {
                                                                                    if (!mSs3.isEmpty()) {
                                                                                        if (!mSs3.equals("0")) {
                                                                                            if (mTime != null) {
                                                                                                //请求网络
                                                                                                    next(mS,mTime,mS1,mSs1,mS2,mSs2,mS3,mSs3);
                                                                                            } else {
                                                                                                Toast.makeText(ECApplication.context, "请选择日期", Toast.LENGTH_SHORT).show();
                                                                                            }
                                                                                        } else {
                                                                                            Toast.makeText(ECApplication.context, "赏金不能0", Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    } else {
                                                                                        Toast.makeText(ECApplication.context, "赏金不能为空", Toast.LENGTH_SHORT).show();
                                                                                    }
                                                                                } else {
                                                                                    Toast.makeText(ECApplication.context, "名次不能为0", Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            } else {
                                                                                Toast.makeText(ECApplication.context, "名次不能为空", Toast.LENGTH_SHORT).show();
                                                                            }
                                                                        } else {
                                                                            Toast.makeText(ECApplication.context, "请输入名次和赏金", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    } else {
                                                                        if (mTime != null) {
                                                                            //请求网络
                                                                            next(mS,mTime,mS1,mSs1,mS2,mSs2,"","");
                                                                        } else {
                                                                            Toast.makeText(ECApplication.context, "请选择日期", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    }
                                                                } else {
                                                                    Toast.makeText(ECApplication.context, "赏金不能为0", Toast.LENGTH_SHORT).show();
                                                                }
                                                            } else {
                                                                Toast.makeText(ECApplication.context, "赏金不能为空", Toast.LENGTH_SHORT).show();
                                                            }
                                                        } else {
                                                            Toast.makeText(ECApplication.context, "名次不能为0", Toast.LENGTH_SHORT).show();
                                                        }
                                                    } else {
                                                        Toast.makeText(ECApplication.context, "名次不能为空", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    Toast.makeText(ECApplication.context, "请输入名次和赏金", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                if (mTime != null) {
                                                    //请求网络
                                                     next(mS,mTime,mS1,mSs1,"","","","");
                                                } else {
                                                    Toast.makeText(ECApplication.context, "请选择日期", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        } else {
                                            Toast.makeText(ECApplication.context, "赏金不能为0", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(ECApplication.context, "赏金不能为空", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(ECApplication.context, "名次不能为0", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(ECApplication.context, "名次不能为空", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ECApplication.context, "请输入名次和赏金", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ECApplication.context, "金额不能为0", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ECApplication.context, "请输入预算", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void next(String cash, String day, final String prize, final String money, final String prize1, final String money1, final String prize2, final String money2 ) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "NextMrewardTaskSept1");
        hashMap.put("txt_task_cash", cash);
        hashMap.put("txt_task_day", day);
        //一等奖
        hashMap.put("txt_prize1_cash", money);
        hashMap.put("txt_prize1_num", prize);
        //二等奖
        hashMap.put("txt_prize2_cash", money1);
        hashMap.put("txt_prize2_num", prize1);
        //三等奖
        hashMap.put("txt_prize3_cash", money2);
        hashMap.put("txt_prize3_num", prize2);

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
                    intent.putExtra("flag",2);

                    intent.putExtra("txt_prize1_cash",money);
                    intent.putExtra("txt_prize1_num",prize);

                    intent.putExtra("txt_prize2_cash",money1);
                    intent.putExtra("txt_prize2_num",prize1);

                    intent.putExtra("txt_prize3_cash",money2);
                    intent.putExtra("txt_prize3_num",prize2);

                    intent.putExtra("action","NextMrewardTaskSept2");
                    mContext.startActivity(intent);
                } else{
                    KLog.e("error", error);
                    Toast.makeText(ECApplication.context, error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
