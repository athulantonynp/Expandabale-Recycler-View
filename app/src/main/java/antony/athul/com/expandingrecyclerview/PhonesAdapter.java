package antony.athul.com.expandingrecyclerview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.provider.Contacts;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;




import java.util.ArrayList;


/**
 * Created by athul on 12/6/17.
 */

public class PhonesAdapter extends RecyclerView.Adapter<PhonesAdapter.PhoneViewHolder> {

    ArrayList<Phone>phones;
    Context context;

    public PhonesAdapter(ArrayList<Phone>phones,Context context){
        this.phones=phones;
        this.context=context;
    }
    @Override
    public PhoneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.list_cell_phone, parent, false);

        return new PhoneViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PhoneViewHolder holder, int position) {

        Phone phone=phones.get(position);
        holder.tvTitle.setText(phone.getOsName());
        holder.inflaterLayout.setVisibility(View.GONE);

            for(int i=0;i<phone.getModels().size();i++){
                final Models model=phone.getModels().get(i);
                LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View myView = inflater.inflate(R.layout.list_cell_expanded, null);
                TextView tvName=(TextView)myView.findViewById(R.id.tv_phone);
                tvName.setText(model.getName());
                holder.inflaterLayout.addView(myView);
                CheckBox cbCheck=(CheckBox)myView.findViewById(R.id.cb_model);
                cbCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        Log.e("CHECK","CHECK IS HERE");
                    }
                });

                if(model.isPreselect()){
                    cbCheck.setChecked(model.isPreselect());
                }


        }


        holder.header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.inflaterLayout.getVisibility()==View.GONE){
                    rotateView(holder.ivArrow,true);
                    expand(holder.inflaterLayout);
                }else {
                    rotateView(holder.ivArrow,false);
                    collapse(holder.inflaterLayout);
                }
            }
        });
    }




    private void expand(LinearLayout inflaterLayout) {
        inflaterLayout.setVisibility(View.VISIBLE);
        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        inflaterLayout.measure(widthSpec, heightSpec);
        ValueAnimator mAnimator = slideAnimator(0, inflaterLayout.getMeasuredHeight(),inflaterLayout);
        mAnimator.start();
    }

    private void collapse(final LinearLayout inflaterLayout) {
        int finalHeight = inflaterLayout.getHeight();

        ValueAnimator mAnimator = slideAnimator(finalHeight, 0,inflaterLayout);

        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                //Height=0, but it set visibility to GONE
                inflaterLayout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

        });
        mAnimator.start();
    }
    private ValueAnimator slideAnimator(int start, int end, final LinearLayout inflaterLayout) {

        ValueAnimator animator = ValueAnimator.ofInt(start, end);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //Update Height
                int value = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = inflaterLayout.getLayoutParams();
                layoutParams.height = value;
                inflaterLayout.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

    @Override
    public int getItemCount() {
        return phones.size();
    }

    public class PhoneViewHolder extends RecyclerView.ViewHolder{

            TextView tvTitle;
            LinearLayout inflaterLayout;
            RelativeLayout header;
            ImageView ivArrow;
            public PhoneViewHolder(View itemView) {
            super(itemView);
                tvTitle=(TextView)itemView.findViewById(R.id.tv_title);
                inflaterLayout=(LinearLayout)itemView.findViewById(R.id.inflaterLayout);
                header=(RelativeLayout)itemView.findViewById(R.id.rv_header);
                ivArrow=(ImageView)itemView.findViewById(R.id.ivArrow);
        }
    }

    public void rotateView(ImageView iv,boolean isExpand){
        RotateAnimation rotateAnimation;
        if(isExpand){
        rotateAnimation = new RotateAnimation(180.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
       }else {
            rotateAnimation = new RotateAnimation(0.0f,180.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        }
        rotateAnimation.setInterpolator(new DecelerateInterpolator());
        rotateAnimation.setRepeatCount(0);
        rotateAnimation.setDuration(300);
        rotateAnimation.setFillAfter(true);
        iv.startAnimation(rotateAnimation);
    }


}
