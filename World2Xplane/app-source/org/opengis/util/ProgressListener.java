package org.opengis.util;

public interface ProgressListener {
  InternationalString getTask();
  
  @Deprecated
  String getDescription();
  
  void setTask(InternationalString paramInternationalString);
  
  @Deprecated
  void setDescription(String paramString);
  
  void started();
  
  void progress(float paramFloat);
  
  float getProgress();
  
  void complete();
  
  void dispose();
  
  boolean isCanceled();
  
  void setCanceled(boolean paramBoolean);
  
  void warningOccurred(String paramString1, String paramString2, String paramString3);
  
  void exceptionOccurred(Throwable paramThrowable);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengi\\util\ProgressListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */