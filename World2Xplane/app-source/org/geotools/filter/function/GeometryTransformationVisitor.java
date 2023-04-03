/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Envelope;
/*    */ import org.geotools.filter.visitor.DefaultFilterVisitor;
/*    */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ import org.opengis.filter.expression.ExpressionVisitor;
/*    */ import org.opengis.filter.expression.Function;
/*    */ 
/*    */ public class GeometryTransformationVisitor extends DefaultFilterVisitor {
/*    */   public Object visit(Function expression, Object data) {
/* 23 */     ReferencedEnvelope merged = new ReferencedEnvelope((ReferencedEnvelope)data);
/* 24 */     for (Expression param : expression.getParameters()) {
/* 25 */       ReferencedEnvelope result = (ReferencedEnvelope)param.accept((ExpressionVisitor)this, data);
/* 26 */       if (result != null)
/* 27 */         merged.expandToInclude((Envelope)result); 
/*    */     } 
/* 31 */     if (expression instanceof GeometryTransformation)
/* 32 */       merged = ((GeometryTransformation)expression).invert(merged); 
/* 35 */     return merged;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\GeometryTransformationVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */