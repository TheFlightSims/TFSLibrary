package org.geotools.data.ows;

public interface GetCapabilitiesRequest extends Request {
  public static final String GET_CAPABILITIES = "GetCapabilities";
  
  public static final String SECTION_ALL = "/";
  
  public static final String SECTION_SERVICE = "/OGC_CAPABILITIES/ServiceMetadata";
  
  public static final String SECTION_OPERATIONS = "/OGC_CAPABILITIES/OperationSignatures";
  
  public static final String SECTION_CONTENT = "/OGC_CAPABILITIES/ContentMetadata";
  
  public static final String SECTION_COMMON = "/OGC_CAPABILITIES/Common";
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\ows\GetCapabilitiesRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */