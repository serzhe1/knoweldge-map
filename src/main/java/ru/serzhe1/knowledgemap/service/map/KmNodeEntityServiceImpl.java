package ru.serzhe1.knowledgemap.service.map;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.serzhe1.knowledgemap.persistence.model.KmNodeEntity;
import ru.serzhe1.knowledgemap.persistence.model.StatusCodeEnum;
import ru.serzhe1.knowledgemap.persistence.model.VersionEntity;
import ru.serzhe1.knowledgemap.persistence.model.VersionStatusEntity;
import ru.serzhe1.knowledgemap.persistence.repository.KmNodeEntityRepository;
import ru.serzhe1.knowledgemap.service.version.VersionEntityService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KmNodeEntityServiceImpl implements KmNodeEntityService {
    private final KmNodeEntityRepository kmNodeEntityRepository;
    private final VersionEntityService versionEntityService;

    @Autowired
    private KmNodeEntityService self;

    @Override
    @Transactional(readOnly = true)
    public List<KmNodeEntity> findNodeAnd2LayerChildren(String key, Long versionId) {
        String regex = "^(" + key + "(?:-\\d+){0,2})$";

        return kmNodeEntityRepository.findNodeByRegexAndVersionId(regex, versionId);
    }

    @Override
    @Transactional
    public void publishing() {

        long startTime = System.currentTimeMillis();

        VersionStatusEntity archiveStatus = versionEntityService.findVersionStatusByStatusCode(StatusCodeEnum.ARCHIVE);
        List<KmNodeEntity> publishNodes = kmNodeEntityRepository.findAllByVersion_Status_CodeOrderByKey(StatusCodeEnum.PUBLISH.name());
        publishNodes.forEach(node -> node.getVersion().setStatus(archiveStatus));
        kmNodeEntityRepository.saveAllAndFlush(publishNodes);

        VersionStatusEntity publishStatus = versionEntityService.findVersionStatusByStatusCode(StatusCodeEnum.PUBLISH);
        List<KmNodeEntity> nodes = kmNodeEntityRepository.findAllByVersion_Status_CodeOrderByKey(StatusCodeEnum.WORK.name());
        nodes.forEach(node -> node.getVersion().setStatus(publishStatus));

        nodes = kmNodeEntityRepository.saveAllAndFlush(nodes);

        VersionEntity newWorkVersion = versionEntityService.createWorkVersion();
        List<KmNodeEntity> newWorkNodes = nodes.stream()
                .map(node -> {
                    KmNodeEntity nodeEntity = new KmNodeEntity();
                    nodeEntity.setId(null);
                    nodeEntity.setVersion(newWorkVersion);
                    nodeEntity.setName(node.getName());
                    nodeEntity.setKey(node.getKey());
                    nodeEntity.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));

                    return nodeEntity;
                }).toList();

        self.copyListNodes(newWorkNodes, newWorkVersion.getId());

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println("Время выполнения метода публикации: " + executionTime + " мс");
    }

    @Override
    @Transactional
    public void copyListNodes(List<KmNodeEntity> nodes, Long versionId) {
        kmNodeEntityRepository.saveAllAndFlush(nodes);
        kmNodeEntityRepository.updateParentsAtVersion(versionId);

    }
}
