/*     */ package org.apache.commons.configuration.tree;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class DefaultConfigurationKey {
/*     */   private static final int INITIAL_SIZE = 32;
/*     */   
/*     */   private DefaultExpressionEngine expressionEngine;
/*     */   
/*     */   private StringBuffer keyBuffer;
/*     */   
/*     */   public DefaultConfigurationKey(DefaultExpressionEngine engine) {
/*  71 */     this.keyBuffer = new StringBuffer(32);
/*  72 */     setExpressionEngine(engine);
/*     */   }
/*     */   
/*     */   public DefaultConfigurationKey(DefaultExpressionEngine engine, String key) {
/*  84 */     setExpressionEngine(engine);
/*  85 */     this.keyBuffer = new StringBuffer(trim(key));
/*     */   }
/*     */   
/*     */   public DefaultExpressionEngine getExpressionEngine() {
/*  95 */     return this.expressionEngine;
/*     */   }
/*     */   
/*     */   public void setExpressionEngine(DefaultExpressionEngine expressionEngine) {
/* 105 */     if (expressionEngine == null)
/* 107 */       throw new IllegalArgumentException("Expression engine must not be null!"); 
/* 110 */     this.expressionEngine = expressionEngine;
/*     */   }
/*     */   
/*     */   public DefaultConfigurationKey append(String property, boolean escape) {
/* 126 */     if (escape && property != null) {
/* 128 */       key = escapeDelimiters(property);
/*     */     } else {
/* 132 */       key = property;
/*     */     } 
/* 134 */     String key = trim(key);
/* 136 */     if (this.keyBuffer.length() > 0 && !isAttributeKey(property) && key.length() > 0)
/* 139 */       this.keyBuffer.append(getExpressionEngine().getPropertyDelimiter()); 
/* 142 */     this.keyBuffer.append(key);
/* 143 */     return this;
/*     */   }
/*     */   
/*     */   public DefaultConfigurationKey append(String property) {
/* 156 */     return append(property, false);
/*     */   }
/*     */   
/*     */   public DefaultConfigurationKey appendIndex(int index) {
/* 167 */     this.keyBuffer.append(getExpressionEngine().getIndexStart());
/* 168 */     this.keyBuffer.append(index);
/* 169 */     this.keyBuffer.append(getExpressionEngine().getIndexEnd());
/* 170 */     return this;
/*     */   }
/*     */   
/*     */   public DefaultConfigurationKey appendAttribute(String attr) {
/* 181 */     this.keyBuffer.append(constructAttributeKey(attr));
/* 182 */     return this;
/*     */   }
/*     */   
/*     */   public int length() {
/* 192 */     return this.keyBuffer.length();
/*     */   }
/*     */   
/*     */   public void setLength(int len) {
/* 205 */     this.keyBuffer.setLength(len);
/*     */   }
/*     */   
/*     */   public boolean equals(Object c) {
/* 217 */     if (c == null)
/* 219 */       return false; 
/* 222 */     return this.keyBuffer.toString().equals(c.toString());
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 232 */     return String.valueOf(this.keyBuffer).hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 243 */     return this.keyBuffer.toString();
/*     */   }
/*     */   
/*     */   public boolean isAttributeKey(String key) {
/* 255 */     if (key == null)
/* 257 */       return false; 
/* 260 */     return (key.startsWith(getExpressionEngine().getAttributeStart()) && (getExpressionEngine().getAttributeEnd() == null || key.endsWith(getExpressionEngine().getAttributeEnd())));
/*     */   }
/*     */   
/*     */   public String constructAttributeKey(String key) {
/* 275 */     if (key == null)
/* 277 */       return ""; 
/* 279 */     if (isAttributeKey(key))
/* 281 */       return key; 
/* 285 */     StringBuffer buf = new StringBuffer();
/* 286 */     buf.append(getExpressionEngine().getAttributeStart()).append(key);
/* 287 */     if (getExpressionEngine().getAttributeEnd() != null)
/* 289 */       buf.append(getExpressionEngine().getAttributeEnd()); 
/* 291 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public String attributeName(String key) {
/* 304 */     return isAttributeKey(key) ? removeAttributeMarkers(key) : key;
/*     */   }
/*     */   
/*     */   public String trimLeft(String key) {
/* 315 */     if (key == null)
/* 317 */       return ""; 
/* 321 */     String result = key;
/* 322 */     while (hasLeadingDelimiter(result))
/* 324 */       result = result.substring(getExpressionEngine().getPropertyDelimiter().length()); 
/* 327 */     return result;
/*     */   }
/*     */   
/*     */   public String trimRight(String key) {
/* 339 */     if (key == null)
/* 341 */       return ""; 
/* 345 */     String result = key;
/* 346 */     while (hasTrailingDelimiter(result))
/* 348 */       result = result.substring(0, result.length() - getExpressionEngine().getPropertyDelimiter().length()); 
/* 353 */     return result;
/*     */   }
/*     */   
/*     */   public String trim(String key) {
/* 365 */     return trimRight(trimLeft(key));
/*     */   }
/*     */   
/*     */   public KeyIterator iterator() {
/* 376 */     return new KeyIterator();
/*     */   }
/*     */   
/*     */   private boolean hasTrailingDelimiter(String key) {
/* 388 */     return (key.endsWith(getExpressionEngine().getPropertyDelimiter()) && (getExpressionEngine().getEscapedDelimiter() == null || !key.endsWith(getExpressionEngine().getEscapedDelimiter())));
/*     */   }
/*     */   
/*     */   private boolean hasLeadingDelimiter(String key) {
/* 402 */     return (key.startsWith(getExpressionEngine().getPropertyDelimiter()) && (getExpressionEngine().getEscapedDelimiter() == null || !key.startsWith(getExpressionEngine().getEscapedDelimiter())));
/*     */   }
/*     */   
/*     */   private String removeAttributeMarkers(String key) {
/* 415 */     return key.substring(getExpressionEngine().getAttributeStart().length(), key.length() - ((getExpressionEngine().getAttributeEnd() != null) ? getExpressionEngine().getAttributeEnd().length() : 0));
/*     */   }
/*     */   
/*     */   private String unescapeDelimiters(String key) {
/* 432 */     return (getExpressionEngine().getEscapedDelimiter() == null) ? key : StringUtils.replace(key, getExpressionEngine().getEscapedDelimiter(), getExpressionEngine().getPropertyDelimiter());
/*     */   }
/*     */   
/*     */   private String escapeDelimiters(String key) {
/* 446 */     return (getExpressionEngine().getEscapedDelimiter() == null || key.indexOf(getExpressionEngine().getPropertyDelimiter()) < 0) ? key : StringUtils.replace(key, getExpressionEngine().getPropertyDelimiter(), getExpressionEngine().getEscapedDelimiter());
/*     */   }
/*     */   
/*     */   public class KeyIterator implements Iterator, Cloneable {
/*     */     private String current;
/*     */     
/*     */     private int startIndex;
/*     */     
/*     */     private int endIndex;
/*     */     
/*     */     private int indexValue;
/*     */     
/*     */     private boolean hasIndex;
/*     */     
/*     */     private boolean attribute;
/*     */     
/*     */     private final DefaultConfigurationKey this$0;
/*     */     
/*     */     public String nextKey() {
/* 486 */       return nextKey(false);
/*     */     }
/*     */     
/*     */     public String nextKey(boolean decorated) {
/* 501 */       if (!hasNext())
/* 503 */         throw new NoSuchElementException("No more key parts!"); 
/* 506 */       this.hasIndex = false;
/* 507 */       this.indexValue = -1;
/* 508 */       String key = findNextIndices();
/* 510 */       this.current = key;
/* 511 */       this.hasIndex = checkIndex(key);
/* 512 */       this.attribute = checkAttribute(this.current);
/* 514 */       return currentKey(decorated);
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 524 */       return (this.endIndex < DefaultConfigurationKey.this.keyBuffer.length());
/*     */     }
/*     */     
/*     */     public Object next() {
/* 534 */       return nextKey();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 543 */       throw new UnsupportedOperationException("Remove not supported!");
/*     */     }
/*     */     
/*     */     public String currentKey() {
/* 555 */       return currentKey(false);
/*     */     }
/*     */     
/*     */     public String currentKey(boolean decorated) {
/* 570 */       return (decorated && !isPropertyKey()) ? DefaultConfigurationKey.this.constructAttributeKey(this.current) : this.current;
/*     */     }
/*     */     
/*     */     public boolean isAttribute() {
/* 584 */       return (this.attribute || (isAttributeEmulatingMode() && !hasNext()));
/*     */     }
/*     */     
/*     */     public boolean isPropertyKey() {
/* 599 */       return !this.attribute;
/*     */     }
/*     */     
/*     */     public int getIndex() {
/* 611 */       return this.indexValue;
/*     */     }
/*     */     
/*     */     public boolean hasIndex() {
/* 622 */       return this.hasIndex;
/*     */     }
/*     */     
/*     */     public Object clone() {
/*     */       try {
/* 634 */         return super.clone();
/* 636 */       } catch (CloneNotSupportedException cex) {
/* 639 */         return null;
/*     */       } 
/*     */     }
/*     */     
/*     */     private String findNextIndices() {
/* 650 */       this.startIndex = this.endIndex;
/* 653 */       while (this.startIndex < DefaultConfigurationKey.this.length() && DefaultConfigurationKey.this.hasLeadingDelimiter(DefaultConfigurationKey.this.keyBuffer.substring(this.startIndex)))
/* 655 */         this.startIndex += DefaultConfigurationKey.this.getExpressionEngine().getPropertyDelimiter().length(); 
/* 660 */       if (this.startIndex >= DefaultConfigurationKey.this.length()) {
/* 662 */         this.endIndex = DefaultConfigurationKey.this.length();
/* 663 */         this.startIndex = this.endIndex - 1;
/* 664 */         return DefaultConfigurationKey.this.keyBuffer.substring(this.startIndex, this.endIndex);
/*     */       } 
/* 668 */       return nextKeyPart();
/*     */     }
/*     */     
/*     */     private String nextKeyPart() {
/* 680 */       int attrIdx = DefaultConfigurationKey.this.keyBuffer.toString().indexOf(DefaultConfigurationKey.this.getExpressionEngine().getAttributeStart(), this.startIndex);
/* 682 */       if (attrIdx < 0 || attrIdx == this.startIndex)
/* 684 */         attrIdx = DefaultConfigurationKey.this.length(); 
/* 687 */       int delIdx = nextDelimiterPos(DefaultConfigurationKey.this.keyBuffer.toString(), this.startIndex, attrIdx);
/* 689 */       if (delIdx < 0)
/* 691 */         delIdx = attrIdx; 
/* 694 */       this.endIndex = Math.min(attrIdx, delIdx);
/* 695 */       return DefaultConfigurationKey.this.unescapeDelimiters(DefaultConfigurationKey.this.keyBuffer.substring(this.startIndex, this.endIndex));
/*     */     }
/*     */     
/*     */     private int nextDelimiterPos(String key, int pos, int endPos) {
/* 708 */       int delimiterPos = pos;
/* 709 */       boolean found = false;
/*     */       do {
/* 713 */         delimiterPos = key.indexOf(DefaultConfigurationKey.this.getExpressionEngine().getPropertyDelimiter(), delimiterPos);
/* 715 */         if (delimiterPos < 0 || delimiterPos >= endPos)
/* 717 */           return -1; 
/* 719 */         int escapePos = escapedPosition(key, delimiterPos);
/* 720 */         if (escapePos < 0) {
/* 722 */           found = true;
/*     */         } else {
/* 726 */           delimiterPos = escapePos;
/*     */         } 
/* 729 */       } while (!found);
/* 731 */       return delimiterPos;
/*     */     }
/*     */     
/*     */     private int escapedPosition(String key, int pos) {
/* 745 */       if (DefaultConfigurationKey.this.getExpressionEngine().getEscapedDelimiter() == null)
/* 748 */         return -1; 
/* 750 */       int escapeOffset = escapeOffset();
/* 751 */       if (escapeOffset < 0 || escapeOffset > pos)
/* 754 */         return -1; 
/* 757 */       int escapePos = key.indexOf(DefaultConfigurationKey.this.getExpressionEngine().getEscapedDelimiter(), pos - escapeOffset);
/* 759 */       if (escapePos <= pos && escapePos >= 0)
/* 763 */         return escapePos + DefaultConfigurationKey.this.getExpressionEngine().getEscapedDelimiter().length(); 
/* 768 */       return -1;
/*     */     }
/*     */     
/*     */     private int escapeOffset() {
/* 791 */       return DefaultConfigurationKey.this.getExpressionEngine().getEscapedDelimiter().indexOf(DefaultConfigurationKey.this.getExpressionEngine().getPropertyDelimiter());
/*     */     }
/*     */     
/*     */     private boolean checkAttribute(String key) {
/* 804 */       if (DefaultConfigurationKey.this.isAttributeKey(key)) {
/* 806 */         this.current = DefaultConfigurationKey.this.removeAttributeMarkers(key);
/* 807 */         return true;
/*     */       } 
/* 811 */       return false;
/*     */     }
/*     */     
/*     */     private boolean checkIndex(String key) {
/* 824 */       boolean result = false;
/*     */       try {
/* 828 */         int idx = key.lastIndexOf(DefaultConfigurationKey.this.getExpressionEngine().getIndexStart());
/* 829 */         if (idx > 0) {
/* 831 */           int endidx = key.indexOf(DefaultConfigurationKey.this.getExpressionEngine().getIndexEnd(), idx);
/* 834 */           if (endidx > idx + 1) {
/* 836 */             this.indexValue = Integer.parseInt(key.substring(idx + 1, endidx));
/* 837 */             this.current = key.substring(0, idx);
/* 838 */             result = true;
/*     */           } 
/*     */         } 
/* 842 */       } catch (NumberFormatException nfe) {
/* 844 */         result = false;
/*     */       } 
/* 847 */       return result;
/*     */     }
/*     */     
/*     */     private boolean isAttributeEmulatingMode() {
/* 864 */       return (DefaultConfigurationKey.this.getExpressionEngine().getAttributeEnd() == null && StringUtils.equals(DefaultConfigurationKey.this.getExpressionEngine().getPropertyDelimiter(), DefaultConfigurationKey.this.getExpressionEngine().getAttributeStart()));
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\tree\DefaultConfigurationKey.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */