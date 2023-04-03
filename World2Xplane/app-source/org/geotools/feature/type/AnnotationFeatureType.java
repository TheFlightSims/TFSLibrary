/*    */ package org.geotools.feature.type;
/*    */ 
/*    */ import java.util.logging.Level;
/*    */ import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
/*    */ import org.geotools.util.logging.Logging;
/*    */ import org.opengis.feature.simple.SimpleFeatureType;
/*    */ 
/*    */ public class AnnotationFeatureType {
/*    */   public static final SimpleFeatureType ANNOTATION;
/*    */   
/*    */   public static final String ANNOTATION_ATTRIBUTE_NAME = "annotation_attribute_name";
/*    */   
/*    */   static {
/* 71 */     SimpleFeatureType tmp = null;
/*    */     try {
/* 74 */       SimpleFeatureTypeBuilder tb = new SimpleFeatureTypeBuilder();
/* 75 */       tb.setName("annotation");
/* 76 */       tb.add("annotation_attribute_name", String.class);
/* 77 */       tmp = tb.buildFeatureType();
/* 78 */     } catch (Exception ex) {
/* 79 */       Logging.getLogger("org.geotools.data.vpf.AnnotationFeatureType").log(Level.SEVERE, "Error creating ANNOTATION feature type", ex);
/*    */     } 
/* 82 */     ANNOTATION = tmp;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\type\AnnotationFeatureType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */