/*     */ package org.geotools.metadata.iso.identification;
/*     */ 
/*     */ import java.net.URI;
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.identification.BrowseGraphic;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class BrowseGraphicImpl extends MetadataEntity implements BrowseGraphic {
/*     */   private static final long serialVersionUID = 1715873406472953616L;
/*     */   
/*     */   private URI applicationSchemaInformation;
/*     */   
/*     */   private URI fileName;
/*     */   
/*     */   private InternationalString fileDescription;
/*     */   
/*     */   private String fileType;
/*     */   
/*     */   public BrowseGraphicImpl() {}
/*     */   
/*     */   public BrowseGraphicImpl(BrowseGraphic source) {
/*  79 */     super(source);
/*     */   }
/*     */   
/*     */   public BrowseGraphicImpl(URI fileName) {
/*  86 */     setFileName(fileName);
/*     */   }
/*     */   
/*     */   public URI getApplicationSchemaInformation() {
/*  93 */     return this.applicationSchemaInformation;
/*     */   }
/*     */   
/*     */   public synchronized void setApplicationSchemaInformation(URI newValue) {
/* 101 */     checkWritePermission();
/* 102 */     this.applicationSchemaInformation = newValue;
/*     */   }
/*     */   
/*     */   public URI getFileName() {
/* 109 */     return this.fileName;
/*     */   }
/*     */   
/*     */   public synchronized void setFileName(URI newValue) {
/* 117 */     checkWritePermission();
/* 118 */     this.fileName = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getFileDescription() {
/* 125 */     return this.fileDescription;
/*     */   }
/*     */   
/*     */   public synchronized void setFileDescription(InternationalString newValue) {
/* 132 */     checkWritePermission();
/* 133 */     this.fileDescription = newValue;
/*     */   }
/*     */   
/*     */   public String getFileType() {
/* 141 */     return this.fileType;
/*     */   }
/*     */   
/*     */   public synchronized void setFileType(String newValue) {
/* 148 */     checkWritePermission();
/* 149 */     this.fileType = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\identification\BrowseGraphicImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */