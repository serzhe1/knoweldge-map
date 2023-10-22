package ru.serzhe1.knowledgemap.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.serzhe1.knowledgemap.persistence.model.VersionEntity;

@Repository
public interface VersionEntityRepository extends JpaRepository<VersionEntity, Long> {
}
