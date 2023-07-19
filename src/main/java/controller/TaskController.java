package controller;

import Entity.ProjectEntity;
import Entity.TaskEntity;
import payload.request.UserRespone;
import service.Project.ProjectService;
import service.Task.TaskService;
import service.User.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name="TaskController",urlPatterns = {"/task","/task/add","/task/edit","/task/delete"})
public class TaskController extends HttpServlet {
    private TaskService taskService = new TaskService();
    private ProjectService projectService = new ProjectService();
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path){
            case "/task":
                getAllTask(req,resp);
                break;
            case "/task/add":
                addTask(req,resp);
                break;
            case "/task/delete":
                deleteTask(req,resp);
                break;
            case "/task/edit":
                editTask(req,resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path){
            case "/task/add":
                postAddTask(req,resp);
                break;
            case "/task/edit":
                postEdit(req,resp);
                break;
        }
    }
    public void postEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("task_id"));
        int project = Integer.parseInt(req.getParameter("projects"));
        String task = req.getParameter("task");
        int user_id = Integer.parseInt(req.getParameter("user"));
        Date start = Date.valueOf(req.getParameter("start"));
        Date end = Date.valueOf(req.getParameter("end"));
        int status = Integer.parseInt(req.getParameter("status"));
        taskService.updateTask(id,project,task,user_id,start,end,status);
        resp.sendRedirect("/CRM_project/task");
    }
    public void editTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int task_id = Integer.parseInt(req.getParameter("task_id"));
        List<UserRespone>userResponeList = userService.findAllUser();
        List<ProjectEntity> projectEntityList= projectService.getAllProjectService();
        req.setAttribute("taskID",task_id);
        req.setAttribute("userList",userResponeList);
        req.setAttribute("projectList",projectEntityList);
        req.getRequestDispatcher("/task-edit.jsp").forward(req,resp);
    }
    public void deleteTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int task = Integer.parseInt(req.getParameter("task_id"));
        taskService.deleteTask(task);
        resp.sendRedirect("/CRM_project/task");
    }
    public void postAddTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int project = Integer.parseInt(req.getParameter("projects"));
        String task = req.getParameter("task");
        int user = Integer.parseInt(req.getParameter("users"));
        Date start = Date.valueOf(req.getParameter("start"));
        Date end = Date.valueOf(req.getParameter("end"));
        int status = Integer.parseInt(req.getParameter("status"));
        taskService.addJobUser(user,project,task,start,end,status);
        resp.sendRedirect("/CRM_project/task");
    }
    public void getAllTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<TaskEntity> taskEntities = taskService.getAlljob();
        req.setAttribute("taskList",taskEntities);
        req.getRequestDispatcher("task.jsp").forward(req,resp);
    }
    public void addTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<UserRespone>userResponeList = userService.findAllUser();
        List<ProjectEntity> projectEntityList= projectService.getAllProjectService();
        req.setAttribute("userList",userResponeList);
        req.setAttribute("projectList",projectEntityList);
        req.getRequestDispatcher("/task-add.jsp").forward(req,resp);
    }
}
