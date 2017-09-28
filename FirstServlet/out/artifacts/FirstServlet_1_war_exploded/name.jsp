<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Name Page</title>
</head>
<body>
    <h3>Hello from JSP</h3>

    <strong>
        <%--<%--%>
            <%--String name = (String)request.getAttribute("SUPER-NAME");--%>
            <%--out.write(name);--%>
        <%--%>--%>
        <ol>
        <%
            for (int i = 0; i < 10; i++) {
        %>
            <li><%= (String)request.getAttribute("SUPER-NAME")%></li>
        <%
            }
        %>
        </ol>
    </strong>

</body>
</html>
