package org.apache.xerces.impl.xs.models;

import java.util.Vector;
import org.apache.xerces.impl.xs.SubstitutionGroupHandler;
import org.apache.xerces.impl.xs.XMLSchemaException;
import org.apache.xerces.impl.xs.XSConstraints;
import org.apache.xerces.impl.xs.XSElementDecl;
import org.apache.xerces.xni.QName;

public class XSAllCM implements XSCMValidator {
  private static final short STATE_START = 0;
  
  private static final short STATE_VALID = 1;
  
  private static final short STATE_CHILD = 1;
  
  private final XSElementDecl[] fAllElements;
  
  private final boolean[] fIsOptionalElement;
  
  private final boolean fHasOptionalContent;
  
  private int fNumElements = 0;
  
  public XSAllCM(boolean paramBoolean, int paramInt) {
    this.fHasOptionalContent = paramBoolean;
    this.fAllElements = new XSElementDecl[paramInt];
    this.fIsOptionalElement = new boolean[paramInt];
  }
  
  public void addElement(XSElementDecl paramXSElementDecl, boolean paramBoolean) {
    this.fAllElements[this.fNumElements] = paramXSElementDecl;
    this.fIsOptionalElement[this.fNumElements] = paramBoolean;
    this.fNumElements++;
  }
  
  public int[] startContentModel() {
    int[] arrayOfInt = new int[this.fNumElements + 1];
    for (byte b = 0; b <= this.fNumElements; b++)
      arrayOfInt[b] = 0; 
    return arrayOfInt;
  }
  
  Object findMatchingDecl(QName paramQName, SubstitutionGroupHandler paramSubstitutionGroupHandler) {
    XSElementDecl xSElementDecl = null;
    for (byte b = 0; b < this.fNumElements; b++) {
      xSElementDecl = paramSubstitutionGroupHandler.getMatchingElemDecl(paramQName, this.fAllElements[b]);
      if (xSElementDecl != null)
        break; 
    } 
    return xSElementDecl;
  }
  
  public Object oneTransition(QName paramQName, int[] paramArrayOfint, SubstitutionGroupHandler paramSubstitutionGroupHandler) {
    if (paramArrayOfint[0] < 0) {
      paramArrayOfint[0] = -2;
      return findMatchingDecl(paramQName, paramSubstitutionGroupHandler);
    } 
    paramArrayOfint[0] = 1;
    XSElementDecl xSElementDecl = null;
    for (byte b = 0; b < this.fNumElements; b++) {
      if (paramArrayOfint[b + 1] == 0) {
        xSElementDecl = paramSubstitutionGroupHandler.getMatchingElemDecl(paramQName, this.fAllElements[b]);
        if (xSElementDecl != null) {
          paramArrayOfint[b + 1] = 1;
          return xSElementDecl;
        } 
      } 
    } 
    paramArrayOfint[0] = -1;
    return findMatchingDecl(paramQName, paramSubstitutionGroupHandler);
  }
  
  public boolean endContentModel(int[] paramArrayOfint) {
    int i = paramArrayOfint[0];
    if (i == -1 || i == -2)
      return false; 
    if (this.fHasOptionalContent && i == 0)
      return true; 
    for (byte b = 0; b < this.fNumElements; b++) {
      if (!this.fIsOptionalElement[b] && paramArrayOfint[b + 1] == 0)
        return false; 
    } 
    return true;
  }
  
  public boolean checkUniqueParticleAttribution(SubstitutionGroupHandler paramSubstitutionGroupHandler) throws XMLSchemaException {
    for (byte b = 0; b < this.fNumElements; b++) {
      for (int i = b + 1; i < this.fNumElements; i++) {
        if (XSConstraints.overlapUPA(this.fAllElements[b], this.fAllElements[i], paramSubstitutionGroupHandler))
          throw new XMLSchemaException("cos-nonambig", new Object[] { this.fAllElements[b].toString(), this.fAllElements[i].toString() }); 
      } 
    } 
    return false;
  }
  
  public Vector whatCanGoHere(int[] paramArrayOfint) {
    Vector vector = new Vector();
    for (byte b = 0; b < this.fNumElements; b++) {
      if (paramArrayOfint[b + 1] == 0)
        vector.addElement(this.fAllElements[b]); 
    } 
    return vector;
  }
  
  public boolean isCompactedForUPA() {
    return false;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\models\XSAllCM.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */