package org.sid.paymentservice.test_upload.respository;

import org.sid.paymentservice.test_upload.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData,Long> {


    Optional<ImageData> findByName(String fileName);
}
