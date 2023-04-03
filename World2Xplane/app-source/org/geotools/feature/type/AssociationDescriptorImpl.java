/*    */ package org.geotools.feature.type;
/*    */ 
/*    */ import org.opengis.feature.type.AssociationDescriptor;
/*    */ import org.opengis.feature.type.AssociationType;
/*    */ import org.opengis.feature.type.Name;
/*    */ import org.opengis.feature.type.PropertyType;
/*    */ 
/*    */ public class AssociationDescriptorImpl extends PropertyDescriptorImpl implements AssociationDescriptor {
/*    */   public AssociationDescriptorImpl(AssociationType type, Name name, int min, int max, boolean isNillable) {
/* 32 */     super((PropertyType)type, name, min, max, isNillable);
/*    */   }
/*    */   
/*    */   public AssociationType getType() {
/* 36 */     return (AssociationType)super.getType();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\type\AssociationDescriptorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */