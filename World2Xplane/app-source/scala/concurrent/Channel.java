/*    */ package scala.concurrent;
/*    */ 
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\r4A!\001\002\001\017\t91\t[1o]\026d'BA\002\005\003)\031wN\\2veJ,g\016\036\006\002\013\005)1oY1mC\016\001QC\001\005\025'\t\001\021\002\005\002\013\0275\tA!\003\002\r\t\t1\021I\\=SK\032DQA\004\001\005\002=\ta\001P5oSRtD#\001\t\021\007E\001!#D\001\003!\t\031B\003\004\001\005\013U\001!\031\001\f\003\003\005\013\"a\006\016\021\005)A\022BA\r\005\005\035qu\016\0365j]\036\004\"AC\016\n\005q!!aA!os\032!a\004\001\001 \005)a\025N\\6fI2K7\017^\013\003A\031\032\"!H\005\t\0139iB\021\001\022\025\003\r\0022\001J\017&\033\005\001\001CA\n'\t\025)RD1\001\027\021%AS\0041AA\002\023\005\021&\001\003fY\026lW#A\023\t\023-j\002\031!a\001\n\003a\023\001C3mK6|F%Z9\025\0055\002\004C\001\006/\023\tyCA\001\003V]&$\bbB\031+\003\003\005\r!J\001\004q\022\n\004BB\032\036A\003&Q%A\003fY\026l\007\005C\0046;\001\007I\021\001\034\002\t9,\007\020^\013\002G!9\001(\ba\001\n\003I\024\001\0038fqR|F%Z9\025\0055R\004bB\0318\003\003\005\ra\t\005\007yu\001\013\025B\022\002\0139,\007\020\036\021\t\017y\002\001\031!C\005\0059qO]5ui\026tW#\001!\021\007\021j\"\003C\004C\001\001\007I\021B\"\002\027]\024\030\016\036;f]~#S-\035\013\003[\021Cq!M!\002\002\003\007\001\t\003\004G\001\001\006K\001Q\001\toJLG\017^3oA!9\001\n\001a\001\n\023y\024a\0037bgR<&/\033;uK:DqA\023\001A\002\023%1*A\bmCN$xK]5ui\026tw\fJ3r)\tiC\nC\0042\023\006\005\t\031\001!\t\r9\003\001\025)\003A\0031a\027m\035;Xe&$H/\0328!\021\035\001\006\0011A\005\nE\013\001B\034:fC\022,'o]\013\002%B\021!bU\005\003)\022\0211!\0238u\021\0351\006\0011A\005\n]\013AB\034:fC\022,'o]0%KF$\"!\f-\t\017E*\026\021!a\001%\"1!\f\001Q!\nI\013\021B\034:fC\022,'o\035\021\t\013q\003A\021A/\002\013]\024\030\016^3\025\0055r\006\"B0\\\001\004\021\022!\001=\t\013\005\004A\021\0012\002\tI,\027\rZ\013\002%\001")
/*    */ public class Channel<A> {
/*    */   public class LinkedList<A> {
/*    */     private A elem;
/*    */     
/*    */     public A elem() {
/* 20 */       return this.elem;
/*    */     }
/*    */     
/*    */     public void elem_$eq(Object x$1) {
/* 20 */       this.elem = (A)x$1;
/*    */     }
/*    */     
/* 21 */     private LinkedList<A> next = null;
/*    */     
/*    */     public LinkedList<A> next() {
/* 21 */       return this.next;
/*    */     }
/*    */     
/*    */     public void next_$eq(LinkedList<A> x$1) {
/* 21 */       this.next = x$1;
/*    */     }
/*    */     
/*    */     public LinkedList(Channel $outer) {}
/*    */   }
/*    */   
/* 23 */   private LinkedList<A> written = new LinkedList<A>(this);
/*    */   
/*    */   private LinkedList<A> written() {
/* 23 */     return this.written;
/*    */   }
/*    */   
/*    */   private void written_$eq(LinkedList<A> x$1) {
/* 23 */     this.written = x$1;
/*    */   }
/*    */   
/* 24 */   private LinkedList<A> lastWritten = written();
/*    */   
/*    */   private LinkedList<A> lastWritten() {
/* 24 */     return this.lastWritten;
/*    */   }
/*    */   
/*    */   private void lastWritten_$eq(LinkedList<A> x$1) {
/* 24 */     this.lastWritten = x$1;
/*    */   }
/*    */   
/* 25 */   private int nreaders = 0;
/*    */   
/*    */   private int nreaders() {
/* 25 */     return this.nreaders;
/*    */   }
/*    */   
/*    */   private void nreaders_$eq(int x$1) {
/* 25 */     this.nreaders = x$1;
/*    */   }
/*    */   
/*    */   public synchronized void write(Object x) {
/* 30 */     lastWritten().elem_$eq((A)x);
/* 31 */     lastWritten().next_$eq(new LinkedList<A>(this));
/* 32 */     lastWritten_$eq(lastWritten().next());
/* 33 */     if (nreaders() > 0)
/* 33 */       notify(); 
/*    */   }
/*    */   
/*    */   public synchronized A read() {
/*    */     while (true) {
/* 37 */       if (written().next() == null) {
/*    */         try {
/* 39 */           nreaders_$eq(nreaders() + 1);
/* 40 */           wait();
/*    */         } finally {
/* 42 */           nreaders_$eq(nreaders() - 1);
/*    */         } 
/*    */         continue;
/*    */       } 
/* 44 */       Object x = written().elem();
/* 45 */       written_$eq(written().next());
/* 46 */       return (A)x;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\Channel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */