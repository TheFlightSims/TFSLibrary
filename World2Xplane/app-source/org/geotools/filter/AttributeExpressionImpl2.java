/*     */ package org.geotools.filter;
/*     */ 
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.filter.expression.ExpressionVisitor;
/*     */ import org.xml.sax.helpers.NamespaceSupport;
/*     */ 
/*     */ public class AttributeExpressionImpl2 extends DefaultExpression implements AttributeExpression {
/*  40 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.core");
/*     */   
/*     */   protected String attPath;
/*     */   
/*  46 */   protected AttributeDescriptor at = null;
/*     */   
/*     */   protected AttributeExpressionImpl2(AttributeDescriptor at) {
/*  54 */     this.at = at;
/*  55 */     this.expressionType = 113;
/*     */   }
/*     */   
/*     */   public final void setAttributePath(String attPath) throws IllegalFilterException {
/*  69 */     setPropertyName(attPath);
/*     */   }
/*     */   
/*     */   public final String getAttributePath() {
/*  78 */     return getPropertyName();
/*     */   }
/*     */   
/*     */   public String getPropertyName() {
/*  87 */     return this.attPath;
/*     */   }
/*     */   
/*     */   public void setPropertyName(String name) {
/*  97 */     throw new IllegalFilterException("Attribute: " + this.attPath + " is not in stated schema " + ".");
/*     */   }
/*     */   
/*     */   public Object evaluate(SimpleFeature feature) {
/* 108 */     return feature.getAttribute(this.attPath);
/*     */   }
/*     */   
/*     */   public Object evaluate(Object object) {
/* 113 */     if (object instanceof SimpleFeature)
/* 114 */       return evaluate((SimpleFeature)object); 
/* 116 */     return null;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 125 */     return this.attPath;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 139 */     if (obj.getClass() == getClass()) {
/* 140 */       AttributeExpressionImpl2 expAttr = (AttributeExpressionImpl2)obj;
/* 142 */       boolean isEqual = (expAttr.getType() == this.expressionType);
/* 143 */       isEqual = (expAttr.attPath != null) ? ((isEqual && expAttr.attPath.equals(this.attPath))) : ((isEqual && this.attPath == null));
/* 147 */       return isEqual;
/*     */     } 
/* 149 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 159 */     int result = 17;
/* 160 */     result = 37 * result + ((this.attPath == null) ? 0 : this.attPath.hashCode());
/* 161 */     return result;
/*     */   }
/*     */   
/*     */   public Object accept(ExpressionVisitor visitor, Object extraData) {
/* 175 */     return visitor.visit(this, extraData);
/*     */   }
/*     */   
/*     */   public NamespaceSupport getNamespaceContext() {
/* 179 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\AttributeExpressionImpl2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */