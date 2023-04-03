/*     */ package org.geotools.metadata.iso;
/*     */ 
/*     */ import java.net.URI;
/*     */ import org.opengis.metadata.ApplicationSchemaInformation;
/*     */ import org.opengis.metadata.SpatialAttributeSupplement;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ 
/*     */ public class ApplicationSchemaInformationImpl extends MetadataEntity implements ApplicationSchemaInformation {
/*     */   private static final long serialVersionUID = -3109191272905767382L;
/*     */   
/*     */   private Citation name;
/*     */   
/*     */   private String schemaLanguage;
/*     */   
/*     */   private String constraintLanguage;
/*     */   
/*     */   private URI schemaAscii;
/*     */   
/*     */   private URI graphicsFile;
/*     */   
/*     */   private URI softwareDevelopmentFile;
/*     */   
/*     */   private String softwareDevelopmentFileFormat;
/*     */   
/*     */   private SpatialAttributeSupplement featureCatalogueSupplement;
/*     */   
/*     */   public ApplicationSchemaInformationImpl() {}
/*     */   
/*     */   public ApplicationSchemaInformationImpl(ApplicationSchemaInformation source) {
/* 101 */     super(source);
/*     */   }
/*     */   
/*     */   public ApplicationSchemaInformationImpl(Citation name, String schemaLanguage, String constraintLanguage) {
/* 111 */     setName(name);
/* 112 */     setSchemaLanguage(schemaLanguage);
/* 113 */     setConstraintLanguage(constraintLanguage);
/*     */   }
/*     */   
/*     */   public Citation getName() {
/* 120 */     return this.name;
/*     */   }
/*     */   
/*     */   public synchronized void setName(Citation newValue) {
/* 127 */     checkWritePermission();
/* 128 */     this.name = newValue;
/*     */   }
/*     */   
/*     */   public String getSchemaLanguage() {
/* 135 */     return this.schemaLanguage;
/*     */   }
/*     */   
/*     */   public synchronized void setSchemaLanguage(String newValue) {
/* 142 */     checkWritePermission();
/* 143 */     this.schemaLanguage = newValue;
/*     */   }
/*     */   
/*     */   public String getConstraintLanguage() {
/* 150 */     return this.constraintLanguage;
/*     */   }
/*     */   
/*     */   public synchronized void setConstraintLanguage(String newValue) {
/* 157 */     checkWritePermission();
/* 158 */     this.constraintLanguage = newValue;
/*     */   }
/*     */   
/*     */   public URI getSchemaAscii() {
/* 165 */     return this.schemaAscii;
/*     */   }
/*     */   
/*     */   public synchronized void setSchemaAscii(URI newValue) {
/* 172 */     checkWritePermission();
/* 173 */     this.schemaAscii = newValue;
/*     */   }
/*     */   
/*     */   public URI getGraphicsFile() {
/* 180 */     return this.graphicsFile;
/*     */   }
/*     */   
/*     */   public synchronized void setGraphicsFile(URI newValue) {
/* 187 */     checkWritePermission();
/* 188 */     this.graphicsFile = newValue;
/*     */   }
/*     */   
/*     */   public URI getSoftwareDevelopmentFile() {
/* 195 */     return this.softwareDevelopmentFile;
/*     */   }
/*     */   
/*     */   public synchronized void setSoftwareDevelopmentFile(URI newValue) {
/* 202 */     checkWritePermission();
/* 203 */     this.softwareDevelopmentFile = newValue;
/*     */   }
/*     */   
/*     */   public String getSoftwareDevelopmentFileFormat() {
/* 210 */     return this.softwareDevelopmentFileFormat;
/*     */   }
/*     */   
/*     */   public synchronized void setSoftwareDevelopmentFileFormat(String newValue) {
/* 217 */     checkWritePermission();
/* 218 */     this.softwareDevelopmentFileFormat = newValue;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public SpatialAttributeSupplement getFeatureCatalogueSupplement() {
/* 228 */     return this.featureCatalogueSupplement;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public synchronized void setFeatureCatalogueSupplement(SpatialAttributeSupplement newValue) {
/* 238 */     checkWritePermission();
/* 239 */     this.featureCatalogueSupplement = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\ApplicationSchemaInformationImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */