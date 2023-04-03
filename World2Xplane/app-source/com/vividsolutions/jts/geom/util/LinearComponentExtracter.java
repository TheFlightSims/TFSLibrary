/*     */ package com.vividsolutions.jts.geom.util;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryComponentFilter;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class LinearComponentExtracter implements GeometryComponentFilter {
/*     */   private Collection lines;
/*     */   
/*     */   public static Collection getLines(Collection geoms, Collection lines) {
/*  60 */     for (Iterator<Geometry> i = geoms.iterator(); i.hasNext(); ) {
/*  61 */       Geometry g = i.next();
/*  62 */       getLines(g, lines);
/*     */     } 
/*  64 */     return lines;
/*     */   }
/*     */   
/*     */   public static Collection getLines(Collection geoms, Collection lines, boolean forceToLineString) {
/*  78 */     for (Iterator<Geometry> i = geoms.iterator(); i.hasNext(); ) {
/*  79 */       Geometry g = i.next();
/*  80 */       getLines(g, lines, forceToLineString);
/*     */     } 
/*  82 */     return lines;
/*     */   }
/*     */   
/*     */   public static Collection getLines(Geometry geom, Collection<Geometry> lines) {
/*  95 */     if (geom instanceof LineString) {
/*  96 */       lines.add(geom);
/*     */     } else {
/*  99 */       geom.apply(new LinearComponentExtracter(lines));
/*     */     } 
/* 101 */     return lines;
/*     */   }
/*     */   
/*     */   public static Collection getLines(Geometry geom, Collection lines, boolean forceToLineString) {
/* 115 */     geom.apply(new LinearComponentExtracter(lines, forceToLineString));
/* 116 */     return lines;
/*     */   }
/*     */   
/*     */   public static List getLines(Geometry geom) {
/* 130 */     return getLines(geom, false);
/*     */   }
/*     */   
/*     */   public static List getLines(Geometry geom, boolean forceToLineString) {
/* 145 */     List lines = new ArrayList();
/* 146 */     geom.apply(new LinearComponentExtracter(lines, forceToLineString));
/* 147 */     return lines;
/*     */   }
/*     */   
/*     */   private boolean isForcedToLineString = false;
/*     */   
/*     */   public LinearComponentExtracter(Collection lines) {
/* 158 */     this.lines = lines;
/*     */   }
/*     */   
/*     */   public LinearComponentExtracter(Collection lines, boolean isForcedToLineString) {
/* 166 */     this.lines = lines;
/* 167 */     this.isForcedToLineString = isForcedToLineString;
/*     */   }
/*     */   
/*     */   public void setForceToLineString(boolean isForcedToLineString) {
/* 178 */     this.isForcedToLineString = isForcedToLineString;
/*     */   }
/*     */   
/*     */   public void filter(Geometry geom) {
/* 183 */     if (this.isForcedToLineString && geom instanceof LinearRing) {
/* 184 */       LineString line = geom.getFactory().createLineString(((LinearRing)geom).getCoordinateSequence());
/* 185 */       this.lines.add(line);
/*     */       return;
/*     */     } 
/* 189 */     if (geom instanceof LineString)
/* 190 */       this.lines.add(geom); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geo\\util\LinearComponentExtracter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */