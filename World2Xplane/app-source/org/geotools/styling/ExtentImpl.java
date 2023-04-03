/*    */ package org.geotools.styling;
/*    */ 
/*    */ import org.geotools.util.Utilities;
/*    */ import org.opengis.util.Cloneable;
/*    */ 
/*    */ public class ExtentImpl implements Extent, Cloneable {
/*    */   private String name;
/*    */   
/*    */   private String value;
/*    */   
/*    */   public String getName() {
/* 35 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 39 */     this.name = name;
/*    */   }
/*    */   
/*    */   public String getValue() {
/* 43 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(String value) {
/* 47 */     this.value = value;
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj) {
/* 51 */     if (this == obj)
/* 52 */       return true; 
/* 55 */     if (obj instanceof ExtentImpl) {
/* 56 */       ExtentImpl other = (ExtentImpl)obj;
/* 58 */       return (Utilities.equals(this.name, other.name) && Utilities.equals(this.value, other.value));
/*    */     } 
/* 62 */     return false;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 66 */     int PRIME = 1000003;
/* 67 */     int result = 0;
/* 69 */     if (this.name != null)
/* 70 */       result = 1000003 * result + this.name.hashCode(); 
/* 73 */     if (this.value != null)
/* 74 */       result = 1000003 * result + this.value.hashCode(); 
/* 77 */     return result;
/*    */   }
/*    */   
/*    */   public Object clone() {
/*    */     try {
/* 82 */       ExtentImpl clone = (ExtentImpl)super.clone();
/* 83 */       clone.setName(this.name);
/* 84 */       clone.setValue(this.value);
/* 86 */       return clone;
/* 87 */     } catch (CloneNotSupportedException e) {
/* 89 */       throw new RuntimeException("Failed to clone ExtentImpl");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\ExtentImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */