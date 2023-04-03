package org.jfree.base.modules;

public interface Module extends ModuleInfo {
  ModuleInfo[] getRequiredModules();
  
  ModuleInfo[] getOptionalModules();
  
  void initialize(SubSystem paramSubSystem) throws ModuleInitializeException;
  
  void configure(SubSystem paramSubSystem);
  
  String getDescription();
  
  String getProducer();
  
  String getName();
  
  String getSubSystem();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\base\modules\Module.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */