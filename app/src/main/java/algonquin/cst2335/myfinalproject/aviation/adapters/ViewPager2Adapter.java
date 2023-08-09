package algonquin.cst2335.myfinalproject.aviation.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import algonquin.cst2335.myfinalproject.aviation.fragments.SavedFlightFragment;
import algonquin.cst2335.myfinalproject.aviation.fragments.SearchFlightFragment;

/**
 * The `ViewPager2Adapter` class is an adapter for managing fragments within a ViewPager2.
 * It provides the necessary functionality for creating and displaying fragments in a ViewPager2.
 */
public class ViewPager2Adapter extends FragmentStateAdapter {

    /**
     * Constructs a new `ViewPager2Adapter` instance.
     *
     * @param fragmentActivity The parent FragmentActivity.
     */
    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    /**
     * Called to instantiate the fragment at the specified position.
     *
     * @param position The position of the fragment in the ViewPager.
     * @return The instantiated Fragment for the given position.
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new SearchFlightFragment(); // Returns a new SearchFlightFragment instance for position 0.
            case 1:
                return new SavedFlightFragment();  // Returns a new SavedFlightFragment instance for position 1.
        }
        return null;
    }

    /**
     * Returns the total number of items that the adapter will display.
     *
     * @return The number of items in the adapter.
     */
    @Override
    public int getItemCount() {
        return 2; // Returns a fixed count of 2 since there are two fragments: SearchFlightFragment and SavedFlightFragment.
    }
}
