/*     */ package org.geotools.feature.type;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.logging.Level;
/*     */ import org.geotools.feature.NameImpl;
/*     */ import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
/*     */ import org.geotools.feature.simple.SimpleFeatureTypeImpl;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.Name;
/*     */ 
/*     */ public class BasicFeatureTypes {
/*     */   static {
/*  90 */     SimpleFeatureType tmpPoint = null;
/*  91 */     SimpleFeatureType tmpPolygon = null;
/*  92 */     SimpleFeatureType tmpLine = null;
/*     */   }
/*     */   
/*  96 */   public static final SimpleFeatureType FEATURE = (SimpleFeatureType)new SimpleFeatureTypeImpl((Name)new NameImpl("Feature"), Collections.EMPTY_LIST, null, true, Collections.EMPTY_LIST, null, null);
/*     */   
/*     */   public static final SimpleFeatureType POLYGON;
/*     */   
/*     */   public static final SimpleFeatureType POINT;
/*     */   
/*     */   public static final SimpleFeatureType LINE;
/*     */   
/*     */   public static final String GEOMETRY_ATTRIBUTE_NAME = "the_geom";
/*     */   
/*     */   public static final String DEFAULT_NAMESPACE = "http://www.opengis.net/gml";
/*     */   
/*     */   static {
/*     */     try {
/* 101 */       SimpleFeatureTypeBuilder build = new SimpleFeatureTypeBuilder();
/* 105 */       build.setName("pointFeature");
/* 106 */       tmpPoint = build.buildFeatureType();
/* 108 */       build.setName("lineFeature");
/* 109 */       tmpLine = build.buildFeatureType();
/* 111 */       build.setName("polygonFeature");
/* 112 */       tmpPolygon = build.buildFeatureType();
/* 113 */     } catch (Exception ex) {
/* 114 */       Logging.getLogger("org.geotools.feature.type.BasicFeatureTypes").log(Level.SEVERE, "Error creating basic feature types", ex);
/*     */     } 
/* 117 */     POINT = tmpPoint;
/* 118 */     LINE = tmpLine;
/* 119 */     POLYGON = tmpPolygon;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\type\BasicFeatureTypes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */