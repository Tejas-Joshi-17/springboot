package com.sarvatra.projection;

import com.sarvatra.dto.BloodGroupStats;
import com.sarvatra.dto.CPatientInfo;
import com.sarvatra.dto.IPatientInfo;
import com.sarvatra.entities.Patient;
import com.sarvatra.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

@Slf4j
@SpringBootTest
class ProjectionTests {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    void getAllPatients() {
        final List<IPatientInfo> patients = patientRepository.getAllPatientsInfo();
        log.info("Patients List :- \n{}", patients);
    }

    @Test
    void getPatientListJustItsIdAndName() {
        final List<CPatientInfo> patientInfoList = patientRepository.getAllPatientsInfoConcrete();
        log.info("Patients Info List :- \n{}", patientInfoList);
    }

    @Test
    void getBloodGroupStatics() {
        final List<BloodGroupStats> bloodGroupStats = patientRepository.getBloodGroupStats();
        log.info("Blood Group Static :- \n{}", bloodGroupStats);
    }

}





//@Test
//@Transactional
//@Modifying
//void modifyPatientInformation() {
//    final int noOfRowsAffected = patientRepository.updatePatientNameWithId("Anuj Sharma", 1L);
//    log.info("No of Rows Affected in DB :- \n{}", noOfRowsAffected);
//}

