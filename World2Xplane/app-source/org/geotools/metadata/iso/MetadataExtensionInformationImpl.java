/*     */ package org.geotools.metadata.iso;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.opengis.metadata.ExtendedElementInformation;
/*     */ import org.opengis.metadata.MetadataExtensionInformation;
/*     */ import org.opengis.metadata.citation.OnLineResource;
/*     */ 
/*     */ public class MetadataExtensionInformationImpl extends MetadataEntity implements MetadataExtensionInformation {
/*     */   private static final long serialVersionUID = 573866936088674519L;
/*     */   
/*     */   private OnLineResource extensionOnLineResource;
/*     */   
/*     */   private Collection<ExtendedElementInformation> extendedElementInformation;
/*     */   
/*     */   public MetadataExtensionInformationImpl() {}
/*     */   
/*     */   public MetadataExtensionInformationImpl(MetadataExtensionInformation source) {
/*  73 */     super(source);
/*     */   }
/*     */   
/*     */   public OnLineResource getExtensionOnLineResource() {
/*  81 */     return this.extensionOnLineResource;
/*     */   }
/*     */   
/*     */   public synchronized void setExtensionOnLineResource(OnLineResource newValue) {
/*  88 */     checkWritePermission();
/*  89 */     this.extensionOnLineResource = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<ExtendedElementInformation> getExtendedElementInformation() {
/*  97 */     return this.extendedElementInformation = nonNullCollection(this.extendedElementInformation, ExtendedElementInformation.class);
/*     */   }
/*     */   
/*     */   public synchronized void setExtendedElementInformation(Collection<? extends ExtendedElementInformation> newValues) {
/* 106 */     this.extendedElementInformation = copyCollection(newValues, this.extendedElementInformation, ExtendedElementInformation.class);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\MetadataExtensionInformationImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */