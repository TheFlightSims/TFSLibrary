/*     */ package com.vividsolutions.jts.linearref;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ 
/*     */ public class LinearIterator {
/*     */   private Geometry linearGeom;
/*     */   
/*     */   private final int numLines;
/*     */   
/*     */   private LineString currentLine;
/*     */   
/*     */   private static int segmentEndVertexIndex(LinearLocation loc) {
/*  59 */     if (loc.getSegmentFraction() > 0.0D)
/*  60 */       return loc.getSegmentIndex() + 1; 
/*  61 */     return loc.getSegmentIndex();
/*     */   }
/*     */   
/*  71 */   private int componentIndex = 0;
/*     */   
/*  72 */   private int vertexIndex = 0;
/*     */   
/*     */   public LinearIterator(Geometry linear) {
/*  81 */     this(linear, 0, 0);
/*     */   }
/*     */   
/*     */   public LinearIterator(Geometry linear, LinearLocation start) {
/*  93 */     this(linear, start.getComponentIndex(), segmentEndVertexIndex(start));
/*     */   }
/*     */   
/*     */   public LinearIterator(Geometry linearGeom, int componentIndex, int vertexIndex) {
/* 107 */     if (!(linearGeom instanceof com.vividsolutions.jts.geom.Lineal))
/* 108 */       throw new IllegalArgumentException("Lineal geometry is required"); 
/* 109 */     this.linearGeom = linearGeom;
/* 110 */     this.numLines = linearGeom.getNumGeometries();
/* 111 */     this.componentIndex = componentIndex;
/* 112 */     this.vertexIndex = vertexIndex;
/* 113 */     loadCurrentLine();
/*     */   }
/*     */   
/*     */   private void loadCurrentLine() {
/* 118 */     if (this.componentIndex >= this.numLines) {
/* 119 */       this.currentLine = null;
/*     */       return;
/*     */     } 
/* 122 */     this.currentLine = (LineString)this.linearGeom.getGeometryN(this.componentIndex);
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/* 135 */     if (this.componentIndex >= this.numLines)
/* 135 */       return false; 
/* 136 */     if (this.componentIndex == this.numLines - 1 && this.vertexIndex >= this.currentLine.getNumPoints())
/* 138 */       return false; 
/* 139 */     return true;
/*     */   }
/*     */   
/*     */   public void next() {
/* 147 */     if (!hasNext())
/*     */       return; 
/* 149 */     this.vertexIndex++;
/* 150 */     if (this.vertexIndex >= this.currentLine.getNumPoints()) {
/* 151 */       this.componentIndex++;
/* 152 */       loadCurrentLine();
/* 153 */       this.vertexIndex = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEndOfLine() {
/* 164 */     if (this.componentIndex >= this.numLines)
/* 164 */       return false; 
/* 166 */     if (this.vertexIndex < this.currentLine.getNumPoints() - 1)
/* 167 */       return false; 
/* 168 */     return true;
/*     */   }
/*     */   
/*     */   public int getComponentIndex() {
/* 175 */     return this.componentIndex;
/*     */   }
/*     */   
/*     */   public int getVertexIndex() {
/* 181 */     return this.vertexIndex;
/*     */   }
/*     */   
/*     */   public LineString getLine() {
/* 187 */     return this.currentLine;
/*     */   }
/*     */   
/*     */   public Coordinate getSegmentStart() {
/* 194 */     return this.currentLine.getCoordinateN(this.vertexIndex);
/*     */   }
/*     */   
/*     */   public Coordinate getSegmentEnd() {
/* 205 */     if (this.vertexIndex < getLine().getNumPoints() - 1)
/* 206 */       return this.currentLine.getCoordinateN(this.vertexIndex + 1); 
/* 207 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\linearref\LinearIterator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */