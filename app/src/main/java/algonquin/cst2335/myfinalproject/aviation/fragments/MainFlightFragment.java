package algonquin.cst2335.myfinalproject.aviation.fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Locale;

import algonquin.cst2335.myfinalproject.R;
import algonquin.cst2335.myfinalproject.aviation.adapters.ViewPager2Adapter;

/**
 * The `MainFlightFragment` class represents the main activity that displays flight-related content.
 * This activity contains tabs for searching and viewing saved flights.
 */
public class MainFlightFragment extends AppCompatActivity {

    /**
     * Called when the activity is first created. Responsible for initializing the UI and setting up tabs.
     *
     * @param savedInstanceState The saved instance state, if available.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aviation_activity_main);

        initView();
    }

    /**
     * Initializes UI elements and sets up tabs.
     */
    private void initView() {
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        tabLayout.addTab(tabLayout.newTab()); // add tab for "search"
        tabLayout.addTab(tabLayout.newTab()); // add tab for "saved flights"

        String[] toolbarTabs = new String[]{getString(R.string.search), getString(R.string.saved)};

        setSupportActionBar(toolbar);
        viewPager.setAdapter(new ViewPager2Adapter(this));

        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(toolbarTabs[position])
        );

        mediator.attach();
    }

    /**
     * Initialize the contents of the Activity's options menu.
     *
     * @param menu The options menu in which you place your items.
     * @return `true` if the menu is to be displayed, otherwise `false`.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.aviation_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Called when an options menu item is selected.
     *
     * @param item The selected menu item.
     * @return `true` if the item is handled, otherwise `false`.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String[] options = new String[]{getString(R.string.english), getString(R.string.french)};

        if (item.getItemId() == R.id.language) {
            buildDialogBox(options, 0).create().show();
        } else {
            buildDialogBox().create().show();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * HELPER METHOD SECTION
     */

    // Helper method to change the app's language.
    void changeAppLanguage(Locale locale) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(locale);
        getResources().updateConfiguration(configuration, metrics);
        Intent intent = new Intent(this, MainFlightFragment.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    // Helper method to build a dialog box for language selection.
    AlertDialog.Builder buildDialogBox(String[] items, int defaultOption) {
        int[] checkedId = {defaultOption};
        return new AlertDialog.Builder(MainFlightFragment.this)
                .setTitle(getString(R.string.language))
                .setSingleChoiceItems(items, checkedId[0], (dialog, which) -> checkedId[0] = which)
                .setPositiveButton(getString(R.string.confirm), (dialog, which) -> {
                    if (checkedId[0] == 0) {
                        changeAppLanguage(Locale.ENGLISH);
                    } else {
                        changeAppLanguage(Locale.FRENCH);
                    }
                })
                .setNegativeButton(getString(R.string.cancel), (dialog, which) -> {});
    }

    // Helper method to build a dialog box for displaying app information.
    AlertDialog.Builder buildDialogBox() {
        return new AlertDialog.Builder(MainFlightFragment.this)
                .setTitle(getString(R.string.about))
                .setMessage(getString(R.string.subapp_name_aviation) + "\n\n" + getString(R.string.about_details))
                .setPositiveButton(getString(R.string.confirm), (dialog, which) -> {});
    }
}
