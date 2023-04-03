/*     */ package org.geotools.metadata.iso.content;
/*     */ 
/*     */ import org.opengis.metadata.Identifier;
/*     */ import org.opengis.metadata.content.CoverageDescription;
/*     */ import org.opengis.metadata.content.ImageDescription;
/*     */ import org.opengis.metadata.content.ImagingCondition;
/*     */ 
/*     */ public class ImageDescriptionImpl extends CoverageDescriptionImpl implements ImageDescription {
/*     */   private static final long serialVersionUID = -6168624828802439062L;
/*     */   
/*     */   private Double illuminationElevationAngle;
/*     */   
/*     */   private Double illuminationAzimuthAngle;
/*     */   
/*     */   private ImagingCondition imagingCondition;
/*     */   
/*     */   private Identifier imageQualityCode;
/*     */   
/*     */   private Double cloudCoverPercentage;
/*     */   
/*     */   private Identifier processingLevelCode;
/*     */   
/*     */   private Integer compressionGenerationQuantity;
/*     */   
/*     */   private Boolean triangulationIndicator;
/*     */   
/*     */   private Boolean radiometricCalibrationDataAvailable;
/*     */   
/*     */   private Boolean cameraCalibrationInformationAvailable;
/*     */   
/*     */   private Boolean filmDistortionInformationAvailable;
/*     */   
/*     */   private Boolean lensDistortionInformationAvailable;
/*     */   
/*     */   public ImageDescriptionImpl() {}
/*     */   
/*     */   public ImageDescriptionImpl(ImageDescription source) {
/* 125 */     super((CoverageDescription)source);
/*     */   }
/*     */   
/*     */   public Double getIlluminationElevationAngle() {
/* 134 */     return this.illuminationElevationAngle;
/*     */   }
/*     */   
/*     */   public synchronized void setIlluminationElevationAngle(Double newValue) {
/* 143 */     checkWritePermission();
/* 144 */     this.illuminationElevationAngle = newValue;
/*     */   }
/*     */   
/*     */   public Double getIlluminationAzimuthAngle() {
/* 153 */     return this.illuminationAzimuthAngle;
/*     */   }
/*     */   
/*     */   public synchronized void setIlluminationAzimuthAngle(Double newValue) {
/* 161 */     checkWritePermission();
/* 162 */     this.illuminationAzimuthAngle = newValue;
/*     */   }
/*     */   
/*     */   public ImagingCondition getImagingCondition() {
/* 169 */     return this.imagingCondition;
/*     */   }
/*     */   
/*     */   public synchronized void setImagingCondition(ImagingCondition newValue) {
/* 176 */     checkWritePermission();
/* 177 */     this.imagingCondition = newValue;
/*     */   }
/*     */   
/*     */   public Identifier getImageQualityCode() {
/* 184 */     return this.imageQualityCode;
/*     */   }
/*     */   
/*     */   public synchronized void setImageQualityCode(Identifier newValue) {
/* 191 */     checkWritePermission();
/* 192 */     this.imageQualityCode = newValue;
/*     */   }
/*     */   
/*     */   public Double getCloudCoverPercentage() {
/* 200 */     return this.cloudCoverPercentage;
/*     */   }
/*     */   
/*     */   public synchronized void setCloudCoverPercentage(Double newValue) {
/* 208 */     checkWritePermission();
/* 209 */     this.cloudCoverPercentage = newValue;
/*     */   }
/*     */   
/*     */   public Identifier getProcessingLevelCode() {
/* 217 */     return this.processingLevelCode;
/*     */   }
/*     */   
/*     */   public synchronized void setProcessingLevelCode(Identifier newValue) {
/* 225 */     checkWritePermission();
/* 226 */     this.processingLevelCode = newValue;
/*     */   }
/*     */   
/*     */   public Integer getCompressionGenerationQuantity() {
/* 234 */     return this.compressionGenerationQuantity;
/*     */   }
/*     */   
/*     */   public synchronized void setCompressionGenerationQuantity(Integer newValue) {
/* 241 */     checkWritePermission();
/* 242 */     this.compressionGenerationQuantity = newValue;
/*     */   }
/*     */   
/*     */   public Boolean getTriangulationIndicator() {
/* 250 */     return this.triangulationIndicator;
/*     */   }
/*     */   
/*     */   public synchronized void setTriangulationIndicator(Boolean newValue) {
/* 257 */     checkWritePermission();
/* 258 */     this.triangulationIndicator = newValue;
/*     */   }
/*     */   
/*     */   public Boolean isRadiometricCalibrationDataAvailable() {
/* 266 */     return this.radiometricCalibrationDataAvailable;
/*     */   }
/*     */   
/*     */   public synchronized void setRadiometricCalibrationDataAvailable(Boolean newValue) {
/* 274 */     checkWritePermission();
/* 275 */     this.radiometricCalibrationDataAvailable = newValue;
/*     */   }
/*     */   
/*     */   public Boolean isCameraCalibrationInformationAvailable() {
/* 283 */     return this.cameraCalibrationInformationAvailable;
/*     */   }
/*     */   
/*     */   public synchronized void setCameraCalibrationInformationAvailable(Boolean newValue) {
/* 291 */     checkWritePermission();
/* 292 */     this.cameraCalibrationInformationAvailable = newValue;
/*     */   }
/*     */   
/*     */   public Boolean isFilmDistortionInformationAvailable() {
/* 299 */     return this.filmDistortionInformationAvailable;
/*     */   }
/*     */   
/*     */   public synchronized void setFilmDistortionInformationAvailable(Boolean newValue) {
/* 306 */     checkWritePermission();
/* 307 */     this.filmDistortionInformationAvailable = newValue;
/*     */   }
/*     */   
/*     */   public Boolean isLensDistortionInformationAvailable() {
/* 314 */     return this.lensDistortionInformationAvailable;
/*     */   }
/*     */   
/*     */   public synchronized void setLensDistortionInformationAvailable(Boolean newValue) {
/* 321 */     checkWritePermission();
/* 322 */     this.lensDistortionInformationAvailable = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\content\ImageDescriptionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */