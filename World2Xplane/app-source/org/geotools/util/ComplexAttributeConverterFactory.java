/*    */ package org.geotools.util;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import org.geotools.factory.Hints;
/*    */ import org.geotools.feature.AttributeImpl;
/*    */ import org.geotools.filter.identity.FeatureIdImpl;
/*    */ import org.opengis.feature.Attribute;
/*    */ import org.opengis.feature.ComplexAttribute;
/*    */ import org.opengis.feature.GeometryAttribute;
/*    */ import org.opengis.feature.Property;
/*    */ import org.opengis.filter.identity.FeatureId;
/*    */ 
/*    */ public class ComplexAttributeConverterFactory implements ConverterFactory {
/*    */   public Converter createConverter(Class<?> source, Class<?> target, Hints hints) {
/* 43 */     if (ComplexAttribute.class.isAssignableFrom(source))
/* 44 */       return new Converter() {
/*    */           public Object convert(Object source, Class<?> target) throws Exception {
/* 46 */             if (source instanceof ComplexAttribute) {
/* 47 */               Collection<? extends Property> valueMap = ((ComplexAttribute)source).getValue();
/* 49 */               if (valueMap.isEmpty() || valueMap.size() > 1)
/* 50 */                 return null; 
/* 53 */               source = valueMap.iterator().next();
/* 54 */               if (AttributeImpl.class.equals(source.getClass()))
/* 55 */                 return Converters.convert(((Attribute)source).getValue(), target); 
/*    */             } 
/* 59 */             return null;
/*    */           }
/*    */         }; 
/* 65 */     if (GeometryAttribute.class.isAssignableFrom(source))
/* 66 */       return new Converter() {
/*    */           public Object convert(Object source, Class<?> target) throws Exception {
/* 68 */             if (source instanceof GeometryAttribute)
/* 69 */               return Converters.convert(((GeometryAttribute)source).getValue(), target); 
/* 72 */             return null;
/*    */           }
/*    */         }; 
/* 78 */     if (FeatureId.class.isAssignableFrom(target) && String.class.isAssignableFrom(source))
/* 79 */       return new Converter() {
/*    */           public Object convert(Object source, Class target) {
/* 81 */             if (source != null)
/* 82 */               return new FeatureIdImpl((String)source); 
/* 84 */             return null;
/*    */           }
/*    */         }; 
/* 88 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\ComplexAttributeConverterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */