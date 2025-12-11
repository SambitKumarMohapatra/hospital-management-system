package com.hospital.hospital_management.config;

import com.hospital.hospital_management.model.Department;
import com.hospital.hospital_management.model.Doctor;
import com.hospital.hospital_management.repository.DepartmentRepository;
import com.hospital.hospital_management.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public void run(String... args) throws Exception {
        // Only create sample data if no departments exist
        if (departmentRepository.count() == 0) {
            System.out.println("🔄 Initializing sample data...");

            // Create departments
            Department cardiology = new Department("Cardiology", "Heart and cardiovascular diseases");
            Department neurology = new Department("Neurology", "Brain and nervous system disorders");
            Department pediatrics = new Department("Pediatrics", "Child healthcare");
            Department orthopedics = new Department("Orthopedics", "Bone and joint disorders");
            Department gynecology = new Department("Gynecology", "Female reproductive system health");
            Department oncology = new Department("Oncology", "Cancer diagnosis and treatment");
            Department dermatology = new Department("Dermatology", "Skin, hair, and nail conditions");
            Department urology = new Department("Urology", "Urinary system and male reproductive organs");
            Department gastroenterology = new Department("Gastroenterology", "Digestive system disorders");
            Department hematology = new Department("Hematology", "Blood disorders and diseases");
            Department others = new Department("Others", "General medical consultations and unspecified healthcare services");

            cardiology = departmentRepository.save(cardiology);
            neurology = departmentRepository.save(neurology);
            pediatrics = departmentRepository.save(pediatrics);
            orthopedics = departmentRepository.save(orthopedics);
            gynecology = departmentRepository.save(gynecology);
            oncology = departmentRepository.save(oncology);
            dermatology = departmentRepository.save(dermatology);
            urology = departmentRepository.save(urology);
            gastroenterology = departmentRepository.save(gastroenterology);
            hematology = departmentRepository.save(hematology);
            others = departmentRepository.save(others);

            System.out.println("✅ Created 11 departments");

            // Create doctors - FIXED: Each doctor gets their own department
            Doctor doc1 = new Doctor("Dr. Sandip Mohapatra", "Cardiologist", "sandip.m@hospital.com", "8585451263");
            doc1.setDepartment(cardiology);

            Doctor doc2 = new Doctor("Dr. Bhoomi Mohanty", "Neurologist", "bhoomi.dr@hospital.com", "9856745210");
            doc2.setDepartment(neurology);

            Doctor doc3 = new Doctor("Dr. Sambit Acharya", "Pediatrician", "sambit.ach@hospital.com", "6321458740");
            doc3.setDepartment(pediatrics);

            Doctor doc4 = new Doctor("Dr. Govind Mallick", "Orthopedic Surgeon", "govind.m@hospital.com", "9874564152");
            doc4.setDepartment(orthopedics);

            Doctor doc5 = new Doctor("Dr. Chinmaya Sahoo", "Gynecologist", "chin.lit.m@hospital.com", "9414564152");
            doc5.setDepartment(gynecology);

            Doctor doc6 = new Doctor("Dr. Swastik Mishra", "Oncologist", "swastik.dr.15.m@hospital.com", "9985564152");
            doc6.setDepartment(oncology);

            Doctor doc7 = new Doctor("Dr. Rashmiranjan Panigrahi", "Dermatologist", "rs.rashmi.m@hospital.com", "9825474152");
            doc7.setDepartment(dermatology);

            Doctor doc8 = new Doctor("Dr. Suvam Maharana", "Urologist", "suv.dr.me.m@hospital.com", "9740564152");
            doc8.setDepartment(urology);

            Doctor doc9 = new Doctor("Dr. Pritiranjan Pradhan", "Gastroenterologist", "prit_dr12.m@hospital.com", "9874561908");
            doc9.setDepartment(gastroenterology);

            Doctor doc10 = new Doctor("Dr. Itishree Harichandan", "Hematologist", "iti.me.dr.m@hospital.com", "9872064152");
            doc10.setDepartment(hematology);

            Doctor doc11 = new Doctor("Dr. Puspalata Rana", "General Physician / Consultant", "dr.pr22@hospital.com", "9874463152");
            doc11.setDepartment(others);

            doctorRepository.save(doc1);
            doctorRepository.save(doc2);
            doctorRepository.save(doc3);
            doctorRepository.save(doc4);
            doctorRepository.save(doc5);
            doctorRepository.save(doc6);
            doctorRepository.save(doc7);
            doctorRepository.save(doc8);
            doctorRepository.save(doc9);
            doctorRepository.save(doc10);
            doctorRepository.save(doc11);

            System.out.println("✅ Sample data loaded successfully!");
            System.out.println("📊 Departments: " + departmentRepository.count());
            System.out.println("👨‍⚕️ Doctors: " + doctorRepository.count());
        } else {
            System.out.println("ℹ️ Database already contains data. Existing data preserved.");
            System.out.println("📊 Existing Departments: " + departmentRepository.count());
            System.out.println("👨‍⚕️ Existing Doctors: " + doctorRepository.count());
        }
    }
}