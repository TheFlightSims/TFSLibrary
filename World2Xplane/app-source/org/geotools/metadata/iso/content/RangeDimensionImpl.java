/*     */ package org.geotools.metadata.iso.content;
/*     */ 
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.content.RangeDimension;
/*     */ import org.opengis.util.InternationalString;
/*     */ import org.opengis.util.MemberName;
/*     */ 
/*     */ public class RangeDimensionImpl extends MetadataEntity implements RangeDimension {
/*     */   private static final long serialVersionUID = 4365956866782010460L;
/*     */   
/*     */   private MemberName sequenceIdentifier;
/*     */   
/*     */   private InternationalString descriptor;
/*     */   
/*     */   public RangeDimensionImpl() {}
/*     */   
/*     */   public RangeDimensionImpl(RangeDimension source) {
/*  69 */     super(source);
/*     */   }
/*     */   
/*     */   public MemberName getSequenceIdentifier() {
/*  79 */     return this.sequenceIdentifier;
/*     */   }
/*     */   
/*     */   public synchronized void setSequenceIdentifier(MemberName newValue) {
/*  87 */     checkWritePermission();
/*  88 */     this.sequenceIdentifier = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getDescriptor() {
/*  95 */     return this.descriptor;
/*     */   }
/*     */   
/*     */   public synchronized void setDescriptor(InternationalString newValue) {
/* 102 */     checkWritePermission();
/* 103 */     this.descriptor = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\content\RangeDimensionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */