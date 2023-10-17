package ru.serzhe1.knowledgemap.service.map;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.serzhe1.knowledgemap.persistence.model.KmNodeEntity;
import ru.serzhe1.knowledgemap.web.controller.advice.exception.AppNotFoundException;
import ru.serzhe1.knowledgemap.web.dto.map.KmNodeDTO;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KmNodeDTOService {
    private final KmNodeEntityService kmNodeEntityService;
    private final ModelMapper modelMapper;

    public KmNodeDTO findNodeByKeyAndStatus(String key, Long versionId) {
        List<KmNodeEntity> nodes = kmNodeEntityService.findNodeAnd2LayerChildren(key, versionId);
        KmNodeEntity mainNode = nodes.stream()
                .filter(node -> node.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new AppNotFoundException("Node is not found with key: " + key));

        nodes.remove(mainNode);

        Set<KmNodeDTO> firstLayerChildren = nodes.stream()
                .filter(node -> getParentKey(node.getKey()).equals(key))
                .map(node -> {
                    KmNodeDTO dto = modelMapper.map(node, KmNodeDTO.class);

                    boolean isLeaf = nodes.stream()
                            .noneMatch(child -> getParentKey(child.getKey()).equals(node.getKey()));
                    dto.setLeaf(isLeaf);

                    return dto;
                })
                .collect(Collectors.toSet());

        KmNodeDTO mainNodeDTO = modelMapper.map(mainNode, KmNodeDTO.class);
        mainNodeDTO.setLeaf(firstLayerChildren.isEmpty());
        mainNodeDTO.setChildren(firstLayerChildren);

        return mainNodeDTO;
    }

    private String getParentKey(String key) {
        List<String> keyArr = new ArrayList<>(Arrays.asList(key.split("-")));
        keyArr.remove(keyArr.size() - 1);
        return String.join("-", keyArr);
    }
}
