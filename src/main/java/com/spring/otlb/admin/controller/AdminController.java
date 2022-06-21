package com.spring.otlb.admin.controller;

import com.spring.otlb.emp.model.service.EmpService;
import com.spring.otlb.emp.model.vo.Emp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private EmpService empService;

    @GetMapping("/empListView.do")
    public void empListView(Model model) {

        List<Emp> list = empService.selectAllMember();
        model.addAttribute("list", list);

    }

    @PostMapping("/empCodeUpdate.do")
    public String empCodeUpdate(RedirectAttributes attributes,
                                @RequestParam String empNo,
                                @RequestParam String updateDeptCode,
                                @RequestParam String updateJobCode){

        log.debug("empNo = {}", empNo);
        log.debug("updateDeptCode = {}", updateDeptCode);
        log.debug("updateJobCode = {}", updateJobCode);

        String msg = "";
        Emp emp = empService.selectOneEmp(empNo);
        if(emp.getDeptCode().equals(updateDeptCode) && emp.getJobCode().equals(updateJobCode)){
            msg = "변경사항이 없습니다.";
        }else{
            emp.setDeptCode(updateDeptCode);
            emp.setJobCode(updateJobCode);
            int result = empService.updateEmpCode(emp);
            if(result > 0){
                msg = "정보를 변경하였습니다.";
            } else{
                msg = "변경 요류";
            }

        }
        attributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/empListView.do";
    }
}
