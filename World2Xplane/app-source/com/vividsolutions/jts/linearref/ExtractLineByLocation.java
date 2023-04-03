/*     */ package com.vividsolutions.jts.linearref;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateList;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ 
/*     */ class ExtractLineByLocation {
/*     */   private Geometry line;
/*     */   
/*     */   public static Geometry extract(Geometry line, LinearLocation start, LinearLocation end) {
/*  58 */     ExtractLineByLocation ls = new ExtractLineByLocation(line);
/*  59 */     return ls.extract(start, end);
/*     */   }
/*     */   
/*     */   public ExtractLineByLocation(Geometry line) {
/*  65 */     this.line = line;
/*     */   }
/*     */   
/*     */   public Geometry extract(LinearLocation start, LinearLocation end) {
/*  78 */     if (end.compareTo(start) < 0)
/*  79 */       return reverse(computeLinear(end, start)); 
/*  81 */     return computeLinear(start, end);
/*     */   }
/*     */   
/*     */   private Geometry reverse(Geometry linear) {
/*  86 */     if (linear instanceof LineString)
/*  87 */       return ((LineString)linear).reverse(); 
/*  88 */     if (linear instanceof MultiLineString)
/*  89 */       return ((MultiLineString)linear).reverse(); 
/*  90 */     Assert.shouldNeverReachHere("non-linear geometry encountered");
/*  91 */     return null;
/*     */   }
/*     */   
/*     */   private LineString computeLine(LinearLocation start, LinearLocation end) {
/* 102 */     Coordinate[] coordinates = this.line.getCoordinates();
/* 103 */     CoordinateList newCoordinates = new CoordinateList();
/* 105 */     int startSegmentIndex = start.getSegmentIndex();
/* 106 */     if (start.getSegmentFraction() > 0.0D)
/* 107 */       startSegmentIndex++; 
/* 108 */     int lastSegmentIndex = end.getSegmentIndex();
/* 109 */     if (end.getSegmentFraction() == 1.0D)
/* 110 */       lastSegmentIndex++; 
/* 111 */     if (lastSegmentIndex >= coordinates.length)
/* 112 */       lastSegmentIndex = coordinates.length - 1; 
/* 116 */     if (!start.isVertex())
/* 117 */       newCoordinates.add(start.getCoordinate(this.line)); 
/* 118 */     for (int i = startSegmentIndex; i <= lastSegmentIndex; i++)
/* 119 */       newCoordinates.add(coordinates[i]); 
/* 121 */     if (!end.isVertex())
/* 122 */       newCoordinates.add(end.getCoordinate(this.line)); 
/* 125 */     if (newCoordinates.size() <= 0)
/* 126 */       newCoordinates.add(start.getCoordinate(this.line)); 
/* 128 */     Coordinate[] newCoordinateArray = newCoordinates.toCoordinateArray();
/* 134 */     if (newCoordinateArray.length <= 1)
/* 135 */       newCoordinateArray = new Coordinate[] { newCoordinateArray[0], newCoordinateArray[0] }; 
/* 137 */     return this.line.getFactory().createLineString(newCoordinateArray);
/*     */   }
/*     */   
/*     */   private Geometry computeLinear(LinearLocation start, LinearLocation end) {
/* 149 */     LinearGeometryBuilder builder = new LinearGeometryBuilder(this.line.getFactory());
/* 150 */     builder.setFixInvalidLines(true);
/* 152 */     if (!start.isVertex())
/* 153 */       builder.add(start.getCoordinate(this.line)); 
/* 155 */     for (LinearIterator it = new LinearIterator(this.line, start); it.hasNext() && 
/* 156 */       end.compareLocationValues(it.getComponentIndex(), it.getVertexIndex(), 0.0D) >= 0; it.next()) {
/* 160 */       Coordinate pt = it.getSegmentStart();
/* 161 */       builder.add(pt);
/* 162 */       if (it.isEndOfLine())
/* 163 */         builder.endLine(); 
/*     */     } 
/* 165 */     if (!end.isVertex())
/* 166 */       builder.add(end.getCoordinate(this.line)); 
/* 168 */     return builder.getGeometry();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\linearref\ExtractLineByLocation.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */