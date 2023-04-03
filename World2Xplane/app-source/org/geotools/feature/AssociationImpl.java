/*    */ package org.geotools.feature;
/*    */ 
/*    */ import org.opengis.feature.Association;
/*    */ import org.opengis.feature.Attribute;
/*    */ import org.opengis.feature.type.AssociationDescriptor;
/*    */ import org.opengis.feature.type.AssociationType;
/*    */ import org.opengis.feature.type.AttributeType;
/*    */ import org.opengis.feature.type.PropertyDescriptor;
/*    */ import org.opengis.feature.type.PropertyType;
/*    */ 
/*    */ public class AssociationImpl extends PropertyImpl implements Association {
/*    */   protected AssociationImpl(Attribute value, AssociationDescriptor descriptor) {
/* 33 */     super(value, (PropertyDescriptor)descriptor);
/*    */   }
/*    */   
/*    */   public AttributeType getRelatedType() {
/* 37 */     return getType().getRelatedType();
/*    */   }
/*    */   
/*    */   public AssociationDescriptor getDescriptor() {
/* 41 */     return (AssociationDescriptor)super.getDescriptor();
/*    */   }
/*    */   
/*    */   public AssociationType getType() {
/* 45 */     return (AssociationType)super.getType();
/*    */   }
/*    */   
/*    */   public Attribute getValue() {
/* 49 */     return (Attribute)super.getValue();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\AssociationImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */