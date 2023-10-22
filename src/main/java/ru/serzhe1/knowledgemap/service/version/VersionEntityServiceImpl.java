package ru.serzhe1.knowledgemap.service.version;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.serzhe1.knowledgemap.persistence.model.StatusCodeEnum;
import ru.serzhe1.knowledgemap.persistence.model.VersionEntity;
import ru.serzhe1.knowledgemap.persistence.model.VersionStatusEntity;
import ru.serzhe1.knowledgemap.persistence.repository.VersionEntityRepository;
import ru.serzhe1.knowledgemap.persistence.repository.VersionStatusEntityRepository;
import ru.serzhe1.knowledgemap.web.controller.advice.exception.InternalServerErrorException;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VersionEntityServiceImpl implements VersionEntityService {
    private final VersionEntityRepository versionEntityRepository;
    private final VersionStatusEntityRepository versionStatusEntityRepository;

    @Override
    @Transactional
    public VersionEntity createWorkVersion() {
        VersionEntity version = new VersionEntity();
        version.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));

        VersionStatusEntity versionStatusEntity = findVersionStatusByStatusCode(StatusCodeEnum.WORK);

        version.setStatus(versionStatusEntity);

        return versionEntityRepository.saveAndFlush(version);
    }

    @Override
    @Transactional(readOnly = true)
    public VersionStatusEntity findVersionStatusByStatusCode(StatusCodeEnum code) {
        return versionStatusEntityRepository.findFirstByCode(code.name())
                .orElseThrow(() -> new InternalServerErrorException("Version status with code: " + code + " is not found"));
    }
}
