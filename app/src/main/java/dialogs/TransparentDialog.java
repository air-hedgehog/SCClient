package dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.akimchenko.antony.scclient.MainActivity;
import com.akimchenko.antony.scclient.R;

/**
 * Created by antony on 10/11/17.
 */

public class TransparentDialog extends DialogFragment {

    private View view;
    private ImageView imageBackground;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        MainActivity activity = (MainActivity) getActivity();
        LayoutInflater inflater = activity.getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_blur, null);

        LinearLayout imageBackground = view.findViewById(R.id.dialog_blur_background);
        Button cancelButton = view.findViewById(R.id.my_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setView(view);
        return builder.create();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return view;
    }
}
