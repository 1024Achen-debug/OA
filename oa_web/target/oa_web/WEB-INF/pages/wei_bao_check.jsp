<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.Liuyichen.oa.global.Contant" %>
<%@ include file="/WEB-INF/pages/top.jsp" %>
<section id="content" class="table-layout animated fadeIn">
    <div class="tray tray-center">
        <!--startprint3-->
        <div class="content-header">
            <h2> 维保申请单 </h2>
            <p class="lead"></p>
        </div>
        <div class="admin-form theme-primary mw1000 center-block" style="padding-bottom: 175px;">
            <div class="panel heading-border">
                <div class="panel-body bg-light">
                    <div class="section-divider mt20 mb40">
                        <span> 基本信息 </span>
                    </div>
                    <div class="section row">
                        <div class="col-md-2">事由</div>
                        <div class="col-md-6">${weibao.cause}</div>
                    </div>
                    <div class="section row">
                        <div class="col-md-2">创建人</div>
                        <div class="col-md-4">${weibao.creater.name}</div>
                        <div class="col-md-2">创建时间</div>
                        <div class="col-md-4"><spring:eval expression="weibao.createTime"/></div>
                    </div>
                    <div class="section row">
                        <div class="col-md-2">待处理人</div>
                        <div class="col-md-4">${weibao.dealer.name}</div>
                        <div class="col-md-2">状态</div>
                        <div class="col-md-4">${weibao.status}</div>
                    </div>
                    <div class="section-divider mt20 mb40">
                        <span> 费用明细 </span>
                    </div>
                    <div class="section row">
                        <C:forEach items="${items}" var="item">
                            <div class="col-md-3">${item.item}</div>
                            <div class="col-md-3">${item.amount}</div>
                            <div class="col-md-5">${item.comment}</div>
                        </C:forEach>
                    </div>
                    <div class="section row">
                        <div class="col-md-2">总金额</div>
                        <div class="col-md-6">${weibao.totalAmount}</div>
                    </div>
                    <div class="section-divider mt20 mb40">
                        <span> 处理流程 </span>
                    </div>
                    <div class="section row">
                        <c:forEach items="${records}" var="record">
                            <div class="col-md-1">${record.dealer.name}</div>
                            <div class="col-md-3"><spring:eval expression="record.dealTime"/></div>
                            <div class="col-md-1">${record.dealWay}</div>
                            <div class="col-md-2">${record.dealWay}</div>
                            <div class="col-md-5">备注：${record.comment}</div>
                        </c:forEach>
                        <table id="message-table" class="table admin-form theme-warning tc-checkbox-1">
                            <thead>
                            <tr class="">
                                <th class="hidden-xs">盖章</th>
                                <th class="hidden-xs">盖章人</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${list}" var="image">
                                <tr class="message-unread">
                                    <td><img src="${pageContext.request.contextPath}/fileUpload/temp/${image.imageName}"
                                             alt="avatar" height="100px" width="100px" ondragstart="return false;"></td>
                                    <td>${image.createSn}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!--endprint3-->
                    <form:form id="admin-form" name="addForm" action="${pageContext.request.contextPath}/wei_bao/check"
                               modelAttribute="record" enctype="multipart/form-data" method="post">
                        <form:hidden path="weiBaoId"/>
                        <div class="panel-body bg-light">
                            <div class="section">
                                <label for="comment" class="field prepend-icon">
                                    <form:input path="comment" cssClass="gui-input" placeholder="备注..."/>
                                    <label for="comment" class="field-icon">
                                        <i class="fa fa-lock">
                                        </i>
                                    </label>
                                </label>
                                <div class="panel-body bg-light">
                                    <div class="section-divider mt20 mb40">
                                        <span>上传印章 </span>
                                    </div>
                                </div>
                                <input type="file" name="file" multiple/><br>
                            </div>
                            <div class="panel-footer text-right">
                                <c:if test="${sessionScope.employee.post ==Contant.POST_FM || sessionScope.employee.post==Contant.POST_GM || sessionScope.employee.post == Contant.POST_CM ||sessionScope.employee.post == Contant.POST_CM2||sessionScope.employee.post==Contant.POST_SHENJI||sessionScope.employee.post==Contant.POST_SHENJI2}">
                                    <input type="submit" class="button" name="dealWay" value="${Contant.DEAL_PASS}">
                                    <input type="submit" class="button" name="dealWay" value="${Contant.DEAL_BACK}">
                                    <input type="submit" class="button" name="dealWay" value="${Contant.DEAL_REJECT}">
                                </c:if>
                                <c:if test="${sessionScope.employee.post==Contant.POST_CASHIER}">
                                    <input type="submit" class="button" name="dealWay" value="${Contant.DEAL_PAID}">
                                </c:if>
                                <input type=button name='button_export' value='打印' class="button" onclick=preview(3)>
                                <button type="button" class="button" onclick="javascript:window.history.go(-1);"> 返回
                                </button>

                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</section>

<%@ include file="/WEB-INF/pages/bottom.jsp" %>
