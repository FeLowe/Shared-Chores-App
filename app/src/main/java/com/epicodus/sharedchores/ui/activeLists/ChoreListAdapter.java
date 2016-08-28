package com.epicodus.sharedchores.ui.activeLists;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.sharedchores.R;
import com.epicodus.sharedchores.model.ChoreList;
import com.epicodus.sharedchores.ui.activeListsDetails.ChoreListDetailsActivity;

import org.parceler.Parcels;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by flowe on 8/26/16.
 */
public class ChoreListAdapter extends RecyclerView.Adapter<ChoreListAdapter.ChoreListViewHolder> {

    private List<ChoreList> mChoreLists;
    private Context mContext;

    public ChoreListAdapter(Context context, List<ChoreList> choreLists) {
        mContext = context;
        mChoreLists = choreLists;
    }


    @Override
    public ChoreListAdapter.ChoreListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_active_list, parent, false);
        ChoreListViewHolder viewHolder = new ChoreListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ChoreListAdapter.ChoreListViewHolder holder, int position) {
        holder.bindChoreList(mChoreLists.get(position));
    }

    @Override
    public int getItemCount() {
        return mChoreLists.size();
    }

    public class ChoreListViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.listNameTextView)
        TextView mListNameTextView;
        @Bind(R.id.createdByTextView)
        TextView mCreatedByTextView;
        @Bind(R.id.createdByUserTextView)
        TextView mCreatedByUserTextView;


        public ChoreListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int itemPosition = getLayoutPosition();

                    Intent intent = new Intent(mContext, ChoreListDetailsActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("restaurants", Parcels.wrap(mChoreLists));

                    mContext.startActivity(intent);

                }
            });

        }


        public void bindChoreList(ChoreList choreList) {

            mListNameTextView.setText(choreList.getListName());
            mCreatedByTextView.setText(choreList.getOwner());
            mCreatedByUserTextView.setText(choreList.getOwner());

        }
    }

}
