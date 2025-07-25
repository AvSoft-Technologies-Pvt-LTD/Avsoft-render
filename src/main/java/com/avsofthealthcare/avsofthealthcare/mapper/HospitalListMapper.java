package com.avsofthealthcare.avsofthealthcare.mapper;

import com.avsofthealthcare.avsofthealthcare.dto.HospitalRequestListDTO;
import com.avsofthealthcare.avsofthealthcare.entity.HospitalList;

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
        hospitalList.setState(hospitalList.getState());
        hospitalList.setPinCode(hospitalList.getPinCode());
        hospitalList.setPpnNonPpn(hospitalRequestListDTO.getPpnNonPpn());
        return hospitalList;
    }

}
