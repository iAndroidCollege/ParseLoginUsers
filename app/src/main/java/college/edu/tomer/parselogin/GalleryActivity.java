package college.edu.tomer.parselogin;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GalleryActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.frame1)
    FrameLayout frame1;
    @Bind(R.id.frame2)
    FrameLayout frame2;
    @Bind(R.id.frame3)
    FrameLayout frame3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().
                beginTransaction().
                add(R.id.frame1, new GalleryFragment()).
                commit();

        getSupportFragmentManager().
                beginTransaction().
                add(R.id.frame2, new GalleryFragment()).
                commit();

        getSupportFragmentManager().
                beginTransaction().
                add(R.id.frame3, new GalleryFragment()).
                commit();
    }

}
