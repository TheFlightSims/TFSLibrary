/*      */ package org.apache.commons.lang.text;
/*      */ 
/*      */ import java.io.Reader;
/*      */ import java.io.Writer;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import org.apache.commons.lang.ArrayUtils;
/*      */ import org.apache.commons.lang.SystemUtils;
/*      */ 
/*      */ public class StrBuilder implements Cloneable {
/*      */   static final int CAPACITY = 32;
/*      */   
/*      */   private static final long serialVersionUID = 7628716375283629643L;
/*      */   
/*      */   protected char[] buffer;
/*      */   
/*      */   protected int size;
/*      */   
/*      */   private String newLine;
/*      */   
/*      */   private String nullText;
/*      */   
/*      */   public StrBuilder() {
/*  102 */     this(32);
/*      */   }
/*      */   
/*      */   public StrBuilder(int initialCapacity) {
/*  112 */     if (initialCapacity <= 0)
/*  113 */       initialCapacity = 32; 
/*  115 */     this.buffer = new char[initialCapacity];
/*      */   }
/*      */   
/*      */   public StrBuilder(String str) {
/*  126 */     if (str == null) {
/*  127 */       this.buffer = new char[32];
/*      */     } else {
/*  129 */       this.buffer = new char[str.length() + 32];
/*  130 */       append(str);
/*      */     } 
/*      */   }
/*      */   
/*      */   public String getNewLineText() {
/*  141 */     return this.newLine;
/*      */   }
/*      */   
/*      */   public StrBuilder setNewLineText(String newLine) {
/*  151 */     this.newLine = newLine;
/*  152 */     return this;
/*      */   }
/*      */   
/*      */   public String getNullText() {
/*  162 */     return this.nullText;
/*      */   }
/*      */   
/*      */   public StrBuilder setNullText(String nullText) {
/*  172 */     if (nullText != null && nullText.length() == 0)
/*  173 */       nullText = null; 
/*  175 */     this.nullText = nullText;
/*  176 */     return this;
/*      */   }
/*      */   
/*      */   public int length() {
/*  186 */     return this.size;
/*      */   }
/*      */   
/*      */   public StrBuilder setLength(int length) {
/*  198 */     if (length < 0)
/*  199 */       throw new StringIndexOutOfBoundsException(length); 
/*  201 */     if (length < this.size) {
/*  202 */       this.size = length;
/*  203 */     } else if (length > this.size) {
/*  204 */       ensureCapacity(length);
/*  205 */       int oldEnd = this.size;
/*  206 */       int newEnd = length;
/*  207 */       this.size = length;
/*  208 */       for (int i = oldEnd; i < newEnd; i++)
/*  209 */         this.buffer[i] = Character.MIN_VALUE; 
/*      */     } 
/*  212 */     return this;
/*      */   }
/*      */   
/*      */   public int capacity() {
/*  222 */     return this.buffer.length;
/*      */   }
/*      */   
/*      */   public StrBuilder ensureCapacity(int capacity) {
/*  232 */     if (capacity > this.buffer.length) {
/*  233 */       char[] old = this.buffer;
/*  234 */       this.buffer = new char[capacity * 2];
/*  235 */       System.arraycopy(old, 0, this.buffer, 0, this.size);
/*      */     } 
/*  237 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder minimizeCapacity() {
/*  246 */     if (this.buffer.length > length()) {
/*  247 */       char[] old = this.buffer;
/*  248 */       this.buffer = new char[length()];
/*  249 */       System.arraycopy(old, 0, this.buffer, 0, this.size);
/*      */     } 
/*  251 */     return this;
/*      */   }
/*      */   
/*      */   public int size() {
/*  264 */     return this.size;
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/*  276 */     return (this.size == 0);
/*      */   }
/*      */   
/*      */   public StrBuilder clear() {
/*  291 */     this.size = 0;
/*  292 */     return this;
/*      */   }
/*      */   
/*      */   public char charAt(int index) {
/*  306 */     if (index < 0 || index >= length())
/*  307 */       throw new StringIndexOutOfBoundsException(index); 
/*  309 */     return this.buffer[index];
/*      */   }
/*      */   
/*      */   public StrBuilder setCharAt(int index, char ch) {
/*  323 */     if (index < 0 || index >= length())
/*  324 */       throw new StringIndexOutOfBoundsException(index); 
/*  326 */     this.buffer[index] = ch;
/*  327 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder deleteCharAt(int index) {
/*  340 */     if (index < 0 || index >= this.size)
/*  341 */       throw new StringIndexOutOfBoundsException(index); 
/*  343 */     deleteImpl(index, index + 1, 1);
/*  344 */     return this;
/*      */   }
/*      */   
/*      */   public char[] toCharArray() {
/*  354 */     if (this.size == 0)
/*  355 */       return ArrayUtils.EMPTY_CHAR_ARRAY; 
/*  357 */     char[] chars = new char[this.size];
/*  358 */     System.arraycopy(this.buffer, 0, chars, 0, this.size);
/*  359 */     return chars;
/*      */   }
/*      */   
/*      */   public char[] toCharArray(int startIndex, int endIndex) {
/*  373 */     endIndex = validateRange(startIndex, endIndex);
/*  374 */     int len = endIndex - startIndex;
/*  375 */     if (len == 0)
/*  376 */       return ArrayUtils.EMPTY_CHAR_ARRAY; 
/*  378 */     char[] chars = new char[len];
/*  379 */     System.arraycopy(this.buffer, startIndex, chars, 0, len);
/*  380 */     return chars;
/*      */   }
/*      */   
/*      */   public char[] getChars(char[] destination) {
/*  390 */     int len = length();
/*  391 */     if (destination == null || destination.length < len)
/*  392 */       destination = new char[len]; 
/*  394 */     System.arraycopy(this.buffer, 0, destination, 0, len);
/*  395 */     return destination;
/*      */   }
/*      */   
/*      */   public void getChars(int startIndex, int endIndex, char[] destination, int destinationIndex) {
/*  409 */     if (startIndex < 0)
/*  410 */       throw new StringIndexOutOfBoundsException(startIndex); 
/*  412 */     if (endIndex < 0 || endIndex > length())
/*  413 */       throw new StringIndexOutOfBoundsException(endIndex); 
/*  415 */     if (startIndex > endIndex)
/*  416 */       throw new StringIndexOutOfBoundsException("end < start"); 
/*  418 */     System.arraycopy(this.buffer, startIndex, destination, destinationIndex, endIndex - startIndex);
/*      */   }
/*      */   
/*      */   public StrBuilder appendNewLine() {
/*  432 */     if (this.newLine == null) {
/*  433 */       append(SystemUtils.LINE_SEPARATOR);
/*  434 */       return this;
/*      */     } 
/*  436 */     return append(this.newLine);
/*      */   }
/*      */   
/*      */   public StrBuilder appendNull() {
/*  445 */     if (this.nullText == null)
/*  446 */       return this; 
/*  448 */     return append(this.nullText);
/*      */   }
/*      */   
/*      */   public StrBuilder append(Object obj) {
/*  459 */     if (obj == null)
/*  460 */       return appendNull(); 
/*  462 */     return append(obj.toString());
/*      */   }
/*      */   
/*      */   public StrBuilder append(String str) {
/*  473 */     if (str == null)
/*  474 */       return appendNull(); 
/*  476 */     int strLen = str.length();
/*  477 */     if (strLen > 0) {
/*  478 */       int len = length();
/*  479 */       ensureCapacity(len + strLen);
/*  480 */       str.getChars(0, strLen, this.buffer, len);
/*  481 */       this.size += strLen;
/*      */     } 
/*  483 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder append(String str, int startIndex, int length) {
/*  496 */     if (str == null)
/*  497 */       return appendNull(); 
/*  499 */     if (startIndex < 0 || startIndex > str.length())
/*  500 */       throw new StringIndexOutOfBoundsException("startIndex must be valid"); 
/*  502 */     if (length < 0 || startIndex + length > str.length())
/*  503 */       throw new StringIndexOutOfBoundsException("length must be valid"); 
/*  505 */     if (length > 0) {
/*  506 */       int len = length();
/*  507 */       ensureCapacity(len + length);
/*  508 */       str.getChars(startIndex, startIndex + length, this.buffer, len);
/*  509 */       this.size += length;
/*      */     } 
/*  511 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder append(StringBuffer str) {
/*  522 */     if (str == null)
/*  523 */       return appendNull(); 
/*  525 */     int strLen = str.length();
/*  526 */     if (strLen > 0) {
/*  527 */       int len = length();
/*  528 */       ensureCapacity(len + strLen);
/*  529 */       str.getChars(0, strLen, this.buffer, len);
/*  530 */       this.size += strLen;
/*      */     } 
/*  532 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder append(StringBuffer str, int startIndex, int length) {
/*  545 */     if (str == null)
/*  546 */       return appendNull(); 
/*  548 */     if (startIndex < 0 || startIndex > str.length())
/*  549 */       throw new StringIndexOutOfBoundsException("startIndex must be valid"); 
/*  551 */     if (length < 0 || startIndex + length > str.length())
/*  552 */       throw new StringIndexOutOfBoundsException("length must be valid"); 
/*  554 */     if (length > 0) {
/*  555 */       int len = length();
/*  556 */       ensureCapacity(len + length);
/*  557 */       str.getChars(startIndex, startIndex + length, this.buffer, len);
/*  558 */       this.size += length;
/*      */     } 
/*  560 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder append(StrBuilder str) {
/*  571 */     if (str == null)
/*  572 */       return appendNull(); 
/*  574 */     int strLen = str.length();
/*  575 */     if (strLen > 0) {
/*  576 */       int len = length();
/*  577 */       ensureCapacity(len + strLen);
/*  578 */       System.arraycopy(str.buffer, 0, this.buffer, len, strLen);
/*  579 */       this.size += strLen;
/*      */     } 
/*  581 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder append(StrBuilder str, int startIndex, int length) {
/*  594 */     if (str == null)
/*  595 */       return appendNull(); 
/*  597 */     if (startIndex < 0 || startIndex > str.length())
/*  598 */       throw new StringIndexOutOfBoundsException("startIndex must be valid"); 
/*  600 */     if (length < 0 || startIndex + length > str.length())
/*  601 */       throw new StringIndexOutOfBoundsException("length must be valid"); 
/*  603 */     if (length > 0) {
/*  604 */       int len = length();
/*  605 */       ensureCapacity(len + length);
/*  606 */       str.getChars(startIndex, startIndex + length, this.buffer, len);
/*  607 */       this.size += length;
/*      */     } 
/*  609 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder append(char[] chars) {
/*  620 */     if (chars == null)
/*  621 */       return appendNull(); 
/*  623 */     int strLen = chars.length;
/*  624 */     if (strLen > 0) {
/*  625 */       int len = length();
/*  626 */       ensureCapacity(len + strLen);
/*  627 */       System.arraycopy(chars, 0, this.buffer, len, strLen);
/*  628 */       this.size += strLen;
/*      */     } 
/*  630 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder append(char[] chars, int startIndex, int length) {
/*  643 */     if (chars == null)
/*  644 */       return appendNull(); 
/*  646 */     if (startIndex < 0 || startIndex > chars.length)
/*  647 */       throw new StringIndexOutOfBoundsException("Invalid startIndex: " + length); 
/*  649 */     if (length < 0 || startIndex + length > chars.length)
/*  650 */       throw new StringIndexOutOfBoundsException("Invalid length: " + length); 
/*  652 */     if (length > 0) {
/*  653 */       int len = length();
/*  654 */       ensureCapacity(len + length);
/*  655 */       System.arraycopy(chars, startIndex, this.buffer, len, length);
/*  656 */       this.size += length;
/*      */     } 
/*  658 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder append(boolean value) {
/*  668 */     if (value) {
/*  669 */       ensureCapacity(this.size + 4);
/*  670 */       this.buffer[this.size++] = 't';
/*  671 */       this.buffer[this.size++] = 'r';
/*  672 */       this.buffer[this.size++] = 'u';
/*  673 */       this.buffer[this.size++] = 'e';
/*      */     } else {
/*  675 */       ensureCapacity(this.size + 5);
/*  676 */       this.buffer[this.size++] = 'f';
/*  677 */       this.buffer[this.size++] = 'a';
/*  678 */       this.buffer[this.size++] = 'l';
/*  679 */       this.buffer[this.size++] = 's';
/*  680 */       this.buffer[this.size++] = 'e';
/*      */     } 
/*  682 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder append(char ch) {
/*  692 */     int len = length();
/*  693 */     ensureCapacity(len + 1);
/*  694 */     this.buffer[this.size++] = ch;
/*  695 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder append(int value) {
/*  705 */     return append(String.valueOf(value));
/*      */   }
/*      */   
/*      */   public StrBuilder append(long value) {
/*  715 */     return append(String.valueOf(value));
/*      */   }
/*      */   
/*      */   public StrBuilder append(float value) {
/*  725 */     return append(String.valueOf(value));
/*      */   }
/*      */   
/*      */   public StrBuilder append(double value) {
/*  735 */     return append(String.valueOf(value));
/*      */   }
/*      */   
/*      */   public StrBuilder appendln(Object obj) {
/*  748 */     return append(obj).appendNewLine();
/*      */   }
/*      */   
/*      */   public StrBuilder appendln(String str) {
/*  760 */     return append(str).appendNewLine();
/*      */   }
/*      */   
/*      */   public StrBuilder appendln(String str, int startIndex, int length) {
/*  774 */     return append(str, startIndex, length).appendNewLine();
/*      */   }
/*      */   
/*      */   public StrBuilder appendln(StringBuffer str) {
/*  786 */     return append(str).appendNewLine();
/*      */   }
/*      */   
/*      */   public StrBuilder appendln(StringBuffer str, int startIndex, int length) {
/*  800 */     return append(str, startIndex, length).appendNewLine();
/*      */   }
/*      */   
/*      */   public StrBuilder appendln(StrBuilder str) {
/*  812 */     return append(str).appendNewLine();
/*      */   }
/*      */   
/*      */   public StrBuilder appendln(StrBuilder str, int startIndex, int length) {
/*  826 */     return append(str, startIndex, length).appendNewLine();
/*      */   }
/*      */   
/*      */   public StrBuilder appendln(char[] chars) {
/*  838 */     return append(chars).appendNewLine();
/*      */   }
/*      */   
/*      */   public StrBuilder appendln(char[] chars, int startIndex, int length) {
/*  852 */     return append(chars, startIndex, length).appendNewLine();
/*      */   }
/*      */   
/*      */   public StrBuilder appendln(boolean value) {
/*  863 */     return append(value).appendNewLine();
/*      */   }
/*      */   
/*      */   public StrBuilder appendln(char ch) {
/*  874 */     return append(ch).appendNewLine();
/*      */   }
/*      */   
/*      */   public StrBuilder appendln(int value) {
/*  885 */     return append(value).appendNewLine();
/*      */   }
/*      */   
/*      */   public StrBuilder appendln(long value) {
/*  896 */     return append(value).appendNewLine();
/*      */   }
/*      */   
/*      */   public StrBuilder appendln(float value) {
/*  907 */     return append(value).appendNewLine();
/*      */   }
/*      */   
/*      */   public StrBuilder appendln(double value) {
/*  918 */     return append(value).appendNewLine();
/*      */   }
/*      */   
/*      */   public StrBuilder appendAll(Object[] array) {
/*  932 */     if (array != null && array.length > 0)
/*  933 */       for (int i = 0; i < array.length; i++)
/*  934 */         append(array[i]);  
/*  937 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder appendAll(Collection coll) {
/*  950 */     if (coll != null && coll.size() > 0) {
/*  951 */       Iterator it = coll.iterator();
/*  952 */       while (it.hasNext())
/*  953 */         append(it.next()); 
/*      */     } 
/*  956 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder appendAll(Iterator it) {
/*  969 */     if (it != null)
/*  970 */       while (it.hasNext())
/*  971 */         append(it.next());  
/*  974 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder appendWithSeparators(Object[] array, String separator) {
/*  989 */     if (array != null && array.length > 0) {
/*  990 */       separator = (separator == null) ? "" : separator;
/*  991 */       append(array[0]);
/*  992 */       for (int i = 1; i < array.length; i++) {
/*  993 */         append(separator);
/*  994 */         append(array[i]);
/*      */       } 
/*      */     } 
/*  997 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder appendWithSeparators(Collection coll, String separator) {
/* 1011 */     if (coll != null && coll.size() > 0) {
/* 1012 */       separator = (separator == null) ? "" : separator;
/* 1013 */       Iterator it = coll.iterator();
/* 1014 */       while (it.hasNext()) {
/* 1015 */         append(it.next());
/* 1016 */         if (it.hasNext())
/* 1017 */           append(separator); 
/*      */       } 
/*      */     } 
/* 1021 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder appendWithSeparators(Iterator it, String separator) {
/* 1035 */     if (it != null) {
/* 1036 */       separator = (separator == null) ? "" : separator;
/* 1037 */       while (it.hasNext()) {
/* 1038 */         append(it.next());
/* 1039 */         if (it.hasNext())
/* 1040 */           append(separator); 
/*      */       } 
/*      */     } 
/* 1044 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder appendSeparator(String separator) {
/* 1069 */     return appendSeparator(separator, (String)null);
/*      */   }
/*      */   
/*      */   public StrBuilder appendSeparator(String standard, String defaultIfEmpty) {
/* 1100 */     String str = isEmpty() ? defaultIfEmpty : standard;
/* 1101 */     if (str != null)
/* 1102 */       append(str); 
/* 1104 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder appendSeparator(char separator) {
/* 1127 */     if (size() > 0)
/* 1128 */       append(separator); 
/* 1130 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder appendSeparator(char standard, char defaultIfEmpty) {
/* 1145 */     if (size() > 0) {
/* 1146 */       append(standard);
/*      */     } else {
/* 1149 */       append(defaultIfEmpty);
/*      */     } 
/* 1151 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder appendSeparator(String separator, int loopIndex) {
/* 1175 */     if (separator != null && loopIndex > 0)
/* 1176 */       append(separator); 
/* 1178 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder appendSeparator(char separator, int loopIndex) {
/* 1202 */     if (loopIndex > 0)
/* 1203 */       append(separator); 
/* 1205 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder appendPadding(int length, char padChar) {
/* 1217 */     if (length >= 0) {
/* 1218 */       ensureCapacity(this.size + length);
/* 1219 */       for (int i = 0; i < length; i++)
/* 1220 */         this.buffer[this.size++] = padChar; 
/*      */     } 
/* 1223 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder appendFixedWidthPadLeft(Object obj, int width, char padChar) {
/* 1239 */     if (width > 0) {
/* 1240 */       ensureCapacity(this.size + width);
/* 1241 */       String str = (obj == null) ? getNullText() : obj.toString();
/* 1242 */       if (str == null)
/* 1243 */         str = ""; 
/* 1245 */       int strLen = str.length();
/* 1246 */       if (strLen >= width) {
/* 1247 */         str.getChars(strLen - width, strLen, this.buffer, this.size);
/*      */       } else {
/* 1249 */         int padLen = width - strLen;
/* 1250 */         for (int i = 0; i < padLen; i++)
/* 1251 */           this.buffer[this.size + i] = padChar; 
/* 1253 */         str.getChars(0, strLen, this.buffer, this.size + padLen);
/*      */       } 
/* 1255 */       this.size += width;
/*      */     } 
/* 1257 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder appendFixedWidthPadLeft(int value, int width, char padChar) {
/* 1271 */     return appendFixedWidthPadLeft(String.valueOf(value), width, padChar);
/*      */   }
/*      */   
/*      */   public StrBuilder appendFixedWidthPadRight(Object obj, int width, char padChar) {
/* 1286 */     if (width > 0) {
/* 1287 */       ensureCapacity(this.size + width);
/* 1288 */       String str = (obj == null) ? getNullText() : obj.toString();
/* 1289 */       if (str == null)
/* 1290 */         str = ""; 
/* 1292 */       int strLen = str.length();
/* 1293 */       if (strLen >= width) {
/* 1294 */         str.getChars(0, width, this.buffer, this.size);
/*      */       } else {
/* 1296 */         int padLen = width - strLen;
/* 1297 */         str.getChars(0, strLen, this.buffer, this.size);
/* 1298 */         for (int i = 0; i < padLen; i++)
/* 1299 */           this.buffer[this.size + strLen + i] = padChar; 
/*      */       } 
/* 1302 */       this.size += width;
/*      */     } 
/* 1304 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder appendFixedWidthPadRight(int value, int width, char padChar) {
/* 1318 */     return appendFixedWidthPadRight(String.valueOf(value), width, padChar);
/*      */   }
/*      */   
/*      */   public StrBuilder insert(int index, Object obj) {
/* 1332 */     if (obj == null)
/* 1333 */       return insert(index, this.nullText); 
/* 1335 */     return insert(index, obj.toString());
/*      */   }
/*      */   
/*      */   public StrBuilder insert(int index, String str) {
/* 1348 */     validateIndex(index);
/* 1349 */     if (str == null)
/* 1350 */       str = this.nullText; 
/* 1352 */     int strLen = (str == null) ? 0 : str.length();
/* 1353 */     if (strLen > 0) {
/* 1354 */       int newSize = this.size + strLen;
/* 1355 */       ensureCapacity(newSize);
/* 1356 */       System.arraycopy(this.buffer, index, this.buffer, index + strLen, this.size - index);
/* 1357 */       this.size = newSize;
/* 1358 */       str.getChars(0, strLen, this.buffer, index);
/*      */     } 
/* 1360 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder insert(int index, char[] chars) {
/* 1373 */     validateIndex(index);
/* 1374 */     if (chars == null)
/* 1375 */       return insert(index, this.nullText); 
/* 1377 */     int len = chars.length;
/* 1378 */     if (len > 0) {
/* 1379 */       ensureCapacity(this.size + len);
/* 1380 */       System.arraycopy(this.buffer, index, this.buffer, index + len, this.size - index);
/* 1381 */       System.arraycopy(chars, 0, this.buffer, index, len);
/* 1382 */       this.size += len;
/*      */     } 
/* 1384 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder insert(int index, char[] chars, int offset, int length) {
/* 1399 */     validateIndex(index);
/* 1400 */     if (chars == null)
/* 1401 */       return insert(index, this.nullText); 
/* 1403 */     if (offset < 0 || offset > chars.length)
/* 1404 */       throw new StringIndexOutOfBoundsException("Invalid offset: " + offset); 
/* 1406 */     if (length < 0 || offset + length > chars.length)
/* 1407 */       throw new StringIndexOutOfBoundsException("Invalid length: " + length); 
/* 1409 */     if (length > 0) {
/* 1410 */       ensureCapacity(this.size + length);
/* 1411 */       System.arraycopy(this.buffer, index, this.buffer, index + length, this.size - index);
/* 1412 */       System.arraycopy(chars, offset, this.buffer, index, length);
/* 1413 */       this.size += length;
/*      */     } 
/* 1415 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder insert(int index, boolean value) {
/* 1427 */     validateIndex(index);
/* 1428 */     if (value) {
/* 1429 */       ensureCapacity(this.size + 4);
/* 1430 */       System.arraycopy(this.buffer, index, this.buffer, index + 4, this.size - index);
/* 1431 */       this.buffer[index++] = 't';
/* 1432 */       this.buffer[index++] = 'r';
/* 1433 */       this.buffer[index++] = 'u';
/* 1434 */       this.buffer[index] = 'e';
/* 1435 */       this.size += 4;
/*      */     } else {
/* 1437 */       ensureCapacity(this.size + 5);
/* 1438 */       System.arraycopy(this.buffer, index, this.buffer, index + 5, this.size - index);
/* 1439 */       this.buffer[index++] = 'f';
/* 1440 */       this.buffer[index++] = 'a';
/* 1441 */       this.buffer[index++] = 'l';
/* 1442 */       this.buffer[index++] = 's';
/* 1443 */       this.buffer[index] = 'e';
/* 1444 */       this.size += 5;
/*      */     } 
/* 1446 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder insert(int index, char value) {
/* 1458 */     validateIndex(index);
/* 1459 */     ensureCapacity(this.size + 1);
/* 1460 */     System.arraycopy(this.buffer, index, this.buffer, index + 1, this.size - index);
/* 1461 */     this.buffer[index] = value;
/* 1462 */     this.size++;
/* 1463 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder insert(int index, int value) {
/* 1475 */     return insert(index, String.valueOf(value));
/*      */   }
/*      */   
/*      */   public StrBuilder insert(int index, long value) {
/* 1487 */     return insert(index, String.valueOf(value));
/*      */   }
/*      */   
/*      */   public StrBuilder insert(int index, float value) {
/* 1499 */     return insert(index, String.valueOf(value));
/*      */   }
/*      */   
/*      */   public StrBuilder insert(int index, double value) {
/* 1511 */     return insert(index, String.valueOf(value));
/*      */   }
/*      */   
/*      */   private void deleteImpl(int startIndex, int endIndex, int len) {
/* 1524 */     System.arraycopy(this.buffer, endIndex, this.buffer, startIndex, this.size - endIndex);
/* 1525 */     this.size -= len;
/*      */   }
/*      */   
/*      */   public StrBuilder delete(int startIndex, int endIndex) {
/* 1538 */     endIndex = validateRange(startIndex, endIndex);
/* 1539 */     int len = endIndex - startIndex;
/* 1540 */     if (len > 0)
/* 1541 */       deleteImpl(startIndex, endIndex, len); 
/* 1543 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder deleteAll(char ch) {
/* 1554 */     for (int i = 0; i < this.size; i++) {
/* 1555 */       if (this.buffer[i] == ch) {
/* 1556 */         int start = i;
/*      */         do {
/*      */         
/* 1557 */         } while (++i < this.size && 
/* 1558 */           this.buffer[i] == ch);
/* 1562 */         int len = i - start;
/* 1563 */         deleteImpl(start, i, len);
/* 1564 */         i -= len;
/*      */       } 
/*      */     } 
/* 1567 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder deleteFirst(char ch) {
/* 1577 */     for (int i = 0; i < this.size; i++) {
/* 1578 */       if (this.buffer[i] == ch) {
/* 1579 */         deleteImpl(i, i + 1, 1);
/*      */         break;
/*      */       } 
/*      */     } 
/* 1583 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder deleteAll(String str) {
/* 1594 */     int len = (str == null) ? 0 : str.length();
/* 1595 */     if (len > 0) {
/* 1596 */       int index = indexOf(str, 0);
/* 1597 */       while (index >= 0) {
/* 1598 */         deleteImpl(index, index + len, len);
/* 1599 */         index = indexOf(str, index);
/*      */       } 
/*      */     } 
/* 1602 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder deleteFirst(String str) {
/* 1612 */     int len = (str == null) ? 0 : str.length();
/* 1613 */     if (len > 0) {
/* 1614 */       int index = indexOf(str, 0);
/* 1615 */       if (index >= 0)
/* 1616 */         deleteImpl(index, index + len, len); 
/*      */     } 
/* 1619 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder deleteAll(StrMatcher matcher) {
/* 1634 */     return replace(matcher, null, 0, this.size, -1);
/*      */   }
/*      */   
/*      */   public StrBuilder deleteFirst(StrMatcher matcher) {
/* 1648 */     return replace(matcher, null, 0, this.size, 1);
/*      */   }
/*      */   
/*      */   private void replaceImpl(int startIndex, int endIndex, int removeLen, String insertStr, int insertLen) {
/* 1663 */     int newSize = this.size - removeLen + insertLen;
/* 1664 */     if (insertLen != removeLen) {
/* 1665 */       ensureCapacity(newSize);
/* 1666 */       System.arraycopy(this.buffer, endIndex, this.buffer, startIndex + insertLen, this.size - endIndex);
/* 1667 */       this.size = newSize;
/*      */     } 
/* 1669 */     if (insertLen > 0)
/* 1670 */       insertStr.getChars(0, insertLen, this.buffer, startIndex); 
/*      */   }
/*      */   
/*      */   public StrBuilder replace(int startIndex, int endIndex, String replaceStr) {
/* 1686 */     endIndex = validateRange(startIndex, endIndex);
/* 1687 */     int insertLen = (replaceStr == null) ? 0 : replaceStr.length();
/* 1688 */     replaceImpl(startIndex, endIndex, endIndex - startIndex, replaceStr, insertLen);
/* 1689 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder replaceAll(char search, char replace) {
/* 1702 */     if (search != replace)
/* 1703 */       for (int i = 0; i < this.size; i++) {
/* 1704 */         if (this.buffer[i] == search)
/* 1705 */           this.buffer[i] = replace; 
/*      */       }  
/* 1709 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder replaceFirst(char search, char replace) {
/* 1721 */     if (search != replace)
/* 1722 */       for (int i = 0; i < this.size; i++) {
/* 1723 */         if (this.buffer[i] == search) {
/* 1724 */           this.buffer[i] = replace;
/*      */           break;
/*      */         } 
/*      */       }  
/* 1729 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder replaceAll(String searchStr, String replaceStr) {
/* 1741 */     int searchLen = (searchStr == null) ? 0 : searchStr.length();
/* 1742 */     if (searchLen > 0) {
/* 1743 */       int replaceLen = (replaceStr == null) ? 0 : replaceStr.length();
/* 1744 */       int index = indexOf(searchStr, 0);
/* 1745 */       while (index >= 0) {
/* 1746 */         replaceImpl(index, index + searchLen, searchLen, replaceStr, replaceLen);
/* 1747 */         index = indexOf(searchStr, index + replaceLen);
/*      */       } 
/*      */     } 
/* 1750 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder replaceFirst(String searchStr, String replaceStr) {
/* 1761 */     int searchLen = (searchStr == null) ? 0 : searchStr.length();
/* 1762 */     if (searchLen > 0) {
/* 1763 */       int index = indexOf(searchStr, 0);
/* 1764 */       if (index >= 0) {
/* 1765 */         int replaceLen = (replaceStr == null) ? 0 : replaceStr.length();
/* 1766 */         replaceImpl(index, index + searchLen, searchLen, replaceStr, replaceLen);
/*      */       } 
/*      */     } 
/* 1769 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder replaceAll(StrMatcher matcher, String replaceStr) {
/* 1785 */     return replace(matcher, replaceStr, 0, this.size, -1);
/*      */   }
/*      */   
/*      */   public StrBuilder replaceFirst(StrMatcher matcher, String replaceStr) {
/* 1800 */     return replace(matcher, replaceStr, 0, this.size, 1);
/*      */   }
/*      */   
/*      */   public StrBuilder replace(StrMatcher matcher, String replaceStr, int startIndex, int endIndex, int replaceCount) {
/* 1823 */     endIndex = validateRange(startIndex, endIndex);
/* 1824 */     return replaceImpl(matcher, replaceStr, startIndex, endIndex, replaceCount);
/*      */   }
/*      */   
/*      */   private StrBuilder replaceImpl(StrMatcher matcher, String replaceStr, int from, int to, int replaceCount) {
/* 1845 */     if (matcher == null || this.size == 0)
/* 1846 */       return this; 
/* 1848 */     int replaceLen = (replaceStr == null) ? 0 : replaceStr.length();
/* 1849 */     char[] buf = this.buffer;
/* 1850 */     for (int i = from; i < to && replaceCount != 0; i++) {
/* 1851 */       int removeLen = matcher.isMatch(buf, i, from, to);
/* 1852 */       if (removeLen > 0) {
/* 1853 */         replaceImpl(i, i + removeLen, removeLen, replaceStr, replaceLen);
/* 1854 */         to = to - removeLen + replaceLen;
/* 1855 */         i = i + replaceLen - 1;
/* 1856 */         if (replaceCount > 0)
/* 1857 */           replaceCount--; 
/*      */       } 
/*      */     } 
/* 1861 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder reverse() {
/* 1871 */     if (this.size == 0)
/* 1872 */       return this; 
/* 1875 */     int half = this.size / 2;
/* 1876 */     char[] buf = this.buffer;
/* 1877 */     for (int leftIdx = 0, rightIdx = this.size - 1; leftIdx < half; leftIdx++, rightIdx--) {
/* 1878 */       char swap = buf[leftIdx];
/* 1879 */       buf[leftIdx] = buf[rightIdx];
/* 1880 */       buf[rightIdx] = swap;
/*      */     } 
/* 1882 */     return this;
/*      */   }
/*      */   
/*      */   public StrBuilder trim() {
/* 1893 */     if (this.size == 0)
/* 1894 */       return this; 
/* 1896 */     int len = this.size;
/* 1897 */     char[] buf = this.buffer;
/* 1898 */     int pos = 0;
/* 1899 */     while (pos < len && buf[pos] <= ' ')
/* 1900 */       pos++; 
/* 1902 */     while (pos < len && buf[len - 1] <= ' ')
/* 1903 */       len--; 
/* 1905 */     if (len < this.size)
/* 1906 */       delete(len, this.size); 
/* 1908 */     if (pos > 0)
/* 1909 */       delete(0, pos); 
/* 1911 */     return this;
/*      */   }
/*      */   
/*      */   public boolean startsWith(String str) {
/* 1924 */     if (str == null)
/* 1925 */       return false; 
/* 1927 */     int len = str.length();
/* 1928 */     if (len == 0)
/* 1929 */       return true; 
/* 1931 */     if (len > this.size)
/* 1932 */       return false; 
/* 1934 */     for (int i = 0; i < len; i++) {
/* 1935 */       if (this.buffer[i] != str.charAt(i))
/* 1936 */         return false; 
/*      */     } 
/* 1939 */     return true;
/*      */   }
/*      */   
/*      */   public boolean endsWith(String str) {
/* 1951 */     if (str == null)
/* 1952 */       return false; 
/* 1954 */     int len = str.length();
/* 1955 */     if (len == 0)
/* 1956 */       return true; 
/* 1958 */     if (len > this.size)
/* 1959 */       return false; 
/* 1961 */     int pos = this.size - len;
/* 1962 */     for (int i = 0; i < len; i++, pos++) {
/* 1963 */       if (this.buffer[pos] != str.charAt(i))
/* 1964 */         return false; 
/*      */     } 
/* 1967 */     return true;
/*      */   }
/*      */   
/*      */   public String substring(int start) {
/* 1979 */     return substring(start, this.size);
/*      */   }
/*      */   
/*      */   public String substring(int startIndex, int endIndex) {
/* 1996 */     endIndex = validateRange(startIndex, endIndex);
/* 1997 */     return new String(this.buffer, startIndex, endIndex - startIndex);
/*      */   }
/*      */   
/*      */   public String leftString(int length) {
/* 2013 */     if (length <= 0)
/* 2014 */       return ""; 
/* 2015 */     if (length >= this.size)
/* 2016 */       return new String(this.buffer, 0, this.size); 
/* 2018 */     return new String(this.buffer, 0, length);
/*      */   }
/*      */   
/*      */   public String rightString(int length) {
/* 2035 */     if (length <= 0)
/* 2036 */       return ""; 
/* 2037 */     if (length >= this.size)
/* 2038 */       return new String(this.buffer, 0, this.size); 
/* 2040 */     return new String(this.buffer, this.size - length, length);
/*      */   }
/*      */   
/*      */   public String midString(int index, int length) {
/* 2061 */     if (index < 0)
/* 2062 */       index = 0; 
/* 2064 */     if (length <= 0 || index >= this.size)
/* 2065 */       return ""; 
/* 2067 */     if (this.size <= index + length)
/* 2068 */       return new String(this.buffer, index, this.size - index); 
/* 2070 */     return new String(this.buffer, index, length);
/*      */   }
/*      */   
/*      */   public boolean contains(char ch) {
/* 2082 */     char[] thisBuf = this.buffer;
/* 2083 */     for (int i = 0; i < this.size; i++) {
/* 2084 */       if (thisBuf[i] == ch)
/* 2085 */         return true; 
/*      */     } 
/* 2088 */     return false;
/*      */   }
/*      */   
/*      */   public boolean contains(String str) {
/* 2098 */     return (indexOf(str, 0) >= 0);
/*      */   }
/*      */   
/*      */   public boolean contains(StrMatcher matcher) {
/* 2113 */     return (indexOf(matcher, 0) >= 0);
/*      */   }
/*      */   
/*      */   public int indexOf(char ch) {
/* 2124 */     return indexOf(ch, 0);
/*      */   }
/*      */   
/*      */   public int indexOf(char ch, int startIndex) {
/* 2135 */     startIndex = (startIndex < 0) ? 0 : startIndex;
/* 2136 */     if (startIndex >= this.size)
/* 2137 */       return -1; 
/* 2139 */     char[] thisBuf = this.buffer;
/* 2140 */     for (int i = startIndex; i < this.size; i++) {
/* 2141 */       if (thisBuf[i] == ch)
/* 2142 */         return i; 
/*      */     } 
/* 2145 */     return -1;
/*      */   }
/*      */   
/*      */   public int indexOf(String str) {
/* 2157 */     return indexOf(str, 0);
/*      */   }
/*      */   
/*      */   public int indexOf(String str, int startIndex) {
/* 2171 */     startIndex = (startIndex < 0) ? 0 : startIndex;
/* 2172 */     if (str == null || startIndex >= this.size)
/* 2173 */       return -1; 
/* 2175 */     int strLen = str.length();
/* 2176 */     if (strLen == 1)
/* 2177 */       return indexOf(str.charAt(0), startIndex); 
/* 2179 */     if (strLen == 0)
/* 2180 */       return startIndex; 
/* 2182 */     if (strLen > this.size)
/* 2183 */       return -1; 
/* 2185 */     char[] thisBuf = this.buffer;
/* 2186 */     int len = this.size - strLen + 1;
/* 2188 */     for (int i = startIndex; i < len; i++) {
/* 2189 */       int j = 0;
/*      */       while (true) {
/* 2189 */         if (j < strLen) {
/* 2190 */           if (str.charAt(j) != thisBuf[i + j])
/*      */             break; 
/*      */           j++;
/*      */           continue;
/*      */         } 
/* 2194 */         return i;
/*      */       } 
/*      */     } 
/* 2196 */     return -1;
/*      */   }
/*      */   
/*      */   public int indexOf(StrMatcher matcher) {
/* 2210 */     return indexOf(matcher, 0);
/*      */   }
/*      */   
/*      */   public int indexOf(StrMatcher matcher, int startIndex) {
/* 2226 */     startIndex = (startIndex < 0) ? 0 : startIndex;
/* 2227 */     if (matcher == null || startIndex >= this.size)
/* 2228 */       return -1; 
/* 2230 */     int len = this.size;
/* 2231 */     char[] buf = this.buffer;
/* 2232 */     for (int i = startIndex; i < len; i++) {
/* 2233 */       if (matcher.isMatch(buf, i, startIndex, len) > 0)
/* 2234 */         return i; 
/*      */     } 
/* 2237 */     return -1;
/*      */   }
/*      */   
/*      */   public int lastIndexOf(char ch) {
/* 2248 */     return lastIndexOf(ch, this.size - 1);
/*      */   }
/*      */   
/*      */   public int lastIndexOf(char ch, int startIndex) {
/* 2259 */     startIndex = (startIndex >= this.size) ? (this.size - 1) : startIndex;
/* 2260 */     if (startIndex < 0)
/* 2261 */       return -1; 
/* 2263 */     for (int i = startIndex; i >= 0; i--) {
/* 2264 */       if (this.buffer[i] == ch)
/* 2265 */         return i; 
/*      */     } 
/* 2268 */     return -1;
/*      */   }
/*      */   
/*      */   public int lastIndexOf(String str) {
/* 2280 */     return lastIndexOf(str, this.size - 1);
/*      */   }
/*      */   
/*      */   public int lastIndexOf(String str, int startIndex) {
/* 2294 */     startIndex = (startIndex >= this.size) ? (this.size - 1) : startIndex;
/* 2295 */     if (str == null || startIndex < 0)
/* 2296 */       return -1; 
/* 2298 */     int strLen = str.length();
/* 2299 */     if (strLen > 0 && strLen <= this.size) {
/* 2300 */       if (strLen == 1)
/* 2301 */         return lastIndexOf(str.charAt(0), startIndex); 
/* 2305 */       for (int i = startIndex - strLen + 1; i >= 0; i--) {
/* 2306 */         int j = 0;
/*      */         while (true) {
/* 2306 */           if (j < strLen) {
/* 2307 */             if (str.charAt(j) != this.buffer[i + j])
/*      */               break; 
/*      */             j++;
/*      */             continue;
/*      */           } 
/* 2311 */           return i;
/*      */         } 
/*      */       } 
/* 2314 */     } else if (strLen == 0) {
/* 2315 */       return startIndex;
/*      */     } 
/* 2317 */     return -1;
/*      */   }
/*      */   
/*      */   public int lastIndexOf(StrMatcher matcher) {
/* 2331 */     return lastIndexOf(matcher, this.size);
/*      */   }
/*      */   
/*      */   public int lastIndexOf(StrMatcher matcher, int startIndex) {
/* 2347 */     startIndex = (startIndex >= this.size) ? (this.size - 1) : startIndex;
/* 2348 */     if (matcher == null || startIndex < 0)
/* 2349 */       return -1; 
/* 2351 */     char[] buf = this.buffer;
/* 2352 */     int endIndex = startIndex + 1;
/* 2353 */     for (int i = startIndex; i >= 0; i--) {
/* 2354 */       if (matcher.isMatch(buf, i, 0, endIndex) > 0)
/* 2355 */         return i; 
/*      */     } 
/* 2358 */     return -1;
/*      */   }
/*      */   
/*      */   public StrTokenizer asTokenizer() {
/* 2395 */     return new StrBuilderTokenizer(this);
/*      */   }
/*      */   
/*      */   public Reader asReader() {
/* 2419 */     return new StrBuilderReader(this);
/*      */   }
/*      */   
/*      */   public Writer asWriter() {
/* 2444 */     return new StrBuilderWriter(this);
/*      */   }
/*      */   
/*      */   public boolean equalsIgnoreCase(StrBuilder other) {
/* 2486 */     if (this == other)
/* 2487 */       return true; 
/* 2489 */     if (this.size != other.size)
/* 2490 */       return false; 
/* 2492 */     char[] thisBuf = this.buffer;
/* 2493 */     char[] otherBuf = other.buffer;
/* 2494 */     for (int i = this.size - 1; i >= 0; i--) {
/* 2495 */       char c1 = thisBuf[i];
/* 2496 */       char c2 = otherBuf[i];
/* 2497 */       if (c1 != c2 && Character.toUpperCase(c1) != Character.toUpperCase(c2))
/* 2498 */         return false; 
/*      */     } 
/* 2501 */     return true;
/*      */   }
/*      */   
/*      */   public boolean equals(StrBuilder other) {
/* 2512 */     if (this == other)
/* 2513 */       return true; 
/* 2515 */     if (this.size != other.size)
/* 2516 */       return false; 
/* 2518 */     char[] thisBuf = this.buffer;
/* 2519 */     char[] otherBuf = other.buffer;
/* 2520 */     for (int i = this.size - 1; i >= 0; i--) {
/* 2521 */       if (thisBuf[i] != otherBuf[i])
/* 2522 */         return false; 
/*      */     } 
/* 2525 */     return true;
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/* 2536 */     if (obj instanceof StrBuilder)
/* 2537 */       return equals((StrBuilder)obj); 
/* 2539 */     return false;
/*      */   }
/*      */   
/*      */   public int hashCode() {
/* 2548 */     char[] buf = this.buffer;
/* 2549 */     int hash = 0;
/* 2550 */     for (int i = this.size - 1; i >= 0; i--)
/* 2551 */       hash = 31 * hash + buf[i]; 
/* 2553 */     return hash;
/*      */   }
/*      */   
/*      */   public String toString() {
/* 2567 */     return new String(this.buffer, 0, this.size);
/*      */   }
/*      */   
/*      */   public StringBuffer toStringBuffer() {
/* 2577 */     return (new StringBuffer(this.size)).append(this.buffer, 0, this.size);
/*      */   }
/*      */   
/*      */   public Object clone() throws CloneNotSupportedException {
/* 2588 */     StrBuilder clone = (StrBuilder)super.clone();
/* 2589 */     clone.buffer = new char[this.buffer.length];
/* 2590 */     System.arraycopy(this.buffer, 0, clone.buffer, 0, this.buffer.length);
/* 2591 */     return clone;
/*      */   }
/*      */   
/*      */   protected int validateRange(int startIndex, int endIndex) {
/* 2605 */     if (startIndex < 0)
/* 2606 */       throw new StringIndexOutOfBoundsException(startIndex); 
/* 2608 */     if (endIndex > this.size)
/* 2609 */       endIndex = this.size; 
/* 2611 */     if (startIndex > endIndex)
/* 2612 */       throw new StringIndexOutOfBoundsException("end < start"); 
/* 2614 */     return endIndex;
/*      */   }
/*      */   
/*      */   protected void validateIndex(int index) {
/* 2624 */     if (index < 0 || index > this.size)
/* 2625 */       throw new StringIndexOutOfBoundsException(index); 
/*      */   }
/*      */   
/*      */   class StrBuilderTokenizer extends StrTokenizer {
/*      */     private final StrBuilder this$0;
/*      */     
/*      */     StrBuilderTokenizer(StrBuilder this$0) {
/* 2638 */       this.this$0 = this$0;
/*      */     }
/*      */     
/*      */     protected List tokenize(char[] chars, int offset, int count) {
/* 2644 */       if (chars == null)
/* 2645 */         return super.tokenize(this.this$0.buffer, 0, this.this$0.size()); 
/* 2647 */       return super.tokenize(chars, offset, count);
/*      */     }
/*      */     
/*      */     public String getContent() {
/* 2653 */       String str = super.getContent();
/* 2654 */       if (str == null)
/* 2655 */         return this.this$0.toString(); 
/* 2657 */       return str;
/*      */     }
/*      */   }
/*      */   
/*      */   class StrBuilderReader extends Reader {
/*      */     private int pos;
/*      */     
/*      */     private int mark;
/*      */     
/*      */     private final StrBuilder this$0;
/*      */     
/*      */     StrBuilderReader(StrBuilder this$0) {
/* 2675 */       this.this$0 = this$0;
/*      */     }
/*      */     
/*      */     public void close() {}
/*      */     
/*      */     public int read() {
/* 2686 */       if (!ready())
/* 2687 */         return -1; 
/* 2689 */       return this.this$0.charAt(this.pos++);
/*      */     }
/*      */     
/*      */     public int read(char[] b, int off, int len) {
/* 2694 */       if (off < 0 || len < 0 || off > b.length || off + len > b.length || off + len < 0)
/* 2696 */         throw new IndexOutOfBoundsException(); 
/* 2698 */       if (len == 0)
/* 2699 */         return 0; 
/* 2701 */       if (this.pos >= this.this$0.size())
/* 2702 */         return -1; 
/* 2704 */       if (this.pos + len > this.this$0.size())
/* 2705 */         len = this.this$0.size() - this.pos; 
/* 2707 */       this.this$0.getChars(this.pos, this.pos + len, b, off);
/* 2708 */       this.pos += len;
/* 2709 */       return len;
/*      */     }
/*      */     
/*      */     public long skip(long n) {
/* 2714 */       if (this.pos + n > this.this$0.size())
/* 2715 */         n = (this.this$0.size() - this.pos); 
/* 2717 */       if (n < 0L)
/* 2718 */         return 0L; 
/* 2720 */       this.pos = (int)(this.pos + n);
/* 2721 */       return n;
/*      */     }
/*      */     
/*      */     public boolean ready() {
/* 2726 */       return (this.pos < this.this$0.size());
/*      */     }
/*      */     
/*      */     public boolean markSupported() {
/* 2731 */       return true;
/*      */     }
/*      */     
/*      */     public void mark(int readAheadLimit) {
/* 2736 */       this.mark = this.pos;
/*      */     }
/*      */     
/*      */     public void reset() {
/* 2741 */       this.pos = this.mark;
/*      */     }
/*      */   }
/*      */   
/*      */   class StrBuilderWriter extends Writer {
/*      */     private final StrBuilder this$0;
/*      */     
/*      */     StrBuilderWriter(StrBuilder this$0) {
/* 2754 */       this.this$0 = this$0;
/*      */     }
/*      */     
/*      */     public void close() {}
/*      */     
/*      */     public void flush() {}
/*      */     
/*      */     public void write(int c) {
/* 2770 */       this.this$0.append((char)c);
/*      */     }
/*      */     
/*      */     public void write(char[] cbuf) {
/* 2775 */       this.this$0.append(cbuf);
/*      */     }
/*      */     
/*      */     public void write(char[] cbuf, int off, int len) {
/* 2780 */       this.this$0.append(cbuf, off, len);
/*      */     }
/*      */     
/*      */     public void write(String str) {
/* 2785 */       this.this$0.append(str);
/*      */     }
/*      */     
/*      */     public void write(String str, int off, int len) {
/* 2790 */       this.this$0.append(str, off, len);
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\text\StrBuilder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */