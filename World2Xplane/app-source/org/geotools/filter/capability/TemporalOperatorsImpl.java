/*    */ package org.geotools.filter.capability;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.LinkedHashSet;
/*    */ import java.util.Set;
/*    */ import org.opengis.filter.capability.TemporalOperator;
/*    */ import org.opengis.filter.capability.TemporalOperators;
/*    */ 
/*    */ public class TemporalOperatorsImpl implements TemporalOperators {
/*    */   Set<TemporalOperator> operators;
/*    */   
/*    */   public TemporalOperatorsImpl() {
/* 37 */     this(new ArrayList<TemporalOperator>());
/*    */   }
/*    */   
/*    */   public TemporalOperatorsImpl(Collection<TemporalOperator> operators) {
/* 41 */     this.operators = new LinkedHashSet<TemporalOperator>();
/* 42 */     this.operators.addAll(operators);
/*    */   }
/*    */   
/*    */   public Collection<TemporalOperator> getOperators() {
/* 46 */     return this.operators;
/*    */   }
/*    */   
/*    */   public TemporalOperator getOperator(String name) {
/* 50 */     for (TemporalOperator op : this.operators) {
/* 51 */       if (op.getName().equals(name))
/* 52 */         return op; 
/*    */     } 
/* 55 */     return null;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 60 */     int prime = 31;
/* 61 */     int result = 1;
/* 62 */     result = 31 * result + ((this.operators == null) ? 0 : this.operators.hashCode());
/* 63 */     return result;
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj) {
/* 68 */     if (this == obj)
/* 69 */       return true; 
/* 70 */     if (obj == null)
/* 71 */       return false; 
/* 72 */     if (getClass() != obj.getClass())
/* 73 */       return false; 
/* 74 */     TemporalOperatorsImpl other = (TemporalOperatorsImpl)obj;
/* 75 */     if (this.operators == null) {
/* 76 */       if (other.operators != null)
/* 77 */         return false; 
/* 78 */     } else if (!this.operators.equals(other.operators)) {
/* 79 */       return false;
/*    */     } 
/* 80 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\capability\TemporalOperatorsImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */