/*    */ package org.opengis.feature;
/*    */ 
/*    */ import org.opengis.feature.type.AttributeDescriptor;
/*    */ 
/*    */ public class IllegalAttributeException extends IllegalArgumentException {
/*    */   private static final long serialVersionUID = 3373066465585246605L;
/*    */   
/*    */   private final AttributeDescriptor descriptor;
/*    */   
/*    */   private final Object value;
/*    */   
/*    */   public IllegalAttributeException(AttributeDescriptor descriptor, Object value) {
/* 39 */     this.descriptor = descriptor;
/* 40 */     this.value = value;
/*    */   }
/*    */   
/*    */   public IllegalAttributeException(AttributeDescriptor descriptor, Object value, String message) {
/* 44 */     super(message);
/* 45 */     this.descriptor = descriptor;
/* 46 */     this.value = value;
/*    */   }
/*    */   
/*    */   public IllegalAttributeException(AttributeDescriptor descriptor, Object value, String message, Throwable t) {
/* 51 */     super(message, t);
/* 52 */     this.descriptor = descriptor;
/* 53 */     this.value = value;
/*    */   }
/*    */   
/*    */   public IllegalAttributeException(AttributeDescriptor descriptor, Object value, Throwable t) {
/* 57 */     super(t);
/* 58 */     this.descriptor = descriptor;
/* 59 */     this.value = value;
/*    */   }
/*    */   
/*    */   public AttributeDescriptor getDescriptor() {
/* 68 */     return this.descriptor;
/*    */   }
/*    */   
/*    */   public Object getValue() {
/* 77 */     return this.value;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 82 */     String s = getClass().getName();
/* 83 */     String message = getLocalizedMessage();
/* 85 */     StringBuffer buf = new StringBuffer();
/* 86 */     buf.append(s);
/* 87 */     if (message != null) {
/* 88 */       buf.append(":");
/* 89 */       buf.append(message);
/*    */     } 
/* 91 */     if (this.descriptor != null) {
/* 92 */       buf.append(":");
/* 93 */       buf.append(this.descriptor.getName());
/*    */     } 
/* 95 */     buf.append(" value:");
/* 96 */     buf.append(this.value);
/* 98 */     return buf.toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\feature\IllegalAttributeException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */