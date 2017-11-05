package info.androidhive.cardview;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        adapter = new AlbumsAdapter(getContext(), albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);


        recyclerView.setAdapter(adapter);
        prepareAlbums();
    }

    private void prepareAlbums() {


        Album a = new Album("Poszukiwanie Gryfa","Przemierzaj Szczecin aby odnalezc mitycznego stwora zwanego gryfem 323234","4h","Szczecin, Poland");
        albumList.add(a);
        a = new Album("Poszukiwanie Gryfa","Przemierzaj Szczecin aby odnalezc mitycznego stwora zwanego gryfem 323234","4h","Szczecin, Poland");
        albumList.add(a);
        a = new Album("Poszukiwanie Gryfa","Przemierzaj Szczecin aby odnalezc mitycznego stwora zwanego gryfem 323234","4h","Szczecin, Poland");
        albumList.add(a);
        a = new Album("Poszukiwanie Gryfa","Przemierzaj Szczecin aby odnalezc mitycznego stwora zwanego gryfem 323234","4h","Szczecin, Poland");
        albumList.add(a);
        a = new Album("Poszukiwanie Gryfa","Przemierzaj Szczecin aby odnalezc mitycznego stwora zwanego gryfem 323234","4h","Szczecin, Poland");
        albumList.add(a);
        a = new Album("Poszukiwanie Gryfa","Przemierzaj Szczecin aby odnalezc mitycznego stwora zwanego gryfem 323234","4h","Szczecin, Poland");
        albumList.add(a);
        a = new Album("Poszukiwanie Gryfa","Przemierzaj Szczecin aby odnalezc mitycznego stwora zwanego gryfem 323234","4h","Szczecin, Poland");
        albumList.add(a);
        a = new Album("Poszukiwanie Gryfa","Przemierzaj Szczecin aby odnalezc mitycznego stwora zwanego gryfem 323234","4h","Szczecin, Poland");
        albumList.add(a);
        a = new Album("Poszukiwanie Gryfa","Przemierzaj Szczecin aby odnalezc mitycznego stwora zwanego gryfem 323234","4h","Szczecin, Poland");
        albumList.add(a);
        albumList.add(a);

        adapter.notifyDataSetChanged();
    }
}
