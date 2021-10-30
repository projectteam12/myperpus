package com.lp3i.myapplication.model;

public class Buku {

    private int id = 0;
    private String namaBuku = "";
    private String tahunTerbit = "";
    private String penulis = "";
    private String deskripsi = "";
    private String kategori = "";
    private int stok = 0;
    private String urlBuku = "";

    public Buku(int id, String namaBuku, String tahunTerbit, String penulis, String deskripsi, String kategori, int stok, String urlBuku) {
        this.id = id;
        this.namaBuku = namaBuku;
        this.tahunTerbit = tahunTerbit;
        this.penulis = penulis;
        this.deskripsi = deskripsi;
        this.kategori = kategori;
        this.stok = stok;
        this.urlBuku = urlBuku;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNamaBuku(String namaBuku) {
        this.namaBuku = namaBuku;
    }

    public void setTahunTerbit(String tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public void setUrlBuku(String urlBuku) {
        this.urlBuku = urlBuku;
    }

    public String getUrlBuku() {
        return urlBuku;
    }

    public int getId() {
        return id;
    }

    public String getNamaBuku() {
        return namaBuku;
    }

    public String getTahunTerbit() {
        return tahunTerbit;
    }

    public String getPenulis() {
        return penulis;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getKategori() {
        return kategori;
    }

    public int getStok() {
        return stok;
    }
}
