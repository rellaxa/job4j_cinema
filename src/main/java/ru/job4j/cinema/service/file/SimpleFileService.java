package ru.job4j.cinema.service.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FileDto;
import ru.job4j.cinema.model.File;
import ru.job4j.cinema.repository.file.FileRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
public class SimpleFileService implements FileService {

    private final FileRepository fileRepository;

    public SimpleFileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public Optional<FileDto> getFileById(int id) {
        var fileOptional = fileRepository.findById(id);
        if (fileOptional.isEmpty()) {
            return Optional.empty();
        }
        var file = fileOptional.get();
        var content = readFileAsBytes(file.getPath());
        return Optional.of(new FileDto(file.getName(), content));
    }

    @Override
    public Collection<FileDto> getAllPosters() {
        var posters = fileRepository.getFiles();
        return getPostersDto(posters);
    }

    private Collection<FileDto> getPostersDto(Collection<File> files) {
        var postersDto = new ArrayList<FileDto>();
        for (var poster : files) {
            var content = readFileAsBytes(poster.getPath());
            var posterDto = new FileDto(poster.getName(), content);
            postersDto.add(posterDto);
        }
        return postersDto;
    }

    private byte[] readFileAsBytes(String path) {
        try {
            return Files.readAllBytes(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
