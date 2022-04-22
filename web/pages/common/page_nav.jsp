<%--
  Created by IntelliJ IDEA.
  User: hcxs1986
  Date: 2022/3/28
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <c:if test="${ requestScope.page.pageNo > 1}">
        <%-- 页数大于首页 才能出现首页和上一页的选项--%>
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo - 1} ">上一页</a>
    </c:if>

    <%--	页码输出的开始		--%>
    <c:choose>
        <%-- 情况一：总页码小于5页的情况 ，页码的范围是1-总页码	--%>
        <c:when test="${requestScope.page.pageTotal <= 5}">
            <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
                <c:if test="${requestScope.page.pageNo == i}">
                    【${i}】
                </c:if>
                <c:if test="${requestScope.page.pageNo != i}">
                    <a href="${requestScope.page.url}&pageNo=${i}" >${i}</a>
                </c:if>
            </c:forEach>
        </c:when>

        <%-- 情况二：总页码大于5页的情况 ，页码的范围是1-总页码	--%>
        <c:when test="${requestScope.page.pageTotal >= 5}">
            <c:choose>

                <%-- 分支1：当前页码为前面 3 个：1，2，3 的情况，页码范围是：1-5--%>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:forEach begin="1" end="5" var="i">
                        <c:if test="${requestScope.page.pageNo == i}">
                            【${i}】
                        </c:if>
                        <c:if test="${requestScope.page.pageNo != i}">
                            <a href="${requestScope.page.url}&pageNo=${i}" >${i}</a>
                        </c:if>
                    </c:forEach>
                </c:when>

                <%-- 分支2：当前页码为最后 3 个，8，9，10，页码范围是：总页码减 4 - 总页--%>
                <c:when test="${requestScope.page.pageNo >= 8}">
                    <c:forEach begin="6" end="${requestScope.page.pageTotal}" var="i">
                        <c:if test="${requestScope.page.pageNo == i}">
                            【${i}】
                        </c:if>
                        <c:if test="${requestScope.page.pageNo != i}">
                            <a href="${requestScope.page.url}&pageNo=${i}" >${i}</a>
                        </c:if>
                    </c:forEach>
                </c:when>

                <%-- 分支3：4，5，6，7，页码范围是：当前页码减 2 - 当前页码加 2 --%>
                <c:otherwise>
                    <c:forEach begin="${requestScope.page.pageNo - 2}" end="${requestScope.page.pageNo + 2}" var="i">
                        <c:if test="${requestScope.page.pageNo == i}">
                            【${i}】
                        </c:if>
                        <c:if test="${requestScope.page.pageNo != i}">
                            <a href="${requestScope.page.url}&pageNo=${i}" >${i}</a>
                        </c:if>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </c:when>

    </c:choose>
    <%--	页码输出的结束		--%>

    <%-- 页数小于末页 才能出现末页和下一页的选项--%>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal }">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo + 1} ">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal} ">末页</a>
    </c:if>
    共${ requestScope.page.pageTotal}页，
    ${ requestScope.page.pageTotalCount}条记录 到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input id="b1" type="button" value="确定">
    <script type="text/javascript">
        //给确定标签绑定点击事件
        $("#b1").click(function () {
            //跳转到指定的页码
            var pageNo = $("#pn_input").val();

            var pageTotal = ${requestScope.page.pageTotal};

            if (pageNo < 1 || pageNo > pageTotal ){
                alert("请输入正确的页码")
                return  false;
            }

            //java Script语言中提供了一个location地址栏对象
            //有一个属性href，可以获取浏览器地址栏中的地址
            //href属性可读可写
            location.href =  "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
            // alert(location.href)
        })
    </script>
</div>
