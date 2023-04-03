/*    */ package org.geotools.feature.type;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.opengis.feature.Attribute;
/*    */ import org.opengis.feature.type.AttributeDescriptor;
/*    */ import org.opengis.feature.type.AttributeType;
/*    */ import org.opengis.feature.type.Name;
/*    */ import org.opengis.feature.type.PropertyDescriptor;
/*    */ import org.opengis.feature.type.PropertyType;
/*    */ import org.opengis.filter.identity.Identifier;
/*    */ 
/*    */ public final class ReadonlyAttributeDecorator implements Attribute {
/*    */   private final Attribute delegate;
/*    */   
/*    */   public ReadonlyAttributeDecorator(Attribute delegate) {
/* 41 */     this.delegate = delegate;
/*    */   }
/*    */   
/*    */   public AttributeType getType() {
/* 45 */     return this.delegate.getType();
/*    */   }
/*    */   
/*    */   public Identifier getIdentifier() {
/* 49 */     return this.delegate.getIdentifier();
/*    */   }
/*    */   
/*    */   public Object getValue() {
/* 53 */     return this.delegate.getValue();
/*    */   }
/*    */   
/*    */   public void setValue(Object newValue) throws IllegalArgumentException {
/* 57 */     throw new UnsupportedOperationException("Modification is not supported");
/*    */   }
/*    */   
/*    */   public AttributeDescriptor getDescriptor() {
/* 61 */     return this.delegate.getDescriptor();
/*    */   }
/*    */   
/*    */   public Name getName() {
/* 65 */     return this.delegate.getName();
/*    */   }
/*    */   
/*    */   public Map<Object, Object> getUserData() {
/* 69 */     return this.delegate.getUserData();
/*    */   }
/*    */   
/*    */   public boolean isNillable() {
/* 73 */     return this.delegate.isNillable();
/*    */   }
/*    */   
/*    */   public void validate() {
/* 76 */     this.delegate.validate();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\type\ReadonlyAttributeDecorator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */