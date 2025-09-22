package com.avsofthealthcare.mapper;

import com.avsofthealthcare.dto.HospitalListDropdownDto;
import com.avsofthealthcare.dto.HospitalRequestListDTO;
import com.avsofthealthcare.entity.HospitalList;

public class HospitalListMapper {

    public static HospitalRequestListDTO mapToHospitalRequestListDTO(HospitalList hospitalList){
        HospitalRequestListDTO dto = new HospitalRequestListDTO();
        dto.setHospitalName(hospitalList.getHospitalName());
        dto.setAddress(hospitalList.getAddress());
        dto.setCity(hospitalList.getCity());
        dto.setState(hospitalList.getState());
        dto.setPinCode(hospitalList.getPinCode());
        dto.setPpnNonPpn(hospitalList.getPpnNonPpn());
        return dto;
    }

    public static HospitalList mapToHospitalList(HospitalRequestListDTO dto){
        HospitalList hospitalList = new HospitalList();
        hospitalList.setHospitalName(dto.getHospitalName());
        hospitalList.setAddress(dto.getAddress());
        hospitalList.setCity(dto.getCity());
        hospitalList.setState(dto.getState());
        hospitalList.setPinCode(dto.getPinCode());
        hospitalList.setPpnNonPpn(dto.getPpnNonPpn());
        return hospitalList;
    }

    public static HospitalListDropdownDto toDropdownDto(HospitalList entity) {
        return new HospitalListDropdownDto(entity.getId(), entity.getHospitalName());
    }
}
