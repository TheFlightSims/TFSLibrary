/*    */ package org.geotools.feature.type;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.geotools.referencing.CRS;
/*    */ import org.opengis.feature.type.AttributeType;
/*    */ import org.opengis.feature.type.GeometryType;
/*    */ import org.opengis.feature.type.Name;
/*    */ import org.opengis.filter.Filter;
/*    */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*    */ import org.opengis.util.InternationalString;
/*    */ 
/*    */ public class GeometryTypeImpl extends AttributeTypeImpl implements GeometryType {
/*    */   protected CoordinateReferenceSystem CRS;
/*    */   
/*    */   public GeometryTypeImpl(Name name, Class<?> binding, CoordinateReferenceSystem crs, boolean identified, boolean isAbstract, List<Filter> restrictions, AttributeType superType, InternationalString description) {
/* 44 */     super(name, binding, identified, isAbstract, restrictions, superType, description);
/* 45 */     this.CRS = crs;
/*    */   }
/*    */   
/*    */   public CoordinateReferenceSystem getCoordinateReferenceSystem() {
/* 49 */     return this.CRS;
/*    */   }
/*    */   
/*    */   public boolean equals(Object other) {
/* 54 */     if (!(other instanceof GeometryType))
/* 55 */       return false; 
/* 57 */     if (!super.equals(other))
/* 58 */       return false; 
/* 60 */     GeometryType o = (GeometryType)other;
/* 61 */     if (this.CRS == null)
/* 62 */       return (o.getCoordinateReferenceSystem() == null); 
/* 64 */     if (o.getCoordinateReferenceSystem() == null)
/* 65 */       return false; 
/* 67 */     return CRS.equalsIgnoreMetadata(this.CRS, o.getCoordinateReferenceSystem());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\type\GeometryTypeImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */