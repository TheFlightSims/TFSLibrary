/*    */ package scala.beans;
/*    */ 
/*    */ import java.beans.MethodDescriptor;
/*    */ import java.beans.PropertyDescriptor;
/*    */ import java.beans.SimpleBeanInfo;
/*    */ import java.lang.reflect.Method;
/*    */ import scala.Array$;
/*    */ import scala.Function1;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.reflect.ClassTag$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001q3Q!\001\002\002\002\035\021QbU2bY\006\024U-\0318J]\032|'BA\002\005\003\025\021W-\0318t\025\005)\021!B:dC2\f7\001A\n\003\001!\001\"!C\007\016\003)Q!aA\006\013\0031\tAA[1wC&\021aB\003\002\017'&l\007\017\\3CK\006t\027J\0344p\021!\001\002A!A!\002\023\t\022!B2mCjT\bG\001\n\033!\r\031b\003G\007\002))\021QcC\001\005Y\006tw-\003\002\030)\t)1\t\\1tgB\021\021D\007\007\001\t%Yr\"!A\001\002\013\005ADA\002`IE\n\"!H\021\021\005yyR\"\001\003\n\005\001\"!a\002(pi\"Lgn\032\t\003=\tJ!a\t\003\003\007\005s\027\020\003\005&\001\t\005\t\025!\003'\003\025\001(o\0349t!\rqr%K\005\003Q\021\021Q!\021:sCf\004\"AK\027\017\005yY\023B\001\027\005\003\031\001&/\0323fM&\021af\f\002\007'R\024\030N\\4\013\0051\"\001\002C\031\001\005\003\005\013\021\002\024\002\0175,G\017[8eg\")1\007\001C\001i\0051A(\0338jiz\"B!N\034={A\021a\007A\007\002\005!)\001C\ra\001qA\022\021h\017\t\004'YQ\004CA\r<\t%Yr'!A\001\002\013\005A\004C\003&e\001\007a\005C\0032e\001\007a\005C\004@\001\t\007I\021\002!\002\005A$W#A!\021\007y9#\t\005\002\n\007&\021AI\003\002\023!J|\007/\032:us\022+7o\031:jaR|'\017\003\004G\001\001\006I!Q\001\004a\022\004\003b\002%\001\005\004%I!S\001\003[\022,\022A\023\t\004=\035Z\005CA\005M\023\ti%B\001\tNKRDw\016\032#fg\016\024\030\016\035;pe\"1q\n\001Q\001\n)\0131!\0343!\021\025\t\006\001\"\021S\003Y9W\r\036)s_B,'\017^=EKN\034'/\0339u_J\034H#A!\t\013Q\003A\021I+\002)\035,G/T3uQ>$G)Z:de&\004Ho\034:t)\005Q\005\"B,\001\t\023A\026\001B5oSR$\022!\027\t\003=iK!a\027\003\003\tUs\027\016\036")
/*    */ public abstract class ScalaBeanInfo extends SimpleBeanInfo {
/*    */   private final Class<?> clazz;
/*    */   
/*    */   private final String[] props;
/*    */   
/*    */   public final String[] scala$beans$ScalaBeanInfo$$methods;
/*    */   
/*    */   private final PropertyDescriptor[] pd;
/*    */   
/*    */   private final MethodDescriptor[] md;
/*    */   
/*    */   public ScalaBeanInfo(Class<?> clazz, String[] props, String[] methods) {
/* 25 */     this.pd = new PropertyDescriptor[props.length / 3];
/* 26 */     this.md = 
/* 27 */       (MethodDescriptor[])Predef$.MODULE$.refArrayOps((Object[])clazz.getMethods()).withFilter((Function1)new $anonfun$1(this)).map((Function1)new $anonfun$2(this), Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.apply(MethodDescriptor.class)));
/* 30 */     init();
/*    */   }
/*    */   
/*    */   private PropertyDescriptor[] pd() {
/*    */     return this.pd;
/*    */   }
/*    */   
/*    */   private MethodDescriptor[] md() {
/*    */     return this.md;
/*    */   }
/*    */   
/*    */   public class $anonfun$1 extends AbstractFunction1<Method, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(Method m) {
/*    */       return Predef$.MODULE$.refArrayOps((Object[])this.$outer.scala$beans$ScalaBeanInfo$$methods).exists((Function1)new ScalaBeanInfo$$anonfun$1$$anonfun$apply$1(this, m));
/*    */     }
/*    */     
/*    */     public $anonfun$1(ScalaBeanInfo $outer) {}
/*    */     
/*    */     public class ScalaBeanInfo$$anonfun$1$$anonfun$apply$1 extends AbstractFunction1<String, Object> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final Method m$1;
/*    */       
/*    */       public final boolean apply(String x$1) {
/*    */         String str = this.m$1.getName();
/*    */         if (x$1 == null) {
/*    */           if (str != null);
/*    */         } else if (x$1.equals(str)) {
/*    */         
/*    */         } 
/*    */       }
/*    */       
/*    */       public ScalaBeanInfo$$anonfun$1$$anonfun$apply$1(ScalaBeanInfo.$anonfun$1 $outer, Method m$1) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class $anonfun$2 extends AbstractFunction1<Method, MethodDescriptor> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public $anonfun$2(ScalaBeanInfo $outer) {}
/*    */     
/*    */     public final MethodDescriptor apply(Method m) {
/*    */       return new MethodDescriptor(m);
/*    */     }
/*    */   }
/*    */   
/*    */   public PropertyDescriptor[] getPropertyDescriptors() {
/* 32 */     return pd();
/*    */   }
/*    */   
/*    */   public MethodDescriptor[] getMethodDescriptors() {
/* 33 */     return md();
/*    */   }
/*    */   
/*    */   private void init() {
/* 38 */     int i = 0;
/* 39 */     while (i < this.props.length) {
/* 40 */       pd()[i / 3] = new PropertyDescriptor(this.props[i], this.clazz, this.props[i + 1], this.props[i + 2]);
/* 41 */       i += 3;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\beans\ScalaBeanInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */