package com.eseba.jp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.eseba.jp.R;
import com.eseba.jp.ServiceRegistry;
import com.eseba.jp.business.GenreBusiness;
import com.eseba.jp.database.table.Genre;
import com.eseba.jp.widget.MyTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by danielnguyen on 8/23/17.
 */

public class GenreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = GenreAdapter.class.getSimpleName();

    private List<Genre> itemList;
    private GenreBusiness genreBusiness;

    public GenreAdapter(List<Genre> itemList) {
        this.itemList = itemList;
        this.genreBusiness = (GenreBusiness) ServiceRegistry.getService(GenreBusiness.TAG);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false);
        return new ItemGroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final Genre genre = this.itemList.get(position);
        ItemGroupViewHolder itemViewHolder = (ItemGroupViewHolder) viewHolder;
        itemViewHolder.setup(genre, position);
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public class ItemGroupViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.row_item_divider_line)
        protected ImageView lineImageView;
        @BindView(R.id.row_item_title)
        protected MyTextView titleTextView;
        @BindView(R.id.row_item_toggle_button)
        protected ToggleButton itemToggleButton;

        private Genre item;

        public ItemGroupViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.row_item_toggle_button)
        public void itemToggleButtonOnClick() {
            this.item.setActive(!this.item.isActive());
            genreBusiness.saveGenre(this.item);
        }

        public void setup(Genre item, int position) {
            this.item = item;
            this.setupDividerLine(position);
            this.titleTextView.setText(this.item.getGenreName());
            this.itemToggleButton.setChecked(this.item.isActive());
        }

        private void setupDividerLine(int position) {
            if (position == getItemCount() - 1) {
                this.lineImageView.setVisibility(View.GONE);
            } else {
                this.lineImageView.setVisibility(View.VISIBLE);
            }
        }
    }
}
