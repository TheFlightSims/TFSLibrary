package org.geotools.util;

import org.opengis.util.ProgressListener;

public interface ProgressListener extends ProgressListener {
  String getDescription();
  
  void setDescription(String paramString);
  
  void started();
  
  void progress(float paramFloat);
  
  void complete();
  
  void dispose();
  
  boolean isCanceled();
  
  void setCanceled(boolean paramBoolean);
  
  void warningOccurred(String paramString1, String paramString2, String paramString3);
  
  void exceptionOccurred(Throwable paramThrowable);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\ProgressListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */