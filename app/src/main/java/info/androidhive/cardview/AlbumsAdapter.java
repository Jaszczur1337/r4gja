package info.androidhive.cardview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Album> albumList;
    private float screenwidth;
    boolean activity;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description,city,time;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.gameTitle);
            city = (TextView) view.findViewById(R.id.city);
            time = (TextView) view.findViewById(R.id.time);
            description = (TextView) view.findViewById(R.id.gameDescription);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,DescriptionActivity.class);
                    intent.putExtra("activity",activity);
                    intent.putExtra("data", albumList.get(getAdapterPosition()));
                    mContext.startActivity(intent);
                    ((Activity)mContext).finish();
                }
            });
        }

    }


    public AlbumsAdapter(Context mContext, List<Album> albumList,float screenwidth,boolean activity) {
        this.mContext = mContext;
        this.albumList = albumList;
        this.screenwidth=screenwidth;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Paint paint = new Paint();
        Album album = albumList.get(position);
        holder.title.setText(album.getGameTitle());
        holder.title.setTextSize( (4/paint.measureText(album.getGameTitle())) *(screenwidth*7/8) );
        holder.description.setText(album.getGameDescription());
        holder.city.setText(album.getGamePlace());
        holder.time.setText(album.getGameTime());
        Glide.with(mContext).load(album.getThumbanilUri())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.thumbnail);
    }




    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
