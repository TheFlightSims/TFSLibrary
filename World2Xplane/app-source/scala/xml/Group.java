/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Predef$;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.Nothing$;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\005d\001B\001\003\005\036\021Qa\022:pkBT!a\001\003\002\007alGNC\001\006\003\025\0318-\0317b\007\001\031B\001\001\005\r!A\021\021BC\007\002\005%\0211B\001\002\005\035>$W\r\005\002\016\0355\tA!\003\002\020\t\t9\001K]8ek\016$\bCA\007\022\023\t\021BA\001\007TKJL\027\r\\5{C\ndW\r\003\005\025\001\tU\r\021\"\001\026\003\025qw\016Z3t+\0051\002cA\f \0219\021\001$\b\b\0033qi\021A\007\006\0037\031\ta\001\020:p_Rt\024\"A\003\n\005y!\021a\0029bG.\fw-Z\005\003A\005\0221aU3r\025\tqB\001\003\005$\001\tE\t\025!\003\027\003\031qw\016Z3tA!)Q\005\001C\001M\0051A(\0338jiz\"\"a\n\025\021\005%\001\001\"\002\013%\001\0041\002\"\002\026\001\t\003*\022A\002;iKN+\027\017C\003-\001\021\005S&\001\005dC:,\025/^1m)\tq\023\007\005\002\016_%\021\001\007\002\002\b\005>|G.Z1o\021\025\0214\0061\0014\003\025yG\017[3s!\tiA'\003\0026\t\t\031\021I\\=\t\013]\002A\021\t\035\002\033M$(/[2u?\022*\027\017J3r)\tq\023\bC\0033m\001\007!\b\005\002\nw%\021AH\001\002\t\013F,\030\r\\5us\")a\b\001C)+\005\001\"-Y:jg\032{'\017S1tQ\016{G-\032\005\006\001\002!I!Q\001\005M\006LG\016\006\002C\013B\021QbQ\005\003\t\022\021qAT8uQ&tw\rC\003G\001\007q)A\002ng\036\004\"\001S&\017\0055I\025B\001&\005\003\031\001&/\0323fM&\021A*\024\002\007'R\024\030N\\4\013\005)#\001\"B(\001\t\003\001\026!\0027bE\026dW#\001\"\t\013I\003A\021\t)\002\025\005$HO]5ckR,7\017C\003U\001\021\005\003+A\005oC6,7\017]1dK\")a\013\001C!!\006)1\r[5mI\")\001\f\001C\0013\006Y!-^5mIN#(/\0338h)\t\021%\fC\003\\/\002\007A,\001\002tEB\021q#X\005\003=\006\022Qb\025;sS:<')^5mI\026\024\bb\0021\001\003\003%\t!Y\001\005G>\004\030\020\006\002(E\"9Ac\030I\001\002\0041\002b\0023\001#\003%\t!Z\001\017G>\004\030\020\n3fM\006,H\016\036\0232+\0051'F\001\fhW\005A\007CA5o\033\005Q'BA6m\003%)hn\0315fG.,GM\003\002n\t\005Q\021M\0348pi\006$\030n\0348\n\005=T'!E;oG\",7m[3e-\006\024\030.\0318dK\"9\021\017AA\001\n\003\022\030!\0049s_\022,8\r\036)sK\032L\0070F\001t!\t!\0300D\001v\025\t1x/\001\003mC:<'\"\001=\002\t)\fg/Y\005\003\031VDqa\037\001\002\002\023\005A0\001\007qe>$Wo\031;Be&$\0300F\001~!\tia0\003\002\000\t\t\031\021J\034;\t\023\005\r\001!!A\005\002\005\025\021A\0049s_\022,8\r^#mK6,g\016\036\013\004g\005\035\001\"CA\005\003\003\t\t\0211\001~\003\rAH%\r\005\n\003\033\001\021\021!C!\003\037\tq\002\035:pIV\034G/\023;fe\006$xN]\013\003\003#\001R!a\005\002\032Mj!!!\006\013\007\005]A!\001\006d_2dWm\031;j_:LA!a\007\002\026\tA\021\n^3sCR|'oB\005\002 \t\t\t\021#\001\002\"\005)qI]8vaB\031\021\"a\t\007\021\005\021\021\021!E\001\003K\031R!a\t\002(A\001b!!\013\0020Y9SBAA\026\025\r\ti\003B\001\beVtG/[7f\023\021\t\t$a\013\003#\005\0237\017\036:bGR4UO\\2uS>t\027\007C\004&\003G!\t!!\016\025\005\005\005\002BCA\035\003G\t\t\021\"\022\002<\005AAo\\*ue&tw\rF\001t\021)\ty$a\t\002\002\023\005\025\021I\001\006CB\004H.\037\013\004O\005\r\003B\002\013\002>\001\007a\003\003\006\002H\005\r\022\021!CA\003\023\nq!\0368baBd\027\020\006\003\002L\005E\003\003B\007\002NYI1!a\024\005\005\031y\005\017^5p]\"I\0211KA#\003\003\005\raJ\001\004q\022\002\004BCA,\003G\t\t\021\"\003\002Z\005Y!/Z1e%\026\034x\016\034<f)\t\tY\006E\002u\003;J1!a\030v\005\031y%M[3di\002")
/*    */ public final class Group extends Node implements Product, Serializable {
/*    */   private final Seq<Node> nodes;
/*    */   
/*    */   public Seq<Node> nodes() {
/* 16 */     return this.nodes;
/*    */   }
/*    */   
/*    */   public Group copy(Seq<Node> nodes) {
/* 16 */     return new Group(nodes);
/*    */   }
/*    */   
/*    */   public Seq<Node> copy$default$1() {
/* 16 */     return nodes();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 16 */     return "Group";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 16 */     return 1;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 16 */     switch (x$1) {
/*    */       default:
/* 16 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 16 */     return nodes();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 16 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public Group(Seq<Node> nodes) {
/* 16 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public Seq<Node> theSeq() {
/* 17 */     return nodes();
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object other) {
/*    */     boolean bool;
/* 19 */     if (other instanceof Group) {
/* 19 */       bool = true;
/*    */     } else {
/* 21 */       bool = false;
/*    */     } 
/*    */     return bool;
/*    */   }
/*    */   
/*    */   public boolean strict_$eq$eq(Equality other) {
/*    */     boolean bool;
/* 24 */     if (other instanceof Group) {
/* 24 */       Group group = (Group)other;
/* 25 */       bool = nodes().sameElements((GenIterable)group.nodes());
/*    */     } else {
/* 26 */       bool = false;
/*    */     } 
/*    */     return bool;
/*    */   }
/*    */   
/*    */   public Seq<Node> basisForHashCode() {
/* 29 */     return nodes();
/*    */   }
/*    */   
/*    */   private Nothing$ fail(String msg) {
/* 34 */     Predef$ predef$ = Predef$.MODULE$;
/* 34 */     throw new UnsupportedOperationException((new StringOps("class Group does not support method '%s'")).format(Predef$.MODULE$.genericWrapArray(new Object[] { msg })));
/*    */   }
/*    */   
/*    */   public Nothing$ label() {
/* 36 */     return fail("label");
/*    */   }
/*    */   
/*    */   public Nothing$ attributes() {
/* 37 */     return fail("attributes");
/*    */   }
/*    */   
/*    */   public Nothing$ namespace() {
/* 38 */     return fail("namespace");
/*    */   }
/*    */   
/*    */   public Nothing$ child() {
/* 39 */     return fail("child");
/*    */   }
/*    */   
/*    */   public Nothing$ buildString(StringBuilder sb) {
/* 40 */     return fail("toString(StringBuilder)");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Group.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */