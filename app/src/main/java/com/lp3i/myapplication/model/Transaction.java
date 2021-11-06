package com.lp3i.myapplication.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Transaction implements Serializable {

    private int id = 0;
    private ArrayList<Buku> bukus = new ArrayList<>();

    public Transaction() {
    }

    public Transaction(int id, ArrayList<Buku> bukus) {
        this.id = id;
        this.bukus = bukus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Buku> getBukus() {
        return bukus;
    }

    public void setBukus(ArrayList<Buku> bukus) {
        this.bukus = bukus;
    }
}
