package com.example.s333975s331153mappe3;

public class Reservasjon {
    public int reservasjonsID;
    public int romID;
    public int husID;
    public String navn;
    public String dato;
    public String tid;

    public Reservasjon(){}

    public int getReservasjonsID() {
        return reservasjonsID;
    }

    public void setReservasjonsID(int reservasjonsID) {
        this.reservasjonsID = reservasjonsID;
    }

    public int getRomID() {
        return romID;
    }

    public void setRomID(int romID) {
        this.romID = romID;
    }

    public int getHusID() {
        return husID;
    }

    public void setHusID(int husID) {
        this.husID = husID;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}
