package tn.esprit.TRAVELGO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.TRAVELGO.model.FileDB;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
}