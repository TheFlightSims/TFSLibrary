/*     */ package com.vividsolutions.jts.awt;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class PolygonShape implements Shape {
/*     */   private GeneralPath polygonPath;
/*     */   
/*     */   private GeneralPath ringPath;
/*     */   
/*     */   public PolygonShape(Coordinate[] shellVertices, Collection holeVerticesCollection) {
/*  69 */     this.polygonPath = toPath(shellVertices);
/*  71 */     for (Iterator<Coordinate[]> i = holeVerticesCollection.iterator(); i.hasNext(); ) {
/*  72 */       Coordinate[] holeVertices = i.next();
/*  73 */       this.polygonPath.append(toPath(holeVertices), false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public PolygonShape() {}
/*     */   
/*     */   void addToRing(Point2D p) {
/*  83 */     if (this.ringPath == null) {
/*  84 */       this.ringPath = new GeneralPath(0);
/*  85 */       this.ringPath.moveTo((float)p.getX(), (float)p.getY());
/*     */     } else {
/*  88 */       this.ringPath.lineTo((float)p.getX(), (float)p.getY());
/*     */     } 
/*     */   }
/*     */   
/*     */   void endRing() {
/*  94 */     this.ringPath.closePath();
/*  95 */     if (this.polygonPath == null) {
/*  96 */       this.polygonPath = this.ringPath;
/*     */     } else {
/*  99 */       this.polygonPath.append(this.ringPath, false);
/*     */     } 
/* 101 */     this.ringPath = null;
/*     */   }
/*     */   
/*     */   private GeneralPath toPath(Coordinate[] coordinates) {
/* 113 */     GeneralPath path = new GeneralPath(0, coordinates.length);
/* 115 */     if (coordinates.length > 0) {
/* 116 */       path.moveTo((float)(coordinates[0]).x, (float)(coordinates[0]).y);
/* 117 */       for (int i = 0; i < coordinates.length; i++)
/* 118 */         path.lineTo((float)(coordinates[i]).x, (float)(coordinates[i]).y); 
/*     */     } 
/* 121 */     return path;
/*     */   }
/*     */   
/*     */   public Rectangle getBounds() {
/* 125 */     return this.polygonPath.getBounds();
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/* 129 */     return this.polygonPath.getBounds2D();
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 133 */     return this.polygonPath.contains(x, y);
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D p) {
/* 137 */     return this.polygonPath.contains(p);
/*     */   }
/*     */   
/*     */   public boolean intersects(double x, double y, double w, double h) {
/* 141 */     return this.polygonPath.intersects(x, y, w, h);
/*     */   }
/*     */   
/*     */   public boolean intersects(Rectangle2D r) {
/* 145 */     return this.polygonPath.intersects(r);
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y, double w, double h) {
/* 149 */     return this.polygonPath.contains(x, y, w, h);
/*     */   }
/*     */   
/*     */   public boolean contains(Rectangle2D r) {
/* 153 */     return this.polygonPath.contains(r);
/*     */   }
/*     */   
/*     */   public PathIterator getPathIterator(AffineTransform at) {
/* 157 */     return this.polygonPath.getPathIterator(at);
/*     */   }
/*     */   
/*     */   public PathIterator getPathIterator(AffineTransform at, double flatness) {
/* 161 */     return getPathIterator(at, flatness);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\awt\PolygonShape.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */