/*    */ package org.geotools.filter.capability;
/*    */ 
/*    */ import org.opengis.filter.capability.Operator;
/*    */ 
/*    */ public class OperatorImpl implements Operator {
/*    */   private String name;
/*    */   
/*    */   public OperatorImpl(String name) {
/* 34 */     this.name = name;
/*    */   }
/*    */   
/*    */   public OperatorImpl(Operator copy) {
/* 37 */     this.name = copy.getName();
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 41 */     this.name = name;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 44 */     return this.name;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 49 */     int prime = 31;
/* 50 */     int result = 1;
/* 51 */     result = 31 * result + ((this.name == null) ? 0 : this.name.hashCode());
/* 52 */     return result;
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj) {
/* 57 */     if (this == obj)
/* 58 */       return true; 
/* 59 */     if (obj == null)
/* 60 */       return false; 
/* 61 */     if (getClass() != obj.getClass())
/* 62 */       return false; 
/* 63 */     OperatorImpl other = (OperatorImpl)obj;
/* 64 */     if (this.name == null) {
/* 65 */       if (other.name != null)
/* 66 */         return false; 
/* 67 */     } else if (!this.name.equals(other.name)) {
/* 68 */       return false;
/*    */     } 
/* 69 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 73 */     return getName();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\capability\OperatorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */