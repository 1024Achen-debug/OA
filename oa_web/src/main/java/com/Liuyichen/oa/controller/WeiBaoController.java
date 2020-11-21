package com.Liuyichen.oa.controller;

import com.Liuyichen.oa.biz.ClaimVoucherBiz;
import com.Liuyichen.oa.biz.ImageBiz;
import com.Liuyichen.oa.biz.WeiBaoBiz;
import com.Liuyichen.oa.dao.EmployeeDao;
import com.Liuyichen.oa.dto.ClaimVoucherInfo;
import com.Liuyichen.oa.dto.WeiBaoInfo;
import com.Liuyichen.oa.entity.*;
import com.Liuyichen.oa.global.Contant;
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
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Controller("weiBaoController")
@RequestMapping("/wei_bao")
public class WeiBaoController {
    @Autowired
    private WeiBaoBiz weiBaoBiz;
    @Autowired
    private ImageBiz imageBiz;
    @Qualifier("employeeDao")
    @Autowired
    private EmployeeDao employeeDao;

    @RequestMapping("/to_add")
    public String toAdd(Map<String, Object> map) {
        map.put("items", Contant.getItems());
        map.put("info", new WeiBaoInfo());
        return "wei_bao_add";
    }

    @RequestMapping("/add")
    public String add(HttpSession session, WeiBaoInfo info, @RequestParam MultipartFile file, HttpServletRequest request, Image image) throws IOException {
        Employee employee = (Employee) session.getAttribute("employee");
        info.getweiBao().setCreateSn(employee.getSn());
        weiBaoBiz.save(info.getweiBao(), info.getItems());
        String department = employee.getDepartment().getSn();
        num num = new num();
        String name = num.getSerialNo();
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        String url = request.getSession().getServletContext().getRealPath("/fileUpload/temp");
        file.transferTo(new File(url + "/" + name + "." + ext));
        image.setCreateSn(employee.getName());
        image.setImagePath("fileUpload/temp/" + name + "." + ext);
        image.setImageName(name + "." + ext);
        image.setNoyes(info.getweiBao().getId().toString());
        employee.setDepartmentSn(department);
        employeeDao.update(employee);
        imageBiz.addCopy(image);
        return "redirect:deal";
    }

    @RequestMapping("/detail")
    public String detail(int id, Map<String, Object> map) {
        map.put("weiBao", weiBaoBiz.get(id));
        map.put("items", weiBaoBiz.getItems(id));
        map.put("records", weiBaoBiz.getRecords(id));
        String noyes = id + " ";
        map.put("list", imageBiz.getCopy(noyes));
        return "wei_bao_detail";
    }

    @RequestMapping("/self")
    public String self(HttpSession session, Map<String, Object> map) {
        Employee employee = (Employee) session.getAttribute("employee");
        map.put("list", weiBaoBiz.getForSelf(employee.getSn()));
        return "wei_bao_self";
    }

    @RequestMapping("/to_upload")
    public String toUpload(Map<String, Object> map) {
        map.put("image", new Image());
        return "image_upload";
    }

    @RequestMapping("/deal")
    public String deal(HttpSession session, Map<String, Object> map) {
        Employee employee = (Employee) session.getAttribute("employee");
        map.put("list", weiBaoBiz.getForDeal(employee.getSn()));
        return "wei_bao_deal";
    }

    @RequestMapping("/to_update")
    public String toUpdate(int id, Map<String, Object> map) {
        map.put("items", Contant.getItems());
        WeiBaoInfo info = new WeiBaoInfo();
        info.setWeiBao(weiBaoBiz.get(id));
        info.setItems(weiBaoBiz.getItems(id));
        map.put("info", info);
        return "wei_bao_update";
    }

    @RequestMapping("/update")
    public String update(HttpSession session, WeiBaoInfo info) {
        Employee employee = (Employee) session.getAttribute("employee");
        info.getweiBao().setCreateSn(employee.getSn());
        weiBaoBiz.update(info.getweiBao(), info.getItems());

        return "redirect:deal";

    }

    @RequestMapping("/submit")
    public String submit(int id) {
        weiBaoBiz.submit(id);
        return "redirect:deal";

    }

    @RequestMapping("/to_check")
    public String toCheck(int id, Map<String, Object> map, WeiBaoDealRecord dealRecord) {
        map.put("weibao", weiBaoBiz.get(id));
        map.put("items", weiBaoBiz.getItems(id));
        map.put("records", weiBaoBiz.getRecords(id));
        dealRecord.setWeiBaoId(id);
        map.put("record", dealRecord);
        String noyes = id + " ";
        map.put("list", imageBiz.getCopy(noyes));
        return "wei_bao_check";
    }

    @RequestMapping("/check")
    public String check(HttpSession session, WeiBaoDealRecord dealRecord, @RequestParam MultipartFile file, HttpServletRequest request, Image image) throws IOException {
        Employee employee = (Employee) session.getAttribute("employee");
        dealRecord.setDealSn(employee.getSn());
        weiBaoBiz.deal(dealRecord);
        String department = employee.getDepartment().getSn();
        num num = new num();
        String name = num.getSerialNo();
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        String url = request.getSession().getServletContext().getRealPath("/fileUpload/temp");
        file.transferTo(new File(url + "/" + name + "." + ext));
        System.out.println(imageBiz.get(dealRecord.getWeiBaoId().toString()));
        image.setCreateSn(employee.getName());
        image.setImagePath("fileUpload/temp/" + name + "." + ext);
        image.setImageName(name + "." + ext);
        image.setNoyes(dealRecord.getWeiBaoId().toString());
        employee.setDepartmentSn(department);
        employeeDao.update(employee);
        imageBiz.addCopy(image);
        return "redirect:deal";
    }

    @RequestMapping("/delete")
    public String delete(int id) {
        weiBaoBiz.delete(id);
        return "redirect:deal";

    }
}

