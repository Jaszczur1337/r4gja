package info.androidhive.cardview;


import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibraryFragment extends Fragment {

    long reference;
    private RecyclerView recyclerView;
    private AlbumsAdapter adapter;
    private List<Album> albumList;
    DownloadManager downloadManager;

    public LibraryFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_library, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button downloadButton =(Button) view.findViewById(R.id.downloadButton);

        recyclerView = (RecyclerView) getView().findViewById(R.id.library);
        albumList = new ArrayList<>();
        adapter = new AlbumsAdapter(getContext(), albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);


        recyclerView.setAdapter(adapter);

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference=DownloadData(Uri.parse("http://androidtest1.cba.pl/document.json"),v);
            }
        });
       ShowData();


    }
    private long DownloadData (Uri uri, View v) {

        long downloadReference;

        // Create request for android download manager
        downloadManager = (DownloadManager)getContext().getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        //Setting title of request
        request.setTitle("Data Download");

        //Setting description of request
        request.setDescription("Android Data download using DownloadManager.");

        request.setDestinationInExternalFilesDir(getContext(), Environment.DIRECTORY_DOWNLOADS,"document");

        //Enqueue download and save into referenceId
        downloadReference = downloadManager.enqueue(request);

        return downloadReference;
    }
    private void ShowData()
    {
        StringBuilder text = new StringBuilder();

        try {

            File file = new File( getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"document");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
            JSONObject jsonObject = new JSONObject(text.toString());
            for(int i=0;i<jsonObject.getJSONArray("games").length();i++)
            {
                JSONObject gameListObject = jsonObject.getJSONArray("games").getJSONObject(i);
                Album a = new Album(gameListObject.getString("title"),gameListObject.getString("description"),gameListObject.getString("time"),gameListObject.getString("city"));
                albumList.add(a);

            }
            adapter.notifyDataSetChanged();

        }
        catch (IOException e) {
            Toast.makeText(getContext(),"Error:"+e.toString(),Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            Toast.makeText(getContext(),"Error:"+e.toString(),Toast.LENGTH_LONG).show();
        }
    }

}
