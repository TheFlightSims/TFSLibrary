package org.opengis.coverage.grid;

import java.io.IOException;
import org.opengis.parameter.GeneralParameterValue;

public interface GridCoverageWriter {
  Format getFormat();
  
  Object getDestination();
  
  String[] getMetadataNames();
  
  void setMetadataValue(String paramString1, String paramString2) throws IOException;
  
  void setCurrentSubname(String paramString) throws IOException;
  
  void write(GridCoverage paramGridCoverage, GeneralParameterValue[] paramArrayOfGeneralParameterValue) throws IllegalArgumentException, IOException;
  
  void dispose() throws IOException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\coverage\grid\GridCoverageWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */