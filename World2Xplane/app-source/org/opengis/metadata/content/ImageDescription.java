package org.opengis.metadata.content;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.Identifier;

@UML(identifier = "MD_ImageDescription", specification = Specification.ISO_19115)
public interface ImageDescription extends CoverageDescription {
  @UML(identifier = "illuminationElevationAngle", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Double getIlluminationElevationAngle();
  
  @UML(identifier = "illuminationAzimuthAngle", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Double getIlluminationAzimuthAngle();
  
  @UML(identifier = "imagingCondition", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  ImagingCondition getImagingCondition();
  
  @UML(identifier = "imageQualityCode", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Identifier getImageQualityCode();
  
  @UML(identifier = "cloudCoverPercentage", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Double getCloudCoverPercentage();
  
  @UML(identifier = "processingLevelCode", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Identifier getProcessingLevelCode();
  
  @UML(identifier = "compressionGenerationQuantity", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Integer getCompressionGenerationQuantity();
  
  @UML(identifier = "triangulationIndicator", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Boolean getTriangulationIndicator();
  
  @UML(identifier = "radiometricCalibrationDataAvailability", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Boolean isRadiometricCalibrationDataAvailable();
  
  @UML(identifier = "cameraCalibrationInformationAvailability", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Boolean isCameraCalibrationInformationAvailable();
  
  @UML(identifier = "filmDistortionInformationAvailability", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Boolean isFilmDistortionInformationAvailable();
  
  @UML(identifier = "lensDistortionInformationAvailability", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Boolean isLensDistortionInformationAvailable();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\content\ImageDescription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */