<%--
  Created by IntelliJ IDEA.
  User: 刘怡琛
  Date: 2020/5/22
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/top.jsp" %>

<section id="content" class="table-layout animated fadeIn">
    <div class="tray tray-center">
        <div class="content-header">
            <h2> 修改密码 </h2>
            <p class="lead"></p>
        </div>
        <div class="admin-form theme-primary mw1000 center-block" style="padding-bottom: 175px;">
            <div class="panel heading-border">
                <form method="post" action="change_password" id="admin-form">
                    <div class="panel-body bg-light">
                        <div class="section-divider mt20 mb40">
                            <span> 基本信息 </span>
                        </div>
                        <div class="section row">
                            <div class="col-md-6">
                                <label for="old" class="field prepend-icon">
                                    <input type="password" name="old" id="old" class="gui-input" placeholder="原始密码...">
                                    <label for="old" class="field-icon">
                                        <i class="fa fa-lock"></i>
                                    </label>
                                </label>
                            </div>
                        </div>
                        <div class="section row">
                            <div class="col-md-6">
                                <label for="new1" class="field prepend-icon">
                                    <input type="password" name="new1" id="new1" class="gui-input"
                                           placeholder="重复密码...">
                                    <label for="new1" class="field-icon">
                                        <i class="fa fa-lock"></i>
                                    </label>
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="new2" class="field prepend-icon">
                                    <input type="password" name="new2" id="new2" class="gui-input"
                                           placeholder="重复密码...">
                                    <label for="new2" class="field-icon">
                                        <i class="fa fa-lock"></i>
                                    </label>
                                </label>
                            </div>
                        </div>
                        <div class="panel-footer text-right">
                            <button type="submit" class="button"> 修改</button>
                            <button type="button" class="button" onclick="javascript:window.history.go(-1);"> 返回
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>


<%@ include file="/WEB-INF/pages/bottom.jsp" %>