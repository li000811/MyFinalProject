package algonquin.cst2335.myfinalproject.aviation.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import algonquin.cst2335.myfinalproject.aviation.fragments.SaveFragment;
import algonquin.cst2335.myfinalproject.aviation.fragments.SearchFragment;

public class ViewPager2Adapter extends FragmentStateAdapter {

    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new SearchFragment();
            case 1:
                return new SaveFragment();
        }

        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}