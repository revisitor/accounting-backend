package ru.mtrefelov.accounting.service;

public interface Adapter<Entity, Model> {
  Model entityToModel(Entity entity);
  Entity modelToEntity(Model model);
}
