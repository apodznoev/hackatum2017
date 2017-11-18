package com.example.android.saufdeckel.ui.adapters.overview;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.saufdeckel.R;
import com.example.android.saufdeckel.models.Coaster;

import java.util.List;

/**
 * Created by lars on 18.11.17.
 */

public class AllCoastersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private List<Coaster> mAllCoasters;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onCoasterItemClick(Coaster clickedCoaster);
    }

    public AllCoastersAdapter(Context context, List<Coaster> allCoasters, OnItemClickListener listener) {
        mContext = context;
        mAllCoasters = allCoasters;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View inflatedView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.all_coasters_row, parent, false);
            return new OverviewHolder(inflatedView, mListener);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((OverviewHolder)holder).bindOverview(mContext, mAllCoasters.get(position));
    }

    @Override
    public int getItemCount() {
        return mAllCoasters.size();
    }

    public static class OverviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Coaster mCoaster;
        private OnItemClickListener mListener;
        private TextView mCoasterName;
        private TextView mTableNumber;
        private TextView mCurrentDrink;
        private CardView mCardView;

        public OverviewHolder(View v, OnItemClickListener listener) {
            super(v);
            mListener = listener;
            mCoasterName = (TextView) v.findViewById(R.id.tv_coaster_name);
            mTableNumber = (TextView) v.findViewById(R.id.tv_table_number);
            mCurrentDrink = (TextView) v.findViewById(R.id.tv_current_drink);
            mCardView = (CardView) v.findViewById(R.id.cardview);
            mCardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if ( mListener != null ) {
                mListener.onCoasterItemClick(mCoaster);
            }
        }

        public void bindOverview(Context context, Coaster coaster) {
            mCoaster = coaster;
            mCoasterName.setText(coaster.getName());
            mTableNumber.setText(String.valueOf(coaster.getTableNumber()));
            if(coaster.getCurrentDrink() != null) {
                mCurrentDrink.setText(coaster.getCurrentDrink().getName());
            }
        }
    }
}
