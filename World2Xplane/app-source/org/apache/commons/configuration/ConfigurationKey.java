/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class ConfigurationKey implements Serializable {
/*     */   public static final char PROPERTY_DELIMITER = '.';
/*     */   
/*  47 */   public static final String ESCAPED_DELIMITER = String.valueOf('.') + String.valueOf('.');
/*     */   
/*     */   private static final String ATTRIBUTE_START = "[@";
/*     */   
/*     */   private static final String ATTRIBUTE_END = "]";
/*     */   
/*     */   private static final char INDEX_START = '(';
/*     */   
/*     */   private static final char INDEX_END = ')';
/*     */   
/*     */   private static final int INITIAL_SIZE = 32;
/*     */   
/*     */   private static final long serialVersionUID = -4299732083605277656L;
/*     */   
/*     */   private StringBuffer keyBuffer;
/*     */   
/*     */   public ConfigurationKey() {
/*  78 */     this.keyBuffer = new StringBuffer(32);
/*     */   }
/*     */   
/*     */   public ConfigurationKey(String key) {
/*  89 */     this.keyBuffer = new StringBuffer(key);
/*  90 */     removeTrailingDelimiter();
/*     */   }
/*     */   
/*     */   public ConfigurationKey append(String property) {
/* 102 */     if (this.keyBuffer.length() > 0 && !hasDelimiter() && !isAttributeKey(property))
/* 104 */       this.keyBuffer.append('.'); 
/* 107 */     this.keyBuffer.append(property);
/* 108 */     removeTrailingDelimiter();
/* 109 */     return this;
/*     */   }
/*     */   
/*     */   public ConfigurationKey appendIndex(int index) {
/* 120 */     this.keyBuffer.append('(').append(index);
/* 121 */     this.keyBuffer.append(')');
/* 122 */     return this;
/*     */   }
/*     */   
/*     */   public ConfigurationKey appendAttribute(String attr) {
/* 133 */     this.keyBuffer.append(constructAttributeKey(attr));
/* 134 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isAttributeKey() {
/* 144 */     return isAttributeKey(this.keyBuffer.toString());
/*     */   }
/*     */   
/*     */   public static boolean isAttributeKey(String key) {
/* 157 */     return (key != null && key.startsWith("[@") && key.endsWith("]"));
/*     */   }
/*     */   
/*     */   public static String constructAttributeKey(String key) {
/* 171 */     StringBuffer buf = new StringBuffer();
/* 172 */     buf.append("[@").append(key).append("]");
/* 173 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public static String attributeName(String key) {
/* 186 */     return isAttributeKey(key) ? removeAttributeMarkers(key) : key;
/*     */   }
/*     */   
/*     */   static String removeAttributeMarkers(String key) {
/* 197 */     return key.substring("[@".length(), key.length() - "]".length());
/*     */   }
/*     */   
/*     */   private boolean hasDelimiter() {
/* 208 */     int count = 0;
/* 209 */     int idx = this.keyBuffer.length() - 1;
/* 210 */     for (; idx >= 0 && this.keyBuffer.charAt(idx) == '.'; idx--)
/* 212 */       count++; 
/* 214 */     return (count % 2 != 0);
/*     */   }
/*     */   
/*     */   private void removeTrailingDelimiter() {
/* 222 */     while (hasDelimiter())
/* 224 */       this.keyBuffer.deleteCharAt(this.keyBuffer.length() - 1); 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 236 */     return this.keyBuffer.toString();
/*     */   }
/*     */   
/*     */   public KeyIterator iterator() {
/* 247 */     return new KeyIterator();
/*     */   }
/*     */   
/*     */   public int length() {
/* 257 */     return this.keyBuffer.length();
/*     */   }
/*     */   
/*     */   public void setLength(int len) {
/* 270 */     this.keyBuffer.setLength(len);
/*     */   }
/*     */   
/*     */   public boolean equals(Object c) {
/* 282 */     if (c == null)
/* 284 */       return false; 
/* 287 */     return this.keyBuffer.toString().equals(c.toString());
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 297 */     return String.valueOf(this.keyBuffer).hashCode();
/*     */   }
/*     */   
/*     */   public ConfigurationKey commonKey(ConfigurationKey other) {
/* 309 */     if (other == null)
/* 311 */       throw new IllegalArgumentException("Other key must no be null!"); 
/* 314 */     ConfigurationKey result = new ConfigurationKey();
/* 315 */     KeyIterator it1 = iterator();
/* 316 */     KeyIterator it2 = other.iterator();
/* 318 */     while (it1.hasNext() && it2.hasNext() && partsEqual(it1, it2)) {
/* 320 */       if (it1.isAttribute()) {
/* 322 */         result.appendAttribute(it1.currentKey());
/*     */         continue;
/*     */       } 
/* 326 */       result.append(it1.currentKey());
/* 327 */       if (it1.hasIndex)
/* 329 */         result.appendIndex(it1.getIndex()); 
/*     */     } 
/* 334 */     return result;
/*     */   }
/*     */   
/*     */   public ConfigurationKey differenceKey(ConfigurationKey other) {
/* 349 */     ConfigurationKey common = commonKey(other);
/* 350 */     ConfigurationKey result = new ConfigurationKey();
/* 352 */     if (common.length() < other.length()) {
/* 354 */       String k = other.toString().substring(common.length());
/* 356 */       int i = 0;
/* 357 */       while (i < k.length() && k.charAt(i) == '.')
/* 359 */         i++; 
/* 362 */       if (i < k.length())
/* 364 */         result.append(k.substring(i)); 
/*     */     } 
/* 368 */     return result;
/*     */   }
/*     */   
/*     */   private static boolean partsEqual(KeyIterator it1, KeyIterator it2) {
/* 380 */     return (it1.nextKey().equals(it2.nextKey()) && it1.getIndex() == it2.getIndex() && it1.isAttribute() == it2.isAttribute());
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
/*     */     private final ConfigurationKey this$0;
/*     */     
/*     */     private String findNextIndices() {
/* 417 */       this.startIndex = this.endIndex;
/* 420 */       while (this.startIndex < ConfigurationKey.this.keyBuffer.length() && ConfigurationKey.this.keyBuffer.charAt(this.startIndex) == '.')
/* 422 */         this.startIndex++; 
/* 426 */       if (this.startIndex >= ConfigurationKey.this.keyBuffer.length()) {
/* 428 */         this.endIndex = ConfigurationKey.this.keyBuffer.length();
/* 429 */         this.startIndex = this.endIndex - 1;
/* 430 */         return ConfigurationKey.this.keyBuffer.substring(this.startIndex, this.endIndex);
/*     */       } 
/* 434 */       return nextKeyPart();
/*     */     }
/*     */     
/*     */     private String nextKeyPart() {
/* 446 */       StringBuffer key = new StringBuffer(32);
/* 447 */       int idx = this.startIndex;
/* 448 */       int endIdx = ConfigurationKey.this.keyBuffer.toString().indexOf("[@", this.startIndex);
/* 450 */       if (endIdx < 0 || endIdx == this.startIndex)
/* 452 */         endIdx = ConfigurationKey.this.keyBuffer.length(); 
/* 454 */       boolean found = false;
/* 456 */       while (!found && idx < endIdx) {
/* 458 */         char c = ConfigurationKey.this.keyBuffer.charAt(idx);
/* 459 */         if (c == '.')
/* 462 */           if (idx == endIdx - 1 || ConfigurationKey.this.keyBuffer.charAt(idx + 1) != '.') {
/* 465 */             found = true;
/*     */           } else {
/* 469 */             idx++;
/*     */           }  
/* 472 */         if (!found) {
/* 474 */           key.append(c);
/* 475 */           idx++;
/*     */         } 
/*     */       } 
/* 479 */       this.endIndex = idx;
/* 480 */       return key.toString();
/*     */     }
/*     */     
/*     */     public String nextKey() {
/* 491 */       return nextKey(false);
/*     */     }
/*     */     
/*     */     public String nextKey(boolean decorated) {
/* 506 */       if (!hasNext())
/* 508 */         throw new NoSuchElementException("No more key parts!"); 
/* 511 */       this.hasIndex = false;
/* 512 */       this.indexValue = -1;
/* 513 */       String key = findNextIndices();
/* 515 */       this.current = key;
/* 516 */       this.hasIndex = checkIndex(key);
/* 517 */       this.attribute = checkAttribute(this.current);
/* 519 */       return currentKey(decorated);
/*     */     }
/*     */     
/*     */     private boolean checkAttribute(String key) {
/* 531 */       if (ConfigurationKey.isAttributeKey(key)) {
/* 533 */         this.current = ConfigurationKey.removeAttributeMarkers(key);
/* 534 */         return true;
/*     */       } 
/* 538 */       return false;
/*     */     }
/*     */     
/*     */     private boolean checkIndex(String key) {
/* 551 */       boolean result = false;
/* 553 */       int idx = key.lastIndexOf('(');
/* 554 */       if (idx > 0) {
/* 556 */         int endidx = key.indexOf(')', idx);
/* 558 */         if (endidx > idx + 1) {
/* 560 */           this.indexValue = Integer.parseInt(key.substring(idx + 1, endidx));
/* 561 */           this.current = key.substring(0, idx);
/* 562 */           result = true;
/*     */         } 
/*     */       } 
/* 566 */       return result;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 576 */       return (this.endIndex < ConfigurationKey.this.keyBuffer.length());
/*     */     }
/*     */     
/*     */     public Object next() {
/* 586 */       return nextKey();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 595 */       throw new UnsupportedOperationException("Remove not supported!");
/*     */     }
/*     */     
/*     */     public String currentKey() {
/* 607 */       return currentKey(false);
/*     */     }
/*     */     
/*     */     public String currentKey(boolean decorated) {
/* 622 */       return (decorated && isAttribute()) ? ConfigurationKey.constructAttributeKey(this.current) : this.current;
/*     */     }
/*     */     
/*     */     public boolean isAttribute() {
/* 633 */       return this.attribute;
/*     */     }
/*     */     
/*     */     public int getIndex() {
/* 645 */       return this.indexValue;
/*     */     }
/*     */     
/*     */     public boolean hasIndex() {
/* 656 */       return this.hasIndex;
/*     */     }
/*     */     
/*     */     public Object clone() {
/*     */       try {
/* 668 */         return super.clone();
/* 670 */       } catch (CloneNotSupportedException cex) {
/* 673 */         return null;
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\ConfigurationKey.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */