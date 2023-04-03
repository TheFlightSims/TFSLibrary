/*    */ package scala.reflect.macros.internal;
/*    */ 
/*    */ import scala.annotation.Annotation;
/*    */ import scala.annotation.StaticAnnotation;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0212Q!\001\002\001\021)\021\021\"\\1de>LU\016\0357\013\005\r!\021\001C5oi\026\024h.\0317\013\005\0251\021AB7bGJ|7O\003\002\b\021\0059!/\0324mK\016$(\"A\005\002\013M\034\027\r\\1\024\007\001Y\021\003\005\002\r\0375\tQB\003\002\017\021\005Q\021M\0348pi\006$\030n\0348\n\005Ai!AC!o]>$\030\r^5p]B\021ABE\005\003'5\021\001c\025;bi&\034\027I\0348pi\006$\030n\0348\t\021U\001!Q1A\005\002]\tAC]3gKJ,gnY3U_6\0137M]8J[Bd7\001A\013\0021A\021\021DG\007\002\021%\0211\004\003\002\004\003:L\b\002C\017\001\005\003\005\013\021\002\r\002+I,g-\032:f]\016,Gk\\'bGJ|\027*\0349mA!)q\004\001C\001A\0051A(\0338jiz\"\"!I\022\021\005\t\002Q\"\001\002\t\013Uq\002\031\001\r")
/*    */ public class macroImpl extends Annotation implements StaticAnnotation {
/*    */   private final Object referenceToMacroImpl;
/*    */   
/*    */   public Object referenceToMacroImpl() {
/* 18 */     return this.referenceToMacroImpl;
/*    */   }
/*    */   
/*    */   public macroImpl(Object referenceToMacroImpl) {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\reflect\macros\internal\macroImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */