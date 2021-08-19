package com.dommy.dialog.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.dommy.dialog.R;

/**
 * 普通对话框提示
 */
public class NoticeDialog {
    private Dialog dialog;
    private TextView tvTitle;
    private TextView tvContent;
    private Button btnCancel;
    private Button btnConfirm;

    public NoticeDialog(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.dialog_confirm, null);

        initView(layout);

        dialog = new AlertDialog.Builder(context).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.getWindow().setContentView(layout);
    }

    private void initView(View layout) {
        tvTitle = (TextView) layout.findViewById(R.id.txt_title);
        tvContent = (TextView) layout.findViewById(R.id.txt_content);

        btnCancel = (Button) layout.findViewById(R.id.btn_cancel);
        btnCancel.setVisibility(View.GONE);
        btnConfirm = (Button) layout.findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(view -> dialog.dismiss());
    }

    public void setTitle(String text) {
        tvTitle.setText(text);
        tvTitle.setVisibility(View.VISIBLE);
    }

    public void setTitle(int resid) {
        tvTitle.setText(resid);
        tvTitle.setVisibility(View.VISIBLE);
    }

    public void setContent(String text) {
        tvContent.setText(text);
    }

    public void setContent(int resid) {
        tvContent.setText(resid);
    }

    public void close() {
        dialog.dismiss();
    }

    public void show() {
        dialog.show();
    }

    public void setConfirmText(int resId) {
        btnConfirm.setText(resId);
    }
}
