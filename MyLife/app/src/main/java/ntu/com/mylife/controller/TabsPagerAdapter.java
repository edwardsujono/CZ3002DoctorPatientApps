package ntu.com.mylife.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import ntu.com.mylife.view.ChatView;
import ntu.com.mylife.view.ContactView;

/**
 * Created by martinus on 28/9/16.
 */
public class TabsPagerAdapter extends SmartFragmentStatePagerAdapter {

    public TabsPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        switch (position) {
            case 0:
                return new ContactView();
            case 1:
                return new ChatView();
            default:
                return new ContactView();
        }

    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Contact";
            case 1:
                return "Chat";
            default:
                return "Contact";
        }
    }


}

