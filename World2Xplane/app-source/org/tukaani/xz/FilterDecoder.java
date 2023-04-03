package org.tukaani.xz;

import java.io.InputStream;

interface FilterDecoder extends FilterCoder {
  int getMemoryUsage();
  
  InputStream getInputStream(InputStream paramInputStream);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\FilterDecoder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */