/*    */ package com.vividsolutions.jtsexample.geom;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.GeometryFactory;
/*    */ import com.vividsolutions.jts.geom.Polygon;
/*    */ 
/*    */ public class ExtendedCoordinateExample {
/*    */   public static void main(String[] args) {
/* 47 */     ExtendedCoordinateSequenceFactory seqFact = ExtendedCoordinateSequenceFactory.instance();
/* 49 */     ExtendedCoordinate[] array1 = { new ExtendedCoordinate(0.0D, 0.0D, 0.0D, 91.0D), new ExtendedCoordinate(10.0D, 0.0D, 0.0D, 92.0D), new ExtendedCoordinate(10.0D, 10.0D, 0.0D, 93.0D), new ExtendedCoordinate(0.0D, 10.0D, 0.0D, 94.0D), new ExtendedCoordinate(0.0D, 0.0D, 0.0D, 91.0D) };
/* 56 */     CoordinateSequence seq1 = seqFact.create((Coordinate[])array1);
/* 58 */     CoordinateSequence seq2 = seqFact.create((Coordinate[])new ExtendedCoordinate[] { new ExtendedCoordinate(5.0D, 5.0D, 0.0D, 91.0D), new ExtendedCoordinate(15.0D, 5.0D, 0.0D, 92.0D), new ExtendedCoordinate(15.0D, 15.0D, 0.0D, 93.0D), new ExtendedCoordinate(5.0D, 15.0D, 0.0D, 94.0D), new ExtendedCoordinate(5.0D, 5.0D, 0.0D, 91.0D) });
/* 67 */     GeometryFactory fact = new GeometryFactory(ExtendedCoordinateSequenceFactory.instance());
/* 70 */     Polygon polygon1 = fact.createPolygon(fact.createLinearRing(seq1), null);
/* 71 */     Polygon polygon2 = fact.createPolygon(fact.createLinearRing(seq2), null);
/* 73 */     System.out.println("WKT for g1: " + polygon1);
/* 74 */     System.out.println("Internal rep for g1: " + polygon1.getExteriorRing().getCoordinateSequence());
/* 76 */     System.out.println("WKT for g2: " + polygon2);
/* 77 */     System.out.println("Internal rep for g2: " + polygon2.getExteriorRing().getCoordinateSequence());
/* 79 */     Geometry gInt = polygon1.intersection((Geometry)polygon2);
/* 81 */     System.out.println("WKT for gInt: " + gInt);
/* 82 */     System.out.println("Internal rep for gInt: " + ((Polygon)gInt).getExteriorRing().getCoordinateSequence());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jtsexample\geom\ExtendedCoordinateExample.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */