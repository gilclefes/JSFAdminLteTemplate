/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilbert.demo.jsfadmintemplate.webbeans;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Named(value = "userloginBean")
@SessionScoped

public class userloginBean implements Serializable {

    private String name = "";
    private String password;
    private boolean loggedIn = false;
    private Integer userid = 1;
    private String userIPAdress;
    private Integer groupID;
    private Boolean isAdmin;

    public String getName() {
        return name;
    }

    public void setName(String newValue) {
        name = newValue;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newValue) {
        password = newValue;
    }

    public String login() {
        try {
            return WelcomeForm();
            // return "/search/dashboard.xhtml?faces-redirect=true";
        } catch (Exception e) {
            Logger.getLogger(userloginBean.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("login error " + e.toString());
            return null;
        }
    }

    public String logout() {
        loggedIn = false;
        password = "";

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        session.invalidate();
        return "/loginForm.xhtml?faces-redirect=true";

    }

    public void checkLogin(ComponentSystemEvent event) {
        if (!loggedIn) {
            // System.out.println("Not logged in");
            // System.out.println("value of name" + name);
            FacesContext context = FacesContext.getCurrentInstance();

            ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();
            handler.performNavigation("/loginForm.xhtml?faces-redirect=true");
        }
    }

    public void checkLogin4(ComponentSystemEvent event) {
        if (!loggedIn) {
            FacesContext context = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();
            handler.performNavigation("/loginForm.xhtml?faces-redirect=true");
        }
    }

    /**
     * @return the loggedIn
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * @param loggedIn the loggedIn to set
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String LoginHome() {
        //return "index.xhtml?faces-redirect=true";
        return "pretty:home";
    }

    public String WelcomeForm() {

        return "pretty:welcome";
    }

  
   
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @return the userIPAdress
     */
    public String getUserIPAdress() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        userIPAdress = ipAddress;
        return userIPAdress;
    }

    /**
     * @param userIPAdress the userIPAdress to set
     */
    public void setUserIPAdress(String userIPAdress) {
        this.userIPAdress = userIPAdress;
    }

    /**
     * @return the groupID
     */
    public Integer getGroupID() {
        return groupID;
    }

    /**
     * @param groupID the groupID to set
     */
    public void setGroupID(Integer groupID) {
        this.groupID = groupID;
    }

    /**
     * @return the isAdmin
     */
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * @param isAdmin the isAdmin to set
     */
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

   
}
