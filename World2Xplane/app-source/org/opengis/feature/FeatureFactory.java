package org.opengis.feature;

import java.util.Collection;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AssociationDescriptor;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.ComplexType;
import org.opengis.feature.type.FeatureType;
import org.opengis.feature.type.GeometryDescriptor;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

public interface FeatureFactory {
  Association createAssociation(Attribute paramAttribute, AssociationDescriptor paramAssociationDescriptor);
  
  Attribute createAttribute(Object paramObject, AttributeDescriptor paramAttributeDescriptor, String paramString);
  
  GeometryAttribute createGeometryAttribute(Object paramObject, GeometryDescriptor paramGeometryDescriptor, String paramString, CoordinateReferenceSystem paramCoordinateReferenceSystem);
  
  ComplexAttribute createComplexAttribute(Collection<Property> paramCollection, AttributeDescriptor paramAttributeDescriptor, String paramString);
  
  ComplexAttribute createComplexAttribute(Collection<Property> paramCollection, ComplexType paramComplexType, String paramString);
  
  Feature createFeature(Collection<Property> paramCollection, AttributeDescriptor paramAttributeDescriptor, String paramString);
  
  Feature createFeature(Collection<Property> paramCollection, FeatureType paramFeatureType, String paramString);
  
  SimpleFeature createSimpleFeature(Object[] paramArrayOfObject, SimpleFeatureType paramSimpleFeatureType, String paramString);
  
  SimpleFeature createSimpleFeautre(Object[] paramArrayOfObject, AttributeDescriptor paramAttributeDescriptor, String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\feature\FeatureFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */