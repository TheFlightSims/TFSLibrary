package org.apache.commons.digester.plugins;

import java.util.Properties;
import org.apache.commons.digester.Digester;

public abstract class RuleFinder {
  public abstract RuleLoader findLoader(Digester paramDigester, Class paramClass, Properties paramProperties) throws PluginException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\plugins\RuleFinder.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */