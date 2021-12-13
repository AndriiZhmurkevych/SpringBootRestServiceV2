package edu.labs.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import edu.labs.converters.TaskAssignmentConverter;
import edu.labs.converters.TaskConverter;
import edu.labs.dto.TaskAssignmentDTO;
import edu.labs.dto.TaskDTO;
import edu.labs.entities.Customer;
import edu.labs.entities.Employee;
import edu.labs.entities.TaskAssignment;
import edu.labs.exceptions.TaskAssignmentNotFoundException;
import edu.labs.repositories.TaskAssignmentRepository;
import edu.labs.services.TaskAssignmentService;

@Service
public class TaskAssignmentServiceImpl implements TaskAssignmentService {
    @Resource
    private TaskAssignmentRepository taskAssignmentRepository;
    @Resource
    private TaskAssignmentConverter taskAssignmentConverter;
    @Resource
    private TaskConverter taskConverter;

    @Override
    public List<TaskAssignmentDTO> getAllTaskAssignments() {
        final List<TaskAssignment> allTaskAssignments = taskAssignmentRepository.getAll();
        return allTaskAssignments.stream().map(item -> taskAssignmentConverter.toDto(item))
                .collect(Collectors.toList());
    }

    @Override
    public TaskAssignmentDTO getTaskAssignmentById(final Long id) throws TaskAssignmentNotFoundException {
        return taskAssignmentConverter.toDto(taskAssignmentRepository.getById(id));
    }

    @Override
    public TaskAssignmentDTO updateTaskAssignment(final TaskAssignmentDTO dto) throws TaskAssignmentNotFoundException {
        Assert.isNull(dto, "TaskAssignment dto is null");

        final TaskAssignment updatedTaskAssignment = taskAssignmentRepository
                .update(taskAssignmentConverter.toEntity(dto));
        return taskAssignmentConverter.toDto(updatedTaskAssignment);
    }

    @Override
    public void removeTaskAssignment(TaskAssignmentDTO dto) {
        final TaskAssignment taskToRemove = taskAssignmentConverter.toEntity(dto);
        taskAssignmentRepository.remove(taskToRemove);
    }

    @Override
    public Long createTaskAssignment(final TaskAssignmentDTO dto) {
        final TaskAssignment taskToCreate = taskAssignmentConverter.toEntity(dto);
        return taskToCreate.getId();
    }

    @Override
    public void removeTaskAssignmentById(final Long id) throws TaskAssignmentNotFoundException {
        final TaskAssignmentDTO taskToRemove = getTaskAssignmentById(id);
        removeTaskAssignment(taskToRemove);
    }

    @Override
    public List<TaskDTO> findTasksByEmployee(final Employee employee) {
        return taskAssignmentRepository.findTasksByEmployee(employee).stream().map(item -> taskConverter.toDto(item))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findTasksByReporter(final Customer customer) {
        return taskAssignmentRepository.findTasksByReporter(customer).stream().map(item -> taskConverter.toDto(item))
                .collect(Collectors.toList());
    }

}
