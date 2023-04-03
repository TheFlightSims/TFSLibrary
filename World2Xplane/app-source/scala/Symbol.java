/*    */ package scala;
/*    */ 
/*    */ import java.io.ObjectStreamException;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001M4A!\001\002\003\013\t11+_7c_2T\021aA\001\006g\016\fG.Y\002\001'\r\001aA\003\t\003\017!i\021AA\005\003\023\t\021a!\0218z%\0264\007CA\004\f\023\ta!A\001\007TKJL\027\r\\5{C\ndW\r\003\005\017\001\t\025\r\021\"\001\020\003\021q\027-\\3\026\003A\001\"!\005\013\017\005\035\021\022BA\n\003\003\031\001&/\0323fM&\021QC\006\002\007'R\024\030N\\4\013\005M\021\001\002\003\r\001\005\003\005\013\021\002\t\002\0139\fW.\032\021\t\013i\001A\021B\016\002\rqJg.\033;?)\taR\004\005\002\b\001!)a\"\007a\001!!)q\004\001C!A\005AAo\\*ue&tw\rF\001\021\021\025\021\003\001\"\003$\003-\021X-\0313SKN|GN^3\025\003\021\002\"aB\023\n\005\031\022!aA!os\"\032\021\005\013\036\021\007\035I3&\003\002+\005\t1A\017\033:poN\004\"\001L\027\r\001\021)a\006\001b\001_\t\tA+\005\0021gA\021q!M\005\003e\t\021qAT8uQ&tw\r\005\0025o9\021q!N\005\003m\t\tq\001]1dW\006<W-\003\0029s\tIA\013\033:po\006\024G.\032\006\003m\t\031\023a\017\t\003y\005k\021!\020\006\003}}\n!![8\013\003\001\013AA[1wC&\021!)\020\002\026\037\nTWm\031;TiJ,\027-\\#yG\026\004H/[8o\021\025!\005\001\"\021F\003!A\027m\0355D_\022,G#\001$\021\005\0359\025B\001%\003\005\rIe\016\036\005\006\025\002!\teS\001\007KF,\030\r\\:\025\0051{\005CA\004N\023\tq%AA\004C_>dW-\0318\t\013AK\005\031\001\023\002\013=$\b.\032:\b\013I\023\001\022A*\002\rMKXNY8m!\t9AKB\003\002\005!\005QkE\002U-*\001BaB,\0219%\021\001L\001\002\020+:L\027/^3oKN\0348)Y2iK\")!\004\026C\0015R\t1\013C\003])\022\005S,A\003baBd\027\020\006\002\035=\")ab\027a\001!!)\001\r\026C\tC\006aa/\0317vK\032\023x.\\&fsR\021AD\031\005\006\035}\003\r\001\005\005\006IR#\t\"Z\001\rW\026LhI]8n-\006dW/\032\013\003M&\0042aB4\021\023\tA'A\001\004PaRLwN\034\005\006U\016\004\r\001H\001\004gfl\007b\002\022U\003\003%I\001\034\013\002[B\021a.]\007\002_*\021\001oP\001\005Y\006tw-\003\002s_\n1qJ\0316fGR\004")
/*    */ public final class Symbol implements Serializable {
/*    */   private final String name;
/*    */   
/*    */   public String name() {
/* 23 */     return this.name;
/*    */   }
/*    */   
/*    */   public Symbol(String name) {}
/*    */   
/*    */   public String toString() {
/* 26 */     return (new StringBuilder()).append("'").append(name()).toString();
/*    */   }
/*    */   
/*    */   private Object readResolve() throws ObjectStreamException {
/* 29 */     return Symbol$.MODULE$.apply(name());
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 30 */     return name().hashCode();
/*    */   }
/*    */   
/*    */   public boolean equals(Object other) {
/* 31 */     return (this == other);
/*    */   }
/*    */   
/*    */   public static Symbol apply(String paramString) {
/*    */     return Symbol$.MODULE$.apply(paramString);
/*    */   }
/*    */   
/*    */   public static Option<String> unapply(Symbol paramSymbol) {
/*    */     return Symbol$.MODULE$.unapply(paramSymbol);
/*    */   }
/*    */   
/*    */   public static Object apply(Object paramObject) {
/*    */     return Symbol$.MODULE$.apply(paramObject);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Symbol.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */