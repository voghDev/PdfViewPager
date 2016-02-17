package es.voghdev.pdfviewpager.library.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import es.voghdev.pdfviewpager.library.R;

public class LegacyPDFView extends LinearLayout {
    TextView textView;
    ProgressBar progressBar;
    Button button;

    public LegacyPDFView(Context context) {
        super(context);
        init(null);
    }

    public LegacyPDFView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public LegacyPDFView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        View v = inflate(getContext(), getLayoutId(), this);

        textView = (TextView) v.findViewById(R.id.legacy_pdf_textView);
        button = (Button) v.findViewById(R.id.legacy_pdf_button);
        progressBar = (ProgressBar) v.findViewById(R.id.legacy_pdf_progressBar);

        if (attrs != null){
            TypedArray a;

            a = getContext().obtainStyledAttributes(attrs, R.styleable.LegacyPDFView);

            a.recycle();
        }
    }

    public void setOnClickListener(OnClickListener l){
        getButton().setOnClickListener(l);
    }

    public void setMax(int max){
        getProgressBar().setMax(max);
    }

    public void setProgress(int progress){
        getProgressBar().setProgress(progress);
    }

    public void setText(String text){
        getTextView().setText(text);
    }

    public void setText(int resId){
        getTextView().setText(resId);
    }

    //region overridable methods - You can customize this view by adding a subclass with your own implementations
    protected int getLayoutId(){
        return R.layout.view_legacy_pdf;
    }

    protected TextView getTextView() {
        return textView;
    }

    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    protected Button getButton() {
        return button;
    }
    //end region
}
