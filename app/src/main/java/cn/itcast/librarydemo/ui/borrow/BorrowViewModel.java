package cn.itcast.librarydemo.ui.borrow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BorrowViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BorrowViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}