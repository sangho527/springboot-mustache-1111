package com.mustache.springbootmustache1111.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HospitalResponse {
    private Integer id;
    private String roadNameAddress;
    private String hospitalName;
    private Integer patientRoomCount;
    private Integer totalNumberOfBeds;
    private String businessTypeName;
    private Float totalAreaSize;
    private String businessStatusName;

    public HospitalResponse(Integer id, String roadNameAddress, String hospitalName,
                            Integer patientRoomCount, Integer totalNumberOfBeds,
                            String businessTypeName, Float totalAreaSize) {
    }

    public void setBusinessStatusName(String businessStatusName) {
        this.businessStatusName = businessStatusName;
    }

}