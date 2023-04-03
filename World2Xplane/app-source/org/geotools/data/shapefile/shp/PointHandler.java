/*     */ package org.geotools.data.shapefile.shp;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import java.nio.ByteBuffer;
/*     */ 
/*     */ public class PointHandler implements ShapeHandler {
/*     */   final ShapeType shapeType;
/*     */   
/*     */   GeometryFactory geometryFactory;
/*     */   
/*     */   public PointHandler(ShapeType type, GeometryFactory gf) throws ShapefileException {
/*  42 */     if (type != ShapeType.POINT && type != ShapeType.POINTM && type != ShapeType.POINTZ)
/*  44 */       throw new ShapefileException("PointHandler constructor: expected a type of 1, 11 or 21"); 
/*  48 */     this.shapeType = type;
/*  49 */     this.geometryFactory = gf;
/*     */   }
/*     */   
/*     */   public PointHandler() {
/*  53 */     this.shapeType = ShapeType.POINT;
/*     */   }
/*     */   
/*     */   public ShapeType getShapeType() {
/*  62 */     return this.shapeType;
/*     */   }
/*     */   
/*     */   public int getLength(Object geometry) {
/*     */     int length;
/*  67 */     if (this.shapeType == ShapeType.POINT) {
/*  68 */       length = 20;
/*  69 */     } else if (this.shapeType == ShapeType.POINTM) {
/*  70 */       length = 28;
/*  71 */     } else if (this.shapeType == ShapeType.POINTZ) {
/*  72 */       length = 36;
/*     */     } else {
/*  74 */       throw new IllegalStateException("Expected ShapeType of Point, got" + this.shapeType);
/*     */     } 
/*  77 */     return length;
/*     */   }
/*     */   
/*     */   public Object read(ByteBuffer buffer, ShapeType type, boolean flatGeometry) {
/*  81 */     if (type == ShapeType.NULL)
/*  82 */       return createNull(); 
/*  85 */     int dimension = (this.shapeType == ShapeType.POINTZ && !flatGeometry) ? 3 : 2;
/*  86 */     CoordinateSequence cs = this.geometryFactory.getCoordinateSequenceFactory().create(1, dimension);
/*  87 */     cs.setOrdinate(0, 0, buffer.getDouble());
/*  88 */     cs.setOrdinate(0, 1, buffer.getDouble());
/*  90 */     if (this.shapeType == ShapeType.POINTM)
/*  91 */       buffer.getDouble(); 
/*  94 */     if (dimension > 2)
/*  95 */       cs.setOrdinate(0, 2, buffer.getDouble()); 
/*  98 */     return this.geometryFactory.createPoint(cs);
/*     */   }
/*     */   
/*     */   private Object createNull() {
/* 102 */     return this.geometryFactory.createPoint(new Coordinate(Double.NaN, Double.NaN, Double.NaN));
/*     */   }
/*     */   
/*     */   public void write(ByteBuffer buffer, Object geometry) {
/* 107 */     Coordinate c = ((Point)geometry).getCoordinate();
/* 109 */     buffer.putDouble(c.x);
/* 110 */     buffer.putDouble(c.y);
/* 112 */     if (this.shapeType == ShapeType.POINTZ)
/* 113 */       if (Double.isNaN(c.z)) {
/* 114 */         buffer.putDouble(0.0D);
/*     */       } else {
/* 116 */         buffer.putDouble(c.z);
/*     */       }  
/* 120 */     if (this.shapeType == ShapeType.POINTZ || this.shapeType == ShapeType.POINTM)
/* 121 */       buffer.putDouble(-1.0E41D); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\shp\PointHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */