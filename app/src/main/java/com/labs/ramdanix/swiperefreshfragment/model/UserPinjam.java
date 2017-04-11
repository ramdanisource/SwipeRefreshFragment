package com.labs.ramdanix.swiperefreshfragment.model;


/**
 * Created by ramdani on 13/03/17.
 */

public class UserPinjam {



    String uuid_pinjam;
    String photo;
    int nis;
    String nama;
    String kelas;
    int petugas;
    String waktu_pinjam;

    public UserPinjam(String uuid_pinjam, String photo, int nis, String nama, String kelas, int petugas, String waktu_pinjam) {
        this.uuid_pinjam = uuid_pinjam;
        this.photo = photo;
        this.nis = nis;
        this.nama = nama;
        this.kelas = kelas;
        this.petugas = petugas;
        this.waktu_pinjam = waktu_pinjam;
    }

    public UserPinjam(String nama, String kelas, String waktu_pinjam) {
        this.nama = nama;
        this.kelas = kelas;
        this.waktu_pinjam = waktu_pinjam;
    }

    public String getUuid_pinjam() {
        return uuid_pinjam;
    }

    public void setUuid_pinjam(String uuid_pinjam) {
        this.uuid_pinjam = uuid_pinjam;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getNis() {
        return nis;
    }

    public void setNis(int nis) {
        this.nis = nis;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public int getPetugas() {
        return petugas;
    }

    public void setPetugas(int petugas) {
        this.petugas = petugas;
    }

    public String getWaktu_pinjam() {
        return waktu_pinjam;
    }

    public void setWaktu_pinjam(String waktu_pinjam) {
        this.waktu_pinjam = waktu_pinjam;
    }


}
