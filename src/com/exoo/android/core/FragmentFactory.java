package com.exoo.android.core;

import android.app.Fragment;

import com.exoo.android.core.express.ExpressFragment;
import com.exoo.android.core.home.HomeFragment;

public class FragmentFactory {
    public static Fragment getInstanceByIndex(int index) {
        Fragment fragment = null;
        switch (index) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new ExpressFragment();
                break;
            case 2:
                fragment = new ExpressFragment();
                break;
            case 3:
                fragment = new ExpressFragment();
                break;
           
        }
        return fragment;
    }
}
