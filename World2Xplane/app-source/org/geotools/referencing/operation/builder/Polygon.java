/*     */ package org.geotools.referencing.operation.builder;
/*     */ 
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.geotools.geometry.DirectPosition2D;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ 
/*     */ class Polygon {
/*     */   private DirectPosition[] vertices;
/*     */   
/*     */   Polygon(DirectPosition[] coordinates) {
/*  47 */     this.vertices = coordinates;
/*     */   }
/*     */   
/*     */   public void setCoordinates(DirectPosition[] coordinates) {
/*  56 */     this.vertices = coordinates;
/*     */   }
/*     */   
/*     */   public DirectPosition[] getPoints() {
/*  65 */     return this.vertices;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  74 */     String wkt = "";
/*  76 */     for (int i = 0; i < this.vertices.length; i++) {
/*  77 */       wkt = wkt + this.vertices[i].getCoordinate()[0] + " " + this.vertices[i].getCoordinate()[1];
/*  80 */       if (i != this.vertices.length - 1)
/*  81 */         wkt = wkt + ", "; 
/*     */     } 
/*  85 */     return "LINESTRING (" + wkt + ")";
/*     */   }
/*     */   
/*     */   protected GeneralPath generateGeneralPath(DirectPosition[] points) {
/*  96 */     GeneralPath ring = new GeneralPath();
/*  99 */     ring.moveTo((float)points[0].getCoordinate()[0], (float)points[0].getCoordinate()[1]);
/* 103 */     for (int i = 1; i < points.length; i++)
/* 104 */       ring.lineTo((float)points[i].getCoordinate()[0], (float)points[i].getCoordinate()[1]); 
/* 108 */     return ring;
/*     */   }
/*     */   
/*     */   protected boolean containsOrIsVertex(DirectPosition dp) {
/* 120 */     if (generateGeneralPath(this.vertices).contains((Point2D)dp) || hasVertex(dp))
/* 122 */       return true; 
/* 125 */     return false;
/*     */   }
/*     */   
/*     */   public boolean hasVertex(DirectPosition p) {
/* 136 */     for (int i = 0; i < this.vertices.length; i++) {
/* 137 */       if (p == this.vertices[i])
/* 138 */         return true; 
/*     */     } 
/* 142 */     return false;
/*     */   }
/*     */   
/*     */   protected void enlarge(double scale) {
/* 152 */     double sumX = 0.0D;
/* 153 */     double sumY = 0.0D;
/*     */     int i;
/* 155 */     for (i = 0; i < this.vertices.length; i++) {
/* 156 */       sumX += this.vertices[i].getCoordinate()[0];
/* 157 */       sumY += this.vertices[i].getCoordinate()[1];
/*     */     } 
/* 161 */     sumX /= this.vertices.length;
/* 162 */     sumY /= this.vertices.length;
/* 165 */     for (i = 0; i < this.vertices.length; i++) {
/* 166 */       this.vertices[i].getCoordinate()[0] = scale * (this.vertices[i].getCoordinate()[0] - sumX) + sumX;
/* 168 */       this.vertices[i].getCoordinate()[1] = scale * (this.vertices[i].getCoordinate()[1] - sumY) + sumY;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected List<DirectPosition> reduce() {
/* 181 */     ArrayList<DirectPosition> redCoords = new ArrayList<DirectPosition>();
/* 183 */     for (int i = 0; i < this.vertices.length; i++)
/* 184 */       redCoords.add(new DirectPosition2D(this.vertices[i].getCoordinateReferenceSystem(), this.vertices[i].getCoordinate()[0] - this.vertices[0].getCoordinate()[0], this.vertices[i].getCoordinate()[1] - this.vertices[0].getCoordinate()[1])); 
/* 192 */     return redCoords;
/*     */   }
/*     */   
/*     */   protected boolean containsAll(List<DirectPosition> coordinate) {
/* 204 */     for (Iterator<DirectPosition> i = coordinate.iterator(); i.hasNext();) {
/* 205 */       if (!containsOrIsVertex(i.next()))
/* 206 */         return false; 
/*     */     } 
/* 210 */     return true;
/*     */   }
/*     */   
/*     */   public Polygon clone() {
/* 220 */     return new Polygon(this.vertices);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\builder\Polygon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */