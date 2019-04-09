package com.o7services.pillreminder;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.WindowManager.LayoutParams;

public class AlertAlarm extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Uri currentRintoneUri = RingtoneManager.getActualDefaultRingtoneUri(getActivity()
                .getApplicationContext(), RingtoneManager.TYPE_RINGTONE);
        final Ringtone currentRingtone = RingtoneManager.getRingtone(getActivity(), currentRintoneUri);

        //play current Ringtone
        currentRingtone.play();
        /** Turn Screen On and Unlock the keypad when this alert dialog is displayed */
        getActivity().getWindow().addFlags(LayoutParams.FLAG_TURN_SCREEN_ON | LayoutParams.FLAG_DISMISS_KEYGUARD);

        /** Creating a alert dialog builder */
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        /** Setting title for the alert dialog */
        builder.setTitle("PillApp");

        /** Making it so notification can only go away by pressing the buttons */
        setCancelable(false);



        final String pill_name = getActivity().getIntent().getStringExtra("pill_name");

        builder.setMessage("Did you take your "+ pill_name + " ?");

        builder.setPositiveButton("I took it", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                currentRingtone.stop();
                AlertActivity act = (AlertActivity)getActivity();
                act.doPositiveClick(pill_name);
                getActivity().finish();
            }
        });

        builder.setNeutralButton("Snooze", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /** Exit application on click OK */
                currentRingtone.stop();
                AlertActivity act = (AlertActivity)getActivity();
                act.doNeutralClick(pill_name);
                getActivity().finish();
            }
        });

        builder.setNegativeButton("I won't take", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /** Exit application on click OK */
                currentRingtone.stop();
                AlertActivity act = (AlertActivity)getActivity();
                act.doNegativeClick();
                getActivity().finish();

            }
        });


       return builder.create();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().finish();
    }
}