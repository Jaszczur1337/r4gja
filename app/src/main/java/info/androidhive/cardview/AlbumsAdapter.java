package info.androidhive.cardview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Album> albumList;

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

        }
    }


    public AlbumsAdapter(Context mContext, List<Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.title.setText(album.getGameTitle());
        holder.description.setText(album.getGameDescription());
        holder.city.setText(album.getGamePlace());
        holder.time.setText(album.getGameTime());
    }




    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
