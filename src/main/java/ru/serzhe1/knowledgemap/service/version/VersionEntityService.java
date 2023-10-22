package ru.serzhe1.knowledgemap.service.version;

import ru.serzhe1.knowledgemap.persistence.model.StatusCodeEnum;
import ru.serzhe1.knowledgemap.persistence.model.VersionEntity;
import ru.serzhe1.knowledgemap.persistence.model.VersionStatusEntity;

public interface VersionEntityService {

    VersionEntity createWorkVersion();

    VersionStatusEntity findVersionStatusByStatusCode(StatusCodeEnum code);
}
