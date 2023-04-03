/*    */ package org.geotools.filter.visitor;
/*    */ 
/*    */ import org.opengis.feature.simple.SimpleFeatureType;
/*    */ import org.opengis.feature.type.AttributeDescriptor;
/*    */ import org.opengis.filter.expression.PropertyName;
/*    */ 
/*    */ public class PropertyNameResolvingVisitor extends DuplicatingFilterVisitor {
/*    */   SimpleFeatureType featureType;
/*    */   
/*    */   public PropertyNameResolvingVisitor(SimpleFeatureType featureType) {
/* 44 */     if (featureType == null)
/* 45 */       throw new NullPointerException("featureType"); 
/* 48 */     this.featureType = featureType;
/*    */   }
/*    */   
/*    */   public Object visit(PropertyName expression, Object extraData) {
/* 52 */     AttributeDescriptor att = (AttributeDescriptor)expression.evaluate(this.featureType, null);
/* 53 */     if (att != null)
/* 54 */       return getFactory(extraData).property(att.getLocalName()); 
/* 56 */     return super.visit(expression, extraData);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\PropertyNameResolvingVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */