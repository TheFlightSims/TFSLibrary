/*     */ package org.geotools.styling;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.filter.Filter;
/*     */ 
/*     */ public class FeatureTypeConstraintImpl implements FeatureTypeConstraint, Cloneable {
/*     */   String featureTypeName;
/*     */   
/*     */   Filter filter;
/*     */   
/*     */   Extent[] extents;
/*     */   
/*     */   public String getFeatureTypeName() {
/*  42 */     return this.featureTypeName;
/*     */   }
/*     */   
/*     */   public void setFeatureTypeName(String name) {
/*  46 */     this.featureTypeName = name;
/*     */   }
/*     */   
/*     */   public Filter getFilter() {
/*  50 */     return this.filter;
/*     */   }
/*     */   
/*     */   public void setFilter(Filter filter) {
/*  54 */     this.filter = filter;
/*     */   }
/*     */   
/*     */   public Extent[] getExtents() {
/*  58 */     return this.extents;
/*     */   }
/*     */   
/*     */   public void setExtents(Extent[] extents) {
/*  62 */     this.extents = extents;
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/*  66 */     visitor.visit(this);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  70 */     int PRIME = 1000003;
/*  71 */     int result = 0;
/*  73 */     if (this.featureTypeName != null)
/*  74 */       result = 1000003 * result + this.featureTypeName.hashCode(); 
/*  77 */     if (this.filter != null)
/*  78 */       result = 1000003 * result + this.filter.hashCode(); 
/*  81 */     if (this.extents != null)
/*  82 */       result = 1000003 * result + this.extents.hashCode(); 
/*  85 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  89 */     if (this == obj)
/*  90 */       return true; 
/*  93 */     if (obj instanceof FeatureTypeConstraintImpl) {
/*  94 */       FeatureTypeConstraintImpl other = (FeatureTypeConstraintImpl)obj;
/*  95 */       return (Utilities.equals(this.featureTypeName, other.featureTypeName) && Utilities.equals(this.filter, other.filter) && Arrays.equals((Object[])this.extents, (Object[])other.extents));
/*     */     } 
/* 100 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\FeatureTypeConstraintImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */