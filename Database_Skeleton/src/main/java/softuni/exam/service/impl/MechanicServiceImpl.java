package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.MechanicSeedDto;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.repository.MechanicRepository;
import softuni.exam.service.MechanicService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class MechanicServiceImpl implements MechanicService {
    private static final String  MECHANIC_FILE_PATH = "src/main/resources/files/json/mechanics.json";

    private final MechanicRepository mechanicRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    @Autowired
    public MechanicServiceImpl(MechanicRepository mechanicRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.mechanicRepository = mechanicRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return mechanicRepository.count()>0;
    }

    @Override
    public String readMechanicsFromFile() throws IOException {
        return Files.readString(Path.of(MECHANIC_FILE_PATH));
    }

    @Override
    public String importMechanics() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson.fromJson(readMechanicsFromFile(), MechanicSeedDto[].class))
                .filter(mechanicSeedDto -> {
                    boolean isValid = validationUtil.isValid(mechanicSeedDto)
                            && !mechanicRepository.existsMechanicByEmail(mechanicSeedDto.getEmail());

                    sb.append(isValid ? String.format("Successfully imported mechanic %s %s",
                                    mechanicSeedDto.getFirstName(), mechanicSeedDto.getLastName())
                                    : "Invalid mechanic")
                            .append(System.lineSeparator());
                    return isValid;
                }).map(mechanicSeedDto -> {
                    Mechanic mechanic = modelMapper.map(mechanicSeedDto, Mechanic.class);
                    return mechanic;
                }).forEach(mechanicRepository::save);

        return sb.toString();
    }
}
