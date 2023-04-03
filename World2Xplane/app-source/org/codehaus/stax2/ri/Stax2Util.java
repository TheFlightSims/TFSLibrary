/*     */ package org.codehaus.stax2.ri;
/*     */ 
/*     */ import javax.xml.stream.XMLStreamConstants;
/*     */ 
/*     */ public final class Stax2Util implements XMLStreamConstants {
/*     */   public static String eventTypeDesc(int type) {
/*  31 */     switch (type) {
/*     */       case 1:
/*  33 */         return "START_ELEMENT";
/*     */       case 2:
/*  35 */         return "END_ELEMENT";
/*     */       case 7:
/*  37 */         return "START_DOCUMENT";
/*     */       case 8:
/*  39 */         return "END_DOCUMENT";
/*     */       case 4:
/*  42 */         return "CHARACTERS";
/*     */       case 12:
/*  44 */         return "CDATA";
/*     */       case 6:
/*  46 */         return "SPACE";
/*     */       case 5:
/*  49 */         return "COMMENT";
/*     */       case 3:
/*  51 */         return "PROCESSING_INSTRUCTION";
/*     */       case 11:
/*  53 */         return "DTD";
/*     */       case 9:
/*  55 */         return "ENTITY_REFERENCE";
/*     */     } 
/*  57 */     return "[" + type + "]";
/*     */   }
/*     */   
/*     */   public static String trimSpaces(String lexical) {
/*  68 */     int end = lexical.length();
/*  69 */     int start = 0;
/*     */     while (true) {
/*  72 */       if (start >= end)
/*  73 */         return null; 
/*  75 */       if (!_isSpace(lexical.charAt(start)))
/*     */         break; 
/*  78 */       start++;
/*     */     } 
/*  81 */     end--;
/*  82 */     if (!_isSpace(lexical.charAt(end)))
/*  83 */       return (start == 0) ? lexical : lexical.substring(start); 
/*  87 */     while (--end > start && _isSpace(lexical.charAt(end)));
/*  89 */     return lexical.substring(start, end + 1);
/*     */   }
/*     */   
/*     */   private static final boolean _isSpace(char c) {
/* 101 */     return (c <= ' ');
/*     */   }
/*     */   
/*     */   public static final class TextBuffer {
/* 110 */     private String mText = null;
/*     */     
/* 115 */     private StringBuffer mBuilder = null;
/*     */     
/*     */     public void reset() {
/* 121 */       this.mText = null;
/* 122 */       this.mBuilder = null;
/*     */     }
/*     */     
/*     */     public void append(String text) {
/* 127 */       int len = text.length();
/* 128 */       if (len > 0) {
/* 130 */         if (this.mText != null) {
/* 131 */           this.mBuilder = new StringBuffer(this.mText.length() + len);
/* 132 */           this.mBuilder.append(this.mText);
/* 133 */           this.mText = null;
/*     */         } 
/* 135 */         if (this.mBuilder != null) {
/* 136 */           this.mBuilder.append(text);
/*     */         } else {
/* 138 */           this.mText = text;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public String get() {
/* 145 */       if (this.mText != null)
/* 146 */         return this.mText; 
/* 148 */       if (this.mBuilder != null)
/* 149 */         return this.mBuilder.toString(); 
/* 151 */       return "";
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 154 */       return (this.mText == null && this.mBuilder == null);
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class ByteAggregator {
/* 163 */     private static final byte[] NO_BYTES = new byte[0];
/*     */     
/*     */     private static final int INITIAL_BLOCK_SIZE = 500;
/*     */     
/*     */     static final int DEFAULT_BLOCK_ARRAY_SIZE = 100;
/*     */     
/*     */     private byte[][] mBlocks;
/*     */     
/*     */     private int mBlockCount;
/*     */     
/*     */     private int mTotalLen;
/*     */     
/*     */     private byte[] mSpareBlock;
/*     */     
/*     */     public byte[] startAggregation() {
/* 199 */       this.mTotalLen = 0;
/* 200 */       this.mBlockCount = 0;
/* 201 */       byte[] result = this.mSpareBlock;
/* 202 */       if (result == null) {
/* 203 */         result = new byte[500];
/*     */       } else {
/* 205 */         this.mSpareBlock = null;
/*     */       } 
/* 207 */       return result;
/*     */     }
/*     */     
/*     */     public byte[] addFullBlock(byte[] block) {
/* 220 */       int blockLen = block.length;
/* 222 */       if (this.mBlocks == null) {
/* 223 */         this.mBlocks = new byte[100][];
/*     */       } else {
/* 225 */         int oldLen = this.mBlocks.length;
/* 226 */         if (this.mBlockCount >= oldLen) {
/* 227 */           byte[][] old = this.mBlocks;
/* 228 */           this.mBlocks = new byte[oldLen + oldLen][];
/* 229 */           System.arraycopy(old, 0, this.mBlocks, 0, oldLen);
/*     */         } 
/*     */       } 
/* 232 */       this.mBlocks[this.mBlockCount] = block;
/* 233 */       this.mBlockCount++;
/* 234 */       this.mTotalLen += blockLen;
/* 242 */       int newSize = Math.max(this.mTotalLen >> 1, 1000);
/* 243 */       return new byte[newSize];
/*     */     }
/*     */     
/*     */     public byte[] aggregateAll(byte[] lastBlock, int lastLen) {
/* 252 */       int totalLen = this.mTotalLen + lastLen;
/* 254 */       if (totalLen == 0)
/* 255 */         return NO_BYTES; 
/* 258 */       byte[] result = new byte[totalLen];
/* 259 */       int offset = 0;
/* 261 */       if (this.mBlocks != null)
/* 262 */         for (int i = 0; i < this.mBlockCount; i++) {
/* 263 */           byte[] block = this.mBlocks[i];
/* 264 */           int len = block.length;
/* 265 */           System.arraycopy(block, 0, result, offset, len);
/* 266 */           offset += len;
/*     */         }  
/* 269 */       System.arraycopy(lastBlock, 0, result, offset, lastLen);
/* 271 */       this.mSpareBlock = lastBlock;
/* 272 */       offset += lastLen;
/* 273 */       if (offset != totalLen)
/* 274 */         throw new RuntimeException("Internal error: total len assumed to be " + totalLen + ", copied " + offset + " bytes"); 
/* 276 */       return result;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\Stax2Util.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */