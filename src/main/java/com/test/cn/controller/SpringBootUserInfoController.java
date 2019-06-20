package com.test.cn.controller;

import com.github.pagehelper.Page;
import com.test.cn.pojo.jpa.SpringBootUserInfo;
import com.test.cn.service.jpa.SpringBootUserInfoService;
import com.test.cn.service.mybatis.SpringBootUserInfoMybatisService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/userInfo")
public class SpringBootUserInfoController {
    private static final Logger log = LoggerFactory.getLogger(SpringBootUserInfoController.class);

    @Autowired
    private SpringBootUserInfoService userInfoService;
    @Autowired
    private SpringBootUserInfoMybatisService userInfoMybatisService;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/loginUser")
    public String loginUser(HttpServletRequest request, String username, String password, Model model){
        log.info("SpringBootUserInfoController.login()");
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        String msg = "";
        try{
            subject.login(usernamePasswordToken);
            return "redirect:/userInfo/index";
        }catch (UnknownAccountException e){
            log.info("UnknownAccountException -- > 账号不存在：");
            msg = "账号不存在";
        }catch (IncorrectCredentialsException e){
            log.info("IncorrectCredentialsException -- > 密码不正确：");
            msg = "密码不正确";
        }catch (LockedAccountException e){
            log.info("LockedAccountException -- > 账号已被锁定，请联系管理员");
            msg = "账号已被锁定，请联系管理员";
        }catch (Exception e) {
            log.error("SpringBootUserInfoController loginUser 异常：",e);
            msg = "系统异常";
        }
        model.addAttribute("msg",msg);
        // 此方法不处理登录成功，由shiro进行处理
        return "login";
    }

    @RequestMapping("/index")
    public String index(Model model, @RequestParam(value = "pageIndex", defaultValue = "0")Integer pageIndex, @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize){
        Page<SpringBootUserInfo> page = userInfoMybatisService.findSpringBootUserInfo(pageIndex, pageSize);
        model.addAttribute("user",page);
        return "ListUser";
    }

    @RequestMapping("/addUser")
    public String addUser(Model model){
        model.addAttribute("user",new SpringBootUserInfo());
        model.addAttribute("title","添加用户");
        return "saveOrUpdate";
    }

    @RequestMapping("/updateUser/{id}")
    public String updateUser(Model model, @PathVariable("id") Long id){
        model.addAttribute("user",userInfoService.selectByPrimaryKey(id));
        model.addAttribute("title","修改用户");
        return "saveOrUpdate";
    }

    @RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
    public String saveOrUpdate(SpringBootUserInfo user){
        userInfoService.saveOrUpdate(user);
        return "redirect:/userInfo/index";
    }

    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userInfoService.deleteByPrimaryKey(id);
        return "redirect:/userInfo/index";
    }

    @RequestMapping("/403")
    public String unauthorized(){
        return "403";
    }

    @RequestMapping("logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    @RequestMapping("unLockAccount")
    public String unLockAccount(String username, Model model){
//        retryLimitHashedCredentialsMatcher.unLockAccount(username);
        model.addAttribute("msg","用户解锁成功");
        return "login";
    }
}
