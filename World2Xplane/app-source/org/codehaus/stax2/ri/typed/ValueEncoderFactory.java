/*     */ package org.codehaus.stax2.ri.typed;
/*     */ 
/*     */ import org.codehaus.stax2.typed.Base64Variant;
/*     */ 
/*     */ public final class ValueEncoderFactory {
/*     */   static final byte BYTE_SPACE = 32;
/*     */   
/*  39 */   protected TokenEncoder _tokenEncoder = null;
/*     */   
/*  40 */   protected IntEncoder _intEncoder = null;
/*     */   
/*  41 */   protected LongEncoder _longEncoder = null;
/*     */   
/*  42 */   protected FloatEncoder _floatEncoder = null;
/*     */   
/*  43 */   protected DoubleEncoder _doubleEncoder = null;
/*     */   
/*     */   public ScalarEncoder getScalarEncoder(String value) {
/*  52 */     if (value.length() > 64) {
/*  53 */       if (this._tokenEncoder == null)
/*  54 */         this._tokenEncoder = new TokenEncoder(); 
/*  56 */       this._tokenEncoder.reset(value);
/*  57 */       return this._tokenEncoder;
/*     */     } 
/*  60 */     return new StringEncoder(value);
/*     */   }
/*     */   
/*     */   public ScalarEncoder getEncoder(boolean value) {
/*  66 */     return getScalarEncoder(value ? "true" : "false");
/*     */   }
/*     */   
/*     */   public IntEncoder getEncoder(int value) {
/*  71 */     if (this._intEncoder == null)
/*  72 */       this._intEncoder = new IntEncoder(); 
/*  74 */     this._intEncoder.reset(value);
/*  75 */     return this._intEncoder;
/*     */   }
/*     */   
/*     */   public LongEncoder getEncoder(long value) {
/*  80 */     if (this._longEncoder == null)
/*  81 */       this._longEncoder = new LongEncoder(); 
/*  83 */     this._longEncoder.reset(value);
/*  84 */     return this._longEncoder;
/*     */   }
/*     */   
/*     */   public FloatEncoder getEncoder(float value) {
/*  89 */     if (this._floatEncoder == null)
/*  90 */       this._floatEncoder = new FloatEncoder(); 
/*  92 */     this._floatEncoder.reset(value);
/*  93 */     return this._floatEncoder;
/*     */   }
/*     */   
/*     */   public DoubleEncoder getEncoder(double value) {
/*  98 */     if (this._doubleEncoder == null)
/*  99 */       this._doubleEncoder = new DoubleEncoder(); 
/* 101 */     this._doubleEncoder.reset(value);
/* 102 */     return this._doubleEncoder;
/*     */   }
/*     */   
/*     */   public IntArrayEncoder getEncoder(int[] values, int from, int length) {
/* 109 */     return new IntArrayEncoder(values, from, from + length);
/*     */   }
/*     */   
/*     */   public LongArrayEncoder getEncoder(long[] values, int from, int length) {
/* 114 */     return new LongArrayEncoder(values, from, from + length);
/*     */   }
/*     */   
/*     */   public FloatArrayEncoder getEncoder(float[] values, int from, int length) {
/* 119 */     return new FloatArrayEncoder(values, from, from + length);
/*     */   }
/*     */   
/*     */   public DoubleArrayEncoder getEncoder(double[] values, int from, int length) {
/* 124 */     return new DoubleArrayEncoder(values, from, from + length);
/*     */   }
/*     */   
/*     */   public Base64Encoder getEncoder(Base64Variant v, byte[] data, int from, int length) {
/* 131 */     return new Base64Encoder(v, data, from, from + length);
/*     */   }
/*     */   
/*     */   static abstract class ScalarEncoder extends AsciiValueEncoder {}
/*     */   
/*     */   static final class TokenEncoder extends ScalarEncoder {
/*     */     String _value;
/*     */     
/*     */     protected void reset(String value) {
/* 168 */       this._value = value;
/*     */     }
/*     */     
/*     */     public boolean isCompleted() {
/* 171 */       return (this._value == null);
/*     */     }
/*     */     
/*     */     public int encodeMore(char[] buffer, int ptr, int end) {
/* 175 */       String str = this._value;
/* 176 */       this._value = null;
/* 177 */       int len = str.length();
/* 178 */       str.getChars(0, len, buffer, ptr);
/* 179 */       ptr += len;
/* 180 */       return ptr;
/*     */     }
/*     */     
/*     */     public int encodeMore(byte[] buffer, int ptr, int end) {
/* 185 */       String str = this._value;
/* 186 */       this._value = null;
/* 187 */       int len = str.length();
/* 188 */       for (int i = 0; i < len; i++)
/* 189 */         buffer[ptr++] = (byte)str.charAt(i); 
/* 191 */       return ptr;
/*     */     }
/*     */   }
/*     */   
/*     */   static final class StringEncoder extends ScalarEncoder {
/*     */     String _value;
/*     */     
/*     */     int _offset;
/*     */     
/*     */     protected StringEncoder(String value) {
/* 214 */       this._value = value;
/*     */     }
/*     */     
/*     */     public boolean isCompleted() {
/* 217 */       return (this._value == null);
/*     */     }
/*     */     
/*     */     public int encodeMore(char[] buffer, int ptr, int end) {
/* 221 */       int left = this._value.length() - this._offset;
/* 222 */       int free = end - ptr;
/* 223 */       if (free >= left) {
/* 224 */         this._value.getChars(this._offset, left, buffer, ptr);
/* 225 */         this._value = null;
/* 226 */         return ptr + left;
/*     */       } 
/* 228 */       this._value.getChars(this._offset, free, buffer, ptr);
/* 229 */       this._offset += free;
/* 230 */       return end;
/*     */     }
/*     */     
/*     */     public int encodeMore(byte[] buffer, int ptr, int end) {
/* 235 */       int left = this._value.length() - this._offset;
/* 236 */       int free = end - ptr;
/* 237 */       if (free >= left) {
/* 238 */         String str = this._value;
/* 239 */         this._value = null;
/* 240 */         for (int last = str.length(), offset = this._offset; offset < last; offset++)
/* 241 */           buffer[ptr++] = (byte)str.charAt(offset); 
/* 243 */         return ptr;
/*     */       } 
/* 245 */       for (; ptr < end; ptr++)
/* 246 */         buffer[ptr] = (byte)this._value.charAt(this._offset++); 
/* 248 */       return ptr;
/*     */     }
/*     */   }
/*     */   
/*     */   static abstract class TypedScalarEncoder extends ScalarEncoder {
/*     */     public final boolean isCompleted() {
/* 265 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   static final class IntEncoder extends TypedScalarEncoder {
/*     */     int _value;
/*     */     
/*     */     protected void reset(int value) {
/* 276 */       this._value = value;
/*     */     }
/*     */     
/*     */     public int encodeMore(char[] buffer, int ptr, int end) {
/* 281 */       return NumberUtil.writeInt(this._value, buffer, ptr);
/*     */     }
/*     */     
/*     */     public int encodeMore(byte[] buffer, int ptr, int end) {
/* 286 */       return NumberUtil.writeInt(this._value, buffer, ptr);
/*     */     }
/*     */   }
/*     */   
/*     */   static final class LongEncoder extends TypedScalarEncoder {
/*     */     long _value;
/*     */     
/*     */     protected void reset(long value) {
/* 298 */       this._value = value;
/*     */     }
/*     */     
/*     */     public int encodeMore(char[] buffer, int ptr, int end) {
/* 303 */       return NumberUtil.writeLong(this._value, buffer, ptr);
/*     */     }
/*     */     
/*     */     public int encodeMore(byte[] buffer, int ptr, int end) {
/* 308 */       return NumberUtil.writeLong(this._value, buffer, ptr);
/*     */     }
/*     */   }
/*     */   
/*     */   static final class FloatEncoder extends TypedScalarEncoder {
/*     */     float _value;
/*     */     
/*     */     protected void reset(float value) {
/* 320 */       this._value = value;
/*     */     }
/*     */     
/*     */     public int encodeMore(char[] buffer, int ptr, int end) {
/* 325 */       return NumberUtil.writeFloat(this._value, buffer, ptr);
/*     */     }
/*     */     
/*     */     public int encodeMore(byte[] buffer, int ptr, int end) {
/* 330 */       return NumberUtil.writeFloat(this._value, buffer, ptr);
/*     */     }
/*     */   }
/*     */   
/*     */   static final class DoubleEncoder extends TypedScalarEncoder {
/*     */     double _value;
/*     */     
/*     */     protected void reset(double value) {
/* 342 */       this._value = value;
/*     */     }
/*     */     
/*     */     public int encodeMore(char[] buffer, int ptr, int end) {
/* 347 */       return NumberUtil.writeDouble(this._value, buffer, ptr);
/*     */     }
/*     */     
/*     */     public int encodeMore(byte[] buffer, int ptr, int end) {
/* 352 */       return NumberUtil.writeDouble(this._value, buffer, ptr);
/*     */     }
/*     */   }
/*     */   
/*     */   static abstract class ArrayEncoder extends AsciiValueEncoder {
/*     */     int _ptr;
/*     */     
/*     */     final int _end;
/*     */     
/*     */     protected ArrayEncoder(int ptr, int end) {
/* 374 */       this._ptr = ptr;
/* 375 */       this._end = end;
/*     */     }
/*     */     
/*     */     public final boolean isCompleted() {
/* 378 */       return (this._ptr >= this._end);
/*     */     }
/*     */     
/*     */     public abstract int encodeMore(char[] param1ArrayOfchar, int param1Int1, int param1Int2);
/*     */   }
/*     */   
/*     */   static final class IntArrayEncoder extends ArrayEncoder {
/*     */     final int[] _values;
/*     */     
/*     */     protected IntArrayEncoder(int[] values, int from, int length) {
/* 393 */       super(from, length);
/* 394 */       this._values = values;
/*     */     }
/*     */     
/*     */     public int encodeMore(char[] buffer, int ptr, int end) {
/* 399 */       int lastOk = end - 12;
/* 400 */       while (ptr <= lastOk && this._ptr < this._end) {
/* 401 */         buffer[ptr++] = ' ';
/* 402 */         ptr = NumberUtil.writeInt(this._values[this._ptr++], buffer, ptr);
/*     */       } 
/* 404 */       return ptr;
/*     */     }
/*     */     
/*     */     public int encodeMore(byte[] buffer, int ptr, int end) {
/* 409 */       int lastOk = end - 12;
/* 410 */       while (ptr <= lastOk && this._ptr < this._end) {
/* 411 */         buffer[ptr++] = 32;
/* 412 */         ptr = NumberUtil.writeInt(this._values[this._ptr++], buffer, ptr);
/*     */       } 
/* 414 */       return ptr;
/*     */     }
/*     */   }
/*     */   
/*     */   static final class LongArrayEncoder extends ArrayEncoder {
/*     */     final long[] _values;
/*     */     
/*     */     protected LongArrayEncoder(long[] values, int from, int length) {
/* 425 */       super(from, length);
/* 426 */       this._values = values;
/*     */     }
/*     */     
/*     */     public int encodeMore(char[] buffer, int ptr, int end) {
/* 431 */       int lastOk = end - 22;
/* 432 */       while (ptr <= lastOk && this._ptr < this._end) {
/* 433 */         buffer[ptr++] = ' ';
/* 434 */         ptr = NumberUtil.writeLong(this._values[this._ptr++], buffer, ptr);
/*     */       } 
/* 436 */       return ptr;
/*     */     }
/*     */     
/*     */     public int encodeMore(byte[] buffer, int ptr, int end) {
/* 441 */       int lastOk = end - 22;
/* 442 */       while (ptr <= lastOk && this._ptr < this._end) {
/* 443 */         buffer[ptr++] = 32;
/* 444 */         ptr = NumberUtil.writeLong(this._values[this._ptr++], buffer, ptr);
/*     */       } 
/* 446 */       return ptr;
/*     */     }
/*     */   }
/*     */   
/*     */   static final class FloatArrayEncoder extends ArrayEncoder {
/*     */     final float[] _values;
/*     */     
/*     */     protected FloatArrayEncoder(float[] values, int from, int length) {
/* 457 */       super(from, length);
/* 458 */       this._values = values;
/*     */     }
/*     */     
/*     */     public int encodeMore(char[] buffer, int ptr, int end) {
/* 463 */       int lastOk = end - 33;
/* 464 */       while (ptr <= lastOk && this._ptr < this._end) {
/* 465 */         buffer[ptr++] = ' ';
/* 466 */         ptr = NumberUtil.writeFloat(this._values[this._ptr++], buffer, ptr);
/*     */       } 
/* 468 */       return ptr;
/*     */     }
/*     */     
/*     */     public int encodeMore(byte[] buffer, int ptr, int end) {
/* 473 */       int lastOk = end - 33;
/* 474 */       while (ptr <= lastOk && this._ptr < this._end) {
/* 475 */         buffer[ptr++] = 32;
/* 476 */         ptr = NumberUtil.writeFloat(this._values[this._ptr++], buffer, ptr);
/*     */       } 
/* 478 */       return ptr;
/*     */     }
/*     */   }
/*     */   
/*     */   static final class DoubleArrayEncoder extends ArrayEncoder {
/*     */     final double[] _values;
/*     */     
/*     */     protected DoubleArrayEncoder(double[] values, int from, int length) {
/* 489 */       super(from, length);
/* 490 */       this._values = values;
/*     */     }
/*     */     
/*     */     public int encodeMore(char[] buffer, int ptr, int end) {
/* 495 */       int lastOk = end - 33;
/* 496 */       while (ptr <= lastOk && this._ptr < this._end) {
/* 497 */         buffer[ptr++] = ' ';
/* 498 */         ptr = NumberUtil.writeDouble(this._values[this._ptr++], buffer, ptr);
/*     */       } 
/* 500 */       return ptr;
/*     */     }
/*     */     
/*     */     public int encodeMore(byte[] buffer, int ptr, int end) {
/* 505 */       int lastOk = end - 33;
/* 506 */       while (ptr <= lastOk && this._ptr < this._end) {
/* 507 */         buffer[ptr++] = 32;
/* 508 */         ptr = NumberUtil.writeDouble(this._values[this._ptr++], buffer, ptr);
/*     */       } 
/* 510 */       return ptr;
/*     */     }
/*     */   }
/*     */   
/*     */   static final class Base64Encoder extends AsciiValueEncoder {
/*     */     static final char PAD_CHAR = '=';
/*     */     
/*     */     static final byte PAD_BYTE = 61;
/*     */     
/*     */     static final byte LF_CHAR = 10;
/*     */     
/*     */     static final byte LF_BYTE = 10;
/*     */     
/*     */     final Base64Variant _variant;
/*     */     
/*     */     final byte[] _input;
/*     */     
/*     */     int _inputPtr;
/*     */     
/*     */     final int _inputEnd;
/*     */     
/*     */     int _chunksBeforeLf;
/*     */     
/*     */     protected Base64Encoder(Base64Variant v, byte[] values, int from, int end) {
/* 548 */       this._variant = v;
/* 549 */       this._input = values;
/* 550 */       this._inputPtr = from;
/* 551 */       this._inputEnd = end;
/* 552 */       this._chunksBeforeLf = this._variant.getMaxLineLength() >> 2;
/*     */     }
/*     */     
/*     */     public boolean isCompleted() {
/* 555 */       return (this._inputPtr >= this._inputEnd);
/*     */     }
/*     */     
/*     */     public int encodeMore(char[] buffer, int outPtr, int outEnd) {
/* 560 */       int inEnd = this._inputEnd - 3;
/* 562 */       outEnd -= 5;
/* 564 */       while (this._inputPtr <= inEnd) {
/* 565 */         if (outPtr > outEnd)
/* 566 */           return outPtr; 
/* 569 */         int b24 = this._input[this._inputPtr++] << 8;
/* 570 */         b24 |= this._input[this._inputPtr++] & 0xFF;
/* 571 */         b24 = b24 << 8 | this._input[this._inputPtr++] & 0xFF;
/* 572 */         outPtr = this._variant.encodeBase64Chunk(b24, buffer, outPtr);
/* 574 */         if (--this._chunksBeforeLf <= 0) {
/* 575 */           buffer[outPtr++] = '\n';
/* 576 */           this._chunksBeforeLf = this._variant.getMaxLineLength() >> 2;
/*     */         } 
/*     */       } 
/* 580 */       int inputLeft = this._inputEnd - this._inputPtr;
/* 581 */       if (inputLeft > 0 && 
/* 582 */         outPtr <= outEnd) {
/* 583 */         int b24 = this._input[this._inputPtr++] << 16;
/* 584 */         if (inputLeft == 2)
/* 585 */           b24 |= (this._input[this._inputPtr++] & 0xFF) << 8; 
/* 587 */         outPtr = this._variant.encodeBase64Partial(b24, inputLeft, buffer, outPtr);
/*     */       } 
/* 590 */       return outPtr;
/*     */     }
/*     */     
/*     */     public int encodeMore(byte[] buffer, int outPtr, int outEnd) {
/* 595 */       int inEnd = this._inputEnd - 3;
/* 596 */       outEnd -= 5;
/* 598 */       while (this._inputPtr <= inEnd) {
/* 599 */         if (outPtr > outEnd)
/* 600 */           return outPtr; 
/* 603 */         int b24 = this._input[this._inputPtr++] << 8;
/* 604 */         b24 |= this._input[this._inputPtr++] & 0xFF;
/* 605 */         b24 = b24 << 8 | this._input[this._inputPtr++] & 0xFF;
/* 606 */         outPtr = this._variant.encodeBase64Chunk(b24, buffer, outPtr);
/* 608 */         if (--this._chunksBeforeLf <= 0) {
/* 609 */           buffer[outPtr++] = 10;
/* 610 */           this._chunksBeforeLf = this._variant.getMaxLineLength() >> 2;
/*     */         } 
/*     */       } 
/* 614 */       int inputLeft = this._inputEnd - this._inputPtr;
/* 615 */       if (inputLeft > 0 && 
/* 616 */         outPtr <= outEnd) {
/* 617 */         int b24 = this._input[this._inputPtr++] << 16;
/* 618 */         if (inputLeft == 2)
/* 619 */           b24 |= (this._input[this._inputPtr++] & 0xFF) << 8; 
/* 621 */         outPtr = this._variant.encodeBase64Partial(b24, inputLeft, buffer, outPtr);
/*     */       } 
/* 624 */       return outPtr;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\typed\ValueEncoderFactory.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */