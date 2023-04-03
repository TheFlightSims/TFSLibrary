package org.apache.xerces.impl.xs;

import org.apache.xerces.util.NamespaceSupport;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.QName;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class SchemaNamespaceSupport extends NamespaceSupport {
  private SchemaRootContext fSchemaRootContext = null;
  
  public SchemaNamespaceSupport(Element paramElement, SymbolTable paramSymbolTable) {
    if (paramElement != null && !(paramElement instanceof org.apache.xerces.impl.xs.opti.ElementImpl)) {
      Document document = paramElement.getOwnerDocument();
      if (document != null && paramElement != document.getDocumentElement())
        this.fSchemaRootContext = new SchemaRootContext(paramElement, paramSymbolTable); 
    } 
  }
  
  public SchemaNamespaceSupport(SchemaNamespaceSupport paramSchemaNamespaceSupport) {
    this.fSchemaRootContext = paramSchemaNamespaceSupport.fSchemaRootContext;
    this.fNamespaceSize = paramSchemaNamespaceSupport.fNamespaceSize;
    if (this.fNamespace.length < this.fNamespaceSize)
      this.fNamespace = new String[this.fNamespaceSize]; 
    System.arraycopy(paramSchemaNamespaceSupport.fNamespace, 0, this.fNamespace, 0, this.fNamespaceSize);
    this.fCurrentContext = paramSchemaNamespaceSupport.fCurrentContext;
    if (this.fContext.length <= this.fCurrentContext)
      this.fContext = new int[this.fCurrentContext + 1]; 
    System.arraycopy(paramSchemaNamespaceSupport.fContext, 0, this.fContext, 0, this.fCurrentContext + 1);
  }
  
  public void setEffectiveContext(String[] paramArrayOfString) {
    if (paramArrayOfString == null || paramArrayOfString.length == 0)
      return; 
    pushContext();
    int i = this.fNamespaceSize + paramArrayOfString.length;
    if (this.fNamespace.length < i) {
      String[] arrayOfString = new String[i];
      System.arraycopy(this.fNamespace, 0, arrayOfString, 0, this.fNamespace.length);
      this.fNamespace = arrayOfString;
    } 
    System.arraycopy(paramArrayOfString, 0, this.fNamespace, this.fNamespaceSize, paramArrayOfString.length);
    this.fNamespaceSize = i;
  }
  
  public String[] getEffectiveLocalContext() {
    String[] arrayOfString = null;
    if (this.fCurrentContext >= 3) {
      int i = this.fContext[3];
      int j = this.fNamespaceSize - i;
      if (j > 0) {
        arrayOfString = new String[j];
        System.arraycopy(this.fNamespace, i, arrayOfString, 0, j);
      } 
    } 
    return arrayOfString;
  }
  
  public void makeGlobal() {
    if (this.fCurrentContext >= 3) {
      this.fCurrentContext = 3;
      this.fNamespaceSize = this.fContext[3];
    } 
  }
  
  public String getURI(String paramString) {
    String str = super.getURI(paramString);
    if (str == null && this.fSchemaRootContext != null) {
      if (!this.fSchemaRootContext.fDOMContextBuilt) {
        this.fSchemaRootContext.fillNamespaceContext();
        this.fSchemaRootContext.fDOMContextBuilt = true;
      } 
      if (this.fSchemaRootContext.fNamespaceSize > 0 && !containsPrefix(paramString))
        str = this.fSchemaRootContext.getURI(paramString); 
    } 
    return str;
  }
  
  static final class SchemaRootContext {
    String[] fNamespace = new String[32];
    
    int fNamespaceSize = 0;
    
    boolean fDOMContextBuilt = false;
    
    private final Element fSchemaRoot;
    
    private final SymbolTable fSymbolTable;
    
    private final QName fAttributeQName = new QName();
    
    SchemaRootContext(Element param1Element, SymbolTable param1SymbolTable) {
      this.fSchemaRoot = param1Element;
      this.fSymbolTable = param1SymbolTable;
    }
    
    void fillNamespaceContext() {
      if (this.fSchemaRoot != null)
        for (Node node = this.fSchemaRoot.getParentNode(); node != null; node = node.getParentNode()) {
          if (1 == node.getNodeType()) {
            NamedNodeMap namedNodeMap = node.getAttributes();
            int i = namedNodeMap.getLength();
            for (byte b = 0; b < i; b++) {
              Attr attr = (Attr)namedNodeMap.item(b);
              String str = attr.getValue();
              if (str == null)
                str = XMLSymbols.EMPTY_STRING; 
              fillQName(this.fAttributeQName, attr);
              if (this.fAttributeQName.uri == NamespaceContext.XMLNS_URI)
                if (this.fAttributeQName.prefix == XMLSymbols.PREFIX_XMLNS) {
                  declarePrefix(this.fAttributeQName.localpart, (str.length() != 0) ? this.fSymbolTable.addSymbol(str) : null);
                } else {
                  declarePrefix(XMLSymbols.EMPTY_STRING, (str.length() != 0) ? this.fSymbolTable.addSymbol(str) : null);
                }  
            } 
          } 
        }  
    }
    
    String getURI(String param1String) {
      for (byte b = 0; b < this.fNamespaceSize; b += 2) {
        if (this.fNamespace[b] == param1String)
          return this.fNamespace[b + 1]; 
      } 
      return null;
    }
    
    private void declarePrefix(String param1String1, String param1String2) {
      if (this.fNamespaceSize == this.fNamespace.length) {
        String[] arrayOfString = new String[this.fNamespaceSize * 2];
        System.arraycopy(this.fNamespace, 0, arrayOfString, 0, this.fNamespaceSize);
        this.fNamespace = arrayOfString;
      } 
      this.fNamespace[this.fNamespaceSize++] = param1String1;
      this.fNamespace[this.fNamespaceSize++] = param1String2;
    }
    
    private void fillQName(QName param1QName, Node param1Node) {
      String str1 = param1Node.getPrefix();
      String str2 = param1Node.getLocalName();
      String str3 = param1Node.getNodeName();
      String str4 = param1Node.getNamespaceURI();
      param1QName.prefix = (str1 != null) ? this.fSymbolTable.addSymbol(str1) : XMLSymbols.EMPTY_STRING;
      param1QName.localpart = (str2 != null) ? this.fSymbolTable.addSymbol(str2) : XMLSymbols.EMPTY_STRING;
      param1QName.rawname = (str3 != null) ? this.fSymbolTable.addSymbol(str3) : XMLSymbols.EMPTY_STRING;
      param1QName.uri = (str4 != null && str4.length() > 0) ? this.fSymbolTable.addSymbol(str4) : null;
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\SchemaNamespaceSupport.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */