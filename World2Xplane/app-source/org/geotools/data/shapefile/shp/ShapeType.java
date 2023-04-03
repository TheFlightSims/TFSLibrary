/*     */ package org.geotools.data.shapefile.shp;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ 
/*     */ public final class ShapeType {
/*  34 */   public static final ShapeType NULL = new ShapeType(0, "Null");
/*     */   
/*  36 */   public static final ShapeType POINT = new ShapeType(1, "Point");
/*     */   
/*  38 */   public static final ShapeType POINTZ = new ShapeType(11, "PointZ");
/*     */   
/*  40 */   public static final ShapeType POINTM = new ShapeType(21, "PointM");
/*     */   
/*  42 */   public static final ShapeType ARC = new ShapeType(3, "Arc");
/*     */   
/*  44 */   public static final ShapeType ARCZ = new ShapeType(13, "ArcZ");
/*     */   
/*  46 */   public static final ShapeType ARCM = new ShapeType(23, "ArcM");
/*     */   
/*  48 */   public static final ShapeType POLYGON = new ShapeType(5, "Polygon");
/*     */   
/*  50 */   public static final ShapeType POLYGONZ = new ShapeType(15, "PolygonZ");
/*     */   
/*  52 */   public static final ShapeType POLYGONM = new ShapeType(25, "PolygonM");
/*     */   
/*  54 */   public static final ShapeType MULTIPOINT = new ShapeType(8, "MultiPoint");
/*     */   
/*  56 */   public static final ShapeType MULTIPOINTZ = new ShapeType(18, "MultiPointZ");
/*     */   
/*  58 */   public static final ShapeType MULTIPOINTM = new ShapeType(28, "MultiPointM");
/*     */   
/*  61 */   public static final ShapeType UNDEFINED = new ShapeType(-1, "Undefined");
/*     */   
/*     */   public final int id;
/*     */   
/*     */   public final String name;
/*     */   
/*     */   protected ShapeType(int id, String name) {
/*  80 */     this.id = id;
/*  81 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  90 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean isMultiPoint() {
/* 100 */     boolean mp = true;
/* 101 */     if (this == UNDEFINED) {
/* 102 */       mp = false;
/* 103 */     } else if (this == NULL) {
/* 104 */       mp = false;
/* 105 */     } else if (this == POINT || this == POINTM || this == POINTZ) {
/* 106 */       mp = false;
/*     */     } 
/* 108 */     return mp;
/*     */   }
/*     */   
/*     */   public boolean isPointType() {
/* 112 */     return (this.id % 10 == 1);
/*     */   }
/*     */   
/*     */   public boolean isLineType() {
/* 116 */     return (this.id % 10 == 3);
/*     */   }
/*     */   
/*     */   public boolean isPolygonType() {
/* 120 */     return (this.id % 10 == 5);
/*     */   }
/*     */   
/*     */   public boolean isMultiPointType() {
/* 124 */     return (this.id % 10 == 8);
/*     */   }
/*     */   
/*     */   public static ShapeType forID(int id) {
/* 136 */     switch (id) {
/*     */       case 0:
/* 138 */         t = NULL;
/* 180 */         return t;
/*     */       case 1:
/*     */         t = POINT;
/* 180 */         return t;
/*     */       case 11:
/*     */         t = POINTZ;
/* 180 */         return t;
/*     */       case 21:
/*     */         t = POINTM;
/* 180 */         return t;
/*     */       case 3:
/*     */         t = ARC;
/* 180 */         return t;
/*     */       case 13:
/*     */         t = ARCZ;
/* 180 */         return t;
/*     */       case 23:
/*     */         t = ARCM;
/* 180 */         return t;
/*     */       case 5:
/*     */         t = POLYGON;
/* 180 */         return t;
/*     */       case 15:
/*     */         t = POLYGONZ;
/* 180 */         return t;
/*     */       case 25:
/*     */         t = POLYGONM;
/* 180 */         return t;
/*     */       case 8:
/*     */         t = MULTIPOINT;
/* 180 */         return t;
/*     */       case 18:
/*     */         t = MULTIPOINTZ;
/* 180 */         return t;
/*     */       case 28:
/*     */         t = MULTIPOINTM;
/* 180 */         return t;
/*     */     } 
/*     */     ShapeType t = UNDEFINED;
/* 180 */     return t;
/*     */   }
/*     */   
/*     */   public ShapeHandler getShapeHandler(GeometryFactory gf) throws ShapefileException {
/* 193 */     switch (this.id) {
/*     */       case 1:
/*     */       case 11:
/*     */       case 21:
/* 197 */         handler = new PointHandler(this, gf);
/* 217 */         return handler;
/*     */       case 3:
/*     */       case 13:
/*     */       case 23:
/*     */         handler = new MultiLineHandler(this, gf);
/* 217 */         return handler;
/*     */       case 5:
/*     */       case 15:
/*     */       case 25:
/*     */         handler = new PolygonHandler(this, gf);
/* 217 */         return handler;
/*     */       case 8:
/*     */       case 18:
/*     */       case 28:
/*     */         handler = new MultiPointHandler(this, gf);
/* 217 */         return handler;
/*     */     } 
/*     */     ShapeHandler handler = null;
/* 217 */     return handler;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\shp\ShapeType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */