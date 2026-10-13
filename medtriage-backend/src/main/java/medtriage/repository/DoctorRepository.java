package medtriage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import medtriage.model.Doctor;
public interface DoctorRepository extends JpaRepository<Doctor, Long> {}