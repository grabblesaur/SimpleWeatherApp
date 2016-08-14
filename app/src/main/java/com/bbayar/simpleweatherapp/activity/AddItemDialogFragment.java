package com.bbayar.simpleweatherapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import com.bbayar.simpleweatherapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddItemDialogFragment extends DialogFragment {

    public interface AddItemDialogCallbackInterface {
        public void addItem(int id);
    }

    @BindView(R.id.et_id)
    EditText mEditText;

    AddItemDialogCallbackInterface mListener;

    public AddItemDialogFragment() {
    }

    public static AddItemDialogFragment newInstance() {
        return new AddItemDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_item, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mListener = (AddItemDialogCallbackInterface) getActivity();
        mEditText.requestFocus();

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @OnClick(R.id.cancel) void onCancelClicked() {
        dismiss();
    }

    @OnClick(R.id.add_item) void addItem(View view) {

        String stringId = mEditText.getText().toString();
        try {
            mListener.addItem(Integer.valueOf(stringId));
        } catch (NumberFormatException nfe)  {
            Snackbar.make(view, "Invalid id: " + stringId, Snackbar.LENGTH_SHORT).show();
        }
        dismiss();
    }

}
