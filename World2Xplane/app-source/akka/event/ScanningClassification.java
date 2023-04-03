/*     */ package akka.event;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.concurrent.ConcurrentSkipListSet;
/*     */ import scala.Tuple2;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001!4\001\"\001\002\021\002\007\005qA\031\002\027'\016\fgN\\5oO\016c\027m]:jM&\034\027\r^5p]*\0211\001B\001\006KZ,g\016\036\006\002\013\005!\021m[6b\007\001\031\"\001\001\005\021\005%aQ\"\001\006\013\003-\tQa]2bY\006L!!\004\006\003\r\005s\027PU3g\021\025y\001\001\"\001\021\003\031!\023N\\5uIQ\t\021\003\005\002\n%%\0211C\003\002\005+:LG\017C\004\026\001\t\007IQ\003\f\002\027M,(m]2sS\n,'o]\013\002/A\031\001dH\021\016\003eQ!AG\016\002\025\r|gnY;se\026tGO\003\002\035;\005!Q\017^5m\025\005q\022\001\0026bm\006L!\001I\r\003+\r{gnY;se\026tGoU6ja2K7\017^*fiB!\021B\t\023+\023\t\031#B\001\004UkBdWM\r\t\003K\031j\021\001A\005\003O!\022!b\0217bgNLg-[3s\023\tI#A\001\005Fm\026tGOQ;t!\t)3&\003\002-Q\tQ1+\0362tGJL'-\032:\t\r9\002\001\025!\004\030\0031\031XOY:de&\024WM]:!\021\025\001\004A\"\0052\003I\031w.\0349be\026\034E.Y:tS\032LWM]:\025\007I*t\007\005\002\ng%\021AG\003\002\004\023:$\b\"\002\0340\001\004!\023!A1\t\013az\003\031\001\023\002\003\tDQA\017\001\007\022m\n!cY8na\006\024XmU;cg\016\024\030NY3sgR\031!\007P\037\t\013YJ\004\031\001\026\t\013aJ\004\031\001\026\t\013}\002a\021\003!\002\0175\fGo\0315fgR\031\021\t\022$\021\005%\021\025BA\"\013\005\035\021un\0347fC:DQ!\022 A\002\021\n!b\0317bgNLg-[3s\021\025\031a\b1\001H!\t)\003*\003\002JQ\t)QI^3oi\")1\n\001D\t\031\0069\001/\0362mSNDGcA\tN\035\")1A\023a\001\017\")qJ\023a\001U\005Q1/\0362tGJL'-\032:\t\013E\003A\021\001*\002\023M,(m]2sS\n,GcA!T)\")q\n\025a\001U!)Q\013\025a\001I\005\021Ao\034\005\006/\002!\t\001W\001\fk:\034XOY:de&\024W\rF\002B3jCQa\024,A\002)BQa\027,A\002\021\nAA\032:p[\")q\013\001C\001;R\021\021C\030\005\006\037r\003\rA\013\005\006\027\002!\t\001\031\013\003#\005DQaA0A\002\035\0232aY3h\r\021!\007\001\0012\003\031q\022XMZ5oK6,g\016\036 \021\005\031\004Q\"\001\002\021\005\031D\003")
/*     */ public interface ScanningClassification {
/*     */   void akka$event$ScanningClassification$_setter_$subscribers_$eq(ConcurrentSkipListSet paramConcurrentSkipListSet);
/*     */   
/*     */   ConcurrentSkipListSet<Tuple2<Object, Object>> subscribers();
/*     */   
/*     */   int compareClassifiers(Object paramObject1, Object paramObject2);
/*     */   
/*     */   int compareSubscribers(Object paramObject1, Object paramObject2);
/*     */   
/*     */   boolean matches(Object paramObject1, Object paramObject2);
/*     */   
/*     */   void publish(Object paramObject1, Object paramObject2);
/*     */   
/*     */   boolean subscribe(Object paramObject1, Object paramObject2);
/*     */   
/*     */   boolean unsubscribe(Object paramObject1, Object paramObject2);
/*     */   
/*     */   void unsubscribe(Object paramObject);
/*     */   
/*     */   void publish(Object paramObject);
/*     */   
/*     */   public class $anon$2 implements Comparator<Tuple2<Object, Object>> {
/*     */     public $anon$2(ScanningClassification $outer) {}
/*     */     
/*     */     public int compare(Tuple2 a, Tuple2 b) {
/* 197 */       int i = this.$outer.compareClassifiers(a._1(), b._1());
/* 197 */       switch (i) {
/*     */         default:
/*     */         
/*     */         case 0:
/*     */           break;
/*     */       } 
/* 197 */       return 
/* 198 */         this.$outer.compareSubscribers(a._2(), b._2());
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\ScanningClassification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */