/*    */ package org.geotools.feature.type;
/*    */ 
/*    */ import org.opengis.feature.type.AttributeType;
/*    */ import org.opengis.feature.type.GeometryDescriptor;
/*    */ import org.opengis.feature.type.GeometryType;
/*    */ import org.opengis.feature.type.Name;
/*    */ import org.opengis.feature.type.PropertyType;
/*    */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*    */ 
/*    */ public class GeometryDescriptorImpl extends AttributeDescriptorImpl implements GeometryDescriptor {
/*    */   public GeometryDescriptorImpl(GeometryType type, Name name, int min, int max, boolean isNillable, Object defaultValue) {
/* 34 */     super((AttributeType)type, name, min, max, isNillable, defaultValue);
/*    */   }
/*    */   
/*    */   public GeometryType getType() {
/* 39 */     return (GeometryType)super.getType();
/*    */   }
/*    */   
/*    */   public CoordinateReferenceSystem getCoordinateReferenceSystem() {
/* 43 */     return getType().getCoordinateReferenceSystem();
/*    */   }
/*    */   
/*    */   public String getLocalName() {
/* 47 */     return getName().getLocalPart();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\type\GeometryDescriptorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */