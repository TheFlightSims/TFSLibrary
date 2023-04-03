package org.apache.xerces.impl.xs.identity;

import org.apache.xerces.impl.xpath.XPathException;
import org.apache.xerces.impl.xs.util.ShortListImpl;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xs.ShortList;
import org.apache.xerces.xs.XSComplexTypeDefinition;
import org.apache.xerces.xs.XSTypeDefinition;

public class Field {
  protected final XPath fXPath;
  
  protected final IdentityConstraint fIdentityConstraint;
  
  public Field(XPath paramXPath, IdentityConstraint paramIdentityConstraint) {
    this.fXPath = paramXPath;
    this.fIdentityConstraint = paramIdentityConstraint;
  }
  
  public org.apache.xerces.impl.xpath.XPath getXPath() {
    return this.fXPath;
  }
  
  public IdentityConstraint getIdentityConstraint() {
    return this.fIdentityConstraint;
  }
  
  public XPathMatcher createMatcher(ValueStore paramValueStore) {
    return new Matcher(this, this.fXPath, paramValueStore);
  }
  
  public String toString() {
    return this.fXPath.toString();
  }
  
  protected class Matcher extends XPathMatcher {
    protected final ValueStore fStore;
    
    protected boolean fMayMatch;
    
    private final Field this$0;
    
    public Matcher(Field this$0, Field.XPath param1XPath, ValueStore param1ValueStore) {
      super(param1XPath);
      this.this$0 = this$0;
      this.fMayMatch = true;
      this.fStore = param1ValueStore;
    }
    
    protected void matched(Object param1Object, short param1Short, ShortList param1ShortList, boolean param1Boolean) {
      super.matched(param1Object, param1Short, param1ShortList, param1Boolean);
      if (param1Boolean && this.this$0.fIdentityConstraint.getCategory() == 1) {
        String str = "KeyMatchesNillable";
        this.fStore.reportError(str, new Object[] { this.this$0.fIdentityConstraint.getElementName(), this.this$0.fIdentityConstraint.getIdentityConstraintName() });
      } 
      this.fStore.addValue(this.this$0, this.fMayMatch, param1Object, convertToPrimitiveKind(param1Short), convertToPrimitiveKind(param1ShortList));
      this.fMayMatch = false;
    }
    
    private short convertToPrimitiveKind(short param1Short) {
      return (param1Short <= 20) ? param1Short : ((param1Short <= 29) ? 2 : ((param1Short <= 42) ? 4 : param1Short));
    }
    
    private ShortList convertToPrimitiveKind(ShortList param1ShortList) {
      if (param1ShortList != null) {
        int i = param1ShortList.getLength();
        byte b;
        for (b = 0; b < i; b++) {
          short s = param1ShortList.item(b);
          if (s != convertToPrimitiveKind(s))
            break; 
        } 
        if (b != i) {
          short[] arrayOfShort = new short[i];
          for (byte b1 = 0; b1 < b; b1++)
            arrayOfShort[b1] = param1ShortList.item(b1); 
          while (b < i) {
            arrayOfShort[b] = convertToPrimitiveKind(param1ShortList.item(b));
            b++;
          } 
          return (ShortList)new ShortListImpl(arrayOfShort, arrayOfShort.length);
        } 
      } 
      return param1ShortList;
    }
    
    protected void handleContent(XSTypeDefinition param1XSTypeDefinition, boolean param1Boolean, Object param1Object, short param1Short, ShortList param1ShortList) {
      if (param1XSTypeDefinition == null || (param1XSTypeDefinition.getTypeCategory() == 15 && ((XSComplexTypeDefinition)param1XSTypeDefinition).getContentType() != 1))
        this.fStore.reportError("cvc-id.3", new Object[] { this.this$0.fIdentityConstraint.getName(), this.this$0.fIdentityConstraint.getElementName() }); 
      this.fMatchedString = param1Object;
      matched(this.fMatchedString, param1Short, param1ShortList, param1Boolean);
    }
  }
  
  public static class XPath extends org.apache.xerces.impl.xpath.XPath {
    public XPath(String param1String, SymbolTable param1SymbolTable, NamespaceContext param1NamespaceContext) throws XPathException {
      super(fixupXPath(param1String), param1SymbolTable, param1NamespaceContext);
      for (byte b = 0; b < this.fLocationPaths.length; b++) {
        for (byte b1 = 0; b1 < (this.fLocationPaths[b]).steps.length; b1++) {
          org.apache.xerces.impl.xpath.XPath.Axis axis = ((this.fLocationPaths[b]).steps[b1]).axis;
          if (axis.type == 2 && b1 < (this.fLocationPaths[b]).steps.length - 1)
            throw new XPathException("c-fields-xpaths"); 
        } 
      } 
    }
    
    private static String fixupXPath(String param1String) {
      int i = param1String.length();
      byte b = 0;
      boolean bool = true;
      while (b < i) {
        char c = param1String.charAt(b);
        if (bool) {
          if (!XMLChar.isSpace(c))
            if (c == '.' || c == '/') {
              bool = false;
            } else if (c != '|') {
              return fixupXPath2(param1String, b, i);
            }  
        } else if (c == '|') {
          bool = true;
        } 
        b++;
      } 
      return param1String;
    }
    
    private static String fixupXPath2(String param1String, int param1Int1, int param1Int2) {
      StringBuffer stringBuffer = new StringBuffer(param1Int2 + 2);
      for (byte b = 0; b < param1Int1; b++)
        stringBuffer.append(param1String.charAt(b)); 
      stringBuffer.append("./");
      boolean bool = false;
      while (param1Int1 < param1Int2) {
        char c = param1String.charAt(param1Int1);
        if (bool) {
          if (!XMLChar.isSpace(c))
            if (c == '.' || c == '/') {
              bool = false;
            } else if (c != '|') {
              stringBuffer.append("./");
              bool = false;
            }  
        } else if (c == '|') {
          bool = true;
        } 
        stringBuffer.append(c);
        param1Int1++;
      } 
      return stringBuffer.toString();
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\identity\Field.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */