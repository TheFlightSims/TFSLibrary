/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ public class Datum {
/*    */   String name;
/*    */   
/*    */   Ellipsoid ellipsoid;
/*    */   
/*    */   double deltaX;
/*    */   
/*    */   double deltaY;
/*    */   
/*    */   double deltaZ;
/*    */   
/* 29 */   public static Datum[] datums = new Datum[] { 
/* 29 */       new Datum("ADINDAN", Ellipsoid.CLARKE_1880, -162.0D, -12.0D, -206.0D), new Datum("ARC1950", Ellipsoid.CLARKE_1880, -143.0D, -90.0D, -294.0D), new Datum("ARC1960", Ellipsoid.CLARKE_1880, -160.0D, -8.0D, -300.0D), new Datum("Australian Geodetic 1966", Ellipsoid.AUSTRALIAN, -133.0D, -48.0D, 148.0D), new Datum("Australian Geodetic 984", Ellipsoid.AUSTRALIAN, -134.0D, -48.0D, 149.0D), new Datum("CAMP_AREA_ASTRO", Ellipsoid.INTERNATIONAL_1967, -104.0D, -129.0D, 239.0D), new Datum("Cape", Ellipsoid.CLARKE_1880, -136.0D, -108.0D, -292.0D), new Datum("European Datum 1950", Ellipsoid.INTERNATIONAL_1967, -87.0D, -98.0D, -121.0D), new Datum("European Datum 1979", Ellipsoid.INTERNATIONAL_1967, -86.0D, -98.0D, -119.0D), new Datum("Geodetic Datum 1949", Ellipsoid.INTERNATIONAL_1967, 84.0D, -22.0D, 209.0D), 
/* 29 */       new Datum("Hong Kong 1963", Ellipsoid.INTERNATIONAL_1967, -156.0D, -271.0D, -189.0D), new Datum("Hu Tzu Shan", Ellipsoid.INTERNATIONAL_1967, -634.0D, -549.0D, -201.0D), new Datum("NAD27", Ellipsoid.CLARKE_1866, -8.0D, 160.0D, 176.0D), new Datum("NAD83", Ellipsoid.GRS_1980, 0.0D, 0.0D, 0.0D), new Datum("Old Hawaiian mean", Ellipsoid.CLARKE_1866, 89.0D, -279.0D, -183.0D), new Datum("OMAN", Ellipsoid.CLARKE_1880, -346.0D, -1.0D, 224.0D), new Datum("Ordnance Survey 1936", Ellipsoid.AIRY, 375.0D, -111.0D, 431.0D), new Datum("Puerto Rico", Ellipsoid.CLARKE_1866, 11.0D, 72.0D, -101.0D), new Datum("Pulkovo 1942", Ellipsoid.KRASOVSKY, 27.0D, -135.0D, -89.0D), new Datum("PROVISIONAL_S_AMERICAN_1956", Ellipsoid.INTERNATIONAL_1967, -288.0D, 175.0D, -376.0D), 
/* 29 */       new Datum("Tokyo", Ellipsoid.BESSEL, -128.0D, 481.0D, 664.0D), new Datum("WGS72", Ellipsoid.WGS_1972, 0.0D, 0.0D, -4.5D), new Datum("WGS84", Ellipsoid.WGS_1984, 0.0D, 0.0D, 0.0D) };
/*    */   
/*    */   public Datum(String name, Ellipsoid ellipsoid, double deltaX, double deltaY, double deltaZ) {
/* 57 */     this.name = name;
/* 58 */     this.ellipsoid = ellipsoid;
/* 59 */     this.deltaX = deltaX;
/* 60 */     this.deltaY = deltaY;
/* 61 */     this.deltaZ = deltaZ;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\Datum.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */