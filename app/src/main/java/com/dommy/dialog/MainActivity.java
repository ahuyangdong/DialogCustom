package com.dommy.dialog;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.dommy.dialog.widget.ConfirmDialog;
import com.dommy.dialog.widget.InputDialog;
import com.dommy.dialog.widget.LoadingDialog;
import com.dommy.dialog.widget.NoticeDialog;
import com.dommy.dialog.widget.NoticeToast;
import com.dommy.dialog.widget.SelectDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    /**
     * 加载或处理提示
     */
    @OnClick(R.id.btn_loading)
    void loading() {
        LoadingDialog.show(this);
        new Handler().postDelayed(() -> LoadingDialog.close(), 2000);
    }

    /**
     * 普通对话框提示
     */
    @OnClick(R.id.btn_notice)
    void notice() {
        NoticeDialog noticeDialog = new NoticeDialog(this);
        noticeDialog.setTitle("提示");
        noticeDialog.setContent("欢迎使用本APP");
        noticeDialog.setConfirmText(R.string.layout_ok);
        noticeDialog.show();
    }

    /**
     * 确认对话框
     */
    @OnClick(R.id.btn_confirm)
    void confirm() {
        ConfirmDialog confirmDialog = new ConfirmDialog(this,
                () -> NoticeToast.show(MainActivity.this, "我点确定了"));
        confirmDialog.setTitle("请确认操作");
        confirmDialog.setContent("确认删除选中的数据吗？");
        confirmDialog.show();
    }

    /**
     * 带输入内容的对话框
     */
    @OnClick(R.id.btn_input)
    void input() {
        InputDialog inputDialog = new InputDialog(this,
                text -> NoticeToast.show(MainActivity.this, "您输入的内容为：" + text));
        inputDialog.setTitle("请输入");
        inputDialog.show();
    }

    /**
     * 带按钮选项的对话框
     */
    @OnClick(R.id.btn_select)
    void select() {
        SelectDialog selectDialog = new SelectDialog(this,
                type -> NoticeToast.show(MainActivity.this, "您选择了第" + (type + 1) + "个选项"));
        selectDialog.setType(0);
        selectDialog.show();
    }
}