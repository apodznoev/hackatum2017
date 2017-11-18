package com.example.android.saufdeckel.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

    public EmptyDrinkAdapter(Context context, List<Coaster> emptyDrinkCoasters) {
        mContext = context;
        mEmptyDrinkCoasters = emptyDrinkCoasters;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View inflatedView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.empty_drink_row, parent, false);
            return new OverviewHolder(inflatedView);

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
        private TextView mCoasterName;
        private TextView mTableNumber;
        private TextView mCurrentDrink;

        public OverviewHolder(View v) {
            super(v);

            mCoasterName = (TextView) v.findViewById(R.id.tv_coaster_name);
            mTableNumber = (TextView) v.findViewById(R.id.tv_table_number);
            mCurrentDrink = (TextView) v.findViewById(R.id.tv_current_drink);
        }

        @Override
        public void onClick(View v) {

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
