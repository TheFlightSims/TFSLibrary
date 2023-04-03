/*     */ package scala;
/*     */ 
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.StringOps$;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.IntRef;
/*     */ import scala.runtime.ObjectRef;
/*     */ import scala.runtime.VolatileByteRef;
/*     */ 
/*     */ public final class StringContext$ implements Serializable {
/*     */   public static final StringContext$ MODULE$;
/*     */   
/*     */   public StringContext apply(Seq<String> parts) {
/*  51 */     return new StringContext(parts);
/*     */   }
/*     */   
/*     */   public Option<Seq<String>> unapplySeq(StringContext x$0) {
/*  51 */     return (x$0 == null) ? None$.MODULE$ : new Some<Seq<String>>(x$0.parts());
/*     */   }
/*     */   
/*     */   public class StringContext$$anonfun$s$1 extends AbstractFunction1<String, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(String str) {
/*  90 */       return StringContext$.MODULE$.treatEscapes(str);
/*     */     }
/*     */     
/*     */     public StringContext$$anonfun$s$1(StringContext $outer) {}
/*     */   }
/*     */   
/*     */   public class StringContext$$anonfun$raw$1 extends AbstractFunction1<String, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(String x) {
/* 114 */       return Predef$.MODULE$.<String>identity(x);
/*     */     }
/*     */     
/*     */     public StringContext$$anonfun$raw$1(StringContext $outer) {}
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 168 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private StringContext$() {
/* 168 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public String treatEscapes(String str) {
/* 188 */     ObjectRef bldr$lzy = new ObjectRef(null);
/* 188 */     VolatileByteRef bitmap$0 = new VolatileByteRef((byte)0);
/* 189 */     int len = str.length();
/* 190 */     IntRef start = new IntRef(0);
/* 191 */     IntRef cur = new IntRef(0);
/* 192 */     IntRef idx = new IntRef(0);
/* 198 */     while (idx.elem < len) {
/* 199 */       cur.elem = idx.elem;
/* 200 */       Predef$ predef$ = Predef$.MODULE$;
/* 200 */       if (StringOps$.MODULE$.apply$extension(str, idx.elem) == '\\') {
/* 201 */         idx.elem++;
/* 202 */         if (idx.elem >= len)
/* 202 */           throw new StringContext.InvalidEscapeException(str, cur.elem); 
/* 203 */         Predef$ predef$1 = Predef$.MODULE$, predef$2 = Predef$.MODULE$;
/* 203 */         if ('0' <= StringOps$.MODULE$.apply$extension(str, idx.elem) && StringOps$.MODULE$.apply$extension(str, idx.elem) <= '7') {
/* 204 */           Predef$ predef$4 = Predef$.MODULE$;
/* 204 */           char leadch = StringOps$.MODULE$.apply$extension(str, idx.elem);
/* 205 */           int oct = leadch - 48;
/* 206 */           idx.elem++;
/* 207 */           Predef$ predef$5 = Predef$.MODULE$, predef$6 = Predef$.MODULE$;
/* 207 */           if (idx.elem < len && '0' <= StringOps$.MODULE$.apply$extension(str, idx.elem) && StringOps$.MODULE$.apply$extension(str, idx.elem) <= '7') {
/* 208 */             Predef$ predef$7 = Predef$.MODULE$;
/* 208 */             oct = oct * 8 + StringOps$.MODULE$.apply$extension(str, idx.elem) - 48;
/* 209 */             idx.elem++;
/* 210 */             Predef$ predef$8 = Predef$.MODULE$, predef$9 = Predef$.MODULE$;
/* 210 */             if (idx.elem < len && leadch <= '3' && '0' <= StringOps$.MODULE$.apply$extension(str, idx.elem) && StringOps$.MODULE$.apply$extension(str, idx.elem) <= '7') {
/* 211 */               Predef$ predef$10 = Predef$.MODULE$;
/* 211 */               oct = oct * 8 + StringOps$.MODULE$.apply$extension(str, idx.elem) - 48;
/* 212 */               idx.elem++;
/*     */             } 
/*     */           } 
/* 215 */           output$1((char)oct, str, bldr$lzy, start, cur, idx, bitmap$0);
/*     */           continue;
/*     */         } 
/* 217 */         Predef$ predef$3 = Predef$.MODULE$;
/* 217 */         char ch = StringOps$.MODULE$.apply$extension(str, idx.elem);
/* 218 */         idx.elem++;
/* 220 */         switch (ch) {
/*     */           default:
/* 229 */             throw new StringContext.InvalidEscapeException(str, cur.elem);
/*     */           case '\\':
/*     */           
/*     */           case '\'':
/*     */           
/*     */           case '"':
/*     */           
/*     */           case 'r':
/*     */           
/*     */           case 'f':
/*     */           
/*     */           case 'n':
/*     */           
/*     */           case 't':
/*     */           
/*     */           case 'b':
/*     */             break;
/*     */         } 
/*     */         output$1('\b', str, bldr$lzy, start, cur, idx, bitmap$0);
/*     */         continue;
/*     */       } 
/* 234 */       idx.elem++;
/*     */     } 
/* 237 */     return (start.elem == 0) ? str : 
/* 238 */       bldr$1(bldr$lzy, bitmap$0).append(str, start.elem, idx.elem).toString();
/*     */   }
/*     */   
/*     */   private final StringBuilder bldr$lzycompute$1(ObjectRef bldr$lzy$1, VolatileByteRef bitmap$0$1) {
/*     */     synchronized (this) {
/*     */       if ((byte)(bitmap$0$1.elem & 0x1) == 0) {
/*     */         bldr$lzy$1.elem = new StringBuilder();
/*     */         bitmap$0$1.elem = (byte)(bitmap$0$1.elem | 0x1);
/*     */       } 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/StringContext$}} */
/*     */       return (StringBuilder)bldr$lzy$1.elem;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final StringBuilder bldr$1(ObjectRef bldr$lzy$1, VolatileByteRef bitmap$0$1) {
/*     */     return ((byte)(bitmap$0$1.elem & 0x1) == 0) ? bldr$lzycompute$1(bldr$lzy$1, bitmap$0$1) : (StringBuilder)bldr$lzy$1.elem;
/*     */   }
/*     */   
/*     */   private final void output$1(char ch, String str$1, ObjectRef bldr$lzy$1, IntRef start$1, IntRef cur$1, IntRef idx$1, VolatileByteRef bitmap$0$1) {
/*     */     bldr$1(bldr$lzy$1, bitmap$0$1).append(str$1, start$1.elem, cur$1.elem);
/*     */     bldr$1(bldr$lzy$1, bitmap$0$1).append(ch);
/*     */     start$1.elem = idx$1.elem;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\StringContext$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */