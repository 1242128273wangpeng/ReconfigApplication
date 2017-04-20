package com.changzhoureconfig.yimeng.com.reconfigapplication.ui.views.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.changzhoureconfig.yimeng.com.reconfigapplication.R;
import com.changzhoureconfig.yimeng.com.reconfigapplication.utils.BaseUtils;

/**
 * Created by Administrator on 2016/12/29 0029.
 */

public class TwoButtonStyleTwoDialog extends Dialog {

    public TwoButtonStyleTwoDialog(Context context, int theme, View layout) {
        super(context, theme);
        setContentView(layout);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }

    public TwoButtonStyleTwoDialog(Context context) {
        super(context);
    }

    /**
     * Helper class for creating a custom dialog
     */
    public static class Builder {
        private int screenWidth;
        private Context context;
        private String title;
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;
        @SuppressWarnings("unused")
        private View contentView;

        private DialogInterface.OnClickListener
                positiveButtonClickListener,
                negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
            screenWidth = BaseUtils.getScreenWidth(context);

        }

        /**
         * Set the Dialog message from String
         *
         * @return
         */
        public TwoButtonStyleTwoDialog.Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * Set the Dialog message from resource
         *
         * @return
         */
        public TwoButtonStyleTwoDialog.Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        /**
         * Set the Dialog title from resource
         *
         * @param title
         * @return
         */
        public TwoButtonStyleTwoDialog.Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        /**
         * Set the Dialog title from String
         *
         * @param title
         * @return
         */
        public TwoButtonStyleTwoDialog.Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * Set a custom content view for the Dialog.
         * If a message is set, the contentView is not
         * added to the Dialog...
         *
         * @param v
         * @return
         */
        public TwoButtonStyleTwoDialog.Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         *
         * @param positiveButtonText
         * @param listener
         * @return
         */
        public TwoButtonStyleTwoDialog.Builder setPositiveButton(int positiveButtonText,
                                                                 DialogInterface.OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        /**
         * Set the positive button text and it's listener
         *
         * @param positiveButtonText
         * @param listener
         * @return
         */
        public TwoButtonStyleTwoDialog.Builder setPositiveButton(String positiveButtonText,
                                                                 DialogInterface.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        /**
         * Set the negative button resource and it's listener
         *
         * @param negativeButtonText
         * @param listener
         * @return
         */
        public TwoButtonStyleTwoDialog.Builder setNegativeButton(int negativeButtonText,
                                                                 DialogInterface.OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        /**
         * Set the negative button text and it's listener
         *
         * @param negativeButtonText
         * @param listener
         * @return
         */
        public TwoButtonStyleTwoDialog.Builder setNegativeButton(String negativeButtonText, DialogInterface.OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        /**
         * Create the custom dialog
         */
        public TwoButtonStyleTwoDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.two_button_dialog, null);
            final TwoButtonStyleTwoDialog dialog = new TwoButtonStyleTwoDialog(context, R.style.TwoButtonStyleTwoDialog, layout);
            dialog.setCanceledOnTouchOutside(true);
            if (title == null || title.equals("")) {
                ((TextView) layout.findViewById(R.id.two_btn_title)).setVisibility(View.GONE);
            } else {
                ((TextView) layout.findViewById(R.id.two_btn_title)).setText(title);
            }
            if (positiveButtonText != null) {
                ((Button) layout.findViewById(R.id.confirm_btn)).setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.confirm_btn))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    positiveButtonClickListener.onClick(
                                            dialog,
                                            DialogInterface.BUTTON_POSITIVE);
                                }
                            });
                }
            } else {
                layout.findViewById(R.id.confirm_btn).setVisibility( View.GONE);
            }
            if (negativeButtonText != null) {
                ((Button) layout.findViewById(R.id.cancle_btn)).setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.cancle_btn))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    negativeButtonClickListener.onClick(
                                            dialog,
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.cancle_btn).setVisibility(View.GONE);
                ((Button) layout.findViewById(R.id.confirm_btn)).setBackground(context.getResources().getDrawable(R.drawable.onebuttonconfirm));
            }
            if (message != null) {
                ((TextView) layout.findViewById(
                        R.id.two_btn_desc)).setText(message);
            }
            return dialog;
        }
    }
}
