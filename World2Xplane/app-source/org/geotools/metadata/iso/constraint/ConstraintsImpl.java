/*    */ package org.geotools.metadata.iso.constraint;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import org.geotools.metadata.iso.MetadataEntity;
/*    */ import org.opengis.metadata.constraint.Constraints;
/*    */ import org.opengis.util.InternationalString;
/*    */ 
/*    */ public class ConstraintsImpl extends MetadataEntity implements Constraints {
/*    */   private static final long serialVersionUID = 7197823876215294777L;
/*    */   
/*    */   private Collection<InternationalString> useLimitation;
/*    */   
/*    */   public ConstraintsImpl() {}
/*    */   
/*    */   public ConstraintsImpl(Constraints source) {
/* 65 */     super(source);
/*    */   }
/*    */   
/*    */   public synchronized Collection<InternationalString> getUseLimitation() {
/* 73 */     return this.useLimitation = nonNullCollection(this.useLimitation, InternationalString.class);
/*    */   }
/*    */   
/*    */   public synchronized void setUseLimitation(Collection<? extends InternationalString> newValues) {
/* 83 */     this.useLimitation = copyCollection(newValues, this.useLimitation, InternationalString.class);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\constraint\ConstraintsImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */