package ru.serzhe1.knowledgemap.service.map;

import ru.serzhe1.knowledgemap.persistence.model.KmNodeEntity;

import java.util.List;

public interface KmNodeEntityService {

    List<KmNodeEntity> findNodeAnd2LayerChildren(String key, Long versionId);

    void publishing();

    void copyListNodes(List<KmNodeEntity> nodes, Long versionId);
}
