/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.generic.Growable;
/*    */ import scala.collection.generic.Shrinkable;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.immutable.Set;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005=a\001B\001\003\001%\0211#S7nkR\f'\r\\3TKR\fE-\0319u_JT!a\001\003\002\0175,H/\0312mK*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001U\021!\"E\n\005\001-Yb\004E\002\r\033=i\021AA\005\003\035\t\0211\"\0212tiJ\f7\r^*fiB\021\001#\005\007\001\t\025\021\002A1\001\024\005\005\t\025C\001\013\031!\t)b#D\001\007\023\t9bAA\004O_RD\027N\\4\021\005UI\022B\001\016\007\005\r\te.\037\t\004\031qy\021BA\017\003\005\r\031V\r\036\t\003+}I!\001\t\004\003\031M+'/[1mSj\f'\r\\3\t\021\t\002!\0211A\005\022\r\n1a]3u+\005!\003cA\023)\0375\taE\003\002(\t\005I\021.\\7vi\006\024G.Z\005\003;\031B\001B\013\001\003\002\004%\tbK\001\bg\026$x\fJ3r)\tas\006\005\002\026[%\021aF\002\002\005+:LG\017C\0041S\005\005\t\031\001\023\002\007a$\023\007\003\0053\001\t\005\t\025)\003%\003\021\031X\r\036\021\t\013Q\002A\021A\033\002\rqJg.\033;?)\t1t\007E\002\r\001=AQAI\032A\002\021BQ!\017\001\005Bi\nAa]5{KV\t1\b\005\002\026y%\021QH\002\002\004\023:$\b\"B \001\t\003\002\025aB5t\0136\004H/_\013\002\003B\021QCQ\005\003\007\032\021qAQ8pY\026\fg\016C\003F\001\021\005a)\001\005d_:$\030-\0338t)\t\tu\tC\003I\t\002\007q\"\001\003fY\026l\007\"\002&\001\t\003Z\025a\0024pe\026\f7\r[\013\003\031N#\"\001L'\t\0139K\005\031A(\002\003\031\004B!\006)\020%&\021\021K\002\002\n\rVt7\r^5p]F\002\"\001E*\005\013QK%\031A\n\003\003UCQA\026\001\005B]\013a!\032=jgR\034HCA!Y\021\025IV\0131\001[\003\005\001\b\003B\013Q\037\005CQ\001\030\001\005Bu\013a\001^8MSN$X#\0010\021\007};wB\004\002aK:\021\021\rZ\007\002E*\0211\rC\001\007yI|w\016\036 \n\003\035I!A\032\004\002\017A\f7m[1hK&\021\001.\033\002\005\031&\034HO\003\002g\r!)1\016\001C!Y\006AAo\\*ue&tw\rF\001n!\tq\027O\004\002\026_&\021\001OB\001\007!J,G-\0324\n\005I\034(AB*ue&twM\003\002q\r!)Q\017\001C\001m\006A\021\016^3sCR|'/F\001x!\rA\030pD\007\002\t%\021!\020\002\002\t\023R,'/\031;pe\")A\020\001C\001{\006AA\005\0357vg\022*\027\017\006\0026\t\001\001C\003Iw\002\007q\002C\004\002\004\001!\t!!\002\002\023\021j\027N\\;tI\025\fHc\001@\002\b!1\001*!\001A\002=Aq!a\003\001\t\003\ni!A\003dY\026\f'\017F\001-\001")
/*    */ public class ImmutableSetAdaptor<A> extends AbstractSet<A> implements Set<A>, Serializable {
/*    */   private Set<A> set;
/*    */   
/*    */   public Set<A> set() {
/* 25 */     return this.set;
/*    */   }
/*    */   
/*    */   public void set_$eq(Set<A> x$1) {
/* 25 */     this.set = x$1;
/*    */   }
/*    */   
/*    */   public ImmutableSetAdaptor(Set<A> set) {}
/*    */   
/*    */   public int size() {
/* 30 */     return set().size();
/*    */   }
/*    */   
/*    */   public boolean isEmpty() {
/* 32 */     return set().isEmpty();
/*    */   }
/*    */   
/*    */   public boolean contains(Object elem) {
/* 34 */     return set().contains(elem);
/*    */   }
/*    */   
/*    */   public <U> void foreach(Function1 f) {
/* 36 */     set().foreach(f);
/*    */   }
/*    */   
/*    */   public boolean exists(Function1 p) {
/* 38 */     return set().exists(p);
/*    */   }
/*    */   
/*    */   public List<A> toList() {
/* 40 */     return set().toList();
/*    */   }
/*    */   
/*    */   public String toString() {
/* 42 */     return set().toString();
/*    */   }
/*    */   
/*    */   public Iterator<A> iterator() {
/* 44 */     return set().iterator();
/*    */   }
/*    */   
/*    */   public ImmutableSetAdaptor<A> $plus$eq(Object elem) {
/* 46 */     set_$eq((Set<A>)set().$plus(elem));
/* 46 */     return this;
/*    */   }
/*    */   
/*    */   public ImmutableSetAdaptor<A> $minus$eq(Object elem) {
/* 48 */     set_$eq((Set<A>)set().$minus(elem));
/* 48 */     return this;
/*    */   }
/*    */   
/*    */   public void clear() {
/* 50 */     set_$eq((Set<A>)set().empty());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\ImmutableSetAdaptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */