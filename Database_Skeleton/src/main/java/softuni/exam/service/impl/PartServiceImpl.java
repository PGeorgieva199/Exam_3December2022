package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PartSeedDto;
import softuni.exam.models.entity.Part;
import softuni.exam.repository.PartRepository;
import softuni.exam.service.PartService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class PartServiceImpl implements PartService {

    private static final String PARTS_FILE_PATH = "src/main/resources/files/json/parts.json";

    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return partRepository.count()>0;
    }

    @Override
    public String readPartsFileContent() throws IOException {
        return Files.readString(Path.of(PARTS_FILE_PATH));
    }

    @Override
    public String importParts() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson.fromJson(readPartsFileContent(), PartSeedDto[].class))
                .filter(partSeedDto -> {
                    boolean isValid = validationUtil.isValid(partSeedDto) &&
                            !partRepository.existsPartByPartName(partSeedDto.getPartName());

                    sb.append(isValid? String.format("Successfully imported part %s - %.2f",
                            partSeedDto.getPartName(), partSeedDto.getPrice())
                                : "Invalid part")
                            .append(System.lineSeparator());
                    return isValid;
                }).map(partSeedDto -> {
                    Part part = modelMapper.map(partSeedDto, Part.class);
                    return part;
                }).forEach(partRepository::save);
        return sb.toString();
    }
}
