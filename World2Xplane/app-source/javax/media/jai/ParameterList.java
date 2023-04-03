package javax.media.jai;

public interface ParameterList {
  ParameterListDescriptor getParameterListDescriptor();
  
  ParameterList setParameter(String paramString, byte paramByte);
  
  ParameterList setParameter(String paramString, boolean paramBoolean);
  
  ParameterList setParameter(String paramString, char paramChar);
  
  ParameterList setParameter(String paramString, short paramShort);
  
  ParameterList setParameter(String paramString, int paramInt);
  
  ParameterList setParameter(String paramString, long paramLong);
  
  ParameterList setParameter(String paramString, float paramFloat);
  
  ParameterList setParameter(String paramString, double paramDouble);
  
  ParameterList setParameter(String paramString, Object paramObject);
  
  Object getObjectParameter(String paramString);
  
  byte getByteParameter(String paramString);
  
  boolean getBooleanParameter(String paramString);
  
  char getCharParameter(String paramString);
  
  short getShortParameter(String paramString);
  
  int getIntParameter(String paramString);
  
  long getLongParameter(String paramString);
  
  float getFloatParameter(String paramString);
  
  double getDoubleParameter(String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\ParameterList.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */