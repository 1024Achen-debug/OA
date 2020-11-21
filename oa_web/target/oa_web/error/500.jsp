<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 刘怡琛
  Date: 2020/6/1
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.Liuyichen.oa.global.Contant" %>
<html>
<head>
    <title>500报错处理界面</title>
</head>
<body>
<h1>一.提交采购单问题</h1>
<h2>1.检查是否存在项目经理或者总经理的存在，若不存在则需要创建</h2>
<a href="${pageContext.request.contextPath}/employee/to_add">点击这里去创建</a>
<h2>2.若是想要修改采购单，并且添加子采购项目，需要重新创建采购单</h2>
<a href="${pageContext.request.contextPath}/claim_voucher/to_add">点击这里去创建</a>
<h2>3.请检查采购单是否为空，若为空，则请创建</h2>
<a href="${pageContext.request.contextPath}/claim_voucher/to_add">点击这里去创建</a>
<h1>二.删除部门问题</h1>
<h2>1.若此部门下有员工存在，则不能直接删除部门，需要删除所有员工后，在删除部门</h2>
<a onclick="if (${(sessionScope.employee.post== Contant.POST_FM || sessionScope.employee.post == Contant.POST_GM) || sessionScope.employee.post == Contant.POST_ADMIN}){
        location.href = '${pageContext.request.contextPath}/employee/list';
        }else {
        alert('您没有权限');
        }">点击这里去删除</a>
<h1>三.删除员工问题</h1>
<h2>1.若此员工下存在采购单，不可以直接删除此员工，需联系管理员删除其后台记录</h2>
<h1>四.登录错误</h1>
<h2>1.登录密码错误三次会将此账号封禁30分钟，如果需要请联系管理员解封</h2>
<h2>2.未存在登录账户</h2>
</body>
</html>
