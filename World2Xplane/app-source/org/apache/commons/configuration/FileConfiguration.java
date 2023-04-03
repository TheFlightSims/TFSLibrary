package org.apache.commons.configuration;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import org.apache.commons.configuration.reloading.ReloadingStrategy;

public interface FileConfiguration extends Configuration {
  void load() throws ConfigurationException;
  
  void load(String paramString) throws ConfigurationException;
  
  void load(File paramFile) throws ConfigurationException;
  
  void load(URL paramURL) throws ConfigurationException;
  
  void load(InputStream paramInputStream) throws ConfigurationException;
  
  void load(InputStream paramInputStream, String paramString) throws ConfigurationException;
  
  void load(Reader paramReader) throws ConfigurationException;
  
  void save() throws ConfigurationException;
  
  void save(String paramString) throws ConfigurationException;
  
  void save(File paramFile) throws ConfigurationException;
  
  void save(URL paramURL) throws ConfigurationException;
  
  void save(OutputStream paramOutputStream) throws ConfigurationException;
  
  void save(OutputStream paramOutputStream, String paramString) throws ConfigurationException;
  
  void save(Writer paramWriter) throws ConfigurationException;
  
  String getFileName();
  
  void setFileName(String paramString);
  
  String getBasePath();
  
  void setBasePath(String paramString);
  
  File getFile();
  
  void setFile(File paramFile);
  
  URL getURL();
  
  void setURL(URL paramURL);
  
  void setAutoSave(boolean paramBoolean);
  
  boolean isAutoSave();
  
  ReloadingStrategy getReloadingStrategy();
  
  void setReloadingStrategy(ReloadingStrategy paramReloadingStrategy);
  
  void reload();
  
  String getEncoding();
  
  void setEncoding(String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\FileConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */