package com.myes.gandom;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.onecode.stickyheadergrid.adapter.StickyGridAdapter;
import com.onecode.stickyheadergrid.viewholder.BaseViewHolder;

public class PhotoAdapter extends StickyGridAdapter<PhotoModel, PhotoAdapter.ViewHolder, PhotoAdapter.HeaderViewHolder> {

    private static final long GROUP1 = 1;
    private static final long GROUP2 = 2;
    private static final long GROUP3 = 3;

    private static final String STRING_Group1 = "group 1";
    private static final String STRING_Group2 = "group 2";
    private static final String STRING_Group3 = "group 3";

    PhotoAdapter(Context context) {
        super(context);
    }

    @Override
    protected int layout() {
        return R.layout.linearlayout_images;
    }

    @Override
    protected ViewHolder viewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void populate(PhotoModel photoModel, PhotoAdapter.ViewHolder viewHolder) {

        String photoUrl = photoModel.getUrl();

        Glide.with(mContext)
                .load("http://gandom.co/devTest/1/images/" + photoUrl)
                .into(viewHolder.imageViewCoverArt);
    }

    @Override
    protected int headerLayout() {
        return R.layout.header_layout;
    }

    @Override
    protected HeaderViewHolder headerViewHolder(View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    protected void populateHeader(final PhotoModel photoModel, HeaderViewHolder headerViewHolder) {
        final String title = photoModel.getTitle();
        headerViewHolder.title.setText(title);

    }

    @Override
    public long getHeaderId(int position) {

        PhotoModel photoModel = getItem(position);

        switch (photoModel.getTitle()) {
            case STRING_Group1:
                return GROUP1;
            case STRING_Group2:
                return GROUP2;
            case STRING_Group3:
                return GROUP3;
            default:
                return -1;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder extends BaseViewHolder {

        private final ImageView imageViewCoverArt;

        ViewHolder(View imageViewCoverArt) {
            super(imageViewCoverArt);
            this.imageViewCoverArt = (ImageView) imageViewCoverArt.findViewById(R.id.imageview_cover_art);

        }
    }

    static class HeaderViewHolder extends BaseViewHolder {

        TextView title;
        View view;

        HeaderViewHolder(View view) {
            super(view);
            this.view = view;

            title = view.findViewById(R.id.header_text);

        }
    }
}
