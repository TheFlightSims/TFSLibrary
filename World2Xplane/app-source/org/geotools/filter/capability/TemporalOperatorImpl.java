/*    */ package org.geotools.filter.capability;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.LinkedHashSet;
/*    */ import java.util.Set;
/*    */ import org.opengis.feature.type.Name;
/*    */ import org.opengis.filter.capability.TemporalOperator;
/*    */ 
/*    */ public class TemporalOperatorImpl implements TemporalOperator {
/*    */   String name;
/*    */   
/*    */   Set<Name> operands;
/*    */   
/*    */   public TemporalOperatorImpl(String name) {
/* 37 */     this.name = name;
/* 38 */     this.operands = new LinkedHashSet<Name>();
/*    */   }
/*    */   
/*    */   public String getName() {
/* 42 */     return this.name;
/*    */   }
/*    */   
/*    */   public Collection<Name> getTemporalOperands() {
/* 46 */     return this.operands;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 51 */     int prime = 31;
/* 52 */     int result = 1;
/* 53 */     result = 31 * result + ((this.name == null) ? 0 : this.name.hashCode());
/* 54 */     result = 31 * result + ((this.operands == null) ? 0 : this.operands.hashCode());
/* 55 */     return result;
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj) {
/* 60 */     if (this == obj)
/* 61 */       return true; 
/* 62 */     if (obj == null)
/* 63 */       return false; 
/* 64 */     if (getClass() != obj.getClass())
/* 65 */       return false; 
/* 66 */     TemporalOperatorImpl other = (TemporalOperatorImpl)obj;
/* 67 */     if (this.name == null) {
/* 68 */       if (other.name != null)
/* 69 */         return false; 
/* 70 */     } else if (!this.name.equals(other.name)) {
/* 71 */       return false;
/*    */     } 
/* 72 */     if (this.operands == null) {
/* 73 */       if (other.operands != null)
/* 74 */         return false; 
/* 75 */     } else if (!this.operands.equals(other.operands)) {
/* 76 */       return false;
/*    */     } 
/* 77 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\capability\TemporalOperatorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */