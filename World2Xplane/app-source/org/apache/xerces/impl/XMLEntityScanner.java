package org.apache.xerces.impl;

import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.Locale;
import org.apache.xerces.impl.io.UCSReader;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.util.XMLStringBuffer;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XMLString;

public class XMLEntityScanner implements XMLLocator {
  private static final boolean DEBUG_ENCODINGS = false;
  
  private static final boolean DEBUG_BUFFER = false;
  
  private static final EOFException END_OF_DOCUMENT_ENTITY = new EOFException() {
      private static final long serialVersionUID = 980337771224675268L;
      
      public Throwable fillInStackTrace() {
        return this;
      }
    };
  
  private XMLEntityManager fEntityManager = null;
  
  protected XMLEntityManager.ScannedEntity fCurrentEntity = null;
  
  protected SymbolTable fSymbolTable = null;
  
  protected int fBufferSize = 2048;
  
  protected XMLErrorReporter fErrorReporter;
  
  public final String getBaseSystemId() {
    return (this.fCurrentEntity != null && this.fCurrentEntity.entityLocation != null) ? this.fCurrentEntity.entityLocation.getExpandedSystemId() : null;
  }
  
  public final void setEncoding(String paramString) throws IOException {
    if (this.fCurrentEntity.stream != null && (this.fCurrentEntity.encoding == null || !this.fCurrentEntity.encoding.equals(paramString))) {
      if (this.fCurrentEntity.encoding != null && this.fCurrentEntity.encoding.startsWith("UTF-16")) {
        String str = paramString.toUpperCase(Locale.ENGLISH);
        if (str.equals("UTF-16"))
          return; 
        if (str.equals("ISO-10646-UCS-4")) {
          if (this.fCurrentEntity.encoding.equals("UTF-16BE")) {
            this.fCurrentEntity.reader = (Reader)new UCSReader(this.fCurrentEntity.stream, (short)8);
          } else {
            this.fCurrentEntity.reader = (Reader)new UCSReader(this.fCurrentEntity.stream, (short)4);
          } 
          return;
        } 
        if (str.equals("ISO-10646-UCS-2")) {
          if (this.fCurrentEntity.encoding.equals("UTF-16BE")) {
            this.fCurrentEntity.reader = (Reader)new UCSReader(this.fCurrentEntity.stream, (short)2);
          } else {
            this.fCurrentEntity.reader = (Reader)new UCSReader(this.fCurrentEntity.stream, (short)1);
          } 
          return;
        } 
      } 
      this.fCurrentEntity.setReader(this.fCurrentEntity.stream, paramString, null);
      this.fCurrentEntity.encoding = paramString;
    } 
  }
  
  public final void setXMLVersion(String paramString) {
    this.fCurrentEntity.xmlVersion = paramString;
  }
  
  public final boolean isExternal() {
    return this.fCurrentEntity.isExternal();
  }
  
  public int peekChar() throws IOException {
    if (this.fCurrentEntity.position == this.fCurrentEntity.count)
      load(0, true); 
    char c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
    return this.fCurrentEntity.isExternal() ? ((c != '\r') ? c : 10) : c;
  }
  
  public int scanChar() throws IOException {
    if (this.fCurrentEntity.position == this.fCurrentEntity.count)
      load(0, true); 
    char c = this.fCurrentEntity.ch[this.fCurrentEntity.position++];
    boolean bool = false;
    if (c == '\n' || (c == '\r' && (bool = this.fCurrentEntity.isExternal()))) {
      this.fCurrentEntity.lineNumber++;
      this.fCurrentEntity.columnNumber = 1;
      if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
        this.fCurrentEntity.ch[0] = (char)c;
        load(1, false);
      } 
      if (c == '\r' && bool) {
        if (this.fCurrentEntity.ch[this.fCurrentEntity.position++] != '\n')
          this.fCurrentEntity.position--; 
        c = '\n';
      } 
    } 
    this.fCurrentEntity.columnNumber++;
    return c;
  }
  
  public String scanNmtoken() throws IOException {
    if (this.fCurrentEntity.position == this.fCurrentEntity.count)
      load(0, true); 
    int i = this.fCurrentEntity.position;
    while (XMLChar.isName(this.fCurrentEntity.ch[this.fCurrentEntity.position])) {
      if (++this.fCurrentEntity.position == this.fCurrentEntity.count) {
        int k = this.fCurrentEntity.position - i;
        if (k == this.fCurrentEntity.ch.length) {
          char[] arrayOfChar = new char[this.fCurrentEntity.ch.length << 1];
          System.arraycopy(this.fCurrentEntity.ch, i, arrayOfChar, 0, k);
          this.fCurrentEntity.ch = arrayOfChar;
        } else {
          System.arraycopy(this.fCurrentEntity.ch, i, this.fCurrentEntity.ch, 0, k);
        } 
        i = 0;
        if (load(k, false))
          break; 
      } 
    } 
    int j = this.fCurrentEntity.position - i;
    this.fCurrentEntity.columnNumber += j;
    String str = null;
    if (j > 0)
      str = this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, i, j); 
    return str;
  }
  
  public String scanName() throws IOException {
    if (this.fCurrentEntity.position == this.fCurrentEntity.count)
      load(0, true); 
    int i = this.fCurrentEntity.position;
    if (XMLChar.isNameStart(this.fCurrentEntity.ch[i])) {
      if (++this.fCurrentEntity.position == this.fCurrentEntity.count) {
        this.fCurrentEntity.ch[0] = this.fCurrentEntity.ch[i];
        i = 0;
        if (load(1, false)) {
          this.fCurrentEntity.columnNumber++;
          return this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, 0, 1);
        } 
      } 
      while (XMLChar.isName(this.fCurrentEntity.ch[this.fCurrentEntity.position])) {
        if (++this.fCurrentEntity.position == this.fCurrentEntity.count) {
          int k = this.fCurrentEntity.position - i;
          if (k == this.fCurrentEntity.ch.length) {
            char[] arrayOfChar = new char[this.fCurrentEntity.ch.length << 1];
            System.arraycopy(this.fCurrentEntity.ch, i, arrayOfChar, 0, k);
            this.fCurrentEntity.ch = arrayOfChar;
          } else {
            System.arraycopy(this.fCurrentEntity.ch, i, this.fCurrentEntity.ch, 0, k);
          } 
          i = 0;
          if (load(k, false))
            break; 
        } 
      } 
    } 
    int j = this.fCurrentEntity.position - i;
    this.fCurrentEntity.columnNumber += j;
    String str = null;
    if (j > 0)
      str = this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, i, j); 
    return str;
  }
  
  public String scanNCName() throws IOException {
    if (this.fCurrentEntity.position == this.fCurrentEntity.count)
      load(0, true); 
    int i = this.fCurrentEntity.position;
    if (XMLChar.isNCNameStart(this.fCurrentEntity.ch[i])) {
      if (++this.fCurrentEntity.position == this.fCurrentEntity.count) {
        this.fCurrentEntity.ch[0] = this.fCurrentEntity.ch[i];
        i = 0;
        if (load(1, false)) {
          this.fCurrentEntity.columnNumber++;
          return this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, 0, 1);
        } 
      } 
      while (XMLChar.isNCName(this.fCurrentEntity.ch[this.fCurrentEntity.position])) {
        if (++this.fCurrentEntity.position == this.fCurrentEntity.count) {
          int k = this.fCurrentEntity.position - i;
          if (k == this.fCurrentEntity.ch.length) {
            char[] arrayOfChar = new char[this.fCurrentEntity.ch.length << 1];
            System.arraycopy(this.fCurrentEntity.ch, i, arrayOfChar, 0, k);
            this.fCurrentEntity.ch = arrayOfChar;
          } else {
            System.arraycopy(this.fCurrentEntity.ch, i, this.fCurrentEntity.ch, 0, k);
          } 
          i = 0;
          if (load(k, false))
            break; 
        } 
      } 
    } 
    int j = this.fCurrentEntity.position - i;
    this.fCurrentEntity.columnNumber += j;
    String str = null;
    if (j > 0)
      str = this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, i, j); 
    return str;
  }
  
  public boolean scanQName(QName paramQName) throws IOException {
    if (this.fCurrentEntity.position == this.fCurrentEntity.count)
      load(0, true); 
    int i = this.fCurrentEntity.position;
    if (XMLChar.isNCNameStart(this.fCurrentEntity.ch[i])) {
      if (++this.fCurrentEntity.position == this.fCurrentEntity.count) {
        this.fCurrentEntity.ch[0] = this.fCurrentEntity.ch[i];
        i = 0;
        if (load(1, false)) {
          this.fCurrentEntity.columnNumber++;
          String str = this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, 0, 1);
          paramQName.setValues(null, str, str, null);
          return true;
        } 
      } 
      int j = -1;
      while (XMLChar.isName(this.fCurrentEntity.ch[this.fCurrentEntity.position])) {
        char c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
        if (c == ':') {
          if (j != -1)
            break; 
          j = this.fCurrentEntity.position;
        } 
        if (++this.fCurrentEntity.position == this.fCurrentEntity.count) {
          int m = this.fCurrentEntity.position - i;
          if (m == this.fCurrentEntity.ch.length) {
            char[] arrayOfChar = new char[this.fCurrentEntity.ch.length << 1];
            System.arraycopy(this.fCurrentEntity.ch, i, arrayOfChar, 0, m);
            this.fCurrentEntity.ch = arrayOfChar;
          } else {
            System.arraycopy(this.fCurrentEntity.ch, i, this.fCurrentEntity.ch, 0, m);
          } 
          if (j != -1)
            j -= i; 
          i = 0;
          if (load(m, false))
            break; 
        } 
      } 
      int k = this.fCurrentEntity.position - i;
      this.fCurrentEntity.columnNumber += k;
      if (k > 0) {
        String str1 = null;
        String str2 = null;
        String str3 = this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, i, k);
        if (j != -1) {
          int m = j - i;
          str1 = this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, i, m);
          int n = k - m - 1;
          int i1 = j + 1;
          if (!XMLChar.isNCNameStart(this.fCurrentEntity.ch[i1]))
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "IllegalQName", null, (short)2); 
          str2 = this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, i1, n);
        } else {
          str2 = str3;
        } 
        paramQName.setValues(str1, str2, str3, null);
        return true;
      } 
    } 
    return false;
  }
  
  public int scanContent(XMLString paramXMLString) throws IOException {
    if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
      load(0, true);
    } else if (this.fCurrentEntity.position == this.fCurrentEntity.count - 1) {
      this.fCurrentEntity.ch[0] = this.fCurrentEntity.ch[this.fCurrentEntity.count - 1];
      load(1, false);
      this.fCurrentEntity.position = 0;
      this.fCurrentEntity.startPosition = 0;
    } 
    int i = this.fCurrentEntity.position;
    byte b = this.fCurrentEntity.ch[i];
    byte b1 = 0;
    boolean bool = this.fCurrentEntity.isExternal();
    if (b == 10 || (b == 13 && bool)) {
      do {
        b = this.fCurrentEntity.ch[this.fCurrentEntity.position++];
        if (b == 13 && bool) {
          b1++;
          this.fCurrentEntity.lineNumber++;
          this.fCurrentEntity.columnNumber = 1;
          if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            i = 0;
            this.fCurrentEntity.baseCharOffset += this.fCurrentEntity.position - this.fCurrentEntity.startPosition;
            this.fCurrentEntity.position = b1;
            this.fCurrentEntity.startPosition = b1;
            if (load(b1, false))
              break; 
          } 
          if (this.fCurrentEntity.ch[this.fCurrentEntity.position] == '\n') {
            this.fCurrentEntity.position++;
            i++;
          } else {
            b1++;
          } 
        } else if (b == 10) {
          b1++;
          this.fCurrentEntity.lineNumber++;
          this.fCurrentEntity.columnNumber = 1;
          if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            i = 0;
            this.fCurrentEntity.baseCharOffset += this.fCurrentEntity.position - this.fCurrentEntity.startPosition;
            this.fCurrentEntity.position = b1;
            this.fCurrentEntity.startPosition = b1;
            if (load(b1, false))
              break; 
          } 
        } else {
          this.fCurrentEntity.position--;
          break;
        } 
      } while (this.fCurrentEntity.position < this.fCurrentEntity.count - 1);
      for (int k = i; k < this.fCurrentEntity.position; k++)
        this.fCurrentEntity.ch[k] = '\n'; 
      int m = this.fCurrentEntity.position - i;
      if (this.fCurrentEntity.position == this.fCurrentEntity.count - 1) {
        paramXMLString.setValues(this.fCurrentEntity.ch, i, m);
        return -1;
      } 
    } 
    while (this.fCurrentEntity.position < this.fCurrentEntity.count) {
      b = this.fCurrentEntity.ch[this.fCurrentEntity.position++];
      if (!XMLChar.isContent(b)) {
        this.fCurrentEntity.position--;
        break;
      } 
    } 
    int j = this.fCurrentEntity.position - i;
    this.fCurrentEntity.columnNumber += j - b1;
    paramXMLString.setValues(this.fCurrentEntity.ch, i, j);
    if (this.fCurrentEntity.position != this.fCurrentEntity.count) {
      b = this.fCurrentEntity.ch[this.fCurrentEntity.position];
      if (b == 13 && bool)
        b = 10; 
    } else {
      b = -1;
    } 
    return b;
  }
  
  public int scanLiteral(int paramInt, XMLString paramXMLString) throws IOException {
    if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
      load(0, true);
    } else if (this.fCurrentEntity.position == this.fCurrentEntity.count - 1) {
      this.fCurrentEntity.ch[0] = this.fCurrentEntity.ch[this.fCurrentEntity.count - 1];
      load(1, false);
      this.fCurrentEntity.position = 0;
      this.fCurrentEntity.startPosition = 0;
    } 
    int i = this.fCurrentEntity.position;
    byte b = this.fCurrentEntity.ch[i];
    byte b1 = 0;
    boolean bool = this.fCurrentEntity.isExternal();
    if (b == 10 || (b == 13 && bool)) {
      do {
        b = this.fCurrentEntity.ch[this.fCurrentEntity.position++];
        if (b == 13 && bool) {
          b1++;
          this.fCurrentEntity.lineNumber++;
          this.fCurrentEntity.columnNumber = 1;
          if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            i = 0;
            this.fCurrentEntity.baseCharOffset += this.fCurrentEntity.position - this.fCurrentEntity.startPosition;
            this.fCurrentEntity.position = b1;
            this.fCurrentEntity.startPosition = b1;
            if (load(b1, false))
              break; 
          } 
          if (this.fCurrentEntity.ch[this.fCurrentEntity.position] == '\n') {
            this.fCurrentEntity.position++;
            i++;
          } else {
            b1++;
          } 
        } else if (b == 10) {
          b1++;
          this.fCurrentEntity.lineNumber++;
          this.fCurrentEntity.columnNumber = 1;
          if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            i = 0;
            this.fCurrentEntity.baseCharOffset += this.fCurrentEntity.position - this.fCurrentEntity.startPosition;
            this.fCurrentEntity.position = b1;
            this.fCurrentEntity.startPosition = b1;
            if (load(b1, false))
              break; 
          } 
        } else {
          this.fCurrentEntity.position--;
          break;
        } 
      } while (this.fCurrentEntity.position < this.fCurrentEntity.count - 1);
      for (int k = i; k < this.fCurrentEntity.position; k++)
        this.fCurrentEntity.ch[k] = '\n'; 
      int m = this.fCurrentEntity.position - i;
      if (this.fCurrentEntity.position == this.fCurrentEntity.count - 1) {
        paramXMLString.setValues(this.fCurrentEntity.ch, i, m);
        return -1;
      } 
    } 
    while (this.fCurrentEntity.position < this.fCurrentEntity.count) {
      b = this.fCurrentEntity.ch[this.fCurrentEntity.position++];
      if ((b == paramInt && (!this.fCurrentEntity.literal || bool)) || b == 37 || !XMLChar.isContent(b)) {
        this.fCurrentEntity.position--;
        break;
      } 
    } 
    int j = this.fCurrentEntity.position - i;
    this.fCurrentEntity.columnNumber += j - b1;
    paramXMLString.setValues(this.fCurrentEntity.ch, i, j);
    if (this.fCurrentEntity.position != this.fCurrentEntity.count) {
      b = this.fCurrentEntity.ch[this.fCurrentEntity.position];
      if (b == paramInt && this.fCurrentEntity.literal)
        byte b2 = -1; 
    } else {
      b = -1;
    } 
    return b;
  }
  
  public boolean scanData(String paramString, XMLStringBuffer paramXMLStringBuffer) throws IOException {
    boolean bool = false;
    int i = paramString.length();
    char c1 = paramString.charAt(0);
    boolean bool1 = this.fCurrentEntity.isExternal();
    if (this.fCurrentEntity.position == this.fCurrentEntity.count)
      load(0, true); 
    boolean bool2 = false;
    while (this.fCurrentEntity.position > this.fCurrentEntity.count - i && !bool2) {
      System.arraycopy(this.fCurrentEntity.ch, this.fCurrentEntity.position, this.fCurrentEntity.ch, 0, this.fCurrentEntity.count - this.fCurrentEntity.position);
      bool2 = load(this.fCurrentEntity.count - this.fCurrentEntity.position, false);
      this.fCurrentEntity.position = 0;
      this.fCurrentEntity.startPosition = 0;
    } 
    if (this.fCurrentEntity.position > this.fCurrentEntity.count - i) {
      int m = this.fCurrentEntity.count - this.fCurrentEntity.position;
      paramXMLStringBuffer.append(this.fCurrentEntity.ch, this.fCurrentEntity.position, m);
      this.fCurrentEntity.columnNumber += this.fCurrentEntity.count;
      this.fCurrentEntity.baseCharOffset += this.fCurrentEntity.position - this.fCurrentEntity.startPosition;
      this.fCurrentEntity.position = this.fCurrentEntity.count;
      this.fCurrentEntity.startPosition = this.fCurrentEntity.count;
      load(0, true);
      return false;
    } 
    int j = this.fCurrentEntity.position;
    char c2 = this.fCurrentEntity.ch[j];
    byte b = 0;
    if (c2 == '\n' || (c2 == '\r' && bool1)) {
      do {
        c2 = this.fCurrentEntity.ch[this.fCurrentEntity.position++];
        if (c2 == '\r' && bool1) {
          b++;
          this.fCurrentEntity.lineNumber++;
          this.fCurrentEntity.columnNumber = 1;
          if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            j = 0;
            this.fCurrentEntity.baseCharOffset += this.fCurrentEntity.position - this.fCurrentEntity.startPosition;
            this.fCurrentEntity.position = b;
            this.fCurrentEntity.startPosition = b;
            if (load(b, false))
              break; 
          } 
          if (this.fCurrentEntity.ch[this.fCurrentEntity.position] == '\n') {
            this.fCurrentEntity.position++;
            j++;
          } else {
            b++;
          } 
        } else if (c2 == '\n') {
          b++;
          this.fCurrentEntity.lineNumber++;
          this.fCurrentEntity.columnNumber = 1;
          if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            j = 0;
            this.fCurrentEntity.baseCharOffset += this.fCurrentEntity.position - this.fCurrentEntity.startPosition;
            this.fCurrentEntity.position = b;
            this.fCurrentEntity.startPosition = b;
            this.fCurrentEntity.count = b;
            if (load(b, false))
              break; 
          } 
        } else {
          this.fCurrentEntity.position--;
          break;
        } 
      } while (this.fCurrentEntity.position < this.fCurrentEntity.count - 1);
      for (int m = j; m < this.fCurrentEntity.position; m++)
        this.fCurrentEntity.ch[m] = '\n'; 
      int n = this.fCurrentEntity.position - j;
      if (this.fCurrentEntity.position == this.fCurrentEntity.count - 1) {
        paramXMLStringBuffer.append(this.fCurrentEntity.ch, j, n);
        return true;
      } 
    } 
    label82: while (this.fCurrentEntity.position < this.fCurrentEntity.count) {
      c2 = this.fCurrentEntity.ch[this.fCurrentEntity.position++];
      if (c2 == c1) {
        int m = this.fCurrentEntity.position - 1;
        for (byte b1 = 1; b1 < i; b1++) {
          if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            this.fCurrentEntity.position -= b1;
            break label82;
          } 
          c2 = this.fCurrentEntity.ch[this.fCurrentEntity.position++];
          if (paramString.charAt(b1) != c2) {
            this.fCurrentEntity.position--;
            break;
          } 
        } 
        if (this.fCurrentEntity.position == m + i) {
          bool = true;
          break;
        } 
        continue;
      } 
      if (c2 == '\n' || (bool1 && c2 == '\r')) {
        this.fCurrentEntity.position--;
        break;
      } 
      if (XMLChar.isInvalid(c2)) {
        this.fCurrentEntity.position--;
        int m = this.fCurrentEntity.position - j;
        this.fCurrentEntity.columnNumber += m - b;
        paramXMLStringBuffer.append(this.fCurrentEntity.ch, j, m);
        return true;
      } 
    } 
    int k = this.fCurrentEntity.position - j;
    this.fCurrentEntity.columnNumber += k - b;
    if (bool)
      k -= i; 
    paramXMLStringBuffer.append(this.fCurrentEntity.ch, j, k);
    return !bool;
  }
  
  public boolean skipChar(int paramInt) throws IOException {
    if (this.fCurrentEntity.position == this.fCurrentEntity.count)
      load(0, true); 
    char c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
    if (c == paramInt) {
      this.fCurrentEntity.position++;
      if (paramInt == 10) {
        this.fCurrentEntity.lineNumber++;
        this.fCurrentEntity.columnNumber = 1;
      } else {
        this.fCurrentEntity.columnNumber++;
      } 
      return true;
    } 
    if (paramInt == 10 && c == '\r' && this.fCurrentEntity.isExternal()) {
      if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
        this.fCurrentEntity.ch[0] = (char)c;
        load(1, false);
      } 
      this.fCurrentEntity.position++;
      if (this.fCurrentEntity.ch[this.fCurrentEntity.position] == '\n')
        this.fCurrentEntity.position++; 
      this.fCurrentEntity.lineNumber++;
      this.fCurrentEntity.columnNumber = 1;
      return true;
    } 
    return false;
  }
  
  public boolean skipSpaces() throws IOException {
    if (this.fCurrentEntity.position == this.fCurrentEntity.count)
      load(0, true); 
    char c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
    if (XMLChar.isSpace(c)) {
      boolean bool = this.fCurrentEntity.isExternal();
      while (true) {
        boolean bool1 = false;
        if (c == '\n' || (bool && c == '\r')) {
          this.fCurrentEntity.lineNumber++;
          this.fCurrentEntity.columnNumber = 1;
          if (this.fCurrentEntity.position == this.fCurrentEntity.count - 1) {
            this.fCurrentEntity.ch[0] = (char)c;
            bool1 = load(1, true);
            if (!bool1) {
              this.fCurrentEntity.position = 0;
              this.fCurrentEntity.startPosition = 0;
            } 
          } 
          if (c == '\r' && bool && this.fCurrentEntity.ch[++this.fCurrentEntity.position] != '\n')
            this.fCurrentEntity.position--; 
        } else {
          this.fCurrentEntity.columnNumber++;
        } 
        if (!bool1)
          this.fCurrentEntity.position++; 
        if (this.fCurrentEntity.position == this.fCurrentEntity.count)
          load(0, true); 
        if (!XMLChar.isSpace(c = this.fCurrentEntity.ch[this.fCurrentEntity.position]))
          return true; 
      } 
    } 
    return false;
  }
  
  public final boolean skipDeclSpaces() throws IOException {
    if (this.fCurrentEntity.position == this.fCurrentEntity.count)
      load(0, true); 
    char c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
    if (XMLChar.isSpace(c)) {
      boolean bool = this.fCurrentEntity.isExternal();
      while (true) {
        boolean bool1 = false;
        if (c == '\n' || (bool && c == '\r')) {
          this.fCurrentEntity.lineNumber++;
          this.fCurrentEntity.columnNumber = 1;
          if (this.fCurrentEntity.position == this.fCurrentEntity.count - 1) {
            this.fCurrentEntity.ch[0] = (char)c;
            bool1 = load(1, true);
            if (!bool1) {
              this.fCurrentEntity.position = 0;
              this.fCurrentEntity.startPosition = 0;
            } 
          } 
          if (c == '\r' && bool && this.fCurrentEntity.ch[++this.fCurrentEntity.position] != '\n')
            this.fCurrentEntity.position--; 
        } else {
          this.fCurrentEntity.columnNumber++;
        } 
        if (!bool1)
          this.fCurrentEntity.position++; 
        if (this.fCurrentEntity.position == this.fCurrentEntity.count)
          load(0, true); 
        if (!XMLChar.isSpace(c = this.fCurrentEntity.ch[this.fCurrentEntity.position]))
          return true; 
      } 
    } 
    return false;
  }
  
  public boolean skipString(String paramString) throws IOException {
    if (this.fCurrentEntity.position == this.fCurrentEntity.count)
      load(0, true); 
    int i = paramString.length();
    for (byte b = 0; b < i; b++) {
      char c = this.fCurrentEntity.ch[this.fCurrentEntity.position++];
      if (c != paramString.charAt(b)) {
        this.fCurrentEntity.position -= b + 1;
        return false;
      } 
      if (b < i - 1 && this.fCurrentEntity.position == this.fCurrentEntity.count) {
        System.arraycopy(this.fCurrentEntity.ch, this.fCurrentEntity.count - b - 1, this.fCurrentEntity.ch, 0, b + 1);
        if (load(b + 1, false)) {
          this.fCurrentEntity.startPosition -= b + 1;
          this.fCurrentEntity.position -= b + 1;
          return false;
        } 
      } 
    } 
    this.fCurrentEntity.columnNumber += i;
    return true;
  }
  
  public final String getPublicId() {
    return (this.fCurrentEntity != null && this.fCurrentEntity.entityLocation != null) ? this.fCurrentEntity.entityLocation.getPublicId() : null;
  }
  
  public final String getExpandedSystemId() {
    return (this.fCurrentEntity != null) ? ((this.fCurrentEntity.entityLocation != null && this.fCurrentEntity.entityLocation.getExpandedSystemId() != null) ? this.fCurrentEntity.entityLocation.getExpandedSystemId() : this.fCurrentEntity.getExpandedSystemId()) : null;
  }
  
  public final String getLiteralSystemId() {
    return (this.fCurrentEntity != null) ? ((this.fCurrentEntity.entityLocation != null && this.fCurrentEntity.entityLocation.getLiteralSystemId() != null) ? this.fCurrentEntity.entityLocation.getLiteralSystemId() : this.fCurrentEntity.getLiteralSystemId()) : null;
  }
  
  public final int getLineNumber() {
    return (this.fCurrentEntity != null) ? (this.fCurrentEntity.isExternal() ? this.fCurrentEntity.lineNumber : this.fCurrentEntity.getLineNumber()) : -1;
  }
  
  public final int getColumnNumber() {
    return (this.fCurrentEntity != null) ? (this.fCurrentEntity.isExternal() ? this.fCurrentEntity.columnNumber : this.fCurrentEntity.getColumnNumber()) : -1;
  }
  
  public final int getCharacterOffset() {
    return (this.fCurrentEntity != null) ? (this.fCurrentEntity.isExternal() ? (this.fCurrentEntity.baseCharOffset + this.fCurrentEntity.position - this.fCurrentEntity.startPosition) : this.fCurrentEntity.getCharacterOffset()) : -1;
  }
  
  public final String getEncoding() {
    return (this.fCurrentEntity != null) ? (this.fCurrentEntity.isExternal() ? this.fCurrentEntity.encoding : this.fCurrentEntity.getEncoding()) : null;
  }
  
  public final String getXMLVersion() {
    return (this.fCurrentEntity != null) ? (this.fCurrentEntity.isExternal() ? this.fCurrentEntity.xmlVersion : this.fCurrentEntity.getXMLVersion()) : null;
  }
  
  public final void setCurrentEntity(XMLEntityManager.ScannedEntity paramScannedEntity) {
    this.fCurrentEntity = paramScannedEntity;
  }
  
  public final void setBufferSize(int paramInt) {
    this.fBufferSize = paramInt;
  }
  
  public final void reset(SymbolTable paramSymbolTable, XMLEntityManager paramXMLEntityManager, XMLErrorReporter paramXMLErrorReporter) {
    this.fCurrentEntity = null;
    this.fSymbolTable = paramSymbolTable;
    this.fEntityManager = paramXMLEntityManager;
    this.fErrorReporter = paramXMLErrorReporter;
  }
  
  final boolean load(int paramInt, boolean paramBoolean) throws IOException {
    this.fCurrentEntity.baseCharOffset += this.fCurrentEntity.position - this.fCurrentEntity.startPosition;
    boolean bool1 = this.fCurrentEntity.mayReadChunks ? (this.fCurrentEntity.ch.length - paramInt) : true;
    int i = this.fCurrentEntity.reader.read(this.fCurrentEntity.ch, paramInt, bool1);
    boolean bool2 = false;
    if (i != -1) {
      if (i != 0) {
        this.fCurrentEntity.count = i + paramInt;
        this.fCurrentEntity.position = paramInt;
        this.fCurrentEntity.startPosition = paramInt;
      } 
    } else {
      this.fCurrentEntity.count = paramInt;
      this.fCurrentEntity.position = paramInt;
      this.fCurrentEntity.startPosition = paramInt;
      bool2 = true;
      if (paramBoolean) {
        this.fEntityManager.endEntity();
        if (this.fCurrentEntity == null)
          throw END_OF_DOCUMENT_ENTITY; 
        if (this.fCurrentEntity.position == this.fCurrentEntity.count)
          load(0, true); 
      } 
    } 
    return bool2;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\XMLEntityScanner.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */