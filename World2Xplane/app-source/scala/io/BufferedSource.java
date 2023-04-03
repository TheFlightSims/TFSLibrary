/*    */ package scala.io;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.PushbackReader;
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Serializable;
/*    */ import scala.collection.AbstractIterator;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Iterator$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0055a\001B\001\003\001\035\021aBQ;gM\026\024X\rZ*pkJ\034WM\003\002\004\t\005\021\021n\034\006\002\013\005)1oY1mC\016\0011C\001\001\t!\tI!\"D\001\003\023\tY!A\001\004T_V\0248-\032\005\t\033\001\021\t\021)A\005\035\005Y\021N\0349viN#(/Z1n!\ty1#D\001\021\025\t\031\021CC\001\023\003\021Q\027M^1\n\005Q\001\"aC%oaV$8\013\036:fC6D\001B\006\001\003\002\003\006IaF\001\013EV4g-\032:TSj,\007C\001\r\032\033\005!\021B\001\016\005\005\rIe\016\036\005\t9\001\021)\031!C\002;\005)1m\0343fGV\ta\004\005\002\n?%\021\001E\001\002\006\007>$Wm\031\005\tE\001\021\t\021)A\005=\00511m\0343fG\002BQ\001\n\001\005\002\025\na\001P5oSRtDc\001\024*UQ\021q\005\013\t\003\023\001AQ\001H\022A\004yAQ!D\022A\0029AQAF\022A\002]AQ\001\n\001\005\0021\"\"!L\030\025\005\035r\003\"\002\017,\001\bq\002\"B\007,\001\004q\001\"B\031\001\t\003\021\024A\002:fC\022,'\017F\0014!\tyA'\003\0026!\t\t\022J\0349viN#(/Z1n%\026\fG-\032:\t\013]\002A\021\001\035\002\035\t,hMZ3sK\022\024V-\0313feR\t\021\b\005\002\020u%\0211\b\005\002\017\005V4g-\032:fIJ+\027\rZ3s\021\035i\004\0011A\005\ny\n\021c\0315beJ+\027\rZ3s\007J,\027\r^3e+\005y\004C\001\rA\023\t\tEAA\004C_>dW-\0318\t\017\r\003\001\031!C\005\t\006)2\r[1s%\026\fG-\032:De\026\fG/\0323`I\025\fHCA#I!\tAb)\003\002H\t\t!QK\\5u\021\035I%)!AA\002}\n1\001\037\0232\021\031Y\005\001)Q\005\005\0212\r[1s%\026\fG-\032:De\026\fG/\0323!\021!i\005\001#b\001\n\023q\025AC2iCJ\024V-\0313feV\t\021\b\003\005Q\001!\005\t\025)\003:\003-\031\007.\031:SK\006$WM\035\021\t\021I\003\001R1A\005BM\013A!\033;feV\tA\013E\002V1jk\021A\026\006\003/\022\t!bY8mY\026\034G/[8o\023\tIfK\001\005Ji\026\024\030\r^8s!\tA2,\003\002]\t\t!1\t[1s\021!q\006\001#A!B\023!\026!B5uKJ\004c\001\0021\001\001\005\024ACQ;gM\026\024X\r\032'j]\026LE/\032:bi>\0248cA0cYB\031QkY3\n\005\0214&\001E!cgR\024\030m\031;Ji\026\024\030\r^8s!\t1\027N\004\002\031O&\021\001\016B\001\007!J,G-\0324\n\005)\\'AB*ue&twM\003\002i\tA\031Q\013W3\t\013\021zF\021\0018\025\003=\004\"\001]0\016\003\001AqA]0C\002\023%a*\001\006mS:,'+Z1eKJDa\001^0!\002\023I\024a\0037j]\026\024V-\0313fe\002BqA^0A\002\023\005q/\001\005oKb$H*\0338f+\005)\007bB=`\001\004%\tA_\001\r]\026DH\017T5oK~#S-\035\013\003\013nDq!\023=\002\002\003\007Q\r\003\004~?\002\006K!Z\001\n]\026DH\017T5oK\002BQa`0\005By\nq\001[1t\035\026DH\017C\004\002\004}#\t%!\002\002\t9,\007\020\036\013\002K\"9\021\021\002\001\005B\005-\021\001C4fi2Kg.Z:\025\0031\004")
/*    */ public class BufferedSource extends Source {
/*    */   private final InputStream inputStream;
/*    */   
/*    */   public final int scala$io$BufferedSource$$bufferSize;
/*    */   
/*    */   private final Codec codec;
/*    */   
/*    */   public Codec codec() {
/* 20 */     return this.codec;
/*    */   }
/*    */   
/*    */   public BufferedSource(InputStream inputStream, Codec codec) {
/* 21 */     this(inputStream, Source$.MODULE$.DefaultBufSize(), codec);
/*    */   }
/*    */   
/*    */   public InputStreamReader reader() {
/* 22 */     return new InputStreamReader(this.inputStream, codec().decoder());
/*    */   }
/*    */   
/*    */   public BufferedReader bufferedReader() {
/* 23 */     return new BufferedReader(reader(), this.scala$io$BufferedSource$$bufferSize);
/*    */   }
/*    */   
/*    */   private boolean scala$io$BufferedSource$$charReaderCreated = false;
/*    */   
/*    */   private BufferedReader scala$io$BufferedSource$$charReader;
/*    */   
/*    */   private Iterator<Object> iter;
/*    */   
/*    */   private volatile byte bitmap$0;
/*    */   
/*    */   public boolean scala$io$BufferedSource$$charReaderCreated() {
/* 30 */     return this.scala$io$BufferedSource$$charReaderCreated;
/*    */   }
/*    */   
/*    */   private void scala$io$BufferedSource$$charReaderCreated_$eq(boolean x$1) {
/* 30 */     this.scala$io$BufferedSource$$charReaderCreated = x$1;
/*    */   }
/*    */   
/*    */   private BufferedReader scala$io$BufferedSource$$charReader$lzycompute() {
/* 31 */     synchronized (this) {
/* 31 */       if ((byte)(this.bitmap$0 & 0x1) == 
/*    */         
/* 42 */         0) {
/*    */         scala$io$BufferedSource$$charReaderCreated_$eq(true);
/*    */         this.scala$io$BufferedSource$$charReader = bufferedReader();
/*    */         this.bitmap$0 = (byte)(this.bitmap$0 | 0x1);
/*    */       } 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/io/BufferedSource}} */
/*    */       return this.scala$io$BufferedSource$$charReader;
/*    */     } 
/*    */   }
/*    */   
/*    */   public BufferedReader scala$io$BufferedSource$$charReader() {
/* 42 */     return ((byte)(this.bitmap$0 & 0x1) == 0) ? scala$io$BufferedSource$$charReader$lzycompute() : this.scala$io$BufferedSource$$charReader;
/*    */   }
/*    */   
/*    */   private Iterator iter$lzycompute() {
/*    */     synchronized (this) {
/* 42 */       if ((byte)(this.bitmap$0 & 0x2) == 0) {
/*    */         BufferedSource$$anonfun$iter$1 bufferedSource$$anonfun$iter$1 = new BufferedSource$$anonfun$iter$1(this);
/*    */         Iterator$ iterator$ = Iterator$.MODULE$;
/*    */         this.iter = (new Object((Function0)bufferedSource$$anonfun$iter$1)).takeWhile((Function1)new BufferedSource$$anonfun$iter$2(this)).map((Function1)new BufferedSource$$anonfun$iter$3(this));
/*    */         this.bitmap$0 = (byte)(this.bitmap$0 | 0x2);
/*    */       } 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/io/BufferedSource}} */
/*    */       return this.iter;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Iterator<Object> iter() {
/* 42 */     return ((byte)(this.bitmap$0 & 0x2) == 0) ? iter$lzycompute() : this.iter;
/*    */   }
/*    */   
/*    */   public class BufferedSource$$anonfun$iter$1 extends AbstractFunction0.mcI.sp implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final int apply() {
/*    */       return this.$outer.codec().wrap((Function0<Object>)new BufferedSource$$anonfun$iter$1$$anonfun$apply$mcI$sp$1(this));
/*    */     }
/*    */     
/*    */     public int apply$mcI$sp() {
/*    */       return this.$outer.codec().wrap((Function0<Object>)new BufferedSource$$anonfun$iter$1$$anonfun$apply$mcI$sp$1(this));
/*    */     }
/*    */     
/*    */     public BufferedSource$$anonfun$iter$1(BufferedSource $outer) {}
/*    */     
/*    */     public class BufferedSource$$anonfun$iter$1$$anonfun$apply$mcI$sp$1 extends AbstractFunction0.mcI.sp implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final int apply() {
/*    */         return this.$outer.$outer.scala$io$BufferedSource$$charReader().read();
/*    */       }
/*    */       
/*    */       public int apply$mcI$sp() {
/*    */         return this.$outer.$outer.scala$io$BufferedSource$$charReader().read();
/*    */       }
/*    */       
/*    */       public BufferedSource$$anonfun$iter$1$$anonfun$apply$mcI$sp$1(BufferedSource$$anonfun$iter$1 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class BufferedSource$$anonfun$iter$2 extends AbstractFunction1.mcZI.sp implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(int x$1) {
/*    */       return apply$mcZI$sp(x$1);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZI$sp(int x$1) {
/*    */       return (x$1 != -1);
/*    */     }
/*    */     
/*    */     public BufferedSource$$anonfun$iter$2(BufferedSource $outer) {}
/*    */   }
/*    */   
/*    */   public class BufferedSource$$anonfun$iter$3 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final char apply(int x$2) {
/*    */       return (char)x$2;
/*    */     }
/*    */     
/*    */     public BufferedSource$$anonfun$iter$3(BufferedSource $outer) {}
/*    */   }
/*    */   
/*    */   public class BufferedLineIterator extends AbstractIterator<String> implements Iterator<String> {
/*    */     private final BufferedReader lineReader;
/*    */     
/*    */     private String nextLine;
/*    */     
/*    */     public BufferedLineIterator(BufferedSource $outer) {
/* 57 */       PushbackReader pb = new PushbackReader($outer.scala$io$BufferedSource$$charReader());
/* 58 */       pb.unread(BoxesRunTime.unboxToChar($outer.iter().next()));
/* 59 */       this.lineReader = ($outer.scala$io$BufferedSource$$charReaderCreated() && $outer.iter().hasNext()) ? new BufferedReader(pb, $outer.scala$io$BufferedSource$$bufferSize) : 
/*    */         
/* 61 */         $outer.scala$io$BufferedSource$$charReader();
/* 63 */       this.nextLine = null;
/*    */     }
/*    */     
/*    */     private BufferedReader lineReader() {
/*    */       return this.lineReader;
/*    */     }
/*    */     
/*    */     public String nextLine() {
/* 63 */       return this.nextLine;
/*    */     }
/*    */     
/*    */     public void nextLine_$eq(String x$1) {
/* 63 */       this.nextLine = x$1;
/*    */     }
/*    */     
/*    */     public boolean hasNext() {
/* 66 */       if (nextLine() == null)
/* 67 */         nextLine_$eq(lineReader().readLine()); 
/* 69 */       return !(nextLine() == null);
/*    */     }
/*    */     
/*    */     public String next() {
/*    */       // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: invokevirtual nextLine : ()Ljava/lang/String;
/*    */       //   4: ifnonnull -> 17
/*    */       //   7: aload_0
/*    */       //   8: invokespecial lineReader : ()Ljava/io/BufferedReader;
/*    */       //   11: invokevirtual readLine : ()Ljava/lang/String;
/*    */       //   14: goto -> 26
/*    */       //   17: aload_0
/*    */       //   18: invokevirtual nextLine : ()Ljava/lang/String;
/*    */       //   21: aload_0
/*    */       //   22: aconst_null
/*    */       //   23: invokevirtual nextLine_$eq : (Ljava/lang/String;)V
/*    */       //   26: astore_1
/*    */       //   27: aload_1
/*    */       //   28: ifnonnull -> 48
/*    */       //   31: getstatic scala/collection/Iterator$.MODULE$ : Lscala/collection/Iterator$;
/*    */       //   34: invokevirtual empty : ()Lscala/collection/Iterator;
/*    */       //   37: invokeinterface next : ()Ljava/lang/Object;
/*    */       //   42: checkcast java/lang/String
/*    */       //   45: goto -> 49
/*    */       //   48: aload_1
/*    */       //   49: areturn
/*    */       //   50: astore_2
/*    */       //   51: aload_0
/*    */       //   52: aconst_null
/*    */       //   53: invokevirtual nextLine_$eq : (Ljava/lang/String;)V
/*    */       //   56: aload_2
/*    */       //   57: athrow
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #73	-> 0
/*    */       //   #74	-> 17
/*    */       //   #72	-> 26
/*    */       //   #76	-> 27
/*    */       //   #77	-> 48
/*    */       //   #71	-> 49
/*    */       //   #74	-> 50
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	58	0	this	Lscala/io/BufferedSource$BufferedLineIterator;
/*    */       //   27	31	1	result	Ljava/lang/String;
/*    */       // Exception table:
/*    */       //   from	to	target	type
/*    */       //   17	21	50	finally
/*    */     }
/*    */   }
/*    */   
/*    */   public Iterator<String> getLines() {
/* 81 */     return new BufferedLineIterator(this);
/*    */   }
/*    */   
/*    */   public BufferedSource(InputStream inputStream, int bufferSize, Codec codec) {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\io\BufferedSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */