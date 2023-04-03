/*    */ package org.geotools.filter.capability;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import org.opengis.filter.capability.TemporalCapabilities;
/*    */ import org.opengis.filter.capability.TemporalOperator;
/*    */ import org.opengis.filter.capability.TemporalOperators;
/*    */ 
/*    */ public class TemporalCapabilitiesImpl implements TemporalCapabilities {
/*    */   TemporalOperators temporalOperators;
/*    */   
/*    */   public TemporalCapabilitiesImpl() {
/* 35 */     this((TemporalOperators)null);
/*    */   }
/*    */   
/*    */   public TemporalCapabilitiesImpl(Collection<TemporalOperator> operators) {
/* 39 */     this(new TemporalOperatorsImpl(operators));
/*    */   }
/*    */   
/*    */   public TemporalCapabilitiesImpl(TemporalOperators operators) {
/* 43 */     this.temporalOperators = toTemporalOperatorsImpl(operators);
/*    */   }
/*    */   
/*    */   public TemporalCapabilitiesImpl(TemporalCapabilities capabilities) {
/* 47 */     this.temporalOperators = toTemporalOperatorsImpl(capabilities.getTemporalOperators());
/*    */   }
/*    */   
/*    */   TemporalOperators toTemporalOperatorsImpl(TemporalOperators operators) {
/* 51 */     if (operators == null)
/* 52 */       return new TemporalOperatorsImpl(); 
/* 54 */     if (operators instanceof TemporalOperatorsImpl)
/* 55 */       return operators; 
/* 57 */     return new TemporalOperatorsImpl(operators.getOperators());
/*    */   }
/*    */   
/*    */   public TemporalOperators getTemporalOperators() {
/* 61 */     return this.temporalOperators;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 66 */     int prime = 31;
/* 67 */     int result = 1;
/* 68 */     result = 31 * result + ((this.temporalOperators == null) ? 0 : this.temporalOperators.hashCode());
/* 69 */     return result;
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj) {
/* 74 */     if (this == obj)
/* 75 */       return true; 
/* 76 */     if (obj == null)
/* 77 */       return false; 
/* 78 */     if (getClass() != obj.getClass())
/* 79 */       return false; 
/* 80 */     TemporalCapabilitiesImpl other = (TemporalCapabilitiesImpl)obj;
/* 81 */     if (this.temporalOperators == null) {
/* 82 */       if (other.temporalOperators != null)
/* 83 */         return false; 
/* 84 */     } else if (!this.temporalOperators.equals(other.temporalOperators)) {
/* 85 */       return false;
/*    */     } 
/* 86 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\capability\TemporalCapabilitiesImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */