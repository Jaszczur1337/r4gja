package info.androidhive.cardview;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


public class DescriptionActivity extends AppCompatActivity {
    private Album album;
    DownloadManager downloadManager;
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_description);
        final Intent intent = getIntent();
        Bundle data = getIntent().getExtras();
        album = (Album) data.getParcelable("data");
        title=album.getGameTitle();
        boolean bool = intent.getBooleanExtra("activity",false);
        final Button downloadBtn = (Button) findViewById(R.id.downloadButton);
        TextView titleTv = (TextView) findViewById(R.id.titleTV);
        TextView descriptionTv = (TextView) findViewById(R.id.descriptionTV);
        TextView timeTv = (TextView) findViewById(R.id.timeTV);
        TextView cityTv = (TextView) findViewById(R.id.cityTv);
        ImageView thumbnailView = (ImageView) findViewById(R.id.imageView);
        titleTv.setText(album.getGameTitle());
        descriptionTv.setText(album.getGameDescription());
        timeTv.setText(album.getGameTime());
        cityTv.setText(album.getGamePlace());
        Glide.with(this).load(album.getThumbanilUri())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(thumbnailView);
            downloadBtn.setText("Play");
            downloadBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent gameIntent = new Intent(getApplicationContext(), GameActivity.class);
                    gameIntent.putExtra("endpoint", "http://androidtest1.cba.pl/" + title + "/");
                    startActivity(gameIntent);
                    finish();
                }
            });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
