package javax.persistence;

import java.util.Map;

public interface EntityManagerFactory {
  EntityManager createEntityManager();
  
  EntityManager createEntityManager(Map paramMap);
  
  void close();
  
  boolean isOpen();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\persistence\EntityManagerFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */