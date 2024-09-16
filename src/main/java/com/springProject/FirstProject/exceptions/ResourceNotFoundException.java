package com.springProject.FirstProject.exceptions;

public class ResourceNotFoundException extends RuntimeException {

     String resourceName;
     String fieldName;
     Integer fieldValue;
    public ResourceNotFoundException(String resourceName, String fieldName, Integer fieldValue) {
        super(String.format("%s Not Not found %s : %s", resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }


}
