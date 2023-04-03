/*     */ package com.vividsolutions.jts.operation.predicate;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ 
/*     */ public class RectangleIntersects {
/*     */   private Polygon rectangle;
/*     */   
/*     */   private Envelope rectEnv;
/*     */   
/*     */   public static boolean intersects(Polygon rectangle, Geometry b) {
/*  69 */     RectangleIntersects rp = new RectangleIntersects(rectangle);
/*  70 */     return rp.intersects(b);
/*     */   }
/*     */   
/*     */   public RectangleIntersects(Polygon rectangle) {
/*  85 */     this.rectangle = rectangle;
/*  86 */     this.rectEnv = rectangle.getEnvelopeInternal();
/*     */   }
/*     */   
/*     */   public boolean intersects(Geometry geom) {
/*  98 */     if (!this.rectEnv.intersects(geom.getEnvelopeInternal()))
/*  99 */       return false; 
/* 105 */     EnvelopeIntersectsVisitor visitor = new EnvelopeIntersectsVisitor(this.rectEnv);
/* 106 */     visitor.applyTo(geom);
/* 107 */     if (visitor.intersects())
/* 108 */       return true; 
/* 113 */     GeometryContainsPointVisitor ecpVisitor = new GeometryContainsPointVisitor(this.rectangle);
/* 114 */     ecpVisitor.applyTo(geom);
/* 115 */     if (ecpVisitor.containsPoint())
/* 116 */       return true; 
/* 121 */     RectangleIntersectsSegmentVisitor riVisitor = new RectangleIntersectsSegmentVisitor(this.rectangle);
/* 122 */     riVisitor.applyTo(geom);
/* 123 */     if (riVisitor.intersects())
/* 124 */       return true; 
/* 126 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\predicate\RectangleIntersects.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */