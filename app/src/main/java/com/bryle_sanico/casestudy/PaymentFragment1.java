package com.bryle_sanico.casestudy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PaymentFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentFragment1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button payBtn;

    public PaymentFragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PaymentFragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static PaymentFragment1 newInstance(String param1, String param2) {
        PaymentFragment1 fragment = new PaymentFragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment1, container, false);

        // Find the Button in the inflated view
        payBtn = view.findViewById(R.id.paymentButton);

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Load PaymentFragment2 when the button is clicked
                loadPaymentFragment2();
            }
        });

        return view;
    }

    private void loadPaymentFragment2() {
        // Create an instance of PaymentFragment2
        PaymentFragment2 fragment2 = new PaymentFragment2();

        // Replace the current fragment with PaymentFragment2
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.paymentfragment, fragment2)
                .addToBackStack(null)  // Optionally add to back stack
                .commit();
    }
}
