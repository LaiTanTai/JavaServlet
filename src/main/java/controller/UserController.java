package controller;

import Entity.ProjectEntity;
import Entity.RoleEntity;
import payload.request.UserRespone;
import payload.response.ProfileJobTask;
import payload.response.UserAmountJob;
import payload.response.UserTask;
import payload.response.addUsererror;
import service.Project.ProjectService;
import service.Role.RoleService;
import service.Task.TaskService;
import service.User.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "UserController",urlPatterns = {"/profile","/profile/profileyourself","/user","/user/add","/user/delete","/user/edit","/user/look"})
public class UserController extends HttpServlet {
    private ProjectService projectService = new ProjectService();
    private TaskService taskService = new TaskService();
    private UserService userService = new UserService();
    private RoleService roleService = new RoleService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path =req.getServletPath();
        switch (path){
            case "/profile":
                try {
                    profile(req,resp);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/user":
                getAllUser(req,resp);
                break;
            case "/user/add":
                addUser_Get(req,resp);
                break;
            case "/user/delete":
                deleteUser(req,resp);
                break;
            case "/user/look":
                lookUser(req,resp);
                break;
            case "/user/edit":
                editUser(req,resp);
                break;
            case "/profile/profileyourself":
                yourself(req,resp);
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path =req.getServletPath();
        switch (path){
            case "/user/add":
                addUser_Post(req,resp);
                break;
            case "/user/edit":
                try {
                    post_editUser(req,resp);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/profile/profileyourself":
                post_yourself(req,resp);
                break;
        }
    }
    public void yourself(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("id"));
        String fullname = req.getParameter("fullname");
        String name = req.getParameter("name");
        String job = req.getParameter("job");
        Date start = Date.valueOf(req.getParameter("start"));
        Date end = Date.valueOf(req.getParameter("end"));
        String status = req.getParameter("status");
        req.setAttribute("id",id);
        req.setAttribute("name",name);
        req.setAttribute("job",job);
        req.setAttribute("start",start);
        req.setAttribute("end",end);
        req.setAttribute("status",status);
        req.getRequestDispatcher("/profile-edit__yourseft.jsp").forward(req,resp);
    }
    public void post_yourself(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("taskid"));
        int status = Integer.parseInt(req.getParameter("statusnew"));
        taskService.updateStatus(id,status);
        resp.sendRedirect("/CRM_project/profile");
    }
    public void profile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException{
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        ProfileJobTask profileJobTask = userService.findIdByNamePassword(username,password);
        req.setAttribute("email",username);
        req.setAttribute("listProfile",profileJobTask);
        req.getRequestDispatcher("profile.jsp").forward(req,resp);
    }
    public void post_editUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
        String usercheck = req.getParameter("usercheck");
        String projectcheck = req.getParameter("projectcheck");
        int user_id=Integer.parseInt(req.getParameter("userid"));
        int role_id = Integer.parseInt(req.getParameter("rolenew"));;
        String fullname = req.getParameter("fullnamenew");
        String email = req.getParameter("emailnew");
        userService.updateUser(user_id,role_id,fullname,email);
        resp.sendRedirect("/CRM_project/user");
    }
    public void addUser_Post(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        List<RoleEntity> roleResponeList = roleService.findAllRole();
        req.setAttribute("role",roleResponeList);
        String fullname = "";
        String email = "";
        int role_id = 0;
        String password = "";
        addUsererror addUsererror = new addUsererror();
        fullname = req.getParameter("fullname");
        boolean isValid = true;
        if(fullname==""){
            addUsererror.setFullname("Fullname must be filled");
            isValid = false;
        }
        email = req.getParameter("username");
        if(email==""){
            addUsererror.setEmail("Email must be filled");
            isValid = false;
        }
        role_id = Integer.parseInt(req.getParameter("roleId"));
        password = req.getParameter("password");
        if(password==""){
            addUsererror.setPassword("Password must be filled");
            isValid = false;
        }
        if(isValid){
            Boolean isSuccess = userService.adduser(email,fullname,password,role_id);
        }else{
            req.setAttribute("erroradd",addUsererror);
        }
        req.getRequestDispatcher("/user-add.jsp").forward(req,resp);
    }
    public void addUser_Get(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        List<RoleEntity> roleResponeList = roleService.findAllRole();
        req.setAttribute("role",roleResponeList);
        req.getRequestDispatcher("/user-add.jsp").forward(req,resp);
    }
    public void getAllUser(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
        List<UserRespone> userResponeList = userService.findAllUser();
        req.setAttribute("list",userResponeList);
        req.getRequestDispatcher("user-table.jsp").forward(req,resp);
    }
    public void editUser(HttpServletRequest req,HttpServletResponse resp) throws  ServletException , IOException{
        int id = Integer.parseInt(req.getParameter("userid"));
        int role_id = Integer.parseInt(req.getParameter("role_id"));
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        List<ProjectEntity> projectEntityList= projectService.getAllProjectService();
        List<RoleEntity> roleResponeList = roleService.findAllRole();
        req.setAttribute("userid",id);
        req.setAttribute("role_id",role_id);
        req.setAttribute("fullname",fullname);
        req.setAttribute("email",email);
        req.setAttribute("roleList",roleResponeList);
        req.setAttribute("projectList",projectEntityList);
        req.getRequestDispatcher("/profile-edit.jsp").forward(req,resp);
    }
    public void deleteUser(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("userid"));
        userService.deleteUser(id);
        resp.sendRedirect("/CRM_project/user");
    }
    public void lookUser(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("userid"));
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        UserAmountJob userAmountJob = userService.findUser(id);
        req.setAttribute("UserlistAmount",userAmountJob);
        req.setAttribute("fullname",fullname);
        req.setAttribute("email",email);
        req.getRequestDispatcher("/user-details.jsp").forward(req,resp);
    }
}
