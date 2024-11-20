package com.company.pmdataadvancedstarter.repository;

import com.company.pmdataadvancedstarter.entity.Task;
import io.jmix.core.repository.ApplyConstraints;
import io.jmix.core.repository.JmixDataRepository;

import java.util.UUID;

@ApplyConstraints(value = false)
public interface TaskRepository extends JmixDataRepository<Task, UUID> {
}