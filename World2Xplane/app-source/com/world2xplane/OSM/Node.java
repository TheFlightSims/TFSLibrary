/*     */ package com.world2xplane.OSM;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.world2xplane.Geom.EastNorth;
/*     */ import com.world2xplane.Geom.GeomUtils;
/*     */ import com.world2xplane.Rules.AcceptingRule;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import math.geom2d.Point2D;
/*     */ 
/*     */ public class Node implements Serializable {
/*     */   private long id;
/*     */   
/*     */   private double lon;
/*     */   
/*     */   private double lat;
/*     */   
/*  39 */   private float minHeight = 0.0F;
/*     */   
/*  40 */   private ArrayList<Tag> tags = new ArrayList<>();
/*     */   
/*     */   private List<AcceptingRule> rules;
/*     */   
/*     */   public Node() {}
/*     */   
/*     */   public Node(long id, double lonf, double latf) {
/*  48 */     this.lon = lonf;
/*  49 */     this.lat = latf;
/*  50 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Node(Point2D point) {
/*  54 */     this.lon = point.x();
/*  55 */     this.lat = point.y();
/*     */   }
/*     */   
/*     */   public long getId() {
/*  59 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(long id) {
/*  63 */     this.id = id;
/*     */   }
/*     */   
/*     */   public double getLat() {
/*  67 */     return this.lat;
/*     */   }
/*     */   
/*     */   public void setLat(double lat) {
/*  71 */     this.lat = lat;
/*     */   }
/*     */   
/*     */   public double getLon() {
/*  75 */     return this.lon;
/*     */   }
/*     */   
/*     */   public void setLon(double lon) {
/*  79 */     this.lon = lon;
/*     */   }
/*     */   
/*     */   public void setLat(long lat) {
/*  83 */     this.lat = lat;
/*     */   }
/*     */   
/*     */   public ArrayList<Tag> getTags() {
/*  87 */     return this.tags;
/*     */   }
/*     */   
/*     */   public void setTags(ArrayList<Tag> tags) {
/*  91 */     this.tags = tags;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/*  95 */     if (other == null)
/*  95 */       return false; 
/*  96 */     if (other == this)
/*  96 */       return true; 
/*  97 */     if (!(other instanceof Node))
/*  97 */       return false; 
/*  98 */     Node otherMyClass = (Node)other;
/*  99 */     return (otherMyClass.lon == this.lon && otherMyClass.lat == this.lat);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 104 */     return Coordinate.hashCode(this.lon) + Coordinate.hashCode(this.lat);
/*     */   }
/*     */   
/*     */   public Point2D getCoord() {
/* 108 */     return new Point2D(this.lon, this.lat);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 112 */     return String.format("Node: " + this.lon + "  " + this.lat, new Object[0]);
/*     */   }
/*     */   
/*     */   public EastNorth getEastNorth() {
/* 116 */     Point2D point = GeomUtils.toMercator(getCoord());
/* 117 */     return new EastNorth(point.x(), point.y());
/*     */   }
/*     */   
/*     */   public void setFromEastNorth(EastNorth eastNorth) {
/* 121 */     Point2D point = new Point2D(eastNorth.getX(), eastNorth.getY());
/* 122 */     this.lat = point.y();
/* 123 */     this.lon = point.x();
/*     */   }
/*     */   
/*     */   public void setRules(List<AcceptingRule> rules) {
/* 127 */     this.rules = rules;
/*     */   }
/*     */   
/*     */   public List<AcceptingRule> getRules() {
/* 131 */     return this.rules;
/*     */   }
/*     */   
/*     */   public float getMinHeight() {
/* 135 */     return this.minHeight;
/*     */   }
/*     */   
/*     */   public void setMinHeight(float minHeight) {
/* 139 */     this.minHeight = minHeight;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\OSM\Node.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */