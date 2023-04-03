package org.apache.xml.serialize;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import org.apache.xerces.dom.DOMMessageFormatter;
import org.apache.xerces.util.NamespaceSupport;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.XML11Char;
import org.apache.xerces.util.XMLChar;
import org.w3c.dom.DOMError;
import org.xml.sax.SAXException;

public class XML11Serializer extends XMLSerializer {
  protected static final boolean DEBUG = false;
  
  protected NamespaceSupport fNSBinder;
  
  protected NamespaceSupport fLocalNSBinder;
  
  protected SymbolTable fSymbolTable;
  
  protected boolean fDOML1 = false;
  
  protected int fNamespaceCounter = 1;
  
  protected static final String PREFIX = "NS";
  
  protected boolean fNamespaces = false;
  
  public XML11Serializer() {
    this._format.setVersion("1.1");
  }
  
  public XML11Serializer(OutputFormat paramOutputFormat) {
    super(paramOutputFormat);
    this._format.setVersion("1.1");
  }
  
  public XML11Serializer(Writer paramWriter, OutputFormat paramOutputFormat) {
    super(paramWriter, paramOutputFormat);
    this._format.setVersion("1.1");
  }
  
  public XML11Serializer(OutputStream paramOutputStream, OutputFormat paramOutputFormat) {
    super(paramOutputStream, (paramOutputFormat != null) ? paramOutputFormat : new OutputFormat("xml", null, false));
    this._format.setVersion("1.1");
  }
  
  public void characters(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws SAXException {
    try {
      ElementState elementState = content();
      if (elementState.inCData || elementState.doCData) {
        if (!elementState.inCData) {
          this._printer.printText("<![CDATA[");
          elementState.inCData = true;
        } 
        int i = this._printer.getNextIndent();
        this._printer.setNextIndent(0);
        int j = paramInt1 + paramInt2;
        for (int k = paramInt1; k < j; k++) {
          char c = paramArrayOfchar[k];
          if (c == ']' && k + 2 < j && paramArrayOfchar[k + 1] == ']' && paramArrayOfchar[k + 2] == '>') {
            this._printer.printText("]]]]><![CDATA[>");
            k += 2;
          } else if (!XML11Char.isXML11Valid(c)) {
            if (++k < j) {
              surrogates(c, paramArrayOfchar[k], true);
            } else {
              fatalError("The character '" + c + "' is an invalid XML character");
            } 
          } else if (this._encodingInfo.isPrintable(c) && XML11Char.isXML11ValidLiteral(c)) {
            this._printer.printText(c);
          } else {
            this._printer.printText("]]>&#x");
            this._printer.printText(Integer.toHexString(c));
            this._printer.printText(";<![CDATA[");
          } 
        } 
        this._printer.setNextIndent(i);
      } else if (elementState.preserveSpace) {
        int i = this._printer.getNextIndent();
        this._printer.setNextIndent(0);
        printText(paramArrayOfchar, paramInt1, paramInt2, true, elementState.unescaped);
        this._printer.setNextIndent(i);
      } else {
        printText(paramArrayOfchar, paramInt1, paramInt2, false, elementState.unescaped);
      } 
    } catch (IOException iOException) {
      throw new SAXException(iOException);
    } 
  }
  
  protected void printEscaped(String paramString) throws IOException {
    int i = paramString.length();
    for (byte b = 0; b < i; b++) {
      char c = paramString.charAt(b);
      if (!XML11Char.isXML11Valid(c)) {
        if (++b < i) {
          surrogates(c, paramString.charAt(b), false);
        } else {
          fatalError("The character '" + (char)c + "' is an invalid XML character");
        } 
      } else if (c == '\n' || c == '\r' || c == '\t' || c == '' || c == ' ') {
        printHex(c);
      } else if (c == '<') {
        this._printer.printText("&lt;");
      } else if (c == '&') {
        this._printer.printText("&amp;");
      } else if (c == '"') {
        this._printer.printText("&quot;");
      } else if (c >= ' ' && this._encodingInfo.isPrintable((char)c)) {
        this._printer.printText((char)c);
      } else {
        printHex(c);
      } 
    } 
  }
  
  protected final void printCDATAText(String paramString) throws IOException {
    int i = paramString.length();
    for (byte b = 0; b < i; b++) {
      char c = paramString.charAt(b);
      if (c == ']' && b + 2 < i && paramString.charAt(b + 1) == ']' && paramString.charAt(b + 2) == '>') {
        if (this.fDOMErrorHandler != null)
          if ((this.features & 0x10) == 0 && (this.features & 0x2) == 0) {
            String str = DOMMessageFormatter.formatMessage("http://apache.org/xml/serializer", "EndingCDATA", null);
            modifyDOMError(str, (short)3, null, this.fCurrentNode);
            boolean bool = this.fDOMErrorHandler.handleError((DOMError)this.fDOMError);
            if (!bool)
              throw new IOException(); 
          } else {
            String str = DOMMessageFormatter.formatMessage("http://apache.org/xml/serializer", "SplittingCDATA", null);
            modifyDOMError(str, (short)1, null, this.fCurrentNode);
            this.fDOMErrorHandler.handleError((DOMError)this.fDOMError);
          }  
        this._printer.printText("]]]]><![CDATA[>");
        b += 2;
      } else if (!XML11Char.isXML11Valid(c)) {
        if (++b < i) {
          surrogates(c, paramString.charAt(b), true);
        } else {
          fatalError("The character '" + c + "' is an invalid XML character");
        } 
      } else if (this._encodingInfo.isPrintable(c) && XML11Char.isXML11ValidLiteral(c)) {
        this._printer.printText(c);
      } else {
        this._printer.printText("]]>&#x");
        this._printer.printText(Integer.toHexString(c));
        this._printer.printText(";<![CDATA[");
      } 
    } 
  }
  
  protected final void printXMLChar(int paramInt) throws IOException {
    if (paramInt == 13 || paramInt == 133 || paramInt == 8232) {
      printHex(paramInt);
    } else if (paramInt == 60) {
      this._printer.printText("&lt;");
    } else if (paramInt == 38) {
      this._printer.printText("&amp;");
    } else if (paramInt == 62) {
      this._printer.printText("&gt;");
    } else if (this._encodingInfo.isPrintable((char)paramInt) && XML11Char.isXML11ValidLiteral(paramInt)) {
      this._printer.printText((char)paramInt);
    } else {
      printHex(paramInt);
    } 
  }
  
  protected final void surrogates(int paramInt1, int paramInt2, boolean paramBoolean) throws IOException {
    if (XMLChar.isHighSurrogate(paramInt1)) {
      if (!XMLChar.isLowSurrogate(paramInt2)) {
        fatalError("The character '" + (char)paramInt2 + "' is an invalid XML character");
      } else {
        int i = XMLChar.supplemental((char)paramInt1, (char)paramInt2);
        if (!XML11Char.isXML11Valid(i)) {
          fatalError("The character '" + (char)i + "' is an invalid XML character");
        } else if (paramBoolean && (content()).inCData) {
          this._printer.printText("]]>&#x");
          this._printer.printText(Integer.toHexString(i));
          this._printer.printText(";<![CDATA[");
        } else {
          printHex(i);
        } 
      } 
    } else {
      fatalError("The character '" + (char)paramInt1 + "' is an invalid XML character");
    } 
  }
  
  protected void printText(String paramString, boolean paramBoolean1, boolean paramBoolean2) throws IOException {
    int i = paramString.length();
    if (paramBoolean1) {
      for (byte b = 0; b < i; b++) {
        char c = paramString.charAt(b);
        if (!XML11Char.isXML11Valid(c)) {
          if (++b < i) {
            surrogates(c, paramString.charAt(b), true);
          } else {
            fatalError("The character '" + c + "' is an invalid XML character");
          } 
        } else if (paramBoolean2 && XML11Char.isXML11ValidLiteral(c)) {
          this._printer.printText(c);
        } else {
          printXMLChar(c);
        } 
      } 
    } else {
      for (byte b = 0; b < i; b++) {
        char c = paramString.charAt(b);
        if (!XML11Char.isXML11Valid(c)) {
          if (++b < i) {
            surrogates(c, paramString.charAt(b), true);
          } else {
            fatalError("The character '" + c + "' is an invalid XML character");
          } 
        } else if (paramBoolean2 && XML11Char.isXML11ValidLiteral(c)) {
          this._printer.printText(c);
        } else {
          printXMLChar(c);
        } 
      } 
    } 
  }
  
  protected void printText(char[] paramArrayOfchar, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2) throws IOException {
    if (paramBoolean1) {
      while (paramInt2-- > 0) {
        char c = paramArrayOfchar[paramInt1++];
        if (!XML11Char.isXML11Valid(c)) {
          if (paramInt2-- > 0) {
            surrogates(c, paramArrayOfchar[paramInt1++], true);
            continue;
          } 
          fatalError("The character '" + c + "' is an invalid XML character");
          continue;
        } 
        if (paramBoolean2 && XML11Char.isXML11ValidLiteral(c)) {
          this._printer.printText(c);
          continue;
        } 
        printXMLChar(c);
      } 
    } else {
      while (paramInt2-- > 0) {
        char c = paramArrayOfchar[paramInt1++];
        if (!XML11Char.isXML11Valid(c)) {
          if (paramInt2-- > 0) {
            surrogates(c, paramArrayOfchar[paramInt1++], true);
            continue;
          } 
          fatalError("The character '" + c + "' is an invalid XML character");
          continue;
        } 
        if (paramBoolean2 && XML11Char.isXML11ValidLiteral(c)) {
          this._printer.printText(c);
          continue;
        } 
        printXMLChar(c);
      } 
    } 
  }
  
  public boolean reset() {
    super.reset();
    return true;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xml\serialize\XML11Serializer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */