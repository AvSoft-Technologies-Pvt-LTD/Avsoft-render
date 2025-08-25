package com.avsofthealthcare.mapper;

import com.avsofthealthcare.dto.HospitalListDropdownDto;
import com.avsofthealthcare.dto.HospitalRequestListDTO;
import com.avsofthealthcare.entity.HospitalList;

public class HospitalListMapper {

    public static HospitalRequestListDTO mapToHospitalRequestListDTO(HospitalList hospitalList){
        HospitalRequestListDTO hospitalRequestListDTO = new HospitalRequestListDTO();
        hospitalRequestListDTO.setId(hospitalList.getId());
        hospitalRequestListDTO.setHospitalName(hospitalList.getHospitalName());
        hospitalRequestListDTO.setAddress(hospitalList.getAddress());
        hospitalRequestListDTO.setCity(hospitalList.getCity());
        hospitalRequestListDTO.setState(hospitalList.getState());
        hospitalRequestListDTO.setPinCode(hospitalList.getPinCode());
        hospitalRequestListDTO.setPpnNonPpn(hospitalList.getPpnNonPpn());
        return hospitalRequestListDTO;
    }

    public static HospitalList mapToHospitalList(HospitalRequestListDTO hospitalRequestListDTO){
        HospitalList hospitalList = new HospitalList();
        hospitalList.setId(hospitalRequestListDTO.getId());
        hospitalList.setHospitalName(hospitalRequestListDTO.getHospitalName());
        hospitalList.setAddress(hospitalRequestListDTO.getAddress());
        hospitalList.setCity(hospitalRequestListDTO.getCity());
        hospitalList.setState(hospitalRequestListDTO.getState());
        hospitalList.setPinCode(hospitalRequestListDTO.getPinCode());
        hospitalList.setPpnNonPpn(hospitalRequestListDTO.getPpnNonPpn());
        return hospitalList;
    }

    public static HospitalListDropdownDto toDropdownDto(HospitalList entity) {
        return new HospitalListDropdownDto(entity.getId(), entity.getHospitalName());
    }


}
