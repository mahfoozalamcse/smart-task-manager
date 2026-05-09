import api from "../api/axios";

export const getTasks = async () => {

  const response =
    await api.get("/tasks");

  return response.data;
};

export const createTask = async (data) => {

  const response =
    await api.post("/tasks", data);

  return response.data;
};

export const deleteTask = async (id) => {

  const response =
    await api.delete(`/tasks/${id}`);

  return response.data;
};