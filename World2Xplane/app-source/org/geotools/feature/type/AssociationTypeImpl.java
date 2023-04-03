/*    */ package org.geotools.feature.type;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.geotools.util.Utilities;
/*    */ import org.opengis.feature.type.AssociationType;
/*    */ import org.opengis.feature.type.AttributeType;
/*    */ import org.opengis.feature.type.Name;
/*    */ import org.opengis.feature.type.PropertyType;
/*    */ import org.opengis.filter.Filter;
/*    */ import org.opengis.util.InternationalString;
/*    */ 
/*    */ public class AssociationTypeImpl extends PropertyTypeImpl implements AssociationType {
/*    */   protected final AttributeType relatedType;
/*    */   
/*    */   public AssociationTypeImpl(Name name, AttributeType referenceType, boolean isAbstract, List<Filter> restrictions, AssociationType superType, InternationalString description) {
/* 41 */     super(name, referenceType.getBinding(), isAbstract, restrictions, (PropertyType)superType, description);
/* 42 */     this.relatedType = referenceType;
/* 44 */     if (this.relatedType == null)
/* 45 */       throw new NullPointerException("relatedType"); 
/*    */   }
/*    */   
/*    */   public AttributeType getRelatedType() {
/* 50 */     return this.relatedType;
/*    */   }
/*    */   
/*    */   public AssociationType getSuper() {
/* 54 */     return (AssociationType)super.getSuper();
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 58 */     return super.hashCode() ^ this.relatedType.hashCode();
/*    */   }
/*    */   
/*    */   public boolean equals(Object other) {
/* 62 */     if (!(other instanceof AssociationTypeImpl))
/* 63 */       return false; 
/* 66 */     AssociationType ass = (AssociationType)other;
/* 68 */     return (super.equals(ass) && Utilities.equals(this.relatedType, ass.getRelatedType()));
/*    */   }
/*    */   
/*    */   public String toString() {
/* 73 */     return super.toString() + "; relatedType=[" + this.relatedType + "]";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\type\AssociationTypeImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */