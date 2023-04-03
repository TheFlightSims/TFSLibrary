package org.codehaus.stax2.evt;

import javax.xml.stream.events.DTD;

public interface DTD2 extends DTD {
  String getRootName();
  
  String getSystemId();
  
  String getPublicId();
  
  String getInternalSubset();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\evt\DTD2.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */