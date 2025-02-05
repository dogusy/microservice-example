package com.school.school;

import com.school.school.client.StudentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository schoolRepository;

    private final StudentClient studentClient;

    public void saveSchool(School school){
        schoolRepository.save(school);
    }

    public List<School> findAllSchools(){
        return schoolRepository.findAll();
    }

    public FullSchoolResponse findSchoolwithStudents(Integer schoolId) {
        var school = schoolRepository.findById(schoolId).orElse(School.
                builder().
                name("NOT_FOUND").
                email("NOT_FOUND").build());
        var students = studentClient.findAllStudentsBySchool(schoolId);
        return FullSchoolResponse.builder().
                name(school.getName()).
                studentList(students).
                build();
    }
}
