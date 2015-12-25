package college.edu.tomer.parselogin;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.fetchRecipes)
    FloatingActionButton fetchRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

    }
    @OnClick(R.id.fetchRecipes)
    void fetchRecipes() {
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Recipe");
        query.whereEqualTo("User", AppManager.getCurrentUser(this));
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> recipes, ParseException e) {
                for (ParseObject recipe : recipes) {
                    String title = recipe.getString("Title");
                    System.out.println(title);
                }
            }
        });
    }

    @OnClick(R.id.fab)
    void fabClicked() {
        ParseObject o = new ParseObject("Recipe");
        o.put("Title", "Potato Mesh Burekas");
        o.put("User", AppManager.getCurrentUser(this));

        o.saveEventually(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                String message = e == null ? "Saved" : e.getLocalizedMessage();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
