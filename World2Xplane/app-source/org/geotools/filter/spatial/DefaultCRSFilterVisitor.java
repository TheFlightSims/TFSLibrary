/*    */ package org.geotools.filter.spatial;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import org.geotools.filter.visitor.DuplicatingFilterVisitor;
/*    */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*    */ import org.opengis.filter.FilterFactory2;
/*    */ import org.opengis.filter.expression.Literal;
/*    */ import org.opengis.filter.spatial.BBOX;
/*    */ import org.opengis.geometry.BoundingBox;
/*    */ import org.opengis.geometry.Envelope;
/*    */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*    */ 
/*    */ public class DefaultCRSFilterVisitor extends DuplicatingFilterVisitor {
/*    */   private CoordinateReferenceSystem defaultCrs;
/*    */   
/*    */   public DefaultCRSFilterVisitor(FilterFactory2 factory, CoordinateReferenceSystem defaultCrs) {
/* 41 */     super(factory);
/* 42 */     this.defaultCrs = defaultCrs;
/*    */   }
/*    */   
/*    */   public Object visit(BBOX filter, Object extraData) {
/* 47 */     String srs = filter.getSRS();
/* 48 */     if (srs != null && !"".equals(srs.trim()))
/* 49 */       return super.visit(filter, extraData); 
/*    */     try {
/* 52 */       return getFactory(extraData).bbox(filter.getExpression1(), (BoundingBox)ReferencedEnvelope.create((Envelope)filter.getBounds(), this.defaultCrs));
/* 53 */     } catch (Exception e) {
/* 54 */       throw new RuntimeException("Could not decode srs '" + srs + "'", e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public Object visit(Literal expression, Object extraData) {
/* 59 */     if (!(expression.getValue() instanceof Geometry))
/* 60 */       return super.visit(expression, extraData); 
/* 63 */     Geometry geom = (Geometry)expression.getValue();
/* 64 */     if (geom.getUserData() != null && geom.getUserData() instanceof CoordinateReferenceSystem)
/* 65 */       return super.visit(expression, extraData); 
/* 68 */     Geometry clone = geom.getFactory().createGeometry(geom);
/* 69 */     clone.setUserData(this.defaultCrs);
/* 72 */     return this.ff.literal(clone);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\spatial\DefaultCRSFilterVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */