import { useEffect, useState } from "react";
import {
  createTask,
  deleteTask,
  getTasks,
} from "../services/taskService";

function DashboardPage() {

  const [tasks, setTasks] = useState([]);

  const [formData, setFormData] = useState({
    title: "",
    description: "",
    status: "TODO",
  });

  useEffect(() => {

    fetchTasks();

  }, []);

  const fetchTasks = async () => {

    try {

      const data =
        await getTasks();

      setTasks(data);

    } catch (error) {

      console.log(error);
    }
  };

  const handleChange = (e) => {

    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {

    e.preventDefault();

    try {

      await createTask(formData);

      setFormData({
        title: "",
        description: "",
        status: "TODO",
      });

      fetchTasks();

    } catch (error) {

      console.log(error);
    }
  };

  const handleDelete = async (id) => {

    try {

      await deleteTask(id);

      fetchTasks();

    } catch (error) {

      console.log(error);
    }
  };

  const handleLogout = () => {

    localStorage.removeItem("token");

    window.location.href = "/";
  };

  return (

    <div className="min-h-screen bg-gray-100">

      <div className="flex justify-between items-center p-6 bg-white shadow">

        <h1 className="text-3xl font-bold">
          Smart Task Manager
        </h1>

        <button
          onClick={handleLogout}
          className="bg-red-500 text-white px-4 py-2 rounded-lg"
        >
          Logout
        </button>

      </div>

      <div className="max-w-4xl mx-auto p-6">

        <form
          onSubmit={handleSubmit}
          className="bg-white p-6 rounded-2xl shadow mb-8"
        >

          <h2 className="text-2xl font-bold mb-4">
            Create Task
          </h2>

          <input
            type="text"
            name="title"
            placeholder="Task title"
            value={formData.title}
            onChange={handleChange}
            className="w-full border p-3 rounded-lg mb-4"
          />

          <textarea
            name="description"
            placeholder="Task description"
            value={formData.description}
            onChange={handleChange}
            className="w-full border p-3 rounded-lg mb-4"
          />

          <select
            name="status"
            value={formData.status}
            onChange={handleChange}
            className="w-full border p-3 rounded-lg mb-4"
          >

            <option value="TODO">
              TODO
            </option>

            <option value="IN_PROGRESS">
              IN PROGRESS
            </option>

            <option value="DONE">
              DONE
            </option>

          </select>

          <button
            className="bg-black text-white px-6 py-3 rounded-lg"
          >
            Create Task
          </button>

        </form>

        <div className="space-y-4">

          {tasks.map((task) => (

            <div
              key={task.id}
              className="bg-white p-6 rounded-2xl shadow"
            >

              <div className="flex justify-between">

                <div>

                  <h2 className="text-2xl font-bold">
                    {task.title}
                  </h2>

                  <p className="text-gray-600 mt-2">
                    {task.description}
                  </p>

                  <p className="mt-2 font-semibold">
                    Status: {task.status}
                  </p>

                </div>

                <button
                  onClick={() =>
                    handleDelete(task.id)
                  }
                  className="bg-red-500 text-white px-4 py-2 rounded-lg h-fit"
                >
                  Delete
                </button>

              </div>

            </div>
          ))}

        </div>

      </div>

    </div>
  );
}

export default DashboardPage;