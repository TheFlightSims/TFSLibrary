package javax.media.jai;

public interface PropertySource {
  String[] getPropertyNames();
  
  String[] getPropertyNames(String paramString);
  
  Class getPropertyClass(String paramString);
  
  Object getProperty(String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\PropertySource.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */