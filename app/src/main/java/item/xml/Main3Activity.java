package item.xml;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.expensetracker.R;

/*
 * Main Activity class that loads {@link MainFragment}.
 */
public class Main3Activity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_browse_fragment, new MainFragment())
                    .commitNow();
        }
    }
}
