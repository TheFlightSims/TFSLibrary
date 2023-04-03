package org.apache.xerces.impl.xs.models;

import java.util.Vector;
import org.apache.xerces.impl.xs.SubstitutionGroupHandler;
import org.apache.xerces.impl.xs.XMLSchemaException;
import org.apache.xerces.xni.QName;

public interface XSCMValidator {
  public static final short FIRST_ERROR = -1;
  
  public static final short SUBSEQUENT_ERROR = -2;
  
  int[] startContentModel();
  
  Object oneTransition(QName paramQName, int[] paramArrayOfint, SubstitutionGroupHandler paramSubstitutionGroupHandler);
  
  boolean endContentModel(int[] paramArrayOfint);
  
  boolean checkUniqueParticleAttribution(SubstitutionGroupHandler paramSubstitutionGroupHandler) throws XMLSchemaException;
  
  Vector whatCanGoHere(int[] paramArrayOfint);
  
  boolean isCompactedForUPA();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\models\XSCMValidator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */