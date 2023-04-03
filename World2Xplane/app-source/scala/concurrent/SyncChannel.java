/*    */ package scala.concurrent;
/*    */ 
/*    */ import scala.MatchError;
/*    */ import scala.Predef$;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.immutable.List$;
/*    */ import scala.collection.immutable.Nil$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\00193A!\001\002\001\017\tY1+\0378d\007\"\fgN\\3m\025\t\031A!\001\006d_:\034WO\035:f]RT\021!B\001\006g\016\fG.Y\002\001+\tAAc\005\002\001\023A\021!bC\007\002\t%\021A\002\002\002\007\003:L(+\0324\t\0139\001A\021A\b\002\rqJg.\033;?)\005\001\002cA\t\001%5\t!\001\005\002\024)1\001A!B\013\001\005\0041\"!A!\022\005]Q\002C\001\006\031\023\tIBAA\004O_RD\027N\\4\021\005)Y\022B\001\017\005\005\r\te.\037\005\b=\001\001\r\021\"\003 \0035\001XM\0343j]\036<&/\033;fgV\t\001\005E\002\"M!j\021A\t\006\003G\021\n\021\"[7nkR\f'\r\\3\013\005\025\"\021AC2pY2,7\r^5p]&\021qE\t\002\005\031&\034H\017\005\003\013SIY\023B\001\026\005\005\031!V\017\0357feA\031\021\003\f\030\n\0055\022!aB*z]\0164\026M\035\t\003\025=J!\001\r\003\003\017\t{w\016\\3b]\"9!\007\001a\001\n\023\031\024!\0059f]\022LgnZ,sSR,7o\030\023fcR\021Ag\016\t\003\025UJ!A\016\003\003\tUs\027\016\036\005\bqE\n\t\0211\001!\003\rAH%\r\005\007u\001\001\013\025\002\021\002\035A,g\016Z5oO^\023\030\016^3tA!9A\b\001a\001\n\023i\024\001\0049f]\022Lgn\032*fC\022\034X#\001 \021\007\0052s\bE\002\022YIAq!\021\001A\002\023%!)\001\tqK:$\027N\\4SK\006$7o\030\023fcR\021Ag\021\005\bq\001\013\t\0211\001?\021\031)\005\001)Q\005}\005i\001/\0328eS:<'+Z1eg\002BQa\022\001\005\002!\013Qa\036:ji\026$\"\001N%\t\013)3\005\031\001\n\002\t\021\fG/\031\005\006\031\002!\t!T\001\005e\026\fG-F\001\023\001")
/*    */ public class SyncChannel<A> {
/* 20 */   private List<Tuple2<A, SyncVar<Object>>> pendingWrites = (List<Tuple2<A, SyncVar<Object>>>)Nil$.MODULE$;
/*    */   
/*    */   private List<Tuple2<A, SyncVar<Object>>> pendingWrites() {
/* 20 */     return this.pendingWrites;
/*    */   }
/*    */   
/*    */   private void pendingWrites_$eq(List<Tuple2<A, SyncVar<Object>>> x$1) {
/* 20 */     this.pendingWrites = x$1;
/*    */   }
/*    */   
/* 21 */   private List<SyncVar<A>> pendingReads = (List<SyncVar<A>>)Nil$.MODULE$;
/*    */   
/*    */   private List<SyncVar<A>> pendingReads() {
/* 21 */     return this.pendingReads;
/*    */   }
/*    */   
/*    */   private void pendingReads_$eq(List<SyncVar<A>> x$1) {
/* 21 */     this.pendingReads = x$1;
/*    */   }
/*    */   
/*    */   public void write(Object data) {
/* 25 */     SyncVar<Boolean> writeReq = new SyncVar();
/* 27 */     synchronized (this) {
/* 29 */       if (pendingReads().isEmpty()) {
/* 41 */         List<Tuple2<A, SyncVar<Object>>> list = pendingWrites();
/* 41 */         (new Tuple2[1])[0] = new Tuple2(data, writeReq);
/* 41 */         pendingWrites_$eq(List$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[1])).$colon$colon$colon(list));
/*    */       } else {
/*    */         SyncVar<Object> readReq = (SyncVar)pendingReads().head();
/*    */         pendingReads_$eq((List<SyncVar<A>>)pendingReads().tail());
/*    */         readReq.set(data);
/*    */         writeReq.set(BoxesRunTime.boxToBoolean(true));
/*    */       } 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/concurrent/SyncChannel}} */
/* 45 */       writeReq.get();
/*    */       return;
/*    */     } 
/*    */   }
/*    */   
/*    */   public A read() {
/* 50 */     SyncVar<Object> readReq = new SyncVar();
/* 52 */     synchronized (this) {
/* 54 */       if (pendingWrites().isEmpty()) {
/* 67 */         List<SyncVar<A>> list = pendingReads();
/* 67 */         (new SyncVar[1])[0] = readReq;
/* 67 */         pendingReads_$eq(List$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new SyncVar[1])).$colon$colon$colon(list));
/*    */       } else {
/*    */         Tuple2 tuple2 = (Tuple2)pendingWrites().head();
/*    */         if (tuple2 != null) {
/*    */           Tuple2 tuple21 = new Tuple2(tuple2._1(), tuple2._2());
/*    */           Object data = tuple21._1();
/*    */           SyncVar<Boolean> writeReq = (SyncVar)tuple21._2();
/*    */           pendingWrites_$eq((List<Tuple2<A, SyncVar<Object>>>)pendingWrites().tail());
/*    */           writeReq.set(BoxesRunTime.boxToBoolean(true));
/*    */           readReq.set(data);
/*    */         } else {
/*    */           /* monitor exit ThisExpression{ObjectType{scala/concurrent/SyncChannel}} */
/*    */           throw new MatchError(tuple2);
/*    */         } 
/*    */       } 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/concurrent/SyncChannel}} */
/* 71 */       return (A)readReq.get();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\SyncChannel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */