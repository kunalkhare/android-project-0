package com.example.android.menu;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

import com.example.android.myappportfolio.R;

import java.util.List;

/**
 * Created by Kunal on 6/17/2016.
 */
public class MovieMenuSettingsActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener{
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String selectedKey = newValue.toString();
        if(preference instanceof ListPreference){

            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(selectedKey);
            if(prefIndex>0)
            {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        }
        return true;
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return MovieMenuSettingsFragment.class.getName().equals(fragmentName);
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.preferences, target);
    }

    public static class MovieMenuSettingsFragment extends PreferenceFragment{

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.sort_preference);
        }
    }
}
