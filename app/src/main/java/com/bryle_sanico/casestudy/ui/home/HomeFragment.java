package com.bryle_sanico.casestudy.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bryle_sanico.casestudy.CustomAdapterMainHousings;
import com.bryle_sanico.casestudy.UnitModel;
import com.bryle_sanico.casestudy.databinding.FragmentHomeBinding;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        homeViewModel.getUnitListLiveData().observe(getViewLifecycleOwner(), new Observer<List<UnitModel>>() {
            @Override
            public void onChanged(List<UnitModel> unitList) {
                updateListView(unitList);
            }
        });

        return binding.getRoot();
    }

    private void updateListView(List<UnitModel> unitList) {
        CustomAdapterMainHousings adapter = new CustomAdapterMainHousings(requireContext(), unitList);
        ListView listView = binding.listViewMainHousing;
        listView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}