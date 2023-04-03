/*    */ package scala.sys;
/*    */ 
/*    */ import java.io.File;
/*    */ import scala.Function1;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0055caB\001\003!\003\r\na\002\002\005!J|\007O\003\002\004\t\005\0311/_:\013\003\025\tQa]2bY\006\034\001!\006\002\t9M\021\001!\003\t\003\025-i\021\001B\005\003\031\021\021a!\0218z%\0264\007\"\002\b\001\r\003y\021aA6fsV\t\001\003\005\002\022)9\021!BE\005\003'\021\ta\001\025:fI\0264\027BA\013\027\005\031\031FO]5oO*\0211\003\002\005\0061\0011\t!G\001\006m\006dW/Z\013\0025A\0211\004\b\007\001\t\031i\002\001\"b\001=\t\tA+\005\002 EA\021!\002I\005\003C\021\021qAT8uQ&tw\r\005\002\013G%\021A\005\002\002\004\003:L\b\"\002\024\001\r\0039\023!B5t'\026$X#\001\025\021\005)I\023B\001\026\005\005\035\021un\0347fC:DQ\001\f\001\007\0025\n1a]3u)\t\001b\006C\0030W\001\007\001#\001\005oK^4\026\r\\;f\021\025\t\004A\"\0013\003!\031X\r\036,bYV,WCA\0327)\tQB\007C\003\031a\001\007Q\007\005\002\034m\021)q\007\rb\001q\t\021A+M\t\0035\tBQA\017\001\007\002=\t1aZ3u\021\025a\004A\"\001>\003\031y\007\017^5p]V\ta\bE\002\013iI!\001\021\003\003\r=\003H/[8o\021\025\021\005A\"\001D\003\025\031G.Z1s)\005!\005C\001\006F\023\t1EA\001\003V]&$\b\"\002%\001\r#I\022\001\002>fe><QA\023\002\t\002-\013A\001\025:paB\021A*T\007\002\005\031)\021A\001E\001\035N\021Q*\003\005\006!6#\t!U\001\007y%t\027\016\036 \025\003-3qaU'\021\002G\005AKA\004De\026\fGo\034:\026\005U[6C\001*\n\021\0259&K\"\001Y\003\025\t\007\017\0357z)\tIF\fE\002M\001i\003\"aG.\005\ru\021FQ1\001\037\021\025qa\0131\001\021Q\r\021f\f\032\t\003?\nl\021\001\031\006\003C\022\t!\"\0318o_R\fG/[8o\023\t\031\007M\001\tj[Bd\027nY5u\035>$hi\\;oI\006\nQ-A\033O_\002JW\016\0357jG&$\b\005\035:pa\026\024H/\037\021de\026\fGo\034:!CZ\f\027\016\\1cY\026\004cm\034:!if\004X\r\t\023|)vts!B4N\021\007A\027\001\003$jY\026\004&o\0349\021\005%TW\"A'\007\013-l\005\022\0017\003\021\031KG.\032)s_B\034\"A[7\021\0071s\007/\003\002p\005\tY1I]3bi>\024\030*\0349m!\t\th/D\001s\025\t\031H/\001\002j_*\tQ/\001\003kCZ\f\027BA<s\005\0211\025\016\\3\t\013ASG\021A=\025\003!<Qa_'\t\004q\f!b\025;sS:<\007K]8q!\tIWPB\003\033\"\005qP\001\006TiJLgn\032)s_B\0342!`A\001!\rae\016\005\005\007!v$\t!!\002\025\003q<q!!\003N\021\007\tY!A\004J]R\004&o\0349\021\007%\fiAB\004\002\0205C\t!!\005\003\017%sG\017\025:paN!\021QBA\n!\021ae.!\006\021\007)\t9\"C\002\002\032\021\0211!\0238u\021\035\001\026Q\002C\001\003;!\"!a\003\b\017\005\005R\nc\001\002$\005QAi\\;cY\026\004&o\0349\021\007%\f)CB\004\002(5C\t!!\013\003\025\021{WO\0317f!J|\007o\005\003\002&\005-\002\003\002'o\003[\0012ACA\030\023\r\t\t\004\002\002\007\t>,(\r\\3\t\017A\013)\003\"\001\0026Q\021\0211\005\005\007/6#\t!!\017\026\t\005m\0221\t\013\005\003{\tY\005\006\003\002@\005\025\003\003\002'\001\003\003\0022aGA\"\t\031i\022q\007b\001=!Q\021qIA\034\003\003\005\035!!\023\002\025\0254\030\016Z3oG\026$\023\007\005\003j%\006\005\003B\002\b\0028\001\007\001\003")
/*    */ public interface Prop<T> {
/*    */   String key();
/*    */   
/*    */   T value();
/*    */   
/*    */   boolean isSet();
/*    */   
/*    */   String set(String paramString);
/*    */   
/*    */   <T1> T setValue(T1 paramT1);
/*    */   
/*    */   String get();
/*    */   
/*    */   Option<T> option();
/*    */   
/*    */   void clear();
/*    */   
/*    */   T zero();
/*    */   
/*    */   public static class FileProp$ extends CreatorImpl<File> {
/*    */     public static final FileProp$ MODULE$;
/*    */     
/*    */     public FileProp$() {
/* 83 */       super((Function1<String, File>)new Prop$FileProp$$anonfun$$init$$1());
/* 83 */       MODULE$ = this;
/*    */     }
/*    */     
/*    */     public static class Prop$FileProp$$anonfun$$init$$1 extends AbstractFunction1<String, File> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final File apply(String s) {
/* 83 */         return new File(s);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public static class StringProp$ extends CreatorImpl<String> {
/*    */     public static final StringProp$ MODULE$;
/*    */     
/*    */     public StringProp$() {
/* 84 */       super((Function1<String, String>)new Prop$StringProp$$anonfun$$init$$2());
/* 84 */       MODULE$ = this;
/*    */     }
/*    */     
/*    */     public static class Prop$StringProp$$anonfun$$init$$2 extends AbstractFunction1<String, String> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final String apply(String s) {
/* 84 */         return s;
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public static class IntProp$ extends CreatorImpl<Object> {
/*    */     public static final IntProp$ MODULE$;
/*    */     
/*    */     public IntProp$() {
/* 85 */       super((Function1<String, Object>)new Prop$IntProp$$anonfun$$init$$3());
/* 85 */       MODULE$ = this;
/*    */     }
/*    */     
/*    */     public static class Prop$IntProp$$anonfun$$init$$3 extends AbstractFunction1<String, Object> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final int apply(String x$1) {
/* 85 */         scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 85 */         return (new StringOps(x$1)).toInt();
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public static class DoubleProp$ extends CreatorImpl<Object> {
/*    */     public static final DoubleProp$ MODULE$;
/*    */     
/*    */     public DoubleProp$() {
/* 86 */       super((Function1<String, Object>)new Prop$DoubleProp$$anonfun$$init$$4());
/* 86 */       MODULE$ = this;
/*    */     }
/*    */     
/*    */     public static class Prop$DoubleProp$$anonfun$$init$$4 extends AbstractFunction1<String, Object> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final double apply(String x$2) {
/* 86 */         scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 86 */         return (new StringOps(x$2)).toDouble();
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public static interface Creator<T> {
/*    */     Prop<T> apply(String param1String);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\Prop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */