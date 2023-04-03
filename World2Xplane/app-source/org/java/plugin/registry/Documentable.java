package org.java.plugin.registry;

public interface Documentable<T extends Identity> {
  Documentation<T> getDocumentation();
  
  String getDocsPath();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\Documentable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */