package com.mapup.geofence.util;

import java.util.List;

public class PolygonUtil {

    private PolygonUtil() {
    }

    /**
     * Checks whether a point lies inside a polygon.
     *
     * latitude  -> vehicle latitude
     * longitude -> vehicle longitude
     *
     * polygon format:
     *
     * [
     *   [77.0266, 28.4595],
     *   [77.0300, 28.4600],
     *   [77.0290, 28.4550],
     *   [77.0266, 28.4595]
     * ]
     *
     * Each point is:
     * [longitude, latitude]
     */
    public static boolean isPointInsidePolygon(
            double latitude,
            double longitude,
            List<List<Double>> polygon) {

        int n = polygon.size();

        if (n < 3) {
            return false;
        }

        boolean inside = false;

        int j = n - 1;

        for (int i = 0; i < n; i++) {

            double xi = polygon.get(i).get(0); // longitude
            double yi = polygon.get(i).get(1); // latitude

            double xj = polygon.get(j).get(0); // longitude
            double yj = polygon.get(j).get(1); // latitude

            boolean intersects =
                    ((yi > latitude) != (yj > latitude))
                            &&
                            (longitude <
                                    (xj - xi) * (latitude - yi)
                                            / (yj - yi)
                                            + xi);

            if (intersects) {
                inside = !inside;
            }

            j = i;
        }

        return inside;
    }
}