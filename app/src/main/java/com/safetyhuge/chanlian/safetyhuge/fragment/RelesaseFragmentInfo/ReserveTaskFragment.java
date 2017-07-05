package com.safetyhuge.chanlian.safetyhuge.fragment.RelesaseFragmentInfo;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
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
import com.safetyhuge.chanlian.ECApplication;
import com.safetyhuge.chanlian.safetyhuge.Bean.MoneySectionBean;
import com.safetyhuge.chanlian.safetyhuge.R;
import com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.IssueProjectActivity;
import com.safetyhuge.chanlian.safetyhuge.http.JsonCallback;
import com.safetyhuge.chanlian.safetyhuge.http.RequestAddress;
import com.safetyhuge.chanlian.safetyhuge.utils.PopupUtils;
import com.socks.library.KLog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.safetyhuge.chanlian.ECApplication.context;
import static com.safetyhuge.chanlian.safetyhuge.utils.JSONUtil.getMapForJson;
import static com.safetyhuge.chanlian.safetyhuge.utils.PopupUtils.QJid;

/**
 * 定金招标
 * 作者：王海宾 on 2017/4/1 0001 15:50
 * 邮箱：995696826@qq.com
 */

public class ReserveTaskFragment extends Fragment {
    @Bind(R.id.release3_text_none)
    TextView mRelease3TextNone;
    @Bind(R.id.release3_et)
    EditText mRelease3Et;
    @Bind(R.id.release3_cut)
    TextView mRelease3Cut;
    @Bind(R.id.relesase3_item_text)
    TextView mRelesase3ItemText;
    @Bind(R.id.release3_none)
    LinearLayout mRelease3None;
    @Bind(R.id.release3_have)
    LinearLayout mRelease3Have;
    @Bind(R.id.relesase3_item_finder)
    LinearLayout mRelesase3ItemFinder;
    @Bind(R.id.release3_next)
    Button mRelease3Next;
    boolean flag = true;
    @Bind(R.id.release3_pop)
    LinearLayout mRelease3Pop;
    private List<MoneySectionBean.DataBean> mDataBeen;
    private ArrayList<String> mArrayList;
    private ArrayList<String> mArrayList1;
    String mTime;
    private String mVid;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_relesase_common, container, false);
        ButterKnife.bind(this, view);
        mContext = getActivity();
        mRelease3Cut.setText("我有明确的预算");
        mRelease3Cut.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        NetWork();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.release3_next, R.id.release3_none, R.id.release3_have, R.id.release3_cut, R.id.relesase3_item_finder})
    public void onClick(View view) {
        switch (view.getId()) {
            //选择预算区间
            case R.id.release3_none:
                PopupUtils.initSelectPopup(mContext, mArrayList, null, null, null, null, null, mArrayList1, mRelease3TextNone, mRelease3Pop).showAsDropDown(mRelease3Pop, 0, 10);
                break;
            //明确预算区间
            case R.id.release3_have:
                break;
            //切换
            case R.id.release3_cut:
                if (flag) {
                    mRelease3None.setVisibility(View.GONE);
                    mRelease3Have.setVisibility(View.VISIBLE);
                    mRelease3Cut.setText("我没有明确的预算");
                    mRelease3Cut.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                } else {
                    mRelease3Cut.setText("我有明确的预算");
                    mRelease3Have.setVisibility(View.GONE);
                    mRelease3None.setVisibility(View.VISIBLE);
                    mRelease3Cut.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                }
                flag = !flag;
                break;
            //时间选择器
            case R.id.relesase3_item_finder:
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
                        mRelesase3ItemText.setText(mTime);
                    }
                }).setType(TimePickerView.Type.YEAR_MONTH_DAY)//默认全部显示
                        .setLabel("年", "月", "日", "", "", "")
                        .setCancelText("取消")
                        .setSubmitText("确认")
                        .build();
                pvTime.show();
                break;
            //下一步
            case R.id.release3_next:
                String s = mRelease3TextNone.getText().toString();
                String s1 = mRelease3Et.getText().toString();
                if (mRelease3None.getVisibility() == View.VISIBLE) {
                    if (!s.equals("请选择预算区间")) {
                        if (mTime != null) {
                         /*   mVid = (String) SharedPrefsUtil.get(ECApplication.context, "aMidid", "");
                            KLog.e("mid", mVid);*/
                            Next(QJid, mTime, "1", "");
                        } else {
                            Toast.makeText(context, "请选择日期", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "预算不能为空", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (!s1.isEmpty()) {
                        if (!s1.equals("2")) {
                            if (mTime != null) {
                                Next("", mTime, "2", s1);
                            } else {
                                Toast.makeText(context, "请选择日期", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(context, "金额不能为0", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "请输入预算", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    public void NetWork() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "GetBudgetInfo");
        hashMap.put("model_id", "3");
        OkGo.post(RequestAddress.HOST + RequestAddress.WCYDRW).params(hashMap).execute(new JsonCallback<MoneySectionBean>() {
            @Override
            public void onSuccess(MoneySectionBean moneySectionBean, Call call, Response response) {
                mDataBeen = moneySectionBean.getData();
                mArrayList = new ArrayList<>();
                mArrayList1 = new ArrayList<>();
                for (int i = 0; i < mDataBeen.size(); i++) {
                    mArrayList.add(mDataBeen.get(i).getName());
                    mArrayList1.add(mDataBeen.get(i).getId());
                    KLog.e("money", mDataBeen.get(i).getName() + "-----" + mDataBeen.get(i).getId());
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        QJid="";
    }

    public void Next(final String cove, final String day, final String radio, final String budget) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "NextDtenderTaskSept1");
        hashMap.put("task_cash_cove", cove);
        hashMap.put("txt_task_day", day);
        hashMap.put("budget_radio", radio);
        hashMap.put("txt_budget", budget);
        KLog.e("hashMap",hashMap.toString());
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
                    intent.putExtra("yusuan", budget);
                    intent.putExtra("time", day);
                    intent.putExtra("task_cash_cove", cove);
                    intent.putExtra("budget_radio", radio);
                    intent.putExtra("action", "NextDtenderTaskSept2");
                    intent.putExtra("flag", 4);
                    mContext.startActivity(intent);
                } else {
                    KLog.e("error", error);
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
