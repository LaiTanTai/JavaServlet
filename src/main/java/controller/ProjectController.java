package controller;

import Entity.ProjectEntity;
import Entity.TaskEntity;
import payload.response.Groupwork_details;
import service.Project.ProjectService;
import service.Task.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name ="ProjectController",urlPatterns = {"/groupwork","/groupwork/add","/groupwork/edit","/groupwork/delete","/groupwork/look"})
public class ProjectController extends HttpServlet {
    private ProjectService projectService = new ProjectService();
    private TaskService taskService = new TaskService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path){
            case "/groupwork":
                getAllProject(req,resp);
                break;
            case "/groupwork/add":
                req.getRequestDispatcher("/groupwork-add.jsp").forward(req,resp);
                break;
            case "/groupwork/delete":
                deleteProject(req,resp);
                break;
            case "/groupwork/look":
                projectDetails(req,resp);
                break;
            case "/groupwork/edit":
                projectEdit(req,resp);
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path){
            case "/groupwork/add":
                postAddProject(req,resp);
                break;
            case "/groupwork/edit":
                post_projectEdit(req,resp);
                break;
        }
    }
    public void post_projectEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("idnew"));
        String name = req.getParameter("namenew");
        Date start = Date.valueOf(req.getParameter("startnew"));
        Date end = Date.valueOf(req.getParameter("endnew"));
        projectService.updateProject(id,name,start,end);
        resp.sendRedirect("/CRM_project/groupwork");
    }
    public void projectEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("project_id"));
        String name = req.getParameter("name");
        Date start = Date.valueOf(req.getParameter("start"));
        Date end = Date.valueOf(req.getParameter("end"));
        req.setAttribute("id",id);
        req.setAttribute("name",name);
        req.setAttribute("start",start);
        req.setAttribute("end",end);
        req.getRequestDispatcher("/groupwork-edit.jsp").forward(req,resp);
    }
    public void deleteProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("project_id"));
        projectService.deleteProject(id);
        resp.sendRedirect("/CRM_project/groupwork");
    }
    public void projectDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("project_id"));
        List<Groupwork_details> groupwork_details = taskService.getAllTaskProjectById(id);
        Double NotDone = (double) 0;
        Double Inprocess = (double) 0;
        Double Done = (double) 0;
        for (Groupwork_details item : groupwork_details) {
            NotDone+=item.getNotDone();
            Inprocess+=item.getInprocess();
            Done+=item.getDone();
        }
        int intNotDone= (int) ((NotDone/(NotDone+Inprocess+Done))*100);
        int intInprocess= (int) ((Inprocess/(NotDone+Inprocess+Done))*100);
        int intDone= (int) ((Done/(NotDone+Inprocess+Done))*100);
        req.setAttribute("NotDone",intNotDone);
        req.setAttribute("Inprocess",intInprocess);
        req.setAttribute("Done",intDone);
        req.setAttribute("details",groupwork_details);
        req.getRequestDispatcher("/groupwork-details.jsp").forward(req,resp);
    }
    public void postAddProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String name = req.getParameter("name");
        Date start = Date.valueOf(req.getParameter("start"));
        Date end = Date.valueOf(req.getParameter("end"));
        projectService.addProject(name,start,end);
        resp.sendRedirect("/CRM_project/groupwork");
    }
    public void getAllProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<ProjectEntity> projectEntityList = projectService.getAllProjectService();
        req.setAttribute("projectList",projectEntityList);
        req.getRequestDispatcher("groupwork.jsp").forward(req,resp);
    }
}
