package com.example.taskmanager;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class TaskRepository {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public TaskRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        entityManager = entityManagerFactory.createEntityManager();
    }

    public List<Task> findNotDone() {
        TypedQuery<Task> query = entityManager.createQuery("SELECT t FROM Task t WHERE t.isDone = false ORDER BY t.dueDate ASC", Task.class);
        return query.getResultList();
    }

    public List<Task> findDone() {
        TypedQuery<Task> query = entityManager.createQuery("SELECT t FROM Task t WHERE t.isDone = true ORDER BY t.dueDate ASC", Task.class);
        return query.getResultList();
    }

    public Task findById(Long id) {
        return entityManager.find(Task.class, id);
    }

    public void save(Task task) {
        entityManager.getTransaction().begin();
        entityManager.persist(task);
        entityManager.getTransaction().commit();
    }

}
