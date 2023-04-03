/*    */ package com.world2xplane.OSM;
/*    */ 
/*    */ public class HotspotEntry {
/*    */   private String name;
/*    */   
/*    */   private String identifier;
/*    */   
/*    */   private double longitude;
/*    */   
/*    */   private double latitude;
/*    */   
/* 33 */   private int population = 0;
/*    */   
/*    */   public HotspotEntry(String name, String identifier, double longitude, double latitude) {
/* 36 */     this.name = name;
/* 37 */     this.longitude = longitude;
/* 38 */     this.latitude = latitude;
/* 39 */     this.identifier = identifier;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 43 */     return this.name;
/*    */   }
/*    */   
/*    */   public double getLongitude() {
/* 47 */     return this.longitude;
/*    */   }
/*    */   
/*    */   public double getLatitude() {
/* 51 */     return this.latitude;
/*    */   }
/*    */   
/*    */   public String getIdentifier() {
/* 55 */     return this.identifier;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 59 */     return String.format("%s=%s %f %f Pop:%d", new Object[] { this.identifier, this.name, Double.valueOf(this.longitude), Double.valueOf(this.latitude), Integer.valueOf(this.population) });
/*    */   }
/*    */   
/*    */   public int getPopulation() {
/* 63 */     return this.population;
/*    */   }
/*    */   
/*    */   public void setPopulation(int population) {
/* 67 */     this.population = population;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\OSM\HotspotEntry.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */