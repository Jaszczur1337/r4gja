package info.androidhive.cardview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView= (BottomNavigationView) findViewById(R.id.bottom_navigation) ;
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();

                switch (item.getItemId()) {
                    case R.id.downloaded:
                    fragmentTransaction.replace(R.id.relativeLayout,new DownloadedFragment());
                    fragmentTransaction.commit();
                    return true;
                    case R.id.library:
                    fragmentTransaction.replace(R.id.relativeLayout,new LibraryFragment());
                    fragmentTransaction.commit();
                    return true;

                }
                return false;
            }
        });





    }





}