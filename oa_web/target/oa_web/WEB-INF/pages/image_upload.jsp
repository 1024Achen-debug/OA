<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: 刘怡琛
  Date: 2020/5/21
  Time: 9:26
  To change this template use File | Settings | File Templates.
--%>
<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8"/>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/top.jsp" %>
<section id="content" class="table-layout animated fadeIn">
    <div class="tray tray-center">
        <div class="content-header">
            <h2> 上传图片 </h2>
            <p class="lead"></p>
        </div>
        <div class="admin-form theme-primary mw1000 center-block" style="padding-bottom: 175px;">
            <div class="panel heading-border">
                <form:form action="${pageContext.request.contextPath}/image/upload" id="admin-form" name="addForm"
                           enctype="multipart/form-data" method="post">
                    <div class="panel-body bg-light">
                        <div class="section-divider mt20 mb40">
                            <span> 上传图片 </span>
                        </div>
                    </div>
                    <input type="file" name="file" multiple/><br>
                    <div class="panel-footer text-right">
                        <input type="submit" class="button" value="提交">
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</section>


<%@ include file="/WEB-INF/pages/bottom.jsp" %>