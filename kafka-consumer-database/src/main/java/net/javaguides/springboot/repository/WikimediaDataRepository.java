package net.javaguides.springboot.repository;

import net.javaguides.springboot.entity.WikiData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaDataRepository extends JpaRepository<WikiData,Long> {
}
