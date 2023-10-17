package ru.serzhe1.knowledgemap.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.serzhe1.knowledgemap.persistence.model.KmNodeEntity;

import java.util.List;

@Repository
public interface KmNodeEntityRepository extends JpaRepository<KmNodeEntity, Long> {
    @Query(value = """
            SELECT public.map.*
            FROM public.map
            WHERE key ~ ?1 AND map.version = ?2
            ORDER BY key""",
            nativeQuery = true)
    List<KmNodeEntity> findNodeByRegexAndVersionId(String regex, Long versionId);

}
