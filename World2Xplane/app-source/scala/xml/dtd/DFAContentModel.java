/*     */ package scala.xml.dtd;
/*     */ 
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.util.automata.DetWordAutom;
/*     */ import scala.util.automata.NondetWordAutom;
/*     */ import scala.util.automata.SubsetConstruction;
/*     */ import scala.util.regexp.Base;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001U2Q!\001\002\002\"%\021q\002\022$B\007>tG/\0328u\033>$W\r\034\006\003\007\021\t1\001\032;e\025\t)a!A\002y[2T\021aB\001\006g\016\fG.Y\002\001'\t\001!\002\005\002\f\0315\t!!\003\002\016\005\ta1i\0348uK:$Xj\0343fY\")q\002\001C\001!\0051A(\0338jiz\"\022!\005\t\003\027\001AQa\005\001\007\002Q\t\021A]\013\002+A\021a#\007\b\003\027]I!\001\007\002\002\031\r{g\016^3oi6{G-\0327\n\005iY\"A\002*fO\026C\b/\003\002\035;\t!!)Y:f\025\tqr$\001\004sK\036,\007\020\035\006\003A\031\tA!\036;jY\"A!\005\001EC\002\023\0051%A\002eM\006,\022\001\n\t\004K!RS\"\001\024\013\005\035z\022\001C1vi>l\027\r^1\n\005%2#\001\004#fi^{'\017Z!vi>l\007C\001\f,\023\taSF\001\005FY\026lg*Y7f\025\tA\"\001\003\0050\001!\005\t\025)\003%\003\021!g-\031\021*\007\001\t4'\003\0023\005\tAQ\tT#N\013:#6+\003\0025\005\t)Q*\023-F\t\002")
/*     */ public abstract class DFAContentModel extends ContentModel {
/*     */   private DetWordAutom<ContentModel.ElemName> dfa;
/*     */   
/*     */   private volatile boolean bitmap$0;
/*     */   
/*     */   private DetWordAutom dfa$lzycompute() {
/*  99 */     synchronized (this) {
/*  99 */       if (!this.bitmap$0) {
/* 100 */         NondetWordAutom nfa = ContentModel.Translator$.MODULE$.automatonFrom(r(), 1);
/* 101 */         this.dfa = (new SubsetConstruction(nfa)).determinize();
/*     */         this.bitmap$0 = true;
/*     */       } 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/xml/dtd/DFAContentModel}} */
/*     */       return this.dfa;
/*     */     } 
/*     */   }
/*     */   
/*     */   public abstract Base.RegExp r();
/*     */   
/*     */   public DetWordAutom<ContentModel.ElemName> dfa() {
/*     */     return this.bitmap$0 ? this.dfa : dfa$lzycompute();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\DFAContentModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */