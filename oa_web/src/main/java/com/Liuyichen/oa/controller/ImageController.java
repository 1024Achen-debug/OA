package com.Liuyichen.oa.controller;

import com.Liuyichen.oa.biz.EmployeeBiz;
import com.Liuyichen.oa.biz.ImageBiz;
import com.Liuyichen.oa.dao.EmployeeDao;
import com.Liuyichen.oa.entity.Employee;
import com.Liuyichen.oa.entity.Image;
import com.Liuyichen.oa.global.num;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Map;

@Controller("imageController")
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageBiz imageBiz;
    @Qualifier("employeeDao")
    @Autowired
    private EmployeeDao employeeDao;

    @RequestMapping("/list")
    public String list(Map<String, Object> map) {
        map.put("list", imageBiz.getAll());
        return "image_list";
    }

    @RequestMapping("/to_upload")
    public String toUpload(Map<String, Object> map) {
        map.put("image", new Image());
        return "image_upload";
    }

    @RequestMapping("/upload")
    public String upload(@RequestParam MultipartFile file, HttpServletRequest request, Image image, HttpSession session) throws IOException {
        Employee employee = (Employee) session.getAttribute("employee");
        String department = employee.getDepartment().getSn();
        num num = new num();
        String name = num.getSerialNo();
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        String url = request.getSession().getServletContext().getRealPath("/fileUpload/temp");
        file.transferTo(new File(url + "/" + name + "." + ext));
        image.setCreateSn(employee.getName());
        image.setImagePath("fileUpload/temp/" + name + "." + ext);
        image.setImageName(name + "." + ext);
        employee.setDepartmentSn(department);
        employeeDao.update(employee);
        imageBiz.add(image);
        return "redirect:list";
    }

    @RequestMapping(value = "/remove", params = "createSn")
    public String toUpDate(String createSn) {
        imageBiz.remove(createSn);
        return "redirect:list";
    }
}
