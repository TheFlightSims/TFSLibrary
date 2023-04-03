/*     */ package org.geotools.feature;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.opengis.feature.IllegalAttributeException;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.AttributeType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.feature.type.PropertyType;
/*     */ 
/*     */ public class IllegalAttributeException extends IllegalAttributeException {
/*     */   private static final long serialVersionUID = 3775548331744387093L;
/*     */   
/*  36 */   private static final AttributeDescriptor NULL_ATTRIBUTE_DESCRIPTOR = new NullAttributeDescriptor();
/*     */   
/*     */   public IllegalAttributeException(String message) {
/*  44 */     super(NULL_ATTRIBUTE_DESCRIPTOR, null, message);
/*     */   }
/*     */   
/*     */   public IllegalAttributeException(AttributeDescriptor expected, Object invalid) {
/*  54 */     super(expected, invalid);
/*     */   }
/*     */   
/*     */   public IllegalAttributeException(AttributeDescriptor expected, Object invalid, Throwable cause) {
/*  66 */     super(expected, invalid, cause);
/*     */   }
/*     */   
/*     */   private static class NullAttributeDescriptor implements AttributeDescriptor {
/*     */     private NullAttributeDescriptor() {}
/*     */     
/*     */     public int getMaxOccurs() {
/*  76 */       return 0;
/*     */     }
/*     */     
/*     */     public int getMinOccurs() {
/*  80 */       return 0;
/*     */     }
/*     */     
/*     */     public Name getName() {
/*  84 */       return null;
/*     */     }
/*     */     
/*     */     public Map<Object, Object> getUserData() {
/*  88 */       return null;
/*     */     }
/*     */     
/*     */     public boolean isNillable() {
/*  92 */       return false;
/*     */     }
/*     */     
/*     */     public Object getDefaultValue() {
/*  96 */       return null;
/*     */     }
/*     */     
/*     */     public String getLocalName() {
/* 100 */       return null;
/*     */     }
/*     */     
/*     */     public AttributeType getType() {
/* 104 */       return null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\IllegalAttributeException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */