package javax.media.jai;

public interface RegistryElementDescriptor {
  String getName();
  
  String[] getSupportedModes();
  
  boolean isModeSupported(String paramString);
  
  boolean arePropertiesSupported();
  
  PropertyGenerator[] getPropertyGenerators(String paramString);
  
  ParameterListDescriptor getParameterListDescriptor(String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\RegistryElementDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */