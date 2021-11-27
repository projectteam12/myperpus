package com.lp3i.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lp3i.myapplication.model.Buku;
import com.lp3i.myapplication.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class MyViewModel extends ViewModel {

    /**
     * LiveData Buku
     */
    private MutableLiveData<ArrayList<Buku>> books;
    public LiveData<ArrayList<Buku>> getBooks() {
        if (books == null) {
            books = new MutableLiveData<ArrayList<Buku>>();
        }
        return books;
    }

    public void setBooks(ArrayList<Buku> buku) {
        this.books.setValue(buku);
    }

    /**
     * LiveData Detail Buku
     */
    private MutableLiveData<Buku> detail_book;
    public LiveData<Buku> getDetailBook() {
        if (detail_book == null) {
            detail_book = new MutableLiveData<Buku>();
        }
        return detail_book;
    }

    public void setDetailBook(Buku buku) {
        if (detail_book == null) {
            detail_book = new MutableLiveData<Buku>();
        }
        this.detail_book.setValue(buku);
    }


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
