package controller;

import Entity.RoleEntity;
import service.Role.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RoleController",urlPatterns = {"/role","/role/add","/role/delete","/role/edit"})
public class RoleController extends HttpServlet {
    private RoleService roleService = new RoleService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path){
            case "/role":
                getAllRole(req,resp);
                break;
            case "/role/add":
                addRole(req,resp);
                break;
            case "/role/delete":
                deleteRole(req,resp);
                break;
            case "/role/edit":
                editRole(req,resp);
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path){
            case "/role/add":
                postaddRole(req,resp);
                break;
            case "/role/edit":
                posteditRole(req,resp);
                break;
        }
    }
    public void editRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("role_id"));
        String name = req.getParameter("role_name");
        String des = req.getParameter("role_des");
        req.setAttribute("roleId",id);
        req.setAttribute("roleName",name);
        req.setAttribute("roleDescription",des);
        req.getRequestDispatcher("/role-edit.jsp").forward(req,resp);
    }
    public void posteditRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("idRole"));
        String name = req.getParameter("nameRole");
        String des = req.getParameter("desRole");
        roleService.editRole(id,name,des);
        resp.sendRedirect("/CRM_project/role");
    }
    public void deleteRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int role = Integer.parseInt(req.getParameter("roleId"));
        roleService.deleteRole(role);
        resp.sendRedirect("/CRM_project/role");
    }
    public void getAllRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RoleEntity> roleEntityList = roleService.findAllRole();
        req.setAttribute("rolelist",roleEntityList);
        req.getRequestDispatcher("role-table.jsp").forward(req,resp);
    }
    public void addRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/role-add.jsp").forward(req,resp);
    }
    public void postaddRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String name = req.getParameter("role_name");
        String des = req.getParameter("role_des");
        boolean isValid = true;
        if(name==null){
            req.setAttribute("error_name","Tên quyền không được để trống");
            isValid=false;
        }
        if(des==null){
            req.setAttribute("error_des","Mô tả quyền không được để trống");
            isValid=false;
        }
        if(isValid){
            roleService.addRole(name,des);
        }
    }
}
