package com.asneiya.neobyte.umkmdepok.model.umkm;

/**
 * Created by neobyte on 8/25/2016.
 */
public class ContentUmkm {
    private String nama_umkm;
    private String jen_produk;
    private String tipe;
    private String alamat;
    private String hp;
    private String pemilik;
    private String email;
    private String logo_umkm;
    private float lon,lat;

    public String getLogo() {
        return logo_umkm;
    }

    public void setLogo(String logo_umkm) {
        this.logo_umkm = logo_umkm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setJen_produk(String jen_produk) {
        this.jen_produk = jen_produk;
    }

    public String getJen_produk() {
        return jen_produk;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getPemilik() {
        return pemilik;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }

    public String getNama() {
        return nama_umkm;
    }

    public void setNama(String nama){
        this.nama_umkm = nama;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }
}
