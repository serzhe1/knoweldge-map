package ru.serzhe1.knowledgemap.web.controller.map;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.serzhe1.knowledgemap.persistence.model.KmNodeEntity;
import ru.serzhe1.knowledgemap.service.map.KmNodeEntityService;

import java.util.List;

@RestController
@RequestMapping("/map")
@Tag(name = "map", description = "Knowledge map API")
@RequiredArgsConstructor
public class KmNodeEntityController {
    private final KmNodeEntityService kmNodeEntityService;

    @GetMapping("/test/{id}")
    @Operation(summary = "Find node by id")
    public KmNodeEntity getKmNodeById(@PathVariable Long id) {
        return kmNodeEntityService.findNodeById(id);
    }

    @GetMapping("/{key}")
    @Operation(summary = "Find node by key and version")
    public List<KmNodeEntity> findNode(@PathVariable String key, @RequestParam Long versionId) {
        return kmNodeEntityService.findNodeByKeyAndStatus(key, versionId);
    }
}
