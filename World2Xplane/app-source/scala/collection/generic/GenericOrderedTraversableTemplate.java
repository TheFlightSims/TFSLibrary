package scala.collection.generic;

import scala.collection.mutable.Builder;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0014q!\001\002\021\002\007\005\021BA\021HK:,'/[2Pe\022,'/\0323Ue\0064XM]:bE2,G+Z7qY\006$XM\003\002\004\t\0059q-\0328fe&\034'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\001Qc\001\006\026AM\031\001aC\b\021\0051iQ\"\001\004\n\00591!AB!osJ+g\r\005\003\021#MqR\"\001\002\n\005I\021!!\004%bg:+wOQ;jY\022,'\017\005\002\025+1\001AA\002\f\001\t\013\007qCA\001B#\tA2\004\005\002\r3%\021!D\002\002\b\035>$\b.\0338h!\taA$\003\002\036\r\t\031\021I\\=+\005}Y\003c\001\013!'\0211\021\005\001CC\002\t\022!aQ\"\026\005\rJ\023C\001\r%!\r)c\005K\007\002\t%\021q\005\002\002\f)J\fg/\032:tC\ndW\r\005\002\025S\021)!\006\tb\001/\t\t\001lK\001-!\ti#'D\001/\025\ty\003'A\005v]\016DWmY6fI*\021\021GB\001\013C:tw\016^1uS>t\027BA\032/\005E)hn\0315fG.,GMV1sS\006t7-\032\005\006k\001!\tAN\001\007I%t\027\016\036\023\025\003]\002\"\001\004\035\n\005e2!\001B+oSRDqa\017\001CB\033MA(A\002pe\022,\022!\020\t\004}\031\033bBA E\035\t\0015)D\001B\025\t\021\005\"\001\004=e>|GOP\005\002\017%\021QIB\001\ba\006\0347.Y4f\023\t9\005J\001\005Pe\022,'/\0338h\025\t)e\001C\003K\001\031\0051*\001\tpe\022,'/\0323D_6\004\030M\\5p]V\tA\nE\002\021\033>K!A\024\002\003/\035+g.\032:jG>\023H-\032:fI\016{W\016]1oS>t\007C\001\013!\021\025\t\006\001\"\001S\003U9WM\\3sS\016|%\017Z3sK\022\024U/\0337eKJ,\"aU.\025\005Qs\006\003B+Y5vk\021A\026\006\003/\022\tq!\\;uC\ndW-\003\002Z-\n9!)^5mI\026\024\bC\001\013\\\t\025a\006K1\001\030\005\005\021\005c\001\013!5\")1\b\025a\002?B\031aH\022.")
public interface GenericOrderedTraversableTemplate<A, CC extends scala.collection.Traversable<Object>> extends HasNewBuilder<A, CC> {
  Ordering<A> ord();
  
  GenericOrderedCompanion<CC> orderedCompanion();
  
  <B> Builder<B, CC> genericOrderedBuilder(Ordering<B> paramOrdering);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\GenericOrderedTraversableTemplate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */