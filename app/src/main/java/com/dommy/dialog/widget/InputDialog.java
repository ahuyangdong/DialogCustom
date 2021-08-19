package com.dommy.dialog.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dommy.dialog.R;

/**
 * 带输入内容的对话框
 */
public class InputDialog {
    private Context context;
    private OnClickListener listener;

    private Dialog dialog;
    private TextView tvTitle;
    private EditText etInput;
    private Button btnCancel;
    private Button btnConfirm;

    public InputDialog(Context context, OnClickListener listener) {
        this.context = context;
        this.listener = listener;
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.dialog_input, null);

        initView(layout);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(layout);
        builder.setCancelable(false);
        dialog = builder.create();
    }

    private void initView(View layout) {
        tvTitle = (TextView) layout.findViewById(R.id.txt_title);
        etInput = (EditText) layout.findViewById(R.id.et_input);

        btnCancel = (Button) layout.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(view -> dialog.dismiss());
        btnConfirm = (Button) layout.findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(view -> {
            if (listener != null) {
                hideKeyBoard();
                listener.onConfirm(etInput.getText().toString());
            }
        });
    }

    public void close() {
        dialog.dismiss();
    }

    public void setTitle(String text) {
        tvTitle.setText(text);
        tvTitle.setVisibility(View.VISIBLE);
    }

    public void setTitle(int resid) {
        tvTitle.setText(resid);
        tvTitle.setVisibility(View.VISIBLE);
    }

    public void show() {
        etInput.setText("");
        dialog.show();
    }

    private void hideKeyBoard() {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etInput.getWindowToken(), 0);
    }

    public interface OnClickListener {
        void onConfirm(String text);
    }
}
