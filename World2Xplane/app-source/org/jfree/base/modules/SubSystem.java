package org.jfree.base.modules;

import org.jfree.util.Configuration;
import org.jfree.util.ExtendedConfiguration;

public interface SubSystem {
  Configuration getGlobalConfig();
  
  ExtendedConfiguration getExtendedConfig();
  
  PackageManager getPackageManager();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\base\modules\SubSystem.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */