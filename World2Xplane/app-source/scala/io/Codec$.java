/*     */ package scala.io;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.CharBuffer;
/*     */ import java.nio.charset.CharacterCodingException;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.CharsetDecoder;
/*     */ import java.nio.charset.CharsetEncoder;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ 
/*     */ public final class Codec$ implements LowPriorityCodecImplicits {
/*     */   public static final Codec$ MODULE$;
/*     */   
/*     */   private final Codec ISO8859;
/*     */   
/*     */   private final Codec UTF8;
/*     */   
/*     */   public class $anonfun$1 extends AbstractFunction1<CharacterCodingException, scala.runtime.Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final scala.runtime.Nothing$ apply(CharacterCodingException e) {
/*  40 */       throw e;
/*     */     }
/*     */     
/*     */     public $anonfun$1(Codec $outer) {}
/*     */   }
/*     */   
/*     */   public class Codec$$anonfun$encoder$1 extends AbstractFunction1<CharsetEncoder, CharsetEncoder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final CharsetEncoder apply(CharsetEncoder x$1) {
/*  55 */       return x$1.onMalformedInput(this.$outer.scala$io$Codec$$_onMalformedInput);
/*     */     }
/*     */     
/*     */     public Codec$$anonfun$encoder$1(Codec $outer) {}
/*     */   }
/*     */   
/*     */   public class Codec$$anonfun$encoder$2 extends AbstractFunction1<CharsetEncoder, CharsetEncoder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final CharsetEncoder apply(CharsetEncoder x$2) {
/*  56 */       return x$2.onUnmappableCharacter(this.$outer.scala$io$Codec$$_onUnmappableCharacter);
/*     */     }
/*     */     
/*     */     public Codec$$anonfun$encoder$2(Codec $outer) {}
/*     */   }
/*     */   
/*     */   public class Codec$$anonfun$encoder$3 extends AbstractFunction1<CharsetEncoder, CharsetEncoder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final CharsetEncoder apply(CharsetEncoder x$3) {
/*  57 */       return x$3.replaceWith(this.$outer.scala$io$Codec$$_encodingReplacement);
/*     */     }
/*     */     
/*     */     public Codec$$anonfun$encoder$3(Codec $outer) {}
/*     */   }
/*     */   
/*     */   public class Codec$$anonfun$decoder$1 extends AbstractFunction1<CharsetDecoder, CharsetDecoder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final CharsetDecoder apply(CharsetDecoder x$4) {
/*  62 */       return x$4.onMalformedInput(this.$outer.scala$io$Codec$$_onMalformedInput);
/*     */     }
/*     */     
/*     */     public Codec$$anonfun$decoder$1(Codec $outer) {}
/*     */   }
/*     */   
/*     */   public class Codec$$anonfun$decoder$2 extends AbstractFunction1<CharsetDecoder, CharsetDecoder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final CharsetDecoder apply(CharsetDecoder x$5) {
/*  63 */       return x$5.onUnmappableCharacter(this.$outer.scala$io$Codec$$_onUnmappableCharacter);
/*     */     }
/*     */     
/*     */     public Codec$$anonfun$decoder$2(Codec $outer) {}
/*     */   }
/*     */   
/*     */   public class Codec$$anonfun$decoder$3 extends AbstractFunction1<CharsetDecoder, CharsetDecoder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final CharsetDecoder apply(CharsetDecoder x$6) {
/*  64 */       return x$6.replaceWith(this.$outer.scala$io$Codec$$_decodingReplacement);
/*     */     }
/*     */     
/*     */     public Codec$$anonfun$decoder$3(Codec $outer) {}
/*     */   }
/*     */   
/*     */   public class Codec$$anonfun$applyFunctions$1 extends AbstractFunction2<T, Tuple2<Function1<T, T>, Object>, T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final T apply(Object x, Tuple2 pair) {
/*  72 */       if (pair != null)
/*  72 */         return 
/*  73 */           pair._2$mcZ$sp() ? (T)((Function1)pair._1()).apply(x) : (T)x; 
/*     */       throw new MatchError(pair);
/*     */     }
/*     */     
/*     */     public Codec$$anonfun$applyFunctions$1(Codec $outer) {}
/*     */   }
/*     */   
/*     */   public Codec fallbackSystemCodec() {
/*  84 */     return LowPriorityCodecImplicits$class.fallbackSystemCodec(this);
/*     */   }
/*     */   
/*     */   private Codec$() {
/*  84 */     MODULE$ = this;
/*  84 */     LowPriorityCodecImplicits$class.$init$(this);
/*  85 */     this.ISO8859 = new Codec(Charset.forName("ISO-8859-1"));
/*  86 */     this.UTF8 = new Codec(Charset.forName("UTF-8"));
/*     */   }
/*     */   
/*     */   public final Codec ISO8859() {
/*     */     return this.ISO8859;
/*     */   }
/*     */   
/*     */   public final Codec UTF8() {
/*  86 */     return this.UTF8;
/*     */   }
/*     */   
/*     */   public Codec defaultCharsetCodec() {
/*  93 */     return apply(Charset.defaultCharset());
/*     */   }
/*     */   
/*     */   public Codec fileEncodingCodec() {
/*  94 */     return apply(scala.util.Properties$.MODULE$.encodingString());
/*     */   }
/*     */   
/*     */   public Codec default() {
/*  95 */     return defaultCharsetCodec();
/*     */   }
/*     */   
/*     */   public Codec apply(String encoding) {
/*  97 */     return new Codec(Charset.forName(encoding));
/*     */   }
/*     */   
/*     */   public Codec apply(Charset charSet) {
/*  98 */     return new Codec(charSet);
/*     */   }
/*     */   
/*     */   public Codec apply(CharsetDecoder decoder) {
/* 100 */     return new Codec$$anon$1(decoder, decoder);
/*     */   }
/*     */   
/*     */   public static class Codec$$anon$1 extends Codec {
/*     */     private final CharsetDecoder _decoder$1;
/*     */     
/*     */     public CharsetDecoder decoder() {
/* 101 */       return this._decoder$1;
/*     */     }
/*     */     
/*     */     public Codec$$anon$1(CharsetDecoder decoder$1, CharsetDecoder _decoder$1) {
/* 101 */       super(decoder$1.charset());
/*     */     }
/*     */   }
/*     */   
/*     */   public char[] fromUTF8(byte[] bytes) {
/* 105 */     return fromUTF8(bytes, 0, bytes.length);
/*     */   }
/*     */   
/*     */   public char[] fromUTF8(byte[] bytes, int offset, int len) {
/* 107 */     ByteBuffer bbuffer = ByteBuffer.wrap(bytes, offset, len);
/* 108 */     CharBuffer cbuffer = UTF8().charSet().decode(bbuffer);
/* 109 */     char[] chars = new char[cbuffer.remaining()];
/* 110 */     cbuffer.get(chars);
/* 112 */     return chars;
/*     */   }
/*     */   
/*     */   public byte[] toUTF8(CharSequence cs) {
/* 117 */     CharBuffer cbuffer = CharBuffer.wrap(cs, 0, cs.length());
/* 118 */     ByteBuffer bbuffer = UTF8().charSet().encode(cbuffer);
/* 119 */     byte[] bytes = new byte[bbuffer.remaining()];
/* 120 */     bbuffer.get(bytes);
/* 122 */     return bytes;
/*     */   }
/*     */   
/*     */   public byte[] toUTF8(char[] chars, int offset, int len) {
/* 125 */     CharBuffer cbuffer = CharBuffer.wrap(chars, offset, len);
/* 126 */     ByteBuffer bbuffer = UTF8().charSet().encode(cbuffer);
/* 127 */     byte[] bytes = new byte[bbuffer.remaining()];
/* 128 */     bbuffer.get(bytes);
/* 130 */     return bytes;
/*     */   }
/*     */   
/*     */   public Codec string2codec(String s) {
/* 133 */     return apply(s);
/*     */   }
/*     */   
/*     */   public Codec charset2codec(Charset c) {
/* 134 */     return apply(c);
/*     */   }
/*     */   
/*     */   public Codec decoder2codec(CharsetDecoder cd) {
/* 135 */     return apply(cd);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\io\Codec$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */