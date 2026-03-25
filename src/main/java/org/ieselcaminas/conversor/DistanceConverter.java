package org.ieselcaminas.conversor;

public class DistanceConverter {

    private static final double KM_PER_MILE = 1.609344;

    /**
     * Convierte millas a kilómetros.
     */
    public static double milesToKm(double miles) {
        return miles * KM_PER_MILE;
    }

    /**
     * Convierte kilómetros a millas.
     */
    public static double kmToMiles(double km) {
        return km / KM_PER_MILE;
    }

    /**
     * Formatea un número con N decimales.
     */
    public static String format(double value, int decimals) {
        return String.format("%." + decimals + "f", value);
    }

}

