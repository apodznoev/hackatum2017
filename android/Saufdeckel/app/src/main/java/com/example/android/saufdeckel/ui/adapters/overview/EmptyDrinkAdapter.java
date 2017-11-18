package com.example.android.saufdeckel.ui.adapters.overview;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.saufdeckel.R;
import com.example.android.saufdeckel.models.Coaster;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by lars on 18.11.17.
 */

public class EmptyDrinkAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private List<Coaster> mEmptyDrinkCoasters;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onCoasterItemClick(Coaster clickedCoaster);
    }

    public EmptyDrinkAdapter(Context context, List<Coaster> emptyDrinkCoasters, OnItemClickListener listener) {
        mContext = context;
        mEmptyDrinkCoasters = emptyDrinkCoasters;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View inflatedView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.empty_drink_row, parent, false);
            return new OverviewHolder(inflatedView, mListener);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((OverviewHolder)holder).bindOverview(mContext, mEmptyDrinkCoasters.get(position));
    }

    @Override
    public int getItemCount() {
        return mEmptyDrinkCoasters.size();
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
