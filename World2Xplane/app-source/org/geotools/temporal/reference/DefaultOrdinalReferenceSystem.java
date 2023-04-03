/*    */ package org.geotools.temporal.reference;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import org.geotools.util.Utilities;
/*    */ import org.opengis.metadata.extent.Extent;
/*    */ import org.opengis.referencing.ReferenceIdentifier;
/*    */ import org.opengis.temporal.OrdinalEra;
/*    */ import org.opengis.temporal.OrdinalReferenceSystem;
/*    */ 
/*    */ public class DefaultOrdinalReferenceSystem extends DefaultTemporalReferenceSystem implements OrdinalReferenceSystem {
/*    */   private Collection<OrdinalEra> ordinalEraSequence;
/*    */   
/*    */   public DefaultOrdinalReferenceSystem(ReferenceIdentifier name, Extent domainOfValidity, Collection<OrdinalEra> ordinalEraSequence) {
/* 42 */     super(name, domainOfValidity);
/* 43 */     this.ordinalEraSequence = ordinalEraSequence;
/*    */   }
/*    */   
/*    */   public Collection<OrdinalEra> getOrdinalEraSequence() {
/* 47 */     return this.ordinalEraSequence;
/*    */   }
/*    */   
/*    */   public String toWKT() throws UnsupportedOperationException {
/* 52 */     throw new UnsupportedOperationException("Not supported yet.");
/*    */   }
/*    */   
/*    */   public boolean equals(Object object) {
/* 57 */     if (object == this)
/* 58 */       return true; 
/* 60 */     if (object instanceof DefaultOrdinalReferenceSystem && super.equals(object)) {
/* 61 */       DefaultOrdinalReferenceSystem that = (DefaultOrdinalReferenceSystem)object;
/* 63 */       return Utilities.equals(this.ordinalEraSequence, that.ordinalEraSequence);
/*    */     } 
/* 65 */     return false;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 70 */     int hash = super.hashCode();
/* 71 */     hash = 37 * hash + ((this.ordinalEraSequence != null) ? this.ordinalEraSequence.hashCode() : 0);
/* 72 */     return hash;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 77 */     StringBuilder s = (new StringBuilder("OrdinalReferenceSystem:")).append('\n');
/* 78 */     if (this.ordinalEraSequence != null)
/* 79 */       s.append("ordinalEraSequence:").append(this.ordinalEraSequence).append('\n'); 
/* 81 */     return super.toString().concat("\n").concat(s.toString());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\temporal\reference\DefaultOrdinalReferenceSystem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */