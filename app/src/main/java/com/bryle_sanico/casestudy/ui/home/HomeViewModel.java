package com.bryle_sanico.casestudy.ui.home;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bryle_sanico.casestudy.UnitModel;

import java.util.List;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<List<UnitModel>> unitListLiveData;

    public HomeViewModel() {
        unitListLiveData = new MutableLiveData<>();
    }

    public LiveData<List<UnitModel>> getUnitListLiveData() {
        return unitListLiveData;
    }

    public void setUnitList(List<UnitModel> unitList) {
        unitListLiveData.setValue(unitList);
    }
}
