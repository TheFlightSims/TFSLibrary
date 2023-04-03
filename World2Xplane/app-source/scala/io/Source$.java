/*     */ package scala.io;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.InputStream;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ import scala.Function0;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class Source$ {
/*     */   public static final Source$ MODULE$;
/*     */   
/*     */   private final int DefaultBufSize;
/*     */   
/*     */   private Source$() {
/*  21 */     MODULE$ = this;
/*  22 */     this.DefaultBufSize = 2048;
/*     */   }
/*     */   
/*     */   public int DefaultBufSize() {
/*  22 */     return this.DefaultBufSize;
/*     */   }
/*     */   
/*     */   public BufferedSource stdin() {
/*  26 */     return fromInputStream(System.in, Codec$.MODULE$.fallbackSystemCodec());
/*     */   }
/*     */   
/*     */   public Source fromIterable(Iterable iterable) {
/*  35 */     return (new Source$$anon$1(iterable)).withReset((Function0<Source>)new Source$$anonfun$fromIterable$1(iterable));
/*     */   }
/*     */   
/*     */   public static class Source$$anon$1 extends Source {
/*     */     private final Iterator<Object> iter;
/*     */     
/*     */     public Source$$anon$1(Iterable iterable$1) {
/*     */       this.iter = iterable$1.iterator();
/*     */     }
/*     */     
/*     */     public Iterator<Object> iter() {
/*     */       return this.iter;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Source$$anonfun$fromIterable$1 extends AbstractFunction0<Source> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Iterable iterable$1;
/*     */     
/*     */     public final Source apply() {
/*  35 */       return Source$.MODULE$.fromIterable(this.iterable$1);
/*     */     }
/*     */     
/*     */     public Source$$anonfun$fromIterable$1(Iterable iterable$1) {}
/*     */   }
/*     */   
/*     */   public Source fromChar(char c) {
/*  39 */     return fromIterable((Iterable<Object>)scala.Predef$.MODULE$.wrapCharArray(new char[] { c }));
/*     */   }
/*     */   
/*     */   public Source fromChars(char[] chars) {
/*  43 */     return fromIterable((Iterable<Object>)scala.Predef$.MODULE$.wrapCharArray(chars));
/*     */   }
/*     */   
/*     */   public Source fromString(String s) {
/*  47 */     return fromIterable((Iterable<Object>)scala.Predef$.MODULE$.wrapString(s));
/*     */   }
/*     */   
/*     */   public BufferedSource fromFile(String name, Codec codec) {
/*  53 */     return fromFile(new File(name), codec);
/*     */   }
/*     */   
/*     */   public BufferedSource fromFile(String name, String enc) {
/*  59 */     return fromFile(name, Codec$.MODULE$.apply(enc));
/*     */   }
/*     */   
/*     */   public BufferedSource fromFile(URI uri, Codec codec) {
/*  64 */     return fromFile(new File(uri), codec);
/*     */   }
/*     */   
/*     */   public BufferedSource fromFile(URI uri, String enc) {
/*  69 */     return fromFile(uri, Codec$.MODULE$.apply(enc));
/*     */   }
/*     */   
/*     */   public BufferedSource fromFile(File file, Codec codec) {
/*  75 */     return fromFile(file, DefaultBufSize(), codec);
/*     */   }
/*     */   
/*     */   public BufferedSource fromFile(File file, String enc) {
/*  80 */     return fromFile(file, Codec$.MODULE$.apply(enc));
/*     */   }
/*     */   
/*     */   public BufferedSource fromFile(File file, String enc, int bufferSize) {
/*  83 */     return fromFile(file, bufferSize, Codec$.MODULE$.apply(enc));
/*     */   }
/*     */   
/*     */   public BufferedSource fromFile(File file, int bufferSize, Codec codec) {
/*  90 */     FileInputStream inputStream = new FileInputStream(file);
/*  92 */     return (BufferedSource)
/*     */       
/*  97 */       createBufferedSource(inputStream, bufferSize, (Function0<Source>)new Source$$anonfun$fromFile$2(file, bufferSize, codec), (Function0<BoxedUnit>)new Source$$anonfun$fromFile$1(inputStream), codec).withDescription((new StringBuilder()).append("file:").append(file.getAbsolutePath()).toString());
/*     */   }
/*     */   
/*     */   public static class Source$$anonfun$fromFile$2 extends AbstractFunction0<BufferedSource> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final File file$1;
/*     */     
/*     */     private final int bufferSize$1;
/*     */     
/*     */     private final Codec codec$2;
/*     */     
/*     */     public final BufferedSource apply() {
/*     */       return Source$.MODULE$.fromFile(this.file$1, this.bufferSize$1, this.codec$2);
/*     */     }
/*     */     
/*     */     public Source$$anonfun$fromFile$2(File file$1, int bufferSize$1, Codec codec$2) {}
/*     */   }
/*     */   
/*     */   public static class Source$$anonfun$fromFile$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final FileInputStream inputStream$1;
/*     */     
/*     */     public final void apply() {
/*     */       this.inputStream$1.close();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/*     */       this.inputStream$1.close();
/*     */     }
/*     */     
/*     */     public Source$$anonfun$fromFile$1(FileInputStream inputStream$1) {}
/*     */   }
/*     */   
/*     */   public Source fromBytes(byte[] bytes, Codec codec) {
/* 106 */     return fromString(new String(bytes, codec.name()));
/*     */   }
/*     */   
/*     */   public Source fromBytes(byte[] bytes, String enc) {
/* 109 */     return fromBytes(bytes, Codec$.MODULE$.apply(enc));
/*     */   }
/*     */   
/*     */   public Source fromRawBytes(byte[] bytes) {
/* 115 */     return fromString(new String(bytes, Codec$.MODULE$.ISO8859().name()));
/*     */   }
/*     */   
/*     */   public BufferedSource fromURI(URI uri, Codec codec) {
/* 120 */     return fromFile(new File(uri), codec);
/*     */   }
/*     */   
/*     */   public BufferedSource fromURL(String s, String enc) {
/* 125 */     return fromURL(s, Codec$.MODULE$.apply(enc));
/*     */   }
/*     */   
/*     */   public BufferedSource fromURL(String s, Codec codec) {
/* 130 */     return fromURL(new URL(s), codec);
/*     */   }
/*     */   
/*     */   public BufferedSource fromURL(URL url, String enc) {
/* 135 */     return fromURL(url, Codec$.MODULE$.apply(enc));
/*     */   }
/*     */   
/*     */   public BufferedSource fromURL(URL url, Codec codec) {
/* 140 */     return fromInputStream(url.openStream(), codec);
/*     */   }
/*     */   
/*     */   public int createBufferedSource$default$2() {
/* 154 */     return DefaultBufSize();
/*     */   }
/*     */   
/*     */   public BufferedSource createBufferedSource(InputStream inputStream, int bufferSize, Function0 reset, Function0<BoxedUnit> close, Codec codec) {
/* 159 */     Function0<Source> resetFn = (reset == null) ? (Function0)new Source$$anonfun$2(inputStream, bufferSize, reset, close, codec) : reset;
/* 161 */     return (BufferedSource)(new BufferedSource(inputStream, bufferSize, codec)).withReset(resetFn).withClose(close);
/*     */   }
/*     */   
/*     */   public static class Source$$anonfun$2 extends AbstractFunction0<BufferedSource> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final InputStream inputStream$2;
/*     */     
/*     */     private final int bufferSize$2;
/*     */     
/*     */     private final Function0 reset$1;
/*     */     
/*     */     private final Function0 close$1;
/*     */     
/*     */     private final Codec codec$3;
/*     */     
/*     */     public final BufferedSource apply() {
/*     */       return Source$.MODULE$.createBufferedSource(this.inputStream$2, this.bufferSize$2, this.reset$1, this.close$1, this.codec$3);
/*     */     }
/*     */     
/*     */     public Source$$anonfun$2(InputStream inputStream$2, int bufferSize$2, Function0 reset$1, Function0 close$1, Codec codec$3) {}
/*     */   }
/*     */   
/*     */   public BufferedSource fromInputStream(InputStream is, String enc) {
/* 165 */     return fromInputStream(is, Codec$.MODULE$.apply(enc));
/*     */   }
/*     */   
/*     */   public BufferedSource fromInputStream(InputStream is, Codec codec) {
/* 168 */     Source$$anonfun$3 source$$anonfun$3 = new Source$$anonfun$3(is, codec);
/* 168 */     Source$$anonfun$1 source$$anonfun$1 = new Source$$anonfun$1(is);
/* 168 */     int x$4 = createBufferedSource$default$2();
/* 168 */     return createBufferedSource(is, x$4, (Function0<Source>)source$$anonfun$3, (Function0<BoxedUnit>)source$$anonfun$1, codec);
/*     */   }
/*     */   
/*     */   public Function0<Source> createBufferedSource$default$3() {
/*     */     return null;
/*     */   }
/*     */   
/*     */   public Function0<BoxedUnit> createBufferedSource$default$4() {
/*     */     return null;
/*     */   }
/*     */   
/*     */   public static class Source$$anonfun$3 extends AbstractFunction0<BufferedSource> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final InputStream is$1;
/*     */     
/*     */     private final Codec codec$1;
/*     */     
/*     */     public final BufferedSource apply() {
/* 168 */       return Source$.MODULE$.fromInputStream(this.is$1, this.codec$1);
/*     */     }
/*     */     
/*     */     public Source$$anonfun$3(InputStream is$1, Codec codec$1) {}
/*     */   }
/*     */   
/*     */   public static class Source$$anonfun$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final InputStream is$1;
/*     */     
/*     */     public final void apply() {
/* 168 */       this.is$1.close();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/* 168 */       this.is$1.close();
/*     */     }
/*     */     
/*     */     public Source$$anonfun$1(InputStream is$1) {}
/*     */   }
/*     */   
/*     */   public class Source$$anonfun$spaces$1 extends AbstractFunction0.mcC.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final char apply() {
/* 290 */       return ' ';
/*     */     }
/*     */     
/*     */     public char apply$mcC$sp() {
/* 290 */       return ' ';
/*     */     }
/*     */     
/*     */     public Source$$anonfun$spaces$1(Source $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\io\Source$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */