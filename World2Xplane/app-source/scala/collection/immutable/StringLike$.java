/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import scala.Serializable;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class StringLike$ {
/*     */   public static final StringLike$ MODULE$;
/*     */   
/*     */   private final char scala$collection$immutable$StringLike$$LF;
/*     */   
/*     */   private final char scala$collection$immutable$StringLike$$FF;
/*     */   
/*     */   private final char scala$collection$immutable$StringLike$$CR;
/*     */   
/*     */   private final char SU;
/*     */   
/*     */   private StringLike$() {
/*  21 */     MODULE$ = this;
/*  24 */     this.scala$collection$immutable$StringLike$$LF = '\n';
/*  25 */     this.scala$collection$immutable$StringLike$$FF = '\f';
/*  26 */     this.scala$collection$immutable$StringLike$$CR = '\r';
/*  27 */     this.SU = '\032';
/*     */   }
/*     */   
/*     */   public final char scala$collection$immutable$StringLike$$LF() {
/*     */     return this.scala$collection$immutable$StringLike$$LF;
/*     */   }
/*     */   
/*     */   public final char scala$collection$immutable$StringLike$$FF() {
/*     */     return this.scala$collection$immutable$StringLike$$FF;
/*     */   }
/*     */   
/*     */   public final char scala$collection$immutable$StringLike$$CR() {
/*     */     return this.scala$collection$immutable$StringLike$$CR;
/*     */   }
/*     */   
/*     */   private final char SU() {
/*  27 */     return this.SU;
/*     */   }
/*     */   
/*     */   public class StringLike$$anonfun$$times$1 extends AbstractFunction1<Object, StringBuilder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final StringBuilder buf$1;
/*     */     
/*     */     public final StringBuilder apply(int i) {
/*  71 */       return this.buf$1.append(this.$outer.toString());
/*     */     }
/*     */     
/*     */     public StringLike$$anonfun$$times$1(StringLike $outer, StringBuilder buf$1) {}
/*     */   }
/*     */   
/*     */   public class StringLike$$anon$1 extends AbstractIterator<String> {
/*     */     private final String str;
/*     */     
/*     */     private final int len;
/*     */     
/*     */     private int index;
/*     */     
/*     */     public StringLike$$anon$1(StringLike $outer) {
/* 111 */       this.str = $outer.toString();
/* 112 */       this.len = str().length();
/* 113 */       this.index = 0;
/*     */     }
/*     */     
/*     */     private String str() {
/*     */       return this.str;
/*     */     }
/*     */     
/*     */     private int len() {
/*     */       return this.len;
/*     */     }
/*     */     
/*     */     private int index() {
/* 113 */       return this.index;
/*     */     }
/*     */     
/*     */     private void index_$eq(int x$1) {
/* 113 */       this.index = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 114 */       return (index() < len());
/*     */     }
/*     */     
/*     */     public String next() {
/* 116 */       if (index() >= len())
/* 116 */         throw new NoSuchElementException("next on empty iterator"); 
/* 117 */       int start = index();
/* 118 */       for (; index() < len() && !StringLike$class.scala$collection$immutable$StringLike$$isLineBreak(this.$outer, this.$outer.apply(index())); index_$eq(index() + 1));
/* 119 */       index_$eq(index() + 1);
/* 120 */       int i = index();
/* 120 */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 120 */       return str().substring(start, scala.runtime.RichInt$.MODULE$.min$extension(i, len()));
/*     */     }
/*     */   }
/*     */   
/*     */   public class StringLike$$anonfun$lines$1 extends AbstractFunction1<String, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(String line) {
/* 129 */       return (new WrappedString(line)).stripLineEnd();
/*     */     }
/*     */     
/*     */     public StringLike$$anonfun$lines$1(StringLike $outer) {}
/*     */   }
/*     */   
/*     */   public class StringLike$$anonfun$linesIterator$1 extends AbstractFunction1<String, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(String line) {
/* 136 */       return (new WrappedString(line)).stripLineEnd();
/*     */     }
/*     */     
/*     */     public StringLike$$anonfun$linesIterator$1(StringLike $outer) {}
/*     */   }
/*     */   
/*     */   public class StringLike$$anonfun$stripMargin$1 extends AbstractFunction1<String, StringBuilder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final StringBuilder buf$2;
/*     */     
/*     */     private final char marginChar$1;
/*     */     
/*     */     public StringLike$$anonfun$stripMargin$1(StringLike $outer, StringBuilder buf$2, char marginChar$1) {}
/*     */     
/*     */     public final StringBuilder apply(String line) {
/* 182 */       int len = line.length();
/* 183 */       int index = 0;
/* 184 */       for (; index < len && line.charAt(index) <= ' '; index++);
/* 185 */       return this.buf$2.append((
/* 186 */           index < len && line.charAt(index) == this.marginChar$1) ? line.substring(index + 1) : line);
/*     */     }
/*     */   }
/*     */   
/*     */   public class StringLike$$anonfun$1 extends AbstractFunction2<String, Object, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(String x$1, char x$2) {
/* 205 */       return (new StringBuilder()).append(x$1).append(StringLike$class.scala$collection$immutable$StringLike$$escape(this.$outer, x$2)).toString();
/*     */     }
/*     */     
/*     */     public StringLike$$anonfun$1(StringLike $outer) {}
/*     */   }
/*     */   
/*     */   public class StringLike$$anonfun$format$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Object apply(Object arg) {
/* 266 */       return StringLike$class.scala$collection$immutable$StringLike$$unwrapArg(this.$outer, arg);
/*     */     }
/*     */     
/*     */     public StringLike$$anonfun$format$1(StringLike $outer) {}
/*     */   }
/*     */   
/*     */   public class StringLike$$anonfun$formatLocal$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Object apply(Object arg) {
/* 283 */       return StringLike$class.scala$collection$immutable$StringLike$$unwrapArg(this.$outer, arg);
/*     */     }
/*     */     
/*     */     public StringLike$$anonfun$formatLocal$1(StringLike $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\StringLike$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */