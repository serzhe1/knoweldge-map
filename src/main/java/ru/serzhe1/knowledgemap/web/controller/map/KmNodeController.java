package ru.serzhe1.knowledgemap.web.controller.map;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.serzhe1.knowledgemap.service.map.KmNodeDTOService;
import ru.serzhe1.knowledgemap.service.map.KmNodeEntityService;
import ru.serzhe1.knowledgemap.web.dto.map.KmNodeDTO;

@RestController
@RequestMapping("/map")
@Tag(name = "map", description = "Knowledge map API")
@RequiredArgsConstructor
public class KmNodeController {
    private final KmNodeDTOService kmNodeDTOService;
    private final KmNodeEntityService kmNodeEntityService;

    @GetMapping("/{key}")
    @Operation(summary = "Find node by key and version")
    public KmNodeDTO findNode(@PathVariable String key, @RequestParam Long versionId) {
        return kmNodeDTOService.findNodeByKeyAndStatus(key, versionId);
    }

    @GetMapping("/publish")
    @Operation(summary = "Publishing new version")
    public void publish () {
        kmNodeEntityService.publishing();
    }

}
