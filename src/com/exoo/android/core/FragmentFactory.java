package com.exoo.android.core;

import com.exoo.android.core.express.Express;

import android.app.Fragment;

public class FragmentFactory {
    public static Fragment getInstanceByIndex(int index) {
        Fragment fragment = null;
        switch (index) {
            case 0:
                fragment = new Express();
                break;
            case 1:
                fragment = new Express();
                break;
            case 2:
                fragment = new Express();
                break;
            case 3:
                fragment = new Express();
                break;
           
        }
        return fragment;
    }
}
