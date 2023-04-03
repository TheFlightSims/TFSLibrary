/*    */ package scala.runtime;
/*    */ 
/*    */ import java.io.OutputStream;
/*    */ import scala.Function0;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.collection.immutable.Range;
/*    */ import scala.collection.immutable.Range$;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005=s!B\001\003\021\0039\021\001E,pe.\034\b.Z3u'V\004\bo\034:u\025\t\031A!A\004sk:$\030.\\3\013\003\025\tQa]2bY\006\034\001\001\005\002\t\0235\t!AB\003\013\005!\0051B\001\tX_J\\7\017[3fiN+\b\017]8siN\021\021\002\004\t\003\0339i\021\001B\005\003\037\021\021a!\0218z%\0264\007\"B\t\n\t\003\021\022A\002\037j]&$h\bF\001\b\021\035!\022\0021A\005\nU\tQbY;se\026tGo\0244gg\026$X#\001\f\021\00559\022B\001\r\005\005\rIe\016\036\005\b5%\001\r\021\"\003\034\003E\031WO\035:f]R|eMZ:fi~#S-\035\013\0039}\001\"!D\017\n\005y!!\001B+oSRDq\001I\r\002\002\003\007a#A\002yIEBaAI\005!B\0231\022AD2veJ,g\016^(gMN,G\017\t\004\005I%!QEA\nGYV\034\b.\0323PkR\004X\017^*ue\026\fWn\005\002$MA\021q\005L\007\002Q)\021\021FK\001\003S>T\021aK\001\005U\0064\030-\003\002.Q\taq*\036;qkR\034FO]3b[\"Aqf\tB\001B\003%a%A\002pkRDQ!E\022\005\002E\"\"A\r\033\021\005M\032S\"A\005\t\013=\002\004\031\001\024\t\013Y\032C\021C\034\002\033\031dWo\0355J]R,'O^1m+\005A\004CA\007:\023\tQDA\001\003M_:<\007\"\002\037$\t#)\022!B<jIRD\007\"\002 $\t#)\022A\002;bE&s7\rC\004AG\001\007I\021B\034\002\0231\f7\017\036$mkND\007b\002\"$\001\004%IaQ\001\016Y\006\034HO\0227vg\"|F%Z9\025\005q!\005b\002\021B\003\003\005\r\001\017\005\007\r\016\002\013\025\002\035\002\0251\f7\017\036$mkND\007\005C\004IG\001\007I\021B\013\002\007\r|G\016C\004KG\001\007I\021B&\002\017\r|Gn\030\023fcR\021A\004\024\005\bA%\013\t\0211\001\027\021\031q5\005)Q\005-\005!1m\0347!\021\025\0016\005\"\021R\003\0259(/\033;f)\021a\"K\027/\t\013M{\005\031\001+\002\003\t\0042!D+X\023\t1FAA\003BeJ\f\027\020\005\002\0161&\021\021\f\002\002\005\005f$X\rC\003\\\037\002\007a#A\002pM\032DQ!X(A\002Y\t1\001\\3o\021\025\0016\005\"\021`)\ta\002\rC\003b=\002\007a#A\001d\021\025\0317\005\"\021e\003\0251G.^:i)\005a\002\"\0024$\t\0039\027\001C<sSR,wJ\\3\025\005qA\007\"B1f\001\0041\002\"\0026$\t\003!\027!D3ogV\024XMT3x\031&tW\rC\004m\023\t\007I\021B7\002\025\031dWo\0355fI>+H/F\0013\021\031y\027\002)A\005e\005Ya\r\\;tQ\026$w*\036;!\021\035\t\030B1A\005\nI\f\001\002\035:j]R|U\017^\013\002gB\021q\005^\005\003k\"\0221\002\025:j]R\034FO]3b[\"1q/\003Q\001\nM\f\021\002\035:j]R|U\017\036\021\t\013eLA\021\002>\002\025I,G-\033:fGR,G\r\006\002\035w\"1A\020\037CA\002u\f!a\0349\021\0075qH$\003\002\000\t\tAAHY=oC6,g\bC\004\002\004%!\t!!\002\002\021\021*\0070Z2vi\026$2\001HA\004\021\035a\030\021\001CA\002uDq!a\003\n\t\003\ti!A\003%g.L\007\017F\002\035\003\037Aq!!\005\002\n\001\007a#A\001o\021\035\t)\"\003C\001\003/\tQ\001J:u_B$\"!!\007\021\0075\tY\"C\002\002\036\021\021qAT8uQ&tw\rC\004\002\"%!\t!a\t\002\013\021\032\bn\\<\025\t\005\025\0221\007\t\005\003O\tiCD\002\016\003SI1!a\013\005\003\031\001&/\0323fM&!\021qFA\031\005\031\031FO]5oO*\031\0211\006\003\t\021\005U\022q\004a\001\003o\t\021\001\037\t\004\033\005e\022bAA\036\t\t\031\021I\\=)\017%\ty$!\022\002JA\031Q\"!\021\n\007\005\rCA\001\006eKB\024XmY1uK\022\f#!a\022\002\003NKUF\016\0336qi\002\023J\\:ueVlWM\034;bi&|g\016\t7pO&\034\007e^5mY\002\022W\rI7pm\026$\007e\\;uA=4\007\005\0365fA\r|W\016]5mKJt\023EAA&\003\031\021d&\r\031/a!:\001!a\020\002F\005%\003")
/*    */ public final class WorksheetSupport {
/*    */   public static String $show(Object paramObject) {
/*    */     return WorksheetSupport$.MODULE$.$show(paramObject);
/*    */   }
/*    */   
/*    */   public static Nothing$ $stop() {
/*    */     return WorksheetSupport$.MODULE$.$stop();
/*    */   }
/*    */   
/*    */   public static void $skip(int paramInt) {
/*    */     WorksheetSupport$.MODULE$.$skip(paramInt);
/*    */   }
/*    */   
/*    */   public static void $execute(Function0<BoxedUnit> paramFunction0) {
/*    */     WorksheetSupport$.MODULE$.$execute(paramFunction0);
/*    */   }
/*    */   
/*    */   public static class FlushedOutputStream extends OutputStream {
/*    */     private final OutputStream out;
/*    */     
/*    */     public long flushInterval() {
/* 18 */       return 30000000L;
/*    */     }
/*    */     
/*    */     public int width() {
/* 19 */       return 80;
/*    */     }
/*    */     
/*    */     public int tabInc() {
/* 20 */       return 8;
/*    */     }
/*    */     
/* 21 */     private long lastFlush = 0L;
/*    */     
/*    */     private long lastFlush() {
/* 21 */       return this.lastFlush;
/*    */     }
/*    */     
/*    */     private void lastFlush_$eq(long x$1) {
/* 21 */       this.lastFlush = x$1;
/*    */     }
/*    */     
/* 22 */     private int col = -1;
/*    */     
/*    */     private int col() {
/* 22 */       return this.col;
/*    */     }
/*    */     
/*    */     private void col_$eq(int x$1) {
/* 22 */       this.col = x$1;
/*    */     }
/*    */     
/*    */     public void write(byte[] b, int off, int len) {
/* 24 */       Predef$ predef$1 = Predef$.MODULE$;
/* 24 */       int i = off + len;
/* 24 */       Predef$ predef$2 = Predef$.MODULE$;
/* 24 */       int j = RichInt$.MODULE$.min$extension(i, b.length);
/* 24 */       Range$ range$ = Range$.MODULE$;
/* 24 */       WorksheetSupport$FlushedOutputStream$$anonfun$write$1 worksheetSupport$FlushedOutputStream$$anonfun$write$1 = new WorksheetSupport$FlushedOutputStream$$anonfun$write$1(this, b);
/*    */       Range range;
/* 24 */       if ((range = new Range(off, j, 1)).validateRangeBoundaries(worksheetSupport$FlushedOutputStream$$anonfun$write$1)) {
/*    */         int terminal1;
/*    */         int step1;
/*    */         int i1;
/* 24 */         for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/* 24 */           writeOne(b[i1]);
/* 24 */           i1 += step1;
/*    */         } 
/*    */       } 
/* 25 */       flush();
/*    */     }
/*    */     
/*    */     public class WorksheetSupport$FlushedOutputStream$$anonfun$write$1 extends AbstractFunction1$mcVI$sp implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final byte[] b$1;
/*    */       
/*    */       public final void apply(int idx) {
/*    */         this.$outer.writeOne(this.b$1[idx]);
/*    */       }
/*    */       
/*    */       public void apply$mcVI$sp(int idx) {
/*    */         this.$outer.writeOne(this.b$1[idx]);
/*    */       }
/*    */       
/*    */       public WorksheetSupport$FlushedOutputStream$$anonfun$write$1(WorksheetSupport.FlushedOutputStream $outer, byte[] b$1) {}
/*    */     }
/*    */     
/*    */     public void write(int c) {
/* 28 */       writeOne(c);
/* 29 */       flush();
/*    */     }
/*    */     
/*    */     public void flush() {
/* 32 */       long current = System.nanoTime();
/* 33 */       if (current - lastFlush() >= flushInterval()) {
/* 34 */         this.out.flush();
/* 35 */         lastFlush_$eq(current);
/*    */       } 
/*    */     }
/*    */     
/*    */     public void writeOne(int c) {
/* 39 */       if (col() < 0) {
/* 40 */         col_$eq(0);
/* 41 */         write((new StringBuilder()).append(WorksheetSupport$.MODULE$.scala$runtime$WorksheetSupport$$currentOffset()).append(" ").toString().getBytes());
/*    */       } 
/* 43 */       this.out.write(c);
/* 44 */       col_$eq(
/* 45 */           (c == 10) ? -1 : (
/* 46 */           (c == 9) ? (col() / tabInc() * tabInc() + tabInc()) : (
/* 47 */           col() + 1)));
/* 48 */       if (col() >= width())
/* 48 */         writeOne(10); 
/*    */     }
/*    */     
/*    */     public void ensureNewLine() {
/* 50 */       if (col() > 0)
/* 50 */         writeOne(10); 
/*    */     }
/*    */     
/*    */     public FlushedOutputStream(OutputStream out) {}
/*    */   }
/*    */   
/*    */   public static class WorksheetSupport$$anonfun$$execute$1 extends AbstractFunction0$mcV$sp implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Function0 op$1;
/*    */     
/*    */     public final void apply() {
/* 76 */       apply$mcV$sp();
/*    */     }
/*    */     
/*    */     public void apply$mcV$sp() {
/*    */       try {
/* 76 */         this.op$1.apply$mcV$sp();
/* 76 */       } catch (StopException stopException) {
/*    */       
/*    */       } finally {}
/*    */     }
/*    */     
/*    */     public WorksheetSupport$$anonfun$$execute$1(Function0 op$1) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\WorksheetSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */