/*     */ package com.vividsolutions.jtsexample.io.gml2;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequenceFactory;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequences;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ 
/*     */ class FixingGeometryFactory extends GeometryFactory {
/*     */   public LinearRing createLinearRing(CoordinateSequence cs) {
/* 157 */     if (cs.getCoordinate(0).equals(cs.getCoordinate(cs.size() - 1)))
/* 158 */       return super.createLinearRing(cs); 
/* 161 */     CoordinateSequenceFactory csFact = getCoordinateSequenceFactory();
/* 162 */     CoordinateSequence csNew = csFact.create(cs.size() + 1, cs.getDimension());
/* 163 */     CoordinateSequences.copy(cs, 0, csNew, 0, cs.size());
/* 164 */     CoordinateSequences.copyCoord(csNew, 0, csNew, csNew.size() - 1);
/* 165 */     return super.createLinearRing(csNew);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jtsexample\io\gml2\FixingGeometryFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */