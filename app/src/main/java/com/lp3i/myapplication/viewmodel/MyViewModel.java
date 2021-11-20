package com.lp3i.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lp3i.myapplication.model.Transaction;

import java.util.List;

public class MyViewModel extends ViewModel {

    private MutableLiveData<Transaction> transactions;

    private MutableLiveData<String> tes;
    public LiveData<String> getTes() {
        if (tes == null) {
            tes = new MutableLiveData<String>();
        }
        return tes;
    }

    public void setTes(String s) {
        if (tes == null) {
            tes = new MutableLiveData<String>();
        }
        this.tes.setValue( s );
    }

    public LiveData<Transaction> getTransactions() {
        if (transactions == null) {
            transactions = new MutableLiveData<Transaction>();
        }
        return transactions;
    }

    public void setTransactions(Transaction transactions){
        this.transactions.setValue( transactions );
    }

}
