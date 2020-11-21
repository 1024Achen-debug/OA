package com.Liuyichen.oa.controller;

import com.Liuyichen.oa.biz.ClaimVoucherBiz;
import com.Liuyichen.oa.biz.ImageBiz;
import com.Liuyichen.oa.dao.EmployeeDao;
import com.Liuyichen.oa.dto.ClaimVoucherInfo;
import com.Liuyichen.oa.entity.ClaimVoucher;
import com.Liuyichen.oa.entity.DealRecord;
import com.Liuyichen.oa.entity.Employee;
import com.Liuyichen.oa.entity.Image;
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

@Controller("claimVoucherController")
@RequestMapping("/claim_voucher")
public class ClaimVoucherController {
    @Autowired
    private ClaimVoucherBiz claimVoucherBiz;
    @Autowired
    private ImageBiz imageBiz;
    @Qualifier("employeeDao")
    @Autowired
    private EmployeeDao employeeDao;

    @RequestMapping("/to_add")
    public String toAdd(Map<String, Object> map) {
        map.put("items", Contant.getItems());
        map.put("info", new ClaimVoucherInfo());
        return "claim_voucher_add";
    }

    @RequestMapping("/add")
    public String add(HttpSession session, ClaimVoucherInfo info, @RequestParam MultipartFile file, HttpServletRequest request, Image image) throws IOException {
        Employee employee = (Employee) session.getAttribute("employee");
        info.getClaimVoucher().setCreateSn(employee.getSn());
        claimVoucherBiz.save(info.getClaimVoucher(), info.getItems());
        String department = employee.getDepartment().getSn();
        num num = new num();
        String name = num.getSerialNo();
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        String url = request.getSession().getServletContext().getRealPath("/fileUpload/temp");
        file.transferTo(new File(url + "/" + name + "." + ext));
        image.setCreateSn(employee.getName());
        image.setImagePath("fileUpload/temp/" + name + "." + ext);
        image.setImageName(name + "." + ext);
        image.setNoyes(info.getClaimVoucher().getId().toString());
        employee.setDepartmentSn(department);
        employeeDao.update(employee);
        imageBiz.add(image);
        return "redirect:deal";

    }

    @RequestMapping("/detail")
    public String detail(int id, Map<String, Object> map) {
        map.put("claimVoucher", claimVoucherBiz.get(id));
        map.put("items", claimVoucherBiz.getItems(id));
        map.put("records", claimVoucherBiz.getRecords(id));
        String noyes = id + " ";
        map.put("list", imageBiz.get(noyes));
        return "claim_voucher_detail";
    }

    @RequestMapping("/self")
    public String self(HttpSession session, Map<String, Object> map) {
        Employee employee = (Employee) session.getAttribute("employee");
        map.put("list", claimVoucherBiz.getForSelf(employee.getSn()));
        return "claim_voucher_self";
    }

    @RequestMapping("/to_upload")
    public String toUpload(Map<String, Object> map) {
        map.put("image", new Image());
        return "image_upload";
    }

    @RequestMapping("/deal")
    public String deal(HttpSession session, Map<String, Object> map) {
        Employee employee = (Employee) session.getAttribute("employee");
        map.put("list", claimVoucherBiz.getForDeal(employee.getSn()));
        return "claim_voucher_deal";
    }

    @RequestMapping("/to_update")
    public String toUpdate(int id, Map<String, Object> map) {
        map.put("items", Contant.getItems());
        ClaimVoucherInfo info = new ClaimVoucherInfo();
        info.setClaimVoucher(claimVoucherBiz.get(id));
        info.setItems(claimVoucherBiz.getItems(id));
        map.put("info", info);
        return "claim_voucher_update";
    }

    @RequestMapping("/update")
    public String update(HttpSession session, ClaimVoucherInfo info) {
        Employee employee = (Employee) session.getAttribute("employee");
        info.getClaimVoucher().setCreateSn(employee.getSn());
        claimVoucherBiz.update(info.getClaimVoucher(), info.getItems());

        return "redirect:deal";

    }

    @RequestMapping("/submit")
    public String submit(int id) {
        claimVoucherBiz.submit(id);
        return "redirect:deal";

    }

    @RequestMapping("/to_check")
    public String toCheck(int id, Map<String, Object> map, DealRecord dealRecord) {
        map.put("claimVoucher", claimVoucherBiz.get(id));
        map.put("items", claimVoucherBiz.getItems(id));
        map.put("records", claimVoucherBiz.getRecords(id));
        dealRecord.setClaimVoucherId(id);
        map.put("record", dealRecord);
        String noyes = id + " ";
        map.put("list", imageBiz.get(noyes));
        return "claim_voucher_check";
    }

    @RequestMapping("/check")
    public String check(HttpSession session, DealRecord dealRecord, @RequestParam MultipartFile file, HttpServletRequest request, Image image) throws IOException {
        Employee employee = (Employee) session.getAttribute("employee");
        dealRecord.setDealSn(employee.getSn());
        claimVoucherBiz.deal(dealRecord);
        String department = employee.getDepartment().getSn();
        num num = new num();
        String name = num.getSerialNo();
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        String url = request.getSession().getServletContext().getRealPath("/fileUpload/temp");
        file.transferTo(new File(url + "/" + name + "." + ext));
        System.out.println(imageBiz.get(dealRecord.getClaimVoucherId().toString()));
        image.setCreateSn(employee.getName());
        image.setImagePath("fileUpload/temp/" + name + "." + ext);
        image.setImageName(name + "." + ext);
        image.setNoyes(dealRecord.getClaimVoucherId().toString());
        employee.setDepartmentSn(department);
        employeeDao.update(employee);
        imageBiz.add(image);
        return "redirect:deal";
    }

    @RequestMapping(value = "/delete", params = "id")
    public String delete(int id) {
        claimVoucherBiz.delete(id);
        return "redirect:deal";

    }
}

