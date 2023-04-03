package scala.collection.generic;

import java.util.concurrent.atomic.AtomicInteger;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001q2q!\001\002\021\002\007\005\021BA\bBi>l\027nY%oI\026Dh\t\\1h\025\t\031A!A\004hK:,'/[2\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\024\007\001Qa\002\005\002\f\0315\ta!\003\002\016\r\t1\021I\\=SK\032\004\"a\004\t\016\003\tI!!\005\002\003\025MKwM\\1mY&tw\rC\003\024\001\021\005A#\001\004%S:LG\017\n\013\002+A\0211BF\005\003/\031\021A!\0268ji\"9\021\004\001b\001\n\023Q\022aB5oi\032d\027mZ\013\0027A\021A$J\007\002;)\021adH\001\007CR|W.[2\013\005\001\n\023AC2p]\016,(O]3oi*\021!eI\001\005kRLGNC\001%\003\021Q\027M^1\n\005\031j\"!D!u_6L7-\0238uK\036,'\017\003\004)\001\001\006IaG\001\tS:$h\r\\1hA!1!\006\001I\005\002-\n\021\"\0338eKb4E.Y4\026\0031\002\"aC\027\n\00592!aA%oi\"1\001\007\001I\005\002E\nAb]3u\023:$W\r\037$mC\036$\"!\006\032\t\013Mz\003\031\001\027\002\003\031Da!\016\001\021\n\0031\024!F:fi&sG-\032=GY\006<\027JZ$sK\006$XM\035\013\003+]BQa\r\033A\0021Ba!\017\001\021\n\003Q\024\001F:fi&sG-\032=GY\006<\027J\032'fgN,'\017\006\002\026w!)1\007\017a\001Y\001")
public interface AtomicIndexFlag extends Signalling {
  void scala$collection$generic$AtomicIndexFlag$_setter_$scala$collection$generic$AtomicIndexFlag$$intflag_$eq(AtomicInteger paramAtomicInteger);
  
  AtomicInteger scala$collection$generic$AtomicIndexFlag$$intflag();
  
  int indexFlag();
  
  void setIndexFlag(int paramInt);
  
  void setIndexFlagIfGreater(int paramInt);
  
  void setIndexFlagIfLesser(int paramInt);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\AtomicIndexFlag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */