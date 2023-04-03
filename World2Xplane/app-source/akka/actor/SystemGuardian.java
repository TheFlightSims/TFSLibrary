/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\rqAB\001\003\021\003!a!\001\bTsN$X-\\$vCJ$\027.\0318\013\005\r!\021!B1di>\024(\"A\003\002\t\005\\7.\031\t\003\017!i\021A\001\004\007\023\tA\t\001\002\006\003\035MK8\017^3n\017V\f'\017Z5b]N\021\001b\003\t\003\031=i\021!\004\006\002\035\005)1oY1mC&\021\001#\004\002\007\003:L(+\0324\t\013IAA\021\001\013\002\rqJg.\033;?\007\001!\022AB\004\006-!A\tiF\001\030%\026<\027n\035;feR+'/\\5oCRLwN\034%p_.\004\"\001G\r\016\003!1QA\007\005\t\002n\021qCU3hSN$XM\035+fe6Lg.\031;j_:Dun\\6\024\teYAd\b\t\003\031uI!AH\007\003\017A\023x\016Z;diB\021A\002I\005\003C5\021AbU3sS\006d\027N_1cY\026DQAE\r\005\002\r\"\022a\006\005\bKe\t\t\021\"\021'\0035\001(o\0343vGR\004&/\0324jqV\tq\005\005\002)[5\t\021F\003\002+W\005!A.\0318h\025\005a\023\001\0026bm\006L!AL\025\003\rM#(/\0338h\021\035\001\024$!A\005\002E\nA\002\035:pIV\034G/\021:jif,\022A\r\t\003\031MJ!\001N\007\003\007%sG\017C\00473\005\005I\021A\034\002\035A\024x\016Z;di\026cW-\\3oiR\021\001h\017\t\003\031eJ!AO\007\003\007\005s\027\020C\004=k\005\005\t\031\001\032\002\007a$\023\007C\004?3\005\005I\021I \002\037A\024x\016Z;di&#XM]1u_J,\022\001\021\t\004\003\022CT\"\001\"\013\005\rk\021AC2pY2,7\r^5p]&\021QI\021\002\t\023R,'/\031;pe\"9q)GA\001\n\003A\025\001C2b]\026\013X/\0317\025\005%c\005C\001\007K\023\tYUBA\004C_>dW-\0318\t\017q2\025\021!a\001q!9a*GA\001\n\003z\025\001\0035bg\"\034u\016Z3\025\003IBq!U\r\002\002\023\005#+\001\005u_N#(/\0338h)\0059\003b\002+\032\003\003%I!V\001\fe\026\fGMU3t_24X\rF\001W!\tAs+\003\002YS\t1qJ\0316fGR<QA\027\005\t\002n\013q\002V3s[&t\027\r^5p]\"{wn\033\t\0031q3Q!\030\005\t\002z\023q\002V3s[&t\027\r^5p]\"{wn[\n\0059.ar\004C\003\0239\022\005\001\rF\001\\\021\035)C,!A\005B\031Bq\001\r/\002\002\023\005\021\007C\00479\006\005I\021\0013\025\005a*\007b\002\037d\003\003\005\rA\r\005\b}q\013\t\021\"\021@\021\0359E,!A\005\002!$\"!S5\t\017q:\027\021!a\001q!9a\nXA\001\n\003z\005bB)]\003\003%\tE\025\005\b)r\013\t\021\"\003V\017\025q\007\002#!p\003M!VM]7j]\006$\030n\0348I_>\\Gi\0348f!\tA\002OB\003r\021!\005%OA\nUKJl\027N\\1uS>t\007j\\8l\t>tWm\005\003q\027qy\002\"\002\nq\t\003!H#A8\t\017\025\002\030\021!C!M!9\001\007]A\001\n\003\t\004b\002\034q\003\003%\t\001\037\013\003qeDq\001P<\002\002\003\007!\007C\004?a\006\005I\021I \t\017\035\003\030\021!C\001yR\021\021* \005\bym\f\t\0211\0019\021\035q\005/!A\005B=Cq!\0259\002\002\023\005#\013C\004Ua\006\005I\021B+")
/*     */ public final class SystemGuardian {
/*     */   public static class RegisterTerminationHook$ implements Product, Serializable {
/*     */     public static final RegisterTerminationHook$ MODULE$;
/*     */     
/*     */     public String productPrefix() {
/* 357 */       return "RegisterTerminationHook";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 357 */       return 0;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 357 */       int i = x$1;
/* 357 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 357 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 357 */       return x$1 instanceof RegisterTerminationHook$;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 357 */       return 2047210756;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 357 */       return "RegisterTerminationHook";
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 357 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public RegisterTerminationHook$() {
/* 358 */       MODULE$ = this;
/* 358 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class TerminationHook$ implements Product, Serializable {
/*     */     public static final TerminationHook$ MODULE$;
/*     */     
/*     */     public String productPrefix() {
/* 358 */       return "TerminationHook";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 358 */       return 0;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 358 */       int i = x$1;
/* 358 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 358 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 358 */       return x$1 instanceof TerminationHook$;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 358 */       return -724122073;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 358 */       return "TerminationHook";
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 358 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public TerminationHook$() {
/* 359 */       MODULE$ = this;
/* 359 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class TerminationHookDone$ implements Product, Serializable {
/*     */     public static final TerminationHookDone$ MODULE$;
/*     */     
/*     */     public String productPrefix() {
/* 359 */       return "TerminationHookDone";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 359 */       return 0;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 359 */       int i = x$1;
/* 359 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 359 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 359 */       return x$1 instanceof TerminationHookDone$;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 359 */       return 1649013321;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 359 */       return "TerminationHookDone";
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 359 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public TerminationHookDone$() {
/* 360 */       MODULE$ = this;
/* 360 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\SystemGuardian.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */