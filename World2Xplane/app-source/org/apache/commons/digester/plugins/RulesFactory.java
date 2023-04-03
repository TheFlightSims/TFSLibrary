package org.apache.commons.digester.plugins;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.Rules;

public abstract class RulesFactory {
  public abstract Rules newRules(Digester paramDigester, Class paramClass) throws PluginException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\plugins\RulesFactory.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */