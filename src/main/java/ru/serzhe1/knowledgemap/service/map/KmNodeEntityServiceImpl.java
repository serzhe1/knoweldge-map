package ru.serzhe1.knowledgemap.service.map;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.serzhe1.knowledgemap.persistence.model.KmNodeEntity;
import ru.serzhe1.knowledgemap.persistence.repository.KmNodeEntityRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KmNodeEntityServiceImpl implements KmNodeEntityService {
    private final KmNodeEntityRepository kmNodeEntityRepository;

    @Override
    @Transactional(readOnly = true)
    public List<KmNodeEntity> findNodeAnd2LayerChildren(String key, Long versionId) {
        String regex ="^(" + key + "(?:-\\d+){0,2})$";

        return kmNodeEntityRepository.findNodeByRegexAndVersionId(regex, versionId);
    }

}
