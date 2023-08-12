package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.repository.TaskRepository;
import softuni.exam.service.TaskService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParse;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TaskServiceImpl implements TaskService {
    private static final String TASKS_FILE_PATH = "src/main/resources/files/xml/tasks.xml";

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    private final XmlParse xmlParse;
    private final ValidationUtil validationUtil;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper, XmlParse xmlParse, ValidationUtil validationUtil) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
        this.xmlParse = xmlParse;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return taskRepository.count()>0;
    }

    @Override
    public String readTasksFileContent() throws IOException {
        return Files.readString(Path.of(TASKS_FILE_PATH));
    }

    @Override
    public String importTasks() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        return null;
    }

    @Override
    public String getCoupeCarTasksOrderByPrice() {
        return null;
    }
}
