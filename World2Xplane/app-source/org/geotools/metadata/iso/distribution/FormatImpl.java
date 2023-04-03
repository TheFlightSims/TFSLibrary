/*     */ package org.geotools.metadata.iso.distribution;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.distribution.Distributor;
/*     */ import org.opengis.metadata.distribution.Format;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class FormatImpl extends MetadataEntity implements Format {
/*     */   private static final long serialVersionUID = 6498897239493553607L;
/*     */   
/*     */   private InternationalString name;
/*     */   
/*     */   private InternationalString version;
/*     */   
/*     */   private InternationalString amendmentNumber;
/*     */   
/*     */   private InternationalString specification;
/*     */   
/*     */   private InternationalString fileDecompressionTechnique;
/*     */   
/*     */   private Collection<Distributor> formatDistributors;
/*     */   
/*     */   public FormatImpl() {}
/*     */   
/*     */   public FormatImpl(Format source) {
/*  91 */     super(source);
/*     */   }
/*     */   
/*     */   public FormatImpl(InternationalString name, InternationalString version) {
/*  98 */     setName(name);
/*  99 */     setVersion(version);
/*     */   }
/*     */   
/*     */   public InternationalString getName() {
/* 106 */     return this.name;
/*     */   }
/*     */   
/*     */   public synchronized void setName(InternationalString newValue) {
/* 113 */     checkWritePermission();
/* 114 */     this.name = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getVersion() {
/* 121 */     return this.version;
/*     */   }
/*     */   
/*     */   public synchronized void setVersion(InternationalString newValue) {
/* 128 */     checkWritePermission();
/* 129 */     this.version = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getAmendmentNumber() {
/* 136 */     return this.amendmentNumber;
/*     */   }
/*     */   
/*     */   public synchronized void setAmendmentNumber(InternationalString newValue) {
/* 143 */     checkWritePermission();
/* 144 */     this.amendmentNumber = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getSpecification() {
/* 151 */     return this.specification;
/*     */   }
/*     */   
/*     */   public synchronized void setSpecification(InternationalString newValue) {
/* 158 */     checkWritePermission();
/* 159 */     this.specification = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getFileDecompressionTechnique() {
/* 167 */     return this.fileDecompressionTechnique;
/*     */   }
/*     */   
/*     */   public synchronized void setFileDecompressionTechnique(InternationalString newValue) {
/* 175 */     checkWritePermission();
/* 176 */     this.fileDecompressionTechnique = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<Distributor> getFormatDistributors() {
/* 183 */     return this.formatDistributors = nonNullCollection(this.formatDistributors, Distributor.class);
/*     */   }
/*     */   
/*     */   public synchronized void setFormatDistributors(Collection<? extends Distributor> newValues) {
/* 192 */     this.formatDistributors = copyCollection(newValues, this.formatDistributors, Distributor.class);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\distribution\FormatImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */