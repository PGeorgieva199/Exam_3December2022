package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CarSeedRootDto;
import softuni.exam.models.entity.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParse;

import javax.persistence.Access;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CarServiceImpl implements CarService {
    private static final String CAR_FILE_PATH = "src/main/resources/files/xml/cars.xml";

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final XmlParse xmlParse;
    private final ValidationUtil validationUtil;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, XmlParse xmlParse, ValidationUtil validationUtil) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.xmlParse = xmlParse;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return carRepository.count()>0;
    }

    @Override
    public String readCarsFromFile() throws IOException {
        return Files.readString(Path.of(CAR_FILE_PATH));
    }

    @Override
    public String importCars() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        xmlParse.fromFile(CAR_FILE_PATH, CarSeedRootDto.class)
                .getCars()
                .stream().filter(carSeedDto -> {
                    boolean isValid = validationUtil.isValid(carSeedDto)
                            && !carRepository.findFirstByPlateNumber(carSeedDto.getPlateNumber()).isPresent() ;



                    sb.append(isValid? String.format("Successfully imported car %s - %s",
                            carSeedDto.getCarMake(), carSeedDto.getCarModel())
                            : "Invalid car")
                            .append(System.lineSeparator());
                    return isValid;
                }).map(carSeedDto -> {
                    Car car = modelMapper.map(carSeedDto, Car.class);
                    return car;
                }).forEach(carRepository::save);


        return sb.toString();
    }
}
