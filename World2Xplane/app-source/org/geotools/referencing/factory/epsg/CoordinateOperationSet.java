/*    */ package org.geotools.referencing.factory.epsg;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.geotools.referencing.factory.IdentifiedObjectSet;
/*    */ import org.opengis.referencing.AuthorityFactory;
/*    */ import org.opengis.referencing.FactoryException;
/*    */ import org.opengis.referencing.IdentifiedObject;
/*    */ import org.opengis.referencing.crs.CRSAuthorityFactory;
/*    */ import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;
/*    */ 
/*    */ final class CoordinateOperationSet extends IdentifiedObjectSet {
/*    */   private static final long serialVersionUID = -2421669857023064667L;
/*    */   
/*    */   private Map projections;
/*    */   
/*    */   public CoordinateOperationSet(AuthorityFactory factory) {
/* 61 */     super(factory);
/*    */   }
/*    */   
/*    */   public boolean addAuthorityCode(String code, String crs) {
/* 72 */     if (crs != null) {
/* 73 */       if (this.projections == null)
/* 74 */         this.projections = new HashMap<Object, Object>(); 
/* 76 */       this.projections.put(code, crs);
/*    */     } 
/* 78 */     return addAuthorityCode(code);
/*    */   }
/*    */   
/*    */   protected IdentifiedObject createObject(String code) throws FactoryException {
/* 85 */     if (this.projections != null) {
/* 86 */       String crs = (String)this.projections.get(code);
/* 87 */       if (crs != null)
/* 88 */         return (IdentifiedObject)((CRSAuthorityFactory)this.factory).createProjectedCRS(crs).getConversionFromBase(); 
/*    */     } 
/* 91 */     return (IdentifiedObject)((CoordinateOperationAuthorityFactory)this.factory).createCoordinateOperation(code);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\epsg\CoordinateOperationSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */