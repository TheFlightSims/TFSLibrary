package org.opengis.coverage.grid;

import java.io.IOException;
import org.opengis.parameter.GeneralParameterValue;

public interface GridCoverageReader {
  Format getFormat();
  
  Object getSource();
  
  String[] getMetadataNames() throws IOException;
  
  String[] getMetadataNames(String paramString) throws IOException;
  
  String getMetadataValue(String paramString) throws IOException;
  
  String getMetadataValue(String paramString1, String paramString2) throws IOException;
  
  String[] listSubNames() throws IOException;
  
  String[] getGridCoverageNames() throws IOException;
  
  int getGridCoverageCount() throws IOException;
  
  String getCurrentSubname() throws IOException;
  
  boolean hasMoreGridCoverages() throws IOException;
  
  GridCoverage read(GeneralParameterValue[] paramArrayOfGeneralParameterValue) throws IllegalArgumentException, IOException;
  
  GridCoverage read(String paramString, GeneralParameterValue[] paramArrayOfGeneralParameterValue) throws IllegalArgumentException, IOException;
  
  void skip() throws IOException;
  
  void dispose() throws IOException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\coverage\grid\GridCoverageReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */