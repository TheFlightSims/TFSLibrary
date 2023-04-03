/*     */ package org.geotools.styling;
/*     */ 
/*     */ import org.opengis.style.FeatureTypeStyle;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ 
/*     */ public class NamedStyleImpl extends StyleImpl implements NamedStyle {
/*     */   private String name;
/*     */   
/*     */   public String getName() {
/*  48 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  57 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getTitle() {
/*  66 */     return "";
/*     */   }
/*     */   
/*     */   public void setTitle(String title) {
/*  77 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public String getAbstract() {
/*  86 */     return "";
/*     */   }
/*     */   
/*     */   public void setAbstract(String abstractStr) {
/*  97 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean isDefault() {
/* 106 */     return false;
/*     */   }
/*     */   
/*     */   public void setDefault(boolean isDefault) {
/* 117 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public FeatureTypeStyle[] getFeatureTypeStyles() {
/* 126 */     return new FeatureTypeStyle[0];
/*     */   }
/*     */   
/*     */   public void setFeatureTypeStyles(FeatureTypeStyle[] types) {
/* 137 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void addFeatureTypeStyle(FeatureTypeStyle type) {
/* 148 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object data) {
/* 157 */     return visitor.visit(this, data);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\NamedStyleImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */