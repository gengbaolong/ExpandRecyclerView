package com.ps.expandrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created : 2018/3/8 11:01
 * Description :
 * Author : gengbaolong
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>{

    Context mContext;
    List<ExpandBean> mList;
    LayoutInflater mInflater;

    // 用一组list保存下拉状态（true - 显示下拉， false - 隐藏下拉）
    public ArrayList<Boolean> lDropDown;

    public RecyclerAdapter(Context context, List<ExpandBean> list){
        this.mContext = context;
        this.mList = list;
        mInflater = LayoutInflater.from(context);

        // 初始状态，所有都不显示下拉
        lDropDown = new ArrayList<Boolean>();
        for (int i = 0; i < mList.size(); i++) {
            lDropDown.add(false);
        }
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = mInflater.inflate(R.layout.item_list, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        ExpandBean bean = mList.get(position);
        holder.tvDueDay.setText(bean.dueDate);
        holder.tvDueMoney.setText(bean.paymentDue);
        holder.tvStatus.setText(bean.status);
        holder.tvBenJin.setText(bean.benJin);
        holder.tvLiXi.setText(bean.liXi);

        final int index = position;

        if(lDropDown.get(position)){
            holder.lineBottom.setVisibility(View.VISIBLE);//展开
            holder.imageView.setBackgroundResource(R.mipmap.ic_expand_less);
        }else{
            holder.lineBottom.setVisibility(View.GONE);//隐藏
            holder.imageView.setBackgroundResource(R.mipmap.ic_expand_more);
        }

        holder.rlTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean bFlagTemp = lDropDown.get(index);
//                for (int i = 0; i < lData.size(); i++) {//先把所有都隐藏--------这个循环加上，可以让页面同一时间只能展开一个条目,去掉这个循环，可以实现同时展开多个条目，互不影响
//                    lDropDown.set(i, false);
//                }
                lDropDown.set(index, !bFlagTemp);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return null == mList?0:mList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        //顶部控件
        private RelativeLayout rlTop;
        private TextView tvDueDay;
        private TextView tvDueMoney;
        private TextView tvStatus;
        private ImageView imageView;
        //底部控件
        private LinearLayout lineBottom;
        private TextView tvBenJin;
        private TextView tvLiXi;

        public RecyclerViewHolder(View itemView) {
            super(itemView);

            rlTop = (RelativeLayout) itemView.findViewById(R.id.rl_top);
            tvDueDay = (TextView) itemView.findViewById(R.id.tv_payment_due_day);
            tvDueMoney = (TextView) itemView.findViewById(R.id.tv_payment_due_money);
            tvStatus = (TextView) itemView.findViewById(R.id.tv_status);
            imageView = (ImageView) itemView.findViewById(R.id.status_image);
            lineBottom = (LinearLayout) itemView.findViewById(R.id.line_bottom);
            tvBenJin = (TextView) itemView.findViewById(R.id.tv_benjin);
            tvLiXi = (TextView) itemView.findViewById(R.id.tv_lixi);

        }
    }

}
