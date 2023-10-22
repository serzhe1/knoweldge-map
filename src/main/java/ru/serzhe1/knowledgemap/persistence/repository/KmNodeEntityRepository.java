package ru.serzhe1.knowledgemap.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query(value = """
            UPDATE public.map
            SET parent = t2.parent_id
            FROM (SELECT t1.id as node_id, t2.id as parent_id
                  FROM public.map t1
                           LEFT JOIN public.map t2 ON t2.key = substring(t1.key from 1 for length(t1.key) - position('-' in reverse(t1.key)))
                      AND length(t1.key) > length(t2.key)
                  WHERE t1.version = ?1 AND t2.version = ?1
                  ) as t2
            WHERE id = t2.node_id AND version = ?1
            """,
            nativeQuery = true)
    @Modifying(clearAutomatically = true)
    void updateParentsAtVersion(Long version);

    List<KmNodeEntity> findAllByVersion_Status_CodeOrderByKey(String code);
}
