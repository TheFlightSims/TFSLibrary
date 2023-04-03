/*     */ package scala.collection.generic;
/*     */ 
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0352A!\001\002\001\023\t\001B)\0327fO\006$X\rZ\"p]R,\007\020\036\006\003\007\021\tqaZ3oKJL7M\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001aE\002\001\0259\001\"a\003\007\016\003\031I!!\004\004\003\r\005s\027PU3g!\ty\001#D\001\003\023\t\t\"AA\nEK2,w-\031;fINKwM\\1mY&tw\r\003\005\024\001\t\005\r\021\"\001\025\0039\031\030n\0328bY\022+G.Z4bi\026,\022!\006\t\003\037YI!a\006\002\003\025MKwM\\1mY&tw\r\003\005\032\001\t\005\r\021\"\001\033\003I\031\030n\0328bY\022+G.Z4bi\026|F%Z9\025\005mq\002CA\006\035\023\tibA\001\003V]&$\bbB\020\031\003\003\005\r!F\001\004q\022\n\004\002C\021\001\005\003\005\013\025B\013\002\037MLwM\\1m\t\026dWmZ1uK\002BQa\t\001\005\002\021\na\001P5oSRtDCA\023'!\ty\001\001C\003\024E\001\007Q\003")
/*     */ public class DelegatedContext implements DelegatedSignalling {
/*     */   private Signalling signalDelegate;
/*     */   
/*     */   public boolean isAborted() {
/* 180 */     return DelegatedSignalling$class.isAborted(this);
/*     */   }
/*     */   
/*     */   public void abort() {
/* 180 */     DelegatedSignalling$class.abort(this);
/*     */   }
/*     */   
/*     */   public int indexFlag() {
/* 180 */     return DelegatedSignalling$class.indexFlag(this);
/*     */   }
/*     */   
/*     */   public void setIndexFlag(int f) {
/* 180 */     DelegatedSignalling$class.setIndexFlag(this, f);
/*     */   }
/*     */   
/*     */   public void setIndexFlagIfGreater(int f) {
/* 180 */     DelegatedSignalling$class.setIndexFlagIfGreater(this, f);
/*     */   }
/*     */   
/*     */   public void setIndexFlagIfLesser(int f) {
/* 180 */     DelegatedSignalling$class.setIndexFlagIfLesser(this, f);
/*     */   }
/*     */   
/*     */   public int tag() {
/* 180 */     return DelegatedSignalling$class.tag(this);
/*     */   }
/*     */   
/*     */   public Signalling signalDelegate() {
/* 180 */     return this.signalDelegate;
/*     */   }
/*     */   
/*     */   public void signalDelegate_$eq(Signalling x$1) {
/* 180 */     this.signalDelegate = x$1;
/*     */   }
/*     */   
/*     */   public DelegatedContext(Signalling signalDelegate) {
/* 180 */     DelegatedSignalling$class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\DelegatedContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */