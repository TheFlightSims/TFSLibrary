/*    */ package org.geotools.feature;
/*    */ 
/*    */ import java.rmi.server.UID;
/*    */ import org.geotools.filter.identity.FeatureIdImpl;
/*    */ import org.geotools.util.Converters;
/*    */ import org.opengis.feature.Feature;
/*    */ import org.opengis.feature.FeatureFactory;
/*    */ import org.opengis.feature.type.FeatureType;
/*    */ import org.opengis.feature.type.PropertyDescriptor;
/*    */ 
/*    */ public abstract class FeatureBuilder<FT extends FeatureType, F extends Feature> {
/*    */   protected FT featureType;
/*    */   
/*    */   protected FeatureFactory factory;
/*    */   
/*    */   public abstract F buildFeature(String paramString);
/*    */   
/*    */   protected FeatureBuilder(FT featureType, FeatureFactory factory) {
/* 49 */     this.featureType = featureType;
/* 50 */     this.factory = factory;
/*    */   }
/*    */   
/*    */   public FT getFeatureType() {
/* 58 */     return this.featureType;
/*    */   }
/*    */   
/*    */   protected Object convert(Object value, PropertyDescriptor descriptor) {
/* 63 */     if (value != null) {
/* 64 */       Class<?> target = descriptor.getType().getBinding();
/* 65 */       Object converted = Converters.convert(value, target);
/* 66 */       if (converted != null)
/* 67 */         value = converted; 
/*    */     } 
/* 71 */     return value;
/*    */   }
/*    */   
/*    */   public static String createDefaultFeatureId() {
/* 88 */     return "fid-" + (new UID()).toString().replace(':', '_');
/*    */   }
/*    */   
/*    */   public static FeatureIdImpl createDefaultFeatureIdentifier(String suggestedId) {
/* 96 */     if (suggestedId != null)
/* 97 */       return new FeatureIdImpl(suggestedId); 
/* 99 */     return new FeatureIdImpl(createDefaultFeatureId());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\FeatureBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */