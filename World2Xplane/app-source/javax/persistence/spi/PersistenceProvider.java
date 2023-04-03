package javax.persistence.spi;

import java.util.Map;
import javax.persistence.EntityManagerFactory;

public interface PersistenceProvider {
  EntityManagerFactory createEntityManagerFactory(String paramString, Map paramMap);
  
  EntityManagerFactory createContainerEntityManagerFactory(PersistenceUnitInfo paramPersistenceUnitInfo, Map paramMap);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\persistence\spi\PersistenceProvider.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */