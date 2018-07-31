package com.ahmed.sabih.weatherapp.core;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmed.sabih.weatherapp.weather.Constants;
import com.bumptech.glide.Glide;

public class UIUtils {


    public static void showImage(Context context ,String URL, ImageView target){
        Glide.with(context).load(URL).into(target);
    }

    /**Method for showing Snack bar
     *
     * @param coordinatorLayout
     * @param message
     */
    public static void showSnackBar(CoordinatorLayout coordinatorLayout,
                                    String message) {

        Snackbar snackBar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_INDEFINITE);
        View view = snackBar.getView();
        TextView tv = view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snackBar.show();
    }

    /**This method shows the snack bar with action
     *
     * @param coordinatorLayout
     * @param message
     * @param actionTitle
     * @param listener
     */
    public static void showSnackBar(CoordinatorLayout coordinatorLayout,
                                    String message, String actionTitle,
                                    final SnackBarActionListener listener) {

        Snackbar snackBar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_INDEFINITE).
                setAction(actionTitle, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onSnackBarAction();
                    }
                });

        snackBar.setActionTextColor(Color.WHITE);
        View view = snackBar.getView();

        TextView tv = view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snackBar.show();
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

    public interface SnackBarActionListener{
        void onSnackBarAction();
    }

    /**This method shows a confirmation dialog
     *
     * @param context
     * @param message
     * @param positiveButtonTitle
     * @param negativeButtonTitle
     * @param dialogButtonListener
     */
    public static void showMessageDialog(Context context, String message, String positiveButtonTitle,
                                         String negativeButtonTitle,
                                         final DialogButtonListener dialogButtonListener) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);


        builder.setMessage(message).
                setPositiveButton(positiveButtonTitle, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogButtonListener.onPositiveButtonClicked();
                    }
                }).setNegativeButton(negativeButtonTitle, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
                dialogButtonListener.onNegativeButtonClicked();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public interface DialogButtonListener {
        void onPositiveButtonClicked();
        void onNegativeButtonClicked();
    }
}
