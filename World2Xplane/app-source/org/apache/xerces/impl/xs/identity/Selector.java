package org.apache.xerces.impl.xs.identity;

import org.apache.xerces.impl.xpath.XPathException;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xs.ShortList;
import org.apache.xerces.xs.XSTypeDefinition;

public class Selector {
  protected final XPath fXPath;
  
  protected final IdentityConstraint fIdentityConstraint;
  
  protected IdentityConstraint fIDConstraint;
  
  public Selector(XPath paramXPath, IdentityConstraint paramIdentityConstraint) {
    this.fXPath = paramXPath;
    this.fIdentityConstraint = paramIdentityConstraint;
  }
  
  public org.apache.xerces.impl.xpath.XPath getXPath() {
    return this.fXPath;
  }
  
  public IdentityConstraint getIDConstraint() {
    return this.fIdentityConstraint;
  }
  
  public XPathMatcher createMatcher(FieldActivator paramFieldActivator, int paramInt) {
    return new Matcher(this, this.fXPath, paramFieldActivator, paramInt);
  }
  
  public String toString() {
    return this.fXPath.toString();
  }
  
  public class Matcher extends XPathMatcher {
    protected final FieldActivator fFieldActivator;
    
    protected final int fInitialDepth;
    
    protected int fElementDepth;
    
    protected int fMatchedDepth;
    
    private final Selector this$0;
    
    public Matcher(Selector this$0, Selector.XPath param1XPath, FieldActivator param1FieldActivator, int param1Int) {
      super(param1XPath);
      this.this$0 = this$0;
      this.fFieldActivator = param1FieldActivator;
      this.fInitialDepth = param1Int;
    }
    
    public void startDocumentFragment() {
      super.startDocumentFragment();
      this.fElementDepth = 0;
      this.fMatchedDepth = -1;
    }
    
    public void startElement(QName param1QName, XMLAttributes param1XMLAttributes) {
      super.startElement(param1QName, param1XMLAttributes);
      this.fElementDepth++;
      if (isMatched()) {
        this.fMatchedDepth = this.fElementDepth;
        this.fFieldActivator.startValueScopeFor(this.this$0.fIdentityConstraint, this.fInitialDepth);
        int i = this.this$0.fIdentityConstraint.getFieldCount();
        for (byte b = 0; b < i; b++) {
          Field field = this.this$0.fIdentityConstraint.getFieldAt(b);
          XPathMatcher xPathMatcher = this.fFieldActivator.activateField(field, this.fInitialDepth);
          xPathMatcher.startElement(param1QName, param1XMLAttributes);
        } 
      } 
    }
    
    public void endElement(QName param1QName, XSTypeDefinition param1XSTypeDefinition, boolean param1Boolean, Object param1Object, short param1Short, ShortList param1ShortList) {
      super.endElement(param1QName, param1XSTypeDefinition, param1Boolean, param1Object, param1Short, param1ShortList);
      if (this.fElementDepth-- == this.fMatchedDepth) {
        this.fMatchedDepth = -1;
        this.fFieldActivator.endValueScopeFor(this.this$0.fIdentityConstraint, this.fInitialDepth);
      } 
    }
    
    public IdentityConstraint getIdentityConstraint() {
      return this.this$0.fIdentityConstraint;
    }
    
    public int getInitialDepth() {
      return this.fInitialDepth;
    }
  }
  
  public static class XPath extends org.apache.xerces.impl.xpath.XPath {
    public XPath(String param1String, SymbolTable param1SymbolTable, NamespaceContext param1NamespaceContext) throws XPathException {
      super(normalize(param1String), param1SymbolTable, param1NamespaceContext);
      for (byte b = 0; b < this.fLocationPaths.length; b++) {
        org.apache.xerces.impl.xpath.XPath.Axis axis = ((this.fLocationPaths[b]).steps[(this.fLocationPaths[b]).steps.length - 1]).axis;
        if (axis.type == 2)
          throw new XPathException("c-selector-xpath"); 
      } 
    }
    
    private static String normalize(String param1String) {
      StringBuffer stringBuffer = new StringBuffer(param1String.length() + 5);
      int i = -1;
      while (true) {
        if (!XMLChar.trim(param1String).startsWith("/") && !XMLChar.trim(param1String).startsWith("."))
          stringBuffer.append("./"); 
        i = param1String.indexOf('|');
        if (i == -1) {
          stringBuffer.append(param1String);
        } else {
          stringBuffer.append(param1String.substring(0, i + 1));
          param1String = param1String.substring(i + 1, param1String.length());
          continue;
        } 
        return stringBuffer.toString();
      } 
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\identity\Selector.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */