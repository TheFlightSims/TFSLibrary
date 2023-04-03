package scala.collection.immutable;

import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001Q;a!\001\002\t\002\tA\021\001\004'p]\036l\025\r]+uS2\034(BA\002\005\003%IW.\\;uC\ndWM\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\004\"!\003\006\016\003\t1aa\003\002\t\002\ta!\001\004'p]\036l\025\r]+uS2\0348c\001\006\016#A\021abD\007\002\r%\021\001C\002\002\007\003:L(+\0324\021\005IAbBA\n\027\033\005!\"BA\013\005\003\0359WM\\3sS\016L!a\006\013\002\033\tKGo\0249fe\006$\030n\0348t\023\tI\"D\001\003M_:<'BA\f\025\021\025a\"\002\"\001\037\003\031a\024N\\5u}\r\001A#\001\005\t\013\001RA\021A\021\002\025\t\024\030M\\2i\033\006\0348\016F\002#I%\002\"AD\022\n\005e1\001\"B\023 \001\0041\023!A5\021\005\035BS\"\001\006\n\005eA\002\"\002\026 \001\0041\023!\0016\t\0131RA\021A\027\002\t)|\027N\\\013\003]Q\"RaL\037@\003\016\0032!\003\0313\023\t\t$AA\004M_:<W*\0319\021\005M\"D\002\001\003\006k-\022\rA\016\002\002)F\021qG\017\t\003\035aJ!!\017\004\003\0179{G\017[5oOB\021abO\005\003y\031\0211!\0218z\021\025q4\0061\001'\003\t\001\030\007C\003AW\001\007q&\001\002uc!)!i\013a\001M\005\021\001O\r\005\006\t.\002\raL\001\003iJBQA\022\006\005\002\035\0131AY5o+\tA5\nF\003J\031:\003&\013E\002\na)\003\"aM&\005\013U*%\031\001\034\t\0135+\005\031\001\024\002\rA\024XMZ5y\021\025yU\t1\001'\003\021i\027m]6\t\013E+\005\031A%\002\t1,g\r\036\005\006'\026\003\r!S\001\006e&<\007\016\036")
public final class LongMapUtils {
  public static String bitString$default$2() {
    return LongMapUtils$.MODULE$.bitString$default$2();
  }
  
  public static long highestOneBit(long paramLong) {
    return LongMapUtils$.MODULE$.highestOneBit(paramLong);
  }
  
  public static String bitString(long paramLong, String paramString) {
    return LongMapUtils$.MODULE$.bitString(paramLong, paramString);
  }
  
  public static IndexedSeq<Object> bits(long paramLong) {
    return LongMapUtils$.MODULE$.bits(paramLong);
  }
  
  public static long complement(long paramLong) {
    return LongMapUtils$.MODULE$.complement(paramLong);
  }
  
  public static boolean shorter(long paramLong1, long paramLong2) {
    return LongMapUtils$.MODULE$.shorter(paramLong1, paramLong2);
  }
  
  public static boolean unsignedCompare(long paramLong1, long paramLong2) {
    return LongMapUtils$.MODULE$.unsignedCompare(paramLong1, paramLong2);
  }
  
  public static boolean hasMatch(long paramLong1, long paramLong2, long paramLong3) {
    return LongMapUtils$.MODULE$.hasMatch(paramLong1, paramLong2, paramLong3);
  }
  
  public static long mask(long paramLong1, long paramLong2) {
    return LongMapUtils$.MODULE$.mask(paramLong1, paramLong2);
  }
  
  public static boolean zero(long paramLong1, long paramLong2) {
    return LongMapUtils$.MODULE$.zero(paramLong1, paramLong2);
  }
  
  public static <T> LongMap<T> bin(long paramLong1, long paramLong2, LongMap<T> paramLongMap1, LongMap<T> paramLongMap2) {
    return LongMapUtils$.MODULE$.bin(paramLong1, paramLong2, paramLongMap1, paramLongMap2);
  }
  
  public static <T> LongMap<T> join(long paramLong1, LongMap<T> paramLongMap1, long paramLong2, LongMap<T> paramLongMap2) {
    return LongMapUtils$.MODULE$.join(paramLong1, paramLongMap1, paramLong2, paramLongMap2);
  }
  
  public static long branchMask(long paramLong1, long paramLong2) {
    return LongMapUtils$.MODULE$.branchMask(paramLong1, paramLong2);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\LongMapUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */