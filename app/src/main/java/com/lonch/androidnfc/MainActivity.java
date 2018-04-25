package com.lonch.androidnfc;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lonch.androidnfc.activity.ReadMUActivity;
import com.lonch.androidnfc.activity.ReadTextActivity;
import com.lonch.androidnfc.activity.ReadUriActivity;
import com.lonch.androidnfc.activity.RunAppActivity;
import com.lonch.androidnfc.activity.RunUrlActivity;
import com.lonch.androidnfc.activity.WriteMUActivity;
import com.lonch.androidnfc.activity.WriteTextActivity;
import com.lonch.androidnfc.activity.WriteUriActivity;

import butterknife.BindView;

/**
 * Author:Created by GodofSwond on 2018/4/25.
 */
public class MainActivity extends AppCompatActivity {
    private NfcAdapter mNfcAdapter;

    private static final String[] str = new String[]{
            "自动打开短信界面",
            "自动打开百度页面",
            "读NFC标签中的文本数据",
            "写NFC标签中的文本数据",
            "读NFC标签中的Uri数据",
            "写NFC标签中的Uri数据",
            "读NFC标签非NDEF格式的数据",
            "写NFC标签非NDEF格式的数据"
    };

    @BindView(R.id.ifo_NFC)
    TextView ifo_NFC;
    @BindView(R.id.listview)
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获取默认的NFC控制器,NFC适配器,所有的关于NFC的操作从该适配器进行
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (!ifNFCUse()) {
            return;
        }
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, str));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switchActivity(position);
            }
        });
    }

    /**
     * 检测工作,判断设备是否支持NFC
     *
     * @return
     */
    protected Boolean ifNFCUse() {
        if (mNfcAdapter == null) {
            ifo_NFC.setText("该设备暂不支持NFC！");
            finish();
            return false;
        }
        if (mNfcAdapter != null && !mNfcAdapter.isEnabled()) {
            ifo_NFC.setText("请在系统设置中先启用NFC功能！");
            return false;
        }
        return true;
    }

    private void switchActivity(int position) {
        switch (position) {
            case 0: //自动打开短信界面
                startActivity(new Intent(this, RunAppActivity.class));
                break;
            case 1: //自动打开百度页面
                startActivity(new Intent(this, RunUrlActivity.class));
                break;
            case 2: //读NFC标签中的文本数据
                startActivity(new Intent(this, ReadTextActivity.class));
                break;
            case 3: //写NFC标签中的文本数据
                startActivity(new Intent(this, WriteTextActivity.class));
                break;
            case 4: //读NFC标签中的Uri数据
                startActivity(new Intent(this, ReadUriActivity.class));
                break;
            case 5: //写NFC标签中的Uri数据
                startActivity(new Intent(this, WriteUriActivity.class));
                break;
            case 6: //读NFC标签非NDEF格式的数据
                startActivity(new Intent(this, ReadMUActivity.class));
                break;
            case 7: //写NFC标签非NDEF格式的数据
                startActivity(new Intent(this, WriteMUActivity.class));
                break;
            default:
                break;
        }
    }
}
