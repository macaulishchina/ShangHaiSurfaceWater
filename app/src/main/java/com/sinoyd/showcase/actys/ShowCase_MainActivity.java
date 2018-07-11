package com.sinoyd.showcase.actys;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sinoyd.R;
import com.sinoyd.frame.actys.SinoBaseActivity;
import com.sinoyd.frame.util.ToastUtil;

import butterknife.InjectView;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * 作者： 王一凡
 * 创建时间： 2017/12/13
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.showcase.actys
 */
public class ShowCase_MainActivity extends SinoBaseActivity {
    @InjectView(R.id.lv)
    ListView lv;

    private long exitTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setLayout(R.layout.sc_main_activity);

        openSlideFinish(false);

        initView();
    }

    private void initView(){
        getNbBar().setNBTitle("ShowCase");//设置标题文字
        getNbBar().nbBack.setVisibility(View.GONE);//将默认左侧返回箭头隐藏

        String[] titles =getResources().getStringArray(R.array.showcase_unit);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, titles);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position){
                    case 0:
                        intent = new Intent(getContext(), ShowCase_PagingListDemo.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getContext(), ShowCase_SwipeListDemo.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getContext(), ShowCase_SegmentedDemo.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(getContext(), ShowCase_ViewPageDemo.class);
                        startActivity(intent);
                        break;

                    default:
                        ToastUtil.showWorning(ShowCase_MainActivity.this,"建设中...");
                        break;
                }
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ExitApp();
        }
        return false;
    }

    public void ExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
//            ToastUtil.showShort(this, "再按一次退出程序");
            Toast.makeText(this,"再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            ShowCase_InitActivity.activity.finish();
            finish();
        }

    }
}
