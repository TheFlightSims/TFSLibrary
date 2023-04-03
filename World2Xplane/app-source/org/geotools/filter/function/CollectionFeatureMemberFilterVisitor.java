/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import org.geotools.filter.visitor.DuplicatingFilterVisitor;
/*    */ import org.opengis.filter.expression.PropertyName;
/*    */ 
/*    */ public final class CollectionFeatureMemberFilterVisitor extends DuplicatingFilterVisitor {
/*    */   public Object visit(PropertyName expression, Object data) {
/* 34 */     String xpath = expression.getPropertyName();
/* 35 */     if (xpath.startsWith("featureMembers/*/")) {
/* 36 */       xpath = xpath.substring(17);
/* 37 */     } else if (xpath.startsWith("featureMember/*/")) {
/* 38 */       xpath = xpath.substring(16);
/*    */     } 
/* 40 */     return getFactory(data).property(xpath, expression.getNamespaceContext());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\CollectionFeatureMemberFilterVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */