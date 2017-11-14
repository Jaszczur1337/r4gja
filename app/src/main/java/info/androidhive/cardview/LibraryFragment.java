package info.androidhive.cardview;


import android.app.DownloadManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LibraryFragment extends Fragment {

    long reference;
    private RecyclerView recyclerView;
    private static final String endpoint = "http://androidtest1.cba.pl/document.json";
    private AlbumsAdapter adapter;
    private List<Album> albumList;
    DownloadManager downloadManager;
    SwipeRefreshLayout swipeRefreshLayout;

    public LibraryFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_library, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView = (RecyclerView) getView().findViewById(R.id.library);
        albumList = new ArrayList<>();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        adapter = new AlbumsAdapter(getContext(), albumList, displayMetrics.widthPixels,false);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);


        recyclerView.setAdapter(adapter);

fetchImages();

    }






    private void fetchImages() {



        JsonArrayRequest req = new JsonArrayRequest(endpoint,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        albumList.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                Album a= new Album(object.getString("title"),object.getString("description"),object.getString("time"),object.getString("city"),object.getString("thumbnailUri"));
                                albumList.add(a);

                            } catch (JSONException e) {
                                Toast.makeText(getContext(), "Error:" + e.toString(), Toast.LENGTH_LONG).show();
                            }
                        }

                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                Toast.makeText(getContext(), "Error:" + e.toString(), Toast.LENGTH_LONG).show();
            }
        });

        // Adding request to request queue
        AppControler.getInstance().addToRequestQueue(req);
    }

}


