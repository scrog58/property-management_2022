package com.mycompany.propertymanagement.service;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PropertyService {

    //POST
    PropertyDTO saveProperty(PropertyDTO propertyDTO);

    //GET ALL
    List<PropertyDTO> getAllProperties();

    //GET on by Id
    PropertyDTO getProperty(Long propertyId);

    //PUT update on property
    PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId);

    //PATCH partial of one property
    PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId);

    //DELETE
    void deleteProperty(Long propertyId);
}
