package org.apache.xerces.impl.dtd;

import java.util.Vector;
import org.apache.xerces.util.XMLResourceIdentifierImpl;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.grammars.XMLDTDDescription;
import org.apache.xerces.xni.grammars.XMLGrammarDescription;
import org.apache.xerces.xni.parser.XMLInputSource;

public class XMLDTDDescription extends XMLResourceIdentifierImpl implements XMLDTDDescription {
  protected String fRootName = null;
  
  protected Vector fPossibleRoots = null;
  
  public XMLDTDDescription(XMLResourceIdentifier paramXMLResourceIdentifier, String paramString) {
    setValues(paramXMLResourceIdentifier.getPublicId(), paramXMLResourceIdentifier.getLiteralSystemId(), paramXMLResourceIdentifier.getBaseSystemId(), paramXMLResourceIdentifier.getExpandedSystemId());
    this.fRootName = paramString;
    this.fPossibleRoots = null;
  }
  
  public XMLDTDDescription(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) {
    setValues(paramString1, paramString2, paramString3, paramString4);
    this.fRootName = paramString5;
    this.fPossibleRoots = null;
  }
  
  public XMLDTDDescription(XMLInputSource paramXMLInputSource) {
    setValues(paramXMLInputSource.getPublicId(), null, paramXMLInputSource.getBaseSystemId(), paramXMLInputSource.getSystemId());
    this.fRootName = null;
    this.fPossibleRoots = null;
  }
  
  public String getGrammarType() {
    return "http://www.w3.org/TR/REC-xml";
  }
  
  public String getRootName() {
    return this.fRootName;
  }
  
  public void setRootName(String paramString) {
    this.fRootName = paramString;
    this.fPossibleRoots = null;
  }
  
  public void setPossibleRoots(Vector paramVector) {
    this.fPossibleRoots = paramVector;
  }
  
  public boolean equals(Object paramObject) {
    if (!(paramObject instanceof XMLGrammarDescription))
      return false; 
    if (!getGrammarType().equals(((XMLGrammarDescription)paramObject).getGrammarType()))
      return false; 
    XMLDTDDescription xMLDTDDescription = (XMLDTDDescription)paramObject;
    if (this.fRootName != null) {
      if (xMLDTDDescription.fRootName != null && !xMLDTDDescription.fRootName.equals(this.fRootName))
        return false; 
      if (xMLDTDDescription.fPossibleRoots != null && !xMLDTDDescription.fPossibleRoots.contains(this.fRootName))
        return false; 
    } else if (this.fPossibleRoots != null) {
      if (xMLDTDDescription.fRootName != null) {
        if (!this.fPossibleRoots.contains(xMLDTDDescription.fRootName))
          return false; 
      } else {
        if (xMLDTDDescription.fPossibleRoots == null)
          return false; 
        boolean bool = false;
        for (byte b = 0; b < this.fPossibleRoots.size(); b++) {
          String str = this.fPossibleRoots.elementAt(b);
          bool = xMLDTDDescription.fPossibleRoots.contains(str);
          if (bool)
            break; 
        } 
        if (!bool)
          return false; 
      } 
    } 
    if (this.fExpandedSystemId != null) {
      if (!this.fExpandedSystemId.equals(xMLDTDDescription.fExpandedSystemId))
        return false; 
    } else if (xMLDTDDescription.fExpandedSystemId != null) {
      return false;
    } 
    if (this.fPublicId != null) {
      if (!this.fPublicId.equals(xMLDTDDescription.fPublicId))
        return false; 
    } else if (xMLDTDDescription.fPublicId != null) {
      return false;
    } 
    return true;
  }
  
  public int hashCode() {
    return (this.fExpandedSystemId != null) ? this.fExpandedSystemId.hashCode() : ((this.fPublicId != null) ? this.fPublicId.hashCode() : 0);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dtd\XMLDTDDescription.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */