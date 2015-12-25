package college.edu.tomer.parselogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.etUserName)
    EditText etUserName;
    @Bind(R.id.tilUserName)
    TextInputLayout tilUserName;
    @Bind(R.id.etPass)
    EditText etPass;
    @Bind(R.id.tilPassword)
    TextInputLayout tilPassword;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.checkboxLoginSignUp)
    CheckBox checkboxLoginSignUp;
    private boolean isLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        if (AppManager.getCurrentUser(this) != null) {
            animateToHomeScreen();
        } else Toast.makeText(LoginActivity.this,
                "Please Login",
                Toast.LENGTH_SHORT).show();


    }

    @OnCheckedChanged(R.id.checkboxLoginSignUp)
    void checkedLogin(CompoundButton buttonView, boolean isChecked) {
        isLogin = checkboxLoginSignUp.isChecked();
        fab.setImageResource(isLogin ?
                R.drawable.ic_login_24dp :
                R.drawable.ic_login_add_24dp);
    }

    @OnClick(R.id.fab)
    void fabLogin(View v) {
        if (isLogin)
            login();
        else
            register();
    }

    private void login() {
        String name = etUserName.getText().toString();
        String pass = etPass.getText().toString();
        ParseUser.logInInBackground(name, pass, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e == null)
                    animateToHomeScreen();
                else
                    Toast.makeText(LoginActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void register() {
        ParseUser user = new ParseUser();
        user.setUsername(etUserName.getText().toString());
        user.setPassword(etPass.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null)
                    animateToHomeScreen();
                else
                    Toast.makeText(LoginActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void animateToHomeScreen() {
        Toast.makeText(LoginActivity.this,
                "Hello, " + AppManager.getCurrentUser(this).getUsername(),
                Toast.LENGTH_SHORT).show();

        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this, fab, "fab");

        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        //startActivity(mainIntent);
        // ActivityCompat.startActivity(LoginActivity.this, mainIntent, options.toBundle());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
