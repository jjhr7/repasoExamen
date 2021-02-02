package com.example.jjherram.repasoexamen;

public class Producto {
    public long Dia;
    public String DiaSemana;
    public double precio;

    public Producto(){

    }

    public Producto(long dia, String diaSemana, double precio) {
        Dia = dia;
        DiaSemana = diaSemana;
        this.precio = precio;
    }

    public long getDia() {
        return Dia;
    }

    public void setDia(long dia) {
        Dia = dia;
    }

    public String getDiaSemana() {
        return DiaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        DiaSemana = diaSemana;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "Dia=" + Dia +
                ", DiaSemana='" + DiaSemana + '\'' +
                ", precio=" + precio +
                '}';
    }


}
