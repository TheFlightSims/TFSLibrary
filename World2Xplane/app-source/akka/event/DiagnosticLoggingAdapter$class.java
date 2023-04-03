/*      */ package akka.event;
/*      */ 
/*      */ import java.util.Map;
/*      */ import scala.Predef$;
/*      */ import scala.collection.JavaConverters$;
/*      */ import scala.collection.Map;
/*      */ import scala.collection.TraversableOnce;
/*      */ import scala.collection.immutable.Map;
/*      */ 
/*      */ public abstract class DiagnosticLoggingAdapter$class {
/*      */   public static void $init$(DiagnosticLoggingAdapter $this) {
/* 1072 */     $this.akka$event$DiagnosticLoggingAdapter$$_mdc_$eq(Logging$.MODULE$.emptyMDC());
/*      */   }
/*      */   
/*      */   public static Map mdc(DiagnosticLoggingAdapter $this) {
/* 1082 */     return $this.akka$event$DiagnosticLoggingAdapter$$_mdc();
/*      */   }
/*      */   
/*      */   public static void mdc(DiagnosticLoggingAdapter $this, Map<String, Object> mdc) {
/* 1090 */     $this.akka$event$DiagnosticLoggingAdapter$$_mdc_$eq((mdc == null) ? Logging$.MODULE$.emptyMDC() : mdc);
/*      */   }
/*      */   
/*      */   public static Map getMDC(DiagnosticLoggingAdapter $this) {
/* 1109 */     return (Map)JavaConverters$.MODULE$.mapAsJavaMapConverter((Map)$this.mdc()).asJava();
/*      */   }
/*      */   
/*      */   public static void setMDC(DiagnosticLoggingAdapter $this, Map jMdc) {
/* 1117 */     $this.mdc((jMdc == null) ? Logging$.MODULE$.emptyMDC() : ((TraversableOnce)JavaConverters$.MODULE$.mapAsScalaMapConverter(jMdc).asScala()).toMap(Predef$.MODULE$.conforms()));
/*      */   }
/*      */   
/*      */   public static void clearMDC(DiagnosticLoggingAdapter $this) {
/* 1122 */     $this.mdc(Logging$.MODULE$.emptyMDC());
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\DiagnosticLoggingAdapter$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */