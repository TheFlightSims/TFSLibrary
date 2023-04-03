/*     */ package com.vividsolutions.jts.linearref;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ 
/*     */ public class LengthLocationMap {
/*     */   private Geometry linearGeom;
/*     */   
/*     */   public static LinearLocation getLocation(Geometry linearGeom, double length) {
/*  60 */     LengthLocationMap locater = new LengthLocationMap(linearGeom);
/*  61 */     return locater.getLocation(length);
/*     */   }
/*     */   
/*     */   public static LinearLocation getLocation(Geometry linearGeom, double length, boolean resolveLower) {
/*  77 */     LengthLocationMap locater = new LengthLocationMap(linearGeom);
/*  78 */     return locater.getLocation(length, resolveLower);
/*     */   }
/*     */   
/*     */   public static double getLength(Geometry linearGeom, LinearLocation loc) {
/*  91 */     LengthLocationMap locater = new LengthLocationMap(linearGeom);
/*  92 */     return locater.getLength(loc);
/*     */   }
/*     */   
/*     */   public LengthLocationMap(Geometry linearGeom) {
/*  99 */     this.linearGeom = linearGeom;
/*     */   }
/*     */   
/*     */   public LinearLocation getLocation(double length) {
/* 113 */     return getLocation(length, true);
/*     */   }
/*     */   
/*     */   public LinearLocation getLocation(double length, boolean resolveLower) {
/* 128 */     double forwardLength = length;
/* 131 */     if (length < 0.0D) {
/* 132 */       double lineLen = this.linearGeom.getLength();
/* 133 */       forwardLength = lineLen + length;
/*     */     } 
/* 135 */     LinearLocation loc = getLocationForward(forwardLength);
/* 136 */     if (resolveLower)
/* 137 */       return loc; 
/* 139 */     return resolveHigher(loc);
/*     */   }
/*     */   
/*     */   private LinearLocation getLocationForward(double length) {
/* 144 */     if (length <= 0.0D)
/* 145 */       return new LinearLocation(); 
/* 147 */     double totalLength = 0.0D;
/* 149 */     LinearIterator it = new LinearIterator(this.linearGeom);
/* 150 */     while (it.hasNext()) {
/* 160 */       if (it.isEndOfLine()) {
/* 161 */         if (totalLength == length) {
/* 162 */           int compIndex = it.getComponentIndex();
/* 163 */           int segIndex = it.getVertexIndex();
/* 164 */           return new LinearLocation(compIndex, segIndex, 0.0D);
/*     */         } 
/*     */       } else {
/* 168 */         Coordinate p0 = it.getSegmentStart();
/* 169 */         Coordinate p1 = it.getSegmentEnd();
/* 170 */         double segLen = p1.distance(p0);
/* 172 */         if (totalLength + segLen > length) {
/* 173 */           double frac = (length - totalLength) / segLen;
/* 174 */           int compIndex = it.getComponentIndex();
/* 175 */           int segIndex = it.getVertexIndex();
/* 176 */           return new LinearLocation(compIndex, segIndex, frac);
/*     */         } 
/* 178 */         totalLength += segLen;
/*     */       } 
/* 181 */       it.next();
/*     */     } 
/* 184 */     return LinearLocation.getEndLocation(this.linearGeom);
/*     */   }
/*     */   
/*     */   private LinearLocation resolveHigher(LinearLocation loc) {
/* 189 */     if (!loc.isEndpoint(this.linearGeom))
/* 190 */       return loc; 
/* 191 */     int compIndex = loc.getComponentIndex();
/* 193 */     if (compIndex >= this.linearGeom.getNumGeometries() - 1)
/* 193 */       return loc; 
/*     */     do {
/* 196 */       compIndex++;
/* 198 */     } while (compIndex < this.linearGeom.getNumGeometries() - 1 && this.linearGeom.getGeometryN(compIndex).getLength() == 0.0D);
/* 200 */     return new LinearLocation(compIndex, 0, 0.0D);
/*     */   }
/*     */   
/*     */   public double getLength(LinearLocation loc) {
/* 205 */     double totalLength = 0.0D;
/* 207 */     LinearIterator it = new LinearIterator(this.linearGeom);
/* 208 */     while (it.hasNext()) {
/* 209 */       if (!it.isEndOfLine()) {
/* 210 */         Coordinate p0 = it.getSegmentStart();
/* 211 */         Coordinate p1 = it.getSegmentEnd();
/* 212 */         double segLen = p1.distance(p0);
/* 214 */         if (loc.getComponentIndex() == it.getComponentIndex() && loc.getSegmentIndex() == it.getVertexIndex())
/* 216 */           return totalLength + segLen * loc.getSegmentFraction(); 
/* 218 */         totalLength += segLen;
/*     */       } 
/* 220 */       it.next();
/*     */     } 
/* 222 */     return totalLength;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\linearref\LengthLocationMap.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */