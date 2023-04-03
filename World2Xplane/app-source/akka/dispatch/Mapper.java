/*     */ package akka.dispatch;
/*     */ 
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0213Q!\001\002\002\002\035\021a!T1qa\026\024(BA\002\005\003!!\027n\0359bi\016D'\"A\003\002\t\005\\7.Y\002\001+\rA1CH\n\003\001%\001BAC\b\022;5\t1B\003\002\r\033\0059!/\0368uS6,'\"\001\b\002\013M\034\027\r\\1\n\005AY!!E!cgR\024\030m\031;Gk:\034G/[8ocA\021!c\005\007\001\t\031!\002\001#b\001+\t\tA+\005\002\0275A\021q\003G\007\002\033%\021\021$\004\002\b\035>$\b.\0338h!\t92$\003\002\035\033\t\031\021I\\=\021\005IqBAB\020\001\t\013\007QCA\001S\021\025\t\003\001\"\001#\003\031a\024N\\5u}Q\t1\005\005\003%\001EiR\"\001\002\t\013\031\002A\021A\024\002\013\005\004\b\017\\=\025\005uA\003\"B\025&\001\004\t\022!\0039be\006lW\r^3s\021\025Y\003\001\"\001-\0031\031\007.Z2lK\022\f\005\017\0357z)\tiR\006C\003*U\001\007\021\003K\002+_q\0022a\006\0313\023\t\tTB\001\004uQJ|wo\035\t\003%M\"Q\001\006\001C\002Q\n\"AF\033\021\005YJdBA\f8\023\tAT\"A\004qC\016\\\027mZ3\n\005iZ$!\003+ie><\030M\0317f\025\tATbI\001>!\tq\024H\004\002@o9\021\001iQ\007\002\003*\021!IB\001\007yI|w\016\036 \n\0039\001")
/*     */ public abstract class Mapper<T, R> extends AbstractFunction1<T, R> {
/*     */   public R apply(Object parameter) {
/* 347 */     return checkedApply((T)parameter);
/*     */   }
/*     */   
/*     */   public R checkedApply(Object parameter) throws Throwable {
/* 355 */     throw new UnsupportedOperationException("Mapper.checkedApply has not been implemented");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\Mapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */