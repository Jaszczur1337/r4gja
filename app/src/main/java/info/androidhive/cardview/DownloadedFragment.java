package info.androidhive.cardview;


import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class DownloadedFragment extends Fragment {

    private RecyclerView recyclerView;
    private AlbumsAdapter adapter;
    private List<Album> albumList;
    public DownloadedFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_downloaded, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
        albumList = new ArrayList<>();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        adapter = new AlbumsAdapter(getContext(), albumList,displayMetrics.widthPixels,true);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        final IntentFilter filter = new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE");
        final BroadcastReceiver checkDownloadComplete = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();

                if (action.equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0));
                    DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                    Cursor cursor = manager.query(query);
                    if (cursor.moveToFirst()) {
                        if (cursor.getCount() > 0) {
                            int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                            if (status == DownloadManager.STATUS_SUCCESSFUL) {
                                String file = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME));
                                ShowData();
                            } else {
                                int message = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_REASON));
                                // So something here on failed.
                            }
                        }
                    }
                }
            }
        };

        recyclerView.setAdapter(adapter);
        ShowData();
    }

    public void ShowData() {


        try {
            File directory = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)+"/games");
            File[] files = directory.listFiles();
            for (int i = 0; i < files.length; i++) {
                File file = new File(files[i].getAbsolutePath(), "document.json");
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                StringBuilder text = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    text.append(line);
                    text.append('\n');
                }
                br.close();
                JSONObject jsonObject = new JSONObject(text.toString());
                JSONObject gameObject = jsonObject.getJSONObject("info");
                Album a = new Album(gameObject.getString("title"), gameObject.getString("description"), gameObject.getString("time"), gameObject.getString("city"),files[i]+"/thumbnail.png");

                albumList.add(a);
                adapter.notifyDataSetChanged();


            }

            } catch(Exception e){
                Toast.makeText(getContext(), "Error:" + e.toString(), Toast.LENGTH_LONG).show();
            }



    }
}
