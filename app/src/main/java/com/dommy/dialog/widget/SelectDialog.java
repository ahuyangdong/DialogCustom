package com.dommy.dialog.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.appcompat.app.AlertDialog;

import com.dommy.dialog.R;

/**
 * 带按钮选项的对话框
 */
public class SelectDialog {
    private static final int TYPE_A = 0;
    private static final int TYPE_B = 1;
    private OnClickListener listener;

    private Dialog dialog;
    private Button btnCancel;
    private Button btnConfirm;
    private RadioGroup rgType;

    public SelectDialog(Context context, OnClickListener listener) {
        this.listener = listener;
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.dialog_select, null);

        initView(layout);

        dialog = new AlertDialog.Builder(context).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.getWindow().setContentView(layout);
    }

    private void initView(View layout) {
        rgType = layout.findViewById(R.id.rg_type);
        btnCancel = (Button) layout.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(view -> dialog.dismiss());
        btnConfirm = (Button) layout.findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(view -> {
            if (listener != null) {
                int type = rgType.getCheckedRadioButtonId() == R.id.rb_app ? TYPE_A : TYPE_B;
                listener.onConfirm(type);
            }
            dialog.dismiss();
        });
    }

    public void close() {
        dialog.dismiss();
    }

    public void show() {
        dialog.show();
    }

    public void setType(int type) {
        if (type == TYPE_A) {
            rgType.check(R.id.rb_app);
        } else {
            rgType.check(R.id.rb_google);
        }
    }

    public interface OnClickListener {
        void onConfirm(int type);
    }
}
