package ru.serzhe1.knowledgemap.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.serzhe1.knowledgemap.persistence.model.VersionStatusEntity;

import java.util.Optional;

@Repository
public interface VersionStatusEntityRepository extends JpaRepository<VersionStatusEntity, Long> {
    Optional<VersionStatusEntity> findFirstByCode(String code);
}
