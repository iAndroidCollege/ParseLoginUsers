package college.edu.tomer.parselogin;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseUser;

/**
 * Created by master on 25/12/15.
 */
public class AppManager extends Application {
    private static ParseUser currentUser;
    private static Context ctx;

    public static ParseUser getCurrentUser(Context context) throws RuntimeException {
        if (context.getApplicationContext() == ctx)
            return currentUser;
        throw new SecurityException("Not Authorized context");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("Application Created");
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);
        ctx = getApplicationContext();
        currentUser = ParseUser.getCurrentUser();
    }

}
