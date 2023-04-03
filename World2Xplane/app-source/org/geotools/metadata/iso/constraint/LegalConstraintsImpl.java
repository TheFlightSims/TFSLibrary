/*     */ package org.geotools.metadata.iso.constraint;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.opengis.metadata.constraint.Constraints;
/*     */ import org.opengis.metadata.constraint.LegalConstraints;
/*     */ import org.opengis.metadata.constraint.Restriction;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class LegalConstraintsImpl extends ConstraintsImpl implements LegalConstraints {
/*     */   private static final long serialVersionUID = -2891061818279024901L;
/*     */   
/*     */   private Collection<Restriction> accessConstraints;
/*     */   
/*     */   private Collection<Restriction> useConstraints;
/*     */   
/*     */   private Collection<InternationalString> otherConstraints;
/*     */   
/*     */   public LegalConstraintsImpl() {}
/*     */   
/*     */   public LegalConstraintsImpl(LegalConstraints source) {
/*  78 */     super((Constraints)source);
/*     */   }
/*     */   
/*     */   public synchronized Collection<Restriction> getAccessConstraints() {
/*  86 */     return this.accessConstraints = nonNullCollection(this.accessConstraints, Restriction.class);
/*     */   }
/*     */   
/*     */   public synchronized void setAccessConstraints(Collection<? extends Restriction> newValues) {
/*  96 */     this.accessConstraints = copyCollection(newValues, this.accessConstraints, Restriction.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<Restriction> getUseConstraints() {
/* 104 */     return this.useConstraints = nonNullCollection(this.useConstraints, Restriction.class);
/*     */   }
/*     */   
/*     */   public synchronized void setUseConstraints(Collection<? extends Restriction> newValues) {
/* 114 */     this.useConstraints = copyCollection(newValues, this.useConstraints, Restriction.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<InternationalString> getOtherConstraints() {
/* 124 */     return this.otherConstraints = nonNullCollection(this.otherConstraints, InternationalString.class);
/*     */   }
/*     */   
/*     */   public synchronized void setOtherConstraints(Collection<? extends InternationalString> newValues) {
/* 133 */     this.otherConstraints = copyCollection(newValues, this.otherConstraints, InternationalString.class);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\constraint\LegalConstraintsImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */