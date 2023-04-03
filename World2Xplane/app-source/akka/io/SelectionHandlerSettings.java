/*    */ package akka.io;
/*    */ 
/*    */ import com.typesafe.config.Config;
/*    */ import scala.Serializable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0253Q!\001\002\002\002\035\021\001dU3mK\016$\030n\0348IC:$G.\032:TKR$\030N\\4t\025\t\031A!\001\002j_*\tQ!\001\003bW.\f7\001A\n\003\001!\001\"!\003\007\016\003)Q\021aC\001\006g\016\fG.Y\005\003\033)\021a!\0218z%\0264\007\002C\b\001\005\003\005\013\021\002\t\002\r\r|gNZ5h!\t\tr#D\001\023\025\ty1C\003\002\025+\005AA/\0379fg\0064WMC\001\027\003\r\031w.\\\005\0031I\021aaQ8oM&<\007\"\002\016\001\t\003Y\022A\002\037j]&$h\b\006\002\035=A\021Q\004A\007\002\005!)q\"\007a\001!!9\001\005\001b\001\n\003\t\023aC'bq\016C\027M\0348fYN,\022A\t\t\003\023\rJ!\001\n\006\003\007%sG\017\003\004'\001\001\006IAI\001\r\033\006D8\t[1o]\026d7\017\t\005\bQ\001\021\r\021\"\001\"\003i\031V\r\\3di>\024\030i]:pG&\fG/[8o%\026$(/[3t\021\031Q\003\001)A\005E\005Y2+\0327fGR|'/Q:t_\016L\027\r^5p]J+GO]5fg\002Bq\001\f\001C\002\023\005Q&\001\nTK2,7\r^8s\t&\034\b/\031;dQ\026\024X#\001\030\021\005=\022dBA\0051\023\t\t$\"\001\004Qe\026$WMZ\005\003gQ\022aa\025;sS:<'BA\031\013\021\0311\004\001)A\005]\005\0312+\0327fGR|'\017R5ta\006$8\r[3sA!9\001\b\001b\001\n\003i\023\001E,pe.,'\017R5ta\006$8\r[3s\021\031Q\004\001)A\005]\005\trk\034:lKJ$\025n\0359bi\016DWM\035\021\t\017q\002!\031!C\001{\005aAK]1dK2{wmZ5oOV\ta\b\005\002\n%\021\001I\003\002\b\005>|G.Z1o\021\031\021\005\001)A\005}\005iAK]1dK2{wmZ5oO\002BQ\001\022\001\007\002\005\na#T1y\007\"\fgN\\3mgB+'oU3mK\016$xN\035")
/*    */ public abstract class SelectionHandlerSettings {
/*    */   private final int MaxChannels;
/*    */   
/*    */   private final int SelectorAssociationRetries;
/*    */   
/*    */   private final String SelectorDispatcher;
/*    */   
/*    */   private final String WorkerDispatcher;
/*    */   
/*    */   private final boolean TraceLogging;
/*    */   
/*    */   public SelectionHandlerSettings(Config config) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: invokespecial <init> : ()V
/*    */     //   4: aload_0
/*    */     //   5: aload_1
/*    */     //   6: ldc 'max-channels'
/*    */     //   8: invokeinterface getString : (Ljava/lang/String;)Ljava/lang/String;
/*    */     //   13: astore_2
/*    */     //   14: ldc 'unlimited'
/*    */     //   16: aload_2
/*    */     //   17: astore_3
/*    */     //   18: dup
/*    */     //   19: ifnonnull -> 30
/*    */     //   22: pop
/*    */     //   23: aload_3
/*    */     //   24: ifnull -> 37
/*    */     //   27: goto -> 43
/*    */     //   30: aload_3
/*    */     //   31: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   34: ifeq -> 43
/*    */     //   37: iconst_m1
/*    */     //   38: istore #4
/*    */     //   40: goto -> 87
/*    */     //   43: getstatic akka/util/Helpers$Requiring$.MODULE$ : Lakka/util/Helpers$Requiring$;
/*    */     //   46: getstatic akka/util/Helpers$.MODULE$ : Lakka/util/Helpers$;
/*    */     //   49: aload_1
/*    */     //   50: ldc 'max-channels'
/*    */     //   52: invokeinterface getInt : (Ljava/lang/String;)I
/*    */     //   57: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*    */     //   60: invokevirtual Requiring : (Ljava/lang/Object;)Ljava/lang/Object;
/*    */     //   63: new akka/io/SelectionHandlerSettings$$anonfun$1
/*    */     //   66: dup
/*    */     //   67: aload_0
/*    */     //   68: invokespecial <init> : (Lakka/io/SelectionHandlerSettings;)V
/*    */     //   71: new akka/io/SelectionHandlerSettings$$anonfun$3
/*    */     //   74: dup
/*    */     //   75: aload_0
/*    */     //   76: invokespecial <init> : (Lakka/io/SelectionHandlerSettings;)V
/*    */     //   79: invokevirtual requiring$extension1 : (Ljava/lang/Object;Lscala/Function1;Lscala/Function0;)Ljava/lang/Object;
/*    */     //   82: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   85: istore #4
/*    */     //   87: iload #4
/*    */     //   89: putfield MaxChannels : I
/*    */     //   92: aload_0
/*    */     //   93: getstatic akka/util/Helpers$Requiring$.MODULE$ : Lakka/util/Helpers$Requiring$;
/*    */     //   96: getstatic akka/util/Helpers$.MODULE$ : Lakka/util/Helpers$;
/*    */     //   99: aload_1
/*    */     //   100: ldc 'selector-association-retries'
/*    */     //   102: invokeinterface getInt : (Ljava/lang/String;)I
/*    */     //   107: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*    */     //   110: invokevirtual Requiring : (Ljava/lang/Object;)Ljava/lang/Object;
/*    */     //   113: new akka/io/SelectionHandlerSettings$$anonfun$2
/*    */     //   116: dup
/*    */     //   117: aload_0
/*    */     //   118: invokespecial <init> : (Lakka/io/SelectionHandlerSettings;)V
/*    */     //   121: new akka/io/SelectionHandlerSettings$$anonfun$4
/*    */     //   124: dup
/*    */     //   125: aload_0
/*    */     //   126: invokespecial <init> : (Lakka/io/SelectionHandlerSettings;)V
/*    */     //   129: invokevirtual requiring$extension1 : (Ljava/lang/Object;Lscala/Function1;Lscala/Function0;)Ljava/lang/Object;
/*    */     //   132: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*    */     //   135: putfield SelectorAssociationRetries : I
/*    */     //   138: aload_0
/*    */     //   139: aload_1
/*    */     //   140: ldc 'selector-dispatcher'
/*    */     //   142: invokeinterface getString : (Ljava/lang/String;)Ljava/lang/String;
/*    */     //   147: putfield SelectorDispatcher : Ljava/lang/String;
/*    */     //   150: aload_0
/*    */     //   151: aload_1
/*    */     //   152: ldc 'worker-dispatcher'
/*    */     //   154: invokeinterface getString : (Ljava/lang/String;)Ljava/lang/String;
/*    */     //   159: putfield WorkerDispatcher : Ljava/lang/String;
/*    */     //   162: aload_0
/*    */     //   163: aload_1
/*    */     //   164: ldc 'trace-logging'
/*    */     //   166: invokeinterface getBoolean : (Ljava/lang/String;)Z
/*    */     //   171: putfield TraceLogging : Z
/*    */     //   174: return
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #24	-> 0
/*    */     //   #27	-> 4
/*    */     //   #28	-> 14
/*    */     //   #29	-> 46
/*    */     //   #27	-> 87
/*    */     //   #31	-> 92
/*    */     //   #32	-> 113
/*    */     //   #31	-> 129
/*    */     //   #34	-> 138
/*    */     //   #35	-> 150
/*    */     //   #36	-> 162
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	175	0	this	Lakka/io/SelectionHandlerSettings;
/*    */     //   0	175	1	config	Lcom/typesafe/config/Config;
/*    */   }
/*    */   
/*    */   public int MaxChannels() {
/* 27 */     return this.MaxChannels;
/*    */   }
/*    */   
/*    */   public class $anonfun$1 extends AbstractFunction1.mcZI.sp implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(int x$1) {
/* 29 */       return apply$mcZI$sp(x$1);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZI$sp(int x$1) {
/* 29 */       return (x$1 > 0);
/*    */     }
/*    */     
/*    */     public $anonfun$1(SelectionHandlerSettings $outer) {}
/*    */   }
/*    */   
/*    */   public class $anonfun$3 extends AbstractFunction0<String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final String apply() {
/* 29 */       return "max-channels must be > 0 or 'unlimited'";
/*    */     }
/*    */     
/*    */     public $anonfun$3(SelectionHandlerSettings $outer) {}
/*    */   }
/*    */   
/*    */   public int SelectorAssociationRetries() {
/* 31 */     return this.SelectorAssociationRetries;
/*    */   }
/*    */   
/*    */   public class $anonfun$2 extends AbstractFunction1.mcZI.sp implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(int x$2) {
/* 32 */       return apply$mcZI$sp(x$2);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZI$sp(int x$2) {
/* 32 */       return (x$2 >= 0);
/*    */     }
/*    */     
/*    */     public $anonfun$2(SelectionHandlerSettings $outer) {}
/*    */   }
/*    */   
/*    */   public class $anonfun$4 extends AbstractFunction0<String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final String apply() {
/* 32 */       return "selector-association-retries must be >= 0";
/*    */     }
/*    */     
/*    */     public $anonfun$4(SelectionHandlerSettings $outer) {}
/*    */   }
/*    */   
/*    */   public String SelectorDispatcher() {
/* 34 */     return this.SelectorDispatcher;
/*    */   }
/*    */   
/*    */   public String WorkerDispatcher() {
/* 35 */     return this.WorkerDispatcher;
/*    */   }
/*    */   
/*    */   public boolean TraceLogging() {
/* 36 */     return this.TraceLogging;
/*    */   }
/*    */   
/*    */   public abstract int MaxChannelsPerSelector();
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\SelectionHandlerSettings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */