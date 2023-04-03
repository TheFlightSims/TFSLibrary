/*     */ package org.geotools.filter.expression;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.util.regex.Pattern;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.feature.IllegalAttributeException;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.GeometryDescriptor;
/*     */ 
/*     */ public class SimpleFeaturePropertyAccessorFactory implements PropertyAccessorFactory {
/*  54 */   static PropertyAccessor ATTRIBUTE_ACCESS = new SimpleFeaturePropertyAccessor();
/*     */   
/*  55 */   static PropertyAccessor DEFAULT_GEOMETRY_ACCESS = new DefaultGeometrySimpleFeaturePropertyAccessor();
/*     */   
/*  56 */   static PropertyAccessor FID_ACCESS = new FidSimpleFeaturePropertyAccessor();
/*     */   
/*  57 */   static Pattern idPattern = Pattern.compile("@(\\w+:)?id");
/*     */   
/*     */   private static final String NAME_START_CHAR = ":A-Z_a-z\\u00c0-\\u00d6\\u00d8-\\u00f6\\u00f8-\\u02ff\\u0370-\\u037d\\u037f-\\u1fff\\u200c-\\u200d\\u2070-\\u218f\\u2c00-\\u2fef\\u3001-\\ud7ff\\uf900-\\ufdcf\\ufdf0-\\ufffd";
/*     */   
/*     */   private static final String NAME_CHAR = ":A-Z_a-z\\u00c0-\\u00d6\\u00d8-\\u00f6\\u00f8-\\u02ff\\u0370-\\u037d\\u037f-\\u1fff\\u200c-\\u200d\\u2070-\\u218f\\u2c00-\\u2fef\\u3001-\\ud7ff\\uf900-\\ufdcf\\ufdf0-\\ufffd\\-\\.0-9\\u00b7\\u0300-\\u036f\\u203f-\\u2040";
/*     */   
/*  87 */   static final Pattern propertyPattern = Pattern.compile("^(?!@)([:A-Z_a-z\\u00c0-\\u00d6\\u00d8-\\u00f6\\u00f8-\\u02ff\\u0370-\\u037d\\u037f-\\u1fff\\u200c-\\u200d\\u2070-\\u218f\\u2c00-\\u2fef\\u3001-\\ud7ff\\uf900-\\ufdcf\\ufdf0-\\ufffd][:A-Z_a-z\\u00c0-\\u00d6\\u00d8-\\u00f6\\u00f8-\\u02ff\\u0370-\\u037d\\u037f-\\u1fff\\u200c-\\u200d\\u2070-\\u218f\\u2c00-\\u2fef\\u3001-\\ud7ff\\uf900-\\ufdcf\\ufdf0-\\ufffd\\-\\.0-9\\u00b7\\u0300-\\u036f\\u203f-\\u2040]*)(\\[1])?$");
/*     */   
/*     */   public PropertyAccessor createPropertyAccessor(Class<?> type, String xpath, Class target, Hints hints) {
/*  93 */     if (xpath == null)
/*  94 */       return null; 
/*  96 */     if (!SimpleFeature.class.isAssignableFrom(type) && !SimpleFeatureType.class.isAssignableFrom(type))
/*  97 */       return null; 
/* 100 */     if ("".equals(xpath))
/* 101 */       return DEFAULT_GEOMETRY_ACCESS; 
/* 104 */     if (idPattern.matcher(xpath).matches())
/* 105 */       return FID_ACCESS; 
/* 108 */     if (propertyPattern.matcher(xpath).matches())
/* 109 */       return ATTRIBUTE_ACCESS; 
/* 112 */     return null;
/*     */   }
/*     */   
/*     */   static String stripPrefixIndex(String xpath) {
/* 127 */     int split = xpath.indexOf(":");
/* 128 */     if (split != -1)
/* 129 */       xpath = xpath.substring(split + 1); 
/* 131 */     if (xpath.endsWith("[1]"))
/* 132 */       xpath = xpath.substring(0, xpath.length() - 3); 
/* 134 */     return xpath;
/*     */   }
/*     */   
/*     */   static class FidSimpleFeaturePropertyAccessor implements PropertyAccessor {
/*     */     public boolean canHandle(Object object, String xpath, Class target) {
/* 145 */       return (object instanceof SimpleFeature && xpath.matches("@(\\w+:)?id"));
/*     */     }
/*     */     
/*     */     public Object get(Object object, String xpath, Class target) {
/* 148 */       SimpleFeature feature = (SimpleFeature)object;
/* 149 */       return feature.getID();
/*     */     }
/*     */     
/*     */     public void set(Object object, String xpath, Object value, Class target) throws IllegalAttributeException {
/* 154 */       throw new IllegalAttributeException("feature id is immutable");
/*     */     }
/*     */   }
/*     */   
/*     */   static class DefaultGeometrySimpleFeaturePropertyAccessor implements PropertyAccessor {
/*     */     public boolean canHandle(Object object, String xpath, Class target) {
/* 160 */       if (!"".equals(xpath))
/* 161 */         return false; 
/* 166 */       if (!(object instanceof SimpleFeature) && !(object instanceof SimpleFeatureType))
/* 167 */         return false; 
/* 170 */       return true;
/*     */     }
/*     */     
/*     */     public Object get(Object object, String xpath, Class target) {
/* 174 */       if (object instanceof SimpleFeature) {
/* 175 */         SimpleFeature f = (SimpleFeature)object;
/* 176 */         Object defaultGeometry = f.getDefaultGeometry();
/* 179 */         if (defaultGeometry == null)
/* 180 */           for (Object o : f.getAttributes()) {
/* 181 */             if (o instanceof Geometry) {
/* 182 */               defaultGeometry = o;
/*     */               break;
/*     */             } 
/*     */           }  
/* 188 */         return defaultGeometry;
/*     */       } 
/* 191 */       if (object instanceof SimpleFeatureType) {
/* 192 */         SimpleFeatureType ft = (SimpleFeatureType)object;
/* 193 */         GeometryDescriptor gd = ft.getGeometryDescriptor();
/* 195 */         if (gd == null)
/* 197 */           for (AttributeDescriptor ad : ft.getAttributeDescriptors()) {
/* 198 */             if (Geometry.class.isAssignableFrom(ad.getType().getBinding()))
/* 199 */               return ad; 
/*     */           }  
/* 204 */         return gd;
/*     */       } 
/* 207 */       return null;
/*     */     }
/*     */     
/*     */     public void set(Object object, String xpath, Object value, Class target) throws IllegalAttributeException {
/* 213 */       if (object instanceof SimpleFeature)
/* 214 */         ((SimpleFeature)object).setDefaultGeometry(value); 
/* 216 */       if (object instanceof SimpleFeatureType)
/* 217 */         throw new IllegalAttributeException("feature type is immutable"); 
/*     */     }
/*     */   }
/*     */   
/*     */   static class SimpleFeaturePropertyAccessor implements PropertyAccessor {
/*     */     public boolean canHandle(Object object, String xpath, Class target) {
/* 225 */       xpath = SimpleFeaturePropertyAccessorFactory.stripPrefixIndex(xpath);
/* 227 */       if (object instanceof SimpleFeature)
/* 228 */         return (((SimpleFeature)object).getType().getDescriptor(xpath) != null); 
/* 231 */       if (object instanceof SimpleFeatureType)
/* 232 */         return (((SimpleFeatureType)object).getDescriptor(xpath) != null); 
/* 235 */       return false;
/*     */     }
/*     */     
/*     */     public Object get(Object object, String xpath, Class target) {
/* 239 */       xpath = SimpleFeaturePropertyAccessorFactory.stripPrefixIndex(xpath);
/* 241 */       if (object instanceof SimpleFeature)
/* 242 */         return ((SimpleFeature)object).getAttribute(xpath); 
/* 245 */       if (object instanceof SimpleFeatureType)
/* 246 */         return ((SimpleFeatureType)object).getDescriptor(xpath); 
/* 249 */       return null;
/*     */     }
/*     */     
/*     */     public void set(Object object, String xpath, Object value, Class target) throws IllegalAttributeException {
/* 254 */       xpath = SimpleFeaturePropertyAccessorFactory.stripPrefixIndex(xpath);
/* 256 */       if (object instanceof SimpleFeature)
/* 257 */         ((SimpleFeature)object).setAttribute(xpath, value); 
/* 260 */       if (object instanceof SimpleFeatureType)
/* 261 */         throw new IllegalAttributeException("feature type is immutable"); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\expression\SimpleFeaturePropertyAccessorFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */