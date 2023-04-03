package org.geotools.data;

import java.io.IOException;

public interface LockingManager {
  boolean exists(String paramString);
  
  boolean release(String paramString, Transaction paramTransaction) throws IOException;
  
  boolean refresh(String paramString, Transaction paramTransaction) throws IOException;
  
  void unLockFeatureID(String paramString1, String paramString2, Transaction paramTransaction, FeatureLock paramFeatureLock) throws IOException;
  
  void lockFeatureID(String paramString1, String paramString2, Transaction paramTransaction, FeatureLock paramFeatureLock) throws IOException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\LockingManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */