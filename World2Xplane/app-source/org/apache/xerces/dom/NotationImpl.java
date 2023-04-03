package org.apache.xerces.dom;

import org.apache.xerces.util.URI;
import org.w3c.dom.DOMException;
import org.w3c.dom.Notation;

public class NotationImpl extends NodeImpl implements Notation {
  static final long serialVersionUID = -764632195890658402L;
  
  protected String name;
  
  protected String publicId;
  
  protected String systemId;
  
  protected String baseURI;
  
  public NotationImpl(CoreDocumentImpl paramCoreDocumentImpl, String paramString) {
    super(paramCoreDocumentImpl);
    this.name = paramString;
  }
  
  public short getNodeType() {
    return 12;
  }
  
  public String getNodeName() {
    if (needsSyncData())
      synchronizeData(); 
    return this.name;
  }
  
  public String getPublicId() {
    if (needsSyncData())
      synchronizeData(); 
    return this.publicId;
  }
  
  public String getSystemId() {
    if (needsSyncData())
      synchronizeData(); 
    return this.systemId;
  }
  
  public void setPublicId(String paramString) {
    if (isReadOnly())
      throw new DOMException((short)7, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null)); 
    if (needsSyncData())
      synchronizeData(); 
    this.publicId = paramString;
  }
  
  public void setSystemId(String paramString) {
    if (isReadOnly())
      throw new DOMException((short)7, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null)); 
    if (needsSyncData())
      synchronizeData(); 
    this.systemId = paramString;
  }
  
  public String getBaseURI() {
    if (needsSyncData())
      synchronizeData(); 
    if (this.baseURI != null && this.baseURI.length() != 0)
      try {
        return (new URI(this.baseURI)).toString();
      } catch (org.apache.xerces.util.URI.MalformedURIException malformedURIException) {
        return null;
      }  
    return this.baseURI;
  }
  
  public void setBaseURI(String paramString) {
    if (needsSyncData())
      synchronizeData(); 
    this.baseURI = paramString;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\NotationImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */