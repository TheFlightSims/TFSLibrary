/*      */ package com.ctc.wstx.util;
/*      */ 
/*      */ import com.ctc.wstx.api.ReaderConfig;
/*      */ import com.ctc.wstx.dtd.DTDEventListener;
/*      */ import com.ctc.wstx.sr.InputProblemReporter;
/*      */ import java.io.CharArrayReader;
/*      */ import java.io.IOException;
/*      */ import java.io.Reader;
/*      */ import java.io.StringReader;
/*      */ import java.io.Writer;
/*      */ import java.util.ArrayList;
/*      */ import javax.xml.stream.Location;
/*      */ import javax.xml.stream.XMLStreamException;
/*      */ import org.codehaus.stax2.ri.typed.CharArrayBase64Decoder;
/*      */ import org.codehaus.stax2.typed.Base64Variant;
/*      */ import org.codehaus.stax2.typed.TypedArrayDecoder;
/*      */ import org.codehaus.stax2.typed.TypedValueDecoder;
/*      */ import org.codehaus.stax2.typed.TypedXMLStreamException;
/*      */ import org.codehaus.stax2.validation.XMLValidator;
/*      */ import org.xml.sax.ContentHandler;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.ext.LexicalHandler;
/*      */ 
/*      */ public final class TextBuffer {
/*      */   static final int DEF_INITIAL_BUFFER_SIZE = 500;
/*      */   
/*      */   static final int MAX_SEGMENT_LENGTH = 262144;
/*      */   
/*      */   static final int INT_SPACE = 32;
/*      */   
/*      */   private final ReaderConfig mConfig;
/*      */   
/*      */   private char[] mInputBuffer;
/*      */   
/*      */   private int mInputStart;
/*      */   
/*      */   private int mInputLen;
/*      */   
/*      */   private boolean mHasSegments = false;
/*      */   
/*      */   private ArrayList mSegments;
/*      */   
/*      */   private int mSegmentSize;
/*      */   
/*      */   private char[] mCurrentSegment;
/*      */   
/*      */   private int mCurrentSize;
/*      */   
/*      */   private String mResultString;
/*      */   
/*      */   private char[] mResultArray;
/*      */   
/*      */   public static final int MAX_INDENT_SPACES = 32;
/*      */   
/*      */   public static final int MAX_INDENT_TABS = 8;
/*      */   
/*      */   private static final String sIndSpaces = "\n                                 ";
/*      */   
/*  144 */   private static final char[] sIndSpacesArray = "\n                                 ".toCharArray();
/*      */   
/*  145 */   private static final String[] sIndSpacesStrings = new String[sIndSpacesArray.length];
/*      */   
/*      */   private static final String sIndTabs = "\n\t\t\t\t\t\t\t\t\t";
/*      */   
/*  150 */   private static final char[] sIndTabsArray = "\n\t\t\t\t\t\t\t\t\t".toCharArray();
/*      */   
/*  151 */   private static final String[] sIndTabsStrings = new String[sIndTabsArray.length];
/*      */   
/*      */   private TextBuffer(ReaderConfig cfg) {
/*  161 */     this.mConfig = cfg;
/*      */   }
/*      */   
/*      */   public static TextBuffer createRecyclableBuffer(ReaderConfig cfg) {
/*  166 */     return new TextBuffer(cfg);
/*      */   }
/*      */   
/*      */   public static TextBuffer createTemporaryBuffer() {
/*  171 */     return new TextBuffer(null);
/*      */   }
/*      */   
/*      */   public void recycle(boolean force) {
/*  185 */     if (this.mConfig != null && this.mCurrentSegment != null) {
/*  186 */       if (force) {
/*  191 */         resetWithEmpty();
/*      */       } else {
/*  196 */         if (this.mInputStart < 0 && this.mSegmentSize + this.mCurrentSize > 0)
/*      */           return; 
/*  200 */         if (this.mSegments != null && this.mSegments.size() > 0) {
/*  202 */           this.mSegments.clear();
/*  203 */           this.mSegmentSize = 0;
/*      */         } 
/*      */       } 
/*  207 */       char[] buf = this.mCurrentSegment;
/*  208 */       this.mCurrentSegment = null;
/*  209 */       this.mConfig.freeMediumCBuffer(buf);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void resetWithEmpty() {
/*  219 */     this.mInputBuffer = null;
/*  220 */     this.mInputStart = -1;
/*  221 */     this.mInputLen = 0;
/*  223 */     this.mResultString = null;
/*  224 */     this.mResultArray = null;
/*  227 */     if (this.mHasSegments)
/*  228 */       clearSegments(); 
/*  230 */     this.mCurrentSize = 0;
/*      */   }
/*      */   
/*      */   public void resetWithEmptyString() {
/*  240 */     this.mInputBuffer = null;
/*  241 */     this.mInputStart = -1;
/*  242 */     this.mInputLen = 0;
/*  243 */     this.mResultString = "";
/*  244 */     this.mResultArray = null;
/*  245 */     if (this.mHasSegments)
/*  246 */       clearSegments(); 
/*  248 */     this.mCurrentSize = 0;
/*      */   }
/*      */   
/*      */   public void resetWithShared(char[] buf, int start, int len) {
/*  260 */     this.mInputBuffer = buf;
/*  261 */     this.mInputStart = start;
/*  262 */     this.mInputLen = len;
/*  265 */     this.mResultString = null;
/*  266 */     this.mResultArray = null;
/*  269 */     if (this.mHasSegments)
/*  270 */       clearSegments(); 
/*      */   }
/*      */   
/*      */   public void resetWithCopy(char[] buf, int start, int len) {
/*  276 */     this.mInputBuffer = null;
/*  277 */     this.mInputStart = -1;
/*  278 */     this.mInputLen = 0;
/*  280 */     this.mResultString = null;
/*  281 */     this.mResultArray = null;
/*  284 */     if (this.mHasSegments) {
/*  285 */       clearSegments();
/*      */     } else {
/*  287 */       if (this.mCurrentSegment == null)
/*  288 */         this.mCurrentSegment = allocBuffer(len); 
/*  290 */       this.mCurrentSize = this.mSegmentSize = 0;
/*      */     } 
/*  292 */     append(buf, start, len);
/*      */   }
/*      */   
/*      */   public void resetInitialized() {
/*  301 */     resetWithEmpty();
/*  302 */     if (this.mCurrentSegment == null)
/*  303 */       this.mCurrentSegment = allocBuffer(0); 
/*      */   }
/*      */   
/*      */   private final char[] allocBuffer(int needed) {
/*  309 */     int size = Math.max(needed, 500);
/*  310 */     char[] buf = null;
/*  311 */     if (this.mConfig != null) {
/*  312 */       buf = this.mConfig.allocMediumCBuffer(size);
/*  313 */       if (buf != null)
/*  314 */         return buf; 
/*      */     } 
/*  317 */     return new char[size];
/*      */   }
/*      */   
/*      */   private final void clearSegments() {
/*  322 */     this.mHasSegments = false;
/*  327 */     this.mSegments.clear();
/*  328 */     this.mCurrentSize = this.mSegmentSize = 0;
/*      */   }
/*      */   
/*      */   public void resetWithIndentation(int indCharCount, char indChar) {
/*      */     String text;
/*  333 */     this.mInputStart = 0;
/*  334 */     this.mInputLen = indCharCount + 1;
/*  336 */     if (indChar == '\t') {
/*  337 */       this.mInputBuffer = sIndTabsArray;
/*  338 */       text = sIndTabsStrings[indCharCount];
/*  339 */       if (text == null)
/*  340 */         sIndTabsStrings[indCharCount] = text = "\n\t\t\t\t\t\t\t\t\t".substring(0, this.mInputLen); 
/*      */     } else {
/*  343 */       this.mInputBuffer = sIndSpacesArray;
/*  344 */       text = sIndSpacesStrings[indCharCount];
/*  345 */       if (text == null)
/*  346 */         sIndSpacesStrings[indCharCount] = text = "\n                                 ".substring(0, this.mInputLen); 
/*      */     } 
/*  349 */     this.mResultString = text;
/*  354 */     this.mResultArray = null;
/*  357 */     if (this.mSegments != null && this.mSegments.size() > 0) {
/*  358 */       this.mSegments.clear();
/*  359 */       this.mCurrentSize = this.mSegmentSize = 0;
/*      */     } 
/*      */   }
/*      */   
/*      */   public int size() {
/*  373 */     if (this.mInputStart >= 0)
/*  374 */       return this.mInputLen; 
/*  377 */     return this.mSegmentSize + this.mCurrentSize;
/*      */   }
/*      */   
/*      */   public int getTextStart() {
/*  386 */     return (this.mInputStart >= 0) ? this.mInputStart : 0;
/*      */   }
/*      */   
/*      */   public char[] getTextBuffer() {
/*  392 */     if (this.mInputStart >= 0)
/*  393 */       return this.mInputBuffer; 
/*  396 */     if (this.mSegments == null || this.mSegments.size() == 0)
/*  397 */       return this.mCurrentSegment; 
/*  400 */     return contentsAsArray();
/*      */   }
/*      */   
/*      */   public void decode(TypedValueDecoder tvd) throws IllegalArgumentException {
/*      */     char[] buf;
/*      */     int start, end;
/*  419 */     if (this.mInputStart >= 0) {
/*  420 */       buf = this.mInputBuffer;
/*  421 */       start = this.mInputStart;
/*  422 */       end = start + this.mInputLen;
/*      */     } else {
/*  424 */       buf = getTextBuffer();
/*  425 */       start = 0;
/*  426 */       end = this.mSegmentSize + this.mCurrentSize;
/*      */     } 
/*      */     while (true) {
/*  431 */       if (start >= end) {
/*  432 */         tvd.handleEmptyValue();
/*      */         return;
/*      */       } 
/*  435 */       if (!StringUtil.isSpace(buf[start]))
/*      */         break; 
/*  438 */       start++;
/*      */     } 
/*  441 */     while (--end > start && StringUtil.isSpace(buf[end]));
/*  442 */     tvd.decode(buf, start, end + 1);
/*      */   }
/*      */   
/*      */   public int decodeElements(TypedArrayDecoder tad, InputProblemReporter rep) throws TypedXMLStreamException {
/*  458 */     int count = 0;
/*  465 */     if (this.mInputStart < 0) {
/*  466 */       if (this.mHasSegments) {
/*  467 */         this.mInputBuffer = buildResultArray();
/*  468 */         this.mInputLen = this.mInputBuffer.length;
/*  470 */         clearSegments();
/*      */       } else {
/*  473 */         this.mInputBuffer = this.mCurrentSegment;
/*  474 */         this.mInputLen = this.mCurrentSize;
/*      */       } 
/*  476 */       this.mInputStart = 0;
/*      */     } 
/*  480 */     int ptr = this.mInputStart;
/*  481 */     int end = ptr + this.mInputLen;
/*  482 */     char[] buf = this.mInputBuffer;
/*  483 */     int start = ptr;
/*      */     try {
/*  487 */       label32: while (ptr < end) {
/*  489 */         while (buf[ptr] <= ' ') {
/*  490 */           if (++ptr >= end)
/*      */             break label32; 
/*      */         } 
/*  495 */         start = ptr;
/*  496 */         ptr++;
/*  497 */         while (ptr < end && buf[ptr] > ' ')
/*  498 */           ptr++; 
/*  500 */         count++;
/*  501 */         int tokenEnd = ptr;
/*  502 */         ptr++;
/*  504 */         if (tad.decodeValue(buf, start, tokenEnd))
/*      */           break; 
/*      */       } 
/*  508 */     } catch (IllegalArgumentException iae) {
/*  515 */       Location loc = rep.getLocation();
/*  517 */       String lexical = new String(buf, start, ptr - start - 1);
/*  518 */       throw new TypedXMLStreamException(lexical, iae.getMessage(), loc, iae);
/*      */     } finally {
/*  520 */       this.mInputStart = ptr;
/*  521 */       this.mInputLen = end - ptr;
/*      */     } 
/*  523 */     return count;
/*      */   }
/*      */   
/*      */   public void initBinaryChunks(Base64Variant v, CharArrayBase64Decoder dec, boolean firstChunk) {
/*  536 */     if (this.mInputStart < 0) {
/*  537 */       dec.init(v, firstChunk, this.mCurrentSegment, 0, this.mCurrentSize, this.mSegments);
/*      */     } else {
/*  539 */       dec.init(v, firstChunk, this.mInputBuffer, this.mInputStart, this.mInputLen, null);
/*      */     } 
/*      */   }
/*      */   
/*      */   public String contentsAsString() {
/*  551 */     if (this.mResultString == null)
/*  553 */       if (this.mResultArray != null) {
/*  554 */         this.mResultString = new String(this.mResultArray);
/*  557 */       } else if (this.mInputStart >= 0) {
/*  558 */         if (this.mInputLen < 1)
/*  559 */           return this.mResultString = ""; 
/*  561 */         this.mResultString = new String(this.mInputBuffer, this.mInputStart, this.mInputLen);
/*      */       } else {
/*  564 */         int segLen = this.mSegmentSize;
/*  565 */         int currLen = this.mCurrentSize;
/*  567 */         if (segLen == 0) {
/*  568 */           this.mResultString = (currLen == 0) ? "" : new String(this.mCurrentSegment, 0, currLen);
/*      */         } else {
/*  570 */           StringBuffer sb = new StringBuffer(segLen + currLen);
/*  572 */           if (this.mSegments != null)
/*  573 */             for (int i = 0, len = this.mSegments.size(); i < len; i++) {
/*  574 */               char[] curr = this.mSegments.get(i);
/*  575 */               sb.append(curr, 0, curr.length);
/*      */             }  
/*  579 */           sb.append(this.mCurrentSegment, 0, this.mCurrentSize);
/*  580 */           this.mResultString = sb.toString();
/*      */         } 
/*      */       }  
/*  585 */     return this.mResultString;
/*      */   }
/*      */   
/*      */   public StringBuffer contentsAsStringBuffer(int extraSpace) {
/*  597 */     if (this.mResultString != null)
/*  598 */       return new StringBuffer(this.mResultString); 
/*  600 */     if (this.mResultArray != null) {
/*  601 */       StringBuffer stringBuffer = new StringBuffer(this.mResultArray.length + extraSpace);
/*  602 */       stringBuffer.append(this.mResultArray, 0, this.mResultArray.length);
/*  603 */       return stringBuffer;
/*      */     } 
/*  605 */     if (this.mInputStart >= 0) {
/*  606 */       if (this.mInputLen < 1)
/*  607 */         return new StringBuffer(); 
/*  609 */       StringBuffer stringBuffer = new StringBuffer(this.mInputLen + extraSpace);
/*  610 */       stringBuffer.append(this.mInputBuffer, this.mInputStart, this.mInputLen);
/*  611 */       return stringBuffer;
/*      */     } 
/*  613 */     int segLen = this.mSegmentSize;
/*  614 */     int currLen = this.mCurrentSize;
/*  616 */     StringBuffer sb = new StringBuffer(segLen + currLen + extraSpace);
/*  618 */     if (this.mSegments != null)
/*  619 */       for (int i = 0, len = this.mSegments.size(); i < len; i++) {
/*  620 */         char[] curr = this.mSegments.get(i);
/*  621 */         sb.append(curr, 0, curr.length);
/*      */       }  
/*  625 */     sb.append(this.mCurrentSegment, 0, currLen);
/*  626 */     return sb;
/*      */   }
/*      */   
/*      */   public void contentsToStringBuffer(StringBuffer sb) {
/*  631 */     if (this.mResultString != null) {
/*  632 */       sb.append(this.mResultString);
/*  633 */     } else if (this.mResultArray != null) {
/*  634 */       sb.append(this.mResultArray);
/*  635 */     } else if (this.mInputStart >= 0) {
/*  636 */       if (this.mInputLen > 0)
/*  637 */         sb.append(this.mInputBuffer, this.mInputStart, this.mInputLen); 
/*      */     } else {
/*  641 */       if (this.mSegments != null)
/*  642 */         for (int i = 0, len = this.mSegments.size(); i < len; i++) {
/*  643 */           char[] curr = this.mSegments.get(i);
/*  644 */           sb.append(curr, 0, curr.length);
/*      */         }  
/*  648 */       sb.append(this.mCurrentSegment, 0, this.mCurrentSize);
/*      */     } 
/*      */   }
/*      */   
/*      */   public char[] contentsAsArray() {
/*  654 */     char[] result = this.mResultArray;
/*  655 */     if (result == null)
/*  656 */       this.mResultArray = result = buildResultArray(); 
/*  658 */     return result;
/*      */   }
/*      */   
/*      */   public int contentsToArray(int srcStart, char[] dst, int dstStart, int len) {
/*  663 */     if (this.mInputStart >= 0) {
/*  664 */       int amount = this.mInputLen - srcStart;
/*  665 */       if (amount > len) {
/*  666 */         amount = len;
/*  667 */       } else if (amount < 0) {
/*  668 */         amount = 0;
/*      */       } 
/*  670 */       if (amount > 0)
/*  671 */         System.arraycopy(this.mInputBuffer, this.mInputStart + srcStart, dst, dstStart, amount); 
/*  674 */       return amount;
/*      */     } 
/*  682 */     int totalAmount = 0;
/*  683 */     if (this.mSegments != null)
/*  684 */       for (int i = 0, segc = this.mSegments.size(); i < segc; i++) {
/*  685 */         char[] segment = this.mSegments.get(i);
/*  686 */         int segLen = segment.length;
/*  687 */         int amount = segLen - srcStart;
/*  688 */         if (amount < 1) {
/*  689 */           srcStart -= segLen;
/*      */         } else {
/*  692 */           if (amount >= len) {
/*  693 */             System.arraycopy(segment, srcStart, dst, dstStart, len);
/*  694 */             return totalAmount + len;
/*      */           } 
/*  697 */           System.arraycopy(segment, srcStart, dst, dstStart, amount);
/*  698 */           totalAmount += amount;
/*  699 */           dstStart += amount;
/*  700 */           len -= amount;
/*  701 */           srcStart = 0;
/*      */         } 
/*      */       }  
/*  706 */     if (len > 0) {
/*  707 */       int maxAmount = this.mCurrentSize - srcStart;
/*  708 */       if (len > maxAmount)
/*  709 */         len = maxAmount; 
/*  711 */       if (len > 0) {
/*  712 */         System.arraycopy(this.mCurrentSegment, srcStart, dst, dstStart, len);
/*  713 */         totalAmount += len;
/*      */       } 
/*      */     } 
/*  717 */     return totalAmount;
/*      */   }
/*      */   
/*      */   public int rawContentsTo(Writer w) throws IOException {
/*  728 */     if (this.mResultArray != null) {
/*  729 */       w.write(this.mResultArray);
/*  730 */       return this.mResultArray.length;
/*      */     } 
/*  732 */     if (this.mResultString != null) {
/*  733 */       w.write(this.mResultString);
/*  734 */       return this.mResultString.length();
/*      */     } 
/*  738 */     if (this.mInputStart >= 0) {
/*  739 */       if (this.mInputLen > 0)
/*  740 */         w.write(this.mInputBuffer, this.mInputStart, this.mInputLen); 
/*  742 */       return this.mInputLen;
/*      */     } 
/*  745 */     int rlen = 0;
/*  746 */     if (this.mSegments != null)
/*  747 */       for (int i = 0, len = this.mSegments.size(); i < len; i++) {
/*  748 */         char[] ch = this.mSegments.get(i);
/*  749 */         w.write(ch);
/*  750 */         rlen += ch.length;
/*      */       }  
/*  753 */     if (this.mCurrentSize > 0) {
/*  754 */       w.write(this.mCurrentSegment, 0, this.mCurrentSize);
/*  755 */       rlen += this.mCurrentSize;
/*      */     } 
/*  757 */     return rlen;
/*      */   }
/*      */   
/*      */   public Reader rawContentsViaReader() throws IOException {
/*  764 */     if (this.mResultArray != null)
/*  765 */       return new CharArrayReader(this.mResultArray); 
/*  767 */     if (this.mResultString != null)
/*  768 */       return new StringReader(this.mResultString); 
/*  772 */     if (this.mInputStart >= 0) {
/*  773 */       if (this.mInputLen > 0)
/*  774 */         return new CharArrayReader(this.mInputBuffer, this.mInputStart, this.mInputLen); 
/*  776 */       return new StringReader("");
/*      */     } 
/*  779 */     if (this.mSegments == null || this.mSegments.size() == 0)
/*  780 */       return new CharArrayReader(this.mCurrentSegment, 0, this.mCurrentSize); 
/*  783 */     return new BufferReader(this.mSegments, this.mCurrentSegment, this.mCurrentSize);
/*      */   }
/*      */   
/*      */   public boolean isAllWhitespace() {
/*  788 */     if (this.mInputStart >= 0) {
/*  789 */       char[] arrayOfChar = this.mInputBuffer;
/*  790 */       int j = this.mInputStart;
/*  791 */       int last = j + this.mInputLen;
/*  792 */       for (; j < last; j++) {
/*  793 */         if (arrayOfChar[j] > ' ')
/*  794 */           return false; 
/*      */       } 
/*  797 */       return true;
/*      */     } 
/*  801 */     if (this.mSegments != null)
/*  802 */       for (int j = 0, k = this.mSegments.size(); j < k; j++) {
/*  803 */         char[] arrayOfChar = this.mSegments.get(j);
/*  804 */         for (int m = 0, len2 = arrayOfChar.length; m < len2; m++) {
/*  805 */           if (arrayOfChar[m] > ' ')
/*  806 */             return false; 
/*      */         } 
/*      */       }  
/*  812 */     char[] buf = this.mCurrentSegment;
/*  813 */     for (int i = 0, len = this.mCurrentSize; i < len; i++) {
/*  814 */       if (buf[i] > ' ')
/*  815 */         return false; 
/*      */     } 
/*  818 */     return true;
/*      */   }
/*      */   
/*      */   public boolean endsWith(String str) {
/*  834 */     if (this.mInputStart >= 0)
/*  835 */       unshare(16); 
/*  838 */     int segIndex = (this.mSegments == null) ? 0 : this.mSegments.size();
/*  839 */     int inIndex = str.length() - 1;
/*  840 */     char[] buf = this.mCurrentSegment;
/*  841 */     int bufIndex = this.mCurrentSize - 1;
/*  843 */     while (inIndex >= 0) {
/*  844 */       if (str.charAt(inIndex) != buf[bufIndex])
/*  845 */         return false; 
/*  847 */       if (--inIndex == 0)
/*      */         break; 
/*  850 */       if (--bufIndex < 0) {
/*  851 */         if (--segIndex < 0)
/*  852 */           return false; 
/*  854 */         buf = this.mSegments.get(segIndex);
/*  855 */         bufIndex = buf.length - 1;
/*      */       } 
/*      */     } 
/*  859 */     return true;
/*      */   }
/*      */   
/*      */   public boolean equalsString(String str) {
/*      */     char[] seg;
/*  870 */     int expLen = str.length();
/*  873 */     if (this.mInputStart >= 0) {
/*  874 */       if (this.mInputLen != expLen)
/*  875 */         return false; 
/*  877 */       for (int j = 0; j < expLen; j++) {
/*  878 */         if (str.charAt(j) != this.mInputBuffer[this.mInputStart + j])
/*  879 */           return false; 
/*      */       } 
/*  882 */       return true;
/*      */     } 
/*  886 */     if (expLen != size())
/*  887 */       return false; 
/*  890 */     if (this.mSegments == null || this.mSegments.size() == 0) {
/*  892 */       seg = this.mCurrentSegment;
/*      */     } else {
/*  898 */       seg = contentsAsArray();
/*      */     } 
/*  901 */     for (int i = 0; i < expLen; i++) {
/*  902 */       if (seg[i] != str.charAt(i))
/*  903 */         return false; 
/*      */     } 
/*  906 */     return true;
/*      */   }
/*      */   
/*      */   public void fireSaxCharacterEvents(ContentHandler h) throws SAXException {
/*  918 */     if (this.mResultArray != null) {
/*  919 */       h.characters(this.mResultArray, 0, this.mResultArray.length);
/*  920 */     } else if (this.mInputStart >= 0) {
/*  921 */       h.characters(this.mInputBuffer, this.mInputStart, this.mInputLen);
/*      */     } else {
/*  923 */       if (this.mSegments != null)
/*  924 */         for (int i = 0, len = this.mSegments.size(); i < len; i++) {
/*  925 */           char[] ch = this.mSegments.get(i);
/*  926 */           h.characters(ch, 0, ch.length);
/*      */         }  
/*  929 */       if (this.mCurrentSize > 0)
/*  930 */         h.characters(this.mCurrentSegment, 0, this.mCurrentSize); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void fireSaxSpaceEvents(ContentHandler h) throws SAXException {
/*  938 */     if (this.mResultArray != null) {
/*  939 */       h.ignorableWhitespace(this.mResultArray, 0, this.mResultArray.length);
/*  940 */     } else if (this.mInputStart >= 0) {
/*  941 */       h.ignorableWhitespace(this.mInputBuffer, this.mInputStart, this.mInputLen);
/*      */     } else {
/*  943 */       if (this.mSegments != null)
/*  944 */         for (int i = 0, len = this.mSegments.size(); i < len; i++) {
/*  945 */           char[] ch = this.mSegments.get(i);
/*  946 */           h.ignorableWhitespace(ch, 0, ch.length);
/*      */         }  
/*  949 */       if (this.mCurrentSize > 0)
/*  950 */         h.ignorableWhitespace(this.mCurrentSegment, 0, this.mCurrentSize); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void fireSaxCommentEvent(LexicalHandler h) throws SAXException {
/*  959 */     if (this.mResultArray != null) {
/*  960 */       h.comment(this.mResultArray, 0, this.mResultArray.length);
/*  961 */     } else if (this.mInputStart >= 0) {
/*  962 */       h.comment(this.mInputBuffer, this.mInputStart, this.mInputLen);
/*  963 */     } else if (this.mSegments != null && this.mSegments.size() > 0) {
/*  964 */       char[] ch = contentsAsArray();
/*  965 */       h.comment(ch, 0, ch.length);
/*      */     } else {
/*  967 */       h.comment(this.mCurrentSegment, 0, this.mCurrentSize);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void fireDtdCommentEvent(DTDEventListener l) {
/*  974 */     if (this.mResultArray != null) {
/*  975 */       l.dtdComment(this.mResultArray, 0, this.mResultArray.length);
/*  976 */     } else if (this.mInputStart >= 0) {
/*  977 */       l.dtdComment(this.mInputBuffer, this.mInputStart, this.mInputLen);
/*  978 */     } else if (this.mSegments != null && this.mSegments.size() > 0) {
/*  979 */       char[] ch = contentsAsArray();
/*  980 */       l.dtdComment(ch, 0, ch.length);
/*      */     } else {
/*  982 */       l.dtdComment(this.mCurrentSegment, 0, this.mCurrentSize);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void validateText(XMLValidator vld, boolean lastSegment) throws XMLStreamException {
/*  996 */     if (this.mInputStart >= 0) {
/*  997 */       vld.validateText(this.mInputBuffer, this.mInputStart, this.mInputStart + this.mInputLen, lastSegment);
/*      */     } else {
/* 1004 */       vld.validateText(contentsAsString(), lastSegment);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void ensureNotShared() {
/* 1019 */     if (this.mInputStart >= 0)
/* 1020 */       unshare(16); 
/*      */   }
/*      */   
/*      */   public void append(char c) {
/* 1026 */     if (this.mInputStart >= 0)
/* 1027 */       unshare(16); 
/* 1029 */     this.mResultString = null;
/* 1030 */     this.mResultArray = null;
/* 1032 */     char[] curr = this.mCurrentSegment;
/* 1033 */     if (this.mCurrentSize >= curr.length) {
/* 1034 */       expand(1);
/* 1035 */       curr = this.mCurrentSegment;
/*      */     } 
/* 1037 */     curr[this.mCurrentSize++] = c;
/*      */   }
/*      */   
/*      */   public void append(char[] c, int start, int len) {
/* 1043 */     if (this.mInputStart >= 0)
/* 1044 */       unshare(len); 
/* 1046 */     this.mResultString = null;
/* 1047 */     this.mResultArray = null;
/* 1050 */     char[] curr = this.mCurrentSegment;
/* 1051 */     int max = curr.length - this.mCurrentSize;
/* 1053 */     if (max >= len) {
/* 1054 */       System.arraycopy(c, start, curr, this.mCurrentSize, len);
/* 1055 */       this.mCurrentSize += len;
/*      */     } else {
/* 1058 */       if (max > 0) {
/* 1059 */         System.arraycopy(c, start, curr, this.mCurrentSize, max);
/* 1060 */         start += max;
/* 1061 */         len -= max;
/*      */       } 
/* 1066 */       expand(len);
/* 1067 */       System.arraycopy(c, start, this.mCurrentSegment, 0, len);
/* 1068 */       this.mCurrentSize = len;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void append(String str) {
/* 1075 */     int len = str.length();
/* 1076 */     if (this.mInputStart >= 0)
/* 1077 */       unshare(len); 
/* 1079 */     this.mResultString = null;
/* 1080 */     this.mResultArray = null;
/* 1083 */     char[] curr = this.mCurrentSegment;
/* 1084 */     int max = curr.length - this.mCurrentSize;
/* 1085 */     if (max >= len) {
/* 1086 */       str.getChars(0, len, curr, this.mCurrentSize);
/* 1087 */       this.mCurrentSize += len;
/*      */     } else {
/* 1090 */       if (max > 0) {
/* 1091 */         str.getChars(0, max, curr, this.mCurrentSize);
/* 1092 */         len -= max;
/*      */       } 
/* 1097 */       expand(len);
/* 1098 */       str.getChars(max, max + len, this.mCurrentSegment, 0);
/* 1099 */       this.mCurrentSize = len;
/*      */     } 
/*      */   }
/*      */   
/*      */   public char[] getCurrentSegment() {
/* 1115 */     if (this.mInputStart >= 0) {
/* 1116 */       unshare(1);
/*      */     } else {
/* 1118 */       char[] curr = this.mCurrentSegment;
/* 1119 */       if (curr == null) {
/* 1120 */         this.mCurrentSegment = allocBuffer(0);
/* 1121 */       } else if (this.mCurrentSize >= curr.length) {
/* 1123 */         expand(1);
/*      */       } 
/*      */     } 
/* 1126 */     return this.mCurrentSegment;
/*      */   }
/*      */   
/*      */   public int getCurrentSegmentSize() {
/* 1130 */     return this.mCurrentSize;
/*      */   }
/*      */   
/*      */   public void setCurrentLength(int len) {
/* 1134 */     this.mCurrentSize = len;
/*      */   }
/*      */   
/*      */   public char[] finishCurrentSegment() {
/* 1139 */     if (this.mSegments == null)
/* 1140 */       this.mSegments = new ArrayList(); 
/* 1142 */     this.mHasSegments = true;
/* 1143 */     this.mSegments.add(this.mCurrentSegment);
/* 1144 */     int oldLen = this.mCurrentSegment.length;
/* 1145 */     this.mSegmentSize += oldLen;
/* 1146 */     char[] curr = new char[calcNewSize(oldLen)];
/* 1147 */     this.mCurrentSize = 0;
/* 1148 */     this.mCurrentSegment = curr;
/* 1149 */     return curr;
/*      */   }
/*      */   
/*      */   private int calcNewSize(int latestSize) {
/* 1159 */     int incr = (latestSize < 8000) ? latestSize : (latestSize >> 1);
/* 1160 */     int size = latestSize + incr;
/* 1162 */     return Math.min(size, 262144);
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1177 */     return contentsAsString();
/*      */   }
/*      */   
/*      */   public void unshare(int needExtra) {
/* 1192 */     int len = this.mInputLen;
/* 1193 */     this.mInputLen = 0;
/* 1194 */     char[] inputBuf = this.mInputBuffer;
/* 1195 */     this.mInputBuffer = null;
/* 1196 */     int start = this.mInputStart;
/* 1197 */     this.mInputStart = -1;
/* 1200 */     int needed = len + needExtra;
/* 1201 */     if (this.mCurrentSegment == null || needed > this.mCurrentSegment.length)
/* 1202 */       this.mCurrentSegment = allocBuffer(needed); 
/* 1204 */     if (len > 0)
/* 1205 */       System.arraycopy(inputBuf, start, this.mCurrentSegment, 0, len); 
/* 1207 */     this.mSegmentSize = 0;
/* 1208 */     this.mCurrentSize = len;
/*      */   }
/*      */   
/*      */   private void expand(int roomNeeded) {
/* 1221 */     if (this.mSegments == null)
/* 1222 */       this.mSegments = new ArrayList(); 
/* 1224 */     char[] curr = this.mCurrentSegment;
/* 1225 */     this.mHasSegments = true;
/* 1226 */     this.mSegments.add(curr);
/* 1227 */     int oldLen = curr.length;
/* 1228 */     this.mSegmentSize += oldLen;
/* 1229 */     int newSize = Math.max(roomNeeded, calcNewSize(oldLen));
/* 1230 */     curr = new char[newSize];
/* 1231 */     this.mCurrentSize = 0;
/* 1232 */     this.mCurrentSegment = curr;
/*      */   }
/*      */   
/*      */   private char[] buildResultArray() {
/*      */     char[] result;
/* 1237 */     if (this.mResultString != null)
/* 1238 */       return this.mResultString.toCharArray(); 
/* 1243 */     if (this.mInputStart >= 0) {
/* 1244 */       if (this.mInputLen < 1)
/* 1245 */         return DataUtil.getEmptyCharArray(); 
/* 1247 */       result = new char[this.mInputLen];
/* 1248 */       System.arraycopy(this.mInputBuffer, this.mInputStart, result, 0, this.mInputLen);
/*      */     } else {
/* 1251 */       int size = size();
/* 1252 */       if (size < 1)
/* 1253 */         return DataUtil.getEmptyCharArray(); 
/* 1255 */       int offset = 0;
/* 1256 */       result = new char[size];
/* 1257 */       if (this.mSegments != null)
/* 1258 */         for (int i = 0, len = this.mSegments.size(); i < len; i++) {
/* 1259 */           char[] curr = this.mSegments.get(i);
/* 1260 */           int currLen = curr.length;
/* 1261 */           System.arraycopy(curr, 0, result, offset, currLen);
/* 1262 */           offset += currLen;
/*      */         }  
/* 1265 */       System.arraycopy(this.mCurrentSegment, 0, result, offset, this.mCurrentSize);
/*      */     } 
/* 1267 */     return result;
/*      */   }
/*      */   
/*      */   private static final class BufferReader extends Reader {
/*      */     ArrayList _Segments;
/*      */     
/*      */     char[] _CurrentSegment;
/*      */     
/*      */     final int _CurrentLength;
/*      */     
/*      */     int _SegmentIndex;
/*      */     
/*      */     int _SegmentOffset;
/*      */     
/*      */     int _CurrentOffset;
/*      */     
/*      */     public BufferReader(ArrayList segs, char[] currSeg, int currSegLen) {
/* 1283 */       this._Segments = segs;
/* 1284 */       this._CurrentSegment = currSeg;
/* 1285 */       this._CurrentLength = currSegLen;
/* 1287 */       this._SegmentIndex = 0;
/* 1288 */       this._SegmentOffset = this._CurrentOffset = 0;
/*      */     }
/*      */     
/*      */     public void close() {
/* 1292 */       this._Segments = null;
/* 1293 */       this._CurrentSegment = null;
/*      */     }
/*      */     
/*      */     public void mark(int x) throws IOException {
/* 1299 */       throw new IOException("mark() not supported");
/*      */     }
/*      */     
/*      */     public boolean markSupported() {
/* 1303 */       return false;
/*      */     }
/*      */     
/*      */     public int read(char[] cbuf, int offset, int len) {
/* 1308 */       if (len < 1)
/* 1309 */         return 0; 
/* 1312 */       int origOffset = offset;
/* 1314 */       while (this._Segments != null) {
/* 1315 */         char[] curr = this._Segments.get(this._SegmentIndex);
/* 1316 */         int max = curr.length - this._SegmentOffset;
/* 1317 */         if (len <= max) {
/* 1318 */           System.arraycopy(curr, this._SegmentOffset, cbuf, offset, len);
/* 1319 */           this._SegmentOffset += len;
/* 1320 */           offset += len;
/* 1321 */           return offset - origOffset;
/*      */         } 
/* 1324 */         if (max > 0) {
/* 1325 */           System.arraycopy(curr, this._SegmentOffset, cbuf, offset, max);
/* 1326 */           offset += max;
/*      */         } 
/* 1328 */         if (++this._SegmentIndex >= this._Segments.size()) {
/* 1329 */           this._Segments = null;
/*      */           continue;
/*      */         } 
/* 1331 */         this._SegmentOffset = 0;
/*      */       } 
/* 1336 */       if (len > 0 && this._CurrentSegment != null) {
/* 1337 */         int max = this._CurrentLength - this._CurrentOffset;
/* 1338 */         if (len >= max) {
/* 1339 */           len = max;
/* 1340 */           System.arraycopy(this._CurrentSegment, this._CurrentOffset, cbuf, offset, len);
/* 1342 */           this._CurrentSegment = null;
/*      */         } else {
/* 1344 */           System.arraycopy(this._CurrentSegment, this._CurrentOffset, cbuf, offset, len);
/* 1346 */           this._CurrentOffset += len;
/*      */         } 
/* 1348 */         offset += len;
/*      */       } 
/* 1351 */       return (origOffset == offset) ? -1 : (offset - origOffset);
/*      */     }
/*      */     
/*      */     public boolean ready() {
/* 1355 */       return true;
/*      */     }
/*      */     
/*      */     public void reset() throws IOException {
/* 1361 */       throw new IOException("reset() not supported");
/*      */     }
/*      */     
/*      */     public long skip(long amount) {
/* 1369 */       if (amount < 0L)
/* 1370 */         return 0L; 
/* 1373 */       long origAmount = amount;
/* 1375 */       while (this._Segments != null) {
/* 1376 */         char[] curr = this._Segments.get(this._SegmentIndex);
/* 1377 */         int max = curr.length - this._SegmentOffset;
/* 1378 */         if (max >= amount) {
/* 1379 */           this._SegmentOffset += (int)amount;
/* 1380 */           return origAmount;
/*      */         } 
/* 1383 */         amount -= max;
/* 1384 */         if (++this._SegmentIndex >= this._Segments.size()) {
/* 1385 */           this._Segments = null;
/*      */           continue;
/*      */         } 
/* 1387 */         this._SegmentOffset = 0;
/*      */       } 
/* 1392 */       if (amount > 0L && this._CurrentSegment != null) {
/* 1393 */         int max = this._CurrentLength - this._CurrentOffset;
/* 1394 */         if (amount >= max) {
/* 1395 */           amount -= max;
/* 1396 */           this._CurrentSegment = null;
/*      */         } else {
/* 1398 */           amount = 0L;
/* 1399 */           this._CurrentOffset += (int)amount;
/*      */         } 
/*      */       } 
/* 1403 */       return (amount == origAmount) ? -1L : (origAmount - amount);
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wst\\util\TextBuffer.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */