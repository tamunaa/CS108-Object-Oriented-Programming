<%
    Boolean incorrectData = (Boolean) request.getAttribute("incorrect");
    if (incorrectData != null && incorrectData){
        out.println("Either your username or password is incorrect. try again");
    }

    Boolean emptyData = (Boolean) request.getAttribute("empty data");
    if (emptyData != null && emptyData){
        out.println("username and password cannot be empty. try again");
    }
%>