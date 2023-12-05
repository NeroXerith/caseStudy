package com.bryle_sanico.casestudy.ui.messages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bryle_sanico.casestudy.databinding.FragmentMessagesBinding;

public class MessagesFragment extends Fragment {

    private FragmentMessagesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Initialize ViewModel to Access the Elements or Components from Profile Activity java class explain ko to f2f
        MessagesViewModel messagesViewModel = new ViewModelProvider(requireActivity()).get(MessagesViewModel.class);

        //  Initialize Parent Activity To Access the Elements or Components from Profile Activity java class explain ko to f2f
        binding = FragmentMessagesBinding.inflate(inflater, container, false);

        // FUNCTIONS BELOW


        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}