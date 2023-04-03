package org.apache.xerces.impl;

import java.io.IOException;
import org.apache.xerces.util.XML11Char;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.util.XMLStringBuffer;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLString;

public class XML11EntityScanner extends XMLEntityScanner {
  public int peekChar() throws IOException {
    if (this.fCurrentEntity.position == this.fCurrentEntity.count)
      load(0, true); 
    char c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
    return this.fCurrentEntity.isExternal() ? ((c != '\r' && c != '' && c != ' ') ? c : 10) : c;
  }
  
  public int scanChar() throws IOException {
    if (this.fCurrentEntity.position == this.fCurrentEntity.count)
      load(0, true); 
    char c = this.fCurrentEntity.ch[this.fCurrentEntity.position++];
    boolean bool = false;
    if (c == '\n' || ((c == '\r' || c == '' || c == ' ') && (bool = this.fCurrentEntity.isExternal()))) {
      this.fCurrentEntity.lineNumber++;
      this.fCurrentEntity.columnNumber = 1;
      if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
        this.fCurrentEntity.ch[0] = (char)c;
        load(1, false);
      } 
      if (c == '\r' && bool) {
        char c1 = this.fCurrentEntity.ch[this.fCurrentEntity.position++];
        if (c1 != '\n' && c1 != '')
          this.fCurrentEntity.position--; 
      } 
      c = '\n';
    } 
    this.fCurrentEntity.columnNumber++;
    return c;
  }
  
  public String scanNmtoken() throws IOException {
    if (this.fCurrentEntity.position == this.fCurrentEntity.count)
      load(0, true); 
    int i = this.fCurrentEntity.position;
    while (true) {
      char c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
      if (XML11Char.isXML11Name(c)) {
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
        continue;
      } 
      if (XML11Char.isXML11NameHighSurrogate(c)) {
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
          if (load(k, false)) {
            this.fCurrentEntity.startPosition--;
            this.fCurrentEntity.position--;
            break;
          } 
        } 
        char c1 = this.fCurrentEntity.ch[this.fCurrentEntity.position];
        if (!XMLChar.isLowSurrogate(c1) || !XML11Char.isXML11Name(XMLChar.supplemental(c, c1))) {
          this.fCurrentEntity.position--;
          break;
        } 
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
        continue;
      } 
      break;
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
    char c = this.fCurrentEntity.ch[i];
    if (XML11Char.isXML11NameStart(c)) {
      if (++this.fCurrentEntity.position == this.fCurrentEntity.count) {
        this.fCurrentEntity.ch[0] = c;
        i = 0;
        if (load(1, false)) {
          this.fCurrentEntity.columnNumber++;
          return this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, 0, 1);
        } 
      } 
    } else if (XML11Char.isXML11NameHighSurrogate(c)) {
      if (++this.fCurrentEntity.position == this.fCurrentEntity.count) {
        this.fCurrentEntity.ch[0] = c;
        i = 0;
        if (load(1, false)) {
          this.fCurrentEntity.position--;
          this.fCurrentEntity.startPosition--;
          return null;
        } 
      } 
      char c1 = this.fCurrentEntity.ch[this.fCurrentEntity.position];
      if (!XMLChar.isLowSurrogate(c1) || !XML11Char.isXML11NameStart(XMLChar.supplemental(c, c1))) {
        this.fCurrentEntity.position--;
        return null;
      } 
      if (++this.fCurrentEntity.position == this.fCurrentEntity.count) {
        this.fCurrentEntity.ch[0] = c;
        this.fCurrentEntity.ch[1] = c1;
        i = 0;
        if (load(2, false)) {
          this.fCurrentEntity.columnNumber += 2;
          return this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, 0, 2);
        } 
      } 
    } else {
      return null;
    } 
    while (true) {
      c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
      if (XML11Char.isXML11Name(c)) {
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
        continue;
      } 
      if (XML11Char.isXML11NameHighSurrogate(c)) {
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
          if (load(k, false)) {
            this.fCurrentEntity.position--;
            this.fCurrentEntity.startPosition--;
            break;
          } 
        } 
        char c1 = this.fCurrentEntity.ch[this.fCurrentEntity.position];
        if (!XMLChar.isLowSurrogate(c1) || !XML11Char.isXML11Name(XMLChar.supplemental(c, c1))) {
          this.fCurrentEntity.position--;
          break;
        } 
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
        continue;
      } 
      break;
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
    char c = this.fCurrentEntity.ch[i];
    if (XML11Char.isXML11NCNameStart(c)) {
      if (++this.fCurrentEntity.position == this.fCurrentEntity.count) {
        this.fCurrentEntity.ch[0] = c;
        i = 0;
        if (load(1, false)) {
          this.fCurrentEntity.columnNumber++;
          return this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, 0, 1);
        } 
      } 
    } else if (XML11Char.isXML11NameHighSurrogate(c)) {
      if (++this.fCurrentEntity.position == this.fCurrentEntity.count) {
        this.fCurrentEntity.ch[0] = c;
        i = 0;
        if (load(1, false)) {
          this.fCurrentEntity.position--;
          this.fCurrentEntity.startPosition--;
          return null;
        } 
      } 
      char c1 = this.fCurrentEntity.ch[this.fCurrentEntity.position];
      if (!XMLChar.isLowSurrogate(c1) || !XML11Char.isXML11NCNameStart(XMLChar.supplemental(c, c1))) {
        this.fCurrentEntity.position--;
        return null;
      } 
      if (++this.fCurrentEntity.position == this.fCurrentEntity.count) {
        this.fCurrentEntity.ch[0] = c;
        this.fCurrentEntity.ch[1] = c1;
        i = 0;
        if (load(2, false)) {
          this.fCurrentEntity.columnNumber += 2;
          return this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, 0, 2);
        } 
      } 
    } else {
      return null;
    } 
    while (true) {
      c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
      if (XML11Char.isXML11NCName(c)) {
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
        continue;
      } 
      if (XML11Char.isXML11NameHighSurrogate(c)) {
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
          if (load(k, false)) {
            this.fCurrentEntity.startPosition--;
            this.fCurrentEntity.position--;
            break;
          } 
        } 
        char c1 = this.fCurrentEntity.ch[this.fCurrentEntity.position];
        if (!XMLChar.isLowSurrogate(c1) || !XML11Char.isXML11NCName(XMLChar.supplemental(c, c1))) {
          this.fCurrentEntity.position--;
          break;
        } 
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
        continue;
      } 
      break;
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
    char c = this.fCurrentEntity.ch[i];
    if (XML11Char.isXML11NCNameStart(c)) {
      if (++this.fCurrentEntity.position == this.fCurrentEntity.count) {
        this.fCurrentEntity.ch[0] = c;
        i = 0;
        if (load(1, false)) {
          this.fCurrentEntity.columnNumber++;
          String str = this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, 0, 1);
          paramQName.setValues(null, str, str, null);
          return true;
        } 
      } 
    } else if (XML11Char.isXML11NameHighSurrogate(c)) {
      if (++this.fCurrentEntity.position == this.fCurrentEntity.count) {
        this.fCurrentEntity.ch[0] = c;
        i = 0;
        if (load(1, false)) {
          this.fCurrentEntity.startPosition--;
          this.fCurrentEntity.position--;
          return false;
        } 
      } 
      char c1 = this.fCurrentEntity.ch[this.fCurrentEntity.position];
      if (!XMLChar.isLowSurrogate(c1) || !XML11Char.isXML11NCNameStart(XMLChar.supplemental(c, c1))) {
        this.fCurrentEntity.position--;
        return false;
      } 
      if (++this.fCurrentEntity.position == this.fCurrentEntity.count) {
        this.fCurrentEntity.ch[0] = c;
        this.fCurrentEntity.ch[1] = c1;
        i = 0;
        if (load(2, false)) {
          this.fCurrentEntity.columnNumber += 2;
          String str = this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, 0, 2);
          paramQName.setValues(null, str, str, null);
          return true;
        } 
      } 
    } else {
      return false;
    } 
    int j = -1;
    boolean bool = false;
    while (true) {
      c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
      if (XML11Char.isXML11Name(c)) {
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
        continue;
      } 
      if (XML11Char.isXML11NameHighSurrogate(c)) {
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
          if (load(m, false)) {
            bool = true;
            this.fCurrentEntity.startPosition--;
            this.fCurrentEntity.position--;
            break;
          } 
        } 
        char c1 = this.fCurrentEntity.ch[this.fCurrentEntity.position];
        if (!XMLChar.isLowSurrogate(c1) || !XML11Char.isXML11Name(XMLChar.supplemental(c, c1))) {
          bool = true;
          this.fCurrentEntity.position--;
          break;
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
        continue;
      } 
      break;
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
        if (!XML11Char.isXML11NCNameStart(this.fCurrentEntity.ch[i1]) && (!XML11Char.isXML11NameHighSurrogate(this.fCurrentEntity.ch[i1]) || bool))
          this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "IllegalQName", null, (short)2); 
        str2 = this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, j + 1, n);
      } else {
        str2 = str3;
      } 
      paramQName.setValues(str1, str2, str3, null);
      return true;
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
    if (b == 10 || ((b == 13 || b == 133 || b == 8232) && bool)) {
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
          char c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
          if (c == '\n' || c == '') {
            this.fCurrentEntity.position++;
            i++;
          } else {
            b1++;
          } 
        } else if (b == 10 || ((b == 133 || b == 8232) && bool)) {
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
    if (bool) {
      while (this.fCurrentEntity.position < this.fCurrentEntity.count) {
        b = this.fCurrentEntity.ch[this.fCurrentEntity.position++];
        if (!XML11Char.isXML11Content(b) || b == 133 || b == 8232) {
          this.fCurrentEntity.position--;
          break;
        } 
      } 
    } else {
      while (this.fCurrentEntity.position < this.fCurrentEntity.count) {
        b = this.fCurrentEntity.ch[this.fCurrentEntity.position++];
        if (!XML11Char.isXML11InternalEntityContent(b)) {
          this.fCurrentEntity.position--;
          break;
        } 
      } 
    } 
    int j = this.fCurrentEntity.position - i;
    this.fCurrentEntity.columnNumber += j - b1;
    paramXMLString.setValues(this.fCurrentEntity.ch, i, j);
    if (this.fCurrentEntity.position != this.fCurrentEntity.count) {
      b = this.fCurrentEntity.ch[this.fCurrentEntity.position];
      if ((b == 13 || b == 133 || b == 8232) && bool)
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
      this.fCurrentEntity.startPosition = 0;
      this.fCurrentEntity.position = 0;
    } 
    int i = this.fCurrentEntity.position;
    byte b = this.fCurrentEntity.ch[i];
    byte b1 = 0;
    boolean bool = this.fCurrentEntity.isExternal();
    if (b == 10 || ((b == 13 || b == 133 || b == 8232) && bool)) {
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
          char c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
          if (c == '\n' || c == '') {
            this.fCurrentEntity.position++;
            i++;
          } else {
            b1++;
          } 
        } else if (b == 10 || ((b == 133 || b == 8232) && bool)) {
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
    if (bool) {
      while (this.fCurrentEntity.position < this.fCurrentEntity.count) {
        b = this.fCurrentEntity.ch[this.fCurrentEntity.position++];
        if (b == paramInt || b == 37 || !XML11Char.isXML11Content(b) || b == 133 || b == 8232) {
          this.fCurrentEntity.position--;
          break;
        } 
      } 
    } else {
      while (this.fCurrentEntity.position < this.fCurrentEntity.count) {
        b = this.fCurrentEntity.ch[this.fCurrentEntity.position++];
        if ((b == paramInt && !this.fCurrentEntity.literal) || b == 37 || !XML11Char.isXML11InternalEntityContent(b)) {
          this.fCurrentEntity.position--;
          break;
        } 
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
    char c = paramString.charAt(0);
    boolean bool1 = this.fCurrentEntity.isExternal();
    while (true) {
      if (this.fCurrentEntity.position == this.fCurrentEntity.count)
        load(0, true); 
      boolean bool2 = false;
      while (this.fCurrentEntity.position >= this.fCurrentEntity.count - i && !bool2) {
        System.arraycopy(this.fCurrentEntity.ch, this.fCurrentEntity.position, this.fCurrentEntity.ch, 0, this.fCurrentEntity.count - this.fCurrentEntity.position);
        bool2 = load(this.fCurrentEntity.count - this.fCurrentEntity.position, false);
        this.fCurrentEntity.position = 0;
        this.fCurrentEntity.startPosition = 0;
      } 
      if (this.fCurrentEntity.position >= this.fCurrentEntity.count - i) {
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
      char c1 = this.fCurrentEntity.ch[j];
      byte b = 0;
      if (c1 == '\n' || ((c1 == '\r' || c1 == '' || c1 == ' ') && bool1)) {
        do {
          c1 = this.fCurrentEntity.ch[this.fCurrentEntity.position++];
          if (c1 == '\r' && bool1) {
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
            char c2 = this.fCurrentEntity.ch[this.fCurrentEntity.position];
            if (c2 == '\n' || c2 == '') {
              this.fCurrentEntity.position++;
              j++;
            } else {
              b++;
            } 
          } else if (c1 == '\n' || ((c1 == '' || c1 == ' ') && bool1)) {
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
      if (bool1) {
        while (this.fCurrentEntity.position < this.fCurrentEntity.count) {
          c1 = this.fCurrentEntity.ch[this.fCurrentEntity.position++];
          if (c1 == c) {
            int m = this.fCurrentEntity.position - 1;
            for (byte b1 = 1; b1 < i; b1++) {
              if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                this.fCurrentEntity.position -= b1;
                break label117;
              } 
              c1 = this.fCurrentEntity.ch[this.fCurrentEntity.position++];
              if (paramString.charAt(b1) != c1) {
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
          if (c1 == '\n' || c1 == '\r' || c1 == '' || c1 == ' ') {
            this.fCurrentEntity.position--;
            break;
          } 
          if (!XML11Char.isXML11ValidLiteral(c1)) {
            this.fCurrentEntity.position--;
            int m = this.fCurrentEntity.position - j;
            this.fCurrentEntity.columnNumber += m - b;
            paramXMLStringBuffer.append(this.fCurrentEntity.ch, j, m);
            return true;
          } 
        } 
      } else {
        label117: while (this.fCurrentEntity.position < this.fCurrentEntity.count) {
          c1 = this.fCurrentEntity.ch[this.fCurrentEntity.position++];
          if (c1 == c) {
            int m = this.fCurrentEntity.position - 1;
            for (byte b1 = 1; b1 < i; b1++) {
              if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                this.fCurrentEntity.position -= b1;
                break label117;
              } 
              c1 = this.fCurrentEntity.ch[this.fCurrentEntity.position++];
              if (paramString.charAt(b1) != c1) {
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
          if (c1 == '\n') {
            this.fCurrentEntity.position--;
            break;
          } 
          if (!XML11Char.isXML11Valid(c1)) {
            this.fCurrentEntity.position--;
            int m = this.fCurrentEntity.position - j;
            this.fCurrentEntity.columnNumber += m - b;
            paramXMLStringBuffer.append(this.fCurrentEntity.ch, j, m);
            return true;
          } 
        } 
      } 
      int k = this.fCurrentEntity.position - j;
      this.fCurrentEntity.columnNumber += k - b;
      if (bool)
        k -= i; 
      paramXMLStringBuffer.append(this.fCurrentEntity.ch, j, k);
      if (bool)
        return !bool; 
    } 
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
    if (paramInt == 10 && (c == ' ' || c == '') && this.fCurrentEntity.isExternal()) {
      this.fCurrentEntity.position++;
      this.fCurrentEntity.lineNumber++;
      this.fCurrentEntity.columnNumber = 1;
      return true;
    } 
    if (paramInt == 10 && c == '\r' && this.fCurrentEntity.isExternal()) {
      if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
        this.fCurrentEntity.ch[0] = (char)c;
        load(1, false);
      } 
      char c1 = this.fCurrentEntity.ch[++this.fCurrentEntity.position];
      if (c1 == '\n' || c1 == '')
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
    if (this.fCurrentEntity.isExternal()) {
      if (XML11Char.isXML11Space(c))
        while (true) {
          boolean bool = false;
          if (c == '\n' || c == '\r' || c == '' || c == ' ') {
            this.fCurrentEntity.lineNumber++;
            this.fCurrentEntity.columnNumber = 1;
            if (this.fCurrentEntity.position == this.fCurrentEntity.count - 1) {
              this.fCurrentEntity.ch[0] = (char)c;
              bool = load(1, true);
              if (!bool) {
                this.fCurrentEntity.startPosition = 0;
                this.fCurrentEntity.position = 0;
              } 
            } 
            if (c == '\r') {
              char c1 = this.fCurrentEntity.ch[++this.fCurrentEntity.position];
              if (c1 != '\n' && c1 != '')
                this.fCurrentEntity.position--; 
            } 
          } else {
            this.fCurrentEntity.columnNumber++;
          } 
          if (!bool)
            this.fCurrentEntity.position++; 
          if (this.fCurrentEntity.position == this.fCurrentEntity.count)
            load(0, true); 
          if (!XML11Char.isXML11Space(c = this.fCurrentEntity.ch[this.fCurrentEntity.position]))
            return true; 
        }  
    } else if (XMLChar.isSpace(c)) {
      while (true) {
        boolean bool = false;
        if (c == '\n') {
          this.fCurrentEntity.lineNumber++;
          this.fCurrentEntity.columnNumber = 1;
          if (this.fCurrentEntity.position == this.fCurrentEntity.count - 1) {
            this.fCurrentEntity.ch[0] = (char)c;
            bool = load(1, true);
            if (!bool) {
              this.fCurrentEntity.startPosition = 0;
              this.fCurrentEntity.position = 0;
            } 
          } 
        } else {
          this.fCurrentEntity.columnNumber++;
        } 
        if (!bool)
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
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\XML11EntityScanner.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */