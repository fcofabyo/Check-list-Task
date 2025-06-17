package checklisthero.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import checklisthero.example.demo.entity.Task;

public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> findByUserId(String userId);
}
