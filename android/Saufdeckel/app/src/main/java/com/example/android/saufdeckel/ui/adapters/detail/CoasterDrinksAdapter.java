package com.example.android.saufdeckel.ui.adapters.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.android.saufdeckel.R;
import com.example.android.saufdeckel.models.Drink;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lars on 18.11.17.
 */

public class CoasterDrinksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private List<Drink> mDrinks;
  

    public CoasterDrinksAdapter(Context context, List<Drink> drinks) {
        mContext = context;
        mDrinks = drinks;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View inflatedView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.coaster_drinks_row, parent, false);
            return new OverviewHolder(inflatedView);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((OverviewHolder)holder).bindOverview(mContext, mDrinks.get(position));
    }

    @Override
    public int getItemCount() {
        return mDrinks.size();
    }

    public static class OverviewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private Drink mDrink;
        private TextView mName;
        private TextView mType;
        private TextView mPrice;
        private TextView mStatus;


        public OverviewHolder(View v) {
            super(v);
            mName = (TextView) v.findViewById(R.id.tv_drink_name);
            mType = (TextView) v.findViewById(R.id.tv_drink_type);
            mPrice = (TextView) v.findViewById(R.id.tv_drink_price);
            mStatus = (TextView) v.findViewById(R.id.tv_drink_status);
        }

        public void bindOverview(Context context, Drink drink) {
            mContext = context;
            mDrink = drink;
            mName.setText(drink.getName());
            mType.setText(drink.getType().getUiName());
            mPrice.setText(String.valueOf(drink.getPrice()));
            mStatus.setText(String.valueOf(drink.getStatus()));
        }
    }
}
