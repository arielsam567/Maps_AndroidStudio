package com.example.maps_android;

public class Contato {
    private String nome;
    private String email;
    private Double longitude;
    private Double latitude;
    //http://www.mocky.io/v2/5cdb4544300000640068cc7b


    public Contato(String nome, String email, double longitude, double latitude) {
        this.nome = nome;
        this.email = email;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}

