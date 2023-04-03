/*     */ package com.vividsolutions.jts.operation.buffer;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateList;
/*     */ 
/*     */ public class BufferInputLineSimplifier {
/*     */   private static final int INIT = 0;
/*     */   
/*     */   private static final int DELETE = 1;
/*     */   
/*     */   private static final int KEEP = 1;
/*     */   
/*     */   private Coordinate[] inputLine;
/*     */   
/*     */   private double distanceTol;
/*     */   
/*     */   private byte[] isDeleted;
/*     */   
/*     */   public static Coordinate[] simplify(Coordinate[] inputLine, double distanceTol) {
/*  85 */     BufferInputLineSimplifier simp = new BufferInputLineSimplifier(inputLine);
/*  86 */     return simp.simplify(distanceTol);
/*     */   }
/*     */   
/*  97 */   private int angleOrientation = 1;
/*     */   
/*     */   private static final int NUM_PTS_TO_CHECK = 10;
/*     */   
/*     */   public BufferInputLineSimplifier(Coordinate[] inputLine) {
/* 100 */     this.inputLine = inputLine;
/*     */   }
/*     */   
/*     */   public Coordinate[] simplify(double distanceTol) {
/* 115 */     this.distanceTol = Math.abs(distanceTol);
/* 116 */     if (distanceTol < 0.0D)
/* 117 */       this.angleOrientation = -1; 
/* 120 */     this.isDeleted = new byte[this.inputLine.length];
/* 122 */     boolean isChanged = false;
/*     */     do {
/* 124 */       isChanged = deleteShallowConcavities();
/* 125 */     } while (isChanged);
/* 127 */     return collapseLine();
/*     */   }
/*     */   
/*     */   private boolean deleteShallowConcavities() {
/* 142 */     int index = 1;
/* 143 */     int maxIndex = this.inputLine.length - 1;
/* 145 */     int midIndex = findNextNonDeletedIndex(index);
/* 146 */     int lastIndex = findNextNonDeletedIndex(midIndex);
/* 148 */     boolean isChanged = false;
/* 149 */     while (lastIndex < this.inputLine.length) {
/* 151 */       boolean isMiddleVertexDeleted = false;
/* 152 */       if (isDeletable(index, midIndex, lastIndex, this.distanceTol)) {
/* 154 */         this.isDeleted[midIndex] = 1;
/* 155 */         isMiddleVertexDeleted = true;
/* 156 */         isChanged = true;
/*     */       } 
/* 159 */       if (isMiddleVertexDeleted) {
/* 160 */         index = lastIndex;
/*     */       } else {
/* 162 */         index = midIndex;
/*     */       } 
/* 164 */       midIndex = findNextNonDeletedIndex(index);
/* 165 */       lastIndex = findNextNonDeletedIndex(midIndex);
/*     */     } 
/* 167 */     return isChanged;
/*     */   }
/*     */   
/*     */   private int findNextNonDeletedIndex(int index) {
/* 178 */     int next = index + 1;
/* 179 */     while (next < this.inputLine.length && this.isDeleted[next] == 1)
/* 180 */       next++; 
/* 181 */     return next;
/*     */   }
/*     */   
/*     */   private Coordinate[] collapseLine() {
/* 186 */     CoordinateList coordList = new CoordinateList();
/* 187 */     for (int i = 0; i < this.inputLine.length; i++) {
/* 188 */       if (this.isDeleted[i] != 1)
/* 189 */         coordList.add(this.inputLine[i]); 
/*     */     } 
/* 192 */     return coordList.toCoordinateArray();
/*     */   }
/*     */   
/*     */   private boolean isDeletable(int i0, int i1, int i2, double distanceTol) {
/* 197 */     Coordinate p0 = this.inputLine[i0];
/* 198 */     Coordinate p1 = this.inputLine[i1];
/* 199 */     Coordinate p2 = this.inputLine[i2];
/* 201 */     if (!isConcave(p0, p1, p2))
/* 201 */       return false; 
/* 202 */     if (!isShallow(p0, p1, p2, distanceTol))
/* 202 */       return false; 
/* 207 */     return isShallowSampled(p0, p1, i0, i2, distanceTol);
/*     */   }
/*     */   
/*     */   private boolean isShallowConcavity(Coordinate p0, Coordinate p1, Coordinate p2, double distanceTol) {
/* 212 */     int orientation = CGAlgorithms.computeOrientation(p0, p1, p2);
/* 213 */     boolean isAngleToSimplify = (orientation == this.angleOrientation);
/* 214 */     if (!isAngleToSimplify)
/* 215 */       return false; 
/* 217 */     double dist = CGAlgorithms.distancePointLine(p1, p0, p2);
/* 218 */     return (dist < distanceTol);
/*     */   }
/*     */   
/*     */   private boolean isShallowSampled(Coordinate p0, Coordinate p2, int i0, int i2, double distanceTol) {
/* 238 */     int inc = (i2 - i0) / 10;
/* 239 */     if (inc <= 0)
/* 239 */       inc = 1; 
/*     */     int i;
/* 241 */     for (i = i0; i < i2; i += inc) {
/* 242 */       if (!isShallow(p0, p2, this.inputLine[i], distanceTol))
/* 242 */         return false; 
/*     */     } 
/* 244 */     return true;
/*     */   }
/*     */   
/*     */   private boolean isShallow(Coordinate p0, Coordinate p1, Coordinate p2, double distanceTol) {
/* 249 */     double dist = CGAlgorithms.distancePointLine(p1, p0, p2);
/* 250 */     return (dist < distanceTol);
/*     */   }
/*     */   
/*     */   private boolean isConcave(Coordinate p0, Coordinate p1, Coordinate p2) {
/* 256 */     int orientation = CGAlgorithms.computeOrientation(p0, p1, p2);
/* 257 */     boolean isConcave = (orientation == this.angleOrientation);
/* 258 */     return isConcave;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\buffer\BufferInputLineSimplifier.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */