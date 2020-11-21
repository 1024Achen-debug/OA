function myFunction() {
    if (confirm("是否删除此员工")) {
        location.href = "${pageContext.request.contextPath}/employee/remove?sn=${emp.sn}";
    } else {
        location.href = ""
    }
}